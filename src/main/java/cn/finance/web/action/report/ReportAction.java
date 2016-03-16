package cn.finance.web.action.report;

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
import cn.finance.model.bean.ReportBean;
import cn.finance.service.ChannelService;
import cn.finance.service.ClientService;
import cn.finance.service.ExpenseService;
import cn.finance.service.MediaService;
import cn.finance.service.PmService;
import cn.finance.service.ProjectService;
import cn.finance.util.ExcelUtils;
import cn.finance.util.FileUtils;
import cn.finance.util.Struts2Utils;
import cn.finance.web.action.base.BaseAction;
import cn.finance.web.model.SearchView;
import cn.springside.modules.orm.Page;

public class ReportAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private MediaService mediaService;
	
	@Autowired
	private ProjectService projectService;

	@Autowired
	private ChannelService channelService;

	@Autowired
	private ExpenseService expenseService;
	
	@Autowired
	private PmService pmService;
	
	private SearchView searchView;
	
	private Page<ReportBean> page = new Page<ReportBean>(30);
	
	private ReportBean sumBean; 
	
	private String mediaName;

	private String projectName;

	public void excelOutput() throws IOException {
		String fileName = "项目级别SEM业务报表.xls";
		String fileMergeName = "template/reportProjectList.xls";
		String []pars = StringUtils.split(page.getFilter(),"|");
		if (pars.length==4){
			if (!"0".equals(pars[3])) {
				fileName = "账户级别SEM业务报表.xls";
				fileMergeName = "template/reportAccountList.xls";
			} else if (!"0".equals(pars[2])) {
				fileName = "媒体级别SEM业务报表.xls";
				fileMergeName = "template/reportMediaList.xls";
			}
		}
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("file");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String(fileName.getBytes("GB2312"), "iso8859-1"));
		String path = FileUtils.getProjectUrl() + fileMergeName;
		List<ReportBean> reportBeans = expenseService.getReportByProject(page,true);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportBean", reportBeans);
		short []cols = cn.finance.util.StringUtils.getShouts(page.getColumns());
		ExcelUtils.transformXLS(path, map, cols).write(
				Struts2Utils.getResponse().getOutputStream());
	}
	
	public String reportList() throws Exception {
		initSearchView();
		Struts2Utils.getRequest().setAttribute("nav", "3");
		Struts2Utils.getRequest().setAttribute("subNav", "1");
		sumBean = expenseService.getSumReportByProject(page);
		page.setResult(expenseService.getReportByProject(page,false));
		page.setTotalCount(expenseService.getReportByProjectCount(page));
		return SUCCESS;
	}
	
	public String reportAccountList() throws Exception {
		initSearchView();
		if ("".equals(page.getFilter()))
			page.setFilter("0|0|0|-1|0|0|0|0|0|0|0");
		Struts2Utils.getRequest().setAttribute("nav", "3");
		Struts2Utils.getRequest().setAttribute("subNav", "9");
		sumBean = expenseService.getSumReportByProject(page);
		page.setResult(expenseService.getReportByProject(page,false));
		page.setTotalCount(expenseService.getReportByProjectCount(page));
		return SUCCESS;
	}
	
	public void initSearchView(){
		if (searchView == null) {
			searchView = new SearchView();
		} else {
			StringBuffer filter = new StringBuffer();
			filter.append("".equals(searchView.getStartDate())?"0":searchView.getStartDate()).append("|")
			.append("".equals(searchView.getEndDate())?"0":searchView.getEndDate()).append("|")
			.append("".equals(searchView.getProjectId())?"0":searchView.getProjectId()).append("|")
			.append("".equals(searchView.getMediaId())?"0":searchView.getMediaId()).append("|")
			.append("".equals(searchView.getClientId())?"0":searchView.getClientId()).append("|")
			.append("".equals(searchView.getpId())?"0":searchView.getpId()).append("|")
			.append("".equals(searchView.getmId())?"0":searchView.getmId()).append("|")
			.append("".equals(searchView.getChannelId())?"0":searchView.getChannelId()).append("|")
			.append("".equals(searchView.getPmId())?"0":searchView.getPmId()).append("|")
			.append("".equals(searchView.getClientName())?"0":searchView.getClientName()).append("|")
			.append("".equals(searchView.getProjectName())?"0":searchView.getProjectName());
			page.setFilter(filter.toString());
			
		}
		if (page.getFilter()!=null) {
			String []pars = StringUtils.split(page.getFilter(),"|");
			if (pars.length==11){
				searchView.setStartDate("0".equals(pars[0])?"":pars[0]);
				searchView.setEndDate("0".equals(pars[1])?"":pars[1]);
				searchView.setProjectId(Integer.valueOf(pars[2]));
				searchView.setMediaId(Integer.valueOf(pars[3]));
				
				searchView.setClientId(Integer.valueOf(pars[4]));
				searchView.setpId(Integer.valueOf(pars[5]));
				searchView.setmId(Integer.valueOf(pars[6]));
				searchView.setChannelId(Integer.valueOf(pars[7]));
				searchView.setPmId(NumberUtils.toInt(pars[8]));
				searchView.setClientName("0".equals(pars[9])?"":pars[9]);
				searchView.setProjectName("0".equals(pars[10])?"":pars[10]);
			}
		}
		searchView.setClients(clientService.getAll());
		if (searchView.getClientId()!=null && searchView.getClientId()!=0){
			searchView.setProjects(projectService.getAllByClientId(searchView.getClientId()));
		}
		if (searchView.getProjectId()!=null&&searchView.getProjectId()!=0){
			Project project = projectService.getProjectById(searchView.getProjectId());
			if (project!=null)
				projectName = project.getName();
		}
		searchView.setMedias(mediaService.getAll());
		if (searchView.getmId()!=null && searchView.getmId()!=0){
			searchView.setChannels(channelService.getAllByMediaId(searchView.getmId()));
		}
		if (searchView.getMediaId()!=null&&searchView.getMediaId()!=0){
			Media media = mediaService.getMediaById(searchView.getMediaId());
			if (media!=null)
				mediaName = media.getName();
		}
		searchView.setPms(pmService.getAll());
		if (page.getOrderBy()==null){
			page.setOrderBy("e.account.project.name");
			page.setOrder("asc");
		}
	}
	
	public Page<ReportBean> getPage() {
		return page;
	}

	public void setPage(Page<ReportBean> page) {
		this.page = page;
	}

	public SearchView getSearchView() {
		return searchView;
	}

	public void setSearchView(SearchView searchView) {
		this.searchView = searchView;
	}

	public ReportBean getSumBean() {
		return sumBean;
	}

	public void setSumBean(ReportBean sumBean) {
		this.sumBean = sumBean;
	}
	
	public String getMediaName() {
		return mediaName;
	}

	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

}