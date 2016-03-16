<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<link href="${ctx}/css/styles.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/autocomplete.js"></script>
<div class="content50">
<s:form id="clientForm" action="/client/clientList.action">
<font color="red"><s:actionerror/></font><font color="green"><s:actionmessage/></font>
	<div style="margin:0 auto;width:70%;">
	<table align="center">
		<tr>
			<td>客户名联想:<s:textfield name="searchView.clientName" id="userClient" size="35"/></td>
			<td width="10%" rowspan="4"><input type="image" src="${ctx}/images/search.gif" onClick="userSubmit('clientForm')"/></td>
		</tr>
	</table>
	</div>
<table class="report" borderColor="#f2f2f2" border="1">
	<tr>
		<td align="center" width="80%" colspan="4" class="green-bg1"><h1>客户列表</h1></td>
	</tr>
	<tr>
		<td align="right" colspan="4">
			<report:pages action="clientList.action" excel="clientList!excelOutput.action" page="${page}"/>
		</td>
	</tr>
	<tr align="center">
		<td></td>
		<td><input type="checkbox" checked class="chbx" value="0"/></td>
		<td><input type="checkbox" checked class="chbx" value="1"/></td>
	</tr>
	<tr>
		<th width="5%">序号</th>
		<th><report:tableTitle title="客户名" action="clientList.action" sqlName="name" page="${page}" ctx="${ctx}"/></th>
		<th><report:tableTitle title="客户类型" action="clientList.action" sqlName="type" page="${page}" ctx="${ctx}"/></th>
		<th>操作</th>
	</tr>
		<s:iterator value="page.result" status="index">
		<s:if test="#index.odd == true">
			<tr class="table_style1">
		</s:if>
		<s:else>
			<tr class="table_style2">
		</s:else>
			<td>${index.index+1}</td>
			<td><c:if test="${sessionScope.role['2.2.1'] != null}"><a href="${ctx}/client/projectList.action?searchView.clientId=${id}"></c:if>${name}<c:if test="${sessionScope.role['2.2.1'] != null}"></a></c:if></td>
			<td>${typeDisplay}</a></td>
			<td align="center">
			<c:if test="${sessionScope.role['2.1.2'] != null}">
			<input type="button" value="编辑" onClick="document.location='clientList!initClient.action?clientId=${id}&pageNo=${page.pageNo}'" /></c:if>
			</td>
		</tr>
	</s:iterator>
	<tr>
		<td align="right" colspan="4">
			<report:pages action="clientList.action" excel="clientList!excelOutput.action" page="${page}"/>
		</td>
	</tr>
</table>
</s:form>
</div>
<script type="text/javascript">
  new Autocomplete('userClient', { serviceUrl:'jsonClient.action' });
</script>