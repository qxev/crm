package cn.finance.web.taglib;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

public class BigDecimalFormatNotRMBTag extends ComponentTagSupport {

	private static final long serialVersionUID = 1L;

	private BigDecimal value;

	@Override
	public Component getBean(ValueStack valueStack, HttpServletRequest request,
			HttpServletResponse response) {
		return new BigDecimalFormatNotRMB(valueStack);
	}

	@Override
	protected void populateParams() {
		super.populateParams();
		BigDecimalFormatNotRMB tag = (BigDecimalFormatNotRMB) component;
		tag.setValue(value);
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

}
