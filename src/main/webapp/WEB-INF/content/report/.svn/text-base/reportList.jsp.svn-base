<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<link href="${ctx}/css/styles.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/autocomplete.js"></script>
<script type="text/javascript" src="${ctx}/js/calendar.js"></script>
<div class="content">
<s:form id="reportListForm" action="/report/reportList.action">
	<s:hidden name="searchView.projectId" />
	<s:hidden name="searchView.mediaId" />
	<s:if test="mediaName==null&&projectName!=null"><a href="reportList.action?searchView.projectId=${searchView.projectId}&searchView.startDate=${searchView.startDate}&searchView.endDate=${searchView.endDate}">${projectName}</a> -> 业务报表 </s:if>
	<s:if test="mediaName!=null&&projectName!=null"><a href="reportList.action?searchView.projectId=${searchView.projectId}&searchView.startDate=${searchView.startDate}&searchView.endDate=${searchView.endDate}">${projectName}</a> -> <a href="reportList.action?searchView.projectId=${searchView.projectId}&searchView.mediaId=${searchView.mediaId}&searchView.startDate=${searchView.startDate}&searchView.endDate=${searchView.endDate}">${mediaName}</a> -> 业务报表 </s:if>
	<s:if test="mediaName==null&&projectName==null">
	<div style="margin:0 auto;width:70%;">
		<table align="center">
			<tr>
				<td width="60%">客户选择: <s:select list="searchView.clients" headerKey="0" headerValue="全部" name="searchView.clientId" value="searchView.clientId" listKey="id" listValue="name" id="clientId" onChange="changeProject()"/></td>
				<td width="30%">媒体选择: <s:select list="searchView.medias" headerKey="0" headerValue="全部" name="searchView.mId" id="mediaId" listKey="id" listValue="name" onChange="changeChannel()"/></td>
				<td width="10%" rowspan="4"><input type="image" src="${ctx}/images/search.gif" onClick="userSubmit('reportListForm')"/></td>
			</tr>
			<tr>
				<td>项目选择: <s:select list="searchView.projects" headerKey="0" headerValue="全部" name="searchView.pId" value="searchView.pId" listKey="id" listValue="name" id="projectId"/></td>
				<td>渠道选择: <s:select list="searchView.channels" headerKey="0" headerValue="全部" name="searchView.channelId" value="searchView.channelId" listKey="id" listValue="name" id="channelId"/></td>
			</tr>
			<tr>
				<td>查询日期:<s:textfield name="searchView.startDate" size="10" onClick="showcalendar(event, this);" readonly="true" /> 至 <s:textfield name="searchView.endDate" size="10" onClick="showcalendar(event, this);" readonly="true"/></td>
				<td>PM选择: <s:select list="searchView.pms" headerKey="0" headerValue="全部" name="searchView.pmId" listKey="id" listValue="name"/></td>
			</tr>
			<tr>
				<td>客户名联想:<s:textfield name="searchView.clientName" id="userClient" size="48"/></td>
				<td>项目名联想:<s:textfield name="searchView.projectName" id="userProject" size="20"/></td>
			</tr>
		</table>
	</div>
	</s:if>
	<s:if test="mediaName!=null||projectName!=null">
	<div style="margin:0 auto;width:40%;">
		<table align="center">
			<tr>
				<td>查询日期:<s:textfield name="searchView.startDate" size="10" onClick="showcalendar(event, this);" readonly="readOnly" /> 至 <s:textfield name="searchView.endDate" size="10" onClick="showcalendar(event, this);" readonly="readOnly"/></td>
				<td><input type="image" src="${ctx}/images/search.gif" onClick="userSubmit('reportListForm')"/></td>
			</tr>
		</table>
	</div>
	</s:if>
	<table class="report" borderColor="#f2f2f2" border="1">
		<tr>
			<td align="center" colspan="11" width="80%" class="green-bg1" colspan="5"><h1><s:if test="searchView.projectId==0&&searchView.mediaId==0">项目</s:if><s:if test="searchView.projectId!=0&&searchView.mediaId==0">媒体</s:if><s:if test="searchView.projectId!=0&&searchView.mediaId!=0">账户</s:if>级别SEM业务报表</h1></td>
		</tr>
		<tr>
			<td align="right" colspan="11">
				<report:pages action="reportList.action" excel="reportList!excelOutput.action" page="${page}"/>
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
		</tr>
		<tr>
			<th width="5%">序号</th>
			<th width="20%">
			<s:if test="searchView.projectId==0&&searchView.mediaId==0"><report:tableTitle title="项目名" action="reportList.action" sqlName="col_1_0_" page="${page}" ctx="${ctx}"/></s:if>
			<s:if test="searchView.projectId!=0&&searchView.mediaId==0"><report:tableTitle title="媒体名" action="reportList.action" sqlName="col_1_0_" page="${page}" ctx="${ctx}"/></s:if>
			<s:if test="searchView.projectId!=0&&searchView.mediaId!=0"><report:tableTitle title="账户名" action="reportList.action" sqlName="col_1_0_" page="${page}" ctx="${ctx}"/></s:if>
			</th>
			<th width="12%"><report:tableTitle title="搜索" action="reportList.action" sqlName="col_2_0_" page="${page}" ctx="${ctx}"/></th>
			<th width="12%"><report:tableTitle title="网盟" action="reportList.action" sqlName="col_3_0_" page="${page}" ctx="${ctx}"/></th>
			<th width="12%">媒体费</th>
			<th width="12%"><report:tableTitle title="达闻媒体费" action="reportList.action" sqlName="col_4_0_" page="${page}" ctx="${ctx}"/></th>
			<th width="12%"><report:tableTitle title="达闻成本" action="reportList.action" sqlName="col_5_0_" page="${page}" ctx="${ctx}"/></th>
			<th width="12%"><report:tableTitle title="达闻折扣" action="reportList.action" sqlName="col_6_0_" page="${page}" ctx="${ctx}"/></th>
			<th width="12%"><report:tableTitle title="服务费" action="reportList.action" sqlName="col_7_0_" page="${page}" ctx="${ctx}"/></th>
			<th width="12%"><report:tableTitle title="收入" action="reportList.action" sqlName="col_9_0_" page="${page}" ctx="${ctx}"/></th>
			<th width="12%"><report:tableTitle title="毛利" action="reportList.action" sqlName="col_10_0_" page="${page}" ctx="${ctx}"/></th>
		</tr>
		<s:iterator value="page.result" status="index">
		<s:if test="#index.odd == true">
			<tr class="table_style1">
		</s:if>
		<s:else>
			<tr class="table_style2">
		</s:else>
				<td>${index.index+1}</td>
				<td>
				<s:if test="searchView.projectId==0&&searchView.mediaId==0"><a href="reportList.action?searchView.projectId=${id}&searchView.startDate=${searchView.startDate}&searchView.endDate=${searchView.endDate}">${name}</a></s:if>
				<s:if test="searchView.projectId!=0&&searchView.mediaId==0"><a href="reportList.action?searchView.projectId=${searchView.projectId}&searchView.mediaId=${id}&searchView.startDate=${searchView.startDate}&searchView.endDate=${searchView.endDate}">${name}</a></s:if>
				<s:if test="searchView.projectId!=0&&searchView.mediaId!=0"><a href="reportDailyList.action?searchView.accountId=${id}&searchView.startDate=${searchView.startDate}&searchView.endDate=${searchView.endDate}">${name}</a></s:if>
				</td>
				<td align="right"><report:bigDecimalFormat value = "${manageMedia}" />&nbsp;</td>
				<td align="right"><report:bigDecimalFormat value = "${netAff}" />&nbsp;</td>
				<td align="right"><report:bigDecimalFormat value = "${totalManageMedia}" />&nbsp;</td>
				<td align="right"><report:bigDecimalFormat value = "${cost}" />&nbsp;</td>
				<td align="right"><report:bigDecimalFormat value = "${darwinCost}" />&nbsp;</td>
				<td align="right"><report:bigDecimalFormat value = "${discount}" />&nbsp;</td>
				<td align="right"><report:bigDecimalFormat value = "${serviceFeeTotal}" />&nbsp;</td>
				<td align="right"><report:bigDecimalFormat value = "${revenue}" />&nbsp;</td>
				<td align="right"><report:bigDecimalFormat value = "${grossProfit}" />&nbsp;</td>
			</tr>
		</s:iterator>
			<tr>
				<td></td>
				<td>合计</td>
				<td align="right"><report:bigDecimalFormat value = "${sumBean.manageMedia}" />&nbsp;</td>
				<td align="right"><report:bigDecimalFormat value = "${sumBean.netAff}" />&nbsp;</td>
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
			<td align="right" colspan="9">
				<report:pages action="reportList.action" excel="reportList!excelOutput.action" page="${page}"/>
			</td>
		</tr>
</s:form>
</div>
<script type="text/javascript">
  new Autocomplete('userClient', { serviceUrl:'jsonClient.action' });
  new Autocomplete('userProject', { serviceUrl:'jsonAllProject.action' });
</script>