package cn.finance.web.action.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.finance.model.Media;
import cn.finance.model.Project;
import cn.finance.service.ClientService;
import cn.finance.service.MediaService;
import cn.finance.service.ProjectService;
import cn.finance.util.ExcelUtils;
import cn.finance.util.FileUtils;
import cn.finance.util.Struts2Utils;
import cn.finance.web.action.base.BaseAction;
import cn.finance.web.model.SearchView;
import cn.springside.modules.orm.Page;

public class MediaAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ClientService clientService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private MediaService mediaService;
	
	private String searchDate;
	
	// 基本属性
	private Media entity;
	
	private SearchView searchView;
	
	private String clientName;
	
	private String projectName;
	
	private Page<Media> page = new Page<Media>(30);
	
	public void excelOutput() throws IOException {
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("file");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String("媒体列表.xls".getBytes("GB2312"), "iso8859-1"));
		String path = FileUtils.getProjectUrl();
		path = path + "template/mediaList.xls";
		List<Media> medias = mediaService.getAll(page,true);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("media", medias);
		short []cols = cn.finance.util.StringUtils.getShouts(page.getColumns());
		ExcelUtils.transformXLS(path, map, cols).write(
				Struts2Utils.getResponse().getOutputStream());
	}
	
	public String mediaList() throws Exception {
		initSearchView();
		Struts2Utils.getRequest().setAttribute("nav", "2");
		Struts2Utils.getRequest().setAttribute("subNav", "3");
		page.setResult(mediaService.getAll(page,false));
		page.setTotalCount(mediaService.getTotalCount(page));
		return SUCCESS;
	}

	public void initSearchView(){
		if (searchView == null) {
			searchView = new SearchView();
			if (page.getFilter()!=null) {
				String []pars = StringUtils.split(page.getFilter(),"|");
				if (pars.length==3){
					searchView.setClientId(NumberUtils.toInt(pars[0]));
					searchView.setProjectId(NumberUtils.toInt(pars[1]));
					searchView.setStatusId(NumberUtils.toInt(pars[2]));
				}
			}
		} else {
			StringBuffer filter = new StringBuffer();
			filter.append(searchView.getClientId()).append("|").append(searchView.getProjectId()).append("|")
			.append(searchView.getStatusId());
			page.setFilter(filter.toString());
			if (searchView.getProjectId()!=null&&searchView.getProjectId()!=0){
				Project project = projectService.getProjectById(searchView.getProjectId());
				if (project!=null){
					projectName = project.getName();
					clientName = project.getClient().getName();
					searchView.setClientId(project.getClient().getId());
				}
			}
		}
		searchView.setClients(clientService.getAll());
		if (searchView.getClientId()!=null && searchView.getClientId()!=0){
			searchView.setProjects(projectService.getAllByClientId(searchView.getClientId()));
		}
	}
	
	public Page<Media> getPage() {
		return page;
	}

	public void setPage(Page<Media> page) {
		this.page = page;
	}

	public Media getEntity() {
		return entity;
	}

	public void setEntity(Media entity) {
		this.entity = entity;
	}

	public SearchView getSearchView() {
		return searchView;
	}

	public void setSearchView(SearchView searchView) {
		this.searchView = searchView;
	}
	
	public String getSearchDate() {
		return searchDate;
	}

	public void setSearchDate(String searchDate) {
		this.searchDate = searchDate;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

}