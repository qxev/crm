package cn.finance.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.finance.model.SupplementHistory;
import cn.finance.model.bean.SeBean;
import cn.springside.modules.orm.Page;

public class SupplementHistoryDao extends HibernateDaoSupport {

	@SuppressWarnings("unchecked")
	public List<SupplementHistory> getAll(Page<SupplementHistory> page, boolean hasTotal, boolean isAccountTotal) {
		String []pars = StringUtils.split(page.getFilter(),"|");
		StringBuffer sb = new StringBuffer(" from SupplementHistory s where s.deleted = 0 ");
		if (isAccountTotal){
			sb.append(" and s.type <4 "); 
		}
		Map<String, Integer> par = setParameters(page.getFilter(), sb);
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
		setQuerys(page, pars, par, query);
		return query.list();
	}
	
	public int getTotalCount(Page<SupplementHistory> page, boolean isAccountTotal) {
		String []pars = StringUtils.split(page.getFilter(),"|");
		StringBuffer sb = new StringBuffer(" select count(*) from SupplementHistory s where s.deleted = 0  ");
		if (isAccountTotal){
			sb.append(" and s.type <4 "); 
		}
		Map<String, Integer> par = setParameters(page.getFilter(), sb);
		Query query = getSession().createQuery(sb.toString());
		query.setFirstResult(0);
		setQuerys(page, pars, par, query);
		return ((Number) query.uniqueResult()).intValue();
	}
	
	public BigDecimal getSum(Page<SupplementHistory> page) {
		String []pars = StringUtils.split(page.getFilter(),"|");
		StringBuffer sb = new StringBuffer(" select sum(s.totalAmount) from SupplementHistory s  where s.deleted = 0 ");
		Map<String, Integer> par = setParameters(page.getFilter(), sb);
		Query query = getSession().createQuery(sb.toString());
		setQuerys(page, pars, par, query);
		return (BigDecimal) query.uniqueResult();
	}
	
