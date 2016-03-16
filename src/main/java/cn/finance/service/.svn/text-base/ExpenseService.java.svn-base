package cn.finance.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.finance.dao.ExpenseDao;
import cn.finance.dao.ServiceTypeDao;
import cn.finance.model.Account;
import cn.finance.model.Expense;
import cn.finance.model.ServiceType;
import cn.finance.model.bean.CheckBean;
import cn.finance.model.bean.ReportBean;
import cn.finance.model.bean.ServiceAdjustBean;
import cn.finance.util.AppContext;
import cn.springside.modules.orm.Page;

public class ExpenseService {

	private ExpenseDao expenseDao;

	private ServiceTypeDao serviceTypeDao;
	
	@Transactional
	public void saveTransactional(Expense expense) {
		expenseDao.save(expense);
	}

	public void save(Expense expense) {
		expenseDao.save(expense);
	}
	
	public ExpenseDao getExpenseDao() {
		return expenseDao;
	}

	public ReportBean getSumReportByProject(Page<ReportBean> page) {
		return expenseDao.getSumReportByProject(page);
	}
	
	public List<ReportBean> getReportByProject(Page<ReportBean> page,boolean hasTotal) {
		return expenseDao.getReportByProject(page,hasTotal);
	}
	
	public int getReportByProjectCount(Page<ReportBean> page) {
		return expenseDao.getReportByProjectCount(page);
	}
	
	public ReportBean getSumReportByAccount(Page<ReportBean> page) {
		return expenseDao.getSumReportByAccount(page);
	}
	
	public List<ReportBean> getReportByAccountId(Page<ReportBean> page,boolean hasTotal) {
		return expenseDao.getReportByAccountId(page,hasTotal);
	}
	
	public int getReportCountByAccountId(Page<ReportBean> page) {
		return expenseDao.getReportCountByAccountId(page);
	}
	
	public List<Expense> getExpenseByProjectAndId(Integer channelId, Integer projectId, Date date) {
		return expenseDao.getExpenseByProjectAndId(channelId,projectId,date);
	}
	
	public List<ServiceAdjustBean> getDaysByCondition(Integer channelId, Integer projectId, String startDate, String endDate) {
		return expenseDao.getDaysByCondition(channelId,projectId,startDate,endDate);
	}
	
	public boolean hasExpenseByIdDate(Integer id, Date date){
		Expense expense = expenseDao.getExpenseByDateAndId(id, date);
		if (expense==null){
			return false;
		} else {
			return true;
		}
	}
	
	public Expense getExpenseByDateAndId(Integer id, Date date){
		return expenseDao.getExpenseByDateAndId(id, date);
	}
	
	public void setExpenseDao(ExpenseDao expenseDao) {
		this.expenseDao = expenseDao;
	}
	
	public Expense createExpense(){
		return expenseDao.createExpense();
	}
	
	public ServiceTypeDao getServiceTypeDao() {
		return serviceTypeDao;
	}

	public void setServiceTypeDao(ServiceTypeDao serviceTypeDao) {
		this.serviceTypeDao = serviceTypeDao;
	}
	
	/**
	 * 检查时
	 * @return
	 */
	public List<CheckBean> checkExpense(){
		Page<ServiceType> page = new Page<ServiceType>();
		List<ServiceType> serviceTypes = serviceTypeDao.getAll(page, true);
		List<CheckBean> checks = new ArrayList<CheckBean>();
		for (ServiceType serviceType : serviceTypes){
			Date startDate = serviceType.getStartDate();
			Date endDate = serviceType.getEndDate();
			Date date = new Date();
			int days = 1;
			if (date.getTime()<endDate.getTime()){
				endDate = date;
				days = 0;
			}
			days +=(int)((endDate.getTime()-startDate.getTime())/1000/24/60/60);
			int dbDays = expenseDao.getCountByIdDate(startDate, endDate, serviceType.getAccount().getId());
			if (dbDays != days){
				CheckBean check = new CheckBean();
				check.setAccountId(serviceType.getAccount().getId());
				check.setName(serviceType.getAccount().getName());
				check.setChannel(serviceType.getAccount().getChannel().getName());
				check.setMedia(serviceType.getAccount().getChannel().getMedia().getName());
				check.setProject(serviceType.getAccount().getProject().getName());
				check.setStartDate(startDate);
				check.setEndDate(endDate);
				check.setDays(days);
				check.setDbDays(dbDays);
				checks.add(check);
			}
		}
		return checks;
	}
	
}
