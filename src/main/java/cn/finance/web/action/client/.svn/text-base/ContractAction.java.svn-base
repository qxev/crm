package cn.finance.web.action.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.finance.model.Contract;
import cn.finance.model.ContractProject;
import cn.finance.model.SysInfo;
import cn.finance.model.User;
import cn.finance.service.BdService;
import cn.finance.service.BusinessTypeService;
import cn.finance.service.ContractClientService;
import cn.finance.service.ContractProjectService;
import cn.finance.service.ContractService;
import cn.finance.service.IndustryService;
import cn.finance.service.SysInfoService;
import cn.finance.service.UserService;
import cn.finance.util.Constant;
import cn.finance.util.DateUtil;
import cn.finance.util.EmailUtils;
import cn.finance.util.ExcelUtils;
import cn.finance.util.Struts2Utils;
import cn.finance.web.action.base.BaseAction;
import cn.finance.web.model.SearchView;
import cn.finance.web.model.SelectboxView;
import cn.finance.web.servlet.UserSession;
import cn.springside.modules.orm.Page;

import com.opensymphony.xwork2.ActionContext;

public class ContractAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ContractService contractService;

	@Autowired
	private ContractProjectService contractProjectService;

	@Autowired
	private ContractClientService contractClientService;

	@Autowired
	private SysInfoService sysInfoService;

	@Autowired
	private BdService bdService;

	@Autowired
	private IndustryService industryService;

	@Autowired
	private BusinessTypeService businessTypeService;

	@Autowired
	private UserService userService;

	private Contract contractPage;

	private Integer contractId;

	private ArrayList<SelectboxView> statusBoxs;

	private Page<Contract> page = new Page<Contract>(30);

	private SearchView searchView;

	private File uploadFile;

	private String uploadFileFileName;

	public String upload() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		if (uploadFileFileName == null) {
			this.addActionMessage("请选择上传文件！");
		} else {
			String prefix = uploadFileFileName.substring(uploadFileFileName.lastIndexOf("."));
			FileUtils.copyFile(uploadFile, new File(request.getSession().getServletContext().getRealPath("")
					+ "/contract/" + contractId + prefix));
			Contract contract = contractService.getContractById(contractId);
			contract.setStatus("1");
			contract.setLastRemind(new Date());
			contract.setPrefix(prefix);
			contractService.save(contract);
			this.addActionMessage("上传合同成功！");
		}
		return contractList();
	}

	public String uploadFinal() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		if (uploadFileFileName == null) {
			this.addActionMessage("请选择上传文件！");
		} else {
			String prefix = uploadFileFileName.substring(uploadFileFileName.lastIndexOf("."));
			FileUtils.copyFile(uploadFile, new File(request.getSession().getServletContext().getRealPath("")
					+ "/final/" + contractId + prefix));
			Contract contract = contractService.getContractById(contractId);
			contract.setStatus("12");
			contract.setFinalPrefix(prefix);
			contractService.save(contract);
			this.addActionMessage("合同扫描版上传成功！");
		}
		return contractList();
	}

	public String remind() {
		UserSession userSession = (UserSession) ActionContext.getContext().getSession().get(Constant.USER_SESSION_KEY);
		User applyUser = userService.findUser(userSession.getUserId());
		Contract contract = contractService.getContractById(contractId);
		contract.setLastRemind(new Date());
		contractService.save(contract);
		User createUser = contract.getCreateUser();
		List<User> ccs = new ArrayList<User>();
		ccs.add(createUser);
		Integer status = Integer.valueOf(contract.getStatus());
		if (status > 3 && status < 9) {
			List<User> templist = userService.getUserByTypeAndAgent(3, contract.getAreaId(), contract
					.getContractProject().getPm().getId(), contract.getContractProject().getBd().getLeaderUserId());
			ccs.addAll(templist);
		}
		if (status > 2 && status < 12) {
			List<User> userlist = userService.getUserByTypeAndAgent(status, contract.getAreaId(), contract
					.getContractProject().getPm().getId(), contract.getContractProject().getBd().getLeaderUserId());
			List<User> financeList = userService.getUserByTypeAndAgent(8, contract.getAreaId(), contract
					.getContractProject().getPm().getId(), contract.getContractProject().getBd().getLeaderUserId());
			if (status > 3 && status < 8) {
				userlist.addAll(financeList);
			} else if (status == 9) {
				userlist = new ArrayList();
				userlist.add(createUser);
				List<User> templist = userService.getUserByTypeAndAgent(contract.getContractProject().getBusinessType()
						.getId(), contract.getAreaId(), contract.getContractProject().getPm().getId(), contract
						.getContractProject().getBd().getLeaderUserId());
				ccs.addAll(templist);
				ccs.addAll(financeList);
			} else if (status == 10) {
				ccs.addAll(financeList);
			} else if (status == 11) {
				userlist = new ArrayList();
				userlist.add(createUser);
				ccs = null;
			}
			StringBuffer subject = new StringBuffer("★☆ ");
			if (status == 11) {
				subject.append("请尽快上传扫描版合同：").append(contract.getContractProject().getName());
			} else if (status == 10) {
				subject.append("Waitint for CEO to Check ：please approve the contract of "
						+ contract.getContractProject().getName());
			} else {
				subject.append(contract.getStatusDisplay()).append("阶段： 请审批 “")
						.append(contract.getContractProject().getContractClient().getName()).append(" ”,在项目 “")
						.append(contract.getContractProject().getName()).append(" ”下的合同");
			}
			StringBuffer msg = new StringBuffer("");
			if (status == 11) {
				msg.append("请尽快上传扫描版合同：").append(contract.getContractProject().getName()).append("<br/>");
			} else if (status == 10) {
				msg.append("The contract of '").append(contract.getContractProject().getName()).append("' from '")
						.append(contract.getContractProject().getContractClient().getName())
						.append("’ requires approval，");
				Date remineDate = contract.getRemindTime();
				if (remineDate != null) {
					String dateTemp = DateUtil.dateToString(remineDate);
					msg.append(" the approval deadline is ").append(dateTemp).append(".");
				}
				msg.append("Please login in Contract Management System as early as possible. -> <a href=\"http://192.168.1.29/finance/login!show.action\">Contract System</a><br/>");
				msg.append("The contract summary is as follows: <br/>");
			} else {
				msg.append("“ ").append(contract.getContractProject().getContractClient().getName()).append(" ”,在项目“ ")
						.append(contract.getContractProject().getName()).append(" ”下的合同，");
				Date remineDate = contract.getRemindTime();
				if (remineDate != null) {
					String dateTemp = DateUtil.dateToString(remineDate, "yyyy年MM月dd日");
					msg.append("需要在").append(dateTemp).append("前被审核，");
				}
				msg.append(
						"审核，请尽快登陆系统 -><a href=\"http://192.168.1.29/finance/login!show.action?userLoginView.contractId=")
						.append(contract.getId()).append("\">合同管理系统</a><br/>");
				msg.append("英文摘要如下:<br/>");
			}
			msg.append("Darwin Company Name: ").append(contract.getContractProject().getDarwinName().getName())
					.append("<br/>");
			msg.append("Client Company Name: ").append(contract.getContractProject().getContractClient().getName())
					.append("<br/>");
			msg.append("Project Name: ").append(contract.getContractProject().getName()).append("<br/>");
			msg.append("Project Categoty: ").append(contract.getContractProject().getBusinessType().getName())
					.append("<br/>");
			msg.append("Product: ").append(contract.getProduct()).append("<br/>");
			msg.append("Contract Start Date: ").append(contract.getContractStartDate()).append("<br/>");
			msg.append("Contract End Date: ").append(contract.getContractEndDate()).append("<br/>");
			msg.append("Monthly Budget: ").append(contract.getMonthlyBudget()).append("<br/>");
			msg.append("Service Fee: ").append(contract.getServiceFee()).append("<br/>");
			msg.append("ROI: ").append(contract.getRoi()).append("<br/>");
			msg.append("Payment Term: ").append(contract.getPaymentTerm()).append("<br/>");
			msg.append("Special Terms: ").append(contract.getSpecialTerms()).append("<br/>");
			msg.append("BD Name: ").append(contract.getContractProject().getBd().getName()).append("<br/>");
			msg.append("Lead Name: ").append(contract.getLeadName()).append("<br/>");
			msg.append("Source: ").append(contract.getContractProject().getContractClient().getTypeDisplayEn())
					.append("<br/>");
			EmailUtils.sendSimpleEmail(applyUser.getEmail(), applyUser.getName(), userlist, ccs, subject.toString(),
					msg.toString());
		}
		return "none";
	}

	public void initSearchView(Integer pamId) throws UnsupportedEncodingException {
		if (searchView == null) {
			searchView = new SearchView();
			if (page.getFilter() != null) {
				String[] pars = StringUtils.split(page.getFilter(), "|");
				if (pars.length == 12) {
					String client = pars[0];
					String project = pars[1];
					searchView.setClientName("0".equals(client) ? "" : client);
					searchView.setProjectName("0".equals(project) ? "" : project);
					searchView.setStatusId(NumberUtils.toInt(pars[2]));
					searchView.setBdId(NumberUtils.toInt(pars[3]));
					searchView.setClientTypeId(NumberUtils.toInt(pars[4]));
					searchView.setPmId(NumberUtils.toInt(pars[5]));
					searchView.setIndustryId(NumberUtils.toInt(pars[6]));
					searchView.setBusinessTypeId(NumberUtils.toInt(pars[7]));
					searchView.setAreaId(NumberUtils.toInt(pars[8]));
					searchView.setLeadId(NumberUtils.toInt(pars[9]));
					searchView.setClientId(NumberUtils.toInt(pars[10]));
					searchView.setProjectId(NumberUtils.toInt(pars[11]));
				} else {
					UserSession userSession = (UserSession) ActionContext.getContext().getSession()
							.get(Constant.USER_SESSION_KEY);
					User user = userService.findUser(userSession.getUserId());
					Integer roleId = user.getRole().getId();
					Integer agentId = user.getAgentRole().getId();
					if (agentId != 0)
						roleId = agentId;
					if (roleId > 0 && roleId < 11) {
						StringBuffer filter = new StringBuffer("0|");
						if (contractId != null) {
							Contract contract = contractService.getContractById(contractId);
							filter.append(contract.getContractProject().getName()).append("|");
							searchView.setProjectName(contract.getContractProject().getName());
						} else {
							filter.append("0|");
						}
						if (pamId == 2) {
							searchView.setStatusId(1);
							filter.append("1");
						} else if (roleId == 1 || roleId == 2) {
							searchView.setStatusId(0);
							filter.append("0");
						} else {
							searchView.setStatusId(roleId);
							filter.append(roleId);
						}
						filter.append("|0|");
						if (roleId > 2 && roleId < 8) {
							filter.append(roleId);
							searchView.setClientTypeId(roleId);
						} else {
							filter.append("0");
							searchView.setClientTypeId(0);
						}
						if (roleId == 2) {
							filter.append("|").append(user.getId());
							searchView.setPmId(user.getId());
						} else {
							filter.append("|0");
							searchView.setPmId(0);
						}

						filter.append("|0|0|-1");
						searchView.setAreaId(-1);
						if (roleId == 4 || roleId == 5) {
							filter.append("|").append(user.getPmId());
							searchView.setLeadId(user.getPmId());
						} else if (roleId == 3) {
							filter.append("|").append(user.getId());
							searchView.setLeadId(user.getId());
						} else {
							filter.append("|0");
							searchView.setLeadId(0);
						}
						filter.append("|0|0");
						page.setFilter(filter.toString());
					}
				}
			}
		} else {
			StringBuffer filter = new StringBuffer();
			filter.append("".equals(searchView.getClientName()) ? "0" : searchView.getClientName()).append("|")
					.append("".equals(searchView.getProjectName()) ? "0" : searchView.getProjectName()).append("|")
					.append(searchView.getStatusId()).append("|").append(searchView.getBdId()).append("|")
					.append(searchView.getClientTypeId()).append("|").append(searchView.getPmId()).append("|")
					.append(searchView.getIndustryId()).append("|").append(searchView.getBusinessTypeId()).append("|")
					.append(searchView.getAreaId()).append("|").append(searchView.getLeadId()).append("|")
					.append(searchView.getClientId()).append("|").append(searchView.getProjectId());
			page.setFilter(filter.toString());
		}
		searchView.setIndustrys(industryService.getAll());
		searchView.setBds(bdService.getAll());
		searchView.setBusinessTypes(businessTypeService.getAll());
		searchView.setContractClients(contractClientService.getAll());
		searchView.setContractProjects(contractProjectService.getAll());
		if (page.getOrderBy() == null) {
			page.setOrderBy("c.createAt");
			page.setOrder("desc");
		}
	}

	public String saveContract() throws Exception {
		UserSession userSession = (UserSession) ActionContext.getContext().getSession().get(Constant.USER_SESSION_KEY);
		User createUser = userService.findUser(userSession.getUserId());
		Contract saveContract = contractService.create();
		if (contractId != null) {
			saveContract = contractService.getContractById(contractId);
		}
		if (contractId == null) {
			ContractProject project = contractProjectService.getContractProjectById(searchView.getProjectId());
			saveContract.setContractProject(project);
			StringBuffer codesb = getCode(project);
			saveContract.setCode(codesb.toString());

			saveContract.setCreateUser(createUser);
			saveContract.setChargePerson(createUser);
			saveContract.setStatus("1");
			saveContract.setVersionNum(1);
			saveContract.setFinanceApprove(0);
			saveContract.setDepartApprove(0);
			saveContract.setAreaId(createUser.getAreaId());
		}
		contractService.save(saveContract);
		contractId = saveContract.getId();
		this.addActionMessage("创建合同成功！");
		searchView.setPmId(createUser.getId());
		initSearchView(2);
		Struts2Utils.getRequest().setAttribute("nav", "6");
		Struts2Utils.getRequest().setAttribute("subNav", "7");
		page.setResult(contractService.getAll(page));
		page.setTotalCount(contractService.getTotalCount(page));
		return SUCCESS;
	}

	public String contractList() throws Exception {
		initSearchView(0);
		Struts2Utils.getRequest().setAttribute("nav", "6");
		Struts2Utils.getRequest().setAttribute("subNav", "8");
		page.setResult(contractService.getAll(page));
		page.setTotalCount(contractService.getTotalCount(page));

		return SUCCESS;
	}

	public void excelOutput() throws IOException {
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("file");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String("合同列表.xls".getBytes("GB2312"), "iso8859-1"));
		String path = FileUtils.class.getResource("/").getPath();
		path = path.substring(0, path.lastIndexOf("/"));
		path = path.substring(0, path.lastIndexOf("/") + 1);
		path = path + "template/contractList.xls";
		List<Map<String, Object>> contracts = contractService.getExcel(page);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("contract", contracts);
		ExcelUtils.transformXLS(path, map, null).write(Struts2Utils.getResponse().getOutputStream());
	}

	public void finalPdf() throws IOException {
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("file");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String("合同扫描版.pdf".getBytes("GB2312"), "iso8859-1"));
		String path = FileUtils.class.getResource("/").getPath();
		path = path.substring(0, path.lastIndexOf("/"));
		path = path.substring(0, path.lastIndexOf("/") + 1);
		Contract contract = contractService.getContractById(contractId);
		path = path + "final/" + contractId + contract.getFinalPrefix();
		path = path.replaceAll("/WEB-INF", "");
		File file = new File(path);
		int fileLength = (int) file.length();
		response.setContentLength(fileLength);
		if (fileLength != 0) {
			InputStream inStream = new FileInputStream(file);
			byte[] buf = new byte[4096];
			/* 创建输出流 */
			ServletOutputStream servletOS = response.getOutputStream();
			int readLength;
			while (((readLength = inStream.read(buf)) != -1)) {
				servletOS.write(buf, 0, readLength);
			}
			inStream.close();
			servletOS.flush();
			servletOS.close();
		}
	}

	private StringBuffer getCode(ContractProject project) {
		SysInfo contractCode = sysInfoService.getSysInfoByName("contract_code");
		Integer code = Integer.valueOf(contractCode.getValue());
		StringBuffer varCode = new StringBuffer();
		if (code < 10) {
			varCode.append("00").append(code);
		} else if (code < 100) {
			varCode.append("0").append(code);
		} else {
			varCode.append(code);
		}
		code++;
		contractCode.setValue(code.toString());
		sysInfoService.saveSysInfo(contractCode);
		Date today = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMM");
		StringBuffer codesb = new StringBuffer();
		codesb.append("DW-").append(project.getBusinessType().getName()).append("-").append(dateFormat.format(today))
				.append(varCode);
		return codesb;
	}

	public Contract getContractPage() {
		return contractPage;
	}

	public void setContractPage(Contract contractPage) {
		this.contractPage = contractPage;
	}

	public ArrayList<SelectboxView> getStatusBoxs() {
		if (statusBoxs == null) {
			statusBoxs = new ArrayList<SelectboxView>();
			statusBoxs.add(new SelectboxView("-1", "全部"));
			statusBoxs.add(new SelectboxView("0", "审核中"));
			statusBoxs.add(new SelectboxView("1", "合同草稿"));
			statusBoxs.add(new SelectboxView("2", "合同被退回"));
			statusBoxs.add(new SelectboxView("3", "等待BD主管审核"));
			statusBoxs.add(new SelectboxView("4", "等待SEM主管审核"));
			statusBoxs.add(new SelectboxView("5", "等待SEO主管审核"));
			statusBoxs.add(new SelectboxView("6", "等待UT主管审核"));
			statusBoxs.add(new SelectboxView("7", "等待SCL主管审核"));
			statusBoxs.add(new SelectboxView("8", "等待FIN主管审核"));
			statusBoxs.add(new SelectboxView("9", "等待客户确认"));
			statusBoxs.add(new SelectboxView("10", "等待CEO审核"));
			statusBoxs.add(new SelectboxView("11", "审核完毕"));
			statusBoxs.add(new SelectboxView("12", "合同扫描完毕"));
		}
		return statusBoxs;
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

	public Page<Contract> getPage() {
		return page;
	}

	public void setPage(Page<Contract> page) {
		this.page = page;
	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}
}