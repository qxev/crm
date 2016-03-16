<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<script type="text/javascript" src="${ctx}/js/calendar.js"></script>
<div class="content">
<s:form id="totalAmountListForm" action="/fee/totalAmountList.action">
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
			<td>查询日期:<s:textfield name="searchView.startDate" size="10" onClick="showcalendar(event, this);" readonly="true" /> 至 <s:textfield name="searchView.endDate" size="10" onClick="showcalendar(event, this);" readonly="true"/></td>
			<td><input type="image" src="${ctx}/images/search.gif" onClick="userSubmit('totalAmountListForm')"/></td>
		</tr>
	</table>
	</div>
		<table class="report" borderColor="#f2f2f2" border="1">
		<tr>
			<td align="center" colspan="9" width="80%" class="green-bg1"><h1>账户充值历史</h1></td>
		</tr>
		<tr>
			<td align="right" colspan="9">
				<report:pages action="totalAmountList.action" excel="totalAmountList!excelOutput.action" page="${page}"/>
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
			<td>&nbsp;</td>
		</tr>	
		<tr>
			<th width="5%">序号</th>
			<th width="18%"><report:tableTitle title="客户名" action="totalAmountList.action" sqlName="s.account.project.client.name" page="${page}" ctx="${ctx}"/></th>
			<th width="10%"><report:tableTitle title="项目名" action="totalAmountList.action" sqlName="s.account.project.name" page="${page}" ctx="${ctx}"/></th>
			<th width="18%"><report:tableTitle title="账户名" action="totalAmountList.action" sqlName="s.account.name" page="${page}" ctx="${ctx}"/></th>
			<th width="8%"><report:tableTitle title="媒体名" action="totalAmountList.action" sqlName="s.account.channel.media.name" page="${page}" ctx="${ctx}"/></th>
			<th width="18%"><report:tableTitle title="渠道名" action="totalAmountList.action" sqlName="s.account.channel.name" page="${page}" ctx="${ctx}"/></th>
			<th width="10%"><report:tableTitle title="冲值日期" action="totalAmountList.action" sqlName="s.supplementDate" page="${page}" ctx="${ctx}"/></th>
			<th width="18%"><report:tableTitle title="金额" action="totalAmountList.action" sqlName="totalAmount" page="${page}" ctx="${ctx}"/></th>
			<th width="10%">操作</th>
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
				<td align="right"><report:dateFormat date = "${supplementDate}" />&nbsp;</td>
				<td align="right"><report:bigDecimalFormat value = "${totalAmount}" />&nbsp;</td>
				<td align="center"><input type="button" value="编辑" onClick="document.location='initFee!initEditFee.action?supplementId=${id}&filter=${page.filter}&pageNo=${page.pageNo}&lastType=1'" /></td>
			</tr>
		</s:iterator>
		<tr>
			<td colspan="6" ></td>
			<td align="right">合计</td>
			<td align="right"><report:bigDecimalFormat value = "${sum}" /></td>
		</tr>
		<tr>
			<td align="right" colspan="9">
				<report:pages action="totalAmountList.action" excel="totalAmountList!excelOutput.action" page="${page}"/>
			</td>
		</tr>	
	</table>
</s:form>
</div>
