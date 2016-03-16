<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<div class="content50">
	<a href="${ctx}/client/contract!contractList.action">我的合同列表</a> -> 历史版本列表
	<table class="report" borderColor="#f2f2f2" border="1">
		<tr>
			<td align="center" colspan="4" width="80%" class="green-bg1"><h1>历史版本列表</h1></td>
		</tr>
		<tr>
			<th width="20%">版本名称</th>
			<th width="30%">修改日期</th>
			<th width="30%">建议负责人</th>
			<th width="20%">查看历史版本</th>
		</tr>
	
		<s:iterator value="page.result" status="index">
		<s:if test="#index.odd == true">
			<tr class="table_style1">
		</s:if>
		<s:else>
			<tr class="table_style2">
		</s:else>
				<td>${version}</td>
				<td><report:dateFormat date = "${createAt}" /></td>
				<td>${suggestUser.name}</td>
				<td><a href="${ctx}/contract/${contract.id}${version}${prefix}">下载${version}版本</a></td>
			</tr>
		<s:if test="#index.odd == true">
			<tr class="table_style1">
		</s:if>
		<s:else>
			<tr class="table_style2">
		</s:else>
			<td colspan="4">
				${reasonhtml}
			</td>
			</tr>
		</s:iterator>
		<tr>
			<td align="center" colspan="4">
				<input type="button" value="返回" onClick="document.location='contract!contractList.action'" /></td>
			</td>
		</tr>
	</table>
</div>
