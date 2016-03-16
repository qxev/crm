package cn.finance.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.finance.dao.ContractProjectDao;
import cn.finance.model.ContractProject;
import cn.finance.model.DarwinName;
import cn.springside.modules.orm.Page;

@Transactional
public class ContractProjectService {

	private ContractProjectDao contractProjectDao;

	public ContractProjectDao getContractProjectDao() {
		return contractProjectDao;
	}

	public void setContractProjectDao(ContractProjectDao contractProjectDao) {
		this.contractProjectDao = contractProjectDao;
	}

	@Transactional(readOnly = true)
	public List<ContractProject> getAllByClientId(Integer clientId) {
		return contractProjectDao.getAllByClientId(clientId);
	}

	@Transactional(readOnly = true)
	public List<ContractProject> getAll() {
		return contractProjectDao.getAll();
	}
	
	@Transactional(readOnly = true)
	public List<DarwinName> getAllDarwinName() {
		return contractProjectDao.getAllDarwinName();
	}
	
	public Integer getAllStatusByClientId(Integer clientId) {
		return contractProjectDao.getAllStatusByClientId(clientId);
	}

	@Transactional(readOnly = true)
	public List<ContractProject> getAll(Page<ContractProject> page, boolean hasTotal) {
		return contractProjectDao.getAll(page,hasTotal);
	}

	@Transactional(readOnly = true)
	public int getTotalCount(Page<ContractProject>page) {
		return contractProjectDao.getTotalCount(page);
	}
	
	public ContractProject getContractProjectById(Integer id) {
		return contractProjectDao.getContractProjectById(id);
	}
	
	public DarwinName getDarwinNameById(Integer id) {
		return contractProjectDao.getDarwinNameById(id);
	}
	
	@Transactional
	public void save(ContractProject contractProject){
		contractProjectDao.save(contractProject);
	}
	
	@Transactional(readOnly = true)
	public List<ContractProject> getAllByContractProjectName(String name){
		return contractProjectDao.getAllByContractProjectName(name);
	}
	
	public ContractProject createContractProject(){
		return contractProjectDao.createContractProject();
	}
}
