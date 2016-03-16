package cn.finance.web.action.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.finance.model.Channel;
import cn.finance.model.Media;
import cn.finance.model.Project;
import cn.finance.service.ChannelService;
import cn.finance.service.ClientService;
import cn.finance.service.MediaService;
import cn.finance.service.ProjectService;
import cn.finance.util.ExcelUtils;
import cn.finance.util.FileUtils;
import cn.finance.util.Struts2Utils;
import cn.finance.web.action.base.BaseAction;
import cn.finance.web.model.SearchView;
import cn.springside.modules.orm.Page;

public class ChannelAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private MediaService mediaService;
	
	@Autowired
	private ChannelService channelService;

	// 基本属性
	private Channel entity;
	
	private SearchView searchView;
	
	private String clientName;
	
	private String projectName;
	
	private String mediaName;
	
	private Page<Channel> page = new Page<Channel>(30);
	
	public void excelOutput() throws IOException {
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("file");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String("渠道列表.xls".getBytes("GB2312"), "iso8859-1"));
		String path = FileUtils.getProjectUrl() + "template/channelList.xls";
		List<Channel> channels = channelService.getAll(page,true);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("channel", channels);
		short []cols = cn.finance.util.StringUtils.getShouts(page.getColumns());
		ExcelUtils.transformXLS(path, map, cols).write(
				Struts2Utils.getResponse().getOutputStream());
	}
	
	public String channelList() throws Exception {
		initSearchView();
		Struts2Utils.getRequest().setAttribute("nav", "2");
		Struts2Utils.getRequest().setAttribute("subNav", "4");
		page.setResult(channelService.getAll(page,false));
		page.setTotalCount(channelService.getTotalCount(page));
		return SUCCESS;
	}

	public void initSearchView(){
		if (searchView == null) {
			searchView = new SearchView();
			if (page.getFilter()!=null) {
				String []pars = StringUtils.split(page.getFilter(),"|");
				if (pars.length==6){
					searchView.setClientId(NumberUtils.toInt(pars[0]));
					searchView.setProjectId(NumberUtils.toInt(pars[1]));
					searchView.setMediaId(NumberUtils.toInt(pars[2]));
					searchView.setStatusId(NumberUtils.toInt(pars[3]));
				}
			}
		} else {
			StringBuffer filter = new StringBuffer();
			filter.append(searchView.getClientId()).append("|").append(searchView.getProjectId()).append("|")
			.append(searchView.getMediaId()).append("|").append(searchView.getStatusId());
			page.setFilter(filter.toString());
			if (searchView.getProjectId()!=null&&searchView.getProjectId()!=0){
				Project project = projectService.getProjectById(searchView.getProjectId());
				if (project!=null){
					projectName = project.getName();
					clientName = project.getClient().getName();
					searchView.setClientId(project.getClient().getId());
				}
			}
			if (searchView.getMediaId()!=null&&searchView.getMediaId()!=0){
				Media media = mediaService.getMediaById(searchView.getMediaId());
				if (media!=null)
					mediaName = media.getName();
			}

		}
		searchView.setClients(clientService.getAll());
		if (searchView.getClientId()!=null && searchView.getClientId()!=0){
			searchView.setProjects(projectService.getAllByClientId(searchView.getClientId()));
		}
		searchView.setMedias(mediaService.getAll());
	}
	
	public Page<Channel> getPage() {
		return page;
	}

	public void setPage(Page<Channel> page) {
		this.page = page;
	}

	public Channel getEntity() {
		return entity;
	}

	public void setEntity(Channel entity) {
		this.entity = entity;
	}

	public SearchView getSearchView() {
		return searchView;
	}

	public void setSearchView(SearchView searchView) {
		this.searchView = searchView;
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

	public String getMediaName() {
		return mediaName;
	}

	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}
}