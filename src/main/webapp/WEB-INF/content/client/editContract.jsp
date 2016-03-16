<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<script type="text/javascript" src="${ctx}/js/calendar.js"></script>
<script type="text/javascript">
function userAction(action){
	var contractStartDate = document.getElementById("contractStartDate").value;
	if (contractStartDate == ''){
		alert("合同开始时间不能为空");
		document.getElementById("contractStartDate").focus();
		return;
	}
	var contractEndDate = document.getElementById("contractEndDate").value;
	if (contractEndDate == ''){
		alert("合同结束时间不能为空");
		document.getElementById("contractEndDate").focus();
		return;
	}
	var monthlyBudget = document.getElementById("monthlyBudget").value;
	if (monthlyBudget == ''){
		alert("每月预算不能为空");
		document.getElementById("monthlyBudget").focus();
		return;
	}
	var paymentTerm = document.getElementById("paymentTerm").value;
	if (paymentTerm == ''){
		alert("付款期限不能为空");
		document.getElementById("paymentTerm").focus();
		return;
	}
	document.getElementById("action").value=action;
	document.getElementById("applyButton").disabled = "true";
	document.getElementById("contractForm").submit();
}
</script>
<div class="content50">
<s:form name="contractForm" id="contractForm" action="/client/verifyContract!applyAction.action">
<s:hidden name="contractId" id="contractId" value="%{contractId}" />
<s:hidden name="filter" value="%{filter}" />
<s:hidden name="pageNo" value="%{pageNo}" />
<s:hidden id="action" name="action" />
<table class="report" borderColor="#f2f2f2" border="1">
	<tr>
		<td width="80%" colspan="8">
		<a href="${ctx}/client/contract!contractList.action">合同列表</a> -> 编辑摘要
		</td>
	</tr>
	<tr>
		<td align="center" width="80%" colspan="8" class="green-bg1"><h1>编辑摘要</h1></td>
	</tr>
	<tr >
		<td colspan="8">
			<div class='list-nav'><div class='pagination' align="center"><h2>Contract Abstract</h2></div></div>
		</td>
	</tr>
	<tr>
		<td width="30%">Darwin Company Name</td>
		<td><s:textfield name="contract.contractProject.darwinName.name" size="30" readonly="true"/></td>
	</tr>
	<tr>
		<td>Client Company Name</td>
		<td><s:textfield name="contract.contractProject.contractClient.name" size="30" readonly="true"/></td>
	</tr>
	<tr>
		<td>Project Name</td>
		<td><s:textfield name="contract.contractProject.name" size="30" readonly="true"/></td>
	</tr>
	<tr>
		<td>Project Categoty</td>
		<td><s:textfield name="contract.contractProject.businessType.name" size="30" readonly="true"/></td>
	</tr>
	<tr>
		<td>Product</td>
		<td><s:textfield name="contract.product" size="30" /></td>
	</tr>
	<tr>
		<td><span style="color:red">Contract Start Date</span></td>
		<td><s:textfield id="contractStartDate" name="contract.contractStartDate" size="15" onClick="showcalendar(event, this);" readonly="true"/></td>
	</tr>
	<tr>
		<td><span style="color:red">Contract End Date</span></td>
		<td><s:textfield id="contractEndDate" name="contract.contractEndDate" onClick="showcalendar(event, this);" size="15" readonly="true"/></td>
	</tr>
	<tr>
		<td><span style="color:red">Monthly Budget</span></td>
		<td><s:textfield id="monthlyBudget" name="contract.monthlyBudget" size="30"/>&nbsp;<span style="color:red">请加入货币符号，例如：RMB 1,000</span></td>
	</tr>
	<tr>
		<td>Service Fee</td>
		<td><s:textfield name="contract.serviceFee" size="30"/></td>
	</tr>
	<tr>
		<td>ROI</td>
		<td><s:textfield name="contract.roi" size="30"/></td>
	</tr>
	<tr>
		<td><span style="color:red">Payment Term</span></td>
		<td><s:textfield id="paymentTerm" name="contract.paymentTerm" size="30"/></td>
	</tr>
	<tr>
		<td>Special Terms</td>
		<td><textarea name="contract.specialTerms" cols="40" rows="5">${contract.specialTerms}</textarea></td>
	</tr>	
	<tr>
		<td>BD Name</td>
		<td><s:textfield name="contract.contractProject.bd.name" size="30" readonly="true"/></td>
	</tr>
	<tr>
		<td>Lead Name</td>
		<td><s:textfield name="contract.leadName" size="30" /></td>
	</tr>
	<tr>
		<td>Source</td>
		<td><input value="${contract.contractProject.contractClient.typeDisplayEn}" size="30" readonly="true"/></td>
	</tr>
	<tr>
		<td>最晚提醒时间</td>
		<td><s:textfield name="contract.remindDate" onClick="showcalendar(event, this);" size="15"/>&nbsp;
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
	<tr align="center">
		<td colspan="2">
			<input type="button" onClick="userAction('save')" value="保存摘要" />
			<input id="applyButton" type="button" onClick="userAction('saveAndApply')" value="提交上级审核"/>
			<input type="button" onClick="document.location='contract!contractList.action?page.pageNo=${pageNo}&page.filter=${filter}'" value="返回">
		</td>
	</tr>
</table>
</s:form>
</div>