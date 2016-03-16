package cn.finance.web.taglib;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import cn.springside.modules.orm.Page;

import com.opensymphony.xwork2.util.ValueStack;

public class TableTitleTag extends ComponentTagSupport {

	private static final long serialVersionUID = 1L;

	private String action;

	private String sqlName;

	private String title;

	private String businessTypeId;
	
	private Page<List> page = new Page<List>(30);
	
	private String ctx;
	
	@Override
	public Component getBean(ValueStack valueStack, HttpServletRequest request,
			HttpServletResponse response) {
		return new TableTitle(valueStack);
	}

	@Override
	protected void populateParams() {
		super.populateParams();
		TableTitle tag = (TableTitle) component;
		tag.setAction(action);
		tag.setTitle(title);
		tag.setSqlName(sqlName);
		tag.setPage(page);
		tag.setCtx(ctx);
		tag.setBusinessTypeId(businessTypeId);
	}

	public String getCtx() {
		return ctx;
	}

	public void setCtx(String ctx) {
		this.ctx = ctx;
	}

	public Page<List> getPage() {
		return page;
	}

	public void setPage(Page<List> page) {
		this.page = page;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getSqlName() {
		return sqlName;
	}

	public void setSqlName(String sqlName) {
		this.sqlName = sqlName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(String businessTypeId) {
		this.businessTypeId = businessTypeId;
	}
	
}
