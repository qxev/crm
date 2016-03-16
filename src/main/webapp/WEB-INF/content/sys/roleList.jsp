<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<div class="content50">
<s:form action="role!save.action">
<table class="report" borderColor="#f2f2f2" border="1">
    <font color="green"><s:actionmessage/></font>
    <tr>
		<td align="center" width="100%" colspan="2"  class="green-bg1"><h1>权限列表</h1></td>
	</tr>
	<tr align="left">
		<td>BD普通用户</td><td width="490px"><s:checkboxlist list="resources" listKey="id" name="model.bdResourceStr" listValue="operation" value="model.bdResources"/></td>
	</tr>
	<tr align="left">
		<td>BD主管</td><td><s:checkboxlist list="resources" listKey="id" name="model.bdDirectResourceStr" listValue="operation" value="model.bdDirectResources"/></td>
	</tr>
	<tr align="left">
		<td>SEM主管</td><td><s:checkboxlist list="resources" listKey="id" name="model.semDirectResourceStr" listValue="operation" value="model.semDirectResources"/></td>
	</tr>
	<tr align="left">
		<td>SEO主管</td><td><s:checkboxlist list="resources" listKey="id" name="model.seoDirectResourceStr" listValue="operation" value="model.seoDirectResources"/></td>
	</tr>
	<tr align="left">
		<td>AFF主管</td><td><s:checkboxlist list="resources" listKey="id" name="model.affDirectResourceStr" listValue="operation" value="model.affDirectResources"/></td>
	</tr>
	<tr align="left">
		<td>SCL主管</td><td><s:checkboxlist list="resources" listKey="id" name="model.womDirectResourceStr" listValue="operation" value="model.womDirectResources"/></td>
	</tr>
	<tr align="left">
		<td>FIN主管</td><td><s:checkboxlist list="resources" listKey="id" name="model.finDirectResourceStr" listValue="operation" value="model.finDirectResources"/></td>
	</tr>
	<tr align="left">
		<td>秘书</td><td><s:checkboxlist list="resources" listKey="id" name="model.secretaryResourceStr" listValue="operation" value="model.secretaryResources"/></td>
	</tr>
	<tr align="left">
		<td>CEO</td><td><s:checkboxlist list="resources" listKey="id" name="model.ceoResourceStr" listValue="operation" value="model.ceoResources"/></td>
	</tr>
	<tr align="center">
		<td colspan="2"><s:submit value="保存"/></td>
	</tr>
</table>
</s:form>
</div>