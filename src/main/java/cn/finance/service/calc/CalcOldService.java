package cn.finance.service.calc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cn.finance.dao.AccountDao;
import cn.finance.dao.DiscountBackDao;
import cn.finance.dao.DiscountDao;
import cn.finance.dao.ExchangeDao;
import cn.finance.dao.ExpenseDao;
import cn.finance.dao.ServiceFeeDao;
import cn.finance.dao.ServiceFeeRateDao;
import cn.finance.dao.ServiceTypeDao;
import cn.finance.model.Account;
import cn.finance.model.Discount;
import cn.finance.model.DiscountBack;
import cn.finance.model.Expense;
import cn.finance.model.ServiceFee;
import cn.finance.model.ServiceFeeRate;
import cn.finance.model.ServiceType;
import cn.finance.util.AppContext;

@Transactional
public class CalcOldService {

	@Autowired
	public AccountDao accountDao;
	
	@Autowired
	public ExpenseDao expenseDao;

	@Autowired
	public DiscountDao discountDao;

	@Autowired
	public DiscountBackDao discountBackDao;
	
	@Autowired
	public ServiceTypeDao serviceTypeDao;
	
	@Autowired
	public ServiceFeeDao serviceFeeDao;
	
	@Autowired
	public ServiceFeeRateDao serviceFeeRateDao;
	
	@Autowired
	public ExchangeDao exchangeDao;

	/*
	 * 重算第一步
	 */
	public void reCalcCost(Integer accountId, Integer supplymentType, Date startDate, Date endDate){
		expenseDao.reCalcCost(accountId,supplymentType,startDate,endDate);
	}
	
	/*
	 * 重算第二步
	 */
	public void reCalcDiscount(Integer accountId, BigDecimal discount, Date startDate, Date endDate){
		expenseDao.reCalcDiscount(accountId,discount,startDate,endDate);
	}

	/*
	 * 重算第三步
	 */
	public void reCalcServiceFee(Integer accountId, BigDecimal serviceFee, Date startDate, Date endDate){
		expenseDao.reCalcServiceFee(accountId,serviceFee,startDate,endDate);
	}
	
	/*
	 * 重算第四步
	 */
	public void reCalcServiceFeeRate(Integer accountId, BigDecimal discount, Date startDate, Date endDate){
		expenseDao.reCalcServiceFeeRate(accountId,discount,startDate,endDate);
	}
	
	/*
	 * 重算第五步
	 */
	public void reCalcDiscountBack(Integer accountId, BigDecimal discountBack, Date startDate, Date endDate){
		expenseDao.reCalcDiscountBack(accountId,discountBack,startDate,endDate);
	}
	
	/*
	 * 重算第六步
	 */
	public void reCalcRevenue(Integer accountId, Date startDate, Date endDate){
		expenseDao.reCalcRevenue(accountId,startDate,endDate);
	}
	
	/*
	 * 重算第七步
	 */
	public void reCalcGrossProfit(Integer accountId, Date startDate, Date endDate){
		expenseDao.reCalcGrossProfit(accountId,startDate,endDate);
	}

