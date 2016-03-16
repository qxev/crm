package cn.finance.web.model;

import java.util.ArrayList;
import java.util.List;

import cn.finance.model.Bd;
import cn.finance.model.BusinessType;
import cn.finance.model.Channel;
import cn.finance.model.Client;
import cn.finance.model.ContractClient;
import cn.finance.model.ContractProject;
import cn.finance.model.DarwinName;
import cn.finance.model.Industry;
import cn.finance.model.Media;
import cn.finance.model.Pm;
import cn.finance.model.Project;

public class SearchView {

	private String startDate = "";

	private String endDate = "";

	private Integer accountId = 0;

	private List <Client> clients;

	private List <ContractClient> contractClients;

	private Integer clientId = 0;

	private List <Project> projects;

	private List <ContractProject> contractProjects;

	private Integer projectId = 0;
	
	private Integer pId = 0;

	private List <Media> medias;

	private Integer mediaId = 0;

	private Integer mId = 0;
	
	private List <Channel> channels;

	private Integer channelId = 0;

	private List <Pm> pms;

	private Integer pmId = 0;
	
	private String pmIds;
	
	private List <Bd> bds;

	private Integer bdId = 0;

	private List <DarwinName> darwinNames;

	private Integer darwinNameId = 0;

	private List <BusinessType> businessTypes;

	private Integer businessTypeId = 0;

	private String bdIds;
	
	private List <SelectboxView> sources;

	private Integer sourceId = 0;
	
	private List <Industry> industrys;
	
	private Integer industryId = 0;
	
	private List <SelectboxView> statuses;

	private Integer statusId = 0;
	
	private Integer areaId = -1;
	
	/**
	 * 各业务部门lead
	 */
	private Integer leadId = 0;
	
	private List <SelectboxView> clientTypes;

	private Integer clientTypeId = 0;
	
	private String repeat = "true";
	
	private String accountName;

	private String clientName = "";

	private String projectName = "";
	
	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getPmId() {
		return pmId;
	}

	public void setPmId(Integer pmId) {
		this.pmId = pmId;
	}

	public Integer getBdId() {
		return bdId;
	}

	public void setBdId(Integer bdId) {
		this.bdId = bdId;
	}

	public Integer getSourceId() {
		return sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	public Integer getIndustryId() {
		return industryId;
	}

	public void setIndustryId(Integer industryId) {
		this.industryId = industryId;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public SearchView(){}
	
	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public List<Project> getProjects() {
		if (projects == null){
			return new ArrayList<Project>(); 
		} else {
		return projects;
		}
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public List<Pm> getPms() {
		return pms;
	}

	public void setPms(List<Pm> pms) {
		this.pms = pms;
	}

	public List<Bd> getBds() {
		return bds;
	}

	public void setBds(List<Bd> bds) {
		this.bds = bds;
	}

	public List<SelectboxView> getSources() {
		if (sources==null){
			sources = new ArrayList<SelectboxView>();
			sources.add(new SelectboxView("1","直接客户"));
			sources.add(new SelectboxView("2","代理客户"));
		}
		return sources;
	}

	public void setSources(List<SelectboxView> sources) {
		this.sources = sources;
	}

	public List<Industry> getIndustrys() {
		return industrys;
	}

	public void setIndustrys(List<Industry> industrys) {
		this.industrys = industrys;
	}

	public List<SelectboxView> getStatuses() {
		if (statuses==null){
			statuses = new ArrayList<SelectboxView>();
			statuses.add(new SelectboxView("1","有效"));
			statuses.add(new SelectboxView("2","无效"));
		}
		return statuses;
	}

	public void setStatuses(List<SelectboxView> statuses) {
		this.statuses = statuses;
	}

	public List<Media> getMedias() {
		if (medias == null){
			return new ArrayList<Media>(); 
		} else {
		return medias;
		}
	}

	public void setMedias(List<Media> medias) {
		this.medias = medias;
	}

	public Integer getMediaId() {
		return mediaId;
	}

	public void setMediaId(Integer mediaId) {
		this.mediaId = mediaId;
	}

	public List<Channel> getChannels() {
		if (channels == null){
			return new ArrayList<Channel>(); 
		} else {
		return channels;
		}
	}

	public void setChannels(List<Channel> channels) {
		this.channels = channels;
	}

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}
	
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getPmIds() {
		return pmIds;
	}

	public void setPmIds(String pmIds) {
		this.pmIds = pmIds;
	}

	public String getBdIds() {
		return bdIds;
	}

	public void setBdIds(String bdIds) {
		this.bdIds = bdIds;
	}

	public List<SelectboxView> getClientTypes() {
		if (clientTypes==null){
			clientTypes = new ArrayList<SelectboxView>();
			clientTypes.add(new SelectboxView("1","直接客户"));
			clientTypes.add(new SelectboxView("2","代理客户"));
		}
		return clientTypes;
	}

	public void setClientTypes(List<SelectboxView> clientTypes) {
		this.clientTypes = clientTypes;
	}

	public Integer getClientTypeId() {
		return clientTypeId;
	}

	public void setClientTypeId(Integer clientTypeId) {
		this.clientTypeId = clientTypeId;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getRepeat() {
		return repeat;
	}

	public void setRepeat(String repeat) {
		this.repeat = repeat;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public Integer getmId() {
		return mId;
	}

	public void setmId(Integer mId) {
		this.mId = mId;
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

	public List<BusinessType> getBusinessTypes() {
		return businessTypes;
	}

	public void setBusinessTypes(List<BusinessType> businessTypes) {
		this.businessTypes = businessTypes;
	}

	public Integer getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(Integer businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

	public List<ContractClient> getContractClients() {
		return contractClients;
	}

	public void setContractClients(List<ContractClient> contractClients) {
		this.contractClients = contractClients;
	}

	public List<DarwinName> getDarwinNames() {
		return darwinNames;
	}

	public void setDarwinNames(List<DarwinName> darwinNames) {
		this.darwinNames = darwinNames;
	}

	public Integer getDarwinNameId() {
		return darwinNameId;
	}

	public void setDarwinNameId(Integer darwinNameId) {
		this.darwinNameId = darwinNameId;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public Integer getLeadId() {
		return leadId;
	}

	public void setLeadId(Integer leadId) {
		this.leadId = leadId;
	}

	public List<ContractProject> getContractProjects() {
		return contractProjects;
	}

	public void setContractProjects(List<ContractProject> contractProjects) {
		this.contractProjects = contractProjects;
	}
	
}