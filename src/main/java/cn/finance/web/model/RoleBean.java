package cn.finance.web.model;

import java.util.List;

import cn.finance.model.Resource;
import cn.finance.model.Role;
import cn.finance.model.User;

public class RoleBean {

	private Role bd;
	
	private String bdResourceStr;

	private Integer[] bdResources;
	
	private Role bdDirect;

	private String bdDirectResourceStr;

	private Integer[] bdDirectResources;
	
	private Role semDirect;

	private String semDirectResourceStr;

	private Integer[] semDirectResources;
	
	private Role seoDirect;

	private String seoDirectResourceStr;

	private Integer[] seoDirectResources;
	
	private Role affDirect;

	private String affDirectResourceStr;

	private Integer[] affDirectResources;
	
	private Role womDirect;

	private String womDirectResourceStr;

	private Integer[] womDirectResources;
	
	private Role finDirect;

	private String finDirectResourceStr;

	private Integer[] finDirectResources;
	
	private Role ceo;
	
	private String ceoResourceStr;

	private Integer[] ceoResources;
	
	private Role secretary;
	
	private String secretaryResourceStr;

	private Integer[] secretaryResources;
	
	private List<Resource> resources;
	
	private List<User> users;
	
	private Integer userId;
	
	public RoleBean(){}

	public Role getBd() {
		return bd;
	}

	public void setBd(Role bd) {
		this.bd = bd;
	}

	public Role getBdDirect() {
		return bdDirect;
	}

	public void setBdDirect(Role bdDirect) {
		this.bdDirect = bdDirect;
	}

	public Role getSemDirect() {
		return semDirect;
	}

	public void setSemDirect(Role semDirect) {
		this.semDirect = semDirect;
	}

	public Role getSeoDirect() {
		return seoDirect;
	}

	public void setSeoDirect(Role seoDirect) {
		this.seoDirect = seoDirect;
	}

	public Role getAffDirect() {
		return affDirect;
	}

	public void setAffDirect(Role affDirect) {
		this.affDirect = affDirect;
	}

	public Role getWomDirect() {
		return womDirect;
	}

	public void setWomDirect(Role womDirect) {
		this.womDirect = womDirect;
	}

	public Role getFinDirect() {
		return finDirect;
	}

	public void setFinDirect(Role finDirect) {
		this.finDirect = finDirect;
	}

	public Role getCeo() {
		return ceo;
	}

	public void setCeo(Role ceo) {
		this.ceo = ceo;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	public String getBdResourceStr() {
		return bdResourceStr;
	}

	public void setBdResourceStr(String bdResourceStr) {
		this.bdResourceStr = bdResourceStr;
	}

	public Integer[] getBdResources() {
		return bdResources;
	}

	public void setBdResources(Integer[] bdResources) {
		this.bdResources = bdResources;
	}

	public String getBdDirectResourceStr() {
		return bdDirectResourceStr;
	}

	public void setBdDirectResourceStr(String bdDirectResourceStr) {
		this.bdDirectResourceStr = bdDirectResourceStr;
	}

	public Integer[] getBdDirectResources() {
		return bdDirectResources;
	}

	public void setBdDirectResources(Integer[] bdDirectResources) {
		this.bdDirectResources = bdDirectResources;
	}

	public String getSemDirectResourceStr() {
		return semDirectResourceStr;
	}

	public void setSemDirectResourceStr(String semDirectResourceStr) {
		this.semDirectResourceStr = semDirectResourceStr;
	}

	public Integer[] getSemDirectResources() {
		return semDirectResources;
	}

	public void setSemDirectResources(Integer[] semDirectResources) {
		this.semDirectResources = semDirectResources;
	}

	public String getSeoDirectResourceStr() {
		return seoDirectResourceStr;
	}

	public void setSeoDirectResourceStr(String seoDirectResourceStr) {
		this.seoDirectResourceStr = seoDirectResourceStr;
	}

	public Integer[] getSeoDirectResources() {
		return seoDirectResources;
	}

	public void setSeoDirectResources(Integer[] seoDirectResources) {
		this.seoDirectResources = seoDirectResources;
	}

	public String getAffDirectResourceStr() {
		return affDirectResourceStr;
	}

	public void setAffDirectResourceStr(String affDirectResourceStr) {
		this.affDirectResourceStr = affDirectResourceStr;
	}

	public Integer[] getAffDirectResources() {
		return affDirectResources;
	}

	public void setAffDirectResources(Integer[] affDirectResources) {
		this.affDirectResources = affDirectResources;
	}

	public String getWomDirectResourceStr() {
		return womDirectResourceStr;
	}

	public void setWomDirectResourceStr(String womDirectResourceStr) {
		this.womDirectResourceStr = womDirectResourceStr;
	}

	public Integer[] getWomDirectResources() {
		return womDirectResources;
	}

	public void setWomDirectResources(Integer[] womDirectResources) {
		this.womDirectResources = womDirectResources;
	}

	public String getFinDirectResourceStr() {
		return finDirectResourceStr;
	}

	public void setFinDirectResourceStr(String finDirectResourceStr) {
		this.finDirectResourceStr = finDirectResourceStr;
	}

	public Integer[] getFinDirectResources() {
		return finDirectResources;
	}

	public void setFinDirectResources(Integer[] finDirectResources) {
		this.finDirectResources = finDirectResources;
	}

	public String getCeoResourceStr() {
		return ceoResourceStr;
	}

	public void setCeoResourceStr(String ceoResourceStr) {
		this.ceoResourceStr = ceoResourceStr;
	}

	public Integer[] getCeoResources() {
		return ceoResources;
	}

	public void setCeoResources(Integer[] ceoResources) {
		this.ceoResources = ceoResources;
	}

	public Role getSecretary() {
		return secretary;
	}

	public void setSecretary(Role secretary) {
		this.secretary = secretary;
	}

	public String getSecretaryResourceStr() {
		return secretaryResourceStr;
	}

	public void setSecretaryResourceStr(String secretaryResourceStr) {
		this.secretaryResourceStr = secretaryResourceStr;
	}

	public Integer[] getSecretaryResources() {
		return secretaryResources;
	}

	public void setSecretaryResources(Integer[] secretaryResources) {
		this.secretaryResources = secretaryResources;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}
