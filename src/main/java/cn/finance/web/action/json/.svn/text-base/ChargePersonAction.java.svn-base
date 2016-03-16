package cn.finance.web.action.json;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import cn.finance.model.ChargePerson;
import cn.finance.model.Project;
import cn.finance.service.ChargePersonService;
import cn.finance.service.ProjectService;

import com.opensymphony.xwork2.Action;

public class ChargePersonAction extends JdbcDaoSupport  {

	private static final long serialVersionUID = 1L;

	/**
	 * 项目Id
	 */
	private Integer projectId;
	
	/**
	 * 负责人Id
	 */
	private Integer chargePersonId;
	
	/**
	 * 负责人姓名
	 */
	private String name;    
	
	/**
	 * 电话
	 */
	private String phone;
	
	/**
	 * 地址
	 */
	private String address;
	
	/**
	 * email 
	 */
	private String email;
	
	/**
	 * 传真
	 */
	private String fax;
	
	private ProjectService projectService;
	
	private ChargePersonService chargePersonService;
	
	private List<ChargePerson> chargePersons = new ArrayList<ChargePerson>();

	/**
	 * 根据客户id查找所有的项目
	 * @return
	 */
	public String add(){
		ChargePerson chargePerson = chargePersonService.createChargePerson();
		if (chargePersonId==null){
			Project	project = projectService.getProjectById(projectId);
		//	chargePerson.setProject(project);
			chargePerson.setCreateAt(new Date());
		} else {
			chargePerson = chargePersonService.getChargePersonById(chargePersonId);
			chargePerson.setUpdateAt(new Date());
		}
		chargePerson.setName(name);
		chargePerson.setPhone(phone);
		chargePerson.setAddress(address);
		chargePerson.setEmail(email);
		chargePerson.setFax(fax);
		chargePersonService.save(chargePerson);
		chargePersons = chargePersonService.getChargePersonByProjectId(projectId);
		return Action.SUCCESS;
	}
	
	/**
	 * 根据客户id查找所有的项目
	 * @return
	 */
	public String delete(){
		ChargePerson chargePerson=chargePersonService.getChargePersonById(chargePersonId);
		chargePerson.setDeleted("1");
		chargePersonService.save(chargePerson);
		chargePersons = chargePersonService.getChargePersonByProjectId(projectId);
		return Action.SUCCESS;
	}

	public ProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	public ChargePersonService getChargePersonService() {
		return chargePersonService;
	}

	public void setChargePersonService(ChargePersonService chargePersonService) {
		this.chargePersonService = chargePersonService;
	}

	public List<ChargePerson> getChargePersons() {
		return chargePersons;
	}

	public void setChargePersons(List<ChargePerson> chargePersons) {
		this.chargePersons = chargePersons;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public void setChargePersonId(Integer chargePersonId) {
		this.chargePersonId = chargePersonId;
	}

}
