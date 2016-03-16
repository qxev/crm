<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<script type="text/javascript" src="${ctx}/js/calendar.js"></script>
<div class="content">
<s:form id="reportListForm" action="/report/reportDailyList.action">
	<s:hidden name="searchView.accountId" />
	<div style="margin:0 auto;width:40%;">
	<table align="center">
		<tr>
			<td>查询日期:<s:textfield name="searchView.startDate" size="10" onClick="showcalendar(event, this);" readonly="true" /> 至 <s:textfield name="searchView.endDate" size="10" onClick="showcalendar(event, this);" readonly="true"/></td>
			<td><input type="image" src="${ctx}/images/search.gif" onClick="userSubmit('reportListForm')"/></td>
		</tr>
	</table>
	</div>
	<table class="report" borderColor="#f2f2f2" border="1">
		<tr>
			<td align="center" colspan="13" width="80%" class="green-bg1" colspan="5"><h1>(${accountName}) SEM业务日报</h1></td>
		</tr>
		<tr>
			<td align="right" colspan="13">
				<report:pages action="reportDailyList.action" excel="reportDailyList!excelOutput.action" page="${page}"/>
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
			<td><input type="checkbox" checked class="chbx" value="8"/></td>
			<td><input type="checkbox" checked class="chbx" value="9"/></td>
			<td><input type="checkbox" checked class="chbx" value="10"/></td>
			<td><input type="checkbox" checked class="chbx" value="11"/></td>
		</tr>
		<tr>
			<th width="4%">序号</th>
			<th width="8%"><report:tableTitle title="搜索(￥)" action="reportDailyList.action" sqlName="e.manageMedia" page="${page}" ctx="${ctx}"/></th>
			<th width="8%"><report:tableTitle title="原始搜索" action="reportDailyList.action" sqlName="e.manageMedia" page="${page}" ctx="${ctx}"/></th>
			<th width="8%"><report:tableTitle title="网盟(￥)" action="reportDailyList.action" sqlName="e.netAff" page="${page}" ctx="${ctx}"/></th>
			<th width="8%"><report:tableTitle title="原始网盟" action="reportDailyList.action" sqlName="e.netAff" page="${page}" ctx="${ctx}"/></th>
			<th width="8%">媒体费</th>
			<th width="8%"><report:tableTitle title="达闻媒体费" action="reportDailyList.action" sqlName="e.cost" page="${page}" ctx="${ctx}"/></th>
			<th width="8%"><report:tableTitle title="达闻成本" action="reportDailyList.action" sqlName="e.darwinCost" page="${page}" ctx="${ctx}"/></th>
			<th width="8%"><report:tableTitle title="达闻折扣" action="reportDailyList.action" sqlName="e.discount" page="${page}" ctx="${ctx}"/></th>
			<th width="8%"><report:tableTitle title="服务费" action="reportDailyList.action" sqlName="e.serviceFee" page="${page}" ctx="${ctx}"/></th>
			<th width="8%"><report:tableTitle title="收入" action="reportDailyList.action" sqlName="e.revenue" page="${page}" ctx="${ctx}"/></th>
			<th width="8%"><report:tableTitle title="毛利" action="reportDailyList.action" sqlName="e.grossProfit" page="${page}" ctx="${ctx}"/></th>
			<th width="8%"><report:tableTitle title="日期" action="reportDailyList.action" sqlName="e.expenseDate" page="${page}" ctx="${ctx}"/></th>
		</tr>
		<s:iterator value="page.result" status="index">
		<s:if test="#index.odd == true">
			<tr class="table_style1">
		</s:if>
		<s:else>
			<tr class="table_style2">
		</s:else>
				<td>${index.index+1}</td>
				<td align="right"><report:bigDecimalFormat value = "${manageMedia}" />&nbsp;</td>
				<td align="right"><s:if test="exchangeId==1"><report:bigDecimalFormat value = "${originalManageMedia}" /></s:if>
				<s:else><report:bigDecimalCountry value = "${originalManageMedia}" country = "${account.exchange.id}"/></s:else>&nbsp;</td>
				<td align="right"><report:bigDecimalFormat value = "${netAff}" />&nbsp;</td>
				<td align="right"><s:if test="exchangeId==1"><report:bigDecimalFormat value = "${originalNetAff}" /></s:if>
				<s:else><report:bigDecimalCountry value = "${originalNetAff}" country = "${account.exchange.id}"/></s:else>&nbsp;</td>
				<td align="right"><report:bigDecimalFormat value = "${totalManageMedia}" />&nbsp;</td>
				<td align="right"><report:bigDecimalFormat value = "${cost}" />&nbsp;</td>
				<td align="right"><report:bigDecimalFormat value = "${darwinCost}" />&nbsp;</td>
				<td align="right"><report:bigDecimalFormat value = "${discount}" />&nbsp;</td>
				<td align="right"><report:bigDecimalFormat value = "${serviceFeeTotal}" />&nbsp;</td>
				<td align="right"><report:bigDecimalFormat value = "${revenue}" />&nbsp;</td>
				<td align="right"><report:bigDecimalFormat value = "${grossProfit}" />&nbsp;</td>
				<td align="right"><report:dateFormat date = "${expenseDate}" />&nbsp;</td>
			</tr>
		</s:iterator>
			<tr>
				<td>合计</td>
				<td align="right"><report:bigDecimalFormat value = "${sumBean.manageMedia}" />&nbsp;</td>
				<td align="right"><s:if test="exchangeId==1"><report:bigDecimalFormat value = "${sumBean.originalManageMedia}" /></s:if>
				<s:else><report:bigDecimalCountry value = "${sumBean.originalManageMedia}" country = "${exchangeId}"/></s:else>&nbsp;</td>
				<td align="right"><report:bigDecimalFormat value = "${sumBean.netAff}" />&nbsp;</td>
				<td align="right"><s:if test="exchangeId==1"><report:bigDecimalFormat value = "${sumBean.originalNetAff}" /></s:if>
				<s:else><report:bigDecimalCountry value = "${sumBean.originalNetAff}" country = "${exchangeId}"/></s:else>&nbsp;</td>
				<td align="right"><report:bigDecimalFormat value = "${sumBean.totalManageMedia}" />&nbsp;</td>
				<td align="right"><report:bigDecimalFormat value = "${sumBean.cost}" />&nbsp;</td>
				<td align="right"><report:bigDecimalFormat value = "${sumBean.darwinCost}" />&nbsp;</td>
				<td align="right"><report:bigDecimalFormat value = "${sumBean.discount}" />&nbsp;</td>
				<td align="right"><report:bigDecimalFormat value = "${sumBean.serviceFeeTotal}" />&nbsp;</td>
				<td align="right"><report:bigDecimalFormat value = "${sumBean.revenue}" />&nbsp;</td>
				<td align="right"><report:bigDecimalFormat value = "${sumBean.grossProfit}" />&nbsp;</td>
			</tr>
	</table>
		<tr>
			<td align="right" colspan="11">
				<report:pages action="reportDailyList.action" excel="reportList!excelOutput.action" page="${page}"/>
			</td>
		</tr>
</s:form>
</div>
