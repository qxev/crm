<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<script type="text/javascript" src="${ctx}/js/calendar.js"></script>
<script type="text/javascript">
function addTotal(){
	var payAmount = document.getElementById('payAmount').value;
	var counterPoint = document.getElementById('counterPoint').value;
	m1=Number(payAmount);
	if (isNaN(m1))
		m1 = 0;
    m2=Number(counterPoint);
    if (isNaN(m2))
    	m2 = 0;    
    document.getElementById('totalAmount').value = m1+m2;
}

function checkAndSubmit(){
	var payAmount = document.getElementById('payAmount').value;
	var counterPoint = document.getElementById('counterPoint').value;
	if (isNaN(Number(payAmount))){
		alert("请输入正确的付款金额");
		return;
	} else if (isNaN(Number(counterPoint))){
		alert("请输入正确的返点");
		return;
	}
	document.getElementById("editFee").submit();
}
</script>
<div class="content50">
<table borderColor="#f2f2f2" border="0" width="100%">
    <s:form action="editFee.action" id="editFee">
       <s:hidden name="supplementId" id="supplementId" value="%{supplementHistory.id}" />
       <s:hidden name="lastType" id="lastType" value="%{lastType}" />
       <s:hidden name="filter" value="%{filter}" />
	   <s:hidden name="pageNo" value="%{pageNo}" />
       <s:hidden name="lastType" value="%{lastType}" />
       <font color="red"><s:actionerror/></font>
       <tr><td align="center"  class="green-bg1"  colspan="2"><h1>修改冲值记录</h1></td></tr>
       <tr>
           <td>账户名</td>
           <td>${supplementHistory.account.name}</td>
       </tr>
       <tr>
           <td>渠道名</td>
           <td>${supplementHistory.account.channel.name}</td>
       </tr>
       <tr>
           <td>项目名</td>
           <td>${supplementHistory.account.project.name}</td>
       </tr>
       <tr>
           <td>冲值日期</td>
           <td><s:textfield name="supplementHistory.supplementDateStr" onClick="showcalendar(event, this);" readonly="readOnly"/></td>
       </tr>
       <s:if test="supplementHistory.type==2">
       <tr>
           <td>渠道垫付：</td>
           <td><s:textfield id="payAmount" name="supplementHistory.payAmountStr2" onChange="addTotal()"/>RMB</td>
       </tr>
       </s:if>
       <s:if test="supplementHistory.type!=2">
       <tr>
           <td>付款金额：</td>
           <td><s:textfield id="payAmount" name="supplementHistory.payAmountStr" onChange="addTotal()"/>RMB</td>
       </tr>
       </s:if>
       <tr>
           <td><s:if test="supplementHistory.type==2">渠道</s:if>返点：</td>
           <td><s:textfield id="counterPoint" name="supplementHistory.counterPointStr" onChange="addTotal()"/>RMB</td>
       </tr>
       <tr>
           <td>充值总额：</td>
           <td><s:textfield id="totalAmount" name="supplementHistory.totalAmountStr" readonly="readOnly"/>RMB</td>
       </tr>
       <s:if test="supplementHistory.nextSupplementDate != null">
       <tr>
           <td>下次还款日期：</td>
           <td><s:textfield name="supplementHistory.nextSupplementDateStr" onClick="showcalendar(event, this);" readonly="readOnly"/></td>
       </tr>
       </s:if>
       <tr>
          <td></td>
          <td>
             <input type="button" onClick="checkAndSubmit()" value="保存" />
             <input type="button" value="返回" onClick="document.location='totalAmountList.action'" />
             <input type="button" onClick="document.location='deleteFee.action?supplementId=${supplementHistory.id}&lastType=${lastType}&pageNo=${pageNo}&filter=${filter}'" value="删除" />
          </td>
       </tr>
    </s:form>
</table>
</div>
