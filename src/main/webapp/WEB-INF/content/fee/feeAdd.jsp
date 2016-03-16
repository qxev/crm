<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<script type="text/javascript" src="${ctx}/js/calendar.js"></script>
<script type="text/javascript"">
//达闻直冲
function initType1(){
	document.getElementById('td1').innerHTML = '付款金额：';
	document.getElementById('td2').innerHTML = '返点：';
	document.getElementById('payAmount').readOnly = false;
	document.getElementById('counterPoint').value = '0';
	document.getElementById('addDate').style.display='none';
	addTotal();
}
//渠道垫付
function initType2(){
	document.getElementById('td1').innerHTML = '渠道垫付：';
	document.getElementById('td2').innerHTML = '渠道返点：';
	document.getElementById('payAmount').readOnly = false;
	document.getElementById('payAmount').value = '0';
	document.getElementById('counterPoint').value = '0';
	document.getElementById('addDate').style.display='';
	addTotal();
}
//渠道反点
function initType3(){
	document.getElementById('td1').innerHTML = '付款金额：';
	document.getElementById('td2').innerHTML = '返点：';
	document.getElementById('payAmount').readOnly = true;
	document.getElementById('payAmount').value = '0';
	document.getElementById('counterPoint').value = '0';
	document.getElementById('addDate').style.display='none';
	addTotal();
}
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
	var supplementDate = document.getElementById("supplementDate").value;
	var nextSupplementDate = document.getElementById("nextSupplementDate").value;
	var radio1 = document.getElementById('repeatMode_1');
	var radio2 = document.getElementById('repeatMode_2');
	var payAmount = document.getElementById('payAmount').value;
	var counterPoint = document.getElementById('counterPoint').value;
	if (payAmount==''){
		alert("请输入付款金额");
		return;
	} else if (isNaN(Number(payAmount))){
		alert("请输入正确的付款金额");
		return;
	} else if (isNaN(Number(counterPoint))){
		alert("请输入正确的返点");
		return;
    } else if(supplementDate==''){
		alert("请输入充值日期");
		return;
    } else if(radio2.checked && (nextSupplementDate=='')){
		alert("请输入下次还款日期");
		return;
	} else if(radio1.checked && (payAmount=='0')){
		alert("冲值金额不能为0");
		return;
	}
	document.getElementById("addFeeForm").submit();
}
function changeAccount(){
	var url = "jsonAccount.action";
	var myAjax = new Ajax.Request(url,
	{
		method:'post',
		parameters:"projectId="+document.getElementById("projectId").value,
		onComplete:accountProcessResponse,
		asynchronous:true
	});
}
	 
function accountProcessResponse(request){
 	var res = JSON.parse(request.responseText);
	var target = document.getElementById("accountId");
	var size = target.length
	for (var i=0;i<size;i++){
		target.remove(0);
	}
 	for (var box in res)
 	{
 		for (var account in res[box]){
 			if (res[box][account].id!=null){
 				target.options.add(new Option(res[box][account].nameChannel,res[box][account].id)); 
	 		}
 		}
	}
}
</script>
<div class="content50">
<s:form id="addFeeForm" action="initFee!addFee.action">
<font color="red"><s:actionerror/></font><font color="green"><s:actionmessage/></font>
<table class="report" borderColor="#f2f2f2" border="1">
	<tr>
		<td align="center"  class="green-bg1"  colspan="2"><h1>新增充值记录</h1></td>
	</tr>
	<tr>
		<td>账户名：</td>
	    <td><s:select onChange="changeAccount()" id="projectId" list="projects" listKey="id" listValue="name" />
	    <s:select list="accounts" id="accountId" listKey="id" listValue="nameChannel" name="acId"/></td>
	</tr>
	<tr>
    	<td>充值日期：</td>
		<td><s:textfield name="supplementDate" id="supplementDate" onClick="showcalendar(event, this);" readonly="readOnly"/><br></td>
	</tr>
	<tr>
		<td id="td1">付款金额：</td>
		<td><s:textfield id="payAmount" name="supplementHistory.payAmountStr" onChange="addTotal()"/>RMB<br></td>
	</tr>
	<tr>
		<td id="td2">返点：</td>
		<td><s:textfield id="counterPoint" name="supplementHistory.counterPointStr" onChange="addTotal()"/>RMB<br></td>
	</tr>
	<tr>
		<td>充值总额：</td>
		<td><s:textfield id="totalAmount" name="supplementHistory.totalAmountStr" readOnly="true"/>RMB</td>
	</tr>
	<tr id="addDate" style="display:none;">
		<td>下次还款日期：</td>
		<td><s:textfield name="nextSupplementDate" id="nextSupplementDate" onClick="showcalendar(event, this);" readonly="readOnly"/></td><br>
	</tr>
	<tr>
    <td>&nbsp;</td>
    <td>
		<input type="radio" name="supplementHistory.type" value="1" id="repeatMode_1" checked="checked" onclick="initType1()"/>达闻直充
		<input type="radio" name="supplementHistory.type" value="2" id="repeatMode_2"  onclick="initType2()"/>渠道垫付
		<input type="radio" name="supplementHistory.type" value="3" id="repeatMode_3"  onclick="initType3()"/>渠道返点
	</td>
   </tr>   
   <tr>
        <td></td>
        <td><input type="button" onClick="checkAndSubmit()" value="增加"></td>
   </tr>
</s:form>  
</table>
</div>
