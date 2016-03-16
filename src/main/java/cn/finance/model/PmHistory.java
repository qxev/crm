package cn.finance.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cn.finance.model.base.PersistentObject;

/**
 *  SEM分析师负责的项目历史时间表
 */
public class PmHistory extends PersistentObject {

	private static final long serialVersionUID = 1L;
	
	private Account account;    
	
	/**
	 * 开始日期
	 */
	private Date startDate;

	/**
	 * 结束日期
	 */
	private Date endDate;
	
	private Set<Pm> pms = new HashSet<Pm>(); 
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}
	
	@SuppressWarnings("deprecation")
	public Date getEndDateNotNull() {
		if (endDate==null || endDate.compareTo(new Date("3000/01/01"))==0) {
			return null;
		} else {
			return endDate;
		}
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Set<Pm> getPms() {
		return pms;
	}

	public void setPms(Set<Pm> pms) {
		this.pms = pms;
	}

	public Integer[] getPmDisplay(){
		if (pms.size()>0){
			List <Integer>pmStrs = new ArrayList<Integer>();
			Iterator<Pm> iterator = pms.iterator();
	    	while(iterator.hasNext()){
	    		Pm pm = (Pm)iterator.next();
	    		pmStrs.add(pm.getId());
	    		
	        }   
	    	return (Integer[]) pmStrs.toArray(new Integer[pmStrs.size()]);
		} else {
			return new Integer[0];
		}
	}
	
	public String getPmResult() {
		Iterator<Pm> iterator = pms.iterator();
		StringBuffer sb = new StringBuffer();
    	while(iterator.hasNext()){
    		Pm pm = (Pm)iterator.next();
    		sb.append(pm.getName()).append(" ");
        }   
    	return sb.toString();
	}
}
