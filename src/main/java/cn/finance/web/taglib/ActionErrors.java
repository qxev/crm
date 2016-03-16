package cn.finance.web.taglib;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.ActionError;

import com.opensymphony.xwork2.util.ValueStack;

public class ActionErrors extends ActionError {

	public ActionErrors(ValueStack stack, HttpServletRequest request,
			HttpServletResponse response) {
		super(stack, request, response);
		request.getSession().getAttribute("RedirectMessageInterceptor_ActionErrors");
	}

	@Override
	public boolean end(Writer writer, String body) {
		// TODO Auto-generated method stub
		return super.end(writer, body);
	}
}
