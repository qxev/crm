package cn.finance.web.action.system;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.finance.model.BusinessType;
import cn.finance.model.Pm;
import cn.finance.service.BusinessTypeService;
import cn.finance.service.PmService;
import cn.finance.util.Struts2Utils;
import cn.finance.web.action.base.BaseAction;
import cn.springside.modules.orm.Page;

public class PmAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PmService pmService;
	
	@Autowired
	private BusinessTypeService businessTypeService;
	
	private List <BusinessType> businessTypes;
	
	private Page<Pm> page = new Page<Pm>(30);
	
	private Integer id;

	// 基本属性
	private Pm pm;
	
	public String pmList() throws Exception{
		Struts2Utils.getRequest().setAttribute("nav", "5");
		Struts2Utils.getRequest().setAttribute("subNav", "3");
		page.setResult(pmService.getAll(page));
		page.setTotalCount(pmService.getTotalCount(page));
		return SUCCESS;
	}
	
	public String pmAddPage() throws Exception {
		Struts2Utils.getRequest().setAttribute("nav", "5");
		Struts2Utils.getRequest().setAttribute("subNav", "3");
		if (null!=id) {
			pm=pmService.getDetailById(id);
		}
		businessTypes = businessTypeService.getAll();
		return SUCCESS;
	}
	
	public String pmAdd() throws Exception {
		Struts2Utils.getRequest().setAttribute("nav", "5");
		Struts2Utils.getRequest().setAttribute("subNav", "3");
		BusinessType businessType = businessTypeService.getBusinessTypeById(pm.getBusinessType().getId());
		if (id != null) {
			String name=pm.getName();
			pm=pmService.getPmById(id);
			pm.setName(name);
			pm.setBusinessType(businessType);
			pm.setUpdateAt(new Date());
			this.addActionMessage("修改分析师成功！");
		}else {
			pm.setCreateAt(new Date());
			pm.setDeleted("0");
			pm.setBusinessType(businessType);
			this.addActionMessage("新增分析师成功！");
		}
		pmService.save(pm);
		return SUCCESS;
	}
	
	public void validatePmAdd(){
		Struts2Utils.getRequest().setAttribute("nav", "5");
		Struts2Utils.getRequest().setAttribute("subNav", "3");
		if (StringUtils.isBlank(pm.getName())) {
			this.addActionError("输入的分析师名称");
		}else if (null!=pmService.getPmByName(pm.getName(),id)) {
			this.addActionError("输入的分析师名称重名");
		}
	}
	
	public String pmDelete() throws Exception {
		Struts2Utils.getRequest().setAttribute("nav", "5");
		Struts2Utils.getRequest().setAttribute("subNav", "3");
		pm=pmService.getPmById(id);
		pm.setDeleted("1");
		pm.setUpdateAt(new Date());
		pmService.save(pm);
		this.addActionMessage("删除分析师成功！");
		return SUCCESS;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Page<Pm> getPage() {
		return page;
	}
	
	public void setPage(Page<Pm> page) {
		this.page = page;
	}
	
	public Pm getPm() {
		return pm;
	}
	
	public void setPm(Pm pm) {
		this.pm = pm;
	}

	public List<BusinessType> getBusinessTypes() {
		return businessTypes;
	}

	public void setBusinessTypes(List<BusinessType> businessTypes) {
		this.businessTypes = businessTypes;
	}
	
}