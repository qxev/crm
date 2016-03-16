package cn.finance.web.action.client;

import org.springframework.beans.factory.annotation.Autowired;

import cn.finance.model.Version;
import cn.finance.service.ContractService;
import cn.finance.util.Struts2Utils;
import cn.finance.web.action.base.BaseAction;
import cn.springside.modules.orm.Page;

public class VersionAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ContractService contractService;

	private Integer contractId;
	
	private Version version;
	
	private Page<Version> page = new Page<Version>(30);
	
	public String list() throws Exception {
		Struts2Utils.getRequest().setAttribute("nav", "6");
		Struts2Utils.getRequest().setAttribute("subNav", "7");
		page.setResult(contractService.getVersionByContractId(contractId));
		page.setTotalCount(page.getResult().size());
		return SUCCESS;
	}
	
	public String detail() throws Exception {
		Struts2Utils.getRequest().setAttribute("nav", "6");
		Struts2Utils.getRequest().setAttribute("subNav", "7");
		version = contractService.getVersionById(contractId);
		return "detail";
	}

	public Integer getContractId() {
		return contractId;
	}

	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}

	public Page<Version> getPage() {
		return page;
	}

	public void setPage(Page<Version> page) {
		this.page = page;
	}

	public Version getVersion() {
		return version;
	}

	public void setVersion(Version version) {
		this.version = version;
	}
	
}