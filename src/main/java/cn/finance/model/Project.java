package cn.finance.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import cn.finance.model.base.PersistentObject;

/**
 *  项目表
 */
public class Project extends PersistentObject {

	private static final long serialVersionUID = 1L;
	
	/**
	 * sv3 ID
	 */
	private Integer sv3Id;
	
	/**
	 * 客户
	 */
	private Client client;
	
	/**
	 * 项目名
	 */
	private String name;  
	
	/**
	 * 属于的行业
	 */
	private Industry industry;
	
	/**
	 * 项目状态
	 */
	private Integer status;
	
	/**
	 * 项目下的账户
	 */
	private Set<Account> accounts = new HashSet<Account>();
	
	/**
	 * BD负责人
	 */
	private Set<Bd> bds = new HashSet<Bd>();

	/**
	 * PM负责人
	 */
	private Set<Pm> pms = new HashSet<Pm>();

	public Integer getSv3Id() {
		return sv3Id;
	}

	public void setSv3Id(Integer sv3Id) {
		this.sv3Id = sv3Id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}
	
	public String getStatusDisplay() {
		if (status == null){
			return "";
		} else if (status == 1){
			return "有效";
		} else if(status == 2) {
			return "无效";
		} else {
			return "";
		}
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Industry getIndustry() {
		return industry;
	}

	public void setIndustry(Industry industry) {
		this.industry = industry;
	}

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}


	public Set<Bd> getBds() {
		return bds;
	}

	public String getBdDisplay() {
		Iterator<Bd> iterator = bds.iterator();
		StringBuffer sb = new StringBuffer();
		boolean flag = false;
    	while(iterator.hasNext()){
    		Bd bd = (Bd)iterator.next();
    		if (!flag){
    			flag = true;
    		} else {
    			sb.append(",");
    		}
    		sb.append(bd.getName());
    		
        }   
    	return sb.toString();
	}
	
	public void setBds(Set<Bd> bds) {
		this.bds = bds;
	}

	public Set<Pm> getPms() {
		return pms;
	}

	public String getPmDisplay() {
		Iterator<Pm> iterator = pms.iterator();
		StringBuffer sb = new StringBuffer();
		boolean flag = false;
    	while(iterator.hasNext()){
    		Pm pm = (Pm)iterator.next();
    		if (!flag){
    			flag = true;
    		} else {
    			sb.append(",");
    		}
    		sb.append(pm.getName());
    		
        }   
    	return sb.toString();
	}
	
	public Integer getPmId() {
		Iterator<Pm> iterator = pms.iterator();
    	Integer pmId = 0;
		while(iterator.hasNext()){
    		Pm pm = (Pm)iterator.next();
    		pmId =pm.getId();
        }   
    	return pmId;
	}
	
	public Integer getBdId() {
		Iterator<Bd> iterator = bds.iterator();
    	Integer bdId = 0;
		while(iterator.hasNext()){
    		Bd bd = (Bd)iterator.next();
    		bdId =bd.getId();
        }   
    	return bdId;
	}
	
	public void setPms(Set<Pm> pms) {
		this.pms = pms;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Project)) {
			return false;
		} else {
			Project project = (Project) obj;
			return new EqualsBuilder().append(this.getId(), project.getId())
					.append(this.getSv3Id(), project.getSv3Id()).isEquals();
		}
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.getId()).append(this.getSv3Id()).toHashCode();
	}

}
