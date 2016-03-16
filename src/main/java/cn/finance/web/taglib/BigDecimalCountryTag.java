package cn.finance.web.taglib;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

public class BigDecimalCountryTag extends ComponentTagSupport {

	private static final long serialVersionUID = 1L;

	private BigDecimal value;
	
	private Integer country;

	@Override
	public Component getBean(ValueStack valueStack, HttpServletRequest request,
			HttpServletResponse response) {
		return new BigDecimalCountry(valueStack);
	}

	@Override
	protected void populateParams() {
		super.populateParams();
		BigDecimalCountry tag = (BigDecimalCountry) component;
		tag.setValue(value);
		tag.setCountry(country);
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Integer getCountry() {
		return country;
	}

	public void setCountry(Integer country) {
		this.country = country;
	}
}
