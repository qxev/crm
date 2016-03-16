<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<link href="${ctx}/css/styles.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/autocomplete.js"></script>
<div class="content">
<font color="red"><s:actionerror/></font><font color="green"><s:actionmessage/></font>
<s:form id="projectListForm" action="/client/projectList.action">
	<s:if test="clientName!=null">${clientName} -> 项目列表 </s:if>
	<div style="margin:0 auto;width:75%;">
	<table align="center">
		<tr>
			<td width="52%">客户选择: <s:select list="searchView.clients" headerKey="0" headerValue="全部" name="searchView.clientId" value="searchView.clientId" listKey="id" listValue="name"/></td>
			<td width="48%">SEM分析师选择: <s:select list="searchView.pms" headerKey="0" headerValue="全部" name="searchView.pmId" listKey="id" listValue="name"/>
			销售选择: <s:select list="searchView.bds" headerKey="0" headerValue="全部" name="searchView.bdId" listKey="id" listValue="name"/></td>
		</tr>
		<tr>
			<td>来源: <s:select list="searchView.sources" headerKey="0" headerValue="全部" name="searchView.sourceId" listKey="key" listValue="value"/>
			行业: <s:select list="searchView.industrys" headerKey="0" headerValue="全部" name="searchView.industryId" listKey="id" listValue="name"/>
			状态: <s:select list="searchView.statuses" headerKey="0" headerValue="全部" name="searchView.statusId" listKey="key" listValue="value"/></td>
			<td>
			客户名联想:<s:textfield name="searchView.clientName" id="userClient" size="48"/>
			</td>
		</tr>
		<tr>
			<td>项目名联想:<s:textfield name="searchView.projectName" id="userProject" size="20"/></td>
			<td><input type="image" src="${ctx}/images/search.gif" onClick="userSubmit('projectListForm')"/></td>
		</tr>
	</table>
	</div>
	<table class="report" borderColor="#f2f2f2" border="1">
		<tr>
			<td colspan="8" align="center" width="80%" class="green-bg1"><h1>项目列表</h1></td>
		</tr>
		<tr>
			<td align="right" colspan="8">
			<report:pages action="projectList.action" excel="projectList!excelOutput.action" page="${page}"/>
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
		</tr>
		<tr>
			<th width="5%">序号</th>
			<th width="25%"><report:tableTitle title="客户名" action="projectList.action" sqlName="client.name" page="${page}" ctx="${ctx}"/></th>
			<th width="15%"><report:tableTitle title="项目名" action="projectList.action" sqlName="name" page="${page}" ctx="${ctx}"/></th>
			<th width="10%">PM</th>
			<th width="10%">BD</th>
			<th width="10%"><report:tableTitle title="行业" action="projectList.action" sqlName="industry.name" page="${page}" ctx="${ctx}"/></th>
			<th width="10%"><report:tableTitle title="项目状态" action="projectList.action" sqlName="status" page="${page}" ctx="${ctx}"/></th>
			<th width="20%">操作</th>
		</tr>
	
		<s:iterator value="page.result" status="index">
		<s:if test="#index.odd == true">
			<tr class="table_style1">
		</s:if>
		<s:else>
			<tr class="table_style2">
		</s:else>
				<td>${index.index+1}</td>
				<td>${client.name}&nbsp;</td>
				<td><c:if test="${sessionScope.role['2.3.1'] != null}"><a href="${ctx}/client/mediaList.action?searchView.projectId=${id}"></c:if>${name}<c:if test="${sessionScope.role['2.3.1'] != null}"></a></c:if></td>
				<td>${pmDisplay}&nbsp;</td>
				<td>${bdDisplay}&nbsp;</td>
				<td>${industry.name}&nbsp;</td>
				<td>${statusDisplay}&nbsp;</td>
				<td align="center"><c:if test="${sessionScope.role['2.2.2'] != null}"><input type="button" value="编辑" onClick="document.location='initProject.action?projectId=${id}&filter=${page.filter}&pageNo=${page.pageNo}'" /></c:if>
				<c:if test="${(sessionScope.role['type'] == '2')||(sessionScope.role['type'] == '3')}"><a href="${ctx}/client/contract!saveContract.action?projectId=${id}">创建合同</a>
				</c:if></td>
			</tr>
		</s:iterator>
		<tr>
			<td align="right" colspan="8">
			<report:pages action="projectList.action" excel="projectList!excelOutput.action" page="${page}"/>
			</td>
		</tr>
	</table>
</s:form>
</div>
<script type="text/javascript">
  new Autocomplete('userClient', { serviceUrl:'jsonClient.action' });
  new Autocomplete('userProject', { serviceUrl:'jsonAllProject.action' });
</script>
