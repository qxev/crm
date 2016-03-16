package cn.finance.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.finance.model.SysInfo;

public class SysInfoDao extends HibernateDaoSupport {

	@SuppressWarnings("unchecked")
	public SysInfo getSysInfoByName(String name) {
		List<SysInfo> list = (List<SysInfo>)this.getHibernateTemplate().find(
				" from SysInfo where name = ? and deleted=0 ", name);
		if (list != null && list.size() > 0) {
			return (SysInfo) list.get(0);
		}
		return null;
	}
	
	public void save(SysInfo sysInfo) {
		this.getHibernateTemplate().saveOrUpdate(sysInfo);
	}
	
	public SysInfo findSysInfo(Integer id){
		return (SysInfo)this.getHibernateTemplate().get(SysInfo.class, id);
	}
	
	
}
