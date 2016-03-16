package cn.finance.web.action.json;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import cn.finance.model.Account;
import cn.finance.model.Project;
import cn.finance.service.AccountService;
import cn.finance.service.ProjectService;

import com.opensymphony.xwork2.Action;

public class GetAccountAction extends JdbcDaoSupport  {

	private static final long serialVersionUID = 1L;

	private Integer projectId;
	
	private AccountService accountService;
	
	private List<Account> accounts = new ArrayList<Account>();

	/**
	 * 根据客户id查找所有的项目
	 * @return
	 */
	public String execute(){
		accounts = accountService.getAllByProjectId(projectId);
		return Action.SUCCESS;
	}

	public AccountService getAccountService() {
		return accountService;
	}


	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}


	public List<Account> getAccounts() {
		return accounts;
	}


	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}


	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
}
