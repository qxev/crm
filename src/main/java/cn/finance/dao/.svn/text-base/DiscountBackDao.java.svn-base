package cn.finance.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.finance.model.DiscountBack;
import cn.springside.modules.orm.Page;

public class DiscountBackDao extends HibernateDaoSupport {

	@SuppressWarnings("unchecked")
	public List<DiscountBack> getAll(Page<DiscountBack> page,boolean hastotal) {
		String []pars = StringUtils.split(page.getFilter(),"|");
		Map <String,Integer>par = new HashMap<String,Integer>();
		StringBuffer sb = new StringBuffer();
		sb.append(" from DiscountBack where deleted = 0 ");
		par = setParameters(pars, sb);
		//设置排序
		if (page.getOrderBy() != null && !"".equals(page.getOrderBy())) {
			sb.append(" order by ");
			sb.append(page.getOrderBy());
			sb.append(" ");
			sb.append(page.getOrder());
		}
		Query query = getSession().createQuery(sb.toString());
		if (!hastotal){
			query.setMaxResults(page.getPageSize());
			query.setFirstResult((page.getPageNo() - 1) * page.getPageSize());
		}
		setQuery(page, pars, par, query);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public DiscountBack getDiscountBackByDate(Date date, Integer accountId) {
		Object[] obj = { date, date, accountId };
		List<DiscountBack> list = (List<DiscountBack>)this.getHibernateTemplate().find(
				" from DiscountBack where startDate <= ? and endDate >= ? and account.id = ? ", obj);
		if (list != null && list.size() > 0) {
			return (DiscountBack) list.get(0);
		}
		return null;
	}

	private void setQuery(Page<DiscountBack> page, String[] pars,
			Map<String, Integer> par, Query query) {
		if (pars.length==6){
			for (Map.Entry<String, Integer> entry : par.entrySet()) {
				query.setInteger(entry.getKey(), entry.getValue());
			}
			if (!"0".equals(pars[4])) {
				query.setString("startDate", pars[4]);
			}
			if (!"0".equals(pars[5])) {
				query.setString("endDate", pars[5]);
			}
		}
	}
	
	public int getTotalCount(Page<DiscountBack> page) {
		String []pars = StringUtils.split(page.getFilter(),"|");
		Map <String,Integer>par = new HashMap<String,Integer>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(*) from DiscountBack where deleted = 0  ");
		par = setParameters(pars, sb);
		Query query = getSession().createQuery(sb.toString());
		setQuery(page, pars, par, query);
		return ((Number) query.uniqueResult()).intValue();
	}
	
	public int getCountByBetweenDay(String startDate, String endDate, Integer id, Integer accountId) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(*) from DiscountBack where deleted = 0 and account.id = :accountId and ((:startDate between startDate and endDate) or (:endDate between startDate and endDate)) ");
		if (id!=0){
			sb.append(" and id != :id");
		}
		Query query = getSession().createQuery(sb.toString());
		query.setString("startDate", startDate);
		query.setString("endDate", endDate);
		query.setInteger("accountId", accountId);
		if (id!=0){
			query.setInteger("id", id);	
		}
		return ((Number) query.uniqueResult()).intValue();
	}
	
	private Map<String, Integer> setParameters(String []pars,
			StringBuffer sb) {
		Map <String,Integer>par = new HashMap<String,Integer>();
		if (pars.length==6){
			Integer clientId = NumberUtils.toInt(pars[0]);
			if (clientId!=0){
				sb.append(" and account.project.client.id = :clientId ");
				par.put("clientId", clientId);
			}
			Integer projectId = NumberUtils.toInt(pars[1]);
			if (projectId!=0){
				sb.append(" and account.project.id = :projectId ");
				par.put("projectId", projectId);
			}
			Integer mediaId = NumberUtils.toInt(pars[2]);
			if (mediaId!=0){
				sb.append(" and account.channel.media.id = :mediaId ");
				par.put("mediaId", mediaId);
			}
			Integer channelId = NumberUtils.toInt(pars[3]);
			if (channelId!=0){
				sb.append(" and account.channel.id = :channelId ");
				par.put("channelId", channelId);
			}
			if (!"0".equals(pars[4])) {
				sb.append(" and startDate >= :startDate");
			}
			if (!"0".equals(pars[5])) {
				sb.append(" and startDate <= :endDate");
			}
		}
		return par;
	}
	
	public void save(DiscountBack discountBack) {
		this.getHibernateTemplate().saveOrUpdate(discountBack);
	}
	
	public void delete(DiscountBack discountBack) {
		this.getHibernateTemplate().delete(discountBack);
	}
	
	public DiscountBack createDiscountBack() {
		DiscountBack discountBack = new DiscountBack();
		discountBack.setCreateAt(new Date());
		return discountBack;
	}

	public DiscountBack getDiscountBackById(Integer id) {
		return (DiscountBack)this.getHibernateTemplate().get(DiscountBack.class, id);
	}
}
