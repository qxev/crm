package cn.finance.web.action.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.finance.model.Bd;
import cn.finance.model.BusinessType;
import cn.finance.model.ChargePerson;
import cn.finance.model.Client;
import cn.finance.model.Industry;
import cn.finance.model.Pm;
import cn.finance.model.Project;
import cn.finance.service.BdService;
import cn.finance.service.BusinessTypeService;
import cn.finance.service.ClientService;
import cn.finance.service.IndustryService;
import cn.finance.service.PmService;
import cn.finance.service.ProjectService;
import cn.finance.util.ExcelUtils;
import cn.finance.util.FileUtils;
import cn.finance.util.Struts2Utils;
import cn.finance.web.action.base.BaseAction;
import cn.finance.web.model.SearchView;
import cn.springside.modules.orm.Page;

public class ProjectAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private PmService pmService;

	@Autowired
	private BdService bdService;
	
	@Autowired
	private BusinessTypeService businessTypeService;
	
	@Autowired
	private IndustryService industryService;
	
	@Autowired
	private ClientService clientService; 
	
	private SearchView searchView;
	
	private Page<Project> page = new Page<Project>();
	
	private Integer projectId;
	
	private Integer clientId;

	private Integer businessTypeId;
	
	private String bsType;

	private Project project;
	
	private ChargePerson chargePerson;
	
	private String clientName;
	
	private String filter;
	
	private Integer pageNo;
	
	private List<ChargePerson> chargePersons = new ArrayList<ChargePerson>();

	public String initProject() {
		Struts2Utils.getRequest().setAttribute("nav", "2");
		Struts2Utils.getRequest().setAttribute("subNav", "2");
		if (searchView == null) {
			searchView = new SearchView();
		}
		searchView.setPms(pmService.getAll());
		searchView.setBds(bdService.getAll());
		searchView.setIndustrys(industryService.getAll());
		if (projectId !=null){
			project = projectService.getProjectById(projectId);
			clientName = project.getClient().getName();
		} else {
			project = projectService.createProject();
			Client client = clientService.findById(clientId);
			clientName = client.getName();
		}
//		chargePersons = chargePersonService.getChargePersonByProjectId(projectId);
		return SUCCESS;
	}
	
	public String editProject() throws UnsupportedEncodingException {
		Project editProject = new Project();
		if (projectId==null){
			editProject = projectService.createProject();
			Client client = clientService.findById(clientId);
			editProject.setClient(client);
		} else {
			editProject = projectService.getProjectById(projectId);
		}
		editProject.setName(searchView.getProjectName());
		Industry industry = industryService.getIndustryById(searchView.getIndustryId());
		searchView.setIndustryId(0);
		editProject.setIndustry(industry);
		Pm pm = pmService.getPmById(searchView.getPmId());
		List<Pm> pms = new ArrayList<Pm>();
		if (pm!=null){
			pms.add(pm);
			editProject.setPms(new HashSet<Pm>(pms));
		} else {
			editProject.setPms(null);
		}  
		Bd bd = bdService.getBdById(searchView.getBdId());
		List<Bd> bds = new ArrayList<Bd>();
		if (bd!=null){
			bds.add(bd);
			editProject.setBds(new HashSet<Bd>(bds));
		} else {
			editProject.setBds(null);
		}
		projectService.save(editProject);
		if (projectId==null){
			this.addActionMessage("新建项目成功");
		} else {
			this.addActionMessage("修改项目成功");
		}
		searchView = null;
		return projectList();
	}
	
	public void excelOutput() throws IOException {
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("file");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String("项目列表.xls".getBytes("GB2312"), "iso8859-1"));
		String path = FileUtils.getProjectUrl();
		path = path + "template/projectList.xls";
		List<Project> projects = projectService.getAll(page,true);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("project", projects);
		short []cols = cn.finance.util.StringUtils.getShouts(page.getColumns());
		ExcelUtils.transformXLS(path, map, cols).write(
				Struts2Utils.getResponse().getOutputStream());
	}
	
	public String projectList() throws UnsupportedEncodingException{
		initSearchView();
		Struts2Utils.getRequest().setAttribute("nav", "2");
		Struts2Utils.getRequest().setAttribute("subNav", "2");
		page.setResult(projectService.getAll(page,false));
		page.setTotalCount(projectService.getTotalCount(page));
		return SUCCESS;
	}
	
	public void initSearchView() throws UnsupportedEncodingException{
		if (searchView == null) {
			searchView = new SearchView();
			if (page.getFilter()!=null) {
				String []pars = StringUtils.split(page.getFilter(),"|");
				if (pars.length==9){
					searchView.setClientId(NumberUtils.toInt(pars[0]));
					searchView.setPmId(NumberUtils.toInt(pars[1]));
					searchView.setBdId(NumberUtils.toInt(pars[2]));
					searchView.setSourceId(NumberUtils.toInt(pars[3]));
					searchView.setIndustryId(NumberUtils.toInt(pars[4]));
					searchView.setStatusId(NumberUtils.toInt(pars[5]));
					String client = pars[6];
					String project = pars[7];
					searchView.setClientName("0".equals(client)?"":client);
					searchView.setProjectName("0".equals(project)?"":project);
				} else {
					page.setFilter("0|0|0|0|0|0|0|0");
				}
			}
		} else {
			StringBuffer filter = new StringBuffer();
			filter.append(searchView.getClientId()).append("|").append(searchView.getPmId()).append("|")
			.append(searchView.getBdId()).append("|").append(searchView.getSourceId()).append("|")
			.append(searchView.getIndustryId()).append("|").append(searchView.getStatusId()).append("|")
			.append(searchView.getClientName()==null||"".equals(searchView.getClientName())?"0":searchView.getClientName()).append("|")
			.append(searchView.getProjectName()==null||"".equals(searchView.getProjectName())?"0":searchView.getProjectName());
			page.setFilter(filter.toString());
		}
		if (searchView.getClientId()!=null&&searchView.getClientId()!=0){
			Client client = clientService.findById(searchView.getClientId());
			if (client!=null)
				clientName = client.getName();
		}
		searchView.setClients(clientService.getAll());
		searchView.setPms(pmService.getByBusinessType(4));
		searchView.setBds(bdService.getAll());
		searchView.setIndustrys(industryService.getAll());
		if (page.getOrderBy()==null){
			page.setOrderBy("p.name asc p.client.name");
			page.setOrder("asc");
		}
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	public SearchView getSearchView() {
		return searchView;
	}

	public void setSearchView(SearchView searchView) {
		this.searchView = searchView;
	}

	public Page<Project> getPage() {
		return page;
	}

	public void setPage(Page<Project> page) {
		this.page = page;
	}

	public ChargePerson getChargePerson() {
		return chargePerson;
	}

	public void setChargePerson(ChargePerson chargePerson) {
		this.chargePerson = chargePerson;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public List<ChargePerson> getChargePersons() {
		return chargePersons;
	}

	public void setChargePersons(List<ChargePerson> chargePersons) {
		this.chargePersons = chargePersons;
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

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Integer getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(Integer businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

	public String getBsType() {
		return bsType;
	}

	public void setBsType(String bsType) {
		this.bsType = bsType;
	}
	
	

}