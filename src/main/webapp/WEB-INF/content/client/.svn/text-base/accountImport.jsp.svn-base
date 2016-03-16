<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<script type="text/javascript" src="${ctx}/js/calendar.js"></script>
<script type="text/javascript">
function checkAndSubmit(){
	var startDate = document.getElementById("startDate").value;
	var endDate = document.getElementById("endDate").value;
	var totalBalance = document.getElementById("totalBalance").value;
    if(startDate==''){
		alert("请输入开始时间");
		return;
	} else if (endDate == '') {
		alert("请输入结束时间");
		return;
	} else if (endDate < startDate && endDate!='') {
		alert("结束时间必须大于开始时间");
		return;
	} else if (totalBalance==''||isNaN(Number(totalBalance))){
		alert("请输入正确的金额");
		return;
    } 
	document.getElementById("accountImportForm").submit();
}
</script>
<div class="content">
<s:form id="accountImportForm" action="/client/editAccount!importData.action">
	<s:hidden name="accountId" id="accountId" value="%{accountId}" />
	<s:hidden name="filter" value="%{filter}" />
	<s:hidden name="pageNo" value="%{pageNo}" />
	<div style="margin:0 auto;width:65%;">
	<font color="red"><s:actionerror/></font>
	<font color="green"><s:actionmessage/></font>
		<table class="report" borderColor="#f2f2f2" border="1" width="100%">
		<tr>
			<td align="center" width="80%" colspan="2" class="green-bg1"><h1>导入数据</h1></td>
		</tr>
			<tr>
				<td width="20%" align="right">开始日期：</td>
				<td><s:textfield id="startDate" name="accountView.discountStartDate" size="10" onClick="showcalendar(event, this);" readonly="readOnly" /></td>
			</tr>
			<tr>
				<td align="right">结束日期：</td>
				<td><s:textfield id="endDate" name="accountView.discountEndDate" size="10" onClick="showcalendar(event, this);" readonly="readOnly"/></td></td>
			</tr>
			<tr>
				<td align="right">金额：</td>
				<td><s:textfield name="accountView.totalBalance" size="6" id="totalBalance" /> RMB </td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><input type="button" onClick="checkAndSubmit()" value="导入"/></td>
			</tr>
		</table>
	</div>
</s:form>
</div>
