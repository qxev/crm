package cn.finance.web.action.system;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.finance.model.Bd;
import cn.finance.service.BdService;
import cn.finance.util.Struts2Utils;
import cn.finance.web.action.base.BaseAction;
import cn.springside.modules.orm.Page;

public class BdAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private BdService bdService;
	
	private Page<Bd> page = new Page<Bd>(30);
	
	private Integer id;

	// 基本属性
	private Bd bd;

	public String bdList() throws Exception{
		Struts2Utils.getRequest().setAttribute("nav", "5");
		Struts2Utils.getRequest().setAttribute("subNav", "4");
		page.setResult(bdService.getAll(page));
		page.setTotalCount(bdService.getTotalCount(page));
		return SUCCESS;
	}
	
	public String bdAddPage() throws Exception {
		Struts2Utils.getRequest().setAttribute("nav", "5");
		Struts2Utils.getRequest().setAttribute("subNav", "4");
		if (null!=id) {
			bd=bdService.getBdById(id);
		}
		return SUCCESS;
	}
	
	public String bdAdd() throws Exception {
		Struts2Utils.getRequest().setAttribute("nav", "5");
		Struts2Utils.getRequest().setAttribute("subNav", "4");
		if (null!=id) {
			String name=bd.getName();
			bd=bdService.getBdById(id);
			bd.setName(name);
			bd.setUpdateAt(new Date());
			this.addActionMessage("修改Bd成功！");
		}else {
			bd.setCreateAt(new Date());
			bd.setDeleted("0");
			this.addActionMessage("新增Bd成功！");
		}
		bdService.save(bd);
		return SUCCESS;
	}
	
	public void validateBdAdd(){
		if (StringUtils.isBlank(bd.getName())) {
			this.addActionError("输入的BD人名");
		}else if (null!=bdService.getBdByName(bd.getName(),id)) {
			this.addActionError("输入的BD人名重名");
		}
	}
	
	public String bdDelete() throws Exception {
		Struts2Utils.getRequest().setAttribute("nav", "5");
		Struts2Utils.getRequest().setAttribute("subNav", "4");
		bd=bdService.getBdById(id);
		bd.setDeleted("1");
		bd.setUpdateAt(new Date());
		bdService.save(bd);
		this.addActionMessage("删除Bd成功！");
		return SUCCESS;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Page<Bd> getPage() {
		return page;
	}
	
	public void setPage(Page<Bd> page) {
		this.page = page;
	}
	
	
	public Bd getBd() {
		return bd;
	}

	public void setBd(Bd bd) {
		this.bd = bd;
	}
}