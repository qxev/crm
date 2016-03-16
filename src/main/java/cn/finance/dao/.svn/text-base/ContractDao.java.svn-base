package cn.finance.dao;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.finance.model.Contract;
import cn.finance.model.ContractTemplate;
import cn.finance.model.Version;
import cn.springside.modules.orm.Page;

public class ContractDao extends HibernateDaoSupport {

	@SuppressWarnings("unchecked")
	public Contract findDetailById(Integer id) {
		List<Contract> list = (List<Contract>) super.getHibernateTemplate().find(
				"from Contract c left join fetch c.contractProject p left join fetch p.bd where c.id = ?", id);
		if (list != null && list.size() > 0) {
			return (Contract) list.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Contract> getAll() {
		Query query = getSession().createQuery(" from Contract where deleted = 0 ");
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Version> getVersionByContractId(Integer id) {
		Object[] obj = { id };
		List<Version> list = (List<Version>) this.getHibernateTemplate().find(" from Version where contract.id = ?", obj);
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Contract> getAll(Page<Contract> page) {
		StringBuffer sb = new StringBuffer(" from Contract c where c.deleted = 0 ");
		String[] pars = StringUtils.split(page.getFilter(), "|");
		if (pars.length == 12) {
			String clientName = pars[0];
			if (!"0".equals(clientName)) {
				sb.append(" and c.contractProject.contractClient.name like '%").append(clientName).append("%' ");
			}
			String projectName = pars[1];
			if (!"0".equals(projectName)) {
				sb.append(" and c.contractProject.name like '%").append(projectName).append("%' ");
			}
			Integer status = NumberUtils.toInt(pars[2]);
			// 恶心的同时审核
			if (status == 0) {
				sb.append(" and c.status < 12 ");
			} else if (status == 8) {
				sb.append(" and ((c.status < 8 and c.status >3 ) or c.status = 13)");
			} else if (status > 0) {
				sb.append(" and c.status = :status ");
			}
			if (status > 3 && status < 8) {
				sb.append(" and c.departApprove != 1 ");
			} else if (status == 8) {
				sb.append(" and c.financeApprove != 1 ");
			}
			Integer bdId = NumberUtils.toInt(pars[3]);
			if (bdId != 0) {
				sb.append(" and c.contractProject.bd.id = :bdId ");
			}
			Integer clientTypeId = NumberUtils.toInt(pars[4]);
			if (clientTypeId > 3) {
				sb.append(" and c.contractProject.businessType.id = :clientTypeId ");
			}
			Integer createId = NumberUtils.toInt(pars[5]);
			if (createId != 0) {
				sb.append(" and c.createUser.id = :createId ");
			}
			Integer industryId = NumberUtils.toInt(pars[6]);
			if (industryId != 0) {
				sb.append(" and c.contractProject.industry.id = :industryId ");
			}
			Integer businessTypeId = NumberUtils.toInt(pars[7]);
			if (businessTypeId != 0) {
				sb.append(" and c.contractProject.businessType.id = :businessTypeId ");
			}
			Integer areaId = NumberUtils.toInt(pars[8]);
			if (areaId != -1) {
				sb.append(" and c.areaId = :areaId ");
			}
			Integer leadId = NumberUtils.toInt(pars[9]);
			if (leadId != 0) {
				if (clientTypeId == 3) {
					sb.append(" and c.contractProject.bd.leaderUserId = :leadId ");
				} else {
					sb.append(" and c.contractProject.pm.id = :leadId ");
				}
			}
			Integer contractClientId = NumberUtils.toInt(pars[10]);
			if (contractClientId != 0) {
				sb.append(" and c.contractProject.contractClient.id = :contractClientId ");
			}
			Integer contractProjectId = NumberUtils.toInt(pars[11]);
			if (contractProjectId != 0) {
				sb.append(" and c.contractProject.id = :contractProjectId ");
			}
		}
		if (page.getOrderBy() != null && !"".equals(page.getOrderBy())) {
			sb.append("order by ");
			sb.append(page.getOrderBy());
			sb.append(" ");
			sb.append(page.getOrder());
		}
		Query query = getSession().createQuery(sb.toString());
		query.setMaxResults(page.getPageSize());
		query.setFirstResult((page.getPageNo() - 1) * page.getPageSize());
		if (pars.length == 12) {
			Integer status = NumberUtils.toInt(pars[2]);
			Integer bdId = NumberUtils.toInt(pars[3]);
			Integer clientTypeId = NumberUtils.toInt(pars[4]);
			Integer createId = NumberUtils.toInt(pars[5]);
			Integer industryId = NumberUtils.toInt(pars[6]);
			Integer businessTypeId = NumberUtils.toInt(pars[7]);
			Integer areaId = NumberUtils.toInt(pars[8]);
			Integer leadId = NumberUtils.toInt(pars[9]);
			Integer contractClientId = NumberUtils.toInt(pars[10]);
			Integer contractProjectId = NumberUtils.toInt(pars[11]);
			if (bdId != 0)
				query.setInteger("bdId", bdId);
			if (status > 0 && status != 8)
				query.setInteger("status", status);
			if (clientTypeId > 3)
				query.setInteger("clientTypeId", clientTypeId);
			if (createId != 0)
				query.setInteger("createId", createId);
			if (industryId != 0)
				query.setInteger("industryId", industryId);
			if (businessTypeId != 0)
				query.setInteger("businessTypeId", businessTypeId);
			if (areaId != -1)
				query.setInteger("areaId", areaId);
			if (leadId != 0)
				query.setInteger("leadId", leadId);
			if (contractClientId != 0)
				query.setInteger("contractClientId", contractClientId);
			if (contractProjectId != 0)
				query.setInteger("contractProjectId", contractProjectId);
		}
		return query.list();
	}

	public int getTotalCount(Page<Contract> page) {
		StringBuffer sb = new StringBuffer(" select count(*) from Contract c where c.deleted = 0 ");
		String[] pars = StringUtils.split(page.getFilter(), "|");
		if (pars.length == 12) {
			String clientName = pars[0];
			if (!"0".equals(clientName)) {
				sb.append(" and c.contractProject.contractClient.name like '%").append(clientName).append("%' ");
			}
			String projectName = pars[1];
			if (!"0".equals(projectName)) {
				sb.append(" and c.contractProject.name like '%").append(projectName).append("%' ");
			}
			Integer status = NumberUtils.toInt(pars[2]);
			// 恶心的同时审核
			if (status == 0) {
				sb.append(" and c.status < 12 ");
			} else if (status == 8) {
				sb.append(" and c.status < 8 and c.status >3 ");
			} else if (status > 0) {
				sb.append(" and c.status = :status ");
			}
			if (status > 3 && status < 8) {
				sb.append(" and c.departApprove != 1 ");
			} else if (status == 8) {
				sb.append(" and c.financeApprove != 1 ");
			}
			Integer bdId = NumberUtils.toInt(pars[3]);
			if (bdId != 0) {
				sb.append(" and c.contractProject.bd.id = :bdId ");
			}
			Integer clientTypeId = NumberUtils.toInt(pars[4]);
			if (clientTypeId != 0) {
				sb.append(" and c.contractProject.businessType.id = :clientTypeId ");
			}
			Integer createId = NumberUtils.toInt(pars[5]);
			if (createId != 0) {
				sb.append(" and c.createUser.id = :createId ");
			}
			Integer industryId = NumberUtils.toInt(pars[6]);
			if (industryId != 0) {
				sb.append(" and c.contractProject.industry.id = :industryId ");
			}
			Integer businessTypeId = NumberUtils.toInt(pars[7]);
			if (businessTypeId != 0) {
				sb.append(" and c.contractProject.businessType.id = :businessTypeId ");
			}
			Integer areaId = NumberUtils.toInt(pars[8]);
			if (areaId != -1) {
				sb.append(" and c.areaId = :areaId ");
			}
			Integer leadId = NumberUtils.toInt(pars[9]);
			if (leadId != 0) {
				sb.append(" and c.contractProject.pm.id = :leadId ");
			}
			Integer contractClientId = NumberUtils.toInt(pars[10]);
			if (contractClientId != 0) {
				sb.append(" and c.contractProject.contractClient.id = :contractClientId ");
			}
			Integer contractProjectId = NumberUtils.toInt(pars[11]);
			if (contractProjectId != 0) {
				sb.append(" and c.contractProject.id = :contractProjectId ");
			}
		}
		Query query = getSession().createQuery(sb.toString());
		query.setFirstResult(0);
		if (pars.length == 12) {
			Integer status = NumberUtils.toInt(pars[2]);
			Integer bdId = NumberUtils.toInt(pars[3]);
			Integer clientTypeId = NumberUtils.toInt(pars[4]);
			Integer createId = NumberUtils.toInt(pars[5]);
			Integer industryId = NumberUtils.toInt(pars[6]);
			Integer businessTypeId = NumberUtils.toInt(pars[7]);
			Integer areaId = NumberUtils.toInt(pars[8]);
			Integer leadId = NumberUtils.toInt(pars[9]);
			Integer contractClientId = NumberUtils.toInt(pars[10]);
			Integer contractProjectId = NumberUtils.toInt(pars[11]);
			if (bdId != 0)
				query.setInteger("bdId", bdId);
			if (status > 0 && status != 8)
				query.setInteger("status", status);
			if (clientTypeId != 0)
				query.setInteger("clientTypeId", clientTypeId);
			if (createId != 0)
				query.setInteger("createId", createId);
			if (industryId != 0)
				query.setInteger("industryId", industryId);
			if (businessTypeId != 0)
				query.setInteger("businessTypeId", businessTypeId);
			if (areaId != -1)
				query.setInteger("areaId", areaId);
			if (leadId != 0)
				query.setInteger("leadId", leadId);
			if (contractClientId != 0)
				query.setInteger("contractClientId", contractClientId);
			if (contractProjectId != 0)
				query.setInteger("contractProjectId", contractProjectId);
		}
		return ((Number) query.uniqueResult()).intValue();
	}

	public Contract getContractById(Integer id) {
		return (Contract) this.getHibernateTemplate().get(Contract.class, id);
	}

	public void save(Contract contract) {
		this.getHibernateTemplate().saveOrUpdate(contract);
	}

	public void saveVersion(Version version) {
		this.getHibernateTemplate().saveOrUpdate(version);
	}

	public Contract create() {
		Contract contract = new Contract();
		contract.setDeleted("0");
		contract.setCreateAt(new Date());
		return contract;
	}

	public void saveTemplate(ContractTemplate contractTemplate) {
		this.getHibernateTemplate().saveOrUpdate(contractTemplate);
	}

	public int getTemplateCount(Page<ContractTemplate> page) {
		Query query = getSession().createQuery(
				" select count(*) from ContractTemplate where deleted = 0 and businessType.id = :businessTypeId");
		query.setInteger("businessTypeId", Integer.valueOf(page.getFilter()));
		return ((Number) query.uniqueResult()).intValue();
	}

	public ContractTemplate getContractTemplateById(Integer id) {
		return (ContractTemplate) this.getHibernateTemplate().get(ContractTemplate.class, id);
	}

	public Version getVersionById(Integer id) {
		return (Version) this.getHibernateTemplate().get(Version.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<ContractTemplate> getAllTemplate(Page<ContractTemplate> page) {
		return (List<ContractTemplate>) this.getHibernateTemplate().find(
				" from ContractTemplate where deleted = 0 and businessType.id = ? order by id", Integer.valueOf(page.getFilter()));
	}

	@SuppressWarnings("unchecked")
	public List<Contract> getAllTemplate(Date date1, Date date2) {
		Object[] obj = { date1, date2 };
		List<Contract> list = (List<Contract>) this
				.getHibernateTemplate()
				.find(" from Contract c left join fetch c.contractProject left join fetch c.createUser where c.deleted = 0 and c.remindTime > ? and c.remindTime < ? ",
						obj);
		return list;
	}
}
