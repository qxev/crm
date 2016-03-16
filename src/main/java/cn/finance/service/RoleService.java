package cn.finance.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import cn.finance.dao.RoleDao;
import cn.finance.model.Resource;
import cn.finance.model.Role;
import cn.finance.model.SearchEngine;
import cn.finance.web.model.RoleBean;

public class RoleService {

	private RoleDao roleDao;

	public Role findRoleById(Integer id) {
		return roleDao.findRoleById(id);
	}
	
	public List<Resource> getRecourse() {
		return roleDao.getRecourse();
	}
	
	public List<Resource> getResourceByIds(String ids){
		return roleDao.getResourceByIds(ids);
	}
	
	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	
	public RoleBean initRole(RoleBean roleBean) {
		roleBean.setBd(roleDao.findRoleById(2));
		Set <Resource>resources = roleBean.getBd().getResources();
		if (resources.size()>0){
			Integer []bdRecourses = new Integer[resources.size()];
			Iterator<Resource> it = resources.iterator();
			for (int i=0;i<resources.size();i++){
				Resource resource = (Resource)it.next();
				bdRecourses[i]=resource.getId();
			}
			roleBean.setBdResources(bdRecourses);
		}
		roleBean.setBdDirect(roleDao.findRoleById(3));
		resources = roleBean.getBdDirect().getResources();
		if (resources.size()>0){
			Integer []bdDirectRecourses = new Integer[resources.size()];
			Iterator<Resource> it = resources.iterator();
			for (int i=0;i<resources.size();i++){
				Resource resource = (Resource)it.next();
				bdDirectRecourses[i]=resource.getId();
			}
			roleBean.setBdDirectResources(bdDirectRecourses);
		}
		roleBean.setSemDirect(roleDao.findRoleById(4));
		resources = roleBean.getSemDirect().getResources();
		if (resources.size()>0){
			Integer []semDirectRecourses = new Integer[resources.size()];
			Iterator<Resource> it = resources.iterator();
			for (int i=0;i<resources.size();i++){
				Resource resource = (Resource)it.next();
				semDirectRecourses[i]=resource.getId();
			}
			roleBean.setSemDirectResources(semDirectRecourses);
		}
		roleBean.setSeoDirect(roleDao.findRoleById(5));
		resources = roleBean.getSeoDirect().getResources();
		if (resources.size()>0){
			Integer []seoDirectRecourses = new Integer[resources.size()];
			Iterator<Resource> it = resources.iterator();
			for (int i=0;i<resources.size();i++){
				Resource resource = (Resource)it.next();
				seoDirectRecourses[i]=resource.getId();
			}
			roleBean.setSeoDirectResources(seoDirectRecourses);
		}
		roleBean.setAffDirect(roleDao.findRoleById(6));
		resources = roleBean.getAffDirect().getResources();
		if (resources.size()>0){
			Integer []affDirectRecourses = new Integer[resources.size()];
			Iterator<Resource> it = resources.iterator();
			for (int i=0;i<resources.size();i++){
				Resource resource = (Resource)it.next();
				affDirectRecourses[i]=resource.getId();
			}
			roleBean.setAffDirectResources(affDirectRecourses);
		}
		roleBean.setWomDirect(roleDao.findRoleById(7));
		resources = roleBean.getWomDirect().getResources();
		if (resources.size()>0){
			Integer []womDirectRecourses = new Integer[resources.size()];
			Iterator<Resource> it = resources.iterator();
			for (int i=0;i<resources.size();i++){
				Resource resource = (Resource)it.next();
				womDirectRecourses[i]=resource.getId();
			}
			roleBean.setWomDirectResources(womDirectRecourses);
		}
		roleBean.setFinDirect(roleDao.findRoleById(8));
		resources = roleBean.getFinDirect().getResources();
		if (resources.size()>0){
			Integer []finDirectRecourses = new Integer[resources.size()];
			Iterator<Resource> it = resources.iterator();
			for (int i=0;i<resources.size();i++){
				Resource resource = (Resource)it.next();
				finDirectRecourses[i]=resource.getId();
			}
			roleBean.setFinDirectResources(finDirectRecourses);
		}
		roleBean.setSecretary(roleDao.findRoleById(14));
		resources = roleBean.getSecretary().getResources();
		if (resources.size()>0){
			Integer []secretaryRecourses = new Integer[resources.size()];
			Iterator<Resource> it = resources.iterator();
			for (int i=0;i<resources.size();i++){
				Resource resource = (Resource)it.next();
				secretaryRecourses[i]=resource.getId();
			}
			roleBean.setSecretaryResources(secretaryRecourses);
		}
		roleBean.setCeo(roleDao.findRoleById(10));
		resources = roleBean.getCeo().getResources();
		if (resources.size()>0){
			Integer []ceoRecourses = new Integer[resources.size()];
			Iterator<Resource> it = resources.iterator();
			for (int i=0;i<resources.size();i++){
				Resource resource = (Resource)it.next();
				ceoRecourses[i]=resource.getId();
			}
			roleBean.setCeoResources(ceoRecourses);
		}
		roleBean.setResources(roleDao.getRecourse());
		return roleBean;
	}
	
	@Transactional
	public void saveRole(RoleBean roleBean) {
		Role bdRole = roleDao.findRoleById(2);
		List <Resource>list = roleDao.getResourceByIds(roleBean.getBdResourceStr());
		bdRole.setResources(new HashSet<Resource>(list));
		roleDao.save(bdRole);
		Role bdDirectRole = roleDao.findRoleById(3);
		list = roleDao.getResourceByIds(roleBean.getBdDirectResourceStr());
		bdDirectRole.setResources(new HashSet<Resource>(list));
		roleDao.save(bdDirectRole);
		Role semDirectRole = roleDao.findRoleById(4);
		list = roleDao.getResourceByIds(roleBean.getSemDirectResourceStr());
		semDirectRole.setResources(new HashSet<Resource>(list));
		roleDao.save(semDirectRole);
		Role seoDirectRole = roleDao.findRoleById(5);
		list = roleDao.getResourceByIds(roleBean.getSeoDirectResourceStr());
		seoDirectRole.setResources(new HashSet<Resource>(list));
		roleDao.save(seoDirectRole);
		Role affDirectRole = roleDao.findRoleById(6);
		list = roleDao.getResourceByIds(roleBean.getAffDirectResourceStr());
		affDirectRole.setResources(new HashSet<Resource>(list));
		roleDao.save(affDirectRole);
		Role womDirectRole = roleDao.findRoleById(7);
		list = roleDao.getResourceByIds(roleBean.getWomDirectResourceStr());
		womDirectRole.setResources(new HashSet<Resource>(list));
		roleDao.save(womDirectRole);
		Role finDirectRole = roleDao.findRoleById(8);
		list = roleDao.getResourceByIds(roleBean.getFinDirectResourceStr());
		finDirectRole.setResources(new HashSet<Resource>(list));
		roleDao.save(finDirectRole);
		Role secretaryRole = roleDao.findRoleById(14);
		list = roleDao.getResourceByIds(roleBean.getSecretaryResourceStr());
		secretaryRole.setResources(new HashSet<Resource>(list));
		Role ceoRole = roleDao.findRoleById(10);
		list = roleDao.getResourceByIds(roleBean.getCeoResourceStr());
		ceoRole.setResources(new HashSet<Resource>(list));
		roleDao.save(ceoRole);
	}
}
