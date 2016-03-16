<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<div class="content">
<table class="report" borderColor="#f2f2f2" border="1">
	<tr>
		<td align="center" width="80%" colspan="12" class="green-bg1"><h1>账户充值预警</h1></td><td><input type="button" onClick="document.location='alertReset.action'" value="重新分析"/></td>
	</tr>
	<tr>
		<td align="right" colspan="13">
			<report:pages action="alertList.action" excel="alertList!excelOutput.action" page="${page}"/>
		</td>
	</tr>
	<tr align="center">
		<td></td>
		<td><input type="checkbox" class="chbx" checked value="0"/></td>
		<td><input type="checkbox" class="chbx" checked value="1"/></td>
		<td><input type="checkbox" class="chbx" checked value="2"/></td>
		<td><input type="checkbox" class="chbx" checked value="3"/></td>
		<td><input type="checkbox" class="chbx" checked value="4"/></td>
		<td><input type="checkbox" class="chbx" checked value="5"/></td>
		<td><input type="checkbox" class="chbx" checked value="6"/></td>
		<td><input type="checkbox" class="chbx" checked value="7"/></td>
		<td><input type="checkbox" class="chbx" checked value="8"/></td>
		<td><input type="checkbox" class="chbx" checked value="9"/></td>
		<td><input type="checkbox" class="chbx" checked value="10"/></td>
		<td><input type="checkbox" class="chbx" checked value="11"/></td>
	</tr>
	<tr>
		<th width="5%">序号</th>
		<th width="10%"><report:tableTitle title="账户名" action="alertList.action" sqlName="name" page="${page}" ctx="${ctx}"/></th>
		<th width="10%"><report:tableTitle title="项目名" action="alertList.action" sqlName="project.name" page="${page}" ctx="${ctx}"/></th>
		<th width="8%"><report:tableTitle title="媒体名" action="alertList.action" sqlName="channel.media.name" page="${page}" ctx="${ctx}"/></th>
		<th width="8%"><report:tableTitle title="渠道名" action="alertList.action" sqlName="channel.name" page="${page}" ctx="${ctx}"/></th>
		<th width="8%">PM</th>
		<th width="8%"><report:tableTitle title="账户余额" action="alertList.action" sqlName="totalBalance" page="${page}" ctx="${ctx}"/></th>
		<th width="8%"><report:tableTitle title="每日预算" action="alertList.action" sqlName="dailyBudget" page="${page}" ctx="${ctx}"/></th>
		<th width="6%"><report:tableTitle title="剩余天数" action="alertList.action" sqlName="remainDays" page="${page}" ctx="${ctx}"/></th>
		<th width="8%"><report:tableTitle title="充值日期" action="alertList.action" sqlName="remainDays" page="${page}" ctx="${ctx}"/></th>
		<th width="8%">充值金额</th>
		<th width="8%"><report:tableTitle title="上次充值日期" action="alertList.action" sqlName="lastSuppleDate" page="${page}" ctx="${ctx}"/></th>
		<th width="8%"><report:tableTitle title="上次充值金额" action="alertList.action" sqlName="lastSupplement" page="${page}" ctx="${ctx}"/></th>
	</tr>

	<s:iterator value="page.result" status="index">
	<s:if test="#index.odd == true">
		<tr class="table_style1">
	</s:if>
	<s:else>
		<tr class="table_style2">
	</s:else>
			<td>${index.index+1}</td>
			<td>${name}&nbsp;</td>
			<td>${project.name}&nbsp;</td>
			<td>${channel.media.name}&nbsp;</td>
			<td><p title="${channel.name}">${channel.cutName}</p></td>
			<td>${currentPms}</td>
			<td align="right"><report:bigDecimalFormat value = "${totalBalance}" />&nbsp;</td>
			<td align="right"><report:bigDecimalFormat value = "${dailyBudget}" />&nbsp;</td>
			<td align="right">${remainDays}&nbsp;</td>
			<td align="right"><report:dateFormat date = "${suggestAddMoneyDate}" />&nbsp;</td>
			<td align="right"><report:bigDecimalFormat value = "${maxSuggestAdd}" />&nbsp;</td>
			<td align="right"><report:dateFormat date = "${lastSuppleDate}" />&nbsp;</td>
			<td align="right"><report:bigDecimalFormat value = "${lastSupplement}" />&nbsp;</td>
		</tr>
	</s:iterator>
	<tr>
		<td align="right" colspan="13">
			<report:pages action="alertList.action" excel="alertList!excelOutput.action" page="${page}"/>
		</td>
	</tr>
</table>
</div>