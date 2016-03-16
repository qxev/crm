package cn.finance.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.finance.model.CheckMonth;
import cn.finance.model.Expense;
import cn.finance.model.Pm;
import cn.finance.model.bean.ReportBean;
import cn.finance.model.bean.ServiceAdjustBean;
import cn.springside.modules.orm.Page;

public class ExpenseDao extends HibernateDaoSupport {

	/**
	 * 没办法才写的
	 */
	@Autowired
	private PmDao pmDao;

	public void save(Expense expense) {
		this.getHibernateTemplate().saveOrUpdate(expense);
	}

	public void saveCheck(CheckMonth check) {
		this.getHibernateTemplate().saveOrUpdate(check);
	}

	@SuppressWarnings("unchecked")
	public List<Expense> getExpenseByAccountId(Integer accountId) {
		return (List<Expense>) this.getHibernateTemplate().find(" from Expense where deleted = 0 and account.id = ? ", accountId);
	}

	@SuppressWarnings("unchecked")
	public int getCountByIdDate(Date startDate, Date endDate, Integer accountId) {
		Object[] obj = { startDate, endDate, accountId };
		List list = this.getHibernateTemplate().find(" from Expense where expenseDate between ? and ? and account.id = ? ", obj);
		return list.size();
	}

	public Expense createExpense() {
		Expense expense = new Expense();
		expense.setCreateAt(new Date());
		expense.setServiceFeeAdjust(new BigDecimal(0));
		expense.setNetAff(new BigDecimal(0));
		expense.setDeleted("0");
		return expense;
	}

	public CheckMonth createCheckMonth() {
		CheckMonth checkmonth = new CheckMonth();
		checkmonth.setCreateAt(new Date());
		checkmonth.setDeleted("0");
		return checkmonth;
	}

	@SuppressWarnings("unchecked")
	public List calcData(Date startDate, Date endDate, Integer accountId) {
		Object[] obj = { startDate, endDate, accountId };
		List list = this
				.getHibernateTemplate()
				.find(" select sum(manageMedia+netAff) as costsum, avg(manageMedia+netAff) as costavg,max(manageMedia+netAff) as costmax,min(manageMedia+netAff) as costmin from Expense  where expenseDate between ? and ? and account.id = ? ",
						obj);
		return list;
	}

