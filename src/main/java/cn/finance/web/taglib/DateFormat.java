package cn.finance.web.taglib;

import java.io.IOException;
import java.io.Writer;
import java.util.Date;

import org.apache.struts2.components.Component;

import cn.finance.util.DateUtil;

import com.opensymphony.xwork2.util.ValueStack;

public class DateFormat extends Component {
	
	private Date date;

	public DateFormat(ValueStack stack) {
		super(stack);
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public boolean start(Writer writer) {
		if(date != null) {
			try {
				writer.write(DateUtil.dateToString(date));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
		return super.start(writer);
	}
}
