package cn.finance.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.finance.model.Pm;
import cn.springside.modules.orm.Page;

public class PmDao extends HibernateDaoSupport {

	@SuppressWarnings("unchecked")
	public List<Pm> getAll(Page<Pm> page) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from Pm where deleted = 0 ");
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
	public List<Pm> getAll() {
		Query query = getSession().createQuery(" from Pm where deleted = 0 ");
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Pm> getByBusinessType(Integer type) {
		List<Pm> list = (List<Pm>) this.getHibernateTemplate().find(" from Pm where deleted = 0 and businessType.id = ?", type);
		return list;
	}

	@SuppressWarnings("unchecked")
	public Pm getPmByName(String name, Integer id) {
		Object[] obj = { name, id };
		List<Pm> list = (List<Pm>) this.getHibernateTemplate().find(" from Pm where deleted = 0 and name = ? and id != ? ", obj);
		if (list != null && list.size() > 0) {
			return (Pm) list.get(0);
		}
		return null;
	}

	public Pm getPmById(Integer id) {
		return (Pm) this.getHibernateTemplate().get(Pm.class, id);
	}

	@SuppressWarnings("unchecked")
	public Pm getDetailById(Integer id) {
		List<Pm> list = (List<Pm>) this.getHibernateTemplate().find(
				" from Pm p left join fetch p.businessType where p.deleted = 0 and p.id = ? ", id);
		if (list != null && list.size() > 0) {
			return (Pm) list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Pm> getPmByIds(String ids) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from Pm where id in (").append(ids).append(")");
		return (List<Pm>) this.getHibernateTemplate().find(sb.toString());
	}

	public int getTotalCount(Page<Pm> page) {
		Query query = getSession().createQuery(" select count(*) from Pm where deleted = 0 ");
		return ((Number) query.uniqueResult()).intValue();
	}

	public void save(Pm pm) {
		this.getHibernateTemplate().saveOrUpdate(pm);
	}

	public Pm createPm() {
		Pm pm = new Pm();
		pm.setCreateAt(new Date());
		return pm;
	}

}
