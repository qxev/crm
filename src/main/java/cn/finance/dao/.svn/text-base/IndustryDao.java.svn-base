package cn.finance.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.finance.model.Industry;
import cn.springside.modules.orm.Page;

public class IndustryDao extends HibernateDaoSupport {

	@SuppressWarnings("unchecked")
	public List<Industry> getAll(Page<Industry> page) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from Industry where deleted = 0 ");
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
	public List<Industry> getAll(){
		Query query = getSession().createQuery(" from Industry where deleted = 0 ");
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public Industry getIndustryByName(String name, Integer id) {
		Object[] obj = { name, id };
		List<Industry> list = (List<Industry>)this.getHibernateTemplate().find(" from Industry where deleted = 0 and name = ? and id != ?", obj);
		if (list != null && list.size() > 0) {
			return (Industry) list.get(0);
		}
		return null;
	}
	
	public Industry getIndustryById(Integer id) {
		return (Industry)this.getHibernateTemplate().get(Industry.class, id);
	}

	public int getTotalCount(Page<Industry> page) {
		Query query = getSession()
				.createQuery(" select count(*) from Industry where deleted = 0 ");
		return ((Number) query.uniqueResult()).intValue();
	}
	
	public void save(Industry bd) {
		this.getHibernateTemplate().saveOrUpdate(bd);
	}
	
	public Industry createIndustry() {
		Industry industry = new Industry();
		industry.setCreateAt(new Date());
		return industry;
	}

}
