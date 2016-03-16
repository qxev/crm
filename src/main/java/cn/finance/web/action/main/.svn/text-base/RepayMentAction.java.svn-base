package cn.finance.web.action.main;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import cn.finance.model.SupplementHistory;
import cn.finance.service.SupplementHistoryService;
import cn.finance.util.ExcelUtils;
import cn.finance.util.FileUtils;
import cn.finance.util.StringUtils;
import cn.finance.util.Struts2Utils;
import cn.finance.web.action.base.BaseAction;
import cn.finance.web.model.SearchView;
import cn.springside.modules.orm.Page;

public class RepayMentAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private SupplementHistoryService supplementHistoryService;
	
	private SearchView searchView;

	private Integer supplementHistoryId;
	
	private Integer accountId;
	
	private String supplementDate;
	
	private BigDecimal sum;

	/**
	 * 本月
	 */
	private BigDecimal sumthisMonth;

	/**
	 * 下1月
	 */
	private BigDecimal sumNextMonth1;

	/**
	 * 下2月
	 */
	private BigDecimal sumthisMonth2;

	/**
	 * 下3月
	 */
	private BigDecimal sumthisMonth3;

	SupplementHistory supplementHistory = new SupplementHistory();

	private Page<SupplementHistory> page = new Page<SupplementHistory>(30);

	public String repaymentList() {
		initSearchView();
		Struts2Utils.getRequest().setAttribute("nav", "1");
		Struts2Utils.getRequest().setAttribute("subNav", "2");
		page.setResult(supplementHistoryService.getAllRepayment(page,false));
		page.setTotalCount(supplementHistoryService.getAllRepaymentCount(page));
		sum = supplementHistoryService.getSumRepayment(page);
		if (sumthisMonth==null){
			sumthisMonth = supplementHistoryService.getThisMonthSumRepayment();
			sumNextMonth1 = supplementHistoryService.getNextMonthSumRepayment();
			sumthisMonth2 = supplementHistoryService.getNextMonth2SumRepayment();
			sumthisMonth3 = supplementHistoryService.getNextMonth3SumRepayment();
		}
		return SUCCESS;
	}
	
	public void initSearchView(){
		if (searchView == null) {
			searchView = new SearchView();
		} else {
			StringBuffer filter = new StringBuffer();
			filter.append("".equals(searchView.getStartDate())?"0":searchView.getStartDate()).append("|")
			.append("".equals(searchView.getEndDate())?"0":searchView.getEndDate());
			page.setFilter(filter.toString());
		}
		if (page.getFilter()!=null) {
			String []pars = org.apache.commons.lang.StringUtils.split(page.getFilter(),"|");
			if (pars.length==2){
				searchView.setStartDate("0".equals(pars[0])?"":pars[0]);
				searchView.setEndDate("0".equals(pars[1])?"":pars[1]);
			}
		}
		if (page.getOrderBy()==null){
			page.setOrderBy("s.nextSupplementDate");
			page.setOrder("asc");
		}
	}
	
	public String addRepayment() {
		supplementHistoryService.addRepay(supplementHistoryId, accountId, supplementDate, supplementHistory);
		this.addActionMessage("还款成功");
		return repaymentList();
	}

	public void excelOutput() throws IOException {
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("file");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String("还款记录列表.xls".getBytes("GB2312"), "iso8859-1"));
		String path = FileUtils.getProjectUrl();
		path = path + "template/supplementHistoryList.xls";
		List<SupplementHistory> supplementHistorys = supplementHistoryService.getAllRepayment(page,true);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("supplementHistory", supplementHistorys);
		short []cols = StringUtils.getShouts(page.getColumns());
		ExcelUtils.transformXLS(path, map, cols).write(
				Struts2Utils.getResponse().getOutputStream());
	}
	
	public Page<SupplementHistory> getPage() {
		return page;
	}

	public void setPage(Page<SupplementHistory> page) {
		this.page = page;
	}
	
	public Integer getSupplementHistoryId() {
		return supplementHistoryId;
	}

	public void setSupplementHistoryId(Integer supplementHistoryId) {
		this.supplementHistoryId = supplementHistoryId;
	}
	
	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	
	public SupplementHistory getSupplementHistory() {
		return supplementHistory;
	}

	public void setSupplyHistory(SupplementHistory supplementHistory) {
		this.supplementHistory = supplementHistory;
	}
	
	public String getSupplementDate() {
		return supplementDate;
	}

	public void setSupplementDate(String supplementDate) {
		this.supplementDate = supplementDate;
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

	public BigDecimal getSumthisMonth() {
		return sumthisMonth;
	}

	public void setSumthisMonth(String sumthisMonth) {
		if (sumthisMonth==null){
			this.sumthisMonth = new BigDecimal(0);
		} else {
			this.sumthisMonth = new BigDecimal(sumthisMonth);
		}
	}

	public BigDecimal getSumNextMonth1() {
		return sumNextMonth1;
	}

	public void setSumNextMonth1(String sumNextMonth1) {
		if (sumNextMonth1==null){
			this.sumNextMonth1 = new BigDecimal(0);
		} else {
			this.sumNextMonth1 = new BigDecimal(sumNextMonth1);
		}
	}

	public BigDecimal getSumthisMonth2() {
		return sumthisMonth2;
	}

	public void setSumthisMonth2(String sumthisMonth2) {
		if (sumthisMonth2==null){
			this.sumthisMonth2 = new BigDecimal(0);
		} else {
			this.sumthisMonth2 = new BigDecimal(sumthisMonth2);
		}
	}

	public BigDecimal getSumthisMonth3() {
		return sumthisMonth3;
	}

	public void setSumthisMonth3(String sumthisMonth3) {
		if (sumthisMonth3==null){
			this.sumthisMonth3 = new BigDecimal(0);
		} else {
			this.sumthisMonth3 = new BigDecimal(sumthisMonth3);
		}
	}
}