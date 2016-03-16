package cn.finance.web.action.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import cn.finance.model.Client;
import cn.finance.service.ClientService;
import cn.finance.util.ExcelUtils;
import cn.finance.util.FileUtils;
import cn.finance.util.StringUtils;
import cn.finance.util.Struts2Utils;
import cn.finance.web.action.base.BaseAction;
import cn.finance.web.model.SearchView;
import cn.springside.modules.orm.Page;

public class ClientAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ClientService clientService;

	private Client client;

	private Integer clientId;

	private String pageNo;
	
	private SearchView searchView;

	// 基本属性
	private Client entity;

	private Page<Client> page = new Page<Client>(30);
	
	public String clientList() throws UnsupportedEncodingException {
		if (searchView == null) {
			searchView = new SearchView();
			if (page.getFilter()!=null) {
				searchView.setClientName("0".equals(page.getFilter())?"":page.getFilter());
			}
		} else {
			page.setFilter("".equals(searchView.getClientName())?"0":searchView.getClientName());
		}
		Struts2Utils.getRequest().setAttribute("nav", "2");
		Struts2Utils.getRequest().setAttribute("subNav", "1");
		if (page.getOrderBy()==null){
			page.setOrderBy("name");
			page.setOrder("asc");
		}
		page.setResult(clientService.getAll(page,false));
		page.setTotalCount(clientService.getTotalCount(page));
		return SUCCESS;
	}

	public String initClient() {
		Struts2Utils.getRequest().setAttribute("nav", "2");
		Struts2Utils.getRequest().setAttribute("subNav", "1");
		if (searchView == null) {
			searchView = new SearchView();
		}
		client = clientService.findById(clientId);
		return "edit";
	}
	
	public String addClient() {
		Struts2Utils.getRequest().setAttribute("nav", "2");
		Struts2Utils.getRequest().setAttribute("subNav", "7");
		if (searchView == null) {
			searchView = new SearchView();
		}
		client = clientService.createClient();
		return "edit";
	}

	public String editClient() {
		if (clientId==null){
			client = clientService.createClient();
		} else {
			client = clientService.findById(clientId);
		}
		client.setType(searchView.getClientTypeId());
		client.setName(searchView.getClientName());
		clientService.save(client);
		if (clientId==null){
			this.addActionMessage("新增客户成功！");
		} else {
			this.addActionMessage("修改客户成功！");
		}
		return NONE;
	}

	public void excelOutput() throws IOException {
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("file");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String("客户列表.xls".getBytes("GB2312"), "iso8859-1"));
		String path = FileUtils.getProjectUrl();
		path = path + "template/clientList.xls";
		List<Client> clients = clientService.getAll(page,true);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("client", clients);
		short []cols = StringUtils.getShouts(page.getColumns());
		ExcelUtils.transformXLS(path, map, cols).write(
				Struts2Utils.getResponse().getOutputStream());
	}

	public Page<Client> getPage() {
		return page;
	}

	public void setPage(Page<Client> page) {
		this.page = page;
	}

	public Client getEntity() {
		return entity;
	}

	public void setEntity(Client entity) {
		this.entity = entity;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public SearchView getSearchView() {
		return searchView;
	}

	public void setSearchView(SearchView searchView) {
		this.searchView = searchView;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

}