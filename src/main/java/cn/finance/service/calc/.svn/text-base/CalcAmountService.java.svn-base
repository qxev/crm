package cn.finance.service.calc;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sourceforge.jeval.EvaluationException;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cn.finance.dao.AccountDao;
import cn.finance.dao.DiscountBackDao;
import cn.finance.dao.DiscountDao;
import cn.finance.dao.ExpenseDao;
import cn.finance.dao.PmHistoryDao;
import cn.finance.dao.ServiceFeeDao;
import cn.finance.dao.ServiceFeeRateDao;
import cn.finance.dao.ServiceTypeDao;
import cn.finance.dao.SysInfoDao;
import cn.finance.model.Account;
import cn.finance.model.Discount;
import cn.finance.model.DiscountBack;
import cn.finance.model.PmHistory;
import cn.finance.model.ServiceFee;
import cn.finance.model.ServiceFeeRate;
import cn.finance.model.ServiceType;
import cn.finance.model.TaskLog;
import cn.finance.service.TaskLogService;
import cn.finance.util.AppContext;
import cn.finance.util.DateUtil;
import cn.finance.util.EvaluatorUtils;

public class CalcAmountService {

	// where expense_date between expense_date
	// sysinfo alert_days 根据它来获取 天记录
	// max 入库 past_max_cost
	// min 入库 past_min_cost
	// max_remain_days 入库 (max_suggest_add + 余额) / daliy_budget
	// min_remain_days 入库 (min_suggest_add + 余额) / daliy_budget
	// max_suggest_add 入库 (max + min) / 系统中获取的值 * alert_days
	// min_suggest_add 入库 (max + min) * 系统中获取的值 * alert_days
	// daliy_budget 入库 数据库中 sysinfo 中 sum(n) /alert_days
	// remain_days 入库 account表中total_balance 入库 total_balance / daliy_budget

	public final static String MAX_REMAIN_DAYS = "(#{max_suggest_add} + #{total_balance}) / #{daily_budget}";

	public final static String MIN_REMAIN_DAYS = "(#{min_suggest_add} + #{total_balance}) / #{daily_budget}";

	public final static String MAX_SUGGEST_ADD = "(#{costmax} + #{costmin}) / #{sysval} * #{alert_days} * 0.5";

	public final static String MIN_SUGGEST_ADD = "(#{costmax} + #{costmin}) * #{sysval} * #{alert_days} * 0.5";

	public final static String DALIY_BUDGET = "#{costsum} / #{alert_days}";

	public final static String REMAIN_DAYS = "#{total_balance} / #{daily_budget}";

	@Autowired
	private PmHistoryDao pmHistoryDao; 

	@Autowired
	private DiscountDao discountDao;

	@Autowired
	private DiscountBackDao discountBackDao;

	@Autowired
	private ServiceFeeDao serviceFeeDao;

	@Autowired
	private ServiceTypeDao serviceTypeDao;

	@Autowired
	private ServiceFeeRateDao serviceFeeRateDao;

	@Autowired
	private SysInfoDao sysInfoDao;

	@Autowired
	public AccountDao accountDao;
	
	@Autowired
	public ExpenseDao expenseDao;

	private Integer alertDays;

	private Integer caleDays;

	private BigDecimal parameter;

	// 获取初始化的值
	@Transactional
	public void start() {
		TaskLogService taskLogService = (TaskLogService) AppContext
				.getBean("taskLogService");
		try {
			setAlertDays(new Integer(sysInfoDao.getSysInfoByName("alert_days")
					.getValue()));
			setCaleDays(new Integer(sysInfoDao.getSysInfoByName("cale_days")
					.getValue()));
			setParameter(new BigDecimal(sysInfoDao
					.getSysInfoByName("parameter").getValue()));
			List<Account> accounts = getCalcAccounts();
			for (int i=0;i<accounts.size();i++){
				Account account = accounts.get(i);
				try {
					calcByAccount(account);
				} catch (Exception e) {
					TaskLog taskLog = taskLogService.createTaskLog();
					taskLog.setAccountId(account.getId());
					taskLog.setErrorLog(e.getMessage());
					taskLog.setType(2);
					taskLogService.save(taskLog);
				}
			}
			TaskLog taskLog = taskLogService.createTaskLog();
			taskLog.setAccountId(-1);
			taskLog.setType(0);
			taskLogService.save(taskLog);
		} catch (Exception e) {
			e.printStackTrace();
			TaskLog taskLog = taskLogService.createTaskLog();
			taskLog.setAccountId(-1);
			taskLog.setErrorLog(e.getMessage());
			taskLog.setType(1);
			taskLogService.save(taskLog);
		}
	}

	// 1.获取需要计算的账户
	private List<Account> getCalcAccounts() {
		List<Account> list = accountDao.getAllEffective();
		return list;
	}

	// 根据账户来计算
	@SuppressWarnings("unchecked")
	public void calcByAccount(Account account) throws EvaluationException {

		List data = expenseDao.calcData(DateUtil.dateIncreaseByDay(
				new Date(), -caleDays),new Date(), account.getId());
//		List data = expenseDao.calcData1(DateUtil.getCurrentDateShortStyle(), DateUtil
//				.getCurrentDateShortStyle(DateUtil.dateIncreaseByDay(
//						new Date(), -alertDays)), account.getId());

		
		if (data != null && data.size() > 0) {
			Object[] datas = (Object[]) data.get(0);
			if (datas[0] != null) {
				BigDecimal costavg = BigDecimal.valueOf(Double
						.parseDouble(datas[1].toString()));
				BigDecimal costmax = BigDecimal.valueOf(Double
						.parseDouble(datas[2].toString()));
				BigDecimal costmin = BigDecimal.valueOf(Double
						.parseDouble(datas[3].toString()));

				Map<String, String> map = new HashMap<String, String>();
				map.put("costavg", String.valueOf(costavg));
				map.put("costmax", String.valueOf(costmax));
				map.put("costmin", String.valueOf(costmin));
				map.put("alert_days", String.valueOf(alertDays));
				map.put("sysval", String.valueOf(parameter));
				map.put("daily_budget", String.valueOf(costavg));
				map.put("total_balance", String.valueOf(account
						.getTotalBalance()));

				account.setPastMaxCost(costmax);
				account.setPastMinCost(costmin);

				BigDecimal max_suggest_add = calcMaxSuggestAdd(map);
				BigDecimal min_suggest_add = calcMinSuggestAdd(map);

				map.put("max_suggest_add", String.valueOf(max_suggest_add));
				map.put("min_suggest_add", String.valueOf(min_suggest_add));

				Integer max_remain_days = 1000;
				Integer min_remain_days = 1000;
				Integer remain_days = 1000;
				if (!"0.0".equals(map.get("daily_budget"))) {
					max_remain_days = calcMaxRemainDays(map);
					min_remain_days = calcMinRemainDays(map);
					remain_days = calcRemainDays(map);
				}
				if (max_remain_days<0){
					max_remain_days = 0;
				}
				if (min_remain_days<0){
					min_remain_days = 0;
				}
				if (remain_days<0){
					remain_days = 0;
				}
				map.put("max_remain_days", String.valueOf(max_remain_days));
				map.put("min_remain_days", String.valueOf(min_remain_days));
				map.put("remain_days", String.valueOf(remain_days));

				account = dataCollAccount(account, map);
				everyDaySet(account);
				account.setUpdateAt(new Date());
				accountDao.save(account);
			}
		}

	}

