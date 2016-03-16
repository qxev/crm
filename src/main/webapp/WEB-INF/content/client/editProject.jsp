<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<script type="text/javascript" src="${ctx}/js/selectUtil.js"></script>
<script type="text/javascript" src="${ctx}/js/popdiv.js"></script>
<div class="content50">
<s:form id="editProject" action="projectList!editProject.action">
<table class="report" borderColor="#f2f2f2" align="center">
	<s:hidden name="projectId" id="projectId" value="%{projectId}" />
	<s:hidden name="clientId" id="clientId" value="%{clientId}" />
	<s:hidden name="page.filter" value="%{filter}" />
	<s:hidden name="page.pageNo" value="%{pageNo}" />
	<tr>
		<td align="center" width="80%" colspan="8" class="green-bg1"><h1><s:if test="%{projectId!=null||projectId==0}">编辑</s:if><s:else>新建</s:else>项目</h1></td>
	</tr>
	<tr>
		<td width="8%"></td>
		<td width="15%" align="right">客户名：</td>
		<td>${clientName}</td>
	</tr>
	<tr>
		<td width="8%"></td>
		<td width="15%" align="right">项目名：</td>
		<td><input type="text" id="projectName" value="${project.name}" name="searchView.projectName" size="50"/> <span style="color:red">*</span></td>
	</tr>
	<tr>
		<td width="7%"></td><td align="right">分析师：</td>
		<td> 
			<s:select list="searchView.pms" name="searchView.pmId" value="project.pmId" listKey="id" listValue="name" /> <span style="color:red">*</span>
  		</td>
	</tr>
	<tr>
		<td align="center"></td><td align="right">销售：</td>
		<td>
			<s:select list="searchView.bds" name="searchView.bdId" value="project.bdId" listKey="id" listValue="name" /> <span style="color:red">*</span>
  		</td>
	</tr>
	<tr>
		<td></td><td align="right">行业：</td>
		<td><s:select list="searchView.industrys" name="searchView.industryId" listKey="id" listValue="name" value="project.industry.id"/> <span style="color:red">*</span></td>
	</tr>
</table>
<table align="center" width="100%">
	<tr align="center">
		<td>
			<s:if test="%{projectId!=null||projectId==0}"><input type="button" onclick="checkSubmit()" value="保存"/></s:if>
			<s:else><input type="button" onclick="checkSubmit()" value="新建"/></s:else>
			<input type="button" value="返回" onClick="document.location='projectList.action?page.pageNo=${pageNo}&page.filter=${filter}'" />
		</td>
	</tr>
</table>
</s:form>
</div>
<script type="text/javascript">
function checkSubmit(){
	var projectName = document.getElementById("projectName").value;
	if (projectName == ''){
		alert("项目名不能为空");
		return;
	}
	document.getElementById("editProject").submit();
}
</script>