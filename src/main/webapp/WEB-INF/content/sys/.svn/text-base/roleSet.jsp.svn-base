<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<link href="${ctx}/css/styles.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/autocomplete.js"></script>
<div class="content">
<s:form action="/sys/role!roleSet.action">
	<div style="margin:0 auto;width:75%;">
	<font color="red"><s:actionerror/></font><font color="green"><s:actionmessage/></font>
	<table align="center" width="50%">
		<tr>
			<td width="52%">代理人选择: <s:select list="model.users" headerKey="0" headerValue="无" name="model.userId" value="model.userId" listKey="id" listValue="name"/></td>
			<td width="48%"><input type="submit" value="确认">
		</tr>
	</table>
</s:form>
</div>
