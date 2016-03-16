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

import cn.finance.model.Account;
import cn.finance.model.Channel;
import cn.finance.util.Constant;
import cn.springside.modules.orm.Page;

public class ChannelDao extends HibernateDaoSupport {

	@SuppressWarnings("unchecked")
	public List<Channel> getAll(Page<Channel> page, boolean hasTotal) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select {ch.*} from channel {ch} ");
		sb.append(" left join media m on m.id = ch.media_id ");
		sb.append(" left join account on ch.id = account.channel_id ");
		sb.append(" left join project p on p.id = account.project_id  ");
		sb.append(" left join client c on c.id = p.client_id  ");
		sb.append(" where m.deleted = 0 ");
		Map<String, Integer> par = setParameters(page.getFilter(), sb);
		sb.append(" group by ch.id ");
		// 设置筛选条件
		if (page.getOrderBy() != null && !"".equals(page.getOrderBy())) {
			sb.append("order by ");
			sb.append(page.getOrderBy());
			sb.append(" ");
			sb.append(page.getOrder());
		} else {
			sb.append("order by m.name,ch.name asc ");
		}
		SQLQuery query = getSession().createSQLQuery(sb.toString());
		query.addEntity("ch", Channel.class);
		if (!hasTotal) {
			query.setFirstResult((page.getPageNo() - 1) * page.getPageSize());
		}
		for (Map.Entry<String, Integer> entry : par.entrySet()) {
			query.setInteger(entry.getKey(), entry.getValue());
		}
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Channel> getAllNo(Page<Channel> page, boolean hasTotal) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select {ch.*} from channel {ch} ");
		sb.append(" left join media m on m.id = ch.media_id ");
		sb.append(" left join account on ch.id = account.channel_id ");
		sb.append(" left join project p on p.id = account.project_id  ");
		sb.append(" left join client c on c.id = p.client_id  ");
		sb.append(" where m.deleted = 0 ");
		Map<String, Integer> par = setParametersNo(page.getFilter(), sb);
		sb.append(" group by ch.id ");
		// 设置筛选条件
		if (page.getOrderBy() != null && !"".equals(page.getOrderBy())) {
			sb.append("order by ");
			sb.append(page.getOrderBy());
			sb.append(" ");
			sb.append(page.getOrder());
		} else {
			sb.append("order by m.name,ch.name asc ");
		}
		SQLQuery query = getSession().createSQLQuery(sb.toString());
		query.addEntity("ch", Channel.class);
		if (!hasTotal) {
			query.setFirstResult((page.getPageNo() - 1) * page.getPageSize());
		}
		for (Map.Entry<String, Integer> entry : par.entrySet()) {
			query.setInteger(entry.getKey(), entry.getValue());
		}
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Channel> getAllByMediaId(Integer mediaId) {
		return (List<Channel>) this.getHibernateTemplate().find(" from Channel c left join fetch c.media m where m.id = ? ", mediaId);
	}

	public Channel getById(Integer id) {
		return (Channel) this.getHibernateTemplate().get(Channel.class, id);
	}

	@SuppressWarnings("unchecked")
	public Channel getChannelBySv3Id(Integer sv3Id) {
		List<Channel> list = (List<Channel>) this.getHibernateTemplate().find(" from Channel where sv3Id = ? ", sv3Id);
		if (list != null && list.size() > 0) {
			return (Channel) list.get(0);
		}
		return null;
	}

	private Map<String, Integer> setParameters(String filter, StringBuffer sb) {
		Map<String, Integer> par = new HashMap<String, Integer>();
		if (filter != null && !"".equals(filter)) {
			String[] pars = StringUtils.split(filter, "|");
			if (pars.length == 4) {
				Integer clientId = NumberUtils.toInt(pars[0]);
				if (clientId != 0) {
					sb.append(" and c.id = :clientId ");
					par.put("clientId", clientId);
				}
				Integer projectId = NumberUtils.toInt(pars[1]);
				if (projectId != 0) {
					sb.append(" and p.id = :projectId ");
					par.put("projectId", projectId);
				}
				Integer mediaId = NumberUtils.toInt(pars[2]);
				if (mediaId != 0) {
					sb.append(" and m.id = :mediaId ");
					par.put("mediaId", mediaId);
				}
				Integer statusId = NumberUtils.toInt(pars[3]);
				if (statusId != 0) {
					sb.append(" and ch.status = :statusId ");
					par.put("statusId", statusId);
				}

			}
		}
		return par;
	}

	private Map<String, Integer> setParametersNo(String filter, StringBuffer sb) {
		Map<String, Integer> par = new HashMap<String, Integer>();
		if (filter != null && !"".equals(filter)) {
			String[] pars = StringUtils.split(filter, "|");
			if (pars.length == 4) {
				Integer clientId = NumberUtils.toInt(pars[0]);
				// if (clientId!=0){
				sb.append(" and c.id = :clientId ");
				par.put("clientId", clientId);
				// }
				Integer projectId = NumberUtils.toInt(pars[1]);
				if (projectId != 0) {
					sb.append(" and p.id = :projectId ");
					par.put("projectId", projectId);
				}
				Integer mediaId = NumberUtils.toInt(pars[2]);
				if (mediaId != 0) {
					sb.append(" and m.id = :mediaId ");
					par.put("mediaId", mediaId);
				}
				Integer statusId = NumberUtils.toInt(pars[3]);
				if (statusId != 0) {
					sb.append(" and ch.status = :statusId ");
					par.put("statusId", statusId);
				}

			}
		} else {
			sb.append(" and c.id = :clientId ");
			par.put("clientId", 0);
		}
		return par;
	}

	public int getTotalCount(Page<Channel> page) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(distinct(ch.id)) from channel ch ");
		sb.append(" left join media m on m.id = ch.media_id ");
		sb.append(" left join account on ch.id = account.channel_id ");
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

	public int getTotalCountNo(Page<Channel> page) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(distinct(ch.id)) from channel ch ");
		sb.append(" left join media m on m.id = ch.media_id ");
		sb.append(" left join account on ch.id = account.channel_id ");
		sb.append(" left join project p on p.id = account.project_id  ");
		sb.append(" left join client c on c.id = p.client_id  ");
		sb.append(" where m.deleted = 0 ");
		Map<String, Integer> par = setParametersNo(page.getFilter(), sb);
		Query query = getSession().createSQLQuery(sb.toString());
		for (Map.Entry<String, Integer> entry : par.entrySet()) {
			query.setInteger(entry.getKey(), entry.getValue());
		}
		return ((Number) query.uniqueResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	public Integer getAllStatusByMediaId(Integer mediaId) {
		List<Account> list = (List<Account>) this.getHibernateTemplate().find(" from Channel c where c.media.id = ? ", mediaId);
		if (list != null && list.size() > 0) {
			return Constant.STATUS_ACTIVE;
		} else {
			return Constant.STATUS_INVALID;
		}
	}

	public void save(Channel channel) {
		this.getHibernateTemplate().saveOrUpdate(channel);
	}

	public Channel createChannel() {
		Channel channel = new Channel();
		channel.setCreateAt(new Date());
		return channel;
	}

}
