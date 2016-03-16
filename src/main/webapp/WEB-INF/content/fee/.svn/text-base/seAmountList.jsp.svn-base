<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<script type="text/javascript" src="${ctx}/js/calendar.js"></script>
<div class="content50">
<s:form id="seAmountListForm" action="/fee/seAmountList.action">
	<table align="center">
		<tr>
			<td width="75%">查询日期:<input type="text" name="startDate" value="${startDate}" onClick="showcalendar(event, this);" readonly="true"/>至
	         <input type="text" name="endDate" value="${endDate}" onClick="showcalendar(event, this);" readonly="true"/></td>
			<td><input type="image" src="${ctx}/images/search.gif" onClick="userSubmit('seAmountListForm')"/></td>
		</tr>
	</table>
	<table class="report" borderColor="#f2f2f2" border="1"  width="60%">
		<tr>
			<td align="center" width="80%" class="green-bg1" colspan="3"><h1>渠道级别搜索引擎充值报表</h1></td>
		</tr>
		<tr>
			<td align="right" colspan="3">
				<report:pages action="seAmountList.action" excel="seAmountListByAccount!channelExcelOutput.action" page="${page}"/>
			</td>
		</tr>
		<tr align="center">
			<td><input type="checkbox" class="chbx" checked value="0"/><s:if test="!(page.order=='asc'&&page.orderBy=='s.account.channel.media.name')"><img style="cursor:pointer" src="${ctx}/images/up.gif" onclick="document.location='seAmountList.action?page.pageNo=${page.pageNo}&page.filter=${page.filter}&pageSize=${pageSize}&page.order=asc&page.orderBy=s.account.channel.media.name'"/></s:if>
			<s:if test="!(page.order=='desc'&&page.orderBy=='s.account.channel.media.name')"><img src="${ctx}/images/down.gif" style="cursor:pointer" onclick="document.location='seAmountList.action?page.pageNo=${page.pageNo}&page.filter=${page.filter}&pageSize=${pageSize}&page.order=desc&page.orderBy=s.account.channel.media.name'"/></s:if></td>
			<td><input type="checkbox" class="chbx" checked value="1"/><s:if test="!(page.order=='asc'&&page.orderBy=='s.account.channel.name')"><img style="cursor:pointer" src="${ctx}/images/up.gif" onclick="document.location='seAmountList.action?page.pageNo=${page.pageNo}&page.filter=${page.filter}&pageSize=${pageSize}&page.order=asc&page.orderBy=s.account.channel.name'"/></s:if>
			<s:if test="!(page.order=='desc'&&page.orderBy=='s.account.channel.name')"><img src="${ctx}/images/down.gif" style="cursor:pointer" onclick="document.location='seAmountList.action?page.pageNo=${page.pageNo}&page.filter=${page.filter}&pageSize=${pageSize}&page.order=desc&page.orderBy=s.account.channel.name'"/></s:if></td>
			<td><input type="checkbox" class="chbx" checked value="2"/><s:if test="!(page.order=='asc'&&page.orderBy=='col_0_0_')"><img style="cursor:pointer" src="${ctx}/images/up.gif" onclick="document.location='seAmountList.action?page.pageNo=${page.pageNo}&page.filter=${page.filter}&pageSize=${pageSize}&page.order=asc&page.orderBy=col_0_0_'"/></s:if>
			<s:if test="!(page.order=='desc'&&page.orderBy=='col_0_0_')"><img src="${ctx}/images/down.gif" style="cursor:pointer" onclick="document.location='seAmountList.action?page.pageNo=${page.pageNo}&page.filter=${page.filter}&pageSize=${pageSize}&page.order=desc&page.orderBy=col_0_0_'"/></s:if></td>
		</tr>	
		<tr>
			<th><report:tableTitle title="媒体名" action="seAmountList.action" sqlName="s.account.channel.media.name" page="${page}" ctx="${ctx}"/></th>
			<th><report:tableTitle title="渠道名" action="seAmountList.action" sqlName="s.account.channel.media.name" page="${page}" ctx="${ctx}"/></th>
			<th><report:tableTitle title="充值金额" action="seAmountList.action" sqlName="s.account.channel.media.name" page="${page}" ctx="${ctx}"/></th>
		</tr>
		<s:iterator value="page.result" status="index">
		<s:if test="#index.odd == true">
			<tr class="table_style1">
		</s:if>
		<s:else>
			<tr class="table_style2">
		</s:else>
				<td >${name2}&nbsp;</td>
				<td><a href="seAmountListByProject.action?id=${id}&name=${name1}&startDate=${startDate}&endDate=${endDate}">${name1}</a>&nbsp;</td>
				<td align="right"><report:bigDecimalFormat value = "${sum}" />&nbsp;</td>
			</tr>
		</s:iterator>
		<tr>
			<td>合计</td>
			<td>&nbsp;</td>
			<td align="right"><report:bigDecimalFormat value = "${sum}" />&nbsp;</td>
		</tr>
	</table>
	<report:pages action="seAmountList.action" excel="seAmountListByAccount!channelExcelOutput.action" page="${page}"/>
</s:form>
</div>
