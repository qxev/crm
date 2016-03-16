package cn.finance.web.action.json;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import cn.finance.model.Pm;
import cn.finance.service.PmService;

import com.opensymphony.xwork2.Action;

public class GetPmAction extends JdbcDaoSupport  {

	private static final long serialVersionUID = 1L;

	private Integer businessTypeId;
	
	private PmService pmService;
	
	private List<Pm> pms = new ArrayList<Pm>();

	/**
	 * 根据客户id查找所有的项目
	 * @return
	 */
	public String execute(){
		pms = pmService.getByBusinessType(businessTypeId);
		return Action.SUCCESS;
	}

	public Integer getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(Integer businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

	public PmService getPmService() {
		return pmService;
	}

	public void setPmService(PmService pmService) {
		this.pmService = pmService;
	}

	public List<Pm> getPms() {
		return pms;
	}

	public void setPms(List<Pm> pms) {
		this.pms = pms;
	}
	
}
