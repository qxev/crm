package cn.finance.model;

import cn.finance.model.base.PersistentObject;

/**
 *  版本表
 */
public class Version extends PersistentObject {

	private static final long serialVersionUID = 1L;
	
	private Contract contract;    
	
	private String version;
	
	private String context;
	
	private User suggestUser;
	
	private String reason;
	
	private String prefix;

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public User getSuggestUser() {
		return suggestUser;
	}

	public void setSuggestUser(User suggestUser) {
		this.suggestUser = suggestUser;
	}

	public String getReason() {
		return reason;
	}
	
	public String getReasonhtml() {
		return reason.replaceAll("\r\n", "<br/>");
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
}
