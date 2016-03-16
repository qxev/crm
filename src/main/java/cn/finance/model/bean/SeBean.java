package cn.finance.model.bean;

import java.math.BigDecimal;
/**
 * 
 */
public class SeBean {

	private static final long serialVersionUID = 1L;

	/**
	 * sum
	 */
	private BigDecimal sum;
	
	private String name1;

	private String name2;
	
	private Integer id;
	
	public SeBean(BigDecimal sum, String name1,
			String name2,Integer id) {
		super();
		this.sum = sum;
		this.name1 = name1;
		this.name2 = name2;
		this.id=id;
	}
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getSum() {
		return sum;
	}


	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}


	public String getName1() {
		return name1;
	}


	public void setName1(String name1) {
		this.name1 = name1;
	}


	public String getName2() {
		return name2;
	}


	public void setName2(String name2) {
		this.name2 = name2;
	}


}
