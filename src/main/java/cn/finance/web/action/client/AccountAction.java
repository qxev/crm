package cn.finance.web.action.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.finance.model.Account;
import cn.finance.model.Channel;
import cn.finance.model.Client;
import cn.finance.model.Media;
import cn.finance.model.Project;
import cn.finance.service.AccountService;
import cn.finance.service.ChannelService;
import cn.finance.service.ClientService;
import cn.finance.service.MediaService;
import cn.finance.service.PmService;
import cn.finance.service.ProjectService;
import cn.finance.util.ExcelUtils;
import cn.finance.util.FileUtils;
import cn.finance.util.Struts2Utils;
import cn.finance.web.action.base.BaseAction;
import cn.finance.web.model.SearchView;
import cn.springside.modules.orm.Page;

public class AccountAction extends BaseAction {

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
	private AccountService accountService;
	
	@Autowired
	private PmService pmService;
	
	// 基本属性
	private Account entity;
	
	private SearchView searchView;
	
	private BigDecimal sumTotalBalance;

	private BigDecimal sumDailyBudget;
	
	private String clientName;
	
	private String projectName;
	
	private String mediaName;
	
	private String channelName;
	
	private Page<Account> page = new Page<Account>(30);
	
	public void excelOutput() throws IOException {
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("file");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String("账户列表.xls".getBytes("GB2312"), "iso8859-1"));
		String path = FileUtils.getProjectUrl() + "template/accountList.xls";
		List<Account> accounts = accountService.getAll(page,true);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("account", accounts);
		short []cols = cn.finance.util.StringUtils.getShouts(page.getColumns());
		ExcelUtils.transformXLS(path, map, cols).write(
				Struts2Utils.getResponse().getOutputStream());
	}
	
	public String accountList() throws Exception {
		initSearchView();
		Struts2Utils.getRequest().setAttribute("nav", "2");
		Struts2Utils.getRequest().setAttribute("subNav", "5");
		page.setResult(accountService.getAll(page,false));
		page.setTotalCount(accountService.getTotalCount(page));
		sumTotalBalance = accountService.getTotalBalance(page);
		sumDailyBudget = accountService.getTotalDailyBudget(page);
		return SUCCESS;
	}

	public String add() {
		Client client = clientService.findById(searchView.getClientId());
		Project project = projectService.getProjectById(searchView.getProjectId());
		Media media = mediaService.getMediaById(searchView.getMediaId());
		Channel channel = channelService.getById(searchView.getChannelId());
		Account account = accountService.createAccount();
		project.setClient(client);
		channel.setMedia(media);
		account.setProject(project);
		account.setChannel(channel);
		account.setName(searchView.getAccountName());
		accountService.save(account);
		this.addActionMessage("新增账户成功");
		return addInit();
	}
	
	public String addInit(){
		Struts2Utils.getRequest().setAttribute("nav", "2");
		Struts2Utils.getRequest().setAttribute("subNav", "6");
		if (searchView == null) {
			searchView = new SearchView();
		}
		searchView.setClients(clientService.getAll());
		if (searchView.getClientId()!=null && searchView.getClientId()!=0){
			searchView.setProjects(projectService.getAllByClientId(searchView.getClientId()));
		}
		searchView.setMedias(mediaService.getAll());
		if (searchView.getMediaId()!=null && searchView.getMediaId()!=0){
			searchView.setChannels(channelService.getAllByMediaId(searchView.getMediaId()));
		}
		return "init_add";
	}
	
	public void initSearchView() throws UnsupportedEncodingException{
		if (searchView == null) {
			searchView = new SearchView();
			if (page.getFilter()!=null) {
				String []pars = StringUtils.split(page.getFilter(),"|");
				if (pars.length==8){
					searchView.setClientId(NumberUtils.toInt(pars[0]));
					searchView.setProjectId(NumberUtils.toInt(pars[1]));
					searchView.setMediaId(NumberUtils.toInt(pars[2]));
					searchView.setChannelId(NumberUtils.toInt(pars[3]));
					searchView.setPmId(NumberUtils.toInt(pars[4]));
					searchView.setStatusId(NumberUtils.toInt(pars[5]));
					String client = new String(pars[6]);
					String project = new String(pars[7]);
					searchView.setClientName("0".equals(client)?"":client);
					searchView.setProjectName("0".equals(project)?"":project);
				}
			}
		} else {
			StringBuffer filter = new StringBuffer();
			filter.append(searchView.getClientId()).append("|").append(searchView.getProjectId()).append("|")
			.append(searchView.getMediaId()).append("|").append(searchView.getChannelId()).append("|")
			.append(searchView.getPmId()).append("|").append(searchView.getStatusId()).append("|")
			.append(searchView.getClientName()==null||"".equals(searchView.getClientName())?"0":searchView.getClientName()).append("|")
			.append(searchView.getProjectName()==null||"".equals(searchView.getProjectName())?"0":searchView.getProjectName());
			page.setFilter(filter.toString());
			if (searchView.getProjectId()!=null&&searchView.getProjectId()!=0){
				Project project = projectService.getProjectById(searchView.getProjectId());
				if (project!=null){
					projectName = project.getName();
					clientName = project.getClient().getName();
					searchView.setClientId(project.getClient().getId());
				}
			}
			if (searchView.getChannelId()!=null&&searchView.getChannelId()!=0){
				Channel channel = channelService.getById(searchView.getChannelId());
				if (channel!=null){
					channelName = channel.getName();
					mediaName = channel.getMedia().getName();
					searchView.setMediaId(channel.getMedia().getId());
				}
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
		searchView.setPms(pmService.getAll());
		if (page.getOrderBy()==null){
			page.setOrderBy("a.project.name asc a.project.client.name asc name");
			page.setOrder("asc");
		}
		
	}
	
	public Page<Account> getPage() {
		return page;
	}

	public void setPage(Page<Account> page) {
		this.page = page;
	}

	public Account getEntity() {
		return entity;
	}

	public void setEntity(Account entity) {
		this.entity = entity;
	}

	public SearchView getSearchView() {
		return searchView;
	}

	public void setSearchView(SearchView searchView) {
		this.searchView = searchView;
	}
	
	public BigDecimal getSumTotalBalance() {
		return sumTotalBalance;
	}

	public void setSumTotalBalance(BigDecimal sumTotalBalance) {
		this.sumTotalBalance = sumTotalBalance;
	}
	
	public BigDecimal getSumDailyBudget() {
		return sumDailyBudget;
	}

	public void setSumDailyBudget(BigDecimal sumDailyBudget) {
		this.sumDailyBudget = sumDailyBudget;
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

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
}