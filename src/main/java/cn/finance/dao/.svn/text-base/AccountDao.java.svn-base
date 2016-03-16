package cn.finance.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.finance.model.Account;
import cn.finance.model.Exchange;
import cn.finance.model.Pm;
import cn.finance.util.Constant;
import cn.springside.modules.orm.Page;

public class AccountDao extends HibernateDaoSupport {

	@Autowired
	private PmDao pmDao;

	@Autowired
	private ExchangeDao exchangeDao;

	public List<Account> getAllByMediaName(String mediaName) {
		StringBuffer sb = new StringBuffer(" from Account a left join fetch a.channel c left join fetch c.media m where m.name = ? ");

		List<Account> list = (List<Account>) this.getHibernateTemplate().find(sb.toString(), mediaName);
		return list;
	}

	public List<Account> getAll(Page<Account> page, boolean hasTotal) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from Account a where a.deleted = 0 ");
		// 设置筛选条件
		Map<String, Integer> par = setParameters(page.getFilter(), sb);
		if (page.getOrderBy() != null && !"".equals(page.getOrderBy())) {
			sb.append("order by ");
			sb.append(page.getOrderBy());
			sb.append(" ");
			sb.append(page.getOrder());
		}
		Query query = getSession().createQuery(sb.toString());
		if (!hasTotal) {
			query.setMaxResults(page.getPageSize());
			query.setFirstResult((page.getPageNo() - 1) * page.getPageSize());
		}
		for (Map.Entry<String, Integer> entry : par.entrySet()) {
			query.setInteger(entry.getKey(), entry.getValue());
		}
		return query.list();
	}

	public BigDecimal getTotalBalance(Page<Account> page) {
		StringBuffer sb = new StringBuffer(" select sum(a.totalBalance) from Account a where deleted=0 ");
		// 设置筛选条件
		Map<String, Integer> par = setParameters(page.getFilter(), sb);
		Query query = getSession().createQuery(sb.toString());
		for (Map.Entry<String, Integer> entry : par.entrySet()) {
			query.setInteger(entry.getKey(), entry.getValue());
		}
		return (BigDecimal) query.uniqueResult();
	}

	public BigDecimal getTotalDailyBudget(Page<Account> page) {
		StringBuffer sb = new StringBuffer(" select sum(a.dailyBudget) from Account a where deleted=0 ");
		// 设置筛选条件
		Map<String, Integer> par = setParameters(page.getFilter(), sb);
		Query query = getSession().createQuery(sb.toString());
		for (Map.Entry<String, Integer> entry : par.entrySet()) {
			query.setInteger(entry.getKey(), entry.getValue());
		}
		return (BigDecimal) query.uniqueResult();
	}

	public List<Account> getAlertList(Page<Account> page, boolean hasTotal, Integer remainDays) {
		StringBuffer sb = new StringBuffer(" from Account where alert = 1 and status = 1 and deleted = 0 and remainDays <= :remainDays ");
		// 设置筛选条件
		if (page.getOrderBy() != null && !"".equals(page.getOrderBy())) {
			sb.append("order by ");
			sb.append(page.getOrderBy());
			sb.append(" ");
			sb.append(page.getOrder());
		}
		Query query = getSession().createQuery(sb.toString());
		query.setInteger("remainDays", remainDays);
		if (!hasTotal) {
			query.setMaxResults(page.getPageSize());
			query.setFirstResult((page.getPageNo() - 1) * page.getPageSize());
		}
		return query.list();
	}

	public List<Account> getAccountByExchangeId(Integer exchangeId) {
		List<Account> list = (List<Account>) this.getHibernateTemplate().find(" from Account where deleted = 0 and exchange.id = ? ",
				exchangeId);
		return list;
	}

	private Map<String, Integer> setParameters(String filter, StringBuffer sb) {
		Map<String, Integer> par = new HashMap<String, Integer>();
		if (filter != null && !"".equals(filter)) {
			String[] pars = StringUtils.split(filter, "|");
			if (pars.length == 8) {
				Integer clientId = NumberUtils.toInt(pars[0]);
				if (clientId != 0) {
					sb.append(" and project.client.id = :clientId ");
					par.put("clientId", clientId);
				}
				Integer projectId = NumberUtils.toInt(pars[1]);
				if (projectId != 0) {
					sb.append(" and project.id = :projectId ");
					par.put("projectId", projectId);
				}
				Integer mediaId = NumberUtils.toInt(pars[2]);
				if (mediaId != 0) {
					sb.append(" and channel.media.id = :mediaId ");
					par.put("mediaId", mediaId);
				}
				Integer channelId = NumberUtils.toInt(pars[3]);
				if (channelId != 0) {
					sb.append(" and channel.id = :channelId ");
					par.put("channelId", channelId);
				}
				Integer pmId = NumberUtils.toInt(pars[4]);
				if (pmId != 0) {
					Pm pm = pmDao.getPmById(pmId);
					sb.append(" and currentPms like '%").append(pm.getName()).append("%' ");
				}
				Integer statusId = NumberUtils.toInt(pars[5]);
				if (statusId != 0) {
					sb.append(" and a.status = :statusId ");
					par.put("statusId", statusId);
				}
				String clientName = pars[6];
				if (!"0".equals(clientName)) {
					sb.append(" and project.client.name like '%").append(clientName).append("%' ");
				}
				String projectName = pars[7];
				if (!"0".equals(projectName)) {
					sb.append(" and project.name like '%").append(projectName).append("%' ");
				}
			}
		}
		return par;
	}

	public List<Account> getAll() {
		Query query = getSession().createQuery(" from Account a left join fetch a.channel c left join fetch c.media where a.deleted = 0 ");
		List list = query.list();
		return list;
	}

	public List<Account> getAllEffective() {
		StringBuffer sb = new StringBuffer();
		sb.append(" from Account where deleted = 0 and status = 1 ");
		List<Account> list = (List<Account>) this.getHibernateTemplate().find(sb.toString());
		return list;
	}

	public Account getAccountBySv3Id(Integer sv3Id) {
		List<Account> list = (List<Account>) this.getHibernateTemplate().find(" from Account where sv3_id = ? ", sv3Id);
		if (list != null && list.size() > 0) {
			return (Account) list.get(0);
		}
		return null;
	}

	public Account getAccountByNameMediaId(String name, Integer mediaId) {
		Object[] obj = { name, mediaId };
		List<Account> list = (List<Account>) this.getHibernateTemplate()
				.find(" from Account where name = ? and channel.media.id = ? ", obj);
		if (list != null && list.size() > 0) {
			return (Account) list.get(0);
		}
		return null;
	}

	public List<Account> getAllByProjectId(Integer projectId) {
		List<Account> list = (List<Account>) this.getHibernateTemplate().find(" from Account a where a.project.id = ? and status = 1 ",
				projectId);
		return list;
	}

	public List<Account> getAllByProjectChannelId(Integer projectId, Integer channelId) {
		Object[] obj = { projectId, channelId };
		List<Account> list = (List<Account>) this.getHibernateTemplate().find(
				" from Account a where a.project.id = ? and a.channel.id = ? and status = 1 ", obj);
		return list;
	}

	public Integer getAllStatusByChannelId(Integer channelId) {
		List<Account> list = (List<Account>) this.getHibernateTemplate().find(" from Account a where a.channel.id = ? and status = 1 ",
				channelId);
		if (list != null && list.size() > 0) {
			return Constant.STATUS_ACTIVE;
		} else {
			return Constant.STATUS_INVALID;
		}
	}

	public Integer getAllStatusByProjectId(Integer projectId) {
		List<Account> list = (List<Account>) this.getHibernateTemplate().find(" from Account a where a.project.id = ? and status = 1 ",
				projectId);
		if (list != null && list.size() > 0) {
			return Constant.STATUS_ACTIVE;
		} else {
			return Constant.STATUS_INVALID;
		}
	}

	public Account getAccountById(Integer id) {
		return (Account) this.getHibernateTemplate().get(Account.class, id);
	}

	public int getTotalCount(Page<Account> page) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(*) from Account a where deleted = 0 ");
		Map<String, Integer> par = setParameters(page.getFilter(), sb);
		Query query = getSession().createQuery(sb.toString());
		query.setFirstResult(0);
		for (Map.Entry<String, Integer> entry : par.entrySet()) {
			query.setInteger(entry.getKey(), entry.getValue());
		}
		return ((Number) query.uniqueResult()).intValue();
	}

	public int getAlertListCount(Page<Account> page, Integer remainDays) {
		Query query = getSession().createQuery(
				" select count(*) from Account where alert = 1 and status = 1 and deleted = 0 and remainDays <= :remainDays ");
		query.setInteger("remainDays", remainDays);
		return ((Number) query.uniqueResult()).intValue();
	}

	public void save(Account account) {
		this.getHibernateTemplate().saveOrUpdate(account);
		// this.getHibernateTemplate().flush();
	}

	public Account createAccount() {
		Account account = new Account();
		account.setDeleted("0");
		account.setType(1);
		account.setAlert(1);
		account.setStatus(1);
		account.setGetData(1);
		Exchange exchange = exchangeDao.getExchangeById(1);
		account.setExchange(exchange);
		account.setServiceType(1);
		account.setSupplementType(1);
		account.setTotalBalance(new BigDecimal(0));
		account.setCreateAt(new Date());
		return account;
	}
}
