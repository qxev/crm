package cn.finance.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.finance.dao.ServiceFeeDao;
import cn.finance.model.ServiceFee;
import cn.springside.modules.orm.Page;

@Transactional
public class ServiceFeeService {

	private ServiceFeeDao serviceFeeDao;
	
	public ServiceFee createServiceFee(){
		return serviceFeeDao.createServiceFee();
	}
	
	public void save(ServiceFee serviceFee){
		serviceFeeDao.save(serviceFee);
	}
	
	public void delete(ServiceFee serviceFee){
		serviceFeeDao.delete(serviceFee);
	}

	public ServiceFee getServiceFeeById(Integer id) {
		return serviceFeeDao.getServiceFeeById(id);
	}
	
	/**
	 * 查询有没有在此期间重复的记录
	 * @param startDate 
	 * @param endDate
	 * @param id 去除当前这条记录
	 * @param accountId 账号id
	 * @param type 1 搜索 2 网盟
	 * @return 有没有
	 */
	public boolean hasRecordByBetweenDay(String startDate,String endDate,Integer id, Integer accountId) {
		if ("".equals(endDate))
			endDate = "3000-01-01";
		if (serviceFeeDao.getCountByBetweenDay(startDate,endDate,id,accountId)==0){
			return false;
		} else {
			return true;
		}
	}
	
	public List<ServiceFee> getAll(Page<ServiceFee> page,boolean hasTotal) {
		return serviceFeeDao.getAll(page,hasTotal);
	}

	public int getTotalCount(Page<ServiceFee>page) {
		return serviceFeeDao.getTotalCount(page); 
	}
	
	public ServiceFeeDao getServiceFeeDao() {
		return serviceFeeDao;
	}

	public void setServiceFeeDao(ServiceFeeDao serviceFeeDao) {
		this.serviceFeeDao = serviceFeeDao;
	}

}
