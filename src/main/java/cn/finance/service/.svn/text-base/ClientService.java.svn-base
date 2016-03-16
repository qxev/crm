package cn.finance.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.finance.dao.ClientDao;
import cn.finance.model.ChargePerson;
import cn.finance.model.Client;
import cn.finance.model.SearchEngine;
import cn.springside.modules.orm.Page;

@Transactional
public class ClientService {

	private ClientDao clientDao;

	@Transactional(readOnly = true)
	public List<Client> getAll(Page<Client> page, boolean hasTotal) {
		return clientDao.getAll(page,hasTotal);
	}
	
	@Transactional(readOnly = true)
	public List<Client> getAll() {
		return clientDao.getAll();
	}
	
	@Transactional(readOnly = true)
	public List<SearchEngine> getAllSearchEngine() {
		return clientDao.getAllSearchEngine();
	}
	
	@Transactional(readOnly = true)
	public List<SearchEngine> getSearchEngineByIds(String ids) {
		return clientDao.getSearchEngineByIds(ids);
	}
	
	@Transactional(readOnly = true)
	public int getTotalCount(Page<Client>page) {
		return clientDao.getTotalCount(page);
	}
	
	public ClientDao getClientDao() {
		return clientDao;
	}

	public void setClientDao(ClientDao clientDao) {
		this.clientDao = clientDao;
	}

	public Client getClientById(Integer id) {
		return clientDao.getClientById(id);
	}
	
	public Client findById(Integer id) {
		return clientDao.findById(id);
	}
	
	public ChargePerson getChargePersonById(Integer id) {
		return clientDao.getChargePersonById(id);
	}
	
	public List<ChargePerson> getChargePersonByClientId(Integer id) {
		return clientDao.getChargePersonByClientId(id);
	}
	
	public Client createClient(){
		return clientDao.createClient();
	}
	
	@Transactional
	public void save(Client client) {
		clientDao.save(client);
	}
	
	@Transactional
	public void saveChargePerson(ChargePerson chargePerson) {
		clientDao.saveChargePerson(chargePerson);
	}
	
	@Transactional(readOnly = true)
	public List<Client> getAllByClientName(String name){
		return clientDao.getAllByClientName(name);
	}
}
