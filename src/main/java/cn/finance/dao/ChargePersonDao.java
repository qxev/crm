package cn.finance.dao;

import java.util.Date;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.finance.model.ChargePerson;

public class ChargePersonDao extends HibernateDaoSupport {

	
	public void save(ChargePerson chargePerson) {
		this.getHibernateTemplate().saveOrUpdate(chargePerson);
	}
	
	public void delete(ChargePerson chargePerson) {
		this.getHibernateTemplate().delete(chargePerson);
	}
	
	public ChargePerson createChargePerson() {
		ChargePerson chargePerson = new ChargePerson();
		chargePerson.setCreateAt(new Date());
		return chargePerson;
	}

	public ChargePerson getChargePersonById(Integer id) {
		return (ChargePerson)this.getHibernateTemplate().get(ChargePerson.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<ChargePerson> getChargePersonByProjectId(Integer projectId) {
		List<ChargePerson> list = (List<ChargePerson>)this.getHibernateTemplate().find(
				" from ChargePerson where project.id = ? and deleted = 0 order by id desc", projectId);
		return list;
	}
}
