package cn.finance.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.finance.model.Account;
import cn.finance.model.User;
import cn.springside.modules.orm.Page;

public class UserDao extends HibernateDaoSupport {

	@SuppressWarnings("unchecked")
	public List<Account> getAll(Page<Account> page) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from Account and deleted=0");
		if (page.getOrderBy() != null) {
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
	public User getUserByNameAndPassword(String userName, String password) {
		Object[] obj = { userName, password };
		List<User> list = (List<User>) this.getHibernateTemplate().find(" from User where name = ? and password = ? and deleted=0 ", obj);
		if (list != null && list.size() > 0) {
			return (User) list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public User getUserByName(String userName) {
		Object[] obj = { userName };
		List<User> list = (List<User>)this.getHibernateTemplate().find(" from User where name = ? and deleted=0 ", obj);
		if (list != null && list.size() > 0) {
			return (User) list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Integer getAgentIdByRole(Integer roleId, Integer agentId) {
		Object[] obj = { roleId, agentId };
		List<User> list = (List<User>)this.getHibernateTemplate().find(" from User where role.id = ? and agentRole.id = ? and deleted=0 ", obj);
		if (list != null && list.size() > 0) {
			User user = (User) list.get(0);
			return user.getId();
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	public List<User> getUserByType(Integer type) {
		Object[] obj = { type };
		List<User> list = (List<User>)this.getHibernateTemplate().find(" from User where role.id = ? and deleted=0 ", obj);
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<User> getUserByTypeAndAgent(Integer type, Integer areaId) {
		if (type > 7)
			areaId = 0;
		Object[] obj = { type, type, areaId };
		List<User> list = (List<User>)this.getHibernateTemplate().find(
				" from User where (role.id = ? or agentRole.id = ?) and deleted=0 and areaId = ? ", obj);
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<User> getUserByPmId(Integer pmId) {
		Object[] obj = { pmId };
		List<User> list = (List<User>)this.getHibernateTemplate().find(" from User where deleted=0 and pmId = ? ", obj);
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<User> getUserById(Integer userId) {
		Object[] obj = { userId };
		List<User> list = (List<User>)this.getHibernateTemplate().find(" from User where deleted=0 and id = ? ", obj);
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<User> getAll() {
		Query query = getSession().createQuery(" from User where deleted = 0 ");
		return query.list();
	}

	public void deleteAgentByRole(Integer roleId) {
		String sql = "update user set agent_role_id = 0 where role_id = :roleId ";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setInteger("roleId", roleId);
		query.executeUpdate();
	}

	public void setAgentById(Integer roleId, Integer userId) {
		String sql = "update user set agent_role_id = :roleId where id = :userId ";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setInteger("roleId", roleId);
		query.setInteger("userId", userId);
		query.executeUpdate();
	}

	public void save(User user) {
		this.getHibernateTemplate().saveOrUpdate(user);
	}

	public User findUser(Integer id) {
		return (User) this.getHibernateTemplate().get(User.class, id);
	}

}
