<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<script type="text/javascript" src="${ctx}/js/calendar.js"></script>
<div class="content50">
<s:form id="seAmountListByProjectForm" action="/fee/seAmountListByProject.action">
    <s:hidden name="id" id="id" value="%{id}" />
    <s:hidden name="name" id="name" value="%{name}" />   
	<table align="center">
		<tr>
			<td colspan="2">查询渠道：${name}</td>
		</tr>
		<tr>
			<td width="75%">查询日期：<input type="text" name="startDate" value="${startDate}" onClick="showcalendar(event, this);" readonly="true"/>至
         		<input type="text" name="endDate"  value="${endDate}" onClick="showcalendar(event, this);" readonly="true"/></td>
			<td><input type="image" src="${ctx}/images/search.gif" onClick="document.getElementById("seAmountListByProjectForm").submit()"/></td>
		</tr>
	</table>
	<table class="report" borderColor="#f2f2f2" border="1" width="60%">
		<tr>
			<td align="center" width="80%" class="green-bg1" colspan="2"><h1>项目级别搜索引擎充值报表</h1></td>
		</tr>
		<tr>
			<td align="right" colspan="2">
				<report:pages action="seAmountListByProject.action" excel="seAmountListByAccount!projectExcelOutput.action" page="${page}"/>
			</td>
		</tr>
		<tr align="center">
			<td><input type="checkbox" class="chbx" checked value="0"/></td>
			<td><input type="checkbox" class="chbx" checked value="1"/></td>
		</tr>
		<tr>
			<th><report:tableTitle title="项目名称" action="seAmountListByProject.action" sqlName="s.account.project.name" page="${page}" ctx="${ctx}"/></th>
			<th><report:tableTitle title="充值金额" action="seAmountListByProject.action" sqlName="col_1_0_" page="${page}" ctx="${ctx}"/></th>
		</tr>
		<s:iterator value="page.result">
			<tr>
				<td ><a href="seAmountListByAccount.action?id=${id}&name=${name1}&startDate=${startDate}&endDate=${endDate}">${name1}</a>&nbsp;</td>
				<td align="right"><report:bigDecimalFormat value = "${sum}" />&nbsp;</td>
			</tr>
		</s:iterator>
		<tr><td >合计</td><td align="right"><report:bigDecimalFormat value = "${sum}" />&nbsp;</td></tr>
	</table>
	<report:pages action="seAmountListByProject.action" excel="seAmountListByAccount!projectExcelOutput.action" page="${page}"/>
</s:form>
</div>
