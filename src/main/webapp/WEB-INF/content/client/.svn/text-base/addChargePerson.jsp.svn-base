<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<table borderColor="#f2f2f2" border="1">
<script type="text/javascript">
if (${projectId}==0){
	closeAndRefresh();
}
function closeAndRefresh(){
	window.opener.location.href = window.opener.location.href; 
	if (window.opener.progressWindow) 
	{ 
	window.opener.progressWindow.close(); 
	} 
	window.close(); 
	form.submit();
}
</script>
<s:form action="addChargePerson.action">
    <s:hidden name="projectId" id="projectId" value="%{projectId}" />
	<s:hidden name="chargePersonId" id="chargePersonId" value="%{chargePersonId}" />
	<tr align="center">
		<td colspan="2">${title}联系人</td>
	</tr>
	<tr>
		<td width="50px">姓名</td>
		<td><s:textfield name="chargePerson.name"></s:textfield></td>	
	</tr>
	<tr>
		<td>电话</td>
		<td><s:textfield name="chargePerson.phone"></s:textfield></td>	
	</tr>
	<tr>
		<td>地址</td>
		<td><s:textfield name="chargePerson.address"></s:textfield></td>	
	</tr>
	<tr>
		<td>邮件</td>
		<td><s:textfield name="chargePerson.email"></s:textfield></td>	
	</tr>
	<tr>
		<td>传真</td>
		<td><s:textfield name="chargePerson.fax"></s:textfield></td>	
	</tr>
	<tr>
	   <td align="center" colspan="2">
	     <input type="button" value="${title}" onClick="form.submit();"/>
	   </td>
	</tr>
</s:form>
</table>
