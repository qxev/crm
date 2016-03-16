package cn.finance.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.finance.dao.DiscountBackDao;
import cn.finance.model.DiscountBack;
import cn.springside.modules.orm.Page;

@Transactional
public class DiscountBackService {

	private DiscountBackDao discountBackDao;
	
	public DiscountBack createDiscountBack(){
		return discountBackDao.createDiscountBack();
	}
	
	public void save(DiscountBack discountBack){
		discountBackDao.save(discountBack);
	}
	
	public void delete(DiscountBack discountBack){
		discountBackDao.delete(discountBack);
	}

	public DiscountBack getDiscountBackById(Integer id) {
		return discountBackDao.getDiscountBackById(id);
	}
	
	public boolean hasRecordByBetweenDay(String startDate,String endDate,Integer id, Integer accountId) {
		if ("".equals(endDate))
			endDate = "3000-01-01";
		if (discountBackDao.getCountByBetweenDay(startDate,endDate,id,accountId)==0){
			return false;
		} else {
			return true;
		}
	}
	
	public List<DiscountBack> getAll(Page<DiscountBack> page, boolean hasTotal) {
		return discountBackDao.getAll(page,hasTotal);
	}

	public int getTotalCount(Page<DiscountBack>page) {
		return discountBackDao.getTotalCount(page); 
	}
	
	public DiscountBackDao getDiscountBackDao() {
		return discountBackDao;
	}

	public void setDiscountBackDao(DiscountBackDao discountBackDao) {
		this.discountBackDao = discountBackDao;
	}

}
