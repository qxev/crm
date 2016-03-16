package cn.finance.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.finance.dao.BusinessTypeDao;
import cn.finance.model.BusinessType;

@Transactional
public class BusinessTypeService {

	private BusinessTypeDao businessTypeDao;

	@Transactional(readOnly = true)
	public List<BusinessType> getAll() {
		return businessTypeDao.getAll();
	}

	public BusinessType getBusinessTypeById(Integer id) {
		return businessTypeDao.getBusinessTypeById(id);
	}
	
	public void save(BusinessType businessType) {
		businessTypeDao.save(businessType);
	}

	public BusinessTypeDao getBusinessTypeDao() {
		return businessTypeDao;
	}

	public void setBusinessTypeDao(BusinessTypeDao businessTypeDao) {
		this.businessTypeDao = businessTypeDao;
	}

}
