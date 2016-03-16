package cn.finance.web.taglib;

import javax.servlet.jsp.PageContext;

import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;

public class WebInfo extends Component {
	
	private PageContext pageContext;
	
	private String type;

	public WebInfo(ValueStack stack, PageContext pageContext) {
		super(stack);
	}

}
