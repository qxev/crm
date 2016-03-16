package cn.finance.web.action.report;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.finance.model.bean.CheckBean;
import cn.finance.service.ExpenseService;
import cn.finance.util.Struts2Utils;
import cn.finance.web.action.base.BaseAction;

public class CheckAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ExpenseService expenseService;
	
	public List<CheckBean> getModel() {
		return model;
	}

	public void setModel(List<CheckBean> model) {
		this.model = model;
	}

	private List<CheckBean> model;
	
	public String checkList() throws Exception {
		Struts2Utils.getRequest().setAttribute("nav", "3");
		Struts2Utils.getRequest().setAttribute("subNav", "9");
		model = expenseService.checkExpense();
		return SUCCESS;
	}
	
}