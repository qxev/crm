package cn.finance.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.finance.dao.UserDao;
import cn.finance.model.User;

@Transactional
// 真的不知道为什么会写成这样 唉..........
public class UserService {

	private UserDao userDao;

	@Transactional(readOnly = true)
	public User getUserByNameAndPassword(String userName, String password) {
		return userDao.getUserByNameAndPassword(userName, password);
	}

	@Transactional(readOnly = true)
	public User getUserByName(String userName) {
		return userDao.getUserByName(userName);
	}

	@Transactional(readOnly = true)
	public Integer getAgentIdByRole(Integer roleId, Integer agentId) {
		return userDao.getAgentIdByRole(roleId, agentId);
	}

	@Transactional(readOnly = true)
	public List<User> getUserByType(Integer type) {
		return userDao.getUserByType(type);
	}

	@Transactional(readOnly = true)
	public List<User> getUserByTypeAndAgent(Integer type, Integer areaId, Integer pmId, Integer userId) {
		if (type == 3) {
			return userDao.getUserById(userId);
		} else if (type == 4 || type == 5) {
			return userDao.getUserByPmId(pmId);
		} else {
			return userDao.getUserByTypeAndAgent(type, areaId);
		}
	}

	@Transactional(readOnly = true)
	public User findUser(Integer id) {
		return userDao.findUser(id);
	}

	@Transactional
	public void setAgent(Integer userType, Integer roleId, Integer userId) {
		userDao.deleteAgentByRole(userType);
		userDao.setAgentById(roleId, userId);
	}

	public void saveUser(User user) {
		userDao.save(user);
	}

	public List<User> getAll() {
		return userDao.getAll();
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
