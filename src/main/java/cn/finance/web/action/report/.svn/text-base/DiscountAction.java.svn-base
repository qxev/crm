package cn.finance.web.action.report;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.finance.model.Discount;
import cn.finance.service.ChannelService;
import cn.finance.service.ClientService;
import cn.finance.service.DiscountService;
import cn.finance.service.MediaService;
import cn.finance.service.ProjectService;
import cn.finance.util.ExcelUtils;
import cn.finance.util.FileUtils;
import cn.finance.util.Struts2Utils;
import cn.finance.web.action.base.BaseAction;
import cn.finance.web.model.SearchView;
import cn.springside.modules.orm.Page;

public class DiscountAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private MediaService mediaService;

	@Autowired
	private ChannelService channelService;

	@Autowired
	private DiscountService discountService;
	
	private SearchView searchView;
	
	// 基本属性
	private Discount entity;
	
	private Page<Discount> page = new Page<Discount>(30);
	
	public void excelOutput() throws IOException {
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("file");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String("折扣率列表.xls".getBytes("GB2312"), "iso8859-1"));
		String path = FileUtils.getProjectUrl() + "template/discountList.xls";
		List<Discount> discounts = discountService.getAll(page,true);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("discount", discounts);
		short []cols = cn.finance.util.StringUtils.getShouts(page.getColumns());
		ExcelUtils.transformXLS(path, map, cols).write(
				Struts2Utils.getResponse().getOutputStream());
	}
	
	public String discountList() throws Exception {
		initSearchView();
		Struts2Utils.getRequest().setAttribute("nav", "3");
		Struts2Utils.getRequest().setAttribute("subNav", "3");
		page.setResult(discountService.getAll(page,false));
		page.setTotalCount(discountService.getTotalCount(page));
		return SUCCESS;
	}
	
	public void initSearchView(){
		if (searchView == null) {
			searchView = new SearchView();
		} else {
			StringBuffer filter = new StringBuffer();
			filter.append(searchView.getClientId()).append("|").append(searchView.getProjectId()).append("|")
			.append(searchView.getMediaId()).append("|").append(searchView.getChannelId()).append("|")
			.append("".equals(searchView.getStartDate())?"0":searchView.getStartDate()).append("|")
			.append("".equals(searchView.getEndDate())?"0":searchView.getEndDate());
			page.setFilter(filter.toString());
		}
		if (page.getFilter()!=null) {
			String []pars = StringUtils.split(page.getFilter(),"|");
			if (pars.length==6){
				searchView.setClientId(NumberUtils.toInt(pars[0]));
				searchView.setProjectId(NumberUtils.toInt(pars[1]));
				searchView.setMediaId(NumberUtils.toInt(pars[2]));
				searchView.setChannelId(NumberUtils.toInt(pars[3]));
				searchView.setStartDate("0".equals(pars[4])?"":pars[4]);
				searchView.setEndDate("0".equals(pars[5])?"":pars[5]);
			}
		}
		searchView.setClients(clientService.getAll());
		if (searchView.getClientId()!=null && searchView.getClientId()!=0){
			searchView.setProjects(projectService.getAllByClientId(searchView.getClientId()));
		}
		searchView.setMedias(mediaService.getAll());
		if (searchView.getMediaId()!=null && searchView.getMediaId()!=0){
			searchView.setChannels(channelService.getAllByMediaId(searchView.getMediaId()));
		}
		if (page.getOrderBy()==null){
			page.setOrderBy("account.project.name asc account.project.client.name asc account.name");
			page.setOrder("asc");
		}
	}
	
	public Page<Discount> getPage() {
		return page;
	}

	public void setPage(Page<Discount> page) {
		this.page = page;
	}

	public Discount getEntity() {
		return entity;
	}

	public void setEntity(Discount entity) {
		this.entity = entity;
	}

	public SearchView getSearchView() {
		return searchView;
	}

	public void setSearchView(SearchView searchView) {
		this.searchView = searchView;
	}
}