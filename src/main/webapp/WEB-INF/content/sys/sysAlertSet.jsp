<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<div class="content50">
<table class="report" borderColor="#f2f2f2" border="1">
     <s:form action="sysAlertInit!sysAlertSet.action">
     <font color="red"><s:actionerror/></font>
     <font color="green"><s:actionmessage/></font>
     <tr>
	     <td align="center" width="90%" colspan="2" class="green-bg1"><h1> 设置充值提醒天数</h1></td>
	 </tr>
	 <tr>
	    <td>提醒天数: </td>
	    <td><s:textfield name="alertDays.value" />&nbsp;</td>
	 </tr>
	 <tr>
	    <td>统计天数: </td>
	    <td><s:textfield name="caleDays.value" />&nbsp;</td>
	 </tr>
	 <tr>
	    <td>参数: </td>
	    <td><s:textfield name="parameter.value" />&nbsp;</td>
	 </tr>
	 <tr>
	     <td></td>
	     <td><s:submit value="确定"/></td>
	 </tr>
	 </s:form>
</table>
</div>