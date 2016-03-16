package cn.finance.web.converter;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

public class BigDecimalConverter extends StrutsTypeConverter {

	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		BigDecimal value = null;
		String val = null;
		if (values != null && values.length > 0) {
			val = values[0];
			if (val != null) {
				value = BigDecimal.valueOf(Long.parseLong(val));
			}
		}
		return value;
	}

	@Override
	public String convertToString(Map context, Object o) {
		BigDecimal val = (BigDecimal) o;
		return val.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
	}

}
