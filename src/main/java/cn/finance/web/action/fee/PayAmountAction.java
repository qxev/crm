package cn.finance.web.action.fee;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.finance.model.SupplementHistory;
import cn.finance.service.ChannelService;
import cn.finance.service.ClientService;
import cn.finance.service.MediaService;
import cn.finance.service.ProjectService;
import cn.finance.service.SupplementHistoryService;
import cn.finance.util.DateUtil;
import cn.finance.util.ExcelUtils;
import cn.finance.util.FileUtils;
import cn.finance.util.Struts2Utils;
import cn.finance.web.action.base.BaseAction;
import cn.finance.web.model.SearchView;
import cn.springside.modules.orm.Page;

public class PayAmountAction extends BaseAction {

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
	private SupplementHistoryService supplementHistoryService;
	
	// 基本属性
	private SupplementHistory supplementHistory;
	
	private BigDecimal sum;

	private SearchView searchView;
	
	private Page<SupplementHistory> page = new Page<SupplementHistory>(30);
	
	public void excelOutput() throws IOException {
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("file");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String("达闻付款历史.xls".getBytes("GB2312"), "iso8859-1"));
		String path = FileUtils.getProjectUrl() + "template/payamountList.xls";
		List<SupplementHistory> supplementHistorys = supplementHistoryService.getAllPay(page,true);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("supplementHistory", supplementHistorys);
		short []cols = cn.finance.util.StringUtils.getShouts(page.getColumns());
		ExcelUtils.transformXLS(path, map, cols).write(
				Struts2Utils.getResponse().getOutputStream());
	}
	
	public String payAmountList() throws Exception {
		initSearchView();
		Struts2Utils.getRequest().setAttribute("nav", "4");
		Struts2Utils.getRequest().setAttribute("subNav", "4");
		page.setResult(supplementHistoryService.getAllPay(page,false));
		page.setTotalCount(supplementHistoryService.getTotalPayCount(page));
		sum=supplementHistoryService.getSumPay(page);

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
					searchView.setChannelId(NumberUtils.toInt(pars[3]));
					searchView.setStartDate("0".equals(pars[4])?"":pars[4]);
					searchView.setEndDate("0".equals(pars[5])?"":pars[5]);
				} else {
					Calendar startDate = Calendar.getInstance();      
					startDate.set(Calendar.DAY_OF_MONTH, startDate.getActualMinimum(Calendar.DAY_OF_MONTH)); 
					searchView.setStartDate(DateUtil.dateToString(startDate.getTime()));
					Calendar endDate = Calendar.getInstance();      
					searchView.setEndDate(DateUtil.dateToString(endDate.getTime()));
					page.setFilter("0|0|0|0|"+searchView.getStartDate()+"|"+searchView.getEndDate());
				}
			}
		} else {
			StringBuffer filter = new StringBuffer();
			filter.append(searchView.getClientId()).append("|").append(searchView.getProjectId()).append("|")
			.append(searchView.getMediaId()).append("|").append(searchView.getChannelId()).append("|")
			.append("".equals(searchView.getStartDate())?"0":searchView.getStartDate()).append("|")
			.append("".equals(searchView.getEndDate())?"0":searchView.getEndDate());
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
		if (page.getOrderBy()==null){
			page.setOrderBy("s.account.project.name asc s.account.project.client.name asc s.account.name");
			page.setOrder("asc");
		}
	}
	
	public Page<SupplementHistory> getPage() {
		return page;
	}

	public void setPage(Page<SupplementHistory> page) {
		this.page = page;
	}

	public SupplementHistory getSupplementHistory() {
		return supplementHistory;
	}

	public void setSupplementHistory(SupplementHistory supplementHistory) {
		this.supplementHistory = supplementHistory;
	}

	public SearchView getSearchView() {
		return searchView;
	}

	public void setSearchView(SearchView searchView) {
		this.searchView = searchView;
	}
	
	public BigDecimal getSum() {
		return sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}
}