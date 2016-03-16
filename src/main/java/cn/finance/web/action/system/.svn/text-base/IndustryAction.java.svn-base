package cn.finance.web.action.system;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.finance.model.Industry;
import cn.finance.service.IndustryService;
import cn.finance.util.Struts2Utils;
import cn.finance.web.action.base.BaseAction;
import cn.springside.modules.orm.Page;

public class IndustryAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IndustryService industryService;
	
	private Page<Industry> page = new Page<Industry>(30);
	
	private Integer id;

	// 基本属性
	private Industry industry;
	
	public String industryList() throws Exception{
		Struts2Utils.getRequest().setAttribute("nav", "5");
		Struts2Utils.getRequest().setAttribute("subNav", "5");
		page.setResult(industryService.getAll(page));
		page.setTotalCount(industryService.getTotalCount(page));
		return SUCCESS;
	}
	
	public String industryAddPage() throws Exception {
		Struts2Utils.getRequest().setAttribute("nav", "5");
		Struts2Utils.getRequest().setAttribute("subNav", "5");
		if (null!=id) {
			industry=industryService.getIndustryById(id);
		}
		return SUCCESS;
	}
	
	public String industryAdd() throws Exception {
		Struts2Utils.getRequest().setAttribute("nav", "5");
		Struts2Utils.getRequest().setAttribute("subNav", "5");
		if (null!=id) {
			String name=industry.getName();
			industry=industryService.getIndustryById(id);
			industry.setName(name);
			industry.setUpdateAt(new Date());
			this.addActionMessage("修改行业成功！");
		}else {
			industry.setCreateAt(new Date());
			industry.setDeleted("0");
			this.addActionMessage("新增行业成功！");
		}
		industryService.save(industry);
		return SUCCESS;
	}
	
	public void validateIndustryAdd(){
		if (StringUtils.isBlank(industry.getName())) {
			this.addActionError("输入的SEM分析师名称");
		}else if (null!=industryService.getIndustryByName(industry.getName(),id)) {
			this.addActionError("输入的SEM分析师名称重名");
		}
	}
	
	public String industryDelete() throws Exception {
		Struts2Utils.getRequest().setAttribute("nav", "5");
		Struts2Utils.getRequest().setAttribute("subNav", "5");
		industry=industryService.getIndustryById(id);
		industry.setDeleted("1");
		industry.setUpdateAt(new Date());
		industryService.save(industry);
		this.addActionMessage("删除行业成功！");
		return SUCCESS;
	}

	public Page<Industry> getPage() {
		return page;
	}

	public void setPage(Page<Industry> page) {
		this.page = page;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Industry getIndustry() {
		return industry;
	}

	public void setIndustry(Industry industry) {
		this.industry = industry;
	}
	
}