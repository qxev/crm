<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<script type="text/javascript" src="${ctx}/js/calendar.js"></script>
<div class="content50">
<s:form id="mediaListForm" action="/client/mediaList.action">
<s:if test="projectName!=null">${clientName} -> ${projectName} -> 媒体列表 </s:if>
	<table align="center">
		<tr>
			<td width="65%">客户选择: <s:select list="searchView.clients" headerKey="0" headerValue="全部" name="searchView.clientId" value="searchView.clientId" listKey="id" listValue="name" onChange="changeProject()" id="clientId"/></td>
			<td width="35%">状态: <s:select list="searchView.statuses" headerKey="0" headerValue="全部" name="searchView.statusId" listKey="key" listValue="value"/></td>
		</tr>
		<tr>
			<td>项目选择: <s:select list="searchView.projects" headerKey="0" headerValue="全部" name="searchView.projectId" value="searchView.projectId" listKey="id" listValue="name" id="projectId"/></td>
			<td><input type="image" src="${ctx}/images/search.gif" onClick="userSubmit('mediaListForm')"/></td>
		</tr>
	</table>
	<table class="report" borderColor="#f2f2f2" border="1">
		<tr>
			<td align="center" colspan="2" width="80%" class="green-bg1"><h1>媒体列表</h1></td>
		</tr>
		<tr>
			<td align="right" colspan="2">
				<report:pages action="mediaList.action" excel="mediaList!excelOutput.action" page="${page}"/>
			</td>
		</tr>
		<tr align="center">
			<td></td>
			<td><input type="checkbox" checked class="chbx" value="0"/></td>
		</tr>
		<tr>
			<th width="5%">序号</th>
			<th width="45%"><report:tableTitle title="媒体名" action="mediaList.action" sqlName="m.name" page="${page}" ctx="${ctx}"/></th>
		</tr>
	
		<s:iterator value="page.result" status="index">
		<s:if test="#index.odd == true">
			<tr class="table_style1">
		</s:if>
		<s:else>
			<tr class="table_style2">
		</s:else>
				<td>${index.index+1}</td>
				<td><c:if test="${sessionScope.role['2.4.1'] != null}"><a href="${ctx}/client/channelList.action?searchView.mediaId=${id}&searchView.projectId=${searchView.projectId}"></c:if>${name}<c:if test="${sessionScope.role['2.4.1'] != null}"></a></c:if>&nbsp;</td>
			</tr>
		</s:iterator>
		<tr>
			<td align="right" colspan="2">
				<report:pages action="mediaList.action" excel="mediaList!excelOutput.action" page="${page}"/>
			</td>
		</tr>
	</table>
</s:form>
</div>
