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

import cn.finance.model.ServiceFee;
import cn.springside.modules.orm.Page;

public class ServiceFeeDao extends HibernateDaoSupport {

	@SuppressWarnings("unchecked")
	public List<ServiceFee> getAll(Page<ServiceFee> page,boolean hasTotal) {
		String []pars = StringUtils.split(page.getFilter(),"|");
		Map <String,Integer>par = new HashMap<String,Integer>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select {se.*} from service_fee {se} ");

		sb.append(" left join account a on se.account_id = a.id ");
		sb.append(" left join channel ch on ch.id = a.channel_id ");
		sb.append(" left join media m on m.id = ch.media_id ");
		sb.append(" left join project p on p.id = a.project_id  ");
		sb.append(" left join client c on c.id = p.client_id  ");

		if (!(pars.length==7 && "false".equals(pars[6]))){
			sb.append(" join (select max(start_date) d,account_id from service_fee group by account_id) q ");	
			sb.append(" on q.account_id = se.account_id and q.d = se.start_date");
		}
		sb.append(" where se.deleted = 0 ");
		par = setParametersOrder(pars, sb);

		//设置排序
		if (page.getOrderBy() != null && !"".equals(page.getOrderBy())) {
			sb.append(" order by ");
			sb.append(page.getOrderBy());
			sb.append(" ");
			sb.append(page.getOrder());
		}
		SQLQuery query = getSession().createSQLQuery(sb.toString());
		query.addEntity("se", ServiceFee.class);
		if (!hasTotal){
			query.setMaxResults(page.getPageSize());
			query.setFirstResult((page.getPageNo() - 1) * page.getPageSize());
		}
		setQuery(page, pars, par, query);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public ServiceFee getServiceFeeByDate(Date date, Integer accountId, Integer type) {
		Object[] obj = { date, date, accountId, type };
		List<ServiceFee> list = (List<ServiceFee>)this.getHibernateTemplate().find(
				" from ServiceFee where startDate <= ? and endDate >= ? and account.id = ? and type = ? ", obj);
		if (list != null && list.size() > 0) {
			return (ServiceFee) list.get(0);
		}
		return null;
	}
	
	private void setQuery(Page<ServiceFee> page, String[] pars,
			Map<String, Integer> par, Query query) {
		if (pars.length==7){
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
	
	public int getTotalCount(Page<ServiceFee> page) {
		String []pars = StringUtils.split(page.getFilter(),"|");
		Map <String,Integer>par = new HashMap<String,Integer>();
		StringBuffer sb = new StringBuffer();
		if (!(pars.length==7 && "false".equals(pars[6]))){
			sb.append(" select count(distinct account.id) from ServiceFee where deleted = 0 ");
		} else {
			sb.append(" select count(*) from ServiceFee where deleted = 0 ");
		}
		par = setParameters(pars, sb);
		Query query = getSession().createQuery(sb.toString());
		setQuery(page, pars, par, query);
		return ((Number) query.uniqueResult()).intValue();
	}
	
	public int getCountByBetweenDay(String startDate, String endDate, Integer id, Integer accountId) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(*) from ServiceFee where deleted = 0 and account.id = :accountId and ((:startDate between startDate and endDate) or (:endDate between startDate and endDate)) ");
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
		if (pars.length==7){
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
	
	private Map<String, Integer> setParametersOrder(String []pars,
			StringBuffer sb) {
		Map <String,Integer>par = new HashMap<String,Integer>();
		if (pars.length==7){
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
			Integer mediaId = NumberUtils.toInt(pars[2]);
			if (mediaId!=0){
				sb.append(" and m.id = :mediaId ");
				par.put("mediaId", mediaId);
			}
			Integer channelId = NumberUtils.toInt(pars[3]);
			if (channelId!=0){
				sb.append(" and ch.id = :channelId ");
				par.put("channelId", channelId);
			}
			if (!"0".equals(pars[4])) {
				sb.append(" and se.start_date >= :startDate");
			}
			if (!"0".equals(pars[5])) {
				sb.append(" and se.start_date <= :endDate");
			}
		}
		return par;
	}
	
	public void save(ServiceFee serviceFee) {
		this.getHibernateTemplate().saveOrUpdate(serviceFee);
	}
	
	public void delete(ServiceFee serviceFee) {
		this.getHibernateTemplate().delete(serviceFee);
	}
	
	public ServiceFee createServiceFee() {
		ServiceFee serviceFee = new ServiceFee();
		serviceFee.setCreateAt(new Date());
		return serviceFee;
	}

	public ServiceFee getServiceFeeById(Integer id) {
		return (ServiceFee)this.getHibernateTemplate().get(ServiceFee.class, id);
	}
}
