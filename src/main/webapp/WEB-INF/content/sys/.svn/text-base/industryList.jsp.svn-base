<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<div class="content50">
<table class="report" borderColor="#f2f2f2" border="1">
    <font color="green"><s:actionmessage/></font>
    <tr>
		<td align="center" width="90%" colspan="2" class="green-bg1"><h1>行业列表</h1></td>
	</tr>
	<tr><td align="right" colspan="2"><input type="button" onclick="javascript:window.location.href='industryAddPage.action'" value="新增行业" /></td></tr>
	<tr>
	   <td align="center" >行业 </td>
	   <td align="center" >操作</td>
	</tr>
    <s:iterator value="page.result" status="index">
		<s:if test="#index.odd == true">
			<tr class="table_style1">
		</s:if>
		<s:else>
			<tr class="table_style2">
		</s:else>
			<td width="20%"><a href="${ctx}/client/projectList.action?searchView.industryId=${id}">${name}</a>&nbsp;</td>
			<td align="center"><a href="industryAddPage.action?id=${id}">修改</a>&nbsp;<a href="industryDelete.action?id=${id}">删除</a></td>
		</tr>
	</s:iterator>
</table>
<report:pages action="industryList.action" excel="" page="${page}"/>
</div>