package cn.finance.model;

import java.util.Date;

import cn.finance.model.base.PersistentObject;

/**
 * 任务日志表
 */
public class TaskLog extends PersistentObject {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 账户Id
	 */
	private Integer accountId;

	/**
	 * 结果: 0 成功  1 失败
	 */
	private Integer type;
	
	/**
	 * 执行时间
	 */
	private Date excuteDate;
	
	/**
	 * 错误日志
	 */
	private String errorLog;

	public Date getExcuteDate() {
		return excuteDate;
	}

	public void setExcuteDate(Date excuteDate) {
		this.excuteDate = excuteDate;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	
	public String getErrorLog() {
		return errorLog;
	}

	public void setErrorLog(String errorLog) {
		this.errorLog = errorLog;
	}


}
