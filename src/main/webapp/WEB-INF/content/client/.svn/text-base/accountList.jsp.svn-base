<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<link href="${ctx}/css/styles.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/autocomplete.js"></script>
<div class="content">
<s:form id="accountListForm" action="/client/accountList.action">
	<font color="red"><s:actionerror/></font>
	<font color="green"><s:actionmessage/></font>
	<s:if test="projectName!=null&&channelName!=null">${clientName} -> ${projectName} -> ${mediaName} -> ${channelName} -> 帐户列表 </s:if>
	<div style="margin:0 auto;width:70%;">
	<table align="center">
		<tr>
			<td width="60%">客户选择: <s:select list="searchView.clients" headerKey="0" headerValue="全部" name="searchView.clientId" value="searchView.clientId" listKey="id" listValue="name" id="clientId" onChange="changeProject()" style="width:250px"/>
			</td>
			<td width="30%">媒体选择: <s:select list="searchView.medias" headerKey="0" headerValue="全部" name="searchView.mediaId" id="mediaId" listKey="id" listValue="name" onChange="changeChannel()"/></td>
			<td width="10%" rowspan="4"><input type="image" src="${ctx}/images/search.gif" onClick="userSubmit('accountListForm')"/></td>
		</tr>
		<tr>
			<td>项目选择: <s:select list="searchView.projects" headerKey="0" headerValue="全部" name="searchView.projectId" value="searchView.projectId" listKey="id" listValue="name" id="projectId" style="width:250px"/></td>
			<td>渠道选择: <s:select list="searchView.channels" headerKey="0" headerValue="全部" name="searchView.channelId" value="searchView.channelId" listKey="id" listValue="name" id="channelId"/></td>
		</tr>
		<tr>
			<td>PM选择: <s:select list="searchView.pms" headerKey="0" headerValue="全部" name="searchView.pmId" listKey="id" listValue="name"/></td>
			<td>状态: <s:select list="searchView.statuses" headerKey="0" headerValue="全部" name="searchView.statusId" listKey="key" listValue="value"/>
			</td>
		</tr>
		<tr>
			<td>客户名联想:<s:textfield name="searchView.clientName" id="userClient" size="40"/></td>
			<td>项目名联想:<s:textfield name="searchView.projectName" id="userProject" size="20"/></td>
		</tr>
	</table>
	</div>
	<table class="report" borderColor="#f2f2f2" border="1" width="100%">
		<tr>
			<td align="center" colspan="16" width="80%" class="green-bg1"><h1>帐户列表</h1></td>
		</tr>
		<tr>
			<td align="right" colspan="16">
				<report:pages action="accountList.action" excel="accountList!excelOutput.action" page="${page}"/>
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
			<td><input type="checkbox" class="chbx" checked value="12"/></td>
			<td><input type="checkbox" class="chbx" checked value="13"/></td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<th width="2%">序号</th>
			<th width="15%"><report:tableTitle title="客户名" action="accountList.action" sqlName="project.client.name" page="${page}" ctx="${ctx}"/></th>
			<th width="8%"><report:tableTitle title="项目名" action="accountList.action" sqlName="project.name" page="${page}" ctx="${ctx}"/></th>
			<th width="10%"><report:tableTitle title="帐户名" action="accountList.action" sqlName="name" page="${page}" ctx="${ctx}"/></th>
			<th width="6%"><report:tableTitle title="媒体名" action="accountList.action" sqlName="channel.media.name" page="${page}" ctx="${ctx}"/></th>
			<th width="8%"><report:tableTitle title="渠道名" action="accountList.action" sqlName="channel.name" page="${page}" ctx="${ctx}"/></th>
			<th width="8%">PM</th>
			<th width="5%"><report:tableTitle title="折扣率" action="accountList.action" sqlName="currentDiscount" page="${page}" ctx="${ctx}"/></th>
			<th width="5%"><report:tableTitle title="折扣返还率" action="accountList.action" sqlName="currentDiscountBack" page="${page}" ctx="${ctx}"/></th>
			<th width="5%"><report:tableTitle title="月服务费" action="accountList.action" sqlName="currentServiceFee" page="${page}" ctx="${ctx}"/></th>
			<th width="5%"><report:tableTitle title="月服务费率" action="accountList.action" sqlName="currentServiceFeeRate" page="${page}" ctx="${ctx}"/></th>
			<th width="7%"><report:tableTitle title="帐户余额" action="accountList.action" sqlName="totalBalance" page="${page}" ctx="${ctx}"/></th>
			<th width="7%"><report:tableTitle title="每日预算" action="accountList.action" sqlName="dailyBudget" page="${page}" ctx="${ctx}"/></th>
			<th width="5%"><report:tableTitle title="账户状态" action="accountList.action" sqlName="status" page="${page}" ctx="${ctx}"/></th>
			<th width="5%"><report:tableTitle title="预警提醒" action="accountList.action" sqlName="alert" page="${page}" ctx="${ctx}"/></th>
			<th width="5%">操作</th>
		</tr>
	
		<s:iterator value="page.result" status="index">
		<s:if test="#index.odd == true">
			<tr class="table_style1">
		</s:if>
		<s:else>
			<tr class="table_style2">
		</s:else>
				<td>${index.index+1}</td>
				<td><p title="${project.client.name}">${project.client.cutName}</p></td>
				<td>${project.name}&nbsp;</td>
				<td><a href="${ctx}/report/reportDailyList.action?searchView.accountId=${id}&searchView.startDate=0&searchView.endDate=0">${name}</a></td>
				<td>${channel.media.name}&nbsp;</td>
				<td><p title="${channel.name}">${channel.cutName}</p></td>
				<td>${currentPms}&nbsp;</td>
				<td align="right"><report:bigDecimalFormatNotRMB value = "${currentDiscount}" />%&nbsp;</td>
				<td align="right"><report:bigDecimalFormatNotRMB value = "${currentDiscountBack}" />%&nbsp;</td>
				<td align="right"><s:if test="serviceType == 1"><report:bigDecimalFormat value = "${currentServiceFee}" /></s:if></td>
				<td align="right"><s:if test="serviceType == 2"><report:bigDecimalFormatNotRMB value = "${currentServiceFeeRate}" />%</s:if></td>
				<td align="right"><report:bigDecimalFormat value = "${totalBalance}" />&nbsp;</td>
				<td align="right"><report:bigDecimalFormat value = "${dailyBudget}" />&nbsp;</td>
				<td align="right"><s:if test="status == 1"><img src="${ctx}/images/yes.png" width="15px" height="15px"/></s:if>
				                  <s:if test="status == 2"><img src="${ctx}/images/no.png" width="15px" height="15px"/></s:if></td>
				<td align="right"><s:if test="alert == 1"><img src="${ctx}/images/yes.png" width="15px" height="15px"/></s:if>
								  <s:if test="alert == 0"><img src="${ctx}/images/no.png" alt="编辑" width="15px" height="15px"/></s:if></td>
				<td align="right">
				<c:if test="${sessionScope.role['2.5.2'] != null}">
				<img src="${ctx}/images/edit.png" title="编辑" style="cursor:pointer" onClick="document.location='editAccount.action?accountId=${id}&userAction=initAccount&filter=${page.filter}&pageNo=${page.pageNo}'" />
				</c:if>
				<s:if test="channel.id == 10 || channel.id == 22 || channel.id == 23 || channel.id == 31"><img src="${ctx}/images/import.png" title="导入" style="cursor:pointer" onClick="document.location='editAccount!importInit.action?accountId=${id}&userAction=initAccount&filter=${page.filter}&pageNo=${page.pageNo}'" /></s:if></td>
			</tr>
		</s:iterator>
			<tr>
				<td colspan="10">&nbsp;</td>
				<td>合计 </td>
				<td align="right"><report:bigDecimalFormat value = "${sumTotalBalance}" /></td>
				<td align="right"><report:bigDecimalFormat value = "${sumDailyBudget}" /></td>
				<td colspan="2">&nbsp;</td>
			</tr>
		<tr>
			<td align="right" colspan="16">
				<report:pages action="accountList.action" excel="accountList!excelOutput.action" page="${page}"/>
			</td>
		</tr>
	</table>
</s:form>
</div>
<script type="text/javascript">
  new Autocomplete('userClient', { serviceUrl:'jsonClient.action' });
  new Autocomplete('userProject', { serviceUrl:'jsonAllProject.action' });
</script>
