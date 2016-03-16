<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<script type="text/javascript" src="${ctx}/js/calendar.js"></script>
	<s:form id="serviceFeeBaseForm" action="/report/serviceFeeBaseList.action">
	<table>
		<tr width="100%">
			<td width="50%">客户选择: <s:select list="searchView.clients" headerKey="0" headerValue="全部" name="searchView.clientId" value="searchView.clientId" listKey="id" listValue="name" id="clientId" onChange="changeProject()"/></td>
			<td width="50%">项目选择: <s:select list="searchView.projects" headerKey="0" headerValue="全部" name="searchView.projectId" value="searchView.projectId" listKey="id" listValue="name" id="projectId"/></td>
		</tr>
		<tr>
			<td>媒体选择: <s:select list="searchView.medias" headerKey="0" headerValue="全部" name="searchView.mediaId" id="mediaId" listKey="id" listValue="name" onChange="changeChannel()"/></td>
			<td>渠道选择: <s:select list="searchView.channels" headerKey="0" headerValue="全部" name="searchView.channelId" value="searchView.channelId" listKey="id" listValue="name" id="channelId"/></td>
		</tr>
		<tr>
			<td>查询日期:<s:textfield name="searchView.startDate" onClick="showcalendar(event, this);" readonly="true" />至 <s:textfield name="searchView.endDate" onClick="showcalendar(event, this);" readonly="true"/></td>
			<td><input type="image" src="${ctx}/images/search.gif" onClick="userSubmit('serviceFeeBaseForm')"/></td>
		</tr>
	</table>
	<table class="report" borderColor="#f2f2f2" border="1">
		<tr>
			<td align="center" colspan="4" width="80%" class="green-bg1" colspan="5"><h1>基数调整历史</h1></td><td align="right" class="green-bg1" colspan="8"><input onClick="excelOutput('serviceFeeBaseList!excelOutput.action')" type="button" value="导出到Excel" /></td>
		</tr>
		<tr align="center">
			<td><input type="checkbox" checked class="chbx" value="0"/><s:if test="!(page.order=='asc'&&page.orderBy=='account.name')"><img style="cursor:pointer" src="${ctx}/images/up.gif" onclick="document.location='serviceFeeBaseList.action?page.pageNo=${page.pageNo}&page.filter=${page.filter}&pageSize=${pageSize}&page.order=asc&page.orderBy=account.name'"/></s:if>
			<s:if test="!(page.order=='desc'&&page.orderBy=='account.name')"><img src="${ctx}/images/down.gif" style="cursor:pointer" onclick="document.location='serviceFeeBaseList.action?page.pageNo=${page.pageNo}&page.filter=${page.filter}&pageSize=${pageSize}&page.order=desc&page.orderBy=account.name'"/></s:if></td>
			<td><input type="checkbox" checked class="chbx" value="1"/><s:if test="!(page.order=='asc'&&page.orderBy=='createAt')"><img style="cursor:pointer" src="${ctx}/images/up.gif" onclick="document.location='serviceFeeBaseList.action?page.pageNo=${page.pageNo}&page.filter=${page.filter}&pageSize=${pageSize}&page.order=asc&page.orderBy=createAt'"/></s:if>
			<s:if test="!(page.order=='desc'&&page.orderBy=='createAt')"><img src="${ctx}/images/down.gif" style="cursor:pointer" onclick="document.location='serviceFeeBaseList.action?page.pageNo=${page.pageNo}&page.filter=${page.filter}&pageSize=${pageSize}&page.order=desc&page.orderBy=createAt'"/></s:if></td>
			<td><input type="checkbox" checked class="chbx" value="2"/><s:if test="!(page.order=='asc'&&page.orderBy=='value')"><img style="cursor:pointer" src="${ctx}/images/up.gif" onclick="document.location='serviceFeeBaseList.action?page.pageNo=${page.pageNo}&page.filter=${page.filter}&pageSize=${pageSize}&page.order=asc&page.orderBy=value'"/></s:if>
			<s:if test="!(page.order=='desc'&&page.orderBy=='value')"><img src="${ctx}/images/down.gif" style="cursor:pointer" onclick="document.location='serviceFeeBaseList.action?page.pageNo=${page.pageNo}&page.filter=${page.filter}&pageSize=${pageSize}&page.order=desc&page.orderBy=value'"/></s:if></td>
			<td><input type="checkbox" checked class="chbx" value="3"/><s:if test="!(page.order=='asc'&&page.orderBy=='startDate')"><img style="cursor:pointer" src="${ctx}/images/up.gif" onclick="document.location='serviceFeeBaseList.action?page.pageNo=${page.pageNo}&page.filter=${page.filter}&pageSize=${pageSize}&page.order=asc&page.orderBy=startDate'"/></s:if>
			<s:if test="!(page.order=='desc'&&page.orderBy=='startDate')"><img src="${ctx}/images/down.gif" style="cursor:pointer" onclick="document.location='serviceFeeBaseList.action?page.pageNo=${page.pageNo}&page.filter=${page.filter}&pageSize=${pageSize}&page.order=desc&page.orderBy=startDate'"/></s:if></td>
			<td><input type="checkbox" checked class="chbx" value="4"/><s:if test="!(page.order=='asc'&&page.orderBy=='endDate')"><img style="cursor:pointer" src="${ctx}/images/up.gif" onclick="document.location='serviceFeeBaseList.action?page.pageNo=${page.pageNo}&page.filter=${page.filter}&pageSize=${pageSize}&page.order=asc&page.orderBy=endDate'"/></s:if>
			<s:if test="!(page.order=='desc'&&page.orderBy=='endDate')"><img src="${ctx}/images/down.gif" style="cursor:pointer" onclick="document.location='serviceFeeBaseList.action?page.pageNo=${page.pageNo}&page.filter=${page.filter}&pageSize=${pageSize}&page.order=desc&page.orderBy=endDate'"/></s:if></td>
		</tr>
		<tr>
			<th width="20%">账户名</th>
			<th width="20%">基数调整日期</th>
			<th width="20%">基数</th>
			<th width="20%">区间开始日期</th>
			<th width="20%">区间结束日期</th>
		</tr>
		<s:iterator value="page.result" status="index">
		<s:if test="#index.odd == true">
			<tr class="table_style1">
		</s:if>
		<s:else>
			<tr class="table_style2">
		</s:else>
				<td>${account.name}&nbsp;</td>
				<td align="right"><report:dateFormat date = "${createAt}" />&nbsp;</td>
				<td align="right"><report:bigDecimalFormat value = "${value}" />&nbsp;</td>
				<td align="right"><report:dateFormat date = "${startDate}" />&nbsp;</td>
				<td align="right"><report:dateFormat date = "${endDateNotNull}" />&nbsp;</td>
			</tr>
		</s:iterator>
	</table>
	<report:pages action="serviceFeeBaseList.action" page="${page}"/>
</s:form>