	public void everyDaySet(Account account) {
		Date date = new Date();
		//设置每天的pm
		PmHistory pmHistory = pmHistoryDao.getPmHistoryByDate(date, account
				.getId());
		if (pmHistory != null) {
			account.setCurrentPms(pmHistory.getPmResult());
		} else {
			account.setCurrentPms("");
		}
		//设置每天的discount
		Discount discount = discountDao
				.getDiscountByDate(date, account.getId(),1);
		if (discount != null) {
			account.setCurrentDiscount(discount.getTotalDiscount());
		} else {
			account.setCurrentDiscount(new BigDecimal(0));
		}
		//设置每天的Affdiscount
		Discount affDiscount = discountDao
				.getDiscountByDate(date, account.getId(),2);
		if (affDiscount != null) {
			account.setCurrentAffDiscount(affDiscount.getTotalDiscount());
		} else {
			account.setCurrentAffDiscount(new BigDecimal(0));
		}
		//设置每天的discountBack
		DiscountBack discountBack = discountBackDao.getDiscountBackByDate(date,
				account.getId());
		if (discountBack != null) {
			account.setCurrentDiscountBack(discountBack.getValue());
		} else {
			account.setCurrentDiscountBack(new BigDecimal(0));
		}
		//设置每天的serviceFee
		ServiceFee serviceFee = serviceFeeDao.getServiceFeeByDate(date, account
				.getId(),1);
		if (serviceFee != null) {
			account.setCurrentServiceFee(serviceFee.getValue());
		} else {
			account.setCurrentServiceFee(new BigDecimal(0));
		}
//		//设置每天的AffserviceFee
//		ServiceFee affServiceFee = serviceFeeDao.getServiceFeeByDate(date, account
//				.getId(),2);
//		if (affServiceFee != null) {
//			account.setCurrentAffServiceFee(affServiceFee.getValue());
//		} else {
//			account.setCurrentAffServiceFee(new BigDecimal(0));
//		}
		//设置每天的serviceType
		ServiceType serviceType = serviceTypeDao.getServiceTypeByDate(date, account
				.getId());
		if (serviceType != null) {
			account.setSupplementType(serviceType.getType());
		} else {
			account.setSupplementType(1);
		}
		//设置每天的serviceFeeRate
		ServiceFeeRate serviceFeeRate = serviceFeeRateDao
				.getServiceFeeRateByDate(date, account.getId(),1);
		if (serviceFeeRate != null) {
			account.setCurrentServiceFeeRate(serviceFeeRate.getValue());
		} else {
			account.setCurrentServiceFeeRate(new BigDecimal(0));
		}
//		//设置每天的affServiceFeeRate
//		ServiceFeeRate affServiceFeeRate = serviceFeeRateDao
//				.getServiceFeeRateByDate(date, account.getId(),2);
//		if (affServiceFeeRate != null) {
//			account.setCurrentAffServiceFeeRate(affServiceFeeRate.getValue());
//		} else {
//			account.setCurrentAffServiceFeeRate(new BigDecimal(0));
//		}
	}

	private Account dataCollAccount(Account account, Map<String, String> map) {
		account.setDailyBudget(new BigDecimal(map.get("daily_budget")));
		account.setMaxSuggestAdd(new BigDecimal(map.get("max_suggest_add")));
		account.setMinSuggestAdd(new BigDecimal(map.get("min_suggest_add")));
		account.setMaxRemainDays(new Integer(map.get("max_remain_days")));
		account.setMinRemainDays(new Integer(map.get("min_remain_days")));
		account.setRemainDays(new Integer(map.get("remain_days")));
		account.setUpdateAt(new Date());
		return account;
	}

	private Integer calcMaxRemainDays(Map<String, String> map)
			throws EvaluationException {
		return new Double(Math.floor(NumberUtils.toDouble(EvaluatorUtils
				.evaluator(MAX_REMAIN_DAYS, map)))).intValue();
	};

	private Integer calcMinRemainDays(Map<String, String> map)
			throws EvaluationException {
		return new Double(Math.floor(NumberUtils.toDouble(EvaluatorUtils
				.evaluator(MIN_REMAIN_DAYS, map)))).intValue();
	};

	private BigDecimal calcMaxSuggestAdd(Map<String, String> map)
			throws EvaluationException {
		return new BigDecimal(EvaluatorUtils.evaluator(MAX_SUGGEST_ADD, map));
	};

	private BigDecimal calcMinSuggestAdd(Map<String, String> map)
			throws EvaluationException {
		return new BigDecimal(EvaluatorUtils.evaluator(MIN_SUGGEST_ADD, map));
	};

	private Integer calcRemainDays(Map<String, String> map)
			throws EvaluationException {
		return new Double(Math.floor(NumberUtils.toDouble(EvaluatorUtils
				.evaluator(REMAIN_DAYS, map)))).intValue();
	}

	private void setParameter(BigDecimal parameter) {
		this.parameter = parameter;
	}

	private void setAlertDays(Integer alertDays) {
		this.alertDays = alertDays;
	};

	private void setCaleDays(Integer caleDays) {
		this.caleDays = caleDays;
	};
	
	public static void main(String[] args1) {
		CalcAmountService service = (CalcAmountService) AppContext
		.getBean("calcAmountService");
		service.start();
	}

}
