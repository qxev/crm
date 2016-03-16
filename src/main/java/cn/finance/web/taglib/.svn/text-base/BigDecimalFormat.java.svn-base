package cn.finance.web.taglib;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;

public class BigDecimalFormat extends Component {

	private BigDecimal value;

	public BigDecimalFormat(ValueStack stack) {
		super(stack);
	}

	@Override
	public boolean start(Writer writer) {
		if (value != null) {
			try {
				Locale locale=Locale.CHINA;
				NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
				writer.write(currencyFormat.format(value.setScale(2, BigDecimal.ROUND_HALF_UP)));
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