	private void setQuerys(Page<SupplementHistory> page, String[] pars,
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
	
	@SuppressWarnings("unchecked")
	public List<SupplementHistory> getAllPay(Page<SupplementHistory> page, boolean hasTotal) {
		String []pars = StringUtils.split(page.getFilter(),"|");
		StringBuffer sb = new StringBuffer(" from SupplementHistory s where s.deleted = 0 and (s.type=1 or s.type = 4) ");
		Map<String, Integer> par = setParameters(page.getFilter(), sb);
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
		setQuerys(page, pars, par, query);
		return query.list();
	}
	
	public int getTotalPayCount(Page<SupplementHistory> page) {
		String []pars = StringUtils.split(page.getFilter(),"|");
		StringBuffer sb = new StringBuffer(" select count(*) from SupplementHistory s where s.deleted = 0 and (s.type=1 or s.type = 4) ");
		Map<String, Integer> par = setParameters(page.getFilter(), sb);
		Query query = getSession().createQuery(sb.toString());
		query.setFirstResult(0);
		setQuerys(page, pars, par, query);
		return ((Number) query.uniqueResult()).intValue();
	}
	
	
	public BigDecimal getSumPay(Page<SupplementHistory> page) {
		String []pars = StringUtils.split(page.getFilter(),"|");
		StringBuffer sb = new StringBuffer(" select sum(s.payAmount) from SupplementHistory s where s.deleted = 0 and (s.type=1 or s.type = 4) ");
		Map<String, Integer> par = setParameters(page.getFilter(), sb);
		Query query = getSession().createQuery(sb.toString());
		setQuerys(page, pars, par, query);
		return (BigDecimal) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<SupplementHistory> getAllRepayment(Page<SupplementHistory> page, boolean hasTotal) {
		StringBuffer sb = new StringBuffer(" from SupplementHistory s where s.deleted = 0 and s.type = 2 and s.repayAmount > 0");
		String[] pars = addNextPerematers(page.getFilter(), sb);
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
		setQuery(pars, query);
		return query.list();
	}
	
	public BigDecimal getSumRepayment(Page<SupplementHistory> page) {
		StringBuffer sb = new StringBuffer("select sum(s.repayAmount) from SupplementHistory s where s.deleted = 0 and s.type = 2 and s.repayAmount > 0");
		String[] pars = addNextPerematers(page.getFilter(), sb);
		Query query = getSession().createQuery(sb.toString());
		setQuery(pars, query);
		return (BigDecimal) query.uniqueResult();
	}
	
	public BigDecimal getSumRepaymentBetween(Date startDate, Date endDate) {
		StringBuffer sb = new StringBuffer("select sum(s.repayAmount) from SupplementHistory s where s.deleted = 0 and s.type = 2 and s.repayAmount > 0 and s.nextSupplementDate >= :startDate and s.nextSupplementDate <= :endDate");
		Query query = getSession().createQuery(sb.toString());
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
		return (BigDecimal) query.uniqueResult();
	}
	
	public int getAllRepaymentCount(Page<SupplementHistory> page) {
		StringBuffer sb = new StringBuffer(" select count(*) from SupplementHistory s where s.deleted = 0 and s.type = 2 and s.repayAmount > 0 ");
		String[] pars = addNextPerematers(page.getFilter(), sb);
		Query query = getSession().createQuery(sb.toString());
		setQuery(pars, query);
		return ((Number) query.uniqueResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	public List<SeBean> getAllSeAmountHistoryByChannel(Page<SeBean> page, boolean hasTotal){
		StringBuffer sb = new StringBuffer();
		sb.append("select new cn.finance.model.bean.SeBean(sum(s.totalAmount),s.account.channel.name,s.account.channel.media.name,s.account.channel.id)  from SupplementHistory s  where s.deleted = 0 ");
		String[] pars = addPerematers(page.getFilter(), sb);
		sb.append(" group by s.account.channel.id ");
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
		setQuery(pars, query);
		return query.list();
	}

	private void setQuery(String[] pars, Query query) {
		if (pars.length == 2){
			if (!"0".equals(pars[0])) {
				query.setString("startDate", pars[0]);
			}
			if (!"0".equals(pars[1])) {
				query.setString("endDate", pars[1]);
			}
		}
	}
	
	private void setQueryAndId(String[] pars, Query query) {
		if (pars.length == 3){
			if (!"0".equals(pars[0])) {
				query.setString("startDate", pars[0]);
			}
			if (!"0".equals(pars[1])) {
				query.setString("endDate", pars[1]);
			}
			query.setInteger("channelId", Integer.valueOf(pars[2]));
		}
	}
	
	private void setQueryAndProjectId(String[] pars, Query query) {
		if (pars.length == 3){
			if (!"0".equals(pars[0])) {
				query.setString("startDate", pars[0]);
			}
			if (!"0".equals(pars[1])) {
				query.setString("endDate", pars[1]);
			}
			query.setInteger("projectId", Integer.valueOf(pars[2]));
		}
	}

	private String[] addPerematers(String filter, StringBuffer sb) {
		String []pars = StringUtils.split(filter,"|");
		if (pars.length == 2){
			if (!"0".equals(pars[0])) {
				sb.append(" and s.supplementDate >= :startDate");
			}
			if (!"0".equals(pars[1])) {
				sb.append(" and s.supplementDate <= :endDate");
			}
		}
		return pars;
	}
	
	private String[] addNextPerematers(String filter, StringBuffer sb) {
		String []pars = StringUtils.split(filter,"|");
		if (pars.length == 2){
			if (!"0".equals(pars[0])) {
				sb.append(" and s.nextSupplementDate >= :startDate");
			}
			if (!"0".equals(pars[1])) {
				sb.append(" and s.nextSupplementDate <= :endDate");
			}
		}
		return pars;
	}
	
	private String[] addPerematerAndId(Page<SeBean> page, StringBuffer sb) {
		String []pars = StringUtils.split(page.getFilter(),"|");
		if (pars.length == 3){
			if (!"0".equals(pars[0])) {
				sb.append(" and s.supplementDate >= :startDate");
			}
			if (!"0".equals(pars[1])) {
				sb.append(" and s.supplementDate <= :endDate");
			}
			sb.append(" and s.account.channel.id= :channelId");
		}
		return pars;
	}
	
	private String[] addPerematerAndProjectId(Page<SeBean> page, StringBuffer sb) {
		String []pars = StringUtils.split(page.getFilter(),"|");
		if (pars.length == 3){
			if (!"0".equals(pars[0])) {
				sb.append(" and s.supplementDate >= :startDate");
			}
			if (!"0".equals(pars[1])) {
				sb.append(" and s.supplementDate <= :endDate");
			}
			sb.append(" and s.account.project.id= :projectId");
		}
		return pars;
	}

	public BigDecimal getSumSeAmountHistoryByChannel(Page<SeBean> page) {
		StringBuffer sb = new StringBuffer();
		sb.append("select sum(s.totalAmount)   from SupplementHistory s  where s.deleted = 0 ");
		String[] pars = addPerematers(page.getFilter(), sb);
		Query query = getSession().createQuery(sb.toString());
		setQuery(pars, query);
		return (BigDecimal) query.uniqueResult();
	}
	
	public int getTotalSeAmountHistoryCountByChannel(Page<SeBean> page) {
		StringBuffer sb = new StringBuffer();
		sb.append("select new cn.finance.model.bean.SeBean(sum(s.totalAmount),s.account.channel.name,s.account.channel.media.name,s.account.channel.id)  from SupplementHistory s  where s.deleted = 0 ");
		String[] pars = addPerematers(page.getFilter(), sb);
		sb.append(" group by s.account.channel.id ");
		Query query = getSession().createQuery(sb.toString());
		setQuery(pars, query);
		query.setFirstResult(0);
		return query.list().size();
	}
	
	@SuppressWarnings("unchecked")
	public List<SeBean> getAllSeAmountHistoryByProject(Page<SeBean> page, boolean hasTotal) {
		StringBuffer sb = new StringBuffer();
		sb.append("select new cn.finance.model.bean.SeBean(sum(s.totalAmount),s.account.project.name,s.account.channel.name,s.account.project.id)  from SupplementHistory s  where s.deleted = 0 ");
		String[] pars = addPerematerAndId(page, sb);
		sb.append("  group by s.account.project.id ");
		if (page.getOrderBy() != null && !"".equals(page.getOrderBy())) {
			sb.append(" order by ");
			sb.append(page.getOrderBy());
			sb.append(" ");
			sb.append(page.getOrder());
		}
		Query query = getSession().createQuery(sb.toString());
		setQueryAndId(pars, query);
		if (!hasTotal){
			query.setMaxResults(page.getPageSize());
			query.setFirstResult((page.getPageNo() - 1) * page.getPageSize());
		}
		return query.list();
	}

	
	public BigDecimal getSumSeAmountHistoryByProject(Page<SeBean> page) {
		StringBuffer sb = new StringBuffer();
		sb.append("select sum(s.totalAmount) from SupplementHistory s  where s.deleted = 0 ");
		String[] pars = addPerematerAndId(page, sb);
		Query query = getSession().createQuery(sb.toString());
		setQueryAndId(pars, query);
		return (BigDecimal) query.uniqueResult();
	}
	
	
	public int getTotalSeAmountHistoryCountByProject(Page<SeBean> page) {
		StringBuffer sb = new StringBuffer();
		sb.append("select new cn.finance.model.bean.SeBean(sum(s.totalAmount),s.account.project.name,s.account.channel.name,s.account.project.id)  from SupplementHistory s  where s.deleted = 0 ");
		String[] pars = addPerematerAndId(page, sb);
		sb.append("  group by s.account.project.id ");
		Query query = getSession().createQuery(sb.toString());
		setQueryAndId(pars, query);
		query.setFirstResult(0);
		return query.list().size();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<SeBean> getAllSeAmountHistoryByAccount(Page<SeBean> page,boolean hasTotal) {
		StringBuffer sb = new StringBuffer();
		sb.append("select new cn.finance.model.bean.SeBean(sum(s.totalAmount),s.account.name,s.account.project.name,s.account.id)  from SupplementHistory s  where s.deleted = 0 ");
		String[] pars = addPerematerAndProjectId(page, sb);
		sb.append("  group by s.account.id ");
		if (page.getOrderBy() != null && !"".equals(page.getOrderBy())) {
			sb.append("order by ");
			sb.append(page.getOrderBy());
			sb.append(" ");
			sb.append(page.getOrder());
		}
		Query query = getSession().createQuery(sb.toString());
		setQueryAndProjectId(pars, query);
		if (!hasTotal){
			query.setMaxResults(page.getPageSize());
			query.setFirstResult((page.getPageNo() - 1) * page.getPageSize());
		}
		return query.list();
	}

	public BigDecimal getSumSeAmountHistoryByAccount(Page<SeBean> page) {
		StringBuffer sb = new StringBuffer();
		sb.append("select sum(s.totalAmount)  from SupplementHistory s  where s.deleted = 0 ");
		String[] pars = addPerematerAndProjectId(page, sb);
		Query query = getSession().createQuery(sb.toString());
		setQueryAndProjectId(pars, query);
		return (BigDecimal) query.uniqueResult();
	}
	
	public int getTotalSeAmountHistoryCountByAccount(Page<SeBean> page) {
		StringBuffer sb = new StringBuffer();
		sb.append("select new cn.finance.model.bean.SeBean(sum(s.totalAmount),s.account.name,s.account.project.name,s.account.id)  from SupplementHistory s  where s.deleted = 0 ");
		String[] pars = addPerematerAndProjectId(page, sb);
		sb.append("  group by s.account.id ");
		Query query = getSession().createQuery(sb.toString());
		setQueryAndProjectId(pars, query);
		query.setFirstResult(0);
		return query.list().size();
	}
	
	private Map<String, Integer> setParameters(String filter,
			StringBuffer sb) {
		Map <String,Integer>par = new HashMap<String,Integer>();
		if (filter != null && !"".equals(filter)) {
			String []pars = StringUtils.split(filter,"|");
			if (pars.length==6){
				Integer clientId = NumberUtils.toInt(pars[0]);
				if (clientId!=0){
					sb.append(" and s.account.project.client.id = :clientId ");
					par.put("clientId", clientId);
				}
				Integer projectId = NumberUtils.toInt(pars[1]);
				if (projectId!=0){
					sb.append(" and s.account.project.id = :projectId ");
					par.put("projectId", projectId);
				}
				Integer mediaId = NumberUtils.toInt(pars[2]);
				if (mediaId!=0){
					sb.append(" and s.account.channel.media.id = :mediaId ");
					par.put("mediaId", mediaId);
				}
				Integer channelId = NumberUtils.toInt(pars[3]);
				if (channelId!=0){
					sb.append(" and s.account.channel.id = :channelId ");
					par.put("channelId", channelId);
				}
				if (!"0".equals(pars[4])) {
					sb.append(" and s.supplementDate >= :startDate ");
				}
				if (!"0".equals(pars[5])) {
					sb.append(" and s.supplementDate <= :endDate ");
				}
			}
		}
		return par;
	}
	
	
	public void save(SupplementHistory supplementHistory) {
		this.getHibernateTemplate().saveOrUpdate(supplementHistory);
	}
	
	
	public SupplementHistory findSupplementHistory(Integer id){
		return (SupplementHistory)this.getHibernateTemplate().get(SupplementHistory.class, id);
	}
	
	public SupplementHistory getSupplementHistoryById(Integer id) {
		return (SupplementHistory)this.getHibernateTemplate().get(SupplementHistory.class, id);
	}
	
	public void deleteSupplementHistoryById(SupplementHistory supplementHistory) {
		this.getHibernateTemplate().delete(supplementHistory);
	}
}
