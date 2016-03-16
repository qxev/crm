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
import cn.finance.model.Project;
import cn.finance.util.Constant;
import cn.springside.modules.orm.Page;

public class ProjectDao extends HibernateDaoSupport {

	@SuppressWarnings("unchecked")
	public List<Project> getAll(Page<Project> page, boolean hasTotal) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from Project p where 1=1 ");
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
		// query.setCacheable(true);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Project> getAll() {
		Query query = getSession().createQuery(" from Project where deleted = 0 ");
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Project> getAllByClientId(Integer clientId) {
		return (List<Project>) this.getHibernateTemplate().find(" from Project p left join fetch p.client c where c.id = ? ", clientId);
	}

	private Map<String, Integer> setParameters(String filter, StringBuffer sb) {
		Map<String, Integer> par = new HashMap<String, Integer>();
		if (filter != null && !"".equals(filter)) {
			String[] pars = StringUtils.split(filter, "|");
			if (pars.length == 8) {
				Integer clientId = NumberUtils.toInt(pars[0]);
				if (clientId != 0) {
					sb.append(" and client.id = :clientId ");
					par.put("clientId", clientId);
				}
				Integer pmId = NumberUtils.toInt(pars[1]);
				if (pmId != 0) {
					sb.append(" and :pmId in (select pm.id from p.pms pm ) ");
					par.put("pmId", pmId);
				}
				Integer bdId = NumberUtils.toInt(pars[2]);
				if (bdId != 0) {
					sb.append(" and :bdId in (select bd.id from p.bds bd ) ");
					par.put("bdId", bdId);
				}
				Integer sourceId = NumberUtils.toInt(pars[3]);
				if (sourceId != 0) {
					sb.append(" and client.type = :sourceId ");
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
					sb.append(" and p.client.name like '%").append(clientName).append("%' ");
				}
				String projectName = pars[7];
				if (!"0".equals(projectName)) {
					sb.append(" and p.name like '%").append(projectName).append("%' ");
				}
			}
		}
		return par;
	}

	public int getTotalCount(Page<Project> page) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(*) from Project p where p.deleted=0");
		Map<String, Integer> par = setParameters(page.getFilter(), sb);
		Query query = getSession().createQuery(sb.toString());
		query.setFirstResult(0);
		for (Map.Entry<String, Integer> entry : par.entrySet()) {
			query.setInteger(entry.getKey(), entry.getValue());
		}
		return ((Number) query.uniqueResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	public Project getProjectBySv3Id(Integer sv3Id) {
		List<Project> list = (List<Project>) this.getHibernateTemplate().find(" from Project where sv3Id = ? ", sv3Id);
		if (list != null && list.size() > 0) {
			return (Project) list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Project getProjectById(Integer id) {
		List<Project> list = (List<Project>) this.getHibernateTemplate().find(" from Project p where p.deleted = 0 and p.id = ? ", id);
		if (list != null && list.size() > 0) {
			return (Project) list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Integer getAllStatusByClientId(Integer clientId) {
		List<Account> list = (List<Account>) this.getHibernateTemplate().find(" from Project p where p.client.id = ? and p.status = 1 ",
				clientId);
		if (list != null && list.size() > 0) {
			return Constant.STATUS_ACTIVE;
		} else {
			return Constant.STATUS_INVALID;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Project> getAllByProjectName(String name) {
		name = "%" + name + "%";
		return (List<Project>) this.getHibernateTemplate().find(" from Project where name like ? ", name);
	}

	public void save(Project project) {
		this.getHibernateTemplate().saveOrUpdate(project);
	}

	public Project createProject() {
		Project project = new Project();
		project.setCreateAt(new Date());
		project.setDeleted("0");
		project.setStatus(1);
		return project;
	}
}
