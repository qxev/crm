package cn.finance.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import cn.finance.dao.ContractDao;
import cn.finance.model.Contract;
import cn.finance.model.ContractTemplate;
import cn.finance.model.Version;
import cn.springside.modules.orm.Page;

@Transactional
public class ContractService {

	private ContractDao contractDao;

	@Transactional(readOnly = true)
	public List<Contract> getAll() {
		return contractDao.getAll();
	}
	
	@Transactional(readOnly = true)
	public List<Contract> getAll(Page<Contract>page) {
		return contractDao.getAll(page);
	}
	
	@Transactional
	public List<Map<String, Object>> getExcel(Page<Contract>page) {
		List<Contract> contracts = contractDao.getAll(page);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (Contract contract : contracts) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", contract.getCode());
			map.put("clientName", contract.getContractProject().getContractClient().getName());
			map.put("projectName", contract.getContractProject().getName());
			map.put("industryName", contract.getContractProject().getIndustry().getName());
			map.put("createUser", contract.getCreateUser().getName());
			map.put("bdName", contract.getContractProject().getBd().getName());
			map.put("createAt", contract.getCreateAt());
			map.put("status", contract.getStatusDisplay());
			list.add(map);
		}
		return list;
	}
	
	@Transactional(readOnly = true)
	public int getTotalCount(Page<Contract>page) {
		return contractDao.getTotalCount(page);
	}

	public Contract getContractById(Integer id) {
		return contractDao.getContractById(id);
	}
	
	public void save(Contract contract) {
		contractDao.save(contract);
	}
	
	public void saveVersion(Version version) {
		contractDao.saveVersion(version);
	}
	
	public Contract create(){
		return contractDao.create();
	}

	public ContractDao getContractDao() {
		return contractDao;
	}

	public void setContractDao(ContractDao contractDao) {
		this.contractDao = contractDao;
	}

	public void saveTemplate(ContractTemplate contractTemplate) {
		contractDao.saveTemplate(contractTemplate);
	}
	
	@Transactional(readOnly = true)
	public int getTemplateCount(Page<ContractTemplate> page) {
		return contractDao.getTemplateCount(page);
	}
	
	public ContractTemplate getContractTemplateById(Integer id) {
		return contractDao.getContractTemplateById(id);
	}
	
	@Transactional(readOnly = true)
	public List<ContractTemplate> getAllContractTemplate(Page<ContractTemplate> page) {
		return contractDao.getAllTemplate(page);
	}
	
	public List<Version> getVersionByContractId(Integer id){
		return contractDao.getVersionByContractId(id);
	}
	
	public Version getVersionById(Integer id) {
		return contractDao.getVersionById(id);
	}
	
	public Contract findDetailById(Integer id) {
		return contractDao.findDetailById(id);
	}
}
