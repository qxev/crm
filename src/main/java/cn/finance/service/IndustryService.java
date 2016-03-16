package cn.finance.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.finance.dao.IndustryDao;
import cn.finance.model.Industry;
import cn.springside.modules.orm.Page;
@Transactional
public class IndustryService {

	private IndustryDao industryDao;

	@Transactional(readOnly = true)
	public List<Industry> getAll() {
		return industryDao.getAll();
	}
	
	@Transactional(readOnly = true)
	public List<Industry> getAll(Page<Industry> page) {
		return industryDao.getAll(page);
	}
	
	public Industry getIndustryByName(String name, Integer id) {
		return industryDao.getIndustryByName(name,id);
	}
	
	public Industry getIndustryById(Integer id) {
		return industryDao.getIndustryById(id);
	}
	
	@Transactional(readOnly = true)
	public int getTotalCount(Page<Industry> page) {
		return industryDao.getTotalCount(page);
	}
	
	public void save(Industry industry) {
		industryDao.save(industry);
	}
	
	
	public IndustryDao getIndustryDao() {
		return industryDao;
	}

	public void setIndustryDao(IndustryDao industryDao) {
		this.industryDao = industryDao;
	}

}
