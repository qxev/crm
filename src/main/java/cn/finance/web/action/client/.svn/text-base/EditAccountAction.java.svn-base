package cn.finance.web.action.client;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.finance.model.Account;
import cn.finance.model.Discount;
import cn.finance.model.DiscountBack;
import cn.finance.model.Exchange;
import cn.finance.model.Pm;
import cn.finance.model.PmHistory;
import cn.finance.model.ServiceFee;
import cn.finance.model.ServiceFeeRate;
import cn.finance.model.ServiceType;
import cn.finance.service.AccountService;
import cn.finance.service.DiscountBackService;
import cn.finance.service.DiscountService;
import cn.finance.service.ExchangeService;
import cn.finance.service.PmHistoryService;
import cn.finance.service.PmService;
import cn.finance.service.ServiceFeeRateService;
import cn.finance.service.ServiceFeeService;
import cn.finance.service.ServiceTypeService;
import cn.finance.service.calc.CalcAmountService;
import cn.finance.service.calc.CalcOldService;
import cn.finance.util.Constant;
import cn.finance.util.DateUtil;
import cn.finance.util.Struts2Utils;
import cn.finance.web.action.base.BaseAction;
import cn.finance.web.model.AccountView;
import cn.springside.modules.orm.Page;

public class EditAccountAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private AccountService accountService;

	@Autowired
	private PmService pmService;

	@Autowired
	private PmHistoryService pmHistoryService;

	@Autowired
	private DiscountService discountService;

	@Autowired
	private DiscountBackService discountBackService;

	@Autowired
	private ServiceFeeService serviceFeeService;

	@Autowired
	private ServiceTypeService serviceTypeService;

	@Autowired
	private ServiceFeeRateService serviceFeeRateService;

	@Autowired
	private CalcAmountService calcAmountService;

	@Autowired
	private CalcOldService calcOldService;

	@Autowired
	private ExchangeService exchangeService;

	// 基本属性
	private Account entity;

	private AccountView accountView = new AccountView();

	private Account account;

	private Integer accountId;

	private String userAction;

	private String filter;

	private Integer pageNo;

	private List<Exchange> exchanges;

	private Integer exchangeId;

	private Page<Account> page = new Page<Account>(30);

	public String editAccount() throws Exception {
		Struts2Utils.getRequest().setAttribute("nav", "2");
		Struts2Utils.getRequest().setAttribute("subNav", "5");
		account = accountService.getAccountById(accountId);
		exchanges = exchangeService.getAll();
		if (exchangeId == null)
			exchangeId = account.getExchange().getId();
		if ("addPm".equals(accountView.getUserAction())) {
			addPmHistory();
		} else if ("editPm".equals(accountView.getUserAction())) {
			editPmHistory();
		} else if ("deletePm".equals(accountView.getUserAction())) {
			deletePmHistory();
		} else if ("addDiscount".equals(accountView.getUserAction())) {
			addDiscount();
		} else if ("editDiscount".equals(accountView.getUserAction())) {
			editDiscount();
		} else if ("deleteDiscount".equals(accountView.getUserAction())) {
			deleteDiscount();
		} else if ("addAffDiscount".equals(accountView.getUserAction())) {
			addAffDiscount();
		} else if ("editAffDiscount".equals(accountView.getUserAction())) {
			editAffDiscount();
		} else if ("addDiscountBack".equals(accountView.getUserAction())) {
			addDiscountBack();
		} else if ("editDiscountBack".equals(accountView.getUserAction())) {
			editDiscountBack();
		} else if ("deleteDiscountBack".equals(accountView.getUserAction())) {
			deleteDiscountBack();
		} else if ("addServiceFee".equals(accountView.getUserAction())) {
			addServiceFee();
		} else if ("editServiceFee".equals(accountView.getUserAction())) {
			editServiceFee();
		} else if ("deleteServiceFee".equals(accountView.getUserAction())) {
			deleteServiceFee();
		} else if ("addAffServiceFee".equals(accountView.getUserAction())) {
			addAffServiceFee();
		} else if ("editAffServiceFee".equals(accountView.getUserAction())) {
			editAffServiceFee();
		} else if ("deleteAffServiceFee".equals(accountView.getUserAction())) {
			deleteServiceFee();
		} else if ("addServiceType".equals(accountView.getUserAction())) {
			addServiceType();
		} else if ("editServiceType".equals(accountView.getUserAction())) {
			editServiceType();
		} else if ("deleteServiceType".equals(accountView.getUserAction())) {
			deleteServiceType();
		} else if ("addServiceFeeRate".equals(accountView.getUserAction())) {
			addServiceFeeRate();
		} else if ("editServiceFeeRate".equals(accountView.getUserAction())) {
			editServiceFeeRate();
		} else if ("deleteServiceFeeRate".equals(accountView.getUserAction())) {
			deleteServiceFeeRate();
		} else if ("addAffServiceFeeRate".equals(accountView.getUserAction())) {
			addAffServiceFeeRate();
		} else if ("editAffServiceFeeRate".equals(accountView.getUserAction())) {
			editAffServiceFeeRate();
		} else if ("deleteAffServiceFeeRate".equals(accountView.getUserAction())) {
			deleteServiceFeeRate();
		} else if ("editAccount".equals(accountView.getUserAction())) {
			return updateAccount();
		} else {
			return SUCCESS;
		}
		return EDIT;
	}

	private String updateAccount() {
		account.setTotalBalance(new BigDecimal("".equals(accountView.getTotalBalance()) ? "0" : accountView.getTotalBalance()));
		account.setDarwinBalance(new BigDecimal("".equals(accountView.getDarwinBalance()) ? "0" : accountView.getDarwinBalance()));
		account.setClientBalance(new BigDecimal("".equals(accountView.getClientBalance()) ? "0" : accountView.getClientBalance()));
		account.setSupplementType(accountView.getSupplyTypeId());
		account.setServiceType(accountView.getServiceFeeType());
		account.setAlert(accountView.getAlert());
		account.setStatus(accountView.getStatus());
		account.setGetData(accountView.getGetData());
		Exchange exchange = exchangeService.getExchangeById(exchangeId);
		account.setExchange(exchange);
		accountService.updateAccount(account);
		calcOldService.reCalc(accountId);
		calcOldService.updateExpenseStatus(accountId);
		this.addActionMessage("更新账户成功");
		return INPUT;
	}

	private void editServiceFeeRate() {
		if (serviceFeeRateService.hasRecordByBetweenDay(accountView.getServiceFeeRateStartDate(), accountView.getServiceFeeRateEndDate(),
				accountView.getServiceFeeRateId(), accountId, 1)) {
			this.addActionError("您更新的服务费率记录的时间与已有记录时间冲突");
		} else {
			ServiceFeeRate serviceFeeRate = serviceFeeRateService.getServiceFeeRateById(accountView.getServiceFeeRateId());
			serviceFeeRate.setStartDate(DateUtil.stringToDate(accountView.getServiceFeeRateStartDate()));
			serviceFeeRate.setEndDate((DateUtil.stringToDate(accountView.getServiceFeeRateEndDate()) == null) ? Constant.DEFAULT_DATE
					: DateUtil.stringToDate(accountView.getServiceFeeRateEndDate()));
			BigDecimal value = new BigDecimal("".equals(accountView.getServiceFeeRate()) ? "0" : accountView.getServiceFeeRate());
			serviceFeeRate.setPreValue(serviceFeeRate.getValue());
			serviceFeeRate.setValue(value);
			serviceFeeRate.setAccount(account);
			serviceFeeRateService.save(serviceFeeRate);
			if (DateUtil.inToday(serviceFeeRate.getStartDate(), serviceFeeRate.getEndDate())) {
				account.setCurrentServiceFeeRate(serviceFeeRate.getValue());
			}
			account.setServiceType(2);
			accountService.save(account);
			this.addActionMessage("更新服务费率成功");
		}
	}

	private void editAffServiceFeeRate() {
		if (serviceFeeRateService.hasRecordByBetweenDay(accountView.getAffServiceFeeRateStartDate(),
				accountView.getAffServiceFeeRateEndDate(), accountView.getServiceFeeRateId(), accountId, 2)) {
			this.addActionError("您更新的网盟服务费率记录的时间与已有记录时间冲突");
		} else {
			ServiceFeeRate serviceFeeRate = serviceFeeRateService.getServiceFeeRateById(accountView.getServiceFeeRateId());
			serviceFeeRate.setStartDate(DateUtil.stringToDate(accountView.getAffServiceFeeRateStartDate()));
			serviceFeeRate.setEndDate((DateUtil.stringToDate(accountView.getAffServiceFeeRateEndDate()) == null) ? Constant.DEFAULT_DATE
					: DateUtil.stringToDate(accountView.getAffServiceFeeRateEndDate()));
			BigDecimal value = new BigDecimal("".equals(accountView.getAffServiceFeeRate()) ? "0" : accountView.getAffServiceFeeRate());
			serviceFeeRate.setPreValue(serviceFeeRate.getValue());
			serviceFeeRate.setValue(value);
			serviceFeeRate.setAccount(account);
			serviceFeeRateService.save(serviceFeeRate);
			if (DateUtil.inToday(serviceFeeRate.getStartDate(), serviceFeeRate.getEndDate())) {
				account.setCurrentAffServiceFeeRate(serviceFeeRate.getValue());
			}
			account.setServiceType(2);
			accountService.save(account);
			this.addActionMessage("更新网盟服务费率成功");
		}
	}

	private void deleteServiceFeeRate() {
		ServiceFeeRate serviceFeeRate = serviceFeeRateService.getServiceFeeRateById(accountView.getServiceFeeRateId());
		if (serviceFeeRate != null) {
			serviceFeeRateService.delete(serviceFeeRate);
			calcAmountService.everyDaySet(account);
			this.addActionMessage("更新服务费率成功");
		} else {
			this.addActionMessage("该记录不存在");
		}
	}

	private void addServiceFeeRate() {
		if (serviceFeeRateService.hasRecordByBetweenDay(accountView.getServiceFeeRateStartDate(), accountView.getServiceFeeRateEndDate(),
				0, accountId, 1)) {
			this.addActionError("您增加的服务费率记录的时间与已有记录时间冲突");
		} else {
			ServiceFeeRate serviceFeeRate = serviceFeeRateService.createServiceFeeRate();
			serviceFeeRate.setType(1);
			serviceFeeRate.setStartDate(DateUtil.stringToDate(accountView.getServiceFeeRateStartDate()));
			serviceFeeRate.setEndDate((DateUtil.stringToDate(accountView.getServiceFeeRateEndDate()) == null) ? Constant.DEFAULT_DATE
					: DateUtil.stringToDate(accountView.getServiceFeeRateEndDate()));
			serviceFeeRate.setValue(new BigDecimal("".equals(accountView.getServiceFeeRate()) ? "0" : accountView.getServiceFeeRate()));
			serviceFeeRate.setAccount(account);
			serviceFeeRateService.save(serviceFeeRate);
			if (DateUtil.inToday(serviceFeeRate.getStartDate(), serviceFeeRate.getEndDate())) {
				account.setCurrentServiceFeeRate(serviceFeeRate.getValue());
			}
			account.setServiceType(2);
			accountService.save(account);
			this.addActionMessage("增加服务费率成功");
		}
	}

	private void addAffServiceFeeRate() {
		if (serviceFeeRateService.hasRecordByBetweenDay(accountView.getAffServiceFeeRateStartDate(),
				accountView.getAffServiceFeeRateEndDate(), 0, accountId, 2)) {
			this.addActionError("您增加的网盟服务费率记录的时间与已有记录时间冲突");
		} else {
			ServiceFeeRate serviceFeeRate = serviceFeeRateService.createServiceFeeRate();
			serviceFeeRate.setType(2);
			serviceFeeRate.setStartDate(DateUtil.stringToDate(accountView.getAffServiceFeeRateStartDate()));
			serviceFeeRate.setEndDate((DateUtil.stringToDate(accountView.getAffServiceFeeRateEndDate()) == null) ? Constant.DEFAULT_DATE
					: DateUtil.stringToDate(accountView.getAffServiceFeeRateEndDate()));
			serviceFeeRate
					.setValue(new BigDecimal("".equals(accountView.getAffServiceFeeRate()) ? "0" : accountView.getAffServiceFeeRate()));
			serviceFeeRate.setAccount(account);
			serviceFeeRateService.save(serviceFeeRate);
			if (DateUtil.inToday(serviceFeeRate.getStartDate(), serviceFeeRate.getEndDate())) {
				account.setCurrentAffServiceFeeRate(serviceFeeRate.getValue());
			}
			account.setServiceType(2);
			accountService.save(account);
			this.addActionMessage("增加网盟服务费率成功");
		}
	}

	private void editServiceFee() {
		if (serviceFeeService.hasRecordByBetweenDay(accountView.getServiceFeeStartDate(), accountView.getServiceFeeEndDate(),
				accountView.getServiceFeeId(), accountId)) {
			this.addActionError("您更新的服务费记录的时间与已有记录时间冲突");
		} else {
			ServiceFee serviceFee = serviceFeeService.getServiceFeeById(accountView.getServiceFeeId());
			serviceFee.setStartDate(DateUtil.stringToDate(accountView.getServiceFeeStartDate()));
			serviceFee.setEndDate((DateUtil.stringToDate(accountView.getServiceFeeEndDate()) == null) ? Constant.DEFAULT_DATE : DateUtil
					.stringToDate(accountView.getServiceFeeEndDate()));
			BigDecimal value = new BigDecimal("".equals(accountView.getServiceFee()) ? "0" : accountView.getServiceFee());
			serviceFee.setPreValue(serviceFee.getValue());
			serviceFee.setValue(value);
			serviceFee.setAccount(account);
			serviceFeeService.save(serviceFee);
			if (DateUtil.inToday(serviceFee.getStartDate(), serviceFee.getEndDate())) {
				account.setCurrentServiceFee(serviceFee.getValue());
			}
			account.setServiceType(1);
			accountService.save(account);
			this.addActionMessage("更新服务费成功");
		}
	}

	private void editAffServiceFee() {
		if (serviceFeeService.hasRecordByBetweenDay(accountView.getAffServiceFeeStartDate(), accountView.getAffServiceFeeEndDate(),
				accountView.getServiceFeeId(), accountId)) {
			this.addActionError("您更新的短期服务费记录的时间与已有记录时间冲突");
		} else {
			ServiceFee serviceFee = serviceFeeService.getServiceFeeById(accountView.getServiceFeeId());
			serviceFee.setStartDate(DateUtil.stringToDate(accountView.getAffServiceFeeStartDate()));
			serviceFee.setEndDate((DateUtil.stringToDate(accountView.getAffServiceFeeEndDate()) == null) ? Constant.DEFAULT_DATE : DateUtil
					.stringToDate(accountView.getAffServiceFeeEndDate()));
			BigDecimal value = new BigDecimal("".equals(accountView.getAffServiceFee()) ? "0" : accountView.getAffServiceFee());
			serviceFee.setPreValue(serviceFee.getValue());
			serviceFee.setValue(value);
			serviceFee.setAccount(account);
			serviceFeeService.save(serviceFee);
			if (DateUtil.inToday(serviceFee.getStartDate(), serviceFee.getEndDate())) {
				account.setCurrentServiceFee(serviceFee.getValue());
			}
			account.setServiceType(3);
			accountService.save(account);
			this.addActionMessage("更新短期服务费成功");
		}
	}

	private void editServiceType() {
		if (serviceTypeService.hasRecordByBetweenDay(accountView.getServiceTypeStartDate(), accountView.getServiceTypeEndDate(),
				accountView.getServiceTypeId(), accountId)) {
			this.addActionError("您更新的账户冲值类型的时间与已有记录时间冲突");
		} else {
			ServiceType serviceType = serviceTypeService.getServiceTypeById(accountView.getServiceTypeId());
			serviceType.setStartDate(DateUtil.stringToDate(accountView.getServiceTypeStartDate()));
			serviceType.setEndDate((DateUtil.stringToDate(accountView.getServiceTypeEndDate()) == null) ? Constant.DEFAULT_DATE : DateUtil
					.stringToDate(accountView.getServiceTypeEndDate()));
			serviceType.setType(accountView.getSt());
			serviceType.setAccount(account);
			serviceTypeService.save(serviceType);
			if (DateUtil.inToday(serviceType.getStartDate(), serviceType.getEndDate())) {
				account.setSupplementType(accountView.getSt());
				accountService.save(account);
			}
			calcOldService.updateExpenseStatus(accountId);
			this.addActionMessage("更新账户冲值类型成功");
		}
	}

	private void deleteServiceFee() {
		ServiceFee serviceFee = serviceFeeService.getServiceFeeById(accountView.getServiceFeeId());
		if (serviceFee != null) {
			serviceFeeService.delete(serviceFee);
			calcAmountService.everyDaySet(account);
			this.addActionMessage("删除服务费成功");
		} else {
			this.addActionMessage("该记录不存在");
		}
	}

	private void deleteServiceType() {
		ServiceType serviceType = serviceTypeService.getServiceTypeById(accountView.getServiceTypeId());
		if (serviceType != null) {
			serviceTypeService.delete(serviceType);
			calcAmountService.everyDaySet(account);
			calcOldService.updateExpenseStatus(accountId);
			this.addActionMessage("删除账户冲值记录成功");
		} else {
			this.addActionMessage("该记录不存在");
		}
	}

	private void addServiceFee() {
		if (serviceFeeService.hasRecordByBetweenDay(accountView.getServiceFeeStartDate(), accountView.getServiceFeeEndDate(), 0, accountId)) {
			this.addActionError("您增加的服务费记录的时间与已有记录时间冲突");
		} else {
			ServiceFee serviceFee = serviceFeeService.createServiceFee();
			serviceFee.setType(1);
			serviceFee.setStartDate(DateUtil.stringToDate(accountView.getServiceFeeStartDate()));
			serviceFee.setEndDate((DateUtil.stringToDate(accountView.getServiceFeeEndDate()) == null) ? Constant.DEFAULT_DATE : DateUtil
					.stringToDate(accountView.getServiceFeeEndDate()));
			serviceFee.setValue(new BigDecimal("".equals(accountView.getServiceFee()) ? "0" : accountView.getServiceFee()));
			serviceFee.setAccount(account);
			serviceFeeService.save(serviceFee);
			if (DateUtil.inToday(serviceFee.getStartDate(), serviceFee.getEndDate())) {
				account.setCurrentServiceFee(serviceFee.getValue());
			}
			account.setServiceType(1);
			accountService.save(account);
			this.addActionMessage("增加服务费成功");
		}
	}

	private void addAffServiceFee() {
		if (serviceFeeService.hasRecordByBetweenDay(accountView.getAffServiceFeeStartDate(), accountView.getAffServiceFeeEndDate(), 0,
				accountId)) {
			this.addActionError("您增加的短期服务费记录的时间与已有记录时间冲突");
		} else {
			ServiceFee serviceFee = serviceFeeService.createServiceFee();
			serviceFee.setType(2);
			serviceFee.setStartDate(DateUtil.stringToDate(accountView.getAffServiceFeeStartDate()));
			serviceFee.setEndDate((DateUtil.stringToDate(accountView.getAffServiceFeeEndDate()) == null) ? Constant.DEFAULT_DATE : DateUtil
					.stringToDate(accountView.getAffServiceFeeEndDate()));
			serviceFee.setValue(new BigDecimal("".equals(accountView.getAffServiceFee()) ? "0" : accountView.getAffServiceFee()));
			serviceFee.setAccount(account);
			serviceFeeService.save(serviceFee);
			if (DateUtil.inToday(serviceFee.getStartDate(), serviceFee.getEndDate())) {
				account.setCurrentAffServiceFee(serviceFee.getValue());
			}
			account.setServiceType(3);
			accountService.save(account);
			this.addActionMessage("增加短期服务费成功");
		}
	}

	private void addServiceType() {
		if (serviceTypeService.hasRecordByBetweenDay(accountView.getServiceTypeStartDate(), accountView.getServiceTypeEndDate(), 0,
				accountId)) {
			this.addActionError("您增加的服务类型的时间与已有记录时间冲突");
		} else {
			ServiceType serviceType = serviceTypeService.createServiceType();
			serviceType.setStartDate(DateUtil.stringToDate(accountView.getServiceTypeStartDate()));
			serviceType.setEndDate((DateUtil.stringToDate(accountView.getServiceTypeEndDate()) == null) ? Constant.DEFAULT_DATE : DateUtil
					.stringToDate(accountView.getServiceTypeEndDate()));
			serviceType.setType(accountView.getSupplyTypeId());
			serviceType.setAccount(account);
			serviceTypeService.save(serviceType);
			if (DateUtil.inToday(serviceType.getStartDate(), serviceType.getEndDate())) {
				account.setSupplementType(accountView.getSupplyTypeId());
				accountService.save(account);
			}
			calcOldService.updateExpenseStatus(accountId);
			this.addActionMessage("增加服务类型成功");
		}
	}

	private void editDiscountBack() {
		if (discountBackService.hasRecordByBetweenDay(accountView.getDiscountBackStartDate(), accountView.getDiscountBackEndDate(),
				accountView.getDiscountBackId(), accountId)) {
			this.addActionError("您更新的折扣返还率记录的时间与已有记录时间冲突");
		} else {
			DiscountBack discountBack = discountBackService.getDiscountBackById(accountView.getDiscountBackId());
			discountBack.setStartDate(DateUtil.stringToDate(accountView.getDiscountBackStartDate()));
			discountBack.setEndDate((DateUtil.stringToDate(accountView.getDiscountBackEndDate()) == null) ? Constant.DEFAULT_DATE
					: DateUtil.stringToDate(accountView.getDiscountBackEndDate()));
			discountBack.setValue(new BigDecimal("".equals(accountView.getDiscountBack()) ? "0" : accountView.getDiscountBack()));
			discountBack.setAccount(account);
			discountBackService.save(discountBack);
			if (DateUtil.inToday(discountBack.getStartDate(), discountBack.getEndDate())) {
				account.setCurrentDiscountBack(discountBack.getValue());
				accountService.save(account);
			}
			this.addActionMessage("更新折扣返还率成功");
		}
	}

	private void deleteDiscountBack() {
		DiscountBack discountBack = discountBackService.getDiscountBackById(accountView.getDiscountBackId());
		if (discountBack != null) {
			discountBackService.delete(discountBack);
			calcAmountService.everyDaySet(account);
			this.addActionMessage("删除折扣返还率成功");
		} else {
			this.addActionMessage("更新折扣返还率成功");
		}
	}

	private void addDiscountBack() {
		if (discountBackService.hasRecordByBetweenDay(accountView.getDiscountBackStartDate(), accountView.getDiscountBackEndDate(), 0,
				accountId)) {
			this.addActionError("您添加的折扣返还率记录的时间与已有记录时间冲突");
		} else {
			DiscountBack discountBack = discountBackService.createDiscountBack();
			discountBack.setStartDate(DateUtil.stringToDate(accountView.getDiscountBackStartDate()));
			discountBack.setEndDate((DateUtil.stringToDate(accountView.getDiscountBackEndDate()) == null) ? Constant.DEFAULT_DATE
					: DateUtil.stringToDate(accountView.getDiscountBackEndDate()));
			discountBack.setValue(new BigDecimal("".equals(accountView.getDiscountBack()) ? "0" : accountView.getDiscountBack()));
			discountBack.setAccount(account);
			discountBackService.save(discountBack);
			if (DateUtil.inToday(discountBack.getStartDate(), discountBack.getEndDate())) {
				account.setCurrentDiscountBack(discountBack.getValue());
				accountService.save(account);
			}
			this.addActionMessage("增加折扣返还率成功");
		}
	}

	private void editDiscount() {
		if (discountService.hasRecordByBetweenDay(accountView.getDiscountStartDate(), accountView.getDiscountEndDate(),
				accountView.getDiscountId(), accountId, 1)) {
			this.addActionError("您更新的折扣率记录的时间与已有记录时间冲突");
		} else {
			Discount discount = discountService.getDiscountById(accountView.getDiscountId());
			discount.setStartDate(DateUtil.stringToDate(accountView.getDiscountStartDate()));
			discount.setEndDate((DateUtil.stringToDate(accountView.getDiscountEndDate()) == null) ? Constant.DEFAULT_DATE : DateUtil
					.stringToDate(accountView.getDiscountEndDate()));
			discount.setDarwinDiscount(new BigDecimal("".equals(accountView.getEditDarwinDiscount()) ? "0" : accountView
					.getEditDarwinDiscount()));
			discount.setProjectDiscount(new BigDecimal("".equals(accountView.getEditProjectDiscount()) ? "0" : accountView
					.getEditProjectDiscount()));
			discount.setBonusDiscount(new BigDecimal("".equals(accountView.getEditBonusDiscount()) ? "0" : accountView
					.getEditBonusDiscount()));
			discount.setAccount(account);
			discountService.save(discount);
			if (DateUtil.inToday(discount.getStartDate(), discount.getEndDate())) {
				account.setCurrentDiscount(discount.getDarwinDiscount().add(discount.getProjectDiscount()).add(discount.getBonusDiscount()));
				accountService.save(account);
			}
			this.addActionMessage("更新折扣率成功");
		}
	}

	private void editAffDiscount() {
		if (discountService.hasRecordByBetweenDay(accountView.getDiscountStartDate(), accountView.getDiscountEndDate(),
				accountView.getDiscountId(), accountId, 2)) {
			this.addActionError("您更新的网盟折扣率记录的时间与已有记录时间冲突");
		} else {
			Discount discount = discountService.getDiscountById(accountView.getDiscountId());
			discount.setStartDate(DateUtil.stringToDate(accountView.getDiscountStartDate()));
			discount.setEndDate((DateUtil.stringToDate(accountView.getDiscountEndDate()) == null) ? Constant.DEFAULT_DATE : DateUtil
					.stringToDate(accountView.getDiscountEndDate()));
			discount.setDarwinDiscount(new BigDecimal("".equals(accountView.getEditDarwinDiscount()) ? "0" : accountView
					.getEditDarwinDiscount()));
			discount.setProjectDiscount(new BigDecimal("".equals(accountView.getEditProjectDiscount()) ? "0" : accountView
					.getEditProjectDiscount()));
			discount.setBonusDiscount(new BigDecimal("".equals(accountView.getEditBonusDiscount()) ? "0" : accountView
					.getEditBonusDiscount()));
			discount.setAccount(account);
			discountService.save(discount);
			if (DateUtil.inToday(discount.getStartDate(), discount.getEndDate())) {
				account.setCurrentAffDiscount(discount.getDarwinDiscount().add(discount.getProjectDiscount())
						.add(discount.getBonusDiscount()));
				accountService.save(account);
			}
			this.addActionMessage("更新网盟折扣率成功");
		}
	}

	private void deleteDiscount() {
		Discount discount = discountService.getDiscountById(accountView.getDiscountId());
		if (discount != null) {
			discountService.delete(discount);
			calcAmountService.everyDaySet(account);
			this.addActionMessage("删除折扣率记录成功");
		} else {
			this.addActionMessage("该记录不存在");
		}
	}

	/**
	 * 用户增加折扣率
	 */
	private void addDiscount() {
		if (discountService.hasRecordByBetweenDay(accountView.getDiscountStartDate(), accountView.getDiscountEndDate(), 0, accountId, 1)) {
			this.addActionError("您添加的折扣率记录的时间与已有记录时间冲突");
		} else {
			Discount discount = discountService.createDiscount();
			discount.setStartDate(DateUtil.stringToDate(accountView.getDiscountStartDate()));
			discount.setEndDate((DateUtil.stringToDate(accountView.getDiscountEndDate()) == null) ? Constant.DEFAULT_DATE : DateUtil
					.stringToDate(accountView.getDiscountEndDate()));
			discount.setDarwinDiscount(new BigDecimal("".equals(accountView.getEditDarwinDiscount()) ? "0" : accountView
					.getEditDarwinDiscount()));
			discount.setProjectDiscount(new BigDecimal("".equals(accountView.getEditProjectDiscount()) ? "0" : accountView
					.getEditProjectDiscount()));
			discount.setBonusDiscount(new BigDecimal("".equals(accountView.getEditBonusDiscount()) ? "0" : accountView
					.getEditBonusDiscount()));
			discount.setType(1);
			discount.setAccount(account);
			discountService.save(discount);
			if (DateUtil.inToday(discount.getStartDate(), discount.getEndDate())) {
				account.setCurrentDiscount(discount.getDarwinDiscount().add(discount.getProjectDiscount()).add(discount.getBonusDiscount()));
				accountService.save(account);
			}
			this.addActionMessage("增加折扣率成功");
		}
	}

	/**
	 * 用户增加网盟折扣率
	 */
	private void addAffDiscount() {
		if (discountService.hasRecordByBetweenDay(accountView.getDiscountStartDate(), accountView.getDiscountEndDate(), 0, accountId, 2)) {
			this.addActionError("您添加的网盟折扣率记录的时间与已有记录时间冲突");
		} else {
			Discount discount = discountService.createDiscount();
			discount.setStartDate(DateUtil.stringToDate(accountView.getAffDiscountStartDate()));
			discount.setEndDate((DateUtil.stringToDate(accountView.getAffDiscountEndDate()) == null) ? Constant.DEFAULT_DATE : DateUtil
					.stringToDate(accountView.getAffDiscountEndDate()));
			discount.setDarwinDiscount(new BigDecimal("".equals(accountView.getEditDarwinDiscount()) ? "0" : accountView
					.getEditDarwinDiscount()));
			discount.setProjectDiscount(new BigDecimal("".equals(accountView.getEditProjectDiscount()) ? "0" : accountView
					.getEditProjectDiscount()));
			discount.setBonusDiscount(new BigDecimal("".equals(accountView.getEditBonusDiscount()) ? "0" : accountView
					.getEditBonusDiscount()));
			discount.setType(2);
			discount.setAccount(account);
			discountService.save(discount);
			if (DateUtil.inToday(discount.getStartDate(), discount.getEndDate())) {
				account.setCurrentAffDiscount(discount.getDarwinDiscount().add(discount.getProjectDiscount())
						.add(discount.getBonusDiscount()));
				accountService.save(account);
			}
			this.addActionMessage("增加折扣率成功");
		}
	}

	/**
	 * 用户修改Pm
	 */
	private void editPmHistory() {
		if (pmHistoryService.hasRecordByBetweenDay(accountView.getPmStartDate(), accountView.getPmEndDate(), accountView.getPmHistoryId(),
				accountId)) {
			this.addActionError("您更新的SEM分析师记录的时间与已有记录时间冲突");
		} else {
			PmHistory pmHistory = pmHistoryService.getPmHistoryById(accountView.getPmHistoryId());
			List<Pm> pms = pmService.getPmByIds(accountView.getEditPms());
			pmHistory.setPms(new HashSet<Pm>(pms));
			pmHistory.setStartDate(DateUtil.stringToDate(accountView.getPmStartDate()));
			pmHistory.setEndDate((DateUtil.stringToDate(accountView.getPmEndDate()) == null) ? Constant.DEFAULT_DATE : DateUtil
					.stringToDate(accountView.getPmEndDate()));
			pmHistory.setUpdateAt(new Date());
			pmHistory.setAccount(account);
			pmHistoryService.save(pmHistory);
			if (DateUtil.inToday(pmHistory.getStartDate(), pmHistory.getEndDate())) {
				account.setCurrentPms(pmHistory.getPmResult());
				accountService.save(account);
			}
			this.addActionMessage("更新SEM分析师成功");
		}
	}

	private void deletePmHistory() {
		PmHistory pmHistory = pmHistoryService.getPmHistoryById(accountView.getPmHistoryId());
		if (pmHistory != null) {
			pmHistoryService.delete(pmHistory);
			calcAmountService.everyDaySet(account);
			this.addActionMessage("删除SEM分析师成功");
		} else {
			this.addActionMessage("该记录不存在");
		}
	}

	/**
	 * 用户增加Pm
	 */
	private void addPmHistory() {
		if (pmHistoryService.hasRecordByBetweenDay(accountView.getPmStartDate(), accountView.getPmEndDate(), 0, accountId)) {
			this.addActionError("您添加的SEM分析师记录的时间与已有记录时间冲突");
		} else {
			PmHistory pmHistory = pmHistoryService.createPmHistory();
			pmHistory.setStartDate(DateUtil.stringToDate(accountView.getPmStartDate()));
			pmHistory.setEndDate((DateUtil.stringToDate(accountView.getPmEndDate()) == null) ? Constant.DEFAULT_DATE : DateUtil
					.stringToDate(accountView.getPmEndDate()));
			List<Pm> pms = pmService.getPmByIds(accountView.getEditPms());
			pmHistory.setPms(new HashSet<Pm>(pms));
			pmHistory.setAccount(account);
			pmHistoryService.save(pmHistory);
			if (DateUtil.inToday(pmHistory.getStartDate(), pmHistory.getEndDate())) {
				account.setCurrentPms(pmHistory.getPmResult());
				accountService.save(account);
			}
			this.addActionMessage("增加SEM分析师成功");
		}
	}

	public String importInit() {
		Struts2Utils.getRequest().setAttribute("nav", "2");
		Struts2Utils.getRequest().setAttribute("subNav", "5");
		return "init_import";
	}

	public String importData() {
		Struts2Utils.getRequest().setAttribute("nav", "2");
		Struts2Utils.getRequest().setAttribute("subNav", "5");
		BigDecimal expense = new BigDecimal(accountView.getTotalBalance());
		Calendar start = Calendar.getInstance();
		start.setTime(DateUtil.stringToDate(accountView.getDiscountStartDate()));
		Calendar end = Calendar.getInstance();
		end.setTime(DateUtil.stringToDate(accountView.getDiscountEndDate()));
		int days = (int) ((end.getTimeInMillis() - start.getTimeInMillis()) / 86400000) + 1;
		expense = expense.divide(new BigDecimal(days), 4, RoundingMode.HALF_UP);
		end.add(Calendar.DATE, 1);
		do {
			start.add(Calendar.DATE, 1);
		} while (start.before(end));
		calcOldService.reCalc(accountId);
		return INPUT;
	}

	public Page<Account> getPage() {
		return page;
	}

	public void setPage(Page<Account> page) {
		this.page = page;
	}

	public Account getEntity() {
		return entity;
	}

	public void setEntity(Account entity) {
		this.entity = entity;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getUserAction() {
		return userAction;
	}

	public void setUserAction(String userAction) {
		this.userAction = userAction;
	}

	public AccountView getAccountView() {
		return accountView;
	}

	public void setAccountView(AccountView accountView) {
		this.accountView = accountView;
	}

	public String getFilter() {
		return filter;
	}

	public String getPageFilter() throws UnsupportedEncodingException {
		return URLEncoder.encode(filter, "utf-8");
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public List<Exchange> getExchanges() {
		return exchanges;
	}

	public void setExchanges(List<Exchange> exchanges) {
		this.exchanges = exchanges;
	}

	public Integer getExchangeId() {
		return exchangeId;
	}

	public void setExchangeId(Integer exchangeId) {
		this.exchangeId = exchangeId;
	}

}