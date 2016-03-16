package cn.finance.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.finance.dao.ServiceFeeBaseDao;
import cn.finance.model.ServiceFeeBase;
import cn.springside.modules.orm.Page;

@Transactional
public class ServiceFeeBaseService {

	private ServiceFeeBaseDao serviceFeeBaseDao;
	
	public ServiceFeeBase createServiceFeeBase(){
		return serviceFeeBaseDao.createServiceFeeBase();
	}
	
	public void save(ServiceFeeBase serviceFeeBase){
		serviceFeeBaseDao.save(serviceFeeBase);
	}

	public ServiceFeeBase getServiceFeeBaseById(Integer id) {
		return serviceFeeBaseDao.getServiceFeeBaseById(id);
	}
	
	public boolean hasRecordByBetweenDay(String startDate,String endDate,Integer id, Integer accountId) {
		if ("".equals(endDate))
			endDate = "3000-01-01";
		if (serviceFeeBaseDao.getCountByBetweenDay(startDate,endDate,id, accountId)==0){
			return false;
		} else {
			return true;
		}
	}
	
	public List<ServiceFeeBase> getAll(Page<ServiceFeeBase> page, boolean hasTotal) {
		return serviceFeeBaseDao.getAll(page,hasTotal);
	}

	public int getTotalCount(Page<ServiceFeeBase>page) {
		return serviceFeeBaseDao.getTotalCount(page); 
	}
	
	public ServiceFeeBaseDao getServiceFeeBaseDao() {
		return serviceFeeBaseDao;
	}

	public void setServiceFeeBaseDao(ServiceFeeBaseDao serviceFeeBaseDao) {
		this.serviceFeeBaseDao = serviceFeeBaseDao;
	}

}
