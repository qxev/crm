package cn.finance.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.finance.dao.ChargePersonDao;
import cn.finance.model.ChargePerson;

@Transactional
public class ChargePersonService {

	private ChargePersonDao chargePersonDao;

	public void save(ChargePerson chargePerson) {
		chargePersonDao.save(chargePerson);
	}
	
	public void delete(ChargePerson chargePerson) {
		chargePersonDao.delete(chargePerson);
	}
	
	public ChargePerson createChargePerson(){
		return chargePersonDao.createChargePerson();
	}

	public ChargePerson getChargePersonById(Integer id){
		return chargePersonDao.getChargePersonById(id);
	}
	
	public List<ChargePerson> getChargePersonByProjectId(Integer projectId){
		return chargePersonDao.getChargePersonByProjectId(projectId);
	}
	
	public ChargePersonDao getChargePersonDao() {
		return chargePersonDao;
	}

	public void setChargePersonDao(ChargePersonDao chargePersonDao) {
		this.chargePersonDao = chargePersonDao;
	}
	

}
