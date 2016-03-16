package cn.finance.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.finance.model.Bd;
import cn.springside.modules.orm.Page;

public class BdDao extends HibernateDaoSupport {

	@SuppressWarnings("unchecked")
	public List<Bd> getAll(Page<Bd> page) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from Bd where deleted = 0 ");
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
	public List<Bd> getAll() {
		Query query = getSession().createQuery(" from Bd where deleted = 0 ");
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public Bd getBdByName(String name, Integer id) {
		Object[] obj = { name, id };
		List<Bd> list = (List<Bd>) this.getHibernateTemplate().find(" from Bd where deleted = 0 and name = ? and id != ? ", obj);
		if (list != null && list.size() > 0) {
			return (Bd) list.get(0);
		}
		return null;
	}

	public Bd getBdById(Integer id) {
		return (Bd) this.getHibernateTemplate().get(Bd.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Bd> getBdByIds(String ids) {
		// FIXME 注入
		StringBuffer sb = new StringBuffer();
		sb.append(" from Bd where id in (").append(ids).append(")");
		return (List<Bd>) this.getHibernateTemplate().find(sb.toString());
	}

	public int getTotalCount(Page<Bd> page) {
		Query query = getSession().createQuery(" select count(*) from Bd where deleted = 0 ");
		return ((Number) query.uniqueResult()).intValue();
	}

	public void save(Bd bd) {
		this.getHibernateTemplate().saveOrUpdate(bd);
	}

	public Bd createBd() {
		Bd bd = new Bd();
		bd.setCreateAt(new Date());
		return bd;
	}

}
