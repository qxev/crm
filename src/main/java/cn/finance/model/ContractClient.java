package cn.finance.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.finance.model.base.PersistentObject;

/**
 * 客户表
 */
public class ContractClient extends PersistentObject {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 客户名
	 */
	private String name;    
	
	/**
	 * 客户类型
	 */
	private Integer type; 
	
	/**
	 * 所属的项目
	 */
	private Set<ContractProject> contractProjects = new HashSet<ContractProject>();

	/**
	 * 项目负责人
	 */
	private Set<ChargePerson> chargePersons = new HashSet<ChargePerson>();

	public String getName() {
		return name;
	}
	
	public String getCutName() {
		if (name !=null && name.length()>8){
			return name.substring(0, 8);
		}
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTypeDisplay() {
		if (type == 1){
			return "直接客户";
		} else if(type == 2) {
			return "代理客户";
		} else {
			return "";
		}
	}
	
	public String getTypeDisplayEn() {
		if (type == 1){
			return "Direct";
		} else if(type == 2) {
			return "Agency";
		} else {
			return "";
		}
	}
	
	public Set<ChargePerson> getChargePersons() {
		return chargePersons;
	}
	
	public List<ChargePerson> getNormalPersons() {
		List<ChargePerson> list = new ArrayList<ChargePerson>();
		if (chargePersons.size()>0){
			for (ChargePerson chargePerson : chargePersons){
				if (chargePerson.getUserType()==1&&("0".equals(chargePerson.getDeleted()))){
					list.add(chargePerson);
				}
			}
		} 
		return list;
	}
	
	public List<ChargePerson> getFinancePersons() {
		List<ChargePerson> list = new ArrayList<ChargePerson>();
		if (chargePersons.size()>0){
			for (ChargePerson chargePerson : chargePersons){
				if (chargePerson.getUserType()==2&&("0".equals(chargePerson.getDeleted()))){
					list.add(chargePerson);
				}
			}
		} 
		return list;
	}
	
	public List<ChargePerson> getInvoicePersons() {
		List<ChargePerson> list = new ArrayList<ChargePerson>();
		if (chargePersons.size()>0){
			for (ChargePerson chargePerson : chargePersons){
				if (chargePerson.getUserType()==3&&("0".equals(chargePerson.getDeleted()))){
					list.add(chargePerson);
				}
			}
		} 
		return list;
	}

	public void setChargePersons(Set<ChargePerson> chargePersons) {
		this.chargePersons = chargePersons;
	}

	public Set<ContractProject> getContractProjects() {
		return contractProjects;
	}

	public void setContractProjects(Set<ContractProject> contractProjects) {
		this.contractProjects = contractProjects;
	}
	
}
