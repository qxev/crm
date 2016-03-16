package cn.finance.web.action.fee;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.finance.model.Account;
import cn.finance.model.Channel;
import cn.finance.model.Expense;
import cn.finance.model.Project;
import cn.finance.model.ServiceFeeAdjust;
import cn.finance.model.bean.ServiceAdjustBean;
import cn.finance.service.AccountService;
import cn.finance.service.ChannelService;
import cn.finance.service.ClientService;
import cn.finance.service.ExpenseService;
import cn.finance.service.FeeAdjustService;
import cn.finance.service.MediaService;
import cn.finance.service.ProjectService;
import cn.finance.service.calc.CalcOldService;
import cn.finance.util.DateUtil;
import cn.finance.util.ExcelUtils;
import cn.finance.util.FileUtils;
import cn.finance.util.Struts2Utils;
import cn.finance.web.action.base.BaseAction;
import cn.finance.web.model.SearchView;
import cn.springside.modules.orm.Page;

public class ServiceAction extends BaseAction {

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
	private FeeAdjustService feeAdjustService;
	
	@Autowired
	private ExpenseService expenseService;
	
	@Autowired
	private CalcOldService calcOldService;

	// 基本属性
	private Channel entity;
	
	private ServiceFeeAdjust serviceFeeAdjust;

	private String startDate;
	
	private String endDate;
	
	private Integer addChannelId;
	
	private Integer addProjectId;

	private Integer addMediaId;
	
	private Integer addChId;
	
	private Integer addClientId;
	
	private SearchView searchView;
	
	private Page<Channel> page = new Page<Channel>(30);
	
	public void excelOutput() throws IOException {
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("file");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String("服务费调整列表.xls".getBytes("GB2312"), "iso8859-1"));
		String path = FileUtils.getProjectUrl() + "template/channelList.xls";
		List<Channel> channels = channelService.getAll(page,true);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("channel", channels);
		short []cols = cn.finance.util.StringUtils.getShouts(page.getColumns());
		ExcelUtils.transformXLS(path, map, cols).write(
				Struts2Utils.getResponse().getOutputStream());
	}
	
	public String serviceList() throws Exception {
		initSearchView();
		Struts2Utils.getRequest().setAttribute("nav", "3");
		Struts2Utils.getRequest().setAttribute("subNav", "8");
		page.setResult(channelService.getAllNo(page,false));
		page.setTotalCount(channelService.getTotalCountNo(page));
		return SUCCESS;
	}
	
	public String serviceAdjust() throws Exception {
		BigDecimal value = serviceFeeAdjust.getValue();
		List <ServiceAdjustBean>list = expenseService.getDaysByCondition(addChannelId, addProjectId, startDate, endDate);
		if (list.size()>0){
			BigDecimal everyDayValue = value.divide(new BigDecimal(list.size()),4,RoundingMode.HALF_UP);
			//每天的操作
			for (ServiceAdjustBean serviceAdjust: list){
				List <Expense>expenses = expenseService.getExpenseByProjectAndId(addChannelId, addProjectId, serviceAdjust.getSumDate());
				//每个账户的操作
				for (Expense expense: expenses){
					BigDecimal serviceFee = expense.getServiceFee();
					BigDecimal addFee = new BigDecimal(0);
					if (serviceAdjust.getSum().compareTo(new BigDecimal(0))==0){
						addFee = everyDayValue.divide(new BigDecimal(expenses.size()),4,RoundingMode.HALF_UP);
					} else {
						addFee =  everyDayValue.divide(serviceAdjust.getSum(),4,RoundingMode.HALF_UP).multiply(serviceFee);
					}
					expense.setServiceFeeAdjust(addFee);
					expenseService.saveTransactional(expense);
				}
			}
			List <Account>accountlist = accountService.getAllByProjectChannelId(addProjectId, addChannelId);
			for (Account account: accountlist){
				calcOldService.reCalc(account.getId());
			}
			Channel channel = channelService.getById(addChannelId);
			Project project = projectService.getProjectById(addProjectId);
			serviceFeeAdjust.setProject(project);
			serviceFeeAdjust.setChannel(channel);
			serviceFeeAdjust.setCreateAt(new Date());
			serviceFeeAdjust.setStartDate(DateUtil.stringToDate(startDate));
			serviceFeeAdjust.setEndDate(DateUtil.stringToDate(endDate));
			feeAdjustService.save(serviceFeeAdjust);
			this.addActionMessage("调整服务费成功！");
		} else {
			this.addActionMessage("调整服务费有误！该时间段没有服务费记录。");
		}
		searchView = new SearchView();
		searchView.setClientId(addClientId);
		searchView.setProjectId(addProjectId);
		searchView.setMediaId(addMediaId);
		searchView.setChannelId(addChId);
		return serviceList();
	}
	
	public void initSearchView(){
		if (searchView == null) {
			searchView = new SearchView();
			if (page.getFilter()!=null) {
				String []pars = StringUtils.split(page.getFilter(),"|");
				if (pars.length==4){
					searchView.setClientId(NumberUtils.toInt(pars[0]));
					searchView.setProjectId(NumberUtils.toInt(pars[1]));
					searchView.setMediaId(NumberUtils.toInt(pars[2]));
					searchView.setChannelId(NumberUtils.toInt(pars[3]));
				}
			}
		} else {
			StringBuffer filter = new StringBuffer();
			filter.append(searchView.getClientId()).append("|").append(searchView.getProjectId()).append("|")
			.append(searchView.getMediaId()).append("|").append(searchView.getChannelId());
			page.setFilter(filter.toString());
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
	

	public ServiceFeeAdjust getServiceFeeAdjust() {
		return serviceFeeAdjust;
	}

	public void setServiceFeeAdjust(ServiceFeeAdjust serviceFeeAdjust) {
		this.serviceFeeAdjust = serviceFeeAdjust;
	}
	
	
	public Integer getAddChannelId() {
		return addChannelId;
	}

	public void setAddChannelId(Integer addChannelId) {
		this.addChannelId = addChannelId;
	}

	public Integer getAddProjectId() {
		return addProjectId;
	}

	public void setAddProjectId(Integer addProjectId) {
		this.addProjectId = addProjectId;
	}

	public Integer getAddMediaId() {
		return addMediaId;
	}

	public void setAddMediaId(Integer addMediaId) {
		this.addMediaId = addMediaId;
	}

	public Integer getAddChId() {
		return addChId;
	}

	public void setAddChId(Integer addChId) {
		this.addChId = addChId;
	}

	public Integer getAddClientId() {
		return addClientId;
	}

	public void setAddClientId(Integer addClientId) {
		this.addClientId = addClientId;
	}

}