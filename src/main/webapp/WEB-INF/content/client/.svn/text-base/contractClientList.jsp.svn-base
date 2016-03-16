<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<link href="${ctx}/css/styles.css" rel="stylesheet" type="text/css" />
<s:if test="hasActionMessages()">
<s:iterator value="actionMessages">
<script type="text/javascript">
alert("<s:property escape="false"/>");
</script>
</s:iterator>
</s:if> 
<script type="text/javascript" src="${ctx}/js/autocomplete.js"></script>
<div class="content50">
<s:form id="clientForm" action="/client/contractClientList.action">

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
		<td align="center" width="80%" colspan="4" class="green-bg1"><h1>客户列表${message}${pageNo}</h1></td>
	</tr>
	<tr>
		<td align="right" colspan="4">
			<report:pages action="contractClientList.action" excel="contractClientList!excelOutput.action" page="${page}"/>
		</td>
	</tr>
	<tr align="center">
		<td></td>
		<td><input type="checkbox" checked class="chbx" value="0"/></td>
		<td><input type="checkbox" checked class="chbx" value="1"/></td>
	</tr>
	<tr>
		<th width="5%">序号</th>
		<th><report:tableTitle title="客户名" action="contractClientList.action" sqlName="name" page="${page}" ctx="${ctx}"/></th>
		<th><report:tableTitle title="客户类型" action="contractClientList.action" sqlName="type" page="${page}" ctx="${ctx}"/></th>
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
			<td>${name}</td>
			<td>${typeDisplay}</a></td>
			<td align="center">
			<c:if test="${sessionScope.role['6.1.2'] != null}">
			<a href="${ctx}/client/contractClientList!initContractClient.action?contractClientId=${id}&pageNo=${page.pageNo}">编辑</a></c:if>
			<c:if test="${sessionScope.role['6.2.2'] != null}">
			<a href="${ctx}/client/contractProject!initContractProject.action?contractClientId=${id}"/>新建项目</a></c:if>
			</td>
		</tr>
	</s:iterator>
	<tr>
		<td align="right" colspan="4">
			<report:pages action="contractClientList.action" excel="contractClientList!excelOutput.action" page="${page}"/>
		</td>
	</tr>
</table>
</s:form>
</div>
<script type="text/javascript">
  new Autocomplete('userClient', { serviceUrl:'jsonContractClient.action' });
</script>