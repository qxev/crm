<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<div class="content50">
<table class="report" borderColor="#f2f2f2" border="1">
    <font color="green"><s:actionmessage/></font>
    <tr>
		<td align="center" width="90%" colspan="3" class="green-bg1"><h1>${exchangeName}汇率</h1></td>
	</tr>
	<tr>
	   <td align="center">年月</td>
	   <td align="center">汇率</td>
	   <td align="center">操作</td>
	</tr>
    <s:iterator value="page.result" status="index">
		<s:if test="#index.odd == true">
			<tr class="table_style1">
		</s:if>
		<s:else>
			<tr class="table_style2">
		</s:else>
			<td width="20%" align="right">${exchangeDateDisplay}</td>
			<td width="20%" align="right">${value}&nbsp;</td>
			<td width="20%" align="right"><a href="exchangeList!initAdd.action?id=${id}&exchangeId=${exchangeId}&filter=${page.filter}&pageNo=${page.pageNo}">修改</a>	</td>
		</tr>
	</s:iterator>
		<tr>
			<td colspan="3" align="center">
				<input type="button" value="返回" onclick="window.location.href='exchangeList.action?page.pageNo=${pageNo}&page.filter=${filter}'" style="margin-right:40px;" />
			</td>
		</tr>
</table>
</div>