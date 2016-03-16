package cn.finance.web.action.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.finance.model.Bd;
import cn.finance.model.BusinessType;
import cn.finance.model.ChargePerson;
import cn.finance.model.ContractClient;
import cn.finance.model.ContractProject;
import cn.finance.model.DarwinName;
import cn.finance.model.Industry;
import cn.finance.model.Pm;
import cn.finance.service.BdService;
import cn.finance.service.BusinessTypeService;
import cn.finance.service.ContractClientService;
import cn.finance.service.ContractProjectService;
import cn.finance.service.IndustryService;
import cn.finance.service.PmService;
import cn.finance.util.ExcelUtils;
import cn.finance.util.FileUtils;
import cn.finance.util.Struts2Utils;
import cn.finance.web.action.base.BaseAction;
import cn.finance.web.model.SearchView;
import cn.springside.modules.orm.Page;

public class ContractProjectAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ContractProjectService contractProjectService;

	@Autowired
	private PmService pmService;

	@Autowired
	private BdService bdService;

	@Autowired
	private BusinessTypeService businessTypeService;

	@Autowired
	private IndustryService industryService;

	@Autowired
	private ContractClientService contractClientService;

	private SearchView searchView;

	private Page<ContractProject> page = new Page<ContractProject>();

	private Integer contractProjectId;

	private Integer contractClientId;

	private Integer businessTypeId;

	private String bsType;

	private ContractProject contractProject;

	private ChargePerson chargePerson;

	private String contractClientName;

	private String filter;

	private Integer pageNo;

	private List<ChargePerson> chargePersons = new ArrayList<ChargePerson>();

	public String initContractProject() {
		Struts2Utils.getRequest().setAttribute("nav", "6");
		if (searchView == null) {
			searchView = new SearchView();
		}
		searchView.setBds(bdService.getAll());
		searchView.setBusinessTypes(businessTypeService.getAll());
		searchView.setIndustrys(industryService.getAll());
		searchView.setDarwinNames(contractProjectService.getAllDarwinName());
		if (contractProjectId != null) {
			contractProject = contractProjectService.getContractProjectById(contractProjectId);
			searchView.setPms(pmService.getByBusinessType(contractProject.getBusinessType().getId()));
			Struts2Utils.getRequest().setAttribute("subNav", contractProject.getBusinessType().getId() - 1);
			contractClientName = contractProject.getContractClient().getName();
		} else {
			contractProject = contractProjectService.createContractProject();
			ContractClient contractClient = contractClientService.getContractClientById(contractClientId);
			contractClientName = contractClient.getName();
			searchView.setPms(pmService.getByBusinessType(4));
			Struts2Utils.getRequest().setAttribute("subNav", "2");
		}
		// chargePersons =
		// chargePersonService.getChargePersonByContractProjectId(contractProjectId);
		return EDIT;
	}

	public String editContractProject() throws UnsupportedEncodingException {
		ContractProject editContractProject = new ContractProject();
		if (contractProjectId == null) {
			editContractProject = contractProjectService.createContractProject();
			ContractClient contractClient = contractClientService.getContractClientById(contractClientId);
			editContractProject.setContractClient(contractClient);
		} else {
			editContractProject = contractProjectService.getContractProjectById(contractProjectId);
		}
		editContractProject.setName(searchView.getProjectName());
		Industry industry = industryService.getIndustryById(searchView.getIndustryId());
		searchView.setIndustryId(0);
		editContractProject.setIndustry(industry);
		Pm pm = pmService.getPmById(searchView.getPmId());
		editContractProject.setPm(pm);
		Bd bd = bdService.getBdById(searchView.getBdId());
		editContractProject.setBd(bd);
		DarwinName darwinName = contractProjectService.getDarwinNameById(searchView.getDarwinNameId());
		editContractProject.setDarwinName(darwinName);
		BusinessType businessType = businessTypeService.getBusinessTypeById(searchView.getBusinessTypeId());
		editContractProject.setBusinessType(businessType);
		contractProjectService.save(editContractProject);
		if (contractProjectId == null) {
			this.addActionMessage("新建项目成功!");
		} else {
			this.addActionMessage("修改项目成功!");
		}
		searchView = null;
		businessTypeId = businessType.getId();
		if (businessTypeId > 4) {
			return otherContractProjectList();
		} else {
			return contractProjectList();
		}
	}

	public void excelOutput() throws IOException {
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("file");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String("项目列表.xls".getBytes("GB2312"), "iso8859-1"));
		String path = FileUtils.getProjectUrl();
		path = path + "template/contractProjectList.xls";
		List<ContractProject> contractProjects = contractProjectService.getAll(page, true);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("contractProject", contractProjects);
		short[] cols = cn.finance.util.StringUtils.getShouts(page.getColumns());
		ExcelUtils.transformXLS(path, map, cols).write(Struts2Utils.getResponse().getOutputStream());
	}

	public String contractProjectList() throws UnsupportedEncodingException {
		initSearchView(4);
		Struts2Utils.getRequest().setAttribute("nav", "6");
		Struts2Utils.getRequest().setAttribute("subNav", "3");
		page.setResult(contractProjectService.getAll(page, false));
		page.setTotalCount(contractProjectService.getTotalCount(page));
		return SUCCESS;
	}

	public String otherContractProjectList() throws UnsupportedEncodingException {
		if (businessTypeId != null) {
			initSearchView(businessTypeId);
			Struts2Utils.getRequest().setAttribute("nav", "6");
			if (businessTypeId == 5) {
				Struts2Utils.getRequest().setAttribute("subNav", "4");
			} else if (businessTypeId == 6) {
				Struts2Utils.getRequest().setAttribute("subNav", "5");
			} else if (businessTypeId == 7) {
				Struts2Utils.getRequest().setAttribute("subNav", "6");
			} else if (businessTypeId == 13) {
				Struts2Utils.getRequest().setAttribute("subNav", "7");
			}
			BusinessType businessTypetemp = businessTypeService.getBusinessTypeById(businessTypeId);
			bsType = businessTypetemp.getName();
			page.setResult(contractProjectService.getAll(page, false));
			page.setTotalCount(contractProjectService.getTotalCount(page));
		}
		return "other";
	}

	public void initSearchView(Integer buisnessTypeId) throws UnsupportedEncodingException {
		if (searchView == null) {
			searchView = new SearchView();
			if (page.getFilter() != null) {
				String[] pars = StringUtils.split(page.getFilter(), "|");
				if (pars.length == 9) {
					searchView.setClientId(NumberUtils.toInt(pars[0]));
					searchView.setPmId(NumberUtils.toInt(pars[1]));
					searchView.setBdId(NumberUtils.toInt(pars[2]));
					searchView.setSourceId(NumberUtils.toInt(pars[3]));
					searchView.setIndustryId(NumberUtils.toInt(pars[4]));
					searchView.setStatusId(NumberUtils.toInt(pars[5]));
					searchView.setClientName("0".equals(pars[6]) ? "" : pars[6]);
					searchView.setProjectName("0".equals(pars[7]) ? "" : pars[7]);
					searchView.setBusinessTypeId(buisnessTypeId);
				} else {
					page.setFilter("0|0|0|0|0|0|0|0|" + buisnessTypeId);
				}
			}
		} else {
			StringBuffer filter = new StringBuffer();
			filter.append(searchView.getClientId())
					.append("|")
					.append(searchView.getPmId())
					.append("|")
					.append(searchView.getBdId())
					.append("|")
					.append(searchView.getSourceId())
					.append("|")
					.append(searchView.getIndustryId())
					.append("|")
					.append(searchView.getStatusId())
					.append("|")
					.append(searchView.getClientName() == null || "".equals(searchView.getClientName()) ? "0"
							: searchView.getClientName())
					.append("|")
					.append(searchView.getProjectName() == null || "".equals(searchView.getProjectName()) ? "0"
							: searchView.getProjectName()).append("|").append(buisnessTypeId);
			page.setFilter(filter.toString());
		}
		if (searchView.getClientId() != null && searchView.getClientId() != 0) {
			ContractClient contractClient = contractClientService.getContractClientById(searchView.getClientId());
			if (contractClient != null)
				contractClientName = contractClient.getName();
		}
		searchView.setContractClients(contractClientService.getAll());
		searchView.setPms(pmService.getByBusinessType(buisnessTypeId));
		searchView.setBds(bdService.getAll());
		searchView.setIndustrys(industryService.getAll());
		if (page.getOrderBy() == null) {
			page.setOrderBy("p.createAt");
			page.setOrder("desc");
		}
	}

	public Integer getContractProjectId() {
		return contractProjectId;
	}

	public void setContractProjectId(Integer contractProjectId) {
		this.contractProjectId = contractProjectId;
	}

	public ContractProject getContractProject() {
		return contractProject;
	}

	public void setContractProject(ContractProject contractProject) {
		this.contractProject = contractProject;
	}

	public SearchView getSearchView() {
		return searchView;
	}

	public void setSearchView(SearchView searchView) {
		this.searchView = searchView;
	}

	public Page<ContractProject> getPage() {
		return page;
	}

	public void setPage(Page<ContractProject> page) {
		this.page = page;
	}

	public ChargePerson getChargePerson() {
		return chargePerson;
	}

	public void setChargePerson(ChargePerson chargePerson) {
		this.chargePerson = chargePerson;
	}

	public String getContractClientName() {
		return contractClientName;
	}

	public void setContractClientName(String contractClientName) {
		this.contractClientName = contractClientName;
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

	public Integer getContractClientId() {
		return contractClientId;
	}

	public void setContractClientId(Integer contractClientId) {
		this.contractClientId = contractClientId;
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