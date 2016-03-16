package cn.finance.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import cn.finance.model.base.PersistentObject;
import cn.finance.util.Constant;
import cn.finance.util.DateUtil;

/**
 * 账户表
 */
public class Account extends PersistentObject {

	private static final long serialVersionUID = 1L;

	/**
	 * 项目
	 */
	private Project project;

	/**
	 * 渠道
	 */
	private Channel channel;

	/**
	 * sv3的id
	 */
	private Integer sv3Id;

	/**
	 * 线上密码
	 */
	private String onlinePassword;

	/**
	 * 账户名
	 */
	private String name;

	/**
	 * 账户类型
	 */
	private Integer type;

	/**
	 * 账户状态 1有效 2无效
	 */
	private Integer status;
	
	/**
	 * 是否抓取 1有效 2无效
	 */
	private Integer getData;

	/**
	 * 服务费类型 1 服务费  2 服务费率
	 */
	private Integer serviceType;
	
	/**
	 * api账户
	 */
	private String apiAccount;

	/**
	 * 冲值类型
	 */
	private Integer supplementType;

	/**
	 * 客户余额
	 */
	private BigDecimal clientBalance;

	/**
	 * 上次充值金额
	 */
	private BigDecimal lastSupplement;

	/**
	 * 上次充值日期
	 */
	private Date lastSuppleDate;
	
	/**
	 * 达闻余额
	 */
	private BigDecimal darwinBalance;
	
	/**
	 * 总余额
	 */
	private BigDecimal totalBalance;
	
	/**
	 * 过去N天最大消费
	 */
	private BigDecimal pastMaxCost;

	/**
	 * 过去N天最小消费
	 */
	private BigDecimal pastMinCost;

	/**
	 * 预警提醒 1提醒 0不提醒
	 */
	private Integer alert;

	/**
	 * 日消费额
	 */
	private BigDecimal dailyBudget;

	/**
	 * 可用天数
	 */
	private Integer remainDays;

	/**
	 * 过去N天最大消费
	 */
	private BigDecimal maxSuggestAdd;

	/**
	 * 过去N天最小消费
	 */
	private BigDecimal minSuggestAdd;

	/**
	 * 最大可用天数
	 */
	private Integer maxRemainDays;
	
	/**
	 * 最小可用天数
	 */
	private Integer minRemainDays;

	/**
	 * 当前折扣率
	 */
	private BigDecimal currentDiscount;

	/**
	 * 当前折扣率
	 */
	private BigDecimal currentAffDiscount;

	/**
	 * 折扣率历史
	 */
	private Set<Discount> discounts = new HashSet<Discount>();

	/**
	 * 当前折扣返还率
	 */
	private BigDecimal currentDiscountBack;

	/**
	 * 折扣率返还历史
	 */
	private Set<DiscountBack> discountBacks = new HashSet<DiscountBack>();
	
	/**
	 * 当前服务费
	 */
	private BigDecimal currentServiceFee;

	/**
	 * 服务费历史
	 */
	private Set<ServiceFee> serviceFees = new HashSet<ServiceFee>();
	
	/**
	 * 当前网盟服务费
	 */
	private BigDecimal currentAffServiceFee;

	/**
	 * 服务费类型
	 */
	private Set<ServiceType> serviceTypes = new HashSet<ServiceType>();

	/**
	 * 花费历史
	 */
	private Set<Expense> expenses = new HashSet<Expense>();
	
	/**
	 * 当前SEM分析师
	 */
	private String currentPms;
	
	/**
	 * SEM分析师负责的项目历史时间表
	 */
	private Set<PmHistory> pmHistorys = new HashSet<PmHistory>();

	/**
	 * 当前服务费率
	 */
	private BigDecimal currentServiceFeeRate;
	
	/**
	 * 服务费率历史
	 */
	private Set<ServiceFeeRate> serviceFeeRates = new HashSet<ServiceFeeRate>();
	
	/**
	 * 当前网盟服务费率
	 */
	private BigDecimal currentAffServiceFeeRate;
	
	/**
	 * 服务费基数历史
	 */
	private Set<ServiceFeeBase> serviceFeeBases = new HashSet<ServiceFeeBase>();

	/**
	 * 汇率
	 */
	private Exchange exchange;
	
	/**
	 * google线上ID
	 */
	private String googleOnlineId;
	
	public Set<PmHistory> getPmHistorys() {
		return pmHistorys;
	}

	public void setPmHistorys(Set<PmHistory> pmHistorys) {
		this.pmHistorys = pmHistorys;
	}

	public void setServiceFees(Set<ServiceFee> serviceFees) {
		this.serviceFees = serviceFees;
	}

	public void setServiceFeeRates(Set<ServiceFeeRate> serviceFeeRates) {
		this.serviceFeeRates = serviceFeeRates;
	}

	public String getOnlinePassword() {
		return onlinePassword;
	}

	public void setOnlinePassword(String onlinePassword) {
		this.onlinePassword = onlinePassword;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getServiceType() {
		return serviceType;
	}

	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}

	public Integer getSupplementType() {
		return supplementType;
	}

	public void setSupplementType(Integer supplementType) {
		this.supplementType = supplementType;
	}

	public BigDecimal getClientBalance() {
		return clientBalance;
	}

	public void setClientBalance(BigDecimal clientBalance) {
		this.clientBalance = clientBalance;
	}

	public BigDecimal getDarwinBalance() {
		return darwinBalance;
	}

	public void setDarwinBalance(BigDecimal darwinBalance) {
		this.darwinBalance = darwinBalance;
	}

	public Integer getAlert() {
		return alert;
	}
	
	public String getAlertDisplay() {
		if (alert == 1){
			return "√";
		} else if(alert == 0) {
			return "×";
		} else {
			return "";
		}
	}

	public void setAlert(Integer alert) {
		this.alert = alert;
	}

	public BigDecimal getDailyBudget() {
		return dailyBudget;
	}

	public void setDailyBudget(BigDecimal dailyBudget) {
		this.dailyBudget = dailyBudget;
	}

	public Set<DiscountBack> getDiscountBacks() {
		return discountBacks;
	}

	public void setDiscountBacks(Set<DiscountBack> discountBacks) {
		this.discountBacks = discountBacks;
	}

	public Set<Discount> getDiscounts() {
		return discounts;
	}

	public void setDiscounts(Set<Discount> discounts) {
		this.discounts = discounts;
	}

	public Set<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(Set<Expense> expenses) {
		this.expenses = expenses;
	}

	public BigDecimal getPastMaxCost() {
		return pastMaxCost;
	}

	public void setPastMaxCost(BigDecimal pastMaxCost) {
		this.pastMaxCost = pastMaxCost;
	}

	public BigDecimal getPastMinCost() {
		return pastMinCost;
	}

	public void setPastMinCost(BigDecimal pastMinCost) {
		this.pastMinCost = pastMinCost;
	}

	public Integer getRemainDays() {
		return remainDays;
	}

	public void setRemainDays(Integer remainDays) {
		this.remainDays = remainDays;
	}

	public Date getSuggestAddMoneyDate() {
		return DateUtil.dateIncreaseByDay(new Date(), remainDays);
	}
	
	public BigDecimal getMaxSuggestAdd() {
		return maxSuggestAdd;
	}

	public void setMaxSuggestAdd(BigDecimal maxSuggestAdd) {
		this.maxSuggestAdd = maxSuggestAdd;
	}

	public BigDecimal getMinSuggestAdd() {
		return minSuggestAdd;
	}

	public void setMinSuggestAdd(BigDecimal minSuggestAdd) {
		this.minSuggestAdd = minSuggestAdd;
	}

	public Integer getMaxRemainDays() {
		return maxRemainDays;
	}

	public void setMaxRemainDays(Integer maxRemainDays) {
		this.maxRemainDays = maxRemainDays;
	}

	public Integer getMinRemainDays() {
		return minRemainDays;
	}

	public void setMinRemainDays(Integer minRemainDays) {
		this.minRemainDays = minRemainDays;
	}
	
	public String getToken() {
		return Constant.USER_TOKEN;
	}

	public String getStatusDisplay() {
		if (status == 1){
			return "√";
		} else if(status == 2) {
			return "×";
		} else {
			return "";
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Account)) {
			return false;
		} else {
			Account account = (Account) obj;
			return new EqualsBuilder().append(this.getId(), account.getId())
					.append(this.getSv3Id(), account.getSv3Id()).isEquals();
		}
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.getId()).append(this.getSv3Id()).toHashCode();
	}

	public Set<ServiceFeeBase> getServiceFeeBases() {
		return serviceFeeBases;
	}

	public void setServiceFeeBases(Set<ServiceFeeBase> serviceFeeBases) {
		this.serviceFeeBases = serviceFeeBases;
	}

	public BigDecimal getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(BigDecimal totalBalance) {
		this.totalBalance = totalBalance;
	}

	public BigDecimal getCurrentDiscount() {
		return currentDiscount;
	}

	public void setCurrentDiscount(BigDecimal currentDiscount) {
		this.currentDiscount = currentDiscount;
	}

	public BigDecimal getCurrentDiscountBack() {
		return currentDiscountBack;
	}

	public void setCurrentDiscountBack(BigDecimal currentDiscountBack) {
		this.currentDiscountBack = currentDiscountBack;
	}

	public BigDecimal getCurrentServiceFee() {
		return currentServiceFee;
	}

	public void setCurrentServiceFee(BigDecimal currentServiceFee) {
		this.currentServiceFee = currentServiceFee;
	}

	public String getCurrentPms() {
		return currentPms;
	}

	public void setCurrentPms(String currentPms) {
		this.currentPms = currentPms;
	}

	public BigDecimal getCurrentServiceFeeRate() {
		return currentServiceFeeRate;
	}

	public void setCurrentServiceFeeRate(BigDecimal currentServiceFeeRate) {
		this.currentServiceFeeRate = currentServiceFeeRate;
	}
	
	public String getApiAccount() {
		return apiAccount;
	}

	public void setApiAccount(String apiAccount) {
		this.apiAccount = apiAccount;
	}
	
	public String getNameChannel(){
		return name+"("+channel.getName()+")";
	}

	public Set<ServiceType> getServiceTypes() {
		return serviceTypes;
	}

	public void setServiceTypes(Set<ServiceType> serviceTypes) {
		this.serviceTypes = serviceTypes;
	}

	public BigDecimal getCurrentAffServiceFee() {
		return currentAffServiceFee;
	}

	public void setCurrentAffServiceFee(BigDecimal currentAffServiceFee) {
		this.currentAffServiceFee = currentAffServiceFee;
	}

	public Set<ServiceFee> getServiceFees() {
		return serviceFees;
	}
	
	public Set<ServiceFee> getSearchServiceFees() {
		if (serviceFees!=null){
			Set<ServiceFee> returnFees = new HashSet<ServiceFee>();
			for (ServiceFee serviceFee : serviceFees){
				if (serviceFee.getType()==1){
					returnFees.add(serviceFee);
				}
			}
			return returnFees;
		} else {
			return serviceFees;
		}
	}
	
	public Set<ServiceFee> getAffServiceFees() {
		if (serviceFees!=null){
			Set<ServiceFee> returnFees = new HashSet<ServiceFee>();
			for (ServiceFee serviceFee : serviceFees){
				if (serviceFee.getType()==2){
					returnFees.add(serviceFee);
				}
			}
			return returnFees;
		} else {
			return serviceFees;
		}
	}
	
	public Set<Discount> getSearchDiscounts() {
		if (discounts!=null){
			Set<Discount> returndiscounts = new HashSet<Discount>();
			for (Discount discount : discounts){
				if (discount.getType()==1){
					returndiscounts.add(discount);
				}
			}
			return returndiscounts;
		} else {
			return discounts;
		}
	}
	
	public Set<Discount> getAffDiscounts() {
		if (discounts!=null){
			Set<Discount> returndiscounts = new HashSet<Discount>();
			for (Discount discount : discounts){
				if (discount.getType()==2){
					returndiscounts.add(discount);
				}
			}
			return returndiscounts;
		} else {
			return discounts;
		}
	}
	
	public Set<ServiceFeeRate> getServiceFeeRates() {
		return serviceFeeRates;
	}
	public Set<ServiceFeeRate> getSearchServiceFeeRates() {
		if (serviceFeeRates!=null){
			Set<ServiceFeeRate> returnFeeRates = new HashSet<ServiceFeeRate>();
			for (ServiceFeeRate serviceFeeRate : serviceFeeRates){
				if (serviceFeeRate.getType()==1){
					returnFeeRates.add(serviceFeeRate);
				}
			}
			return returnFeeRates;
		} else {
			return serviceFeeRates;
		}
	}
	
	public Set<ServiceFeeRate> getAffServiceFeeRates() {
		if (serviceFeeRates!=null){
			Set<ServiceFeeRate> returnFeeRates = new HashSet<ServiceFeeRate>();
			for (ServiceFeeRate serviceFeeRate : serviceFeeRates){
				if (serviceFeeRate.getType()==2){
					returnFeeRates.add(serviceFeeRate);
				}
			}
			return returnFeeRates;
		} else {
			return serviceFeeRates;
		}
	}

	public BigDecimal getCurrentAffServiceFeeRate() {
		return currentAffServiceFeeRate;
	}

	public void setCurrentAffServiceFeeRate(BigDecimal currentAffServiceFeeRate) {
		this.currentAffServiceFeeRate = currentAffServiceFeeRate;
	}

	public Integer getGetData() {
		return getData;
	}

	public void setGetData(Integer getData) {
		this.getData = getData;
	}

	public BigDecimal getLastSupplement() {
		return lastSupplement;
	}

	public void setLastSupplement(BigDecimal lastSupplement) {
		this.lastSupplement = lastSupplement;
	}

	public Date getLastSuppleDate() {
		return lastSuppleDate;
	}

	public void setLastSuppleDate(Date lastSuppleDate) {
		this.lastSuppleDate = lastSuppleDate;
	}

	public BigDecimal getCurrentAffDiscount() {
		return currentAffDiscount;
	}

	public void setCurrentAffDiscount(BigDecimal currentAffDiscount) {
		this.currentAffDiscount = currentAffDiscount;
	}

	public Exchange getExchange() {
		return exchange;
	}

	public void setExchange(Exchange exchange) {
		this.exchange = exchange;
	}

	public String getGoogleOnlineId() {
		return googleOnlineId;
	}

	public void setGoogleOnlineId(String googleOnlineId) {
		this.googleOnlineId = googleOnlineId;
	}
	
}
