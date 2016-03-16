package cn.finance.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.finance.model.BusinessType;

public class BusinessTypeDao extends HibernateDaoSupport {

	@SuppressWarnings("unchecked")
	public List<BusinessType> getAll(){
		Query query = getSession().createQuery(" from BusinessType where deleted = 0 ");
		return query.list();
	}
	
	public BusinessType getBusinessTypeById(Integer id) {
		return (BusinessType)this.getHibernateTemplate().get(BusinessType.class, id);
	}
	
	public void save(BusinessType businessType) {
		this.getHibernateTemplate().saveOrUpdate(businessType);
	}
	
	public BusinessType create() {
		BusinessType businessType = new BusinessType();
		businessType.setCreateAt(new Date());
		return businessType;
	}

}
