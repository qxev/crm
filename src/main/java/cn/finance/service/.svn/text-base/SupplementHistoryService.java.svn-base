package cn.finance.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.finance.dao.AccountDao;
import cn.finance.dao.SupplementHistoryDao;
import cn.finance.model.Account;
import cn.finance.model.SupplementHistory;
import cn.finance.model.bean.SeBean;
import cn.finance.util.DateUtil;
import cn.springside.modules.orm.Page;

@Transactional
public class SupplementHistoryService {

	private SupplementHistoryDao supplementHistoryDao;
	
	private AccountDao accountDao;
	
	@Transactional(readOnly = true)
	public List<SupplementHistory> getAll(Page<SupplementHistory> page,boolean hasTotal) {
		return supplementHistoryDao.getAll(page,hasTotal,false);
	}
	
	@Transactional(readOnly = true)
	public List<SupplementHistory> getAllAccountAdd(Page<SupplementHistory> page,boolean hasTotal) {
		return supplementHistoryDao.getAll(page,hasTotal,true);
	}
	
	@Transactional(readOnly = true)
	public BigDecimal getSum(Page<SupplementHistory> page) {
		return supplementHistoryDao.getSum(page);
	}
	
	@Transactional(readOnly = true)
	public List<SupplementHistory> getAllPay(Page<SupplementHistory> page, boolean hasTotal) {
		return supplementHistoryDao.getAllPay(page, hasTotal);
	}
	
	@Transactional(readOnly = true)
	public int getTotalCount(Page<SupplementHistory>page) {
		return supplementHistoryDao.getTotalCount(page,false);
	}
	
	@Transactional(readOnly = true)
	public int getTotalCountAdd(Page<SupplementHistory>page) {
		return supplementHistoryDao.getTotalCount(page,true);
	}
	
	@Transactional(readOnly = true)
	public BigDecimal getSumPay(Page<SupplementHistory> page) {
		return supplementHistoryDao.getSumPay(page);
	}
	
	@Transactional(readOnly = true)
	public int getTotalPayCount(Page<SupplementHistory>page) {
		return supplementHistoryDao.getTotalPayCount(page);
	}
	
	@Transactional(readOnly = true)
	public List<SupplementHistory> getAllRepayment(Page<SupplementHistory> page,boolean hasTotal) {
		return supplementHistoryDao.getAllRepayment(page,hasTotal);
	}
	
	@Transactional(readOnly = true)
	public BigDecimal getSumRepayment(Page<SupplementHistory> page) {
		return supplementHistoryDao.getSumRepayment(page);
	}
	
	@Transactional(readOnly = true)
	public BigDecimal getThisMonthSumRepayment() {
		Calendar startDate = Calendar.getInstance();      
		startDate.set(Calendar.DAY_OF_MONTH, startDate.getActualMinimum(Calendar.DAY_OF_MONTH)); 
		Calendar endDate = Calendar.getInstance();      
		endDate.set(Calendar.DAY_OF_MONTH, endDate.getActualMaximum(Calendar.DAY_OF_MONTH)); 
		return supplementHistoryDao.getSumRepaymentBetween(startDate.getTime(),endDate.getTime());
	}
	
	@Transactional(readOnly = true)
	public BigDecimal getNextMonthSumRepayment() {
		Calendar startDate = Calendar.getInstance();
		startDate.add(Calendar.MONTH, 1);
		startDate.set(Calendar.DAY_OF_MONTH, startDate.getActualMinimum(Calendar.DAY_OF_MONTH)); 
		Calendar endDate = Calendar.getInstance();      
		endDate.add(Calendar.MONTH, 1);
		endDate.set(Calendar.DAY_OF_MONTH, endDate.getActualMaximum(Calendar.DAY_OF_MONTH)); 
		return supplementHistoryDao.getSumRepaymentBetween(startDate.getTime(),endDate.getTime());
	}
	
	@Transactional(readOnly = true)
	public BigDecimal getNextMonth2SumRepayment() {
		Calendar startDate = Calendar.getInstance();      
		startDate.add(Calendar.MONTH, 2);
		startDate.set(Calendar.DAY_OF_MONTH, startDate.getActualMinimum(Calendar.DAY_OF_MONTH)); 
		Calendar endDate = Calendar.getInstance();      
		endDate.add(Calendar.MONTH, 2);
		endDate.set(Calendar.DAY_OF_MONTH, endDate.getActualMaximum(Calendar.DAY_OF_MONTH)); 
		return supplementHistoryDao.getSumRepaymentBetween(startDate.getTime(),endDate.getTime());
	}
	
	@Transactional(readOnly = true)
	public BigDecimal getNextMonth3SumRepayment() {
		Calendar startDate = Calendar.getInstance();      
		startDate.add(Calendar.MONTH, 3);
		startDate.set(Calendar.DAY_OF_MONTH, startDate.getActualMinimum(Calendar.DAY_OF_MONTH)); 
		Calendar endDate = Calendar.getInstance();      
		endDate.add(Calendar.MONTH, 3);
		endDate.set(Calendar.DAY_OF_MONTH, endDate.getActualMaximum(Calendar.DAY_OF_MONTH)); 
		return supplementHistoryDao.getSumRepaymentBetween(startDate.getTime(),endDate.getTime());
	}
	
	@Transactional(readOnly = true)
	public int getAllRepaymentCount(Page<SupplementHistory>page) {
		return supplementHistoryDao.getAllRepaymentCount(page);
	}
	
	@Transactional(readOnly = true)
	public List<SeBean> getAllSeAmountHistoryByChannel(Page<SeBean> page, boolean hasTotal) {
		return supplementHistoryDao.getAllSeAmountHistoryByChannel(page, hasTotal);
	}
	
	@Transactional(readOnly = true)
	public BigDecimal getSumSeAmountHistoryByChannel(Page<SeBean> page) {
		return supplementHistoryDao.getSumSeAmountHistoryByChannel(page);
	}
	
	@Transactional(readOnly = true)
	public int getTotalSeAmountHistoryCountByChannel(Page<SeBean> page) {
		return supplementHistoryDao.getTotalSeAmountHistoryCountByChannel(page);
	}
	
	@Transactional(readOnly = true)
	public List<SeBean> getAllSeAmountHistoryByProject(Page<SeBean> page, boolean hasTotal) {
		return supplementHistoryDao.getAllSeAmountHistoryByProject(page, hasTotal);
	}
	
	@Transactional(readOnly = true)
	public BigDecimal getSumSeAmountHistoryByProject(Page<SeBean> page) {
		return supplementHistoryDao.getSumSeAmountHistoryByProject(page);
	}
	
	@Transactional(readOnly = true)
	public int getTotalSeAmountHistoryCountByProject(Page<SeBean> page) {
		return supplementHistoryDao.getTotalSeAmountHistoryCountByProject(page);
	}
	
	@Transactional(readOnly = true)
	public List<SeBean> getAllSeAmountHistoryByAccount(Page<SeBean> page, boolean hasTotal) {
		return supplementHistoryDao.getAllSeAmountHistoryByAccount(page, hasTotal);
	}
	
	@Transactional(readOnly = true)
	public BigDecimal getSumSeAmountHistoryByAccount(Page<SeBean> page) {
		return supplementHistoryDao.getSumSeAmountHistoryByAccount(page);
	}
	
	@Transactional(readOnly = true)
	public int getTotalSeAmountHistoryCountByAccount(Page<SeBean> page) {
		return supplementHistoryDao.getTotalSeAmountHistoryCountByAccount(page);
	}

	@Transactional(readOnly = true)
	public SupplementHistory find(Integer id) {
		return supplementHistoryDao.findSupplementHistory(id);
	}
	
	@Transactional
	public void editSupplementHistory(Integer supplementId, SupplementHistory supplementHistory){
		SupplementHistory saveSupplementHistory = getSupplementHistoryById(supplementId);
		BigDecimal changeValue = supplementHistory.getTotalAmount().subtract(saveSupplementHistory.getTotalAmount());
		saveSupplementHistory.setPayAmount(supplementHistory.getPayAmount());
		saveSupplementHistory.setCounterPoint(supplementHistory.getCounterPoint());
		saveSupplementHistory.setTotalAmount(supplementHistory.getTotalAmount());
		saveSupplementHistory.setSupplementDate(supplementHistory.getSupplementDate());
		saveSupplementHistory.setNextSupplementDate(supplementHistory.getNextSupplementDate());
		if (saveSupplementHistory.getType()==2){
			saveSupplementHistory.setRepayAmount(saveSupplementHistory.getPayAmount());
			saveSupplementHistory.setPayAmount(new BigDecimal(0));
		}
		if (!changeValue.equals(new BigDecimal(0))){
			Account account = saveSupplementHistory.getAccount();
			BigDecimal totalBalance = account.getTotalBalance();
			if (totalBalance == null) 
				totalBalance = new BigDecimal(0);
			account.setTotalBalance(totalBalance.add(changeValue));
		}
		save(saveSupplementHistory);
	}
	
	@Transactional
	public void addRepay(Integer supplementHistoryId, Integer accountId, String supplementDate,SupplementHistory supplementHistory){
		SupplementHistory oldHistory = getSupplementHistoryById(supplementHistoryId);
		if (supplementHistory.getPayAmount().compareTo(BigDecimal.ZERO)==1){
			oldHistory.setRepayAmount(oldHistory.getRepayAmount().subtract(supplementHistory.getPayAmount()));
			save(oldHistory);
		}
		
		Account account = accountDao.getAccountById(accountId);
		supplementHistory.setCreateAt(new Date());
		supplementHistory.setAccount(account);
		supplementHistory.setType(4);
		supplementHistory.setTotalAmount(supplementHistory.getPayAmount());
		supplementHistory.setSupplementDate(DateUtil.stringToDate(supplementDate));
		save(supplementHistory);
	}
	
	@Transactional
	public void save(SupplementHistory supplementHistory) {
		supplementHistoryDao.save(supplementHistory);
	}
	
	public SupplementHistoryDao getSupplementHistoryDao() {
		return supplementHistoryDao;
	}

	public void setSupplementHistoryDao(SupplementHistoryDao supplementHistoryDao) {
		this.supplementHistoryDao = supplementHistoryDao;
	}
	
	public SupplementHistory getSupplementHistoryById(Integer id) {
		return supplementHistoryDao.getSupplementHistoryById(id);
	}
	
	public void deleteSupplementHistory(SupplementHistory supplementHistory) {
		supplementHistoryDao.deleteSupplementHistoryById(supplementHistory);
	}
	
	public AccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}
}
