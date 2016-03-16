<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<%@ page import="java.util.Date"%>

<link href="${ctx}/css/styles.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/autocomplete.js"></script>
<script type="text/javascript" src="${ctx}/js/popdiv-1.js"></script>
<s:if test="hasActionMessages()">
<s:iterator value="actionMessages">
<script type="text/javascript">
alert("<s:property escape="false"/>");
</script>
</s:iterator>
</s:if> 
<div id="sexyBG"></div><div id="sexyBOX"></div>
<div class="content">
<s:form id="contractListForm" action="/client/contract!contractList.action">
	<s:hidden name="searchView.leadId" value="%{searchView.leadId}" />
	<s:hidden name="searchView.pmId" value="%{searchView.pmId}" />
	<s:hidden name="searchView.areaId" value="%{searchView.areaId}" />
	<s:hidden name="searchView.clientTypeId" value="%{searchView.clientTypeId}" />
	<div style="margin:0 auto;width:90%;">
	<table align="center">
		<tr>
			<td>客户选择: <s:select list="searchView.contractClients" headerKey="0" headerValue="全部" name="searchView.clientId" value="searchView.clientId" listKey="id" listValue="name" id="clientId" style="width:250px"/>
			<td>客户名联想<s:textfield name="searchView.clientName" id="userClient" size="30"/></td>
			<td>合同状态</td><td><s:select list="statusBoxs" name="searchView.statusId" listKey="key" listValue="value"/></td>
			<td>合同类型</td><td><s:select list="searchView.businessTypes" headerKey="0" headerValue="全部" name="searchView.businessTypeId" listKey="id" listValue="name" style="width:100px"/></td>
			<td width="10%" rowspan="4"><input type="image" src="${ctx}/images/search.gif" onClick="userSubmit('contractListForm')"/></td>
		</tr>
		<tr>
			<td>项目选择: <s:select list="searchView.contractProjects" headerKey="0" headerValue="全部" name="searchView.projectId" value="searchView.projectId" listKey="id" listValue="name" id="projectId" style="width:250px"/></td>
			<td>项目名联想<s:textfield name="searchView.projectName" id="userProject" size="30"/></td>
			<td>行业</td><td><s:select list="searchView.industrys" headerKey="0" headerValue="全部" name="searchView.industryId" listKey="id" listValue="name"/></td>
			<td>销售</td><td><s:select list="searchView.bds" headerKey="0" headerValue="全部" name="searchView.bdId" listKey="id" listValue="name" style="width:100px"/></td>
		</tr>
	</table>
	</div>
	</s:form>
	<table class="report" borderColor="#f2f2f2" border="1" width="100%">
		<tr>
			<td align="center" colspan="15" width="80%" class="green-bg1"><h1>我的合同列表</h1></td>
		</tr>
		<tr>
			<td align="right" colspan="15">
				<report:pages action="${ctx}/client/contract!contractList.action" excel="${ctx}/client/contract!excelOutput.action" page="${page}"/>
			</td>
		</tr>
		<tr>
			<th width="8%"><report:tableTitle title="合同编号" action="contract!contractList.action" sqlName="c.code" page="${page}" ctx="${ctx}"/></th>
			<th width="8%"><report:tableTitle title="客户名" action="contract!contractList.action" sqlName="c.contractProject.contractClient.name" page="${page}" ctx="${ctx}"/></th>
			<th width="10%"><report:tableTitle title="项目名" action="contract!contractList.action" sqlName="c.contractProject.name" page="${page}" ctx="${ctx}"/></th>
			<th width="5%"><report:tableTitle title="行业" action="contract!contractList.action" sqlName="c.contractProject.industry.id" page="${page}" ctx="${ctx}"/></th>
			<th width="5%"><report:tableTitle title="创建人" action="contract!contractList.action" sqlName="c.createUser.name" page="${page}" ctx="${ctx}"/></th>
			<th width="5%"><report:tableTitle title="销售" action="contract!contractList.action" sqlName="c.contractProject.bd.name" page="${page}" ctx="${ctx}"/></th>
			<th width="5%"><report:tableTitle title="创建日期" action="contract!contractList.action" sqlName="c.createAt" page="${page}" ctx="${ctx}"/></th>
			<th width="10%">合同状态</th>
			<th width="8%"><report:tableTitle title="上一次提醒时间" action="contract!contractList.action" sqlName="c.lastRemind" page="${page}" ctx="${ctx}"/></th>
			<th width="8%"><report:tableTitle title="审核完毕时间" action="contract!contractList.action" sqlName="c.finishTime" page="${page}" ctx="${ctx}"/></th>
			<th width="20%">编辑</th>
			<th width="8%" <c:if test="${sessionScope.role['type'] == 14}">style="display:none"</c:if>>查看历史修改意见</th>
		</tr>
	
		<s:iterator value="page.result" status="index">
		<s:if test="#index.odd == true">
			<tr class="table_style1">
		</s:if>
		<s:else>
			<tr class="table_style2">
		</s:else>
			</tr>
				<td>${code}</td>
				<td><p title="${contractProject.contractClient.name}">${contractProject.contractClient.cutName}</p></td>
				<td>${contractProject.name}</td>
				<td>${contractProject.industry.name}</td>
				<td>${createUser.name}</td>
				<td>${contractProject.bd.name}</td>
				<td><report:dateFormat date="${createAt}"/></td>
				<td><c:if test="${status == 2}"><span style="color:red"></c:if>${statusDisplay}<c:if test="${status == 2}"></span></c:if></td>
				<td>${lastRemindStr}</td>
				<td>${finishTimeStr}</td>
				<td>
				<c:if test="${(sessionScope.user_session.userId == chargePerson.id) && (status > 2 && status < 9)}"><s:if test="canRemind==1">
					&nbsp;<a href="${ctx}/client/contract!remind.action?contractId=${id}">提醒</a>
				</s:if></c:if>
				<c:if test="${(sessionScope.user_session.userId == createUser.id) && (status == 9)}">
					&nbsp;<a onclick="pagedisable(this);" href="${ctx}/client/verifyContract!applyAction.action?contractId=${id}">客户通过</a>
					&nbsp;<a onclick="pagedisable(this);" href="${ctx}/client/verifyContract!applyAction.action?contractId=${id}&action=deny">客户不通过</a>
				</c:if>
				<c:if test="${((sessionScope.role['type'] == status) && (status != 2) && (sessionScope.role['type'] != 10))}">
					&nbsp;<a href="${ctx}/client/verifyContract!verify.action?contractId=${id}&filter=${page.filter}&pageNo=${page.pageNo}">审核</a>
				</c:if>
				<c:if test="${((sessionScope.role['type'] == 8 &&(status==4||status==5 ||status==6||status==7||status==13)) && (status != 2) && (sessionScope.role['type'] != 10))}">
					&nbsp;<a href="${ctx}/client/verifyContract!verify.action?contractId=${id}&filter=${page.filter}&pageNo=${page.pageNo}">审核</a>
				</c:if>
				<c:if test="${((sessionScope.role['type'] == status) && (status != 2) && (sessionScope.role['type'] == 10))}">
					&nbsp;<a href="${ctx}/client/verifyContract!verify.action?contractId=${id}&filter=${page.filter}&pageNo=${page.pageNo}">Check</a>
				</c:if>
				<c:if test="${(status == 1)&&(!(prefix == undefined))&&((sessionScope.role['type']=='2') || (sessionScope.role['type']=='3'))}">
					&nbsp;<a onclick="pagedisable(this);" href="${ctx}/client/verifyContract!edit.action?contractId=${id}&filter=${page.filter}&pageNo=${page.pageNo}">提交上级审核</a>
				</c:if>
				<c:if test="${((sessionScope.role['type'] == status||(sessionScope.role['type'] == 8 &&(status==4||status==5||status==6||status==7||status==13))) && (status != 2) && (sessionScope.role['type'] != 10)) || ((sessionScope.role['type'] != 10) && (status == 1)&&(!(prefix == undefined))&&((sessionScope.role['type']=='2') || (sessionScope.role['type']=='3')))}">
					&nbsp;<a href="${ctx}/contract/${id}${prefix}?key=<%=new Date().getTime()%>" target="_blank">预览</a>
				</c:if>
				<c:if test="${((sessionScope.role['type'] == status||(sessionScope.role['type'] == 8 &&(status==4||status==5||status==6||status==7||status==13))) && (status != 2) && (sessionScope.role['type'] == 10)) || ((sessionScope.role['type'] == 10) && (status == 1)&&(!(prefix == undefined))&&((sessionScope.role['type']=='2') || (sessionScope.role['type']=='3')))}">
					&nbsp;<a href="${ctx}/contract/${id}${prefix}?key=<%=new Date().getTime()%>" target="_blank">Preview</a>
				</c:if>
				<c:if test="${((sessionScope.role['type'] == 2) || (sessionScope.role['type'] == 3))&& (status < 3)}">
					&nbsp;<a href="#" onclick="upload(${id})">上传合同</a>
			    </c:if>
			    <c:if test="${(prefix == undefined || status == 2)&&(contractProject.businessType.id!=6)}">
				    &nbsp;<a href="${ctx}/template/${areaId}/${contractProject.darwinName.id}/${contractProject.businessType.name}.doc" onclick="alert('合同模板下载后,请填写合同编号。\n合同编号：${code}');">模板下载</a>
			    </c:if>
				<s:if test="status==9">
					&nbsp;<a href="${ctx}/contract/${id}${prefix}">合同草样下载</a>
				</s:if>
				<s:if test="status>10&&status<13">
					&nbsp;<a href="${ctx}/contract/${id}${prefix}">Word版合同下载</a>
				</s:if>
				<c:if test="${((sessionScope.role['type'] == 2)|| (sessionScope.role['type'] == 8)) && (status ==11)}">
					&nbsp;<a href="#" onclick="uploadFinal(${id})">扫描版上传</a>
				</c:if>
				<c:if test="${(sessionScope.role['type'] == 8) && (status ==11)}">
					&nbsp;<a href="${ctx}/client/contract!remind.action?contractId=${id}">扫描提醒</a>
				</c:if>
				<s:if test="status==12">
					&nbsp;<a href="${ctx}/client/contract!finalPdf.action?contractId=${id}">合同扫描版下载</a>
				</s:if>
				<s:if test="status>1">
					&nbsp;<a href="${ctx}/client/verifyContract!view.action?contractId=${id}&filter=${page.filter}&pageNo=${page.pageNo}">查看摘要</a>
				</s:if>
				<c:if test="${(sessionScope.role['type'] == 2) && (status >2) && (status < 11)}">
					&nbsp;<a href="${ctx}/client/verifyContract!cancel.action?contractId=${id}&filter=${page.filter}&pageNo=${page.pageNo}">取消审核</a>
				</c:if>
				</td>
				<c:if test="${sessionScope.role['type'] != 10}">
				<td <c:if test="${sessionScope.role['type'] == 14}">style="display:none"</c:if>><a href="${ctx}/client/version!list.action?contractId=${id}">查看历史版本</a></td>
				</c:if>
				<c:if test="${sessionScope.role['type'] == 10}">
				<td><a href="${ctx}/client/version!list.action?contractId=${id}">History versions</a></td>
				</c:if>
		</s:iterator>
		<tr>
			<td align="right" colspan="15">
				<report:pages action="${ctx}/client/contract!contractList.action" excel="${ctx}/client/contract!excelOutput.action" page="${page}"/>
			</td>
		</tr>
	</table>
</div>

<div id="applyDiv" style="VISIBILITY:hidden;position: absolute;">
<s:form id="viewForm" action="/client/contract!userAction.action" type="post">
<s:hidden name="contractPage.code" id="type"/>
<s:hidden name="contractId" id="contractId" value="%{contractId}" />
<table class="report" borderColor="#f2f2f2" cellpadding="30" width="1000px" height="150px">
	<tr align="center">
		<td colspan="2">
			<input type="button" value="" onClick="userApply()" id="userSubmit"/>
			<s:textarea id="denyReason" name="contractPage.content" value="请填写拒绝理由" cols="50" rows="5" style="display:none;"/>
			<input type="button" value="审核不通过" onclick="userDeny()" />
			<input type="button" onmouseout="setTimeout('document.onclick=function(){}',1);" onmousemove="setTimeout('sexyTOG()',1);" value="返回">
			</td>
		</td>
	</tr>
</table>
</s:form>
</div>
<div id="uploadDiv" style="VISIBILITY:hidden;position: absolute;height:300px;">
<s:form action="/client/contract!upload.action" method ="POST" enctype ="multipart/form-data">
<table class="report" borderColor="#f2f2f2" cellpadding="30" width="1000px" height="150px">
	<tr align="center">
		<td>
	<s:hidden name="contractId" id="uploadId"/>
	<s:file name ="uploadFile" label ="Image File" size="30"/>
    <s:submit class="editButton" value="合同上传"/>    <input type="button" onmouseout="setTimeout('document.onclick=function(){}',1);" onmousemove="setTimeout('sexyTOG()',1);" value="返回">
    	</td>
    </tr>
</table>
</s:form>
</div>
<div id="uploadFinalDiv" style="VISIBILITY:hidden;position: absolute;height:300px;">
<s:form action="/client/contract!uploadFinal.action" method ="POST" enctype ="multipart/form-data">
<table class="report" borderColor="#f2f2f2" cellpadding="30" width="1000px" height="150px">
	<tr align="center">
		<td>
	<s:hidden name="contractId" id="uploadFinalId"/>
	<s:file name ="uploadFile" label ="Image File" size="30"/>
    <s:submit class="editButton" value="合同上传"/>    <input type="button" onmouseout="setTimeout('document.onclick=function(){}',1);" onmousemove="setTimeout('sexyTOG()',1);" value="返回">
    	</td>
    </tr>
</table>
</s:form>
</div>
<script type="text/javascript">
function upload(id){
	document.getElementById("uploadId").value = id;
	sexyBOX('上传合同','uploadDiv','500');
}
function uploadFinal(id){
	document.getElementById("uploadFinalId").value = id;
	sexyBOX('扫描版上传','uploadFinalDiv','500');
}
function apply(id,status){
	document.getElementById("contractId").value = id;
	if (status==3){
		document.getElementById("userSubmit").value = '提交业务主管审核';
	} else if (status==4||status==5||status==6||status==7||status==13){
		document.getElementById("userSubmit").value = '提交FIN主管审核';
	} else if (status==8){
		document.getElementById("userSubmit").value = '提交客户审核';
	} else if (status==10){
		document.getElementById("userSubmit").value = '审核通过';
	}
	sexyBOX('审核','applyDiv','500');
}
function userApply(){
	document.getElementById("type").value = 'apply';
	document.getElementById("viewForm").submit();	
}
function userDeny(){
	if(document.getElementById("denyReason").style.display=='none'){
		document.getElementById("denyReason").style.display='';
		document.getElementById("userSubmit").style.display='none';
	} else {
		document.getElementById("type").value = 'deny';
		if (document.getElementById("denyReason").value==''){
			alert("请填写拒绝理由");
		} else {
			document.getElementById("viewForm").submit();	
		}
	}
}
function pagedisable(a){
	var url = a.href;
	a.href="javascript:void(0)";
	window.location = url;
}
  new Autocomplete('userClient', { serviceUrl:'jsonContractClient.action' });
  new Autocomplete('userProject', { serviceUrl:'jsonContractProject.action' });
</script>
