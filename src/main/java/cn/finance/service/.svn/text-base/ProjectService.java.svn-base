package cn.finance.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.finance.dao.ProjectDao;
import cn.finance.model.Client;
import cn.finance.model.Project;
import cn.springside.modules.orm.Page;

@Transactional
public class ProjectService {

	private ProjectDao projectDao;

	public ProjectDao getProjectDao() {
		return projectDao;
	}

	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	@Transactional(readOnly = true)
	public List<Project> getAllByClientId(Integer clientId) {
		return projectDao.getAllByClientId(clientId);
	}

	@Transactional(readOnly = true)
	public List<Project> getAll() {
		return projectDao.getAll();
	}
	
	public Integer getAllStatusByClientId(Integer clientId) {
		return projectDao.getAllStatusByClientId(clientId);
	}

	@Transactional(readOnly = true)
	public List<Project> getAll(Page<Project> page, boolean hasTotal) {
		return projectDao.getAll(page,hasTotal);
	}

	@Transactional(readOnly = true)
	public int getTotalCount(Page<Project>page) {
		return projectDao.getTotalCount(page);
	}
	
	public Project getProjectById(Integer id) {
		return projectDao.getProjectById(id);
	}
	
	@Transactional
	public void save(Project project){
		projectDao.save(project);
	}
	
	@Transactional(readOnly = true)
	public List<Project> getAllByProjectName(String name){
		return projectDao.getAllByProjectName(name);
	}
	
	public Project createProject(){
		return projectDao.createProject();
	}
}
