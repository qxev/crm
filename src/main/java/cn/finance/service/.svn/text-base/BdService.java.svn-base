package cn.finance.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.finance.dao.BdDao;
import cn.finance.model.Bd;
import cn.finance.model.ContractTemplate;
import cn.springside.modules.orm.Page;
@Transactional
public class BdService {

	private BdDao bdDao;

	@Transactional(readOnly = true)
	public List<Bd> getAll() {
		return bdDao.getAll();
	}

	@Transactional(readOnly = true)
	public List<Bd> getAll(Page<Bd> page) {
		return bdDao.getAll(page);
	}
	
	public Bd getBdByName(String name, Integer id) {
		return bdDao.getBdByName(name,id);
	}
	
	public Bd getBdById(Integer id) {
		return bdDao.getBdById(id);
	}

	
	public List<Bd> getBdByIds(String ids) {
		return bdDao.getBdByIds(ids);
	}
	
	@Transactional(readOnly = true)
	public int getTotalCount(Page<Bd> page) {
		return bdDao.getTotalCount(page);
	}
	
	public void save(Bd bd) {
		bdDao.save(bd);
	}

	
	public BdDao getBdDao() {
		return bdDao;
	}

	public void setBdDao(BdDao bdDao) {
		this.bdDao = bdDao;
	}

}
