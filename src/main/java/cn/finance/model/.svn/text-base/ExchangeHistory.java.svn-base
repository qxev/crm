package cn.finance.model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import cn.finance.model.base.PersistentObject;

/**
 *  汇率
 */
public class ExchangeHistory extends PersistentObject {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 汇率名
	 */
	private Exchange exchange;    
	
	/**
	 * 汇率日期
	 */
	private Date exchangeDate;

	/**
	 * 值
	 */
	private BigDecimal value;
	
	public Exchange getExchange() {
		return exchange;
	}

	public void setExchange(Exchange exchange) {
		this.exchange = exchange;
	}

	public Date getExchangeDate() {
		return exchangeDate;
	}
	
	public String getExchangeDateDisplay() {
		StringBuffer date = new StringBuffer();
		if (exchangeDate!=null){
			Calendar cal = Calendar.getInstance();
			cal.setTime(exchangeDate);
			date.append(cal.get(Calendar.YEAR)).append("年").append(cal.get(Calendar.MONTH)+1).append("月");
		}
		return date.toString();
	}

	public void setExchangeDate(Date exchangeDate) {
		this.exchangeDate = exchangeDate;
	}

	public BigDecimal getValue() {
		return value;
	}
	
	public void setValueDisplay(String value) {
		this.value = new BigDecimal(value);
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
}
