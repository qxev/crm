package cn.finance.web.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import cn.finance.util.DateUtil;

public class DateConverter extends StrutsTypeConverter {

	private static String DATE_TIME_FOMART_IE = "yyyy-MM-dd HH:mm:ss";

	private static String DATE_TIME_FOMART_FF = "yy/MM/dd hh:mm:ss";

	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		Date date = null;
		String dateString = null;
		if (values != null && values.length > 0) {
			dateString = values[0];
			if (dateString != null) {
				// 匹配IE浏览器
				SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FOMART_IE);
				try {
					date = format.parse(dateString);
				} catch (ParseException e) {
					date = null;
				}
				// 匹配Firefox浏览器
				if (date == null) {
					format = new SimpleDateFormat(DATE_TIME_FOMART_FF);
					try {
						date = format.parse(dateString);
					} catch (ParseException e) {
						date = null;
					}
				}
			}
		}
		return date;
	}

	@Override
	public String convertToString(Map context, Object o) {
		// 格式化为date格式的字符串
		Date date = (Date) o;
		String dateTimeString = DateUtil.dateToString(date);
		return dateTimeString;
	}
}
