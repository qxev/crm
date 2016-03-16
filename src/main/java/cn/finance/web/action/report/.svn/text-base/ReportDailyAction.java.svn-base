package cn.finance.web.action.report;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.finance.model.Account;
import cn.finance.model.bean.ReportBean;
import cn.finance.service.AccountService;
import cn.finance.service.ExpenseService;
import cn.finance.util.ExcelUtils;
import cn.finance.util.FileUtils;
import cn.finance.util.Struts2Utils;
import cn.finance.web.action.base.BaseAction;
import cn.finance.web.model.SearchView;
import cn.springside.modules.orm.Page;

public class ReportDailyAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ExpenseService expenseService;
	
	@Autowired
	private AccountService accountService;
	
	private SearchView searchView;
	
	private Page<ReportBean> page = new Page<ReportBean>(31);
	
	private ReportBean sumBean; 
	
	private String accountName;
	
	private Integer exchangeId;
	
	public void excelOutput() throws IOException {
		String fileName = "账户每日报表.xls";
		String fileMergeName = "template/reportDailyList.xls";
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("file");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String(fileName.getBytes("GB2312"), "iso8859-1"));
		String path = FileUtils.getProjectUrl() + fileMergeName;
		List<ReportBean> reportBeans = expenseService.getReportByAccountId(page,true);
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
		sumBean = expenseService.getSumReportByAccount(page);
		page.setResult(expenseService.getReportByAccountId(page,false));
		page.setTotalCount(expenseService.getReportCountByAccountId(page));
		Account account = accountService.getAccountById(searchView.getAccountId());
		accountName = account.getName();
		exchangeId = account.getExchange().getId();
		return SUCCESS;
	}
	
	public void initSearchView(){
		if (searchView == null) {
			searchView = new SearchView();
		} else {
			StringBuffer filter = new StringBuffer();
			filter.append("".equals(searchView.getStartDate())?"0":searchView.getStartDate()).append("|")
			.append("".equals(searchView.getEndDate())?"0":searchView.getEndDate()).append("|")
			.append("".equals(searchView.getAccountId())?"0":searchView.getAccountId());
			page.setFilter(filter.toString());
		}
		if (page.getFilter()!=null) {
			String []pars = StringUtils.split(page.getFilter(),"|");
			if (pars.length==3){
				searchView.setStartDate("0".equals(pars[0])?"":pars[0]);
				searchView.setEndDate("0".equals(pars[1])?"":pars[1]);
				searchView.setAccountId(Integer.valueOf(pars[2]));
			}
		}
		if (page.getOrderBy()==null){
			page.setOrderBy("e.expenseDate");
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

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Integer getExchangeId() {
		return exchangeId;
	}

	public void setExchangeId(Integer exchangeId) {
		this.exchangeId = exchangeId;
	}
	
	
}