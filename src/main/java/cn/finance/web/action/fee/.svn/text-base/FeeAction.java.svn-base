package cn.finance.web.action.fee;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.finance.model.Account;
import cn.finance.model.Project;
import cn.finance.model.SupplementHistory;
import cn.finance.service.AccountService;
import cn.finance.service.ProjectService;
import cn.finance.service.SupplementHistoryService;
import cn.finance.util.DateUtil;
import cn.finance.util.Struts2Utils;
import cn.finance.web.action.base.BaseAction;

public class FeeAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private SupplementHistoryService supplementHistoryService;
	
	// 基本属性
	private SupplementHistory supplementHistory;

	private List<Account> accounts ;
	
	private List<Project> projects;
	
	private Integer acId;
	
	private Integer supplementId;
	
	private String supplementDate;
	
	private String nextSupplementDate;
	
	private String lastType;

	private String filter;
	
	private Integer pageNo;
	
	public String addFee(){
		Struts2Utils.getRequest().setAttribute("nav", "4");
		Struts2Utils.getRequest().setAttribute("subNav", "1");
		if (supplementHistory==null) {
			supplementHistory=new SupplementHistory();
		}
		Account account=accountService.getAccountById(acId);
		supplementHistory.setSupplementDate(DateUtil.stringToDate(supplementDate));
		supplementHistory.setNextSupplementDate(DateUtil.stringToDate(nextSupplementDate));
		//账户余额加钱
		BigDecimal totalBalance = account.getTotalBalance();
		if (totalBalance == null) 
			totalBalance = new BigDecimal(0);
		account.setTotalBalance(totalBalance.add(supplementHistory.getTotalAmount()));
		account.setLastSupplement(supplementHistory.getTotalAmount());
		account.setLastSuppleDate(new Date());
		supplementHistory.setAccount(account);
		supplementHistory.setCreateAt(new Date());
		if (supplementHistory.getType()==2) {
			supplementHistory.setRepayAmount(supplementHistory.getPayAmount());
			supplementHistory.setPayAmount(new BigDecimal(0));
		}
		supplementHistoryService.save(supplementHistory);
		this.addActionMessage("新增充值记录成功！");
		return initFee();
	}
	
	public String initFee(){
		supplementHistory=new SupplementHistory();
		supplementDate = "";
		acId = 0;
		Struts2Utils.getRequest().setAttribute("nav", "4");
		Struts2Utils.getRequest().setAttribute("subNav", "1");
		projects=projectService.getAll();
		if (accounts ==null){
			accounts = accountService.getAllByProjectId(1);
		}
		return SUCCESS;
	}
	
	public String initEditFee(){
		Struts2Utils.getRequest().setAttribute("nav", "4");
		if ("2".equals(lastType)){
			Struts2Utils.getRequest().setAttribute("subNav", "4");
		} else {
			Struts2Utils.getRequest().setAttribute("subNav", "3");
		}
		supplementHistory = supplementHistoryService.getSupplementHistoryById(supplementId);
		return NONE;
	}
	
	public String editFee(){
		Struts2Utils.getRequest().setAttribute("nav", "4");
		Struts2Utils.getRequest().setAttribute("subNav", "3");
		supplementHistoryService.editSupplementHistory(supplementId,supplementHistory);
		if ("2".equals(lastType)){
			return INPUT;
		} else {
			return SUCCESS;
		}
	}
	
	public String deleteFee(){
		Struts2Utils.getRequest().setAttribute("nav", "4");
		Struts2Utils.getRequest().setAttribute("subNav", "3");
		SupplementHistory saveSupplementHistory = supplementHistoryService.getSupplementHistoryById(supplementId);
		supplementHistoryService.deleteSupplementHistory(saveSupplementHistory);
		if ("2".equals(lastType)){
			return INPUT;
		} else {
			return SUCCESS;
		}
	}
	
	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	public SupplementHistory getSupplementHistory() {
		return supplementHistory;
	}

	public void setSupplementHistory(SupplementHistory supplementHistory) {
		this.supplementHistory = supplementHistory;
	}

	public Integer getAcId() {
		return acId;
	}

	public void setAcId(Integer acId) {
		this.acId = acId;
	}
	
	public String getSupplementDate() {
		return supplementDate;
	}

	public void setSupplementDate(String supplementDate) {
		this.supplementDate = supplementDate;
	}

	public String getNextSupplementDate() {
		return nextSupplementDate;
	}

	public void setNextSupplementDate(String nextSupplementDate) {
		this.nextSupplementDate = nextSupplementDate;
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

	public Integer getSupplementId() {
		return supplementId;
	}

	public void setSupplementId(Integer supplementId) {
		this.supplementId = supplementId;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public String getLastType() {
		return lastType;
	}

	public void setLastType(String lastType) {
		this.lastType = lastType;
	}
}