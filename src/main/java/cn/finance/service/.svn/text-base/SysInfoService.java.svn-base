package cn.finance.service;

import org.springframework.transaction.annotation.Transactional;

import cn.finance.dao.SysInfoDao;
import cn.finance.model.SysInfo;
@Transactional
public class SysInfoService {

	private SysInfoDao sysInfoDao;

	@Transactional(readOnly = true)
	public SysInfo getSysInfoByName(String name) {
		return sysInfoDao.getSysInfoByName(name);
	}
	
	public void saveSysInfo(SysInfo sysInfo) {
		sysInfoDao.save(sysInfo);
	}
	
	public SysInfoDao getSysInfoDao() {
		return sysInfoDao;
	}

	public void setSysInfoDao(SysInfoDao sysInfoDao) {
		this.sysInfoDao = sysInfoDao;
	}
}
