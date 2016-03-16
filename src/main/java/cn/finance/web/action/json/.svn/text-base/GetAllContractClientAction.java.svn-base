package cn.finance.web.action.json;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import cn.finance.model.ContractClient;
import cn.finance.service.ContractClientService;

import com.opensymphony.xwork2.Action;

public class GetAllContractClientAction extends JdbcDaoSupport {

	private static final long serialVersionUID = 1L;

	private String query;

	private String[] suggestions;

	private ContractClientService contractClientService;

	/**
	 * 根据客户id查找所有的项目
	 * 
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String execute() throws UnsupportedEncodingException {
		List<ContractClient> clients = contractClientService.getAllByContractClientName(query);		
		suggestions = new String[clients.size()];
		for (int i = 0; i < clients.size(); i++) {
			ContractClient client = clients.get(i);
			suggestions[i] = client.getName();
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

	public void setContractClientService(ContractClientService contractClientService) {
		this.contractClientService = contractClientService;
	}

}