	/*
	 * 重算一个账户1天的数据
	 */
	public void reCalcOneDay(Expense expense, Integer accountId){
		Date date = expense.getExpenseDate();
		if (expense!=null){
			Account account = accountDao.getAccountById(accountId);
			if (account.getExchange().getId()!=1){
				BigDecimal exchange = exchangeDao.getExchangeHistoryByDate(account.getExchange().getId(), date);
				expense.setManageMedia(expense.getOriginalManageMedia().multiply(exchange));
				expense.setNetAff(expense.getOriginalNetAff().multiply(exchange));
			} else {
				expense.setManageMedia(expense.getOriginalManageMedia());
				expense.setNetAff(expense.getOriginalNetAff());
			}
			Discount discount = discountDao.getDiscountByDate(date, accountId,1);
			ServiceType serviceType = serviceTypeDao.getServiceTypeByDate(date, accountId);
			DiscountBack discountBack = discountBackDao.getDiscountBackByDate(date, accountId);
			BigDecimal discountBackValue = new BigDecimal(0);
			BigDecimal darwinCost = new BigDecimal(0);
			if (serviceType!=null){
				if (serviceType.getType()==1){
					BigDecimal discountValue = new BigDecimal(0);
					if (discount!=null && discount.getTotalDiscount()!=null)
						discountValue = discount.getTotalDiscount().multiply(new BigDecimal(0.01));
					BigDecimal totalDiscountValue = new BigDecimal(0);
					//google
					if (account.getChannel().getMedia().getId() == 1){
						Discount affDiscount = discountDao.getDiscountByDate(date, accountId,2);
						BigDecimal affDiscountValue = new BigDecimal(0);
						if (affDiscount!=null && affDiscount.getTotalDiscount()!=null)
							affDiscountValue = affDiscount.getTotalDiscount().multiply(new BigDecimal(0.01));
						BigDecimal searchDiscountValue = expense.getManageMedia().multiply(discountValue);
						BigDecimal contentDiscountValue = expense.getNetAff().multiply(affDiscountValue);
						totalDiscountValue = searchDiscountValue.add(contentDiscountValue);
					} else {
						totalDiscountValue = discountValue.multiply(expense.getTotalManageMedia());
					}
					darwinCost = expense.getTotalManageMedia().subtract(totalDiscountValue);
					expense.setCost(expense.getTotalManageMedia());
					expense.setDarwinCost(darwinCost);
					expense.setDiscount(totalDiscountValue);
				} else {
					expense.setCost(new BigDecimal(0));
					expense.setDarwinCost(new BigDecimal(0));
					expense.setDiscount(new BigDecimal(0));
				}
				setServiceFee(expense, accountId, date);
				if (discountBack!=null)
					discountBackValue = discountBack.getValue().multiply(new BigDecimal(0.01)).multiply(expense.getTotalManageMedia());
				expense.setRevenue(expense.getCost().add(expense.getServiceFee()).add(expense.getServiceFeeAdjust()).subtract(discountBackValue));
				expense.setGrossProfit(expense.getRevenue().subtract(darwinCost));
				expenseDao.save(expense);
			}
		}
	}

	private void setServiceFee(Expense expense, Integer accountId, Date date) {
		BigDecimal serviceFeeValue = new BigDecimal(0);
		ServiceFeeRate searchServiceFeeRate = serviceFeeRateDao.getServiceFeeRateByDate(date, accountId, 1);
		if (searchServiceFeeRate!=null){
			serviceFeeValue = searchServiceFeeRate.getValue().multiply(expense.getTotalManageMedia()).multiply(new BigDecimal(0.01));
		} else {
			ServiceFee searchServiceFee = serviceFeeDao.getServiceFeeByDate(date, accountId, 1);
			if (searchServiceFee!=null){
				Calendar c = Calendar.getInstance(); 
				c.setTime(date);
				int oneMonthDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
				serviceFeeValue = searchServiceFee.getValue().divide(new BigDecimal(oneMonthDays),4,RoundingMode.HALF_UP);
			} else {
				searchServiceFee = serviceFeeDao.getServiceFeeByDate(date, accountId, 2);
				if (searchServiceFee!=null){
					Date start = searchServiceFee.getStartDate();
					Date end = searchServiceFee.getEndDate();
					long days = end.getTime()-start.getTime();
					days = days/1000/60/60/24 + 1;
					serviceFeeValue = searchServiceFee.getValue().divide(new BigDecimal(days),4,RoundingMode.HALF_UP);
				} 
			}
		}
		expense.setServiceFee(serviceFeeValue);
	}
	
	public void reCalc(Integer accountId){
		List <Expense>expenses = expenseDao.getExpenseByAccountId(accountId);
		for (Expense expense: expenses){
			reCalcOneDay(expense,accountId);
		}
	}
	
	public void updateExpenseStatus(Integer accountId){
		Account account = accountDao.getAccountById(accountId);
		Set<ServiceType> serviceTypes = account.getServiceTypes();
		if (serviceTypes!=null){
			expenseDao.deleteCalcByAccountId(accountId);
			for (ServiceType serviceType: serviceTypes){
				expenseDao.reCalcStatus(accountId, serviceType.getStartDate(), serviceType.getEndDate());
			}
		}
	}
	
	public static void main(String[] args1) {
		CalcOldService service = (CalcOldService) AppContext.getBean("calcOldService");
		for (int i=1;i<150;i++){
			try{
			service.updateExpenseStatus(i);
			}catch (Exception e){
				
			}
		}
	}
}
