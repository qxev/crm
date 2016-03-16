package cn.finance.web.action.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import cn.finance.model.ContractClient;
import cn.finance.service.ContractClientService;
import cn.finance.util.ExcelUtils;
import cn.finance.util.FileUtils;
import cn.finance.util.StringUtils;
import cn.finance.util.Struts2Utils;
import cn.finance.web.action.base.BaseAction;
import cn.finance.web.model.SearchView;
import cn.springside.modules.orm.Page;

public class ContractClientAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ContractClientService contractClientService;

	private ContractClient contractClient;

	private Integer contractClientId;

	private SearchView searchView;
	
	// 基本属性
	private ContractClient entity;

	private Page<ContractClient> page = new Page<ContractClient>(30);
	
	public String contractClientList() throws UnsupportedEncodingException {
		if (searchView == null) {
			searchView = new SearchView();
			if (page.getFilter()!=null) {
				searchView.setClientName("0".equals(page.getFilter())?"":page.getFilter());
			}
		} else {
			page.setFilter("".equals(searchView.getClientName())?"0":searchView.getClientName());
		}
		Struts2Utils.getRequest().setAttribute("nav", "6");
		Struts2Utils.getRequest().setAttribute("subNav", "2");
		if (page.getOrderBy()==null){
			page.setOrderBy("createAt");
			page.setOrder("desc");
		}
		page.setResult(contractClientService.getAll(page,false));
		page.setTotalCount(contractClientService.getTotalCount(page));
		return SUCCESS;
	}

	public String initContractClient() {
		Struts2Utils.getRequest().setAttribute("nav", "6");
		Struts2Utils.getRequest().setAttribute("subNav", "2");
		if (searchView == null) {
			searchView = new SearchView();
		}
		contractClient = contractClientService.getContractClientById(contractClientId);
		return "edit";
	}
	
	public String addContractClient() {
		Struts2Utils.getRequest().setAttribute("nav", "6");
		Struts2Utils.getRequest().setAttribute("subNav", "1");
		if (searchView == null) {
			searchView = new SearchView();
		}
		contractClient = contractClientService.createContractClient();
		return "edit";
	}

	public String editContractClient() {
		Struts2Utils.getRequest().setAttribute("nav", "6");
		Struts2Utils.getRequest().setAttribute("subNav", "1");
		List<ContractClient> contractClients = contractClientService.getByContractClientName(searchView.getClientName(),contractClientId);
		if (contractClients.size()>0){
			this.addActionMessage("客户名重复！");
			return "edit";
		}else {
			if (contractClientId==null){
				contractClient = contractClientService.createContractClient();
			} else {
				contractClient = contractClientService.getContractClientById(contractClientId);
			}
			contractClient.setType(searchView.getClientTypeId());
			contractClient.setName(searchView.getClientName());
			contractClientService.save(contractClient);
			if (contractClientId==null){
				this.addActionMessage("新增客户成功！");
				contractClientId = contractClient.getId();
				return "next";
			} else {
				this.addActionMessage("修改客户成功！");
			}
		}
		return NONE;
	}

	public void excelOutput() throws IOException {
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("file");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String("客户列表.xls".getBytes("GB2312"), "iso8859-1"));
		String path = FileUtils.getProjectUrl();
		path = path + "template/contractClientList.xls";
		List<ContractClient> contractClients = contractClientService.getAll(page,true);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("contractClient", contractClients);
		short []cols = StringUtils.getShouts(page.getColumns());
		ExcelUtils.transformXLS(path, map, cols).write(
				Struts2Utils.getResponse().getOutputStream());
	}

	public Page<ContractClient> getPage() {
		return page;
	}

	public void setPage(Page<ContractClient> page) {
		this.page = page;
	}

	public ContractClient getEntity() {
		return entity;
	}

	public void setEntity(ContractClient entity) {
		this.entity = entity;
	}

	public ContractClient getContractClient() {
		return contractClient;
	}

	public void setContractClient(ContractClient contractClient) {
		this.contractClient = contractClient;
	}

	public Integer getContractClientId() {
		return contractClientId;
	}

	public void setContractClientId(Integer contractClientId) {
		this.contractClientId = contractClientId;
	}

	public SearchView getSearchView() {
		return searchView;
	}

	public void setSearchView(SearchView searchView) {
		this.searchView = searchView;
	}

}