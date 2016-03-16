package cn.finance.web.action.fee;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.finance.model.Channel;
import cn.finance.model.Project;
import cn.finance.model.bean.SeBean;
import cn.finance.service.ChannelService;
import cn.finance.service.ProjectService;
import cn.finance.service.SupplementHistoryService;
import cn.finance.util.ExcelUtils;
import cn.finance.util.FileUtils;
import cn.finance.util.Struts2Utils;
import cn.finance.web.action.base.BaseAction;
import cn.springside.modules.orm.Page;

public class SeAmountHistoryAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SupplementHistoryService supplementHistoryService;

	@Autowired
	private ChannelService channelService;

	@Autowired
	private ProjectService projectService;

	// 基本属性
	private SeBean seBean;

	private Page<SeBean> page = new Page<SeBean>(30);
	
	private String startDate = "";
	
	private String endDate = "";
	
	private Integer id;
	
	private String name;
	
	private BigDecimal sum;

	public String seAmountList() throws Exception {
		Struts2Utils.getRequest().setAttribute("nav", "4");
		Struts2Utils.getRequest().setAttribute("subNav", "2");
		StringBuffer filter = new StringBuffer();
		filter.append("".equals(startDate)?"0":startDate).append("|").append("".equals(endDate)?"0":endDate);
		page.setFilter(filter.toString());
		sum=supplementHistoryService.getSumSeAmountHistoryByChannel(page);
		page.setResult(supplementHistoryService.getAllSeAmountHistoryByChannel(page,false));
		page.setTotalCount(supplementHistoryService.getTotalSeAmountHistoryCountByChannel(page));
		return SUCCESS;
	}
	
	public void channelExcelOutput() throws IOException {
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("file");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String("渠道级别搜索引擎充值报表.xls".getBytes("GB2312"), "iso8859-1"));
		String path = FileUtils.getProjectUrl() + "template/seAmountList.xls";
		List<SeBean> seBeans = supplementHistoryService.getAllSeAmountHistoryByChannel(page, true);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("seBean", seBeans);
		short []cols = cn.finance.util.StringUtils.getShouts(page.getColumns());
		ExcelUtils.transformXLS(path, map, cols).write(
				Struts2Utils.getResponse().getOutputStream());
	}
	
	public String seAmountListByProject() throws Exception {
		Struts2Utils.getRequest().setAttribute("nav", "4");
		Struts2Utils.getRequest().setAttribute("subNav", "2");
		if (id!=null){
			StringBuffer filter = new StringBuffer();
			filter.append("".equals(startDate)?"0":startDate).append("|").append("".equals(endDate)?"0":endDate).append("|").append(id);
			page.setFilter(filter.toString());
		} else {
			String []pars = StringUtils.split(page.getFilter(),"|");
			id = Integer.valueOf(pars[2]);
		}
		Channel channel = channelService.getById(id);
		name = channel.getName();
		sum=supplementHistoryService.getSumSeAmountHistoryByProject(page);
		page.setResult(supplementHistoryService.getAllSeAmountHistoryByProject(page,false));
		page.setTotalCount(supplementHistoryService.getTotalSeAmountHistoryCountByProject(page));
		return SUCCESS;
	}
	
	public void projectExcelOutput() throws IOException {
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("file");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String("项目级别搜索引擎充值报表.xls".getBytes("GB2312"), "iso8859-1"));
		String path = FileUtils.getProjectUrl() + "template/seAmountListByProject.xls";
		List<SeBean> seBeans = supplementHistoryService.getAllSeAmountHistoryByProject(page,true);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("seBean", seBeans);
		short []cols = cn.finance.util.StringUtils.getShouts(page.getColumns());
		ExcelUtils.transformXLS(path, map, cols).write(
				Struts2Utils.getResponse().getOutputStream());
	}
	
	public String seAmountListByAccount() throws Exception {
		Struts2Utils.getRequest().setAttribute("nav", "4");
		Struts2Utils.getRequest().setAttribute("subNav", "2");
		if (id!=null){
			StringBuffer filter = new StringBuffer();
			filter.append("".equals(startDate)?"0":startDate).append("|").append("".equals(endDate)?"0":endDate).append("|").append(id);
			page.setFilter(filter.toString());
		} else {
			String []pars = StringUtils.split(page.getFilter(),"|");
			id = Integer.valueOf(pars[2]);
		}
		Project project = projectService.getProjectById(id);
		name = project.getName();
		sum=supplementHistoryService.getSumSeAmountHistoryByAccount(page);
		page.setResult(supplementHistoryService.getAllSeAmountHistoryByAccount(page, false));
		page.setTotalCount(supplementHistoryService.getTotalSeAmountHistoryCountByAccount(page));
		return SUCCESS;
	}
	
	public void accountExcelOutput() throws IOException {
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("file");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String("账户级别搜索引擎充值报表.xls".getBytes("GB2312"), "iso8859-1"));
		String path = FileUtils.getProjectUrl() + "template/seAmountListByAccount.xls";
		List<SeBean> seBeans = supplementHistoryService.getAllSeAmountHistoryByAccount(page,false);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("seBean", seBeans);
		short []cols = cn.finance.util.StringUtils.getShouts(page.getColumns());
		ExcelUtils.transformXLS(path, map, cols).write(
				Struts2Utils.getResponse().getOutputStream());
	}
	
	public SeBean getSeBean() {
		return seBean;
	}
	public void setSeBean(SeBean seBean) {
		this.seBean = seBean;
	}
	public Page<SeBean> getPage() {
		return page;
	}
	public void setPage(Page<SeBean> page) {
		this.page = page;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public BigDecimal getSum() {
		return sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}

}