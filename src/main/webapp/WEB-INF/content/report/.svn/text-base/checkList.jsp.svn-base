<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<script type="text/javascript" src="${ctx}/js/calendar.js"></script>
<div class="content">
	<table class="report" borderColor="#f2f2f2" border="1">
		<tr>
			<td align="center" colspan="9" width="80%" class="green-bg1" colspan="5"><h1>抓取历史检查</h1></td>
		</tr>
		<tr>
			<th width="5%">账户ID</th>
			<th width="15%">账户名</th>
			<th width="15%">渠道名</th>
			<th width="10%">媒体名</th>
			<th width="10%">项目名</th>
			<th width="10%">区间开始日期</th>
			<th width="10%">区间结束日期</th>
			<th width="10%">总天数</th>
			<th width="15%">实际抓取条数</th>
		</tr>
		<s:iterator value="model" status="index">
			<s:if test="#index.odd == true">
				<tr class="table_style1">
			</s:if>
			<s:else>
				<tr class="table_style2">
			</s:else>
				<td>${accountId}</td>
				<td><a href="${ctx}/report/reportDailyList.action?searchView.accountId=${accountId}&searchView.startDate=0&searchView.endDate=0">${name}</a></td>
				<td>${channel}</td>
				<td>${media}</td>
				<td>${project}</td>
				<td><report:dateFormat date = "${startDate}" />&nbsp;</td>
				<td><report:dateFormat date = "${endDate}" />&nbsp;</td>
				<td>${days}&nbsp;</td>
				<td>${dbDays}&nbsp;</td>
			</tr>
		</s:iterator>
	</table>
</div>
