<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<script type="text/javascript" src="${ctx}/js/selectUtil.js"></script>
<script type="text/javascript" src="${ctx}/js/popdiv.js"></script>
<div class="content50">
<s:form id="editProject" action="contractProject!editContractProject.action">
<table class="report" borderColor="#f2f2f2" align="center">
	<s:hidden name="contractProjectId" id="contractProjectId" value="%{contractProjectId}" />
	<s:hidden name="contractClientId" id="contractClientId" value="%{contractClientId}" />
	<s:hidden name="page.filter" value="%{filter}" />
	<s:hidden name="page.pageNo" value="%{pageNo}" />
	<s:if test="%{contractProjectId!=null||contractProjectId==0}">
	<tr><td></td></tr>
	<tr>
		<td width="80%" colspan="8">
		<s:if test="%{contractProject.businessType.id==4}">
		<a href="${ctx}/client/contractProject!contractProjectList.action">${contractProject.businessType.name}项目列表</a> -> 编辑项目
		</s:if>
		<s:else>
		<a href="${ctx}/client/contractProject!otherContractProjectList.action?businessTypeId=${contractProject.businessType.id}">${contractProject.businessType.name}项目列表</a> -> 编辑项目
		</s:else>
		</td>
	</tr>
	<tr>
		<td align="center" width="80%" colspan="8" class="green-bg1"><h1>编辑项目</h1></td>
	</tr>
	</s:if>
	<s:else>
	<tr>
		<td colspan="8"  width="80%" align="center"><img src="${ctx}/images/news02.jpg" /></td>
	</tr>
	</s:else>	
	<tr>
		<td width="8%"></td>
		<td width="15%" align="right">客户名：</td>
		<td>${contractClientName}</td>
	</tr>
		<tr>
		<td width="7%"></td><td align="right">达闻公司名：</td>
		<td> 
			<s:select headerKey="0" id="darwinNameId" headerValue="请选择" list="searchView.darwinNames" name="searchView.darwinNameId" value="contractProject.darwinName.id" listKey="id" listValue="name" /> <span style="color:red">*</span>
  		</td>
	</tr>
	<tr>
		<td width="8%"></td>
		<td width="15%" align="right">项目名：</td>
		<td><input type="text" id="projectName" value="${contractProject.name}" name="searchView.projectName" size="50"/> <span style="color:red">*请用英文命名</span></td>
	</tr>
	<tr>
		<td></td><td align="right">业务分类：</td>
		<s:if test="%{contractProjectId!=null||contractProjectId==0}">
		<td>${contractProject.businessType.name}<input name="searchView.businessTypeId" value="${contractProject.businessType.id}" type="hidden"></td>
		</s:if>
		<s:else>
		<td><s:select id="businessTypeId" headerKey="0" headerValue="请选择" list="searchView.businessTypes" name="searchView.businessTypeId" listKey="id" listValue="name" value="contractProject.businessType.id" onChange="changePm()"/> <span style="color:red">*</span></td>
		</s:else>
	</tr>
	<tr>
		<td width="7%"></td><td align="right">PM：</td>
		<td> 
			<s:select id="pmId" headerKey="0" headerValue="请选择" list="searchView.pms" name="searchView.pmId" value="contractProject.pm.id" listKey="id" listValue="name" /> <span style="color:red">*</span>
  		</td>
	</tr>
	<tr>
		<td align="center"></td><td align="right">销售：</td>
		<td>
			<s:select id="bdId" headerKey="0" headerValue="请选择" list="searchView.bds" name="searchView.bdId" value="contractProject.bd.id" listKey="id" listValue="name" /> <span style="color:red">*</span>
  		</td>
	</tr>
	<tr>
		<td></td><td align="right">行业：</td>
		<td><s:select id="industryId" headerKey="0" headerValue="请选择" list="searchView.industrys" name="searchView.industryId" listKey="id" listValue="name" value="contractProject.industry.id"/> <span style="color:red">*</span></td>
	</tr>
	<tr>
		<td></td><td></td>
		<td>
			<s:if test="%{contractProjectId!=null||contractProjectId==0}"><input type="button" onclick="checkSubmit();" value="保存" /></s:if>
			<s:else><input type="button" onclick="checkSubmit()" value="新建" /></s:else>
			<s:if test="%{contractProject.businessType==null}">
			<input type="button" value="返回" onClick="document.location='client/contractClientList.action'" />
			</s:if>
			<s:if test="%{contractProject.businessType.id==4}">
			<input type="button" value="返回" onClick="document.location='contractProject!contractProjectList.action?page.pageNo=${pageNo}&page.filter=${filter}'" />
			</s:if>
			<s:if test="%{contractProject.businessType.id>4&&contractProject.businessType.id<8}">
			<input type="button" value="返回" onClick="document.location='contractProject!otherContractProjectList.action?page.pageNo=${pageNo}&page.filter=${filter}&businessTypeId=${contractProject.businessType.id}'" />
			</s:if>
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
	var darwinNameId = document.getElementById("darwinNameId").value;
	if (darwinNameId == '0'){
		alert("达闻公司名不能为空");
		return;
	}
	
	var temp1 = document.getElementById("businessTypeId");
	if (temp1!=null){
		var businessTypeId = document.getElementById("businessTypeId").value;
		if (businessTypeId == '0'){
			alert("请选择业务分类");
			return;
		}
	}
	var pmId = document.getElementById("pmId").value;
	if (pmId == '0'){
		alert("PM不能为空");
		return;
	}
	var bdId = document.getElementById("bdId").value;
	if (bdId == '0'){
		alert("Bd管理员不能为空");
		return;
	}
	var industryId = document.getElementById("industryId").value;
	if (industryId == '0'){
		alert("行业不能为空");
		return;
	}
	document.getElementById("editProject").submit();
}

</script>