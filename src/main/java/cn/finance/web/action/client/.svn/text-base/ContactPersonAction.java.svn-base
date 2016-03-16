package cn.finance.web.action.client;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import cn.finance.model.ChargePerson;
import cn.finance.model.ContractClient;
import cn.finance.service.ContractClientService;
import cn.finance.util.Struts2Utils;
import cn.finance.web.action.base.BaseAction;

public class ContactPersonAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ContractClientService clientService;

	private ContractClient client;
	
	private Integer contractClientId;

	private String pageNo;
	
	private ChargePerson chargePerson;

	private Integer personId;
	
	public String initClient() {
		Struts2Utils.getRequest().setAttribute("nav", "2");
		Struts2Utils.getRequest().setAttribute("subNav", "1");
		client = clientService.getContractClientById(contractClientId);
		return SUCCESS;
	}
	
	public String editContactPerson() {
		ChargePerson editPerson = new ChargePerson();
		if (personId!=null){
			editPerson = clientService.getChargePersonById(personId);
			editPerson.setCreateAt(new Date());
		}
		client = clientService.getContractClientById(contractClientId);
		editPerson.setContractClient(client);
		editPerson.setUserType(chargePerson.getUserType());
		editPerson.setName(chargePerson.getName());
		editPerson.setAddress(chargePerson.getAddress());
		editPerson.setPhone(chargePerson.getPhone());
		editPerson.setEmail(chargePerson.getEmail());
		editPerson.setUpdateAt(new Date());
		clientService.saveChargePerson(editPerson);
		if (personId!=null){
			this.addActionMessage("更新联系人成功！");
		} else {
			this.addActionMessage("新增联系人成功！");
		}
		personId=null;
		return "list";
	}
	
	public String deleteContactPerson() {
		if (personId!=null){
			ChargePerson chargePerson = clientService.getChargePersonById(personId);
			chargePerson.setDeleted("1");
			clientService.saveChargePerson(chargePerson);
			this.addActionMessage("删除联系人成功！");
		}
		return "list";
	}

	public ContractClient getClient() {
		return client;
	}

	public void setClient(ContractClient client) {
		this.client = client;
	}
	
	public Integer getContractClientId() {
		return contractClientId;
	}

	public void setContractClientId(Integer contractClientId) {
		this.contractClientId = contractClientId;
	}

	public ChargePerson getChargePerson() {
		return chargePerson;
	}

	public void setChargePerson(ChargePerson chargePerson) {
		this.chargePerson = chargePerson;
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
}