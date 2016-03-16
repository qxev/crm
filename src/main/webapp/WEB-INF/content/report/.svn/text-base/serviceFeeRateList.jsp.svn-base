<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<script type="text/javascript" src="${ctx}/js/calendar.js"></script>
<div class="content">
	<s:form id="serviceFeeRateForm" action="/report/serviceFeeRateList.action">
	<div style="margin:0 auto;width:65%;">
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
			<td>查询日期:<s:textfield name="searchView.startDate" onClick="showcalendar(event, this);" readonly="true" />至 <s:textfield name="searchView.endDate" onClick="showcalendar(event, this);" readonly="true"/></td>
			<td>是否显示最近记录<s:checkbox name="searchView.repeat"/><input type="image" src="${ctx}/images/search.gif" onClick="userSubmit('serviceFeeRateForm')"/></td>
		</tr>
	</table>
	</div>
	<table class="report" borderColor="#f2f2f2" border="1">
		<tr>
			<td align="center" colspan="9" width="80%" class="green-bg1" colspan="5"><h1>服务费率历史</h1></td>
		</tr>
		<tr>
			<td align="right" colspan="9">
				<report:pages action="serviceFeeRateList.action" excel="serviceFeeRateList!excelOutput.action" page="${page}"/>
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
			<td><input type="checkbox" checked class="chbx" value="7"/></td>
		</tr>
		<tr>
			<th width="5%">序号</th>
			<th width="21%"><report:tableTitle title="客户名" action="serviceFeeRateList.action" sqlName="c.name" page="${page}" ctx="${ctx}"/></th>
			<th width="10%"><report:tableTitle title="项目名" action="serviceFeeRateList.action" sqlName="p.name" page="${page}" ctx="${ctx}"/></th>
			<th width="11%"><report:tableTitle title="账户名" action="serviceFeeRateList.action" sqlName="a.name" page="${page}" ctx="${ctx}"/></th>
			<th width="11%"><report:tableTitle title="调整日期" action="serviceFeeRateList.action" sqlName="se.create_at" page="${page}" ctx="${ctx}"/></th>
			<th width="11%"><report:tableTitle title="调整前服务费率" action="serviceFeeRateList.action" sqlName="se.pre_value" page="${page}" ctx="${ctx}"/></th>
			<th width="11%"><report:tableTitle title="调整后服务费率" action="serviceFeeRateList.action" sqlName="se.value" page="${page}" ctx="${ctx}"/></th>
			<th width="11%"><report:tableTitle title="区间开始日期" action="serviceFeeRateList.action" sqlName="se.start_date" page="${page}" ctx="${ctx}"/></th>
			<th width="11%"><report:tableTitle title="区间结束日期" action="serviceFeeRateList.action" sqlName="se.end_date" page="${page}" ctx="${ctx}"/></th>
		</tr>
		<s:iterator value="page.result" status="index">
		<s:if test="#index.odd == true">
			<tr class="table_style1">
		</s:if>
		<s:else>
			<tr class="table_style2">
		</s:else>
				<td>${index.index+1}</td>
				<td>${account.project.client.name}&nbsp;</td>
				<td>${account.project.name}&nbsp;</td>
				<td>${account.name}&nbsp;</td>
				<td align="right"><report:dateFormat date = "${createAt}" />&nbsp;</td>
				<td align="right"><report:bigDecimalFormat value = "${preValue}" />&nbsp;</td>
				<td align="right"><report:bigDecimalFormat value = "${value}" />&nbsp;</td>
				<td align="right"><report:dateFormat date = "${startDate}" />&nbsp;</td>
				<td align="right"><report:dateFormat date = "${endDateNotNull}" />&nbsp;</td>
			</tr>
		</s:iterator>
		<tr>
			<td align="right" colspan="9">
				<report:pages action="serviceFeeRateList.action" excel="serviceFeeRateList!excelOutput.action" page="${page}"/>
			</td>
		</tr>	
	</table>
</s:form>
</div>
