package cn.finance.web.action.system;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.finance.model.SysInfo;
import cn.finance.service.SysInfoService;
import cn.finance.util.Struts2Utils;
import cn.finance.web.action.base.BaseAction;

public class SysAlertAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private SysInfoService sysInfoService;

	// 提醒天数
	private SysInfo alertDays;

	// 统计天数
	private SysInfo caleDays;

	// 参数
	private SysInfo parameter;

	public String sysAlertSet() throws Exception {
		Struts2Utils.getRequest().setAttribute("nav", "5");
		Struts2Utils.getRequest().setAttribute("subNav", "6");
		String days = alertDays.getValue();
		String cale = caleDays.getValue();
		String par = parameter.getValue();
		alertDays=sysInfoService.getSysInfoByName("alert_days");
		alertDays.setUpdateAt(new Date());
		alertDays.setValue(days);
		sysInfoService.saveSysInfo(alertDays);
		caleDays=sysInfoService.getSysInfoByName("cale_days");
		caleDays.setUpdateAt(new Date());
		caleDays.setValue(cale);
		sysInfoService.saveSysInfo(caleDays);
		parameter=sysInfoService.getSysInfoByName("parameter");
		parameter.setUpdateAt(new Date());
		parameter.setValue(par);
		sysInfoService.saveSysInfo(parameter);
        this.addActionMessage("设置成功！");
		return sysAlertInit();
	}
	
	public String sysAlertInit() throws Exception {
		Struts2Utils.getRequest().setAttribute("nav", "5");
		Struts2Utils.getRequest().setAttribute("subNav", "6");
		alertDays=sysInfoService.getSysInfoByName("alert_days");
		caleDays=sysInfoService.getSysInfoByName("cale_days");
		parameter=sysInfoService.getSysInfoByName("parameter");
		return SUCCESS;
	}
	
	public void validateSysAlertSet(){
		Struts2Utils.getRequest().setAttribute("nav", "5");
		Struts2Utils.getRequest().setAttribute("subNav", "6");
		if (StringUtils.isBlank(alertDays.getValue())) {
			this.addActionError("请填写提醒天数");
		} else if(!alertDays.getValue().matches("^[0-9]*$")){
			this.addActionError("输入的提醒天数不为整数");
		} else if (StringUtils.isBlank(caleDays.getValue())) {
			this.addActionError("请填写统计天数");
		} else if(!caleDays.getValue().matches("^[0-9]*$")){
			this.addActionError("输入的统计天数不为整数");
		} else if(!NumberUtils.isNumber(parameter.getValue())){
			this.addActionError("请填写正确的参数");
		}
	}

	public SysInfo getAlertDays() {
		return alertDays;
	}

	public void setAlertDays(SysInfo alertDays) {
		this.alertDays = alertDays;
	}

	public SysInfo getParameter() {
		return parameter;
	}

	public void setParameter(SysInfo parameter) {
		this.parameter = parameter;
	}

	public SysInfo getCaleDays() {
		return caleDays;
	}

	public void setCaleDays(SysInfo caleDays) {
		this.caleDays = caleDays;
	}
}