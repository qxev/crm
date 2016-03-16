package cn.finance.web.action.system;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.finance.model.Account;
import cn.finance.model.Exchange;
import cn.finance.model.ExchangeHistory;
import cn.finance.service.AccountService;
import cn.finance.service.ExchangeService;
import cn.finance.service.calc.CalcOldService;
import cn.finance.util.Struts2Utils;
import cn.finance.web.action.base.BaseAction;
import cn.springside.modules.orm.Page;

public class ExchangeAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ExchangeService exchangeService;

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private CalcOldService calcOldService;
	
	private Page<ExchangeHistory> page = new Page<ExchangeHistory>(30);
	
	private Integer id;
	
	private Integer exchangeId;

	private String year;
	
	private String month;
	
	private String exchangeName;
	
	private String filter;
	
	private Integer pageNo;
	
	private List<Exchange> exchanges;

	// 基本属性
	private ExchangeHistory exchangeHistory;
	
	public String exchangeList() throws Exception{
		Struts2Utils.getRequest().setAttribute("nav", "5");
		Struts2Utils.getRequest().setAttribute("subNav", "7");
		page.setResult(exchangeService.getCurrent());
		page.setTotalCount(page.getResult().size());
		return SUCCESS;
	}
	
	public String oneExchangeList() throws Exception{
		Struts2Utils.getRequest().setAttribute("nav", "5");
		Struts2Utils.getRequest().setAttribute("subNav", "7");
		page.setResult(exchangeService.getExchangeHistoryByExchangeId(exchangeId));
		page.setTotalCount(page.getResult().size());
		if (page.getTotalCount()>0){
			ExchangeHistory temp = page.getResult().get(0);
			exchangeName = temp.getExchange().getCountry();
		}
		return "one";
	}
	
	public String initAdd() throws Exception {
		Struts2Utils.getRequest().setAttribute("nav", "5");
		Struts2Utils.getRequest().setAttribute("subNav", "7");
		exchanges = exchangeService.getAll();
		if (null!=id) {
			exchangeHistory=exchangeService.getExchangeHistoryById(id);
		} else {
			exchangeHistory = new ExchangeHistory();
		}
		return "add";
	}
	
	public String add() throws Exception {
		Struts2Utils.getRequest().setAttribute("nav", "5");
		Struts2Utils.getRequest().setAttribute("subNav", "7");
		if (null!=id) {
			BigDecimal value = exchangeHistory.getValue();
//			Integer countryId = exchangeHistory.getExchange().getId();
//			Exchange exchange = exchangeService.getExchangeById(countryId);
			exchangeHistory=exchangeService.getExchangeHistoryById(id);
			exchangeHistory.setValue(value);
//			exchangeHistory.setExchange(exchange);
//			Calendar calendar = Calendar.getInstance();
//			calendar.set(Calendar.YEAR, Integer.valueOf(year));
//			calendar.set(Calendar.MONTH, Integer.valueOf(month)-1);
//			calendar.set(Calendar.DATE,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
//			exchangeHistory.setExchangeDate(calendar.getTime());
			exchangeHistory.setUpdateAt(new Date());
			Date exchangeDate = exchangeHistory.getExchangeDate();
			Date date = new Date();
			Calendar calendar1 = Calendar.getInstance();
			Calendar calendar2 = Calendar.getInstance();
			calendar1.setTime(exchangeDate);
			calendar2.setTime(date);
			if ((calendar1.get(Calendar.YEAR)==calendar2.get(Calendar.YEAR))&&(calendar1.get(Calendar.MONTH)+1>=calendar2.get(Calendar.MONTH))){
				exchangeService.updateFuture(value, date, exchangeHistory.getExchange().getId());
			}
			List<Account> list = accountService.getAccountByExchangeId(exchangeId);
			for (Account account : list){
				calcOldService.reCalc(account.getId());
			}
			this.addActionMessage("修改汇率成功！");
		}else {
			Integer countryId = exchangeHistory.getExchange().getId();
			Exchange exchange = exchangeService.getExchangeById(countryId);
			exchangeHistory.setExchange(exchange);
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, Integer.valueOf(year));
			calendar.set(Calendar.MONTH, Integer.valueOf(month)-1);
			calendar.set(Calendar.DATE,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			exchangeHistory.setExchangeDate(calendar.getTime());
			exchangeHistory.setCreateAt(new Date());
			exchangeHistory.setDeleted("0");
			this.addActionMessage("新增汇率成功！");
		}
		exchangeService.saveHistory(exchangeHistory);
		return "back";
	}
	
	public String delete() throws Exception {
		Struts2Utils.getRequest().setAttribute("nav", "5");
		Struts2Utils.getRequest().setAttribute("subNav", "7");
		exchangeHistory=exchangeService.getExchangeHistoryById(id);
		exchangeHistory.setDeleted("1");
		exchangeHistory.setUpdateAt(new Date());
		exchangeService.saveHistory(exchangeHistory);
		this.addActionMessage("删除汇率成功！");
		return "back";
	}

	public Page<ExchangeHistory> getPage() {
		return page;
	}

	public void setPage(Page<ExchangeHistory> page) {
		this.page = page;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ExchangeHistory getExchangeHistory() {
		return exchangeHistory;
	}

	public void setExchangeHistory(ExchangeHistory exchangeHistory) {
		this.exchangeHistory = exchangeHistory;
	}

	public List<Exchange> getExchanges() {
		return exchanges;
	}

	public void setExchanges(List<Exchange> exchanges) {
		this.exchanges = exchanges;
	}

	public String getYear() {
		String year = "";
		if (exchangeHistory!=null){
			if (exchangeHistory.getExchangeDate()!=null){
				Calendar temp = Calendar.getInstance();
				temp.setTime(exchangeHistory.getExchangeDate());
				year = String.valueOf(temp.get(Calendar.YEAR));
			} else {
				Calendar temp = Calendar.getInstance();
				temp.setTime(new Date());
				year = String.valueOf(temp.get(Calendar.YEAR));
			}
		}
		return year;
	}
	
	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		String month = "";
		if (exchangeHistory!=null){
			if (exchangeHistory.getExchangeDate()!=null){
				Calendar temp = Calendar.getInstance();
				temp.setTime(exchangeHistory.getExchangeDate());
				month = String.valueOf(temp.get(Calendar.MONTH)+1);
			} else {
				Calendar temp = Calendar.getInstance();
				temp.setTime(new Date());
				month = String.valueOf(temp.get(Calendar.MONTH)+1);
			}
		} 
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
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

	public Integer getExchangeId() {
		return exchangeId;
	}

	public void setExchangeId(Integer exchangeId) {
		this.exchangeId = exchangeId;
	}

	public String getExchangeName() {
		return exchangeName;
	}

	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}
	
	
}