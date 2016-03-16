package cn.finance.web.action.json;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import cn.finance.model.Project;
import cn.finance.service.ProjectService;

import com.opensymphony.xwork2.Action;

public class GetProjectAction extends JdbcDaoSupport  {

	private static final long serialVersionUID = 1L;

	private Integer clientId;
	
	private ProjectService projectService;
	
	private List<Project> projects = new ArrayList<Project>();

	/**
	 * 根据客户id查找所有的项目
	 * @return
	 */
	public String execute(){
		projects = projectService.getAllByClientId(clientId);
		return Action.SUCCESS;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public ProjectService getProjectService() {
		return projectService;
	}
	
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
}
