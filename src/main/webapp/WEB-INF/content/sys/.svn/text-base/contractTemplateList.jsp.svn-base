<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<div class="content50">
<s:form id="projectListForm" action="/sys/contractTemplate!list.action">
<table class="report" borderColor="#f2f2f2" border="1">
    <font color="green"><s:actionmessage/></font>
    <tr>
		<td align="center" width="90%" colspan="2"  class="green-bg1"><h1>合同模板列表</h1></td>
	</tr>
	<tr>
		<td align="center">
			业务分类：<s:select list="searchView.businessTypes" name="searchView.businessTypeId" listKey="id" listValue="name" value="searchView.businessTypeId"/> <span style="color:red">*</span></td>
			<td><input type="image" src="${ctx}/images/search.gif" onClick="userSubmit('projectListForm')"/></td>
		</td>
	</tr>
	<tr>
	   <td align="center">合同内容</td>
	   <td align="center">操作</td>
	</tr>
    <s:iterator value="page.result" status="index">
		<s:if test="#index.odd == true">
			<tr class="table_style1">
		</s:if>
		<s:else>
			<tr class="table_style2">
		</s:else>
			<td width="80%">${contextCut}</td>
			<td align="center"><a href="contractTemplate!edit.action?id=${id}">修改</a></td>
		</tr>
	</s:iterator>
</table>
<report:pages action="contract!list.action" excel="" page="${page}"/>
</s:form>
</div>
