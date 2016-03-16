package cn.finance.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.finance.dao.PmHistoryDao;
import cn.finance.model.PmHistory;
import cn.springside.modules.orm.Page;

@Transactional
public class PmHistoryService {

	private PmHistoryDao pmHistoryDao;
	
	public PmHistory createPmHistory(){
		return pmHistoryDao.createPmHistory();
	}
	
	public void save(PmHistory pmHistory){
		pmHistoryDao.save(pmHistory);
	}
	
	public void delete(PmHistory pmHistory){
		pmHistoryDao.delete(pmHistory);
	}

	public PmHistory getPmHistoryById(Integer id) {
		return pmHistoryDao.getPmHistoryById(id);
	}
	
	public List<PmHistory> getAll(Page<PmHistory> page,boolean hastotal) {
		return pmHistoryDao.getAll(page,hastotal);
	}

	public int getTotalCount(Page<PmHistory>page) {
		return pmHistoryDao.getTotalCount(page); 
	}
	
	public boolean hasRecordByBetweenDay(String startDate,String endDate,Integer id,Integer accountId) {
		if ("".equals(endDate))
			endDate = "3000-01-01";
		if (pmHistoryDao.getCountByBetweenDay(startDate,endDate,id,accountId)==0){
			return false;
		} else {
			return true;
		}
	}
	
	
	
	public PmHistoryDao getPmHistoryDao() {
		return pmHistoryDao;
	}

	public void setPmHistoryDao(PmHistoryDao pmHistoryDao) {
		this.pmHistoryDao = pmHistoryDao;
	}

}