	@SuppressWarnings("unchecked")
	public List calcData1(String date1, String date2, Integer accountId) {
		SQLQuery query = this
				.getSession()
				.createSQLQuery(
						" select sum(cost) as costsum, avg(cost) as costavg,max(cost) as costmax,min(cost) as costmin from expense  where expense_date between :date1 and :date2 and account_id=:accountId");
		query.setString("date2", date1);
		query.setString("date1", date2);
		query.setInteger("accountId", accountId);
		List list = query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public ReportBean getSumReportByProject(Page<ReportBean> page) {
		StringBuffer sb = new StringBuffer();
		sb.append("select new cn.finance.model.bean.ReportBean(1 as aa, 'aa' as a, sum(e.originalManageMedia),sum(e.originalNetAff), sum(e.manageMedia),sum(e.netAff),sum(e.cost),sum(e.darwinCost),sum(e.discount),sum(e.serviceFee),sum(e.serviceFeeAdjust),sum(e.revenue),sum(e.grossProfit)) ");
		sb.append(" from Expense e where e.account.deleted = 0 and e.account.project.deleted = 0 and e.deleted = 0 ");
		String[] pars = addPerematers(page.getFilter(), sb, false, false);
		Query query = getSession().createQuery(sb.toString());
		setQuery(pars, query);
		List<ReportBean> reportBeans = query.list();
		if (reportBeans != null && reportBeans.size() > 0) {
			return (ReportBean) reportBeans.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public ReportBean getSumReportByAccount(Page<ReportBean> page) {
		StringBuffer sb = new StringBuffer();
		sb.append("select new cn.finance.model.bean.ReportBean(1 as aa, 'aa' as a, sum(e.originalManageMedia),sum(e.originalNetAff), sum(e.manageMedia),sum(e.netAff),sum(e.cost),sum(e.darwinCost),sum(e.discount),sum(e.serviceFee),sum(e.serviceFeeAdjust),sum(e.revenue),sum(e.grossProfit)) ");
		sb.append(" from Expense e where e.account.deleted = 0 and e.deleted = 0 ");
		String[] pars = StringUtils.split(page.getFilter(), "|");
		if (!"0".equals(pars[0])) {
			sb.append(" and e.expenseDate >= :startDate ");
		}
		if (!"0".equals(pars[1])) {
			sb.append(" and e.expenseDate <= :endDate ");
		}
		if (!"0".equals(pars[2])) {
			sb.append(" and e.account.id = :accountId");
		}
		Query query = getSession().createQuery(sb.toString());
		if (!"0".equals(pars[0])) {
			query.setString("startDate", pars[0]);
		}
		if (!"0".equals(pars[1])) {
			query.setString("endDate", pars[1]);
		}
		if (!"0".equals(pars[2])) {
			query.setInteger("accountId", Integer.valueOf(pars[2]));
		}
		List<ReportBean> reportBeans = query.list();
		if (reportBeans != null && reportBeans.size() > 0) {
			return (ReportBean) reportBeans.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public ReportBean getSumReportByAccount(Date startDate, Date endDate, Integer accountId) {
		startDate = DateUtils.addDays(startDate, -1);
		String sql = "select new cn.finance.model.bean.ReportBean(1 as aa, 'aa' as a, sum(e.originalManageMedia),sum(e.originalNetAff), sum(e.manageMedia),sum(e.netAff),sum(e.cost),sum(e.darwinCost),sum(e.discount),sum(e.serviceFee),sum(e.serviceFeeAdjust),sum(e.revenue),sum(e.grossProfit)) "
				+ " from Expense e where e.account.deleted = 0 and e.deleted = 0 and e.expenseDate between ? and ? and e.account.id = ?";
		Object[] obj = { startDate, endDate, accountId };
		List<ReportBean> reportBeans = (List<ReportBean>) this.getHibernateTemplate().find(sql, obj);
		if (reportBeans != null && reportBeans.size() > 0) {
			return (ReportBean) reportBeans.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<ReportBean> getReportByProject(Page<ReportBean> page, boolean hasTotal) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from Expense e where e.account.deleted = 0 and e.account.project.deleted = 0 and e.deleted = 0 ");
		String[] pars = addPerematers(page.getFilter(), sb, true, true);
		if (page.getOrderBy() != null && !"".equals(page.getOrderBy())) {
			sb.append(" order by ");
			sb.append(page.getOrderBy());
			sb.append(" ");
			sb.append(page.getOrder());
		}
		Query query = getSession().createQuery(sb.toString());
		if (!hasTotal) {
			query.setMaxResults(page.getPageSize());
			query.setFirstResult((page.getPageNo() - 1) * page.getPageSize());
		}
		setQuery(pars, query);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<ReportBean> getReportByAccountId(Page<ReportBean> page, boolean hasTotal) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from Expense e where e.account.deleted = 0 and e.deleted = 0 ");
		String[] pars = StringUtils.split(page.getFilter(), "|");
		if (!"0".equals(pars[0])) {
			sb.append(" and e.expenseDate >= :startDate ");
		}
		if (!"0".equals(pars[1])) {
			sb.append(" and e.expenseDate <= :endDate ");
		}
		if (!"0".equals(pars[2])) {
			sb.append(" and e.account.id = :accountId");
		}
		if (page.getOrderBy() != null && !"".equals(page.getOrderBy())) {
			sb.append(" order by ");
			sb.append(page.getOrderBy());
			sb.append(" ");
			sb.append(page.getOrder());
		}
		Query query = getSession().createQuery(sb.toString());
		if (!"0".equals(pars[0])) {
			query.setString("startDate", pars[0]);
		}
		if (!"0".equals(pars[1])) {
			query.setString("endDate", pars[1]);
		}
		if (!"0".equals(pars[2])) {
			query.setInteger("accountId", Integer.valueOf(pars[2]));
		}
		if (!hasTotal) {
			query.setMaxResults(page.getPageSize());
			query.setFirstResult((page.getPageNo() - 1) * page.getPageSize());
		}
		return query.list();
	}

	public int getReportByProjectCount(Page<ReportBean> page) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(*) from Expense e where e.account.deleted = 0 and e.account.project.deleted = 0 and e.deleted = 0 ");
		String[] pars = addPerematers(page.getFilter(), sb, false, true);
		Query query = getSession().createQuery(sb.toString());
		setQuery(pars, query);
		return query.list().size();
	}

	public int getReportCountByAccountId(Page<ReportBean> page) {
		StringBuffer sb = new StringBuffer(" from Expense e where e.account.deleted = 0 and e.deleted = 0 ");
		String[] pars = StringUtils.split(page.getFilter(), "|");
		if (!"0".equals(pars[0])) {
			sb.append(" and e.expenseDate >= :startDate ");
		}
		if (!"0".equals(pars[1])) {
			sb.append(" and e.expenseDate <= :endDate ");
		}
		if (!"0".equals(pars[2])) {
			sb.append(" and e.account.id = :accountId");
		}
		Query query = getSession().createQuery(sb.toString());
		if (!"0".equals(pars[0])) {
			query.setString("startDate", pars[0]);
		}
		if (!"0".equals(pars[1])) {
			query.setString("endDate", pars[1]);
		}
		if (!"0".equals(pars[2])) {
			query.setInteger("accountId", Integer.valueOf(pars[2]));
		}
		return query.list().size();
	}

	private String[] addPerematers(String filter, StringBuffer sb, boolean addFlag, boolean groupFlag) {
		String[] pars = StringUtils.split(filter, "|");
		if (pars.length == 11) {
			if (!"0".equals(pars[0])) {
				sb.append(" and e.expenseDate >= :startDate ");
			}
			if (!"0".equals(pars[1])) {
				sb.append(" and e.expenseDate <= :endDate ");
			}
			// 账户级别报表
			if (!"0".equals(pars[3])) {
				if ("-1".equals(pars[3])) {
					if (addFlag)
						sb.insert(
								0,
								"select new cn.finance.model.bean.ReportBean(e.account.id, e.account.name,sum(e.originalManageMedia),sum(e.originalNetAff),sum(e.manageMedia),sum(e.netAff),sum(e.cost),sum(e.darwinCost),sum(e.discount),sum(e.serviceFee),sum(e.serviceFeeAdjust),sum(e.revenue),sum(e.grossProfit)) ");
					if (!"0".equals(pars[4])) {
						sb.append(" and e.account.project.client.id = :clientId ");
					}
					if (!"0".equals(pars[5])) {
						sb.append(" and e.account.project.id = :projectId ");
					}
					if (!"0".equals(pars[6])) {
						sb.append(" and e.account.channel.media.id = :mediaId ");
					}
					if (!"0".equals(pars[7])) {
						sb.append(" and e.account.channel.id = :channelId ");
					}
					Integer pmId = NumberUtils.toInt(pars[8]);
					if (pmId != 0) {
						Pm pm = pmDao.getPmById(pmId);
						sb.append(" and e.account.currentPms like '%").append(pm.getName()).append("%' ");
					}
					String clientName = pars[9];
					if (!"0".equals(clientName)) {
						sb.append(" and e.account.project.client.name like '%").append(clientName).append("%' ");
					}
					String projectName = pars[10];
					if (!"0".equals(projectName)) {
						sb.append(" and e.account.project.name like '%").append(projectName).append("%' ");
					}
					if (groupFlag)
						sb.append(" group by e.account.id ");

				} else {
					sb.append(" and e.account.project.id = :projectId and e.account.channel.media.id = :mediaId ");
					if (groupFlag)
						sb.append(" group by e.account.id ");
					if (addFlag)
						sb.insert(
								0,
								"select new cn.finance.model.bean.ReportBean(e.account.id, e.account.name,sum(e.originalManageMedia),sum(e.originalNetAff),sum(e.manageMedia),sum(e.netAff),sum(e.cost),sum(e.darwinCost),sum(e.discount),sum(e.serviceFee),sum(e.serviceFeeAdjust),sum(e.revenue),sum(e.grossProfit)) ");
				}
				// 媒体级别报表
			} else if (!"0".equals(pars[2])) {
				sb.append(" and e.account.project.id = :projectId ");
				if (groupFlag)
					sb.append(" group by e.account.channel.media.id ");
				if (addFlag)
					sb.insert(
							0,
							"select new cn.finance.model.bean.ReportBean(e.account.channel.media.id, e.account.channel.media.name,sum(e.originalManageMedia),sum(e.originalNetAff), sum(e.manageMedia),sum(e.netAff),sum(e.cost),sum(e.darwinCost),sum(e.discount),sum(e.serviceFee),sum(e.serviceFeeAdjust),sum(e.revenue),sum(e.grossProfit)) ");
				// 项目级别报表
			} else {
				if (!"0".equals(pars[4])) {
					sb.append(" and e.account.project.client.id = :clientId ");
				}
				if (!"0".equals(pars[5])) {
					sb.append(" and e.account.project.id = :projectId ");
				}
				if (!"0".equals(pars[6])) {
					sb.append(" and e.account.channel.media.id = :mediaId ");
				}
				if (!"0".equals(pars[7])) {
					sb.append(" and e.account.channel.id = :channelId ");
				}
				Integer pmId = NumberUtils.toInt(pars[8]);
				if (pmId != 0) {
					Pm pm = pmDao.getPmById(pmId);
					sb.append(" and e.account.currentPms like '%").append(pm.getName()).append("%' ");
				}
				String clientName = pars[9];
				if (!"0".equals(clientName)) {
					sb.append(" and e.account.project.client.name like '%").append(clientName).append("%' ");
				}
				String projectName = pars[10];
				if (!"0".equals(projectName)) {
					sb.append(" and e.account.project.name like '%").append(projectName).append("%' ");
				}
				if (groupFlag)
					sb.append(" group by e.account.project.id ");
				if (addFlag)
					sb.insert(
							0,
							"select new cn.finance.model.bean.ReportBean(e.account.project.id, e.account.project.name,sum(e.originalManageMedia),sum(e.originalNetAff), sum(e.manageMedia),sum(e.netAff),sum(e.cost),sum(e.darwinCost),sum(e.discount),sum(e.serviceFee),sum(e.serviceFeeAdjust),sum(e.revenue),sum(e.grossProfit)) ");
			}
			// 默认项目级别报表
		} else {
			if (addFlag)
				sb.insert(
						0,
						"select new cn.finance.model.bean.ReportBean(e.account.project.id, e.account.project.name,sum(e.originalManageMedia),sum(e.originalNetAff), sum(e.manageMedia),sum(e.netAff),sum(e.cost),sum(e.darwinCost),sum(e.discount),sum(e.serviceFee),sum(e.serviceFeeAdjust),sum(e.revenue),sum(e.grossProfit)) ");
			if (groupFlag)
				sb.append(" group by e.account.project.id ");
		}
		return pars;
	}

	private void setQuery(String[] pars, Query query) {
		if (pars.length == 11) {
			if (!"0".equals(pars[0])) {
				query.setString("startDate", pars[0]);
			}
			if (!"0".equals(pars[1])) {
				query.setString("endDate", pars[1]);
			}
			if (!"0".equals(pars[3]) && !"-1".equals(pars[3])) {
				query.setInteger("projectId", Integer.valueOf(pars[2]));
				query.setInteger("mediaId", Integer.valueOf(pars[3]));
			} else if (!"0".equals(pars[2])) {
				query.setInteger("projectId", Integer.valueOf(pars[2]));
			}
			if (!"0".equals(pars[4])) {
				query.setInteger("clientId", Integer.valueOf(pars[4]));
			}
			if (!"0".equals(pars[5])) {
				query.setInteger("projectId", Integer.valueOf(pars[5]));
			}
			if (!"0".equals(pars[6])) {
				query.setInteger("mediaId", Integer.valueOf(pars[6]));
			}
			if (!"0".equals(pars[7])) {
				query.setInteger("channelId", Integer.valueOf(pars[7]));
			}
		}
	}

	@SuppressWarnings("unchecked")
	public Expense getExpenseByDateAndId(Integer id, Date date) {
		Object[] obj = { id, date };
		List<Expense> list = (List<Expense>) this.getHibernateTemplate().find(
				" from Expense where account.id = ? and TO_DAYS(expenseDate) = TO_DAYS(?) and deleted=0 ", obj);
		if (list != null && list.size() > 0) {
			return (Expense) list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Expense> getExpenseByProjectAndId(Integer channelId, Integer projectId, Date date) {
		Object[] obj = { channelId, projectId, date };
		List<Expense> list = (List<Expense>) this.getHibernateTemplate().find(
				" from Expense where account.channel.id = ? and account.project.id = ? and TO_DAYS(expenseDate) = TO_DAYS(?) ", obj);
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<ServiceAdjustBean> getDaysByCondition(Integer channelId, Integer projectId, String startDate, String endDate) {
		StringBuffer sb = new StringBuffer();
		sb.append("select new cn.finance.model.bean.ServiceAdjustBean(sum(e.serviceFee),expenseDate) from Expense e ");
		sb.append(" where e.account.channel.id = :channelId and e.account.project.id = :projectId and expenseDate >= :startDate and expenseDate <= :endDate ");
		sb.append(" group by expenseDate ");
		Query query = getSession().createQuery(sb.toString());
		query.setInteger("channelId", channelId);
		query.setInteger("projectId", projectId);
		query.setString("startDate", startDate);
		query.setString("endDate", endDate);
		query.setFirstResult(0);
		List<ServiceAdjustBean> list = query.list();
		return list;
	}

	public void deleteCalcByAccountId(Integer accountId) {
		String sql = "update expense set deleted = 1 where account_id = :accountId ";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setInteger("accountId", accountId);
		query.executeUpdate();
	}

	public void reCalcStatus(Integer accountId, Date startDate, Date endDate) {
		String sql = "update expense set deleted = 0 where account_id = :accountId and expense_date >= :startDate and expense_date <= :endDate ";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setInteger("accountId", accountId);
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
		query.executeUpdate();
	}

	public void reCalcCost(Integer accountId, Integer supplymentType, Date startDate, Date endDate) {
		String sql = "";
		if (supplymentType == 1) {
			sql = "update expense set cost = manage_media where account_id = :accountId and expense_date >= :startDate and expense_date <= :endDate ";
		} else if (supplymentType == 2) {
			sql = "update expense set cost = 0 where account_id = :accountId and expense_date >= :startDate and expense_date <= :endDate ";
		}
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setInteger("accountId", accountId);
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
		query.executeUpdate();
	}

	public void reCalcDiscount(Integer accountId, BigDecimal discount, Date startDate, Date endDate) {
		String sql = "update expense set darwin_cost = manage_media*(1-:discount*0.01), discount = manage_media*:discount where account_id = :accountId and expense_date >= :startDate and expense_date <= :endDate and cost != 0";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setBigDecimal("discount", discount);
		query.setInteger("accountId", accountId);
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
		query.executeUpdate();
	}

	public void reCalcDiscountBack(Integer accountId, BigDecimal discountBack, Date startDate, Date endDate) {
		String sql = "update expense set discount_back = manage_media*:discountBack*0.01 where account_id = :accountId and expense_date >= :startDate and expense_date <= :endDate and cost != 0";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setBigDecimal("discountBack", discountBack);
		query.setInteger("accountId", accountId);
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
		query.executeUpdate();
	}

	public void reCalcServiceFee(Integer acountId, BigDecimal serviceFee, Date startDate, Date endDate) {
		String sql = "update expense set service_fee = :serviceFee where account_id = :accountId and expense_date >= :startDate and expense_date <= :endDate ";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setBigDecimal("serviceFee", serviceFee);
		query.setInteger("accountId", acountId);
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
		query.executeUpdate();
	}

	public void reCalcServiceFeeRate(Integer acountId, BigDecimal serviceFeeRate, Date startDate, Date endDate) {
		String sql = "update expense set service_fee = manage_media*:serviceFeeRate where account_id = :accountId and expense_date >= :startDate and expense_date <= :endDate ";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setBigDecimal("serviceFeeRate", serviceFeeRate);
		query.setInteger("accountId", acountId);
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
		query.executeUpdate();
	}

	public void reCalcRevenue(Integer acountId, Date startDate, Date endDate) {
		String sql = "update expense set revenue = manage_media+service_fee-discount_back where account_id = :accountId and expense_date >= :startDate and expense_date <= :endDate ";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setInteger("accountId", acountId);
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
		query.executeUpdate();
	}

	public void reCalcGrossProfit(Integer acountId, Date startDate, Date endDate) {
		String sql = "update expense set gross_profit = revenue-cost where account_id = :accountId and expense_date >= :startDate and expense_date <= :endDate ";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setInteger("accountId", acountId);
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
		query.executeUpdate();
	}
}
