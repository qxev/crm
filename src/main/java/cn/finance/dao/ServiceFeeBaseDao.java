package cn.finance.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.finance.model.ServiceFeeBase;
import cn.springside.modules.orm.Page;

public class ServiceFeeBaseDao extends HibernateDaoSupport {

	@SuppressWarnings("unchecked")
	public List<ServiceFeeBase> getAll(Page<ServiceFeeBase> page,boolean hasTotal) {
		String []pars = StringUtils.split(page.getFilter(),"|");
		Map <String,Integer>par = new HashMap<String,Integer>();
		StringBuffer sb = new StringBuffer();
		sb.append(" from ServiceFeeBase where deleted = 0 ");
		par = setParameters(pars, sb);
		//设置排序
		if (page.getOrderBy() != null && !"".equals(page.getOrderBy())) {
			sb.append(" order by ");
			sb.append(page.getOrderBy());
			sb.append(" ");
			sb.append(page.getOrder());
		}
		Query query = getSession().createQuery(sb.toString());
		if (!hasTotal){
			query.setMaxResults(page.getPageSize());
			query.setFirstResult((page.getPageNo() - 1) * page.getPageSize());
		}
		setQuery(page, pars, par, query);
		return query.list();
	}

	private void setQuery(Page<ServiceFeeBase> page, String[] pars,
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
	
	public int getTotalCount(Page<ServiceFeeBase> page) {
		String []pars = StringUtils.split(page.getFilter(),"|");
		Map <String,Integer>par = new HashMap<String,Integer>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(*) from ServiceFeeBase where deleted = 0  ");
		par = setParameters(pars, sb);
		Query query = getSession().createQuery(sb.toString());
		query.setMaxResults(page.getPageSize());
		query.setFirstResult((page.getPageNo() - 1) * page.getPageSize());
		setQuery(page, pars, par, query);
		return ((Number) query.uniqueResult()).intValue();
	}
	
	public int getCountByBetweenDay(String startDate, String endDate, Integer id, Integer accountId) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(*) from ServiceFeeBase where deleted = 0 and account.id = :accountId and ((:startDate between startDate and endDate) or (:endDate between startDate and endDate)) ");
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
	
	public void save(ServiceFeeBase serviceFeeBase) {
		this.getHibernateTemplate().saveOrUpdate(serviceFeeBase);
	}
	
	public ServiceFeeBase createServiceFeeBase() {
		ServiceFeeBase serviceFeeBase = new ServiceFeeBase();
		serviceFeeBase.setCreateAt(new Date());
		return serviceFeeBase;
	}

	public ServiceFeeBase getServiceFeeBaseById(Integer id) {
		return (ServiceFeeBase)this.getHibernateTemplate().get(ServiceFeeBase.class, id);
	}
}
