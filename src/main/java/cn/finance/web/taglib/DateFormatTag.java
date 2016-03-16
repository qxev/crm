package cn.finance.web.taglib;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

public class DateFormatTag extends ComponentTagSupport {

	private static final long serialVersionUID = 1L;
	
	private Date date;

	@Override
	public Component getBean(ValueStack valueStack, HttpServletRequest request,
			HttpServletResponse response) {
		return new DateFormat(valueStack);
	}
	
	@Override
	protected void populateParams() {
		super.populateParams();
		DateFormat tag = (DateFormat) component;
		tag.setDate(date);
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
