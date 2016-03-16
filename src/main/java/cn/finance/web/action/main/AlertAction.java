package cn.finance.web.action.main;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import cn.finance.model.Account;
import cn.finance.service.AccountService;
import cn.finance.service.calc.CalcAmountService;
import cn.finance.util.ExcelUtils;
import cn.finance.util.FileUtils;
import cn.finance.util.StringUtils;
import cn.finance.util.Struts2Utils;
import cn.finance.web.action.base.BaseAction;
import cn.springside.modules.orm.Page;

public class AlertAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private CalcAmountService calcAmountService;
	
	private Page<Account> page = new Page<Account>(30);
	
	public String alertList() throws Exception {
		Struts2Utils.getRequest().setAttribute("nav", "1");
		Struts2Utils.getRequest().setAttribute("subNav", "1");
		if (page.getOrderBy()==null){
			page.setOrderBy("remainDays");
			page.setOrder("asc");
		}
		page.setResult(accountService.getAlertList(page,false));
		page.setTotalCount(accountService.getAlertListCount(page));
		return SUCCESS;
	}
	
	public String alertReset() throws Exception {
		calcAmountService.start();
		Struts2Utils.getRequest().setAttribute("nav", "1");
		Struts2Utils.getRequest().setAttribute("subNav", "1");
		if (page.getOrderBy()==null){
			page.setOrderBy("remainDays");
			page.setOrder("asc");
		}
		page.setResult(accountService.getAlertList(page,false));
		page.setTotalCount(accountService.getAlertListCount(page));
		return SUCCESS;
	}
	
	public void excelOutput() throws IOException {
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("file");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String("帐户充值预警列表.xls".getBytes("GB2312"), "iso8859-1"));
		String path = FileUtils.getProjectUrl();
		path = path + "template/alertList.xls";
		List<Account> accounts = accountService.getAlertList(page,true);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("account", accounts);
		short []cols = StringUtils.getShouts(page.getColumns());
		ExcelUtils.transformXLS(path, map, cols).write(
				Struts2Utils.getResponse().getOutputStream());
	}
	
	public Page<Account> getPage() {
		return page;
	}

	public void setPage(Page<Account> page) {
		this.page = page;
	}
}