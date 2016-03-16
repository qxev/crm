package cn.finance.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.finance.model.Media;
import cn.springside.modules.orm.Page;

public class MediaDao extends HibernateDaoSupport {
	
	@SuppressWarnings("unchecked")
	public List<Media> getAll(Page<Media> page, boolean hasTotal) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select {m.*} from media {m} ");
		sb.append(" left join channel on channel.media_id = m.id ");
		sb.append(" left join account on channel.id = account.channel_id ");
		sb.append(" left join project p on p.id = account.project_id  ");
		sb.append(" left join client c on c.id = p.client_id  ");
		sb.append(" where m.deleted = 0 ");
		Map<String, Integer> par = setParameters(page.getFilter(), sb);
		sb.append(" group by m.id ");
		//设置筛选条件
		if (page.getOrderBy() != null && !"".equals(page.getOrderBy())) {
			sb.append("order by ");
			sb.append(page.getOrderBy());
			sb.append(" ");
			sb.append(page.getOrder());
		} else {
			sb.append("order by m.name asc ");
		}
		SQLQuery query = this.getSession().createSQLQuery(sb.toString());
		query.addEntity("m", Media.class);
		if (!hasTotal) {
			query.setFirstResult((page.getPageNo() - 1) * page.getPageSize());
		}
		for (Map.Entry<String, Integer> entry : par.entrySet()) {
			query.setInteger(entry.getKey(), entry.getValue());
		}
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Media> getAll(){
		Query query = getSession().createQuery(" from Media where deleted = 0 ");
		return query.list();
	}
	
	private Map<String, Integer> setParameters(String filter,
			StringBuffer sb) {
		Map <String,Integer>par = new HashMap<String,Integer>();
		if (filter != null && !"".equals(filter)) {
			String []pars = StringUtils.split(filter,"|");
			if (pars.length==3){
				Integer clientId = NumberUtils.toInt(pars[0]);
				if (clientId!=0){
					sb.append(" and c.id = :clientId ");
					par.put("clientId", clientId);
				}
				Integer projectId = NumberUtils.toInt(pars[1]);
				if (projectId!=0){
					sb.append(" and p.id = :projectId ");
					par.put("projectId", projectId);
				}
				Integer statusId = NumberUtils.toInt(pars[2]);
				if (statusId!=0){
					sb.append(" and m.status = :statusId ");
					par.put("statusId", statusId);
				}

			}
		}
		return par;
	}
	
	@SuppressWarnings("unchecked")
	public Media getMediaBySv3Id(Integer sv3Id) {
		List<Media> list = (List<Media>)this.getHibernateTemplate().find(
				" from Media where sv3Id = ? ", sv3Id);
		if (list != null && list.size() > 0) {
			return (Media) list.get(0);
		}
		return null;
	}

	public int getTotalCount(Page<Media> page) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(distinct(m.id)) from media m ");
		sb.append(" left join channel on channel.media_id = m.id ");
		sb.append(" left join account on channel.id = account.channel_id ");
		sb.append(" left join project p on p.id = account.project_id  ");
		sb.append(" left join client c on c.id = p.client_id  ");
		sb.append(" where m.deleted = 0 ");
		Map<String, Integer> par = setParameters(page.getFilter(), sb);
		Query query = getSession().createSQLQuery(sb.toString());
		for (Map.Entry<String, Integer> entry : par.entrySet()) {
			query.setInteger(entry.getKey(), entry.getValue());
		}
		return ((Number) query.uniqueResult()).intValue();
	}
	
	public Media getMediaById(Integer id) {
		return (Media)this.getHibernateTemplate().get(Media.class, id);
	}
	
	public void save(Media media) {
		this.getHibernateTemplate().saveOrUpdate(media);
	}
	
	public Media createMedia() {
		Media media = new Media();
		media.setCreateAt(new Date());
		return media;
	}
}
