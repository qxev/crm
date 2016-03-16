<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<script type="text/javascript" src="${ctx}/js/calendar.js"></script>
<script type="text/javascript" src="${ctx}/js/popdiv.js"></script>
<script type="text/javascript">
function addRepay(supplementHistoryId,accountId){
	document.getElementById("supplementHistoryId").value=supplementHistoryId;
	document.getElementById("accountId").value=accountId;
	sexyBOX('还款','addRepayDiv','250');
	return false;
}
function checkAndSubmit(){
	var payAmount = document.getElementById('payAmount').value;
	var supplementDate = document.getElementById('supplementDate').value;
	if ((payAmount=='')||isNaN(Number(payAmount))){
		alert("请输入正确的还款金额");
		return;
	}else if(supplementDate==''){
		alert("请输入还款时间");
		return;
	}
	document.getElementById("addRepaymentForm").submit();
}
</script>
<div class="content">
<s:form id="repaymentListForm" action="/main/repaymentList.action">
<s:hidden name="sumthisMonth" value="%{sumthisMonth}" />
<s:hidden name="sumNextMonth1" value="%{sumNextMonth1}" />
<s:hidden name="sumNextMonth2" value="%{sumNextMonth2}" />
<s:hidden name="sumNextMonth3" value="%{sumNextMonth3}" />
<font color="red"><s:actionerror/></font><font color="green"><s:actionmessage/></font>
<table class="report" borderColor="#f2f2f2" border="1">
	<tr>
		<td align="center" colspan="11" width="80%" class="green-bg1" colspan="9"><h1>还款提醒</h1></td>
	</tr>
	<tr align="center" height="40">
		<td colspan="11">还款日期:<s:textfield name="searchView.startDate" size="10" onClick="showcalendar(event, this);" readOnly="true" /> 至 <s:textfield name="searchView.endDate" size="10" onClick="showcalendar(event, this);" readOnly="true"/>
		<input type="button" value="查询" onClick="userSubmit('repaymentListForm')"/></td>
	</tr>
	<tr>
		<td align="right" colspan="11">
			本月合计：<report:bigDecimalFormat value = "${sumthisMonth}"/>  下1月合计：<report:bigDecimalFormat value = "${sumNextMonth1}"/>  下2月合计：<report:bigDecimalFormat value = "${sumNextMonth2}"/>  下3月合计：<report:bigDecimalFormat value = "${sumNextMonth3}"/>
		</td>
	</tr>
	<tr>
		<td align="right" colspan="11">
			<report:pages action="repaymentList.action" excel="repaymentList!excelOutput.action" page="${page}"/>
		</td>
	</tr>
	<tr align="center">
		<td></td>
		<td><input type="checkbox" class="chbx" checked value="0"/></td>
		<td><input type="checkbox" class="chbx" checked value="1"/></td>
		<td><input type="checkbox" class="chbx" checked value="2"/></td>
		<td><input type="checkbox" class="chbx" checked value="3"/></td>
		<td><input type="checkbox" class="chbx" checked value="4"/></td>
		<td><input type="checkbox" class="chbx" checked value="5"/></td>
		<td><input type="checkbox" class="chbx" checked value="6"/></td>
		<td><input type="checkbox" class="chbx" checked value="7"/></td>
		<td><input type="checkbox" class="chbx" checked value="8"/></td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<th width="5%">序号</th>
		<th><report:tableTitle title="账户名" action="repaymentList.action" sqlName="account.name" page="${page}" ctx="${ctx}"/></th>
		<th><report:tableTitle title="项目名" action="repaymentList.action" sqlName="account.project.name" page="${page}" ctx="${ctx}"/></th>
		<th><report:tableTitle title="媒体名" action="repaymentList.action" sqlName="account.channel.media.name" page="${page}" ctx="${ctx}"/></th>
		<th><report:tableTitle title="渠道名" action="repaymentList.action" sqlName="account.channel.name" page="${page}" ctx="${ctx}"/></th>
		<th>PM</th>
		<th><report:tableTitle title="垫付时间" action="repaymentList.action" sqlName="supplementDate" page="${page}" ctx="${ctx}"/></th>
		<th><report:tableTitle title="还款时间" action="repaymentList.action" sqlName="nextSupplementDate" page="${page}" ctx="${ctx}"/></th>
		<th><report:tableTitle title="还款金额" action="repaymentList.action" sqlName="repayAmount" page="${page}" ctx="${ctx}"/></th>
		<th>剩余天数</th>
		<th>操作</th>
	</tr>

	<s:iterator value="page.result" status="index">
	<s:if test="#index.odd == true">
		<tr class="table_style1">
	</s:if>
	<s:else>
		<tr class="table_style2">
	</s:else>
			<td>${index.index+1}</td>
			<td>${account.name}&nbsp;</td>
			<td>${account.project.name}&nbsp;</td>
			<td>${account.channel.name}&nbsp;</td>
			<td>${account.channel.media.name}&nbsp;</td>
			<td>${account.currentPms}&nbsp;</td>
			<td align="right"><report:dateFormat date = "${supplementDate}" />&nbsp;</td>
			<td align="right"><report:dateFormat date = "${nextSupplementDate}" />&nbsp;</td>
			<td align="right"><report:bigDecimalFormat value = "${repayAmount}" />&nbsp;</td>
			<td align="right">${remainDays}&nbsp;</td>
			<td align="center"><c:if test="${sessionScope.role['1.2.2'] != null}"><input type="button" onClick="addRepay(${id},${account.id})" value="还款" /></c:if></td>
		</tr>
	</s:iterator>
	<tr>
		<td colspan="7" ></td>
		<td align="right">合计</td>
		<td align="right"><report:bigDecimalFormat value = "${sum}" /></td>
	</tr>
	<tr>
		<td align="right" colspan="11">
			<report:pages action="repaymentList.action" excel="repaymentList!excelOutput.action" page="${page}"/>
		</td>
	</tr>
</table>
</s:form>
</div>
<div id="addRepayDiv" style="VISIBILITY:hidden;position: absolute;"> 
	<s:form id="addRepaymentForm" action="repaymentList!addRepayment.action">
		<table width="100%"> 
		    <s:hidden name="supplementHistoryId" id="supplementHistoryId" value="%{supplementHistoryId}" /> 
		    <s:hidden name="accountId" id="accountId" value="%{accountId}" />
			<tr align="center">
				<td>金额：</td>
				<td align="left"><s:textfield id="payAmount" size="10" name="supplementHistory.payAmountStr"/>RMB</td>	
			</tr>
			<tr>
				<td>日期：</td>
				<td align="left"><s:textfield id="supplementDate" size="10" name="supplementDate" onClick="showcalendar(event, this);" readonly="readOnly"/></td>	
			</tr>
			<tr>
			   <td align="center" colspan="2">
			     <c:if test="${sessionScope.role['1.2.2'] != null}"><input type="button" value="还款" onClick="checkAndSubmit();"/></c:if><input type="button" onmouseout="setTimeout('document.onclick=function(){}',1);" onmousemove="setTimeout('sexyTOG()',1);" value="返回">
			   </td>
			</tr>
		</table>
	</s:form>
</div>