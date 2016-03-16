package cn.finance.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.finance.model.ChargePerson;
import cn.finance.model.Client;
import cn.finance.model.SearchEngine;
import cn.springside.modules.orm.Page;

public class ClientDao extends HibernateDaoSupport {

	@SuppressWarnings("unchecked")
	public List<Client> getAll(Page<Client> page, boolean hasTotal) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from Client where deleted = 0 ");
		if (!"0".equals(page.getFilter())) {
			sb.append(" and name like '%").append(page.getFilter()).append("%' ");
		}
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
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Client> getAllNoLimit(Page<Client> page) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from Client where deleted = 0 ");
		if (page.getOrderBy() != null && !"".equals(page.getOrderBy())) {
			sb.append("order by ");
			sb.append(page.getOrderBy());
			sb.append(" ");
			sb.append(page.getOrder());
		}
		Query query = getSession().createQuery(sb.toString());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Client> getAll() {
		Query query = getSession().createQuery(" from Client where deleted = 0 and status = 1");
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<SearchEngine> getAllSearchEngine() {
		Query query = getSession().createQuery(" from SearchEngine where deleted = 0 ");
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<SearchEngine> getSearchEngineByIds(String ids) {
		return (List<SearchEngine>) this.getHibernateTemplate().find("from SearchEngine where deleted=0 and id in(" + ids + ")");
	}

	@SuppressWarnings("unchecked")
	public List<Client> getAllByClientName(String name) {
		name = "%" + name + "%";
		return (List<Client>) this.getHibernateTemplate().find(" from Client where name like ? ", name);
	}

	public int getTotalCount(Page<Client> page) {
		StringBuffer sb = new StringBuffer(" select count(*) from Client where deleted=0 ");
		if (!"0".equals(page.getFilter())) {
			sb.append(" and name like '%").append(page.getFilter()).append("%' ");
		}
		Query query = getSession().createQuery(sb.toString());
		return ((Number) query.uniqueResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	public Client getClientBySv3Id(Integer sv3Id) {
		List<Client> list = (List<Client>) this.getHibernateTemplate().find(" from Client where sv3Id = ? ", sv3Id);
		if (list != null && list.size() > 0) {
			return (Client) list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Client getClientById(Integer id) {
		List<Client> list = (List<Client>) this.getHibernateTemplate().find(" from Client c where c.id = ? ", id);
		if (list != null && list.size() > 0) {
			return (Client) list.get(0);
		}
		return null;
	}

	public ChargePerson getChargePersonById(Integer id) {
		return (ChargePerson) this.getHibernateTemplate().get(ChargePerson.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<ChargePerson> getChargePersonByClientId(Integer id) {
		List<ChargePerson> list = (List<ChargePerson>) this.getHibernateTemplate().find(
				" from ChargePerson ch where ch.client.id = ? and ch.userType = 1 and ch.deleted = 0 ", id);
		return list;
	}

	public void save(Client client) {
		this.getHibernateTemplate().saveOrUpdate(client);
	}

	public void saveChargePerson(ChargePerson chargePerson) {
		this.getHibernateTemplate().saveOrUpdate(chargePerson);
	}

	public Client createClient() {
		Client client = new Client();
		client.setStatus(1); // 默认直接客户
		client.setType(1); // 默认有效
		client.setDeleted("0"); // 默认未删除
		client.setCreateAt(new Date());
		return client;
	}

	public Client findById(Integer id) {
		return (Client) this.getHibernateTemplate().get(Client.class, id);
	}

}
