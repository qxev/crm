<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<script type="text/javascript">
function checkAndSubmit(){
	var clientId = document.getElementById("clientId").value;
	var projectId = document.getElementById("projectId").value;
	var mediaId = document.getElementById("mediaId").value;
	var channelId = document.getElementById("channelId").value;
	var accountName = document.getElementById("accountName").value;
	if (clientId==0){
		alert("请选择客户");
		return;
	}
	if (projectId==0){
		alert("请选择项目");
		return;
	}
	if (mediaId==0){
		alert("请选择媒体");
		return;
	}
	if (channelId==0){
		alert("请选择渠道");
		return;
	}
	if (accountName==""){
		alert("请填写账户名");
		return;
	}
	document.getElementById("accountAddForm").submit();
}
</script>
<div class="content">
<s:form id="accountAddForm" action="/client/accountList!add.action">
	<div style="margin:0 auto;width:65%;">
	<font color="red"><s:actionerror/></font>
	<font color="green"><s:actionmessage/></font>
		<table class="report" borderColor="#f2f2f2" border="1" width="100%">
		<tr>
			<td align="center" width="80%" colspan="2" class="green-bg1"><h1>新建账户</h1></td>
		</tr>
			<tr>
				<td width="20%" align="right">客户选择：</td>
				<td width="80%"><s:select list="searchView.clients" headerKey="0" headerValue="全部" name="searchView.clientId" value="searchView.clientId" listKey="id" listValue="name" id="clientId" onChange="changeProject()"/></td>
			</tr>
			<tr>
				<td align="right">项目选择：</td>
				<td><s:select list="searchView.projects" headerKey="0" headerValue="全部" name="searchView.projectId" value="searchView.projectId" listKey="id" listValue="name" id="projectId"/></td>
			</tr>
			<tr>
				<td align="right">媒体选择：</td>
				<td><s:select list="searchView.medias" headerKey="0" headerValue="全部" name="searchView.mediaId" id="mediaId" listKey="id" listValue="name" onChange="changeChannel()"/></td>
			</tr>
			<tr>
				<td align="right">渠道选择：</td>
				<td><s:select list="searchView.channels" headerKey="0" headerValue="全部" name="searchView.channelId" value="searchView.channelId" listKey="id" listValue="name" id="channelId"/></td>
			</tr>
			<tr>
				<td align="right">账户名：</td>
				<td><s:textfield name="searchView.accountName" id="accountName" style="width:140px;"/></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><input type="button" onClick="checkAndSubmit()" value="新增"/></td>
			</tr>
		</table>
	</div>
</s:form>
</div>
