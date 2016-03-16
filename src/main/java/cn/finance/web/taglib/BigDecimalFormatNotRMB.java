package cn.finance.web.taglib;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.text.NumberFormat;

import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;

public class BigDecimalFormatNotRMB extends Component {

	private BigDecimal value;

	public BigDecimalFormatNotRMB(ValueStack stack) {
		super(stack);
	}

	@Override
	public boolean start(Writer writer) {
		if (value != null) {
			try {
				NumberFormat numberFormat = NumberFormat.getNumberInstance();
				writer.write(numberFormat.format(value.setScale(2, BigDecimal.ROUND_HALF_UP)));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return super.start(writer);
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
}
