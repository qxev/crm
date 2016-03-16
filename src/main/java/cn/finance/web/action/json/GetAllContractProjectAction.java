package cn.finance.web.action.json;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import cn.finance.model.ContractProject;
import cn.finance.service.ContractProjectService;

import com.opensymphony.xwork2.Action;

public class GetAllContractProjectAction extends JdbcDaoSupport {

	private static final long serialVersionUID = 1L;

	private String query;

	private String[] suggestions;

	private ContractProjectService contractProjectService;

	/**
	 * 根据客户id查找所有的项目
	 * 
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String execute() throws UnsupportedEncodingException {
		List<ContractProject> projects = contractProjectService.getAllByContractProjectName(query);		
		suggestions = new String[projects.size()];
		for (int i = 0; i < projects.size(); i++) {
			ContractProject project = projects.get(i);
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

	public void setContractProjectService(
			ContractProjectService contractProjectService) {
		this.contractProjectService = contractProjectService;
	}
	
}
