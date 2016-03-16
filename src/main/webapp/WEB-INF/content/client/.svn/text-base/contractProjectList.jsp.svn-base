<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<link href="${ctx}/css/styles.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/autocomplete.js"></script>
<s:if test="hasActionMessages()">
<s:iterator value="actionMessages">
<script type="text/javascript">
alert("<s:property escape="false"/>");
</script>
</s:iterator>
</s:if> 
<div class="content">
<s:form id="projectListForm" action="/client/contractProject!contractProjectList.action">
	<div style="margin:0 auto;width:75%;">
	<table align="center">
		<tr>
			<td >客户选择</td><td ><s:select list="searchView.contractClients" headerKey="0" headerValue="全部" name="searchView.clientId" value="searchView.clientId" listKey="id" listValue="name" style="width:284px"/></td>
			<td width="30px">PM</td><td ><s:select list="searchView.pms" headerKey="0" headerValue="全部" name="searchView.pmId" listKey="id" listValue="name" style="width:140px"/></td>
			<td >BD管理员</td><td ><s:select list="searchView.bds" headerKey="0" headerValue="全部" name="searchView.bdId" listKey="id" listValue="name" style="width:140px"/></td>
		</tr>
		<tr>
			<td>客户名联想</td><td><s:textfield name="searchView.clientName" id="userClient" size="41"/></td>
			<td>客户来源</td><td><s:select list="searchView.sources" headerKey="0" headerValue="全部" name="searchView.sourceId" listKey="key" listValue="value" style="width:140px"/></td>
			<td>行业</td><td><s:select list="searchView.industrys" headerKey="0" headerValue="全部" name="searchView.industryId" listKey="id" listValue="name" style="width:140px"/></td>
		</tr>
		<tr>
			<td>项目名联想</td><td><s:textfield name="searchView.projectName" id="userProject" size="41"/></td>
			<td><input type="image" src="${ctx}/images/search.gif" onClick="userSubmit('projectListForm')"/></td>
		</tr>
	</table>
	</div>
	<table class="report" borderColor="#f2f2f2" border="1">
		
		<tr>
			<td colspan="9" align="center" width="80%" class="green-bg1"><h1>项目列表</h1></td>
		</tr>
		<tr>
			<td align="right" colspan="8">
			<report:pages action="${ctx}/client/contractProject!contractProjectList.action" excel="${ctx}/client/contractProject!excelOutput.action" page="${page}"/>
			</td>
		</tr>
		<tr align="center">
			<td></td>
			<td><input type="checkbox" checked class="chbx" value="0"/></td>
			<td><input type="checkbox" checked class="chbx" value="1"/></td>
			<td><input type="checkbox" checked class="chbx" value="2"/></td>
			<td><input type="checkbox" checked class="chbx" value="3"/></td>
			<td><input type="checkbox" checked class="chbx" value="4"/></td>
			<td><input type="checkbox" checked class="chbx" value="5"/></td>
			<td><input type="checkbox" checked class="chbx" value="6"/></td>
		</tr>
		<tr>
			<th width="5%">序号</th>
			<th width="20%"><report:tableTitle title="客户名" action="contractProject!contractProjectList.action" sqlName="contractClient.name" page="${page}" ctx="${ctx}"/></th>
			<th width="15%"><report:tableTitle title="项目名" action="contractProject!contractProjectList.action" sqlName="name" page="${page}" ctx="${ctx}"/></th>
			<th width="10%">PM</th>
			<th width="10%">BD</th>
			<th width="10%"><report:tableTitle title="行业" action="contractProject!contractProjectList.action" sqlName="industry.name" page="${page}" ctx="${ctx}"/></th>
			<th width="10%"><report:tableTitle title="客户来源" action="contractProject!contractProjectList.action" sqlName="contractClient.type" page="${page}" ctx="${ctx}"/></th>
			<th width="15%">操作</th>
		</tr>
	
		<s:iterator value="page.result" status="index">
		<s:if test="#index.odd == true">
			<tr class="table_style1">
		</s:if>
		<s:else>
			<tr class="table_style2">
		</s:else>
				<td>${index.index+1}</td>
				<td>${contractClient.name}</td>
				<td>${name}</td>
				<td>${pm.name}</td>
				<td>${bd.name}</td>
				<td>${industry.name}</td>
				<td>${contractClient.typeDisplay}</td>
				<td align="center"><c:if test="${sessionScope.role['6.2.2'] != null}"><a href="${ctx}/client/contractProject!initContractProject.action?contractProjectId=${id}&filter=${page.filter}&pageNo=${page.pageNo}" />编辑</a></c:if>
				<c:if test="${(sessionScope.role['type'] == '2')||(sessionScope.role['type'] == '3')}"><a href="${ctx}/client/contract!saveContract.action?searchView.projectId=${id}" />创建合同</a>
				</c:if></td>
			</tr>
		</s:iterator>
		<tr>
			<td align="right" colspan="9">
			<report:pages action="${ctx}/client/contractProject!contractProjectList.action" excel="${ctx}/client/contractProject!excelOutput.action" page="${page}"/>
			</td>
		</tr>
	</table>
</s:form>
</div>
<script type="text/javascript">
  new Autocomplete('userClient', { serviceUrl:'jsonContractClient.action' });
  new Autocomplete('userProject', { serviceUrl:'jsonContractProject.action' });
</script>
