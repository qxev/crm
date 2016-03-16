package cn.finance.web.action.client;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.finance.model.Contract;
import cn.finance.model.User;
import cn.finance.model.Version;
import cn.finance.service.ContractService;
import cn.finance.service.UserService;
import cn.finance.util.Constant;
import cn.finance.util.DateUtil;
import cn.finance.util.EmailUtils;
import cn.finance.util.Struts2Utils;
import cn.finance.web.action.base.BaseAction;
import cn.finance.web.model.SearchView;
import cn.finance.web.servlet.UserSession;

import com.opensymphony.xwork2.ActionContext;

public class VerifyContractAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ContractService contractService;

	@Autowired
	private UserService userService;

	private Contract contract;

	private Integer contractId;

	private SearchView searchView;

	private String filter;

	private Integer pageNo;

	private String action;

	private String reason;

	private String note;

	public String edit() throws UnsupportedEncodingException {
		Struts2Utils.getRequest().setAttribute("nav", "6");
		Struts2Utils.getRequest().setAttribute("subNav", "8");
		contract = contractService.getContractById(contractId);
		return EDIT;
	}

	public String save(Contract saveContract) throws Exception {
		saveContract.setLeadName(contract.getLeadName());
		saveContract.setProduct(contract.getProduct());
		saveContract.setContractStartDate(contract.getContractStartDate());
		saveContract.setContractEndDate(contract.getContractEndDate());
		saveContract.setMonthlyBudget(contract.getMonthlyBudget());
		saveContract.setServiceFee(contract.getServiceFee());
		saveContract.setRoi(contract.getRoi());
		saveContract.setPaymentTerm(contract.getPaymentTerm());
		saveContract.setSpecialTerms(contract.getSpecialTerms());
		if (StringUtils.isNotBlank(contract.getRemindDate())) {
			saveContract.setRemindDate(contract.getRemindDate());
			saveContract.setRemindClock(contract.getRemindClock());
			Date date = DateUtils.parseDate(contract.getRemindDate(), new String[] { "yyyy-MM-dd" });
			date.setHours(contract.getRemindClock());
			saveContract.setRemindTime(date);
		} else {
			saveContract.setRemindDate("");
			saveContract.setRemindClock(0);
			saveContract.setRemindTime(null);
		}
		contractService.save(saveContract);
		return "none";
	}

	public String verify() throws UnsupportedEncodingException {
		Struts2Utils.getRequest().setAttribute("nav", "6");
		Struts2Utils.getRequest().setAttribute("subNav", "8");
		contract = contractService.getContractById(contractId);
		String status = contract.getStatus();
		if ("3".equals(status)) {
			note = "提交" + contract.getContractProject().getBusinessType().getName() + "主管,FIN主管审核";
		} else if ("4".equals(status) || "5".equals(status) || "6".equals(status) || "7".equals(status)
				|| "8".equals(status) || "13".equals(status)) {
			note = "提交客户审核";
		} else if ("10".equals(status)) {
			note = "Approve";
		}
		return "verify";
	}

	public String view() throws UnsupportedEncodingException {
		Struts2Utils.getRequest().setAttribute("nav", "6");
		Struts2Utils.getRequest().setAttribute("subNav", "8");
		contract = contractService.getContractById(contractId);
		return "view";
	}

	public String applyAction() throws Exception {
		Struts2Utils.getRequest().setAttribute("nav", "6");
		Struts2Utils.getRequest().setAttribute("subNav", "8");
		Contract saveContract = contractService.getContractById(contractId);
		if ("save".equals(action)) {
			return save(saveContract);
		} else if ("saveAndApply".equals(action)) {
			save(saveContract);
		}
		if ("deny".equals(action)) {
			return deny();
		}
		UserSession userSession = (UserSession) ActionContext.getContext().getSession().get(Constant.USER_SESSION_KEY);
		User applyUser = userService.findUser(userSession.getUserId());
		Integer status = 2;
		boolean sendEmail = true;
		if (("1").equals(saveContract.getStatus()) || ("2").equals(saveContract.getStatus())) {
			status = 3;
		} else if (("3").equals(saveContract.getStatus())) {
			status = saveContract.getContractProject().getBusinessType().getId();
			if (saveContract.getContractProject().getPm().getId() == 47) {
				saveContract.setDepartApprove(1);
			}
		} else if (("4").equals(saveContract.getStatus()) || ("5").equals(saveContract.getStatus())
				|| ("6").equals(saveContract.getStatus()) || ("7").equals(saveContract.getStatus())
				|| ("13").equals(saveContract.getStatus())) {
			if ((applyUser.getRole().getId() > 3 && applyUser.getRole().getId() < 8)
					|| applyUser.getRole().getId() == 13) {
				saveContract.setDepartApprove(1);
			} else if (applyUser.getRole().getId() == 8) {
				saveContract.setFinanceApprove(1);
			}
			if (saveContract.getDepartApprove() != null && saveContract.getDepartApprove() == 1
					&& saveContract.getFinanceApprove() != null && saveContract.getFinanceApprove() == 1) {
				status = 9;
			} else {
				status = Integer.valueOf(saveContract.getStatus());
				sendEmail = false;
			}
		} else if (("9").equals(saveContract.getStatus())) {
			status = 10;
		} else if (("10").equals(saveContract.getStatus())) {
			saveContract.setFinishTime(new Date());
			status = 11;
		}
		if (applyUser.getRole().getId() == 3 && status == 3) {
			status = saveContract.getContractProject().getBusinessType().getId();
			if (saveContract.getContractProject().getPm().getId() == 47) {
				saveContract.setDepartApprove(1);
			}
		}
		if (contract != null) {
			if (StringUtils.isNotBlank(contract.getRemindDate())) {
				saveContract.setRemindDate(contract.getRemindDate());
				saveContract.setRemindClock(contract.getRemindClock());
				Date date = DateUtils.parseDate(contract.getRemindDate(), new String[] { "yyyy-MM-dd" });
				date.setHours(contract.getRemindClock());
				saveContract.setRemindTime(date);
			} else {
				saveContract.setRemindDate("");
				saveContract.setRemindClock(0);
				saveContract.setRemindTime(null);
			}
		}
		saveContract.setChargePerson(applyUser);
		saveContract.setStatus(status.toString());
		saveContract.setLastRemind(new Date());
		contractService.save(saveContract);
		User createUser = saveContract.getCreateUser();
		List<User> ccs = new ArrayList<User>();
		ccs.add(createUser);
		if ((status > 3 && status < 9) || status == 13) {
			List<User> templist = userService.getUserByTypeAndAgent(3, saveContract.getAreaId(), saveContract
					.getContractProject().getPm().getId(), saveContract.getContractProject().getBd().getLeaderUserId());
			ccs.addAll(templist);
		}
		if ((status > 2 && status < 12) || status == 13) {
			List<User> userlist = userService.getUserByTypeAndAgent(status, saveContract.getAreaId(), saveContract
					.getContractProject().getPm().getId(), saveContract.getContractProject().getBd().getLeaderUserId());
			List<User> financeList = userService.getUserByTypeAndAgent(8, saveContract.getAreaId(), saveContract
					.getContractProject().getPm().getId(), saveContract.getContractProject().getBd().getLeaderUserId());
			if ((status > 3 && status < 8) || status == 13) {
				userlist.addAll(financeList);
			} else if (status == 9) {
				userlist = new ArrayList();
				userlist.add(createUser);
				List<User> templist = userService.getUserByTypeAndAgent(saveContract.getContractProject()
						.getBusinessType().getId(), saveContract.getAreaId(), saveContract.getContractProject().getPm()
						.getId(), saveContract.getContractProject().getBd().getLeaderUserId());
				ccs.addAll(templist);
				ccs.addAll(financeList);
			} else if (status == 10) {
				ccs.addAll(financeList);
			} else if (status == 11) {
				userlist = new ArrayList();
				userlist.addAll(financeList);
				userlist.add(createUser);
			}
			if (sendEmail) {
				StringBuffer subject = new StringBuffer("★☆ ");
				if (status == 11) {
					subject.append(saveContract.getStatusDisplay()).append("请领取合同：")
							.append(saveContract.getContractProject().getName());
				} else if (status == 10) {
					subject.append("Waitint for CEO to Check ：please approve the contract of "
							+ saveContract.getContractProject().getName());
				} else {
					subject.append(saveContract.getStatusDisplay()).append("阶段： 请审批 “")
							.append(saveContract.getContractProject().getContractClient().getName()).append(" ”,在项目 “")
							.append(saveContract.getContractProject().getName()).append(" ”下的合同");
				}
				StringBuffer msg = new StringBuffer("");
				if (status == 11) {
					msg.append(saveContract.getContractProject().getName()).append("项目的合同审核通过，请BD同事去财务处领取盖好章的合同。<br/>");
					msg.append("英文摘要如下<br/>");
				} else if (status == 10) {
					msg.append("The contract of '").append(saveContract.getContractProject().getName())
							.append("' from '").append(saveContract.getContractProject().getContractClient().getName())
							.append("’ requires approval，");
					Date remineDate = saveContract.getRemindTime();
					if (remineDate != null) {
						String dateTemp = DateUtil.dateToString(remineDate);
						msg.append(" the approval deadline is ").append(dateTemp).append(".");
					}
					msg.append("Please login in Contract Management System as early as possible. -> <a href=\"http://192.168.1.29/finance/login!show.action\">Contract System</a><br/>");
					msg.append("The contract summary is as follows: <br/>");
				} else {
					msg.append("“ ").append(saveContract.getContractProject().getContractClient().getName())
							.append(" ”,在项目“ ").append(saveContract.getContractProject().getName()).append(" ”下的合同，需要");
					Date remineDate = saveContract.getRemindTime();
					if (remineDate != null) {
						String dateTemp = DateUtil.dateToString(remineDate, "yyyy年MM月dd日");
						msg.append("在").append(dateTemp).append("前");
					}
					msg.append(
							"审核，请尽快登陆系统 -><a href=\"http://192.168.1.29/finance/login!show.action?userLoginView.contractId=")
							.append(saveContract.getId()).append("\">合同管理系统</a><br/>");
					msg.append("英文摘要如下<br/>");
				}
				msg.append("Darwin Company Name: ").append(saveContract.getContractProject().getDarwinName().getName())
						.append("<br/>");
				msg.append("Client Company Name: ")
						.append(saveContract.getContractProject().getContractClient().getName()).append("<br/>");
				msg.append("Project Name: ").append(saveContract.getContractProject().getName()).append("<br/>");
				msg.append("Project Categoty: ").append(saveContract.getContractProject().getBusinessType().getName())
						.append("<br/>");
				msg.append("Product: ").append(saveContract.getProduct()).append("<br/>");
				msg.append("Contract Start Date: ").append(saveContract.getContractStartDate()).append("<br/>");
				msg.append("Contract End Date: ").append(saveContract.getContractEndDate()).append("<br/>");
				msg.append("Monthly Budget: ").append(saveContract.getMonthlyBudget()).append("<br/>");
				msg.append("Service Fee: ").append(saveContract.getServiceFee()).append("<br/>");
				msg.append("ROI: ").append(saveContract.getRoi()).append("<br/>");
				msg.append("Payment Term: ").append(saveContract.getPaymentTerm()).append("<br/>");
				msg.append("Special Terms: ").append(saveContract.getSpecialTerms()).append("<br/>");
				msg.append("BD Name: ").append(saveContract.getContractProject().getBd().getName()).append("<br/>");
				msg.append("Lead Name: ").append(saveContract.getLeadName()).append("<br/>");
				msg.append("Source: ").append(saveContract.getContractProject().getContractClient().getTypeDisplayEn())
						.append("<br/>");
				EmailUtils.sendSimpleEmail(applyUser.getEmail(), applyUser.getName(), userlist, ccs,
						subject.toString(), msg.toString());
			}
		}
		return "none";
	}

	public String cancel() throws IOException {
		Struts2Utils.getRequest().setAttribute("nav", "6");
		Struts2Utils.getRequest().setAttribute("subNav", "8");
		HttpServletRequest request = ServletActionContext.getRequest();
		Contract contract = contractService.getContractById(contractId);
		Version version = new Version();
		version.setContract(contract);
		UserSession userSession = (UserSession) ActionContext.getContext().getSession().get(Constant.USER_SESSION_KEY);
		User createUser = userService.findUser(userSession.getUserId());
		version.setSuggestUser(createUser);
		version.setContext(contract.getStatus());
		Integer versionNum = contract.getVersionNum();
		version.setVersion("V" + versionNum);
		version.setPrefix(contract.getPrefix());
		versionNum++;
		version.setCreateAt(new Date());
		contract.setVersionNum(versionNum);
		contract.setChargePerson(contract.getCreateUser());
		Integer status = Integer.valueOf(contract.getStatus());
		User cUser = contract.getCreateUser();
		List<User> ccs = new ArrayList<User>();
		ccs.add(cUser);
		List<User> userlist = new ArrayList<User>();
		userlist.add(cUser);
		if (status > 2) {
			List<User> templist = userService.getUserByTypeAndAgent(3, contract.getAreaId(), contract
					.getContractProject().getPm().getId(), contract.getContractProject().getBd().getLeaderUserId());
			ccs.addAll(templist);
		}
		if (status > 3) {
			List<User> templist2 = userService.getUserByTypeAndAgent(contract.getContractProject().getBusinessType()
					.getId(), contract.getAreaId(), contract.getContractProject().getPm().getId(), contract
					.getContractProject().getBd().getLeaderUserId());
			ccs.addAll(templist2);
			List<User> financelist = userService.getUserByTypeAndAgent(8, contract.getAreaId(), contract
					.getContractProject().getPm().getId(), contract.getContractProject().getBd().getLeaderUserId());
			ccs.addAll(financelist);
		}
		StringBuffer subject = new StringBuffer("★★合同被创建者取消：客户“ ");
		subject.append(contract.getContractProject().getContractClient().getName()).append(" ”的项目“ ")
				.append(contract.getContractProject().getName()).append("”下的合同被创建者取消");
		StringBuffer msg = new StringBuffer("客户“ ");
		msg.append(contract.getContractProject().getContractClient().getName()).append(" ”的项目“ ")
				.append(contract.getContractProject().getName()).append("”下的合同，被创建者取消");
		EmailUtils.sendSimpleEmail(createUser.getEmail(), createUser.getName(), userlist, ccs, subject.toString(),
				msg.toString());
		File oldFile = new File(request.getSession().getServletContext().getRealPath("") + "/contract/" + contractId
				+ contract.getPrefix());
		FileUtils.copyFile(oldFile, new File(request.getSession().getServletContext().getRealPath("") + "/contract/"
				+ contractId + version.getVersion() + contract.getPrefix()));
		contract.setStatus("2");
		contract.setDepartApprove(0);
		contract.setFinanceApprove(0);
		contractService.saveVersion(version);
		contractService.save(contract);
		return "none";
	}

	public String deny() throws IOException {
		Struts2Utils.getRequest().setAttribute("nav", "6");
		Struts2Utils.getRequest().setAttribute("subNav", "7");
		HttpServletRequest request = ServletActionContext.getRequest();
		Contract contract = contractService.getContractById(contractId);
		Version version = new Version();
		version.setContract(contract);
		UserSession userSession = (UserSession) ActionContext.getContext().getSession().get(Constant.USER_SESSION_KEY);
		User createUser = userService.findUser(userSession.getUserId());
		version.setSuggestUser(createUser);
		version.setReason(reason);
		version.setContext(contract.getStatus());
		Integer versionNum = contract.getVersionNum();
		version.setVersion("V" + versionNum);
		version.setPrefix(contract.getPrefix());
		versionNum++;
		version.setCreateAt(new Date());
		contract.setVersionNum(versionNum);
		contract.setChargePerson(contract.getCreateUser());
		Integer status = Integer.valueOf(contract.getStatus());
		User cUser = contract.getCreateUser();
		List<User> ccs = new ArrayList<User>();
		ccs.add(cUser);
		List<User> userlist = new ArrayList<User>();
		userlist.add(cUser);
		if (status > 2) {
			List<User> templist = userService.getUserByTypeAndAgent(3, contract.getAreaId(), contract
					.getContractProject().getPm().getId(), contract.getContractProject().getBd().getLeaderUserId());
			ccs.addAll(templist);
		}
		if (status > 3) {
			List<User> templist2 = userService.getUserByTypeAndAgent(contract.getContractProject().getBusinessType()
					.getId(), contract.getAreaId(), contract.getContractProject().getPm().getId(), contract
					.getContractProject().getBd().getLeaderUserId());
			ccs.addAll(templist2);
			List<User> financelist = userService.getUserByTypeAndAgent(8, contract.getAreaId(), contract
					.getContractProject().getPm().getId(), contract.getContractProject().getBd().getLeaderUserId());
			ccs.addAll(financelist);
		}
		StringBuffer subject = new StringBuffer("★★合同被退回：客户“ ");
		subject.append(contract.getContractProject().getContractClient().getName()).append(" ”的项目“ ")
				.append(contract.getContractProject().getName()).append("”下的合同被退回");
		StringBuffer msg = new StringBuffer("客户“ ");
		msg.append(contract.getContractProject().getContractClient().getName())
				.append(" ”的项目“ ")
				.append(contract.getContractProject().getName())
				.append("”下的合同，</br>由于 :")
				.append(reason)
				.append("原因，被")
				.append(createUser.getName())
				.append(" 退回。</br>请修改好合同后，重启审核流程。系统登录地址-><a href=\"http://192.168.1.29/finance/login!show.action\">合同管理系统</a>");
		EmailUtils.sendSimpleEmail(createUser.getEmail(), createUser.getName(), userlist, ccs, subject.toString(),
				msg.toString());
		File oldFile = new File(request.getSession().getServletContext().getRealPath("") + "/contract/" + contractId
				+ contract.getPrefix());
		FileUtils.copyFile(oldFile, new File(request.getSession().getServletContext().getRealPath("") + "/contract/"
				+ contractId + version.getVersion() + contract.getPrefix()));

		contract.setStatus("2");
		contract.setDepartApprove(0);
		contract.setFinanceApprove(0);
		contractService.saveVersion(version);
		contractService.save(contract);
		return "none";
	}

	public ContractService getContractService() {
		return contractService;
	}

	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public Integer getContractId() {
		return contractId;
	}

	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}

	public SearchView getSearchView() {
		return searchView;
	}

	public void setSearchView(SearchView searchView) {
		this.searchView = searchView;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}