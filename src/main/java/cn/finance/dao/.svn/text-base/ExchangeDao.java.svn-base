package cn.finance.dao;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.finance.model.Exchange;
import cn.finance.model.ExchangeHistory;
import cn.springside.modules.orm.Page;

public class ExchangeDao extends HibernateDaoSupport {

	public void save(Exchange exchange) {
		this.getHibernateTemplate().saveOrUpdate(exchange);
	}

	public void saveHistory(ExchangeHistory exchangeHistory) {
		this.getHibernateTemplate().saveOrUpdate(exchangeHistory);
	}

	@SuppressWarnings("unchecked")
	public Exchange getExchangeById(Integer id) {
		List<Exchange> list = (List<Exchange>) this.getHibernateTemplate().find(" from Exchange where id = ? ", id);
		if (list != null && list.size() > 0) {
			return (Exchange) list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public ExchangeHistory getExchangeHistoryById(Integer id) {
		List<ExchangeHistory> list = (List<ExchangeHistory>) this.getHibernateTemplate().find(" from ExchangeHistory where id = ? ", id);
		if (list != null && list.size() > 0) {
			return (ExchangeHistory) list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public BigDecimal getExchangeHistoryByDate(Integer exchangeId, Date date) {
		BigDecimal value = new BigDecimal(1);
		Calendar startDate = Calendar.getInstance();
		startDate.setTime(date);
		startDate.add(Calendar.MONTH, -1);
		startDate.set(Calendar.DAY_OF_MONTH, startDate.getActualMinimum(Calendar.DAY_OF_MONTH));
		Calendar endDate = Calendar.getInstance();
		endDate.setTime(date);
		endDate.set(Calendar.DAY_OF_MONTH, endDate.getActualMaximum(Calendar.DAY_OF_MONTH));
		Object[] obj = { exchangeId, startDate.getTime(), endDate.getTime() };
		List<ExchangeHistory> list = (List<ExchangeHistory>) this.getHibernateTemplate().find(
				" from ExchangeHistory where exchange.id = ? and exchangeDate >= ? and exchangeDate <= ? order by exchangeDate desc", obj);
		if (list != null && list.size() > 0) {
			ExchangeHistory history = (ExchangeHistory) list.get(0);
			value = history.getValue();
		}
		return value;
	}

	@SuppressWarnings("unchecked")
	public List<Exchange> getAll() {
		Query query = getSession().createQuery(" from Exchange where deleted = 0 ");
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<ExchangeHistory> getAll(Page<ExchangeHistory> page) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from ExchangeHistory where deleted = 0 ");
		if (page.getOrderBy() != null && !"".equals(page.getOrderBy())) {
			sb.append("order by ");
			sb.append(page.getOrderBy());
			sb.append(" ");
			sb.append(page.getOrder());
		}
		Query query = getSession().createQuery(sb.toString());
		query.setMaxResults(page.getPageSize());
		query.setFirstResult((page.getPageNo() - 1) * page.getPageSize());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<ExchangeHistory> getCurrent() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date date = calendar.getTime();
		List<ExchangeHistory> exchangeHistory = (List<ExchangeHistory>) this.getHibernateTemplate().find(
				" from ExchangeHistory where deleted = 0 and TO_DAYS(exchangeDate) = TO_DAYS(?) ", date);
		return exchangeHistory;
	}

	@SuppressWarnings("unchecked")
	public List<ExchangeHistory> getExchangeHistoryByExchangeId(Integer exchangeId) {
		List<ExchangeHistory> exchangeHistory = (List<ExchangeHistory>) this.getHibernateTemplate().find(
				" from ExchangeHistory where deleted = 0 and exchange.id = ? order by exchangeDate ", exchangeId);
		return exchangeHistory;
	}

	public int getTotalCount(Page<ExchangeHistory> page) {
		Query query = getSession().createQuery(" select count(*) from ExchangeHistory where deleted = 0 ");
		return ((Number) query.uniqueResult()).intValue();
	}

	public void updateFuture(BigDecimal value, Date date, Integer exchangeId) {
		String sql = "update exchange_history set value = :value where TO_DAYS(exchange_date) >= TO_DAYS(:date) and exchange_id = :exchangeId";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setBigDecimal("value", value);
		query.setInteger("exchangeId", exchangeId);
		query.setDate("date", date);
		query.executeUpdate();
	}

	public void updateAccountRate(BigDecimal value, Date startDate, Date endDate, Integer exchangeId) {
		String sql = "update expense set manage_media = original_manage_media*:value, net_aff = original_net_aff*:value "
				+ "        where TO_DAYS(expense_date) >= TO_DAYS(:startDate) and TO_DAYS(expense_date) <= TO_DAYS(:endDate) and "
				+ " expense.account_id in (select id from account where exchange_id = :exchangeId)";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setBigDecimal("value", value);
		query.setInteger("exchangeId", exchangeId);
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
		query.executeUpdate();
	}
}
