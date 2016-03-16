package cn.finance.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.finance.model.ChargePerson;
import cn.finance.model.ContractClient;
import cn.finance.model.SearchEngine;
import cn.springside.modules.orm.Page;

public class ContractClientDao extends HibernateDaoSupport {

	@SuppressWarnings("unchecked")
	public List<ContractClient> getAll(Page<ContractClient> page, boolean hasTotal) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from ContractClient where deleted = 0 ");
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
	public List<ContractClient> getAllNoLimit(Page<ContractClient> page) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from ContractClient where deleted = 0 ");
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
	public List<ContractClient> getAll() {
		Query query = getSession().createQuery(" from ContractClient where deleted = 0 ");
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
	public List<ContractClient> getAllByContractClientName(String name) {
		name = "%" + name + "%";
		return (List<ContractClient>) this.getHibernateTemplate().find(" from ContractClient where name like ? and deleted = 0 ", name);
	}

	@SuppressWarnings("unchecked")
	public List<ContractClient> getByContractClientName(String name, Integer contractClientId) {
		return (List<ContractClient>) this.getHibernateTemplate().find(" from ContractClient where name = ? and id != " + contractClientId,
				name);
	}

	public int getTotalCount(Page<ContractClient> page) {
		StringBuffer sb = new StringBuffer(" select count(*) from ContractClient where deleted=0 ");
		if (!"0".equals(page.getFilter())) {
			sb.append(" and name like '%").append(page.getFilter()).append("%' ");
		}
		Query query = getSession().createQuery(sb.toString());
		return ((Number) query.uniqueResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	public ContractClient getContractClientBySv3Id(Integer sv3Id) {
		List<ContractClient> list = (List<ContractClient>) this.getHibernateTemplate().find(" from ContractClient where sv3Id = ? ", sv3Id);
		if (list != null && list.size() > 0) {
			return (ContractClient) list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public ContractClient getContractClientById(Integer id) {
		List<ContractClient> list = (List<ContractClient>) this.getHibernateTemplate().find(
				" from ContractClient c left join fetch c.chargePersons ch where c.id = ? ", id);
		if (list != null && list.size() > 0) {
			return (ContractClient) list.get(0);
		}
		return null;
	}

	public ChargePerson getChargePersonById(Integer id) {
		return (ChargePerson) this.getHibernateTemplate().get(ChargePerson.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<ChargePerson> getChargePersonByContractClientId(Integer id) {
		List<ChargePerson> list = (List<ChargePerson>) this.getHibernateTemplate().find(
				" from ChargePerson ch where ch.contractClient.id = ? and ch.userType = 1 and ch.deleted = 0 ", id);
		return list;
	}

	public void save(ContractClient contractClient) {
		this.getHibernateTemplate().saveOrUpdate(contractClient);
	}

	public void saveChargePerson(ChargePerson chargePerson) {
		this.getHibernateTemplate().saveOrUpdate(chargePerson);
	}

	public ContractClient createContractClient() {
		ContractClient contractClient = new ContractClient();
		contractClient.setType(1); // 默认有效
		contractClient.setDeleted("0"); // 默认未删除
		contractClient.setCreateAt(new Date());
		return contractClient;
	}

}
