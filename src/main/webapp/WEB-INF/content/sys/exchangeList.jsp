<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<div class="content50">
<table class="report" borderColor="#f2f2f2" border="1">
    <font color="green"><s:actionmessage/></font>
    <tr>
		<td align="center" width="90%" colspan="3" class="green-bg1"><h1>汇率列表</h1></td>
	</tr>
	<tr>
	   <td align="center">国家</td>
	   <td align="center">日期</td>
	   <td align="center">汇率</td>
	</tr>
    <s:iterator value="page.result" status="index">
		<s:if test="#index.odd == true">
			<tr class="table_style1">
		</s:if>
		<s:else>
			<tr class="table_style2">
		</s:else>
			<td width="20%" align="right"><a href="${ctx}/sys/exchangeList!oneExchangeList.action?exchangeId=${exchange.id}">${exchange.country}</a></td>
			<td width="20%" align="right"><report:dateFormat date = "${exchangeDate}" />&nbsp;</td>
			<td width="20%" align="right">${value}&nbsp;</td>
		</tr>
	</s:iterator>
</table>
</div>