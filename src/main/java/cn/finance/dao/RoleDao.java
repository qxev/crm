package cn.finance.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.finance.model.Resource;
import cn.finance.model.Role;

public class RoleDao extends HibernateDaoSupport {

	public void save(Role role) {
		this.getHibernateTemplate().saveOrUpdate(role);
	}

	public Role findRoleById(Integer id) {
		return (Role) this.getHibernateTemplate().get(Role.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Resource> getRecourse() {
		return (List<Resource>) this.getHibernateTemplate().find(" from Resource order by menu ");
	}

	@SuppressWarnings("unchecked")
	public List<Resource> getResourceByIds(String ids) {
		return (List<Resource>) this.getHibernateTemplate().find("from Resource where id in(" + ids + ")");
	}
}
