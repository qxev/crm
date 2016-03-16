package cn.finance.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.finance.dao.ServiceTypeDao;
import cn.finance.model.ServiceType;
import cn.springside.modules.orm.Page;

@Transactional
public class ServiceTypeService {

	private ServiceTypeDao serviceTypeDao;
	
	public ServiceType createServiceType(){
		return serviceTypeDao.createServiceType();
	}
	
	public void save(ServiceType serviceType){
		serviceTypeDao.save(serviceType);
	}
	
	public void delete(ServiceType serviceType){
		serviceTypeDao.delete(serviceType);
	}

	public ServiceType getServiceTypeById(Integer id) {
		return serviceTypeDao.getServiceTypeById(id);
	}
	
	public boolean hasRecordByBetweenDay(String startDate,String endDate,Integer id, Integer accountId) {
		if ("".equals(endDate))
			endDate = "3000-01-01";
		if (serviceTypeDao.getCountByBetweenDay(startDate,endDate,id,accountId)==0){
			return false;
		} else {
			return true;
		}
	}
	
	public List<ServiceType> getAll(Page<ServiceType> page,boolean hasTotal) {
		return serviceTypeDao.getAll(page,hasTotal);
	}

	public int getTotalCount(Page<ServiceType>page) {
		return serviceTypeDao.getTotalCount(page); 
	}
	
	public ServiceTypeDao getServiceTypeDao() {
		return serviceTypeDao;
	}

	public void setServiceTypeDao(ServiceTypeDao serviceTypeDao) {
		this.serviceTypeDao = serviceTypeDao;
	}

}
