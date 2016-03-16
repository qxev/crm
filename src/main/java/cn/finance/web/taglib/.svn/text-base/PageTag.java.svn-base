package cn.finance.web.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

public class PageTag extends ComponentTagSupport {

	private static final long serialVersionUID = 1L;
	
	private cn.springside.modules.orm.Page page;
	
	private String action;
	
	private String excel;
	
	private String businessTypeId;

	public String getExcel() {
		return excel;
	}

	public void setExcel(String excel) {
		this.excel = excel;
	}

	public cn.springside.modules.orm.Page getPage() {
		return page;
	}

	public void setPage(cn.springside.modules.orm.Page page) {
		this.page = page;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	public String getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(String businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

	@Override
	public Component getBean(ValueStack valueStack, HttpServletRequest request,
			HttpServletResponse response) {
		return new Page(valueStack);
	}

	@Override
	protected void populateParams() {
		super.populateParams();
		Page tag = (Page) component;
		tag.setExcel(excel);
		tag.setAction(action);
		tag.setPage(page);
		tag.setBusinessTypeId(businessTypeId);
	}
}
