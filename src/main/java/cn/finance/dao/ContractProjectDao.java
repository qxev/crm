package cn.finance.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.finance.model.Account;
import cn.finance.model.ContractProject;
import cn.finance.model.DarwinName;
import cn.finance.util.Constant;
import cn.springside.modules.orm.Page;

public class ContractProjectDao extends HibernateDaoSupport {

	public DarwinName getDarwinNameById(Integer id) {
		return (DarwinName) this.getHibernateTemplate().get(DarwinName.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<DarwinName> getAllDarwinName() {
		Query query = getSession().createQuery(" from DarwinName ");
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<ContractProject> getAll(Page<ContractProject> page, boolean hasTotal) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from ContractProject p where 1=1 ");
		// 设置筛选条件
		Map<String, Integer> par = setParameters(page.getFilter(), sb);
		// 设置排序
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
		for (Map.Entry<String, Integer> entry : par.entrySet()) {
			query.setInteger(entry.getKey(), entry.getValue());
		}
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<ContractProject> getAll() {
		Query query = getSession().createQuery(" from ContractProject where deleted = 0 ");
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<ContractProject> getAllByClientId(Integer clientId) {
		return (List<ContractProject>) this.getHibernateTemplate().find(
				" from ContractProject p left join fetch p.client c where c.id = ? ", clientId);
	}

	private Map<String, Integer> setParameters(String filter, StringBuffer sb) {
		Map<String, Integer> par = new HashMap<String, Integer>();
		if (filter != null && !"".equals(filter)) {
			String[] pars = StringUtils.split(filter, "|");
			if (pars.length == 9) {
				Integer clientId = NumberUtils.toInt(pars[0]);
				if (clientId != 0) {
					sb.append(" and p.contractClient.id = :clientId ");
					par.put("clientId", clientId);
				}
				Integer pmId = NumberUtils.toInt(pars[1]);
				if (pmId != 0) {
					sb.append(" and p.pm.id = :pmId ");
					par.put("pmId", pmId);
				}
				Integer bdId = NumberUtils.toInt(pars[2]);
				if (bdId != 0) {
					sb.append(" and p.bd.id = :bdId ");
					par.put("bdId", bdId);
				}
				Integer sourceId = NumberUtils.toInt(pars[3]);
				if (sourceId != 0) {
					sb.append(" and contractClient.type = :sourceId ");
					par.put("sourceId", sourceId);
				}
				Integer industryId = NumberUtils.toInt(pars[4]);
				if (industryId != 0) {
					sb.append(" and p.industry.id = :industryId ");
					par.put("industryId", industryId);
				}
				Integer statusId = NumberUtils.toInt(pars[5]);
				if (statusId != 0) {
					sb.append(" and p.status = :statusId ");
					par.put("statusId", statusId);
				}
				String clientName = pars[6];
				if (!"0".equals(clientName)) {
					sb.append(" and p.contractClient.name like '%").append(clientName).append("%' ");
				}
				String contractProjectName = pars[7];
				if (!"0".equals(contractProjectName)) {
					sb.append(" and p.name like '%").append(contractProjectName).append("%' ");
				}
				Integer businessTypeId = NumberUtils.toInt(pars[8]);
				if (businessTypeId != 0) {
					sb.append(" and businessType.id = :businessTypeId ");
					par.put("businessTypeId", businessTypeId);
				}
			}
		}
		return par;
	}

	public int getTotalCount(Page<ContractProject> page) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(*) from ContractProject p where p.deleted=0");
		Map<String, Integer> par = setParameters(page.getFilter(), sb);
		Query query = getSession().createQuery(sb.toString());
		query.setFirstResult(0);
		for (Map.Entry<String, Integer> entry : par.entrySet()) {
			query.setInteger(entry.getKey(), entry.getValue());
		}
		return ((Number) query.uniqueResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	public ContractProject getContractProjectBySv3Id(Integer sv3Id) {
		List<ContractProject> list = (List<ContractProject>) this.getHibernateTemplate().find(" from ContractProject where sv3Id = ? ",
				sv3Id);
		if (list != null && list.size() > 0) {
			return (ContractProject) list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public ContractProject getContractProjectById(Integer id) {
		List<ContractProject> list = (List<ContractProject>) this
				.getHibernateTemplate()
				.find(" from ContractProject p left join fetch p.businessType left join fetch p.pm left join fetch p.bd where p.deleted = 0 and p.id = ? ",
						id);
		if (list != null && list.size() > 0) {
			return (ContractProject) list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Integer getAllStatusByClientId(Integer clientId) {
		List<Account> list = (List<Account>) this.getHibernateTemplate().find(
				" from ContractProject p where p.client.id = ? and p.status = 1 ", clientId);
		if (list != null && list.size() > 0) {
			return Constant.STATUS_ACTIVE;
		} else {
			return Constant.STATUS_INVALID;
		}
	}

	@SuppressWarnings("unchecked")
	public List<ContractProject> getAllByContractProjectName(String name) {
		name = "%" + name + "%";
		return (List<ContractProject>) this.getHibernateTemplate().find(" from ContractProject where name like ? and deleted = 0 ", name);
	}

	public void save(ContractProject contractProject) {
		this.getHibernateTemplate().saveOrUpdate(contractProject);
	}

	public ContractProject createContractProject() {
		ContractProject contractProject = new ContractProject();
		contractProject.setCreateAt(new Date());
		contractProject.setDeleted("0");
		return contractProject;
	}
}
