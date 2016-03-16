package cn.finance.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.finance.dao.ServiceFeeRateDao;
import cn.finance.model.ServiceFeeRate;
import cn.springside.modules.orm.Page;

@Transactional
public class ServiceFeeRateService {

	private ServiceFeeRateDao serviceFeeRateDao;
	
	public ServiceFeeRate createServiceFeeRate(){
		return serviceFeeRateDao.createServiceFeeRate();
	}
	
	public void save(ServiceFeeRate serviceFeeRate){
		serviceFeeRateDao.save(serviceFeeRate);
	}
	
	public void delete(ServiceFeeRate serviceFeeRate){
		serviceFeeRateDao.delete(serviceFeeRate);
	}

	public ServiceFeeRate getServiceFeeRateById(Integer id) {
		return serviceFeeRateDao.getServiceFeeRateById(id);
	}
	
	public boolean hasRecordByBetweenDay(String startDate,String endDate,Integer id,Integer accountId, Integer type) {
		if ("".equals(endDate))
			endDate = "3000-01-01";
		if (serviceFeeRateDao.getCountByBetweenDay(startDate,endDate,id,accountId,type)==0){
			return false;
		} else {
			return true;
		}
	}
	
	public List<ServiceFeeRate> getAll(Page<ServiceFeeRate> page, boolean hasTotal) {
		return serviceFeeRateDao.getAll(page,hasTotal);
	}

	public int getTotalCount(Page<ServiceFeeRate>page) {
		return serviceFeeRateDao.getTotalCount(page); 
	}
	
	public ServiceFeeRateDao getServiceFeeRateDao() {
		return serviceFeeRateDao;
	}

	public void setServiceFeeRateDao(ServiceFeeRateDao serviceFeeRateDao) {
		this.serviceFeeRateDao = serviceFeeRateDao;
	}

}
