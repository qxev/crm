<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<script type="text/javascript" src="${ctx}/js/calendar.js"></script>
<div class="content50">
<s:form id="viewForm" action="/client/verifyContract!applyAction.action">
<s:hidden name="contractId" id="contractId" value="%{contractId}" />
<s:hidden name="filter" value="%{filter}" />
<s:hidden name="pageNo" value="%{pageNo}" />
<s:hidden id="action" name="action" />
<table class="report" borderColor="#f2f2f2">
	<tr>
		<td width="80%" colspan="8">
		<a href="${ctx}/client/contract!contractList.action">合同列表</a> -> 审核合同
		</td>
	</tr>
	<tr>
		<td align="center" width="80%" colspan="8" class="green-bg1"><h1>审核合同</h1></td>
	</tr>
	<tr >
		<td colspan="8">
			<div class='list-nav'><div class='pagination' align="center"><h2>Contract Abstract</h2></div></div>
		</td>
	</tr>
	<tr>
		<td width="30%">Darwin Company Name</td>
		<td>${contract.contractProject.darwinName.name}</td>
	</tr>
	<tr class="table_style1">
		<td >Client Company Name</td>
		<td>${contract.contractProject.contractClient.name}</td>
	</tr>
	<tr>
		<td>Project Name</td>
		<td>${contract.contractProject.name}</td>
	</tr>
	<tr class="table_style1">
		<td>Project Categoty</td>
		<td>${contract.contractProject.businessType.name}</td>
	</tr>
	<tr>
		<td>Product</td>
		<td>${contract.product}</td>
	</tr>
	<tr class="table_style1">
		<td>Contract Start Date</td>
		<td>${contract.contractStartDate}</td>
	</tr>
	<tr>
		<td>Contract End Date</td>
		<td>${contract.contractEndDate}</td>
	</tr>
	<tr class="table_style1">
		<td>Monthly Budget</td>
		<td>${contract.monthlyBudget}</td>
	</tr>
	<tr>
		<td>Service Fee</td>
		<td>${contract.serviceFee}</td>
	</tr>
	<tr class="table_style1">
		<td>ROI</td>
		<td>${contract.roi}</td>
	</tr>
	<tr>
		<td>Payment Term</td>
		<td>${contract.paymentTerm}</td>
	</tr>
	<tr class="table_style1">
		<td>Special Terms</td>
		<td>${contract.specialTerms}</td>
	</tr>
	<tr>
		<td width="30%">BD Name</td>
		<td>${contract.contractProject.bd.name}</td>
	</tr>
	<tr class="table_style1">
		<td>Lead Name</td>
		<td>${contract.leadName}</td>
	</tr>
	<tr>
		<td>Source</td>
		<td>${contract.contractProject.contractClient.typeDisplayEn}</td>
	</tr>
	<s:if test="contract.status!=10">
	<tr>
		<td>最晚提醒时间</td>
		<td><input name="contract.remindDate" onClick="showcalendar(event, this);" size="15"/>&nbsp;
		<select name="contract.remindClock">
			<option value="9">9点</option>			
			<option value="10">10点</option>			
			<option value="11">11点</option>			
			<option value="12">12点</option>			
			<option value="13">13点</option>			
			<option value="14">14点</option>			
			<option value="15">15点</option>			
			<option value="16">16点</option>			
			<option value="17">17点</option>			
			<option value="18">18点</option>			
		</select>
		<span style="color:red">选填</span></td>
	</tr>
	</s:if>
	<tr align="center">
		<td colspan="2">
			<c:if test="${sessionScope.role['type'] != 10}">
			<input type="button" value="${note}" onClick="userApply()" id="userSubmit"/>
			<s:textarea id="denyReason" name="reason" value="请填写拒绝理由" cols="50" rows="5" style="display:none;" onfocus="onDeny()"/>
			<input type="button" value="审核不通过" onClick="userDeny()" />
			<input type="button" onClick="document.location='contract!contractList.action?page.pageNo=${pageNo}&page.filter=${filter}'" value="返回">
			</c:if>
			<c:if test="${sessionScope.role['type'] == 10}">
			<input type="button" value="${note}" onClick="userApply()" id="userSubmit"/>
			<s:textarea id="denyReason" name="reason" value="Please fill in reasons" cols="50" rows="5" style="display:none;" onfocus="onDeny1()"/>
			<input type="button" value="Refuse" onClick="userDeny()" />
			<input type="button" onClick="document.location='contract!contractList.action?page.pageNo=${pageNo}&page.filter=${filter}'" value="Return">
			</c:if>
		</td>
	</tr>
</table>
</s:form>
</div>
<script type="text/javascript">
function onDeny(){
	if (document.getElementById("denyReason").value=='请填写拒绝理由'){
		document.getElementById("denyReason").value = '';
	}
}
function onDeny1(){
	if (document.getElementById("denyReason").value=='Please fill in reasons'){
		document.getElementById("denyReason").value = '';
	}
}
function userApply(){
	document.getElementById("action").value = 'apply';
	document.getElementById("viewForm").submit();	
}
function userDeny(){
	if(document.getElementById("denyReason").style.display=='none'){
		document.getElementById("denyReason").style.display='';
		document.getElementById("userSubmit").style.display='none';
	} else {
		document.getElementById("action").value = 'deny';
		document.getElementById("viewForm").submit();	
	}
}
</script>