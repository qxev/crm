package cn.finance.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.finance.dao.PmDao;
import cn.finance.model.Pm;
import cn.springside.modules.orm.Page;
@Transactional
public class PmService {

	private PmDao pmDao;

	@Transactional(readOnly = true)
	public List<Pm> getByBusinessType(Integer type) {
		return pmDao.getByBusinessType(type);
	}
	
	@Transactional(readOnly = true)
	public List<Pm> getAll() {
		return pmDao.getAll();
	}
	
	@Transactional(readOnly = true)
	public List<Pm> getAll(Page<Pm> page) {
		return pmDao.getAll(page);
	}
	
	public Pm getPmByName(String name, Integer id) {
		return pmDao.getPmByName(name, id);
	}
	
	public Pm getPmById(Integer id) {
		return pmDao.getPmById(id);
	}
	
	public Pm getDetailById(Integer id) {
		return pmDao.getDetailById(id);
	}

	public List<Pm> getPmByIds(String ids) {
		return pmDao.getPmByIds(ids);
	}

	@Transactional(readOnly = true)
	public int getTotalCount(Page<Pm> page) {
		return pmDao.getTotalCount(page);
	}
	
	public void save(Pm pm) {
		pmDao.save(pm);
	}
	
	public PmDao getPmDao() {
		return pmDao;
	}

	public void setPmDao(PmDao pmDao) {
		this.pmDao = pmDao;
	}

}
