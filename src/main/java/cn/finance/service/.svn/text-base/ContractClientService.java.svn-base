package cn.finance.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.finance.dao.ContractClientDao;
import cn.finance.model.ChargePerson;
import cn.finance.model.ContractClient;
import cn.finance.model.SearchEngine;
import cn.springside.modules.orm.Page;

@Transactional
public class ContractClientService {

	private ContractClientDao contractClientDao;

	@Transactional(readOnly = true)
	public List<ContractClient> getAll(Page<ContractClient> page, boolean hasTotal) {
		return contractClientDao.getAll(page,hasTotal);
	}
	
	@Transactional(readOnly = true)
	public List<ContractClient> getAll() {
		return contractClientDao.getAll();
	}
	
	@Transactional(readOnly = true)
	public List<SearchEngine> getAllSearchEngine() {
		return contractClientDao.getAllSearchEngine();
	}
	
	@Transactional(readOnly = true)
	public List<SearchEngine> getSearchEngineByIds(String ids) {
		return contractClientDao.getSearchEngineByIds(ids);
	}
	
	@Transactional(readOnly = true)
	public int getTotalCount(Page<ContractClient>page) {
		return contractClientDao.getTotalCount(page);
	}
	
	public ContractClientDao getContractClientDao() {
		return contractClientDao;
	}

	public void setContractClientDao(ContractClientDao contractClientDao) {
		this.contractClientDao = contractClientDao;
	}

	public ContractClient getContractClientById(Integer id) {
		return contractClientDao.getContractClientById(id);
	}
	
	public ChargePerson getChargePersonById(Integer id) {
		return contractClientDao.getChargePersonById(id);
	}
	
	public List<ChargePerson> getChargePersonByContractClientId(Integer id) {
		return contractClientDao.getChargePersonByContractClientId(id);
	}
	
	public ContractClient createContractClient(){
		return contractClientDao.createContractClient();
	}
	
	@Transactional
	public void save(ContractClient contractClient) {
		contractClientDao.save(contractClient);
	}
	
	@Transactional
	public void saveChargePerson(ChargePerson chargePerson) {
		contractClientDao.saveChargePerson(chargePerson);
	}
	
	@Transactional(readOnly = true)
	public List<ContractClient> getAllByContractClientName(String name){
		return contractClientDao.getAllByContractClientName(name);
	}
	
	@Transactional(readOnly = true)
	public List<ContractClient> getByContractClientName(String name,Integer contractClientId){
		return contractClientDao.getByContractClientName(name,contractClientId);
	}
}
