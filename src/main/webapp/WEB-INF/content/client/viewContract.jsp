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
		<a href="${ctx}/client/contract!contractList.action">合同列表</a> -> 查看合同摘要
		</td>
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
	<tr align="center">
		<td colspan="2">
			<input type="button" onClick="document.location='contract!contractList.action?page.pageNo=${pageNo}&page.filter=${filter}'" value="返回">
		</td>
	</tr>
</table>
</s:form>
</div>