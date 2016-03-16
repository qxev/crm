<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<div class="content50">
<table class="report" borderColor="#f2f2f2" border="1">
    <font color="green"><s:actionmessage/></font>
    <tr>
		<td align="center" width="90%" colspan="3" class="green-bg1"><h1>PM列表</h1></td>
	</tr>
	<tr><td align="right" colspan="3"><input type="button" onclick="javascript:window.location.href='pmAddPage.action'" value="新增PM" /></td></tr>
	<tr>
	   <th width="45%"><report:tableTitle title="姓名" action="pmList.action" sqlName="name" page="${page}" ctx="${ctx}"/></th>
	   <th width="45%"><report:tableTitle title="分类" action="pmList.action" sqlName="businessType.name" page="${page}" ctx="${ctx}"/></th>
	   <th align="center">操作</th>
	</tr>
    <s:iterator value="page.result" status="index">
	<s:if test="#index.odd == true">
		<tr class="table_style1">
	</s:if>
	<s:else>
		<tr class="table_style2">
	</s:else>
			<td width="20%"><a href="${ctx}/client/projectList.action?searchView.pmId=${id}">${name}</a>&nbsp;</td>
			<td width="30%">${businessType.name}</a>&nbsp;</td>
			<td align="center"><a href="pmAddPage.action?id=${id}">修改</a>&nbsp;<a href="pmDelete.action?id=${id}">删除</a></td>
		</tr>
	</s:iterator>
</table>
<report:pages action="pmList.action" excel="" page="${page}"/>
</div>