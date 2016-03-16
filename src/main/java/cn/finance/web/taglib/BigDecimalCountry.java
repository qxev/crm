package cn.finance.web.taglib;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;

public class BigDecimalCountry extends Component {

	private BigDecimal value;
	
	private Integer country;

	public BigDecimalCountry(ValueStack stack) {
		super(stack);
	}

	@Override
	public boolean start(Writer writer) {
		if (value != null) {
			try {
				NumberFormat numberFormat = NumberFormat.getNumberInstance();
				if (country==2){
					writer.write("$");
				} else if (country==3){
					writer.write("€");
				} else if (country==4){
					writer.write("£");
				} else if (country==5){
					writer.write("￥");
				} else if (country==6){
					writer.write("HK$");
				}
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

	public Integer getCountry() {
		return country;
	}

	public void setCountry(Integer country) {
		this.country = country;
	}
	
}
