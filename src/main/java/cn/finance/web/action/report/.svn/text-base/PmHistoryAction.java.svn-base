package cn.finance.web.action.report;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.finance.model.PmHistory;
import cn.finance.service.ChannelService;
import cn.finance.service.ClientService;
import cn.finance.service.MediaService;
import cn.finance.service.PmHistoryService;
import cn.finance.service.ProjectService;
import cn.finance.util.ExcelUtils;
import cn.finance.util.FileUtils;
import cn.finance.util.Struts2Utils;
import cn.finance.web.action.base.BaseAction;
import cn.finance.web.model.SearchView;
import cn.springside.modules.orm.Page;

public class PmHistoryAction extends BaseAction {

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
	private PmHistoryService pmHistoryService;
	
	private SearchView searchView;
	
	// 基本属性
	private PmHistory entity;
	
	private Page<PmHistory> page = new Page<PmHistory>(30);
	
	public void excelOutput() throws IOException {
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("file");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String("SEM分析师管理帐户历史.xls".getBytes("GB2312"), "iso8859-1"));
		String path = FileUtils.getProjectUrl() + "template/pmHistoryList.xls";
		List<PmHistory> pmHistorys = pmHistoryService.getAll(page,true);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pmHistory", pmHistorys);
		short []cols = cn.finance.util.StringUtils.getShouts(page.getColumns());
		ExcelUtils.transformXLS(path, map, cols).write(
				Struts2Utils.getResponse().getOutputStream());
	}
	
	public String pmHistoryList() throws Exception {
		initSearchView();
		Struts2Utils.getRequest().setAttribute("nav", "3");
		Struts2Utils.getRequest().setAttribute("subNav", "2");
		page.setResult(pmHistoryService.getAll(page,false));
		page.setTotalCount(pmHistoryService.getTotalCount(page));
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
	}
	
	public Page<PmHistory> getPage() {
		return page;
	}

	public void setPage(Page<PmHistory> page) {
		this.page = page;
	}

	public PmHistory getEntity() {
		return entity;
	}

	public void setEntity(PmHistory entity) {
		this.entity = entity;
	}

	public SearchView getSearchView() {
		return searchView;
	}

	public void setSearchView(SearchView searchView) {
		this.searchView = searchView;
	}
}