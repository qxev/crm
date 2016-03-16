package cn.finance.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.time.DateUtils;

import cn.finance.model.base.PersistentObject;

/**
 * 合同表
 */
public class Contract extends PersistentObject {

	private static final long serialVersionUID = 1L;

	private ContractProject contractProject;

	/**
	 * 合同号
	 */
	private String code;

	/**
	 * 联系人
	 */
	private User chargePerson;

	private String leadName;

	private String product;

	private String contractStartDate;

	private String contractEndDate;

	private String monthlyBudget;

	private String serviceFee;

	private String roi;

	private String paymentTerm;

	private String specialTerms;

	private Date remindTime;

	private Date finishTime;

	private String remindDate;

	private Integer remindClock;

	private Integer departApprove;

	private Integer financeApprove;

	/**
	 * 0: 上海 1: 北京
	 */
	private Integer areaId;

	/**
	 * 所属的项目
	 */
	private Set<SearchEngine> searchEngines = new HashSet<SearchEngine>();

	private String searchEngineStr;

	/**
	 * 创建用户
	 */
	private User createUser;

	/**
	 * 状态
	 */
	private String status;

	/**
	 * 上次提醒时间
	 */
	private Date lastRemind;

	/**
	 * 版本
	 */
	private Integer versionNum;

	/**
	 * 合同后缀
	 */
	private String prefix;

	/**
	 * 最终版后缀
	 */
	private String finalPrefix;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public User getChargePerson() {
		return chargePerson;
	}

	public void setChargePerson(User chargePerson) {
		this.chargePerson = chargePerson;
	}

	public String getContractStartDate() {
		return contractStartDate;
	}

	public void setContractStartDate(String contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	public String getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(String contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public String getStatus() {
		return status;
	}

	public String getStatusDisplay() {
		if ("1".equals(status)) {
			return "合同草稿";
		} else if ("2".equals(status)) {
			return "模板被退回";
		} else if ("3".equals(status)) {
			return "等待BD主管审核";
		} else if ("4".equals(status)) {
			String returnValue = "";
			if (departApprove != null && departApprove == 1) {
				returnValue = "SEM主管已审核完毕";
			} else {
				returnValue = "等待SEM主管审核";
			}
			if (financeApprove != null && financeApprove == 1) {
				returnValue = returnValue + ",FIN主管已审核完毕";
			} else {
				returnValue = returnValue + ",等待FIN主管审核";
			}
			return returnValue;
		} else if ("5".equals(status)) {
			String returnValue = "";
			if (departApprove != null && departApprove == 1) {
				returnValue = "SEO主管已审核完毕";
			} else {
				returnValue = "等待SEO主管审核";
			}
			if (financeApprove != null && financeApprove == 1) {
				returnValue = returnValue + ",FIN主管已审核完毕";
			} else {
				returnValue = returnValue + ",等待FIN主管审核";
			}
			return returnValue;
		} else if ("6".equals(status)) {
			String returnValue = "";
			if (departApprove != null && departApprove == 1) {
				returnValue = "UT主管已审核完毕";
			} else {
				returnValue = "等待UT主管审核";
			}
			if (financeApprove != null && financeApprove == 1) {
				returnValue = returnValue + ",FIN主管已审核完毕";
			} else {
				returnValue = returnValue + ",等待FIN主管审核";
			}
			return returnValue;
		} else if ("7".equals(status)) {
			String returnValue = "";
			if (departApprove != null && departApprove == 1) {
				returnValue = "Social主管已审核完毕";
			} else {
				returnValue = "等待Social主管审核";
			}
			if (financeApprove != null && financeApprove == 1) {
				returnValue = returnValue + ",FIN主管已审核完毕";
			} else {
				returnValue = returnValue + ",等待FIN主管审核";
			}
			return returnValue;
		} else if ("13".equals(status)) {
			String returnValue = "";
			if (departApprove != null && departApprove == 1) {
				returnValue = "OTHER主管已审核完毕";
			} else {
				returnValue = "等待OTHER主管审核";
			}
			if (financeApprove != null && financeApprove == 1) {
				returnValue = returnValue + ",FIN主管已审核完毕";
			} else {
				returnValue = returnValue + ",等待FIN主管审核";
			}
			return returnValue;
		} else if ("9".equals(status)) {
			return "等待客户确认";
		} else if ("10".equals(status)) {
			return "waiting for CEO to check";
		} else if ("11".equals(status)) {
			return "审核完毕";
		} else if ("12".equals(status)) {
			return "合同扫描完毕";
		} else {
			return "";
		}
	}

	public String getStatusSubmit() {
		if ("1".equals(status) || "2".equals(status)) {
			return "提交BD主管审核";
		} else if ("3".equals(status)) {
			return "提交业务主管,FIN主管审核";
		} else if ("4".equals(status) || "5".equals(status) || "6".equals(status) || "7".equals(status)
				|| "8".equals(status)) {
			return "提交客户审核";
		} else if ("9".equals(status)) {
			return "审核通过";
		} else {
			return "";
		}
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getLastRemind() {
		return lastRemind;
	}

	public String getLastRemindStr() {
		String returnValue = "";
		if (lastRemind != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			returnValue = dateFormat.format(lastRemind);
		}
		return returnValue;
	}

	public String getFinishTimeStr() {
		String returnValue = "";
		if (finishTime != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			returnValue = dateFormat.format(finishTime);
		}
		return returnValue;
	}

	public String getCanRemind() {
		if (lastRemind != null) {
			Date oneHour = DateUtils.addHours(new Date(), -1);
			if (oneHour.after(lastRemind)) {
				return "1";
			} else {
				return "0";
			}
		} else {
			return "0";
		}
	}

	public void setLastRemind(Date lastRemind) {
		this.lastRemind = lastRemind;
	}

	public String getSearchEngineStr() {
		return searchEngineStr;
	}

	public void setSearchEngineStr(String searchEngineStr) {
		this.searchEngineStr = searchEngineStr;
	}

	public ContractProject getContractProject() {
		return contractProject;
	}

	public void setContractProject(ContractProject contractProject) {
		this.contractProject = contractProject;
	}

	public Integer getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getFinalPrefix() {
		return finalPrefix;
	}

	public void setFinalPrefix(String finalPrefix) {
		this.finalPrefix = finalPrefix;
	}

	public String getLeadName() {
		return leadName;
	}

	public void setLeadName(String leadName) {
		this.leadName = leadName;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getMonthlyBudget() {
		return monthlyBudget;
	}

	public void setMonthlyBudget(String monthlyBudget) {
		this.monthlyBudget = monthlyBudget;
	}

	public String getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(String serviceFee) {
		this.serviceFee = serviceFee;
	}

	public String getRoi() {
		return roi;
	}

	public void setRoi(String roi) {
		this.roi = roi;
	}

	public String getPaymentTerm() {
		return paymentTerm;
	}

	public void setPaymentTerm(String paymentTerm) {
		this.paymentTerm = paymentTerm;
	}

	public String getSpecialTerms() {
		return specialTerms;
	}

	public void setSpecialTerms(String specialTerms) {
		this.specialTerms = specialTerms;
	}

	public Set<SearchEngine> getSearchEngines() {
		return searchEngines;
	}

	public void setSearchEngines(Set<SearchEngine> searchEngines) {
		this.searchEngines = searchEngines;
	}

	public Date getRemindTime() {
		return remindTime;
	}

	public void setRemindTime(Date remindTime) {
		this.remindTime = remindTime;
	}

	public Integer getDepartApprove() {
		return departApprove;
	}

	public void setDepartApprove(Integer departApprove) {
		this.departApprove = departApprove;
	}

	public Integer getFinanceApprove() {
		return financeApprove;
	}

	public void setFinanceApprove(Integer financeApprove) {
		this.financeApprove = financeApprove;
	}

	public String getRemindDate() {
		return remindDate;
	}

	public void setRemindDate(String remindDate) {
		this.remindDate = remindDate;
	}

	public Integer getRemindClock() {
		return remindClock;
	}

	public void setRemindClock(Integer remindClock) {
		this.remindClock = remindClock;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
}
