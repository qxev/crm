<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<script type="text/javascript" src="${ctx}/js/calendar.js"></script>
<div class="content">
<s:form id="discountListForm" action="/report/discountList.action">
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
			<td>查询日期:<s:textfield name="searchView.startDate" size="10" onClick="showcalendar(event, this);" readonly="true"/> 至 <s:textfield name="searchView.endDate" size="10" onClick="showcalendar(event, this);" readonly="true"/></td>
			<td><input type="image" src="${ctx}/images/search.gif" onClick="userSubmit('discountListForm')"/></td>
		</tr>
	</table>
	</div>
	<table class="report" borderColor="#f2f2f2" border="1">
		<tr>
			<td align="center" colspan="13" width="80%" class="green-bg1" colspan="5"><h1>折扣率历史</h1></td>
		</tr>
		<tr>
			<td align="right" colspan="13">
				<report:pages action="discountList.action" excel="discountList!excelOutput.action" page="${page}"/>
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
			<th width="3%">序号</th>
			<th width="15%"><report:tableTitle title="客户名" action="discountList.action" sqlName="account.project.client.name" page="${page}" ctx="${ctx}"/></th>
			<th width="7%"><report:tableTitle title="项目名" action="discountList.action" sqlName="account.project.name" page="${page}" ctx="${ctx}"/></th>
			<th width="10%"><report:tableTitle title="账户名" action="discountList.action" sqlName="account.name" page="${page}" ctx="${ctx}"/></th>
			<th width="6%"><report:tableTitle title="媒体名" action="discountList.action" sqlName="account.channel.media.name" page="${page}" ctx="${ctx}"/></th>
			<th width="10%"><report:tableTitle title="渠道名" action="discountList.action" sqlName="account.channel.name" page="${page}" ctx="${ctx}"/></th>
			<th width="7%"><report:tableTitle title="调整日期" action="discountList.action" sqlName="createAt" page="${page}" ctx="${ctx}"/></th>
			<th width="7%"><report:tableTitle title="调整后达闻折扣率" action="discountList.action" sqlName="darwinDiscount" page="${page}" ctx="${ctx}"/></th>
			<th width="7%"><report:tableTitle title="调整后项目折扣率" action="discountList.action" sqlName="projectDiscount" page="${page}" ctx="${ctx}"/></th>
			<th width="7%"><report:tableTitle title="调整后奖励折扣率" action="discountList.action" sqlName="bonusDiscount" page="${page}" ctx="${ctx}"/></th>
			<th width="7%">调整后总折扣率</th>
			<th width="7%"><report:tableTitle title="区间开始日期" action="discountList.action" sqlName="startDate" page="${page}" ctx="${ctx}"/></th>
			<th width="7%"><report:tableTitle title="区间结束日期" action="discountList.action" sqlName="endDate" page="${page}" ctx="${ctx}"/></th>
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
				<td>${account.channel.media.name}&nbsp;</td>
				<td>${account.channel.name}&nbsp;</td>
				<td align="right"><report:dateFormat date = "${createAt}" />&nbsp;</td>
				<td align="right"><report:bigDecimalFormat value = "${darwinDiscount}" />&nbsp;</td>
				<td align="right"><report:bigDecimalFormat value = "${projectDiscount}" />&nbsp;</td>
				<td align="right"><report:bigDecimalFormat value = "${bonusDiscount}" />&nbsp;</td>
				<td align="right"><report:bigDecimalFormat value = "${totalDiscount}" />&nbsp;</td>
				<td align="right"><report:dateFormat date = "${startDate}" />&nbsp;</td>
				<td align="right"><report:dateFormat date = "${endDateNotNull}" />&nbsp;</td>
			</tr>
		</s:iterator>
		<tr>
			<td align="right" colspan="13">
				<report:pages action="discountList.action" excel="discountList!excelOutput.action" page="${page}"/>
			</td>
		</tr>
	</table>
	</s:form>
</div>
