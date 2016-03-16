package cn.finance.service;


import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.finance.dao.FeeAdjustDao;
import cn.finance.model.ServiceFeeAdjust;
import cn.springside.modules.orm.Page;
@Transactional
public class FeeAdjustService {

	private FeeAdjustDao feeAdjustDao;
	
	@Transactional(readOnly = true)
	public List<ServiceFeeAdjust> getAll(Page<ServiceFeeAdjust> page,boolean hasTotal) {
		return feeAdjustDao.getAll(page,hasTotal);
	}
	
	public void save(ServiceFeeAdjust serviceFeeAdjust) {
		feeAdjustDao.save(serviceFeeAdjust);
	}
	
	public boolean hasRecordByBetweenDay(String startDate,String endDate,Integer id) {
		if ("".equals(endDate))
			endDate = "3000-01-01";
		if (feeAdjustDao.getCountByBetweenDay(startDate,endDate,id)==0){
			return false;
		} else {
			return true;
		}
	}
	
	public int getTotalCount(Page<ServiceFeeAdjust>page) {
		return feeAdjustDao.getTotalCount(page); 
	}
	
	public FeeAdjustDao getFeeAdjustDao() {
		return feeAdjustDao;
	}

	public void setFeeAdjustDao(FeeAdjustDao feeAdjustDao) {
		this.feeAdjustDao = feeAdjustDao;
	}

}
