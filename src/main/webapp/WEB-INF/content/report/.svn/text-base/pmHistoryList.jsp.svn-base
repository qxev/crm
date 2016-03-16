<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<script type="text/javascript" src="${ctx}/js/calendar.js"></script>
<div class="content50">
<s:form id="pmHistoryForm" action="/report/pmHistoryList.action">
	<div style="margin:0 auto;width:100%;">
	<table align="center">
		<tr>
			<td width="65%">客户选择: <s:select list="searchView.clients" headerKey="0" headerValue="全部" name="searchView.clientId" value="searchView.clientId" listKey="id" listValue="name" id="clientId" onChange="changeProject()"/></td>
			<td width="35%">媒体选择: <s:select list="searchView.medias" headerKey="0" headerValue="全部" name="searchView.mediaId" id="mediaId" listKey="id" listValue="name" onChange="changeChannel()"/></td>
		</tr>
		<tr>
			<td>项目选择: <s:select list="searchView.projects" headerKey="0" headerValue="全部" name="searchView.projectId" value="searchView.projectId" listKey="id" listValue="name" id="projectId"/></td>
			<td>渠道选择: <s:select list="searchView.channels" headerKey="0" headerValue="全部" name="searchView.channelId" value="searchView.channelId" listKey="id" listValue="name" id="channelId"/></td>
		</tr>
		<tr>
			<td>查询日期 :<s:textfield name="searchView.startDate" size="10" onClick="showcalendar(event, this);" readonly="true"/> 至 <s:textfield name="searchView.endDate" size="10" onClick="showcalendar(event, this);" readonly="true"/></td>
			<td><input type="image" src="${ctx}/images/search.gif" onClick="userSubmit('pmHistoryForm')"/></td>
		</tr>
	</table>
	</div>
	<table class="report" borderColor="#f2f2f2" border="1">
		<tr>
			<td align="center" colspan="5" width="80%" class="green-bg1" colspan="5"><h1>SEM分析师管理帐户历史</h1></td>
		</tr>
		<tr>
			<td align="right" colspan="5">
				<report:pages action="pmHistoryList.action" excel="pmHistoryList!excelOutput.action" page="${page}"/>
			</td>
		</tr>
		<tr align="center">
			<td></td>
			<td><input type="checkbox" checked class="chbx" value="0"/></td>
			<td><input type="checkbox" checked class="chbx" value="1"/></td>
			<td><input type="checkbox" checked class="chbx" value="2"/></td>
			<td><input type="checkbox" checked class="chbx" value="3"/></td>
		</tr>
		<tr>
			<th width="5%">序号</th>
			<th>PM</th>
			<th><report:tableTitle title="账户名" action="pmHistoryList.action" sqlName="account.name" page="${page}" ctx="${ctx}"/></th>
			<th><report:tableTitle title="开始日期" action="pmHistoryList.action" sqlName="startDate" page="${page}" ctx="${ctx}"/></th>
			<th><report:tableTitle title="结束日期" action="pmHistoryList.action" sqlName="endDate" page="${page}" ctx="${ctx}"/></th>			
		</tr>
		<s:iterator value="page.result" status="index">
		<s:if test="#index.odd == true">
			<tr class="table_style1">
		</s:if>
		<s:else>
			<tr class="table_style2">
		</s:else>
				<td>${index.index+1}</td>
				<td>${pmResult}&nbsp;</td>
				<td>${account.name}&nbsp;</td>
				<td><report:dateFormat date = "${startDate}" />&nbsp;</td>
				<td><report:dateFormat date = "${endDateNotNull}" />&nbsp;</td>
			</tr>
		</s:iterator>
		<tr>
			<td align="right" colspan="5">
				<report:pages action="pmHistoryList.action" excel="pmHistoryList!excelOutput.action" page="${page}"/>
			</td>
		</tr>
	</table>
</s:form>
</div>
