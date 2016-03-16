package cn.finance.web.action.json;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import cn.finance.model.Client;
import cn.finance.model.Project;
import cn.finance.service.ClientService;
import cn.finance.service.ProjectService;

import com.opensymphony.xwork2.Action;

public class GetAllProjectAction extends JdbcDaoSupport {

	private static final long serialVersionUID = 1L;

	private String query;

	private String[] suggestions;

	private ProjectService projectService;

	/**
	 * 根据客户id查找所有的项目
	 * 
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String execute() throws UnsupportedEncodingException {
		List<Project> projects = projectService.getAllByProjectName(query);		
		suggestions = new String[projects.size()];
		for (int i = 0; i < projects.size(); i++) {
			Project project = projects.get(i);
			suggestions[i] = project.getName();
		}
		return Action.SUCCESS;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String[] getSuggestions() {
		return suggestions;
	}

	public String getQuery() {
		return query;
	}

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

}
