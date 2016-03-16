package cn.finance.web.action.system;

import org.springframework.beans.factory.annotation.Autowired;

import cn.finance.model.ContractTemplate;
import cn.finance.service.BusinessTypeService;
import cn.finance.service.ContractService;
import cn.finance.util.Struts2Utils;
import cn.finance.web.action.base.BaseAction;
import cn.finance.web.model.SearchView;
import cn.springside.modules.orm.Page;

public class ContractTemplateAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ContractService contractService;
	
	private Page<ContractTemplate> page = new Page<ContractTemplate>(30);
	
	private Integer id;
	
	private SearchView searchView;
	
	@Autowired
	private BusinessTypeService businessTypeService;

	// 基本属性
	private ContractTemplate contractTemplate;

	public String list() throws Exception{
		if (searchView == null) {
			searchView = new SearchView();
			if ("".equals(page.getFilter())) {
				searchView.setBusinessTypeId(4);
				page.setFilter("4");
			}
		} else {
			page.setFilter(searchView.getBusinessTypeId().toString());
		}
		searchView.setBusinessTypes(businessTypeService.getAll());
		Struts2Utils.getRequest().setAttribute("nav", "5");
		Struts2Utils.getRequest().setAttribute("subNav", "8");
		page.setResult(contractService.getAllContractTemplate(page));
		page.setTotalCount(contractService.getTemplateCount(page));
		return SUCCESS;
	}
	
	public String edit() throws Exception {
		Struts2Utils.getRequest().setAttribute("nav", "5");
		Struts2Utils.getRequest().setAttribute("subNav", "8");
		contractTemplate=contractService.getContractTemplateById(id);
		return "edit";
	}
	
	public String save() throws Exception {
		Struts2Utils.getRequest().setAttribute("nav", "5");
		Struts2Utils.getRequest().setAttribute("subNav", "4");
		ContractTemplate editTemplate=contractService.getContractTemplateById(id);
		editTemplate.setContext(contractTemplate.getContext());
		contractService.saveTemplate(editTemplate);
		this.addActionMessage("修改合同模板成功！");
		return "save";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Page<ContractTemplate> getPage() {
		return page;
	}
	
	public void setPage(Page<ContractTemplate> page) {
		this.page = page;
	}

	public ContractTemplate getContractTemplate() {
		return contractTemplate;
	}

	public void setContractTemplate(ContractTemplate contractTemplate) {
		this.contractTemplate = contractTemplate;
	}

	public SearchView getSearchView() {
		return searchView;
	}

	public void setSearchView(SearchView searchView) {
		this.searchView = searchView;
	}
	
}