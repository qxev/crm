package cn.finance.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.finance.dao.DiscountDao;
import cn.finance.model.Discount;
import cn.springside.modules.orm.Page;

@Transactional
public class DiscountService {

	private DiscountDao discountDao;
	
	public Discount createDiscount(){
		return discountDao.createDiscount();
	}
	
	public void save(Discount discount){
		discountDao.save(discount);
	}
	
	public void delete(Discount discount){
		discountDao.delete(discount);
	}

	public Discount getDiscountById(Integer id) {
		return discountDao.getDiscountById(id);
	}
	
	public boolean hasRecordByBetweenDay(String startDate,String endDate,Integer id, Integer accountId, Integer type) {
		if ("".equals(endDate))
			endDate = "3000-01-01";
		if (discountDao.getCountByBetweenDay(startDate,endDate,id,accountId, type)==0){
			return false;
		} else {
			return true;
		}
	}
	
	public DiscountDao getDiscountDao() {
		return discountDao;
	}

	public void setDiscountDao(DiscountDao discountDao) {
		this.discountDao = discountDao;
	}

	public List<Discount> getAll(Page<Discount> page,boolean hasTotal) {
		return discountDao.getAll(page,hasTotal);
	}

	public int getTotalCount(Page<Discount>page) {
		return discountDao.getTotalCount(page);
	}

}
