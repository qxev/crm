<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<div class="content50">
<s:form id="channelListForm" action="/client/channelList.action">
	<s:if test="projectName!=null&&mediaName!=null">${clientName} -> ${projectName} -> ${mediaName} -> 渠道列表 </s:if>
	<table align="center">
		<tr>
			<td width="65%">客户选择: <s:select list="searchView.clients" headerKey="0" headerValue="全部" name="searchView.clientId" value="searchView.clientId" listKey="id" listValue="name" id="clientId" onChange="changeProject()"/></td>
			<td width="35%">媒体选择: <s:select list="searchView.medias" headerKey="0" headerValue="全部" name="searchView.mediaId" id="medias" listKey="id" listValue="name" />
			状态: <s:select list="searchView.statuses" headerKey="0" headerValue="全部" name="searchView.statusId" listKey="key" listValue="value"/></td>
		</tr>
		<tr>
			<td>项目选择: <s:select list="searchView.projects" headerKey="0" headerValue="全部" name="searchView.projectId" value="searchView.projectId" listKey="id" listValue="name" id="projectId"/></td>
			<td><input type="image" src="${ctx}/images/search.gif" onClick="userSubmit('channelListForm')"/></td>
		</tr>
	</table>
	<table class="report" borderColor="#f2f2f2" border="1">
		<tr>
			<td align="center" colspan="3" width="80%" class="green-bg1"><h1>渠道列表</h1></td>
		</tr>
		<tr>
			<td align="right" colspan="3">
				<report:pages action="channelList.action" excel="channelList!excelOutput.action" page="${page}"/>
			</td>
		</tr>
		<tr align="center">
			<td></td>
			<td><input type="checkbox" checked class="chbx" value="0"/></td>
			<td><input type="checkbox" checked class="chbx" value="1"/></td>
		</tr>
		<tr>
			<th width="5%">序号</th>
			<th><report:tableTitle title="媒体名" action="channelList.action" sqlName="m.name" page="${page}" ctx="${ctx}"/></th>
			<th><report:tableTitle title="渠道名" action="channelList.action" sqlName="ch.name" page="${page}" ctx="${ctx}"/></th>
		</tr>
		<s:iterator value="page.result" status="index">
		<s:if test="#index.odd == true">
			<tr class="table_style1">
		</s:if>
		<s:else>
			<tr class="table_style2">
		</s:else>
				<td>${index.index+1}</td>
				<td>${media.name}&nbsp;</td>
				<td><c:if test="${sessionScope.role['2.5.1'] != null}"><a href="${ctx}/client/accountList.action?searchView.channelId=${id}&searchView.projectId=${searchView.projectId}"></c:if>${name}<c:if test="${sessionScope.role['2.5.1'] != null}"></a></c:if></td>
			</tr>
		</s:iterator>
	</table>
	<report:pages action="channelList.action" excel="channelList!excelOutput.action" page="${page}"/>
</s:form>
</div>