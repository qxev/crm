<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<script type="text/javascript" src="${ctx}/js/calendar.js"></script>
<script type="text/javascript" src="${ctx}/js/popdiv.js"></script>
<script type="text/javascript">
function addPmAction(){
	var startDate = document.getElementById("pmStartDate").value;
	var endDate = document.getElementById("pmEndDate").value;
	var pms = getCheckboxValue('pm');
	if (pms == ''){
		alert("请至少选择一个SEM分析师");
		return;
	} else if(startDate==''){
		alert("请输入SEM分析师管理的开始时间");
		return;
	} else if (endDate < startDate && endDate!='') {
		alert("结束时间必须大于开始时间");
		return;
	} 
	document.getElementById("editPms").value=pms;
	addAction('addPm');
}
function addDiscountAction(){
	var startDate = document.getElementById("discountStartDate").value;
	var endDate = document.getElementById("discountEndDate").value;
	var darwinDiscount = document.getElementById('darwinDiscount').value;
	var projectDiscount = document.getElementById('projectDiscount').value;
	var bonusDiscount = document.getElementById('bonusDiscount').value;
	var m1=Number(darwinDiscount);
	var m2=Number(projectDiscount);
	var m3=Number(bonusDiscount);
	if (isNaN(m1)){
		alert("请输入正确的达闻折扣率");
		return;
	} else if (isNaN(m2)){
		alert("请输入正确的项目折扣率");
		return;
	} else if (isNaN(m3)){
		alert("请输入正确的奖励折扣率");
		return;
    } else if(startDate==''){
		alert("请输入折扣率的开始时间");
		return;
	} else if (endDate < startDate && endDate!='') {
		alert("结束时间必须大于开始时间");
		return;
	} 
	document.getElementById("editDarwinDiscount").value=darwinDiscount;
	document.getElementById("editProjectDiscount").value=projectDiscount;
	document.getElementById("editBonusDiscount").value=bonusDiscount;
	addAction('addDiscount');
}
function addAffDiscountAction(){
	var startDate = document.getElementById("affDiscountStartDate").value;
	var endDate = document.getElementById("affDiscountEndDate").value;
	var darwinAffDiscount = document.getElementById('darwinAffDiscount').value;
	var projectAffDiscount = document.getElementById('projectAffDiscount').value;
	var bonusAffDiscount = document.getElementById('bonusAffDiscount').value;
	var m1=Number(darwinAffDiscount);
	var m2=Number(projectAffDiscount);
	var m3=Number(bonusAffDiscount);
	if (isNaN(m1)){
		alert("请输入正确的达闻折扣率");
		return;
	} else if (isNaN(m2)){
		alert("请输入正确的项目折扣率");
		return;
	} else if (isNaN(m3)){
		alert("请输入正确的奖励折扣率");
		return;
    } else if(startDate==''){
		alert("请输入折扣率的开始时间");
		return;
	} else if (endDate < startDate && endDate!='') {
		alert("结束时间必须大于开始时间");
		return;
	} 
	document.getElementById("editDarwinDiscount").value=darwinAffDiscount;
	document.getElementById("editProjectDiscount").value=projectAffDiscount;
	document.getElementById("editBonusDiscount").value=bonusAffDiscount;
	addAction('addAffDiscount');
}
function addDiscountBackAction(){
	var startDate = document.getElementById("discountBackStartDate").value;
	var endDate = document.getElementById("discountBackEndDate").value;
	var discountBack = document.getElementById('discountBack').value;
	if (isNaN(Number(discountBack))){
		alert("请输入正确的折扣返还率");
		return;
    } else if(startDate==''){
		alert("请输入折扣返还率的开始时间");
		return;
	} else if (endDate < startDate && endDate!='') {
		alert("结束时间必须大于开始时间");
		return;
	} 
	addAction('addDiscountBack');
}
function addServiceFeeAction(){
	var startDate = document.getElementById("serviceFeeStartDate").value;
	var endDate = document.getElementById("serviceFeeEndDate").value;
	var serviceFee = document.getElementById('serviceFee').value;
	if (isNaN(Number(serviceFee))){
		alert("请输入正确的服务费");
		return;
    } else if(startDate==''){
		alert("请输入服务费的开始时间");
		return;
	} else if (endDate < startDate && endDate!='') {
		alert("结束时间必须大于开始时间");
		return;
	} 
	addAction('addServiceFee');
}
function addAffServiceFeeAction(){
	var startDate = document.getElementById("affServiceFeeStartDate").value;
	var endDate = document.getElementById("affServiceFeeEndDate").value;
	var affServiceFee = document.getElementById('affServiceFee').value;
	if (isNaN(Number(affServiceFee))){
		alert("请输入正确的服务费");
		return;
    } else if(startDate==''){
		alert("请输入服务费的开始时间");
		return;   
	} else if(endDate==''){
		alert("请输入服务费的结束时间");
		return;
	} else if (endDate < startDate && endDate!='') {
		alert("结束时间必须大于开始时间");
		return;
	} 
	addAction('addAffServiceFee');
}
function addServiceTypeAction(){
	var startDate = document.getElementById("serviceTypeStartDate").value;
	var endDate = document.getElementById("serviceTypeEndDate").value;
    if(startDate==''){
		alert("请输入服务费的开始时间");
		return;
	} else if (endDate < startDate && endDate!='') {
		alert("结束时间必须大于开始时间");
		return;
	} 
	addAction('addServiceType');
}
function addServiceFeeRateAction(){
	var startDate = document.getElementById("serviceFeeRateStartDate").value;
	var endDate = document.getElementById("serviceFeeRateEndDate").value;
	var serviceFeeRate = document.getElementById('serviceFeeRate').value;
	if (isNaN(Number(serviceFeeRate))){
		alert("请输入正确的服务费率");
		return;
    } else if(startDate==''){
		alert("请输入服务费率的开始时间");
		return;
	} else if (endDate < startDate && endDate!='') {
		alert("结束时间必须大于开始时间");
		return;
	} 
	addAction('addServiceFeeRate');
}
function addAffServiceFeeRateAction(){
	var startDate = document.getElementById("affServiceFeeRateStartDate").value;
	var endDate = document.getElementById("affServiceFeeRateEndDate").value;
	var affServiceFeeRate = document.getElementById('affServiceFeeRate').value;
	if (isNaN(Number(affServiceFeeRate))){
		alert("请输入正确的服务费率");
		return;
    } else if(startDate==''){
		alert("请输入服务费率的开始时间");
		return;
	} else if (endDate < startDate && endDate!='') {
		alert("结束时间必须大于开始时间");
		return;
	} 
	addAction('addAffServiceFeeRate');
}
function addChargePerson(projectId){
	document.getElementById("projectId").value=projectId;
	sexyBOX('添加负责人','addChargePersonDiv','700');
	return false;
}
function checkAndSubmit(){
	var darwinBalance = document.getElementById("darwinBalance").value;
	var clientBalance = document.getElementById("clientBalance").value;
	if (isNaN(Number(darwinBalance))){
		alert("请输入正确的达闻余额");
		return;
    } else if (isNaN(Number(clientBalance))){
		alert("请输入正确的客户余额");
		return;
	} else if ( darwinBalance=='') {
		document.getElementById("darwinBalance").value = '0';
	} else if ( clientBalance=='') {
		document.getElementById("clientBalance").value = '0';
	}
	addAction('editAccount');
}
function addAction(action){
	document.getElementById("userAction").value=action;
	document.getElementById("addAccountProperty").submit();
}
function editDiscountBackAction(discountBackId){
	var par1 = discountBackId+"discountBack";
	var par2 = discountBackId+"discountBackStartDate";
	var par3 = discountBackId+"discountBackEndDate";
	var discountBack = document.getElementById(par1).value;
	var discountBackStartDate = document.getElementById(par2).value;
	var discountBackEndDate = document.getElementById(par3).value;
	if (isNaN(Number(discountBack))){
		alert("请输入正确的折扣返还率");
		return;
    } else if(discountBackStartDate==''){
		alert("请输入折扣返还率的开始时间");
		return;
	} else if (discountBackEndDate < discountBackStartDate && discountBackEndDate!='') {
		alert("结束时间必须大于开始时间");
		return;
	} 
	document.getElementById("discountBackId").value=discountBackId;
	document.getElementById("discountBack").value=discountBack;
	document.getElementById("discountBackStartDate").value=discountBackStartDate;
	document.getElementById("discountBackEndDate").value=discountBackEndDate;
	document.getElementById("userAction").value='editDiscountBack';
	document.getElementById("addAccountProperty").submit();
}
function deleteDiscountBackAction(discountBackId){
	document.getElementById("discountBackId").value=discountBackId;
	document.getElementById("userAction").value='deleteDiscountBack';
	document.getElementById("addAccountProperty").submit();
}
function editServiceFeeAction(serviceFeeId){
	var par1 = serviceFeeId+"serviceFee";
	var par2 = serviceFeeId+"serviceFeeStartDate";
	var par3 = serviceFeeId+"serviceFeeEndDate";
	var serviceFee = document.getElementById(par1).value;
	var serviceFeeStartDate = document.getElementById(par2).value;
	var serviceFeeEndDate = document.getElementById(par3).value;
	if (isNaN(Number(serviceFee))){
		alert("请输入正确的服务费基数");
		return;
    } else if(serviceFeeStartDate==''){
		alert("请输入服务费基数的开始时间");
		return;
	} else if (serviceFeeEndDate < serviceFeeStartDate && serviceFeeEndDate!='') {
		alert("结束时间必须大于开始时间");
		return;
	} 
	document.getElementById("serviceFeeId").value=serviceFeeId;
	document.getElementById("serviceFee").value=serviceFee;
	document.getElementById("serviceFeeStartDate").value=serviceFeeStartDate;
	document.getElementById("serviceFeeEndDate").value=serviceFeeEndDate;
	document.getElementById("userAction").value='editServiceFee';
	document.getElementById("addAccountProperty").submit();
}
function editAffServiceFeeAction(serviceFeeId){
	var par1 = serviceFeeId+"affServiceFee";
	var par2 = serviceFeeId+"affServiceFeeStartDate";
	var par3 = serviceFeeId+"affServiceFeeEndDate";
	var affServiceFee = document.getElementById(par1).value;
	var affServiceFeeStartDate = document.getElementById(par2).value;
	var affServiceFeeEndDate = document.getElementById(par3).value;
	if (isNaN(Number(affServiceFee))){
		alert("请输入正确的服务费基数");
		return;
    } else if(affServiceFeeStartDate==''){
		alert("请输入短期服务费的开始时间");
		return;
	} else if(affServiceFeeEndDate==''){
		alert("请输入短期服务费的结束时间");
		return;
	} else if (affServiceFeeEndDate < affServiceFeeStartDate && affServiceFeeEndDate!='') {
		alert("结束时间必须大于开始时间");
		return;
	} 
	document.getElementById("serviceFeeId").value=serviceFeeId;
	document.getElementById("affServiceFee").value=affServiceFee;
	document.getElementById("affServiceFeeStartDate").value=affServiceFeeStartDate;
	document.getElementById("affServiceFeeEndDate").value=affServiceFeeEndDate;
	document.getElementById("userAction").value='editAffServiceFee';
	document.getElementById("addAccountProperty").submit();
}
function editServiceTypeAction(serviceFeeId){
	var par1 = serviceFeeId+"serviceType";
	var par2 = serviceFeeId+"serviceTypeStartDate";
	var par3 = serviceFeeId+"serviceTypeEndDate";
	var serviceType = document.getElementById(par1).value;
	var serviceTypeStartDate = document.getElementById(par2).value;
	var serviceTypeEndDate = document.getElementById(par3).value;
	if(serviceTypeStartDate==''){
		alert("请输入开始时间");
		return;
	} else if (serviceTypeEndDate < serviceTypeStartDate && serviceTypeEndDate!='') {
		alert("结束时间必须大于开始时间");
		return;
	} 
	document.getElementById("serviceTypeId").value=serviceFeeId;
	var tt = 'input[type=\"radio\"][name=\"'+serviceFeeId+'serviceType\"]';
	var temp = $$(tt);
	for (i=0;i<temp.length;i++){
    	if(temp[i].checked){
    		document.getElementById("st").value = i+1;
    		break;
    	}
  	}
	document.getElementById("serviceTypeStartDate").value=serviceTypeStartDate;
	document.getElementById("serviceTypeEndDate").value=serviceTypeEndDate;
	document.getElementById("userAction").value='editServiceType';
	document.getElementById("addAccountProperty").submit();
}
function deleteServiceFeeAction(serviceFeeId){
	document.getElementById("serviceFeeId").value=serviceFeeId;
	document.getElementById("userAction").value='deleteServiceFee';
	document.getElementById("addAccountProperty").submit();
}
function deleteAffServiceFeeAction(serviceFeeId){
	document.getElementById("serviceFeeId").value=serviceFeeId;
	document.getElementById("userAction").value='deleteAffServiceFee';
	document.getElementById("addAccountProperty").submit();
}
function editServiceFeeRateAction(serviceFeeRateId){
	var par1 = serviceFeeRateId+"serviceFeeRate";
	var par2 = serviceFeeRateId+"serviceFeeRateStartDate";
	var par3 = serviceFeeRateId+"serviceFeeRateEndDate";
	var serviceFeeRate = document.getElementById(par1).value;
	var serviceFeeRateStartDate = document.getElementById(par2).value;
	var serviceFeeRateEndDate = document.getElementById(par3).value;
	if (isNaN(Number(serviceFeeRate))){
		alert("请输入正确的服务费基数");
		return;
    } else if(serviceFeeRateStartDate==''){
		alert("请输入服务费基数的开始时间");
		return;
	} else if (serviceFeeRateEndDate < serviceFeeRateStartDate && serviceFeeRateEndDate!='') {
		alert("结束时间必须大于开始时间");
		return;
	} 
	document.getElementById("serviceFeeRateId").value=serviceFeeRateId;
	document.getElementById("serviceFeeRate").value=serviceFeeRate;
	document.getElementById("serviceFeeRateStartDate").value=serviceFeeRateStartDate;
	document.getElementById("serviceFeeRateEndDate").value=serviceFeeRateEndDate;
	document.getElementById("userAction").value='editServiceFeeRate';
	document.getElementById("addAccountProperty").submit();
}
function editAffServiceFeeRateAction(serviceFeeRateId){
	var par1 = serviceFeeRateId+"affServiceFeeRate";
	var par2 = serviceFeeRateId+"affServiceFeeRateStartDate";
	var par3 = serviceFeeRateId+"affServiceFeeRateEndDate";
	var affServiceFeeRate = document.getElementById(par1).value;
	var affServiceFeeRateStartDate = document.getElementById(par2).value;
	var affServiceFeeRateEndDate = document.getElementById(par3).value;
	if (isNaN(Number(affServiceFeeRate))){
		alert("请输入正确的服务费基数");
		return;
    } else if(affServiceFeeRateStartDate==''){
		alert("请输入服务费基数的开始时间");
		return;
	} else if (affServiceFeeRateEndDate < affServiceFeeRateStartDate && affServiceFeeRateEndDate!='') {
		alert("结束时间必须大于开始时间");
		return;
	} 
	document.getElementById("serviceFeeRateId").value=serviceFeeRateId;
	document.getElementById("affServiceFeeRate").value=affServiceFeeRate;
	document.getElementById("affServiceFeeRateStartDate").value=affServiceFeeRateStartDate;
	document.getElementById("affServiceFeeRateEndDate").value=affServiceFeeRateEndDate;
	document.getElementById("userAction").value='editAffServiceFeeRate';
	document.getElementById("addAccountProperty").submit();
}
function deleteServiceFeeRateAction(serviceFeeRateId){
	document.getElementById("serviceFeeRateId").value=serviceFeeRateId;
	document.getElementById("userAction").value='deleteServiceFeeRate';
	document.getElementById("addAccountProperty").submit();
}
function deleteAffServiceFeeRateAction(serviceFeeRateId){
	document.getElementById("serviceFeeRateId").value=serviceFeeRateId;
	document.getElementById("userAction").value='deleteAffServiceFeeRate';
	document.getElementById("addAccountProperty").submit();
}
function editDiscountAction(discountId){
	var par1 = discountId+"darwinDiscount";
	var par2 = discountId+"projectDiscount";
	var par3 = discountId+"bonusDiscount";
	var par4 = discountId+"discountStartDate";
	var par5 = discountId+"discountEndDate";
	var darwinDiscount = document.getElementById(par1).value;
	var projectDiscount = document.getElementById(par2).value;
	var bonusDiscount = document.getElementById(par3).value;
	var discountStartDate = document.getElementById(par4).value;
	var discountEndDate = document.getElementById(par5).value;
	if (isNaN(Number(darwinDiscount))){
		alert("请输入正确的达闻折扣率");
		return;
	} else if (isNaN(Number(projectDiscount))){
		alert("请输入正确的项目折扣率");
		return;
	} else if (isNaN(Number(bonusDiscount))){
		alert("请输入正确的奖励折扣率");
		return;
    } else if(discountStartDate==''){
		alert("请输入折扣率的开始时间");
		return;
	} else if (discountEndDate < discountStartDate && discountEndDate!='') {
		alert("结束时间必须大于开始时间");
		return;
	} 
	document.getElementById("discountId").value=discountId;
	document.getElementById("editDarwinDiscount").value=darwinDiscount;
	document.getElementById("editProjectDiscount").value=projectDiscount;
	document.getElementById("editBonusDiscount").value=bonusDiscount;
	document.getElementById("discountStartDate").value=discountStartDate;
	document.getElementById("discountEndDate").value=discountEndDate;
	document.getElementById("userAction").value='editDiscount';
	document.getElementById("addAccountProperty").submit();
}
function editAffDiscountAction(discountId){
	var par1 = discountId+"darwinAffDiscount";
	var par2 = discountId+"projectAffDiscount";
	var par3 = discountId+"bonusAffDiscount";
	var par4 = discountId+"AffDiscountStartDate";
	var par5 = discountId+"AffDiscountEndDate";
	var darwinDiscount = document.getElementById(par1).value;
	var projectDiscount = document.getElementById(par2).value;
	var bonusDiscount = document.getElementById(par3).value;
	var discountStartDate = document.getElementById(par4).value;
	var discountEndDate = document.getElementById(par5).value;
	if (isNaN(Number(darwinDiscount))){
		alert("请输入正确的达闻折扣率");
		return;
	} else if (isNaN(Number(projectDiscount))){
		alert("请输入正确的项目折扣率");
		return;
	} else if (isNaN(Number(bonusDiscount))){
		alert("请输入正确的奖励折扣率");
		return;
    } else if(discountStartDate==''){
		alert("请输入折扣率的开始时间");
		return;
	} else if (discountEndDate < discountStartDate && discountEndDate!='') {
		alert("结束时间必须大于开始时间");
		return;
	} 
	document.getElementById("discountId").value=discountId;
	document.getElementById("editDarwinDiscount").value=darwinDiscount;
	document.getElementById("editProjectDiscount").value=projectDiscount;
	document.getElementById("editBonusDiscount").value=bonusDiscount;
	document.getElementById("discountStartDate").value=discountStartDate;
	document.getElementById("discountEndDate").value=discountEndDate;
	document.getElementById("userAction").value='editAffDiscount';
	document.getElementById("addAccountProperty").submit();
}
function deleteDiscountAction(discountId){
	document.getElementById("discountId").value=discountId;
	document.getElementById("userAction").value='deleteDiscount';
	document.getElementById("addAccountProperty").submit();
}
function deleteServiceTypeAction(serviceTypeId){
	document.getElementById("serviceTypeId").value=serviceTypeId;
	document.getElementById("userAction").value='deleteServiceType';
	document.getElementById("addAccountProperty").submit();
}
function getCheckboxValue(pmHistoryId){
	var par1 = pmHistoryId+"tr";
	var checkboxs = document.getElementById(par1).getElementsByTagName("input");
	var pmStrs = "";
	var hasPm = false
	for(var i=0;i<checkboxs.length;i++){
		if(checkboxs[i].checked){
			if (!hasPm){
				pmStrs = checkboxs[i].value;
				hasPm = true;
			} else {
				pmStrs = pmStrs + ','+checkboxs[i].value;
			}
		}
	}
	return pmStrs;
}
function editPmHistoryAction(pmHistoryId){
	var pmStrs = getCheckboxValue(pmHistoryId);
	document.getElementById("editPms").value=pmStrs;
	var par2 = pmHistoryId+"pmHistoryStartDate";
	var par3 = pmHistoryId+"pmHistoryEndDate";
	var startDate = document.getElementById(par2).value;
	var endDate = document.getElementById(par3).value;
	if (pmStrs == ''){
		alert("请至少选择一个SEM分析师");
		return;
	} else if(startDate==''){
		alert("请输入SEM分析师管理的开始时间");
		return;
	} else if (endDate < startDate && endDate!='') {
		alert("结束时间必须大于开始时间");
		return;
	}
	document.getElementById("pmStartDate").value=document.getElementById(par2).value;
	document.getElementById("pmEndDate").value=document.getElementById(par3).value;
	document.getElementById("pmHistoryId").value=pmHistoryId;
	document.getElementById("userAction").value='editPm';
	document.getElementById("addAccountProperty").submit();
}
function deletePmHistoryAction(pmHistoryId){
	document.getElementById("pmHistoryId").value=pmHistoryId;
	document.getElementById("userAction").value='deletePm';
	document.getElementById("addAccountProperty").submit();
}
function addTotal(){
	var darwinDiscount = document.getElementById('darwinDiscount').value;
	var projectDiscount = document.getElementById('projectDiscount').value;
	var bonusDiscount = document.getElementById('bonusDiscount').value;
	var m1=Number(darwinDiscount);
	if (isNaN(m1))
		m1 = 0;
    var m2=Number(projectDiscount);
    if (isNaN(m2))
    	m2 = 0;    
   	var m3=Number(bonusDiscount);
    if (isNaN(m3))
    	m3 = 0;    
    document.getElementById('totalDiscount').value = m1+m2+m3;
}
function addAffTotal(){
	var darwinAffDiscount = document.getElementById('darwinAffDiscount').value;
	var projectAffDiscount = document.getElementById('projectAffDiscount').value;
	var bonusAffDiscount = document.getElementById('bonusAffDiscount').value;
	var m1=Number(darwinAffDiscount);
	if (isNaN(m1))
		m1 = 0;
    var m2=Number(projectAffDiscount);
    if (isNaN(m2))
    	m2 = 0;    
   	var m3=Number(bonusAffDiscount);
    if (isNaN(m3))
    	m3 = 0;    
    document.getElementById('totalAffDiscount').value = m1+m2+m3;
}
function addBalance(){
	var darwinBalance = document.getElementById('darwinBalance').value;
	var clientBalance = document.getElementById('clientBalance').value;
	var m1=Number(darwinBalance);
	if (isNaN(m1))
		m1 = 0;
    var m2=Number(clientBalance);
    if (isNaN(m2))
    	m2 = 0;    
    document.getElementById('totalBalance').value = m1+m2;
}
function displayServiceFeeTd(){
	document.getElementById("serviceFeeTd").style.display="";
	document.getElementById("shortServiceFeeTd").style.display="none";
	document.getElementById("serviceFeeRateTd").style.display="none";
}
function displayServiceFeeRateTd(){
	document.getElementById("serviceFeeTd").style.display="none";
	document.getElementById("shortServiceFeeTd").style.display="none";
	document.getElementById("serviceFeeRateTd").style.display="";
}
function displayShortServiceFeeTd(){
	document.getElementById("serviceFeeTd").style.display="none";
	document.getElementById("shortServiceFeeTd").style.display="";
	document.getElementById("serviceFeeRateTd").style.display="none";
}

function displayclientStyle(){
	document.getElementById('display1').style.display='none';
	document.getElementById('display2').style.display='none';
	document.getElementById('display3').style.display='none';
	document.getElementById('display4').style.display='none';
}
function displayDarwinStyle(){
	document.getElementById('display1').style.display='';
	document.getElementById('display2').style.display='';
	document.getElementById('display3').style.display='';
	document.getElementById('display4').style.display='';
}
function supplymentTypeChange(){
	if(document.getElementById('supplymentType').value==1){
		displayDarwinStyle();
	}
	if(document.getElementById('supplymentType').value==2){
		displayclientStyle();
	}
}
window.onload=function(){
	document.body.innerHTML="<div id=\"sexyBG\"></div><div id=\"sexyBOX\"></div>"+document.body.innerHTML;
}
</script>
<div class="content50">
<s:form id="addAccountProperty" action="editAccount.action">
	<s:hidden name="accountView.userAction" id="userAction"/>
	<s:hidden name="accountId" id="accountId" value="%{accountId}" />
	<s:hidden name="accountView.pmHistoryId" id="pmHistoryId" />
	<s:hidden name="accountView.discountId" id="discountId" />
	<s:hidden name="accountView.discountBackId" id="discountBackId" />
	<s:hidden name="accountView.serviceFeeId" id="serviceFeeId" />
	<s:hidden name="accountView.serviceTypeId" id="serviceTypeId" />
	<s:hidden name="accountView.st" id="st" />
	<s:hidden name="accountView.serviceFeeRateId" id="serviceFeeRateId" />
	<s:hidden name="accountView.editDarwinDiscount" id="editDarwinDiscount" />
	<s:hidden name="accountView.editProjectDiscount" id="editProjectDiscount" />
	<s:hidden name="accountView.editBonusDiscount" id="editBonusDiscount" />
	<s:hidden name="accountView.editPms" id="editPms" />
	<s:hidden name="filter" value="%{filter}" />
	<s:hidden name="pageNo" value="%{pageNo}" />
	<font color="red"><s:actionerror/></font>
	<font color="green"><s:actionmessage/></font>
	<table class="report" borderColor="#f2f2f2" border="1" width="100%">
		<tr>
			<td align="center" width="80%" colspan="8" class="green-bg1"><h1>编辑账户</h1></td>
		</tr>
		<tr>
			<td width="20%" align="right">帐户名：</td>
			<td width="80%">${account.name}(${account.channel.name})(${account.channel.media.name})</td>
		</tr>
		<tr id="pmtr">
			<td align="right">SEM分析师：</td>
			<td>
				<font color="red">${account.currentPms}</font><BR>
				<s:checkboxlist list="account.project.pms" listKey="id" listValue="name" name="accountView.pms" value="accountView.pmDisplay"/>
				管理开始时间<s:textfield name="accountView.pmStartDate" size="10" onClick="showcalendar(event, this);" readonly="true" id="pmStartDate"/>
				管理结束时间<s:textfield name="accountView.pmEndDate" size="10" onClick="showcalendar(event, this);" readonly="true" id="pmEndDate"/>
				<input type="button" onClick="addPmAction('addPm');" value="新增"/><input type="button" onClick="sexyBOX('pm历史','pmHistoryDiv','700');" value="历史"/>
			</td>
		</tr>
		<tr>
			<td align="right">充值类型：</td>
			<td>
			<font color="red"><c:if test="${account.supplementType == '1'}">达闻充值</c:if><c:if test="${account.supplementType == '2'}">客户充值</c:if></font>
			<input type="radio" id="serviceType" name="accountView.supplyTypeId" value="1" <c:if test="${account.supplementType == '1'}">checked</c:if>/>达闻充值
			<input type="radio" id="serviceType" name="accountView.supplyTypeId" value="2" <c:if test="${account.supplementType == '2'}">checked</c:if>/>客户充值<br/>
			开始时间<s:textfield name="accountView.serviceTypeStartDate" size="10" onClick="showcalendar(event, this);" readonly="true" id="serviceTypeStartDate"/>
			结束时间<s:textfield name="accountView.serviceTypeEndDate" size="10" onClick="showcalendar(event, this);" readonly="true" id="serviceTypeEndDate"/>
			<input type="button" onClick="addServiceTypeAction();" value="新增"/><input type="button" onClick="sexyBOX('冲值类型历史','serviceTypeHistoryDiv','700');" value="历史"/>
			</td>
		</tr>
		<tr id="display1">
			<td align="right">折扣率：</td>
			<td>
			<font color="red"><report:bigDecimalFormatNotRMB value = "${account.currentDiscount}" />%</font><BR>
			<input type="text" readOnly size="6" id="totalDiscount">%=达闻折扣率<s:textfield name="accountView.darwinDiscount" size="6" id="darwinDiscount" onChange="addTotal()"/>%+项目折扣率<s:textfield name="accountView.projectDiscount" size="6" id="projectDiscount" onChange="addTotal()"/>%+奖励折扣率<s:textfield name="accountView.bonusDiscount" size="6" id="bonusDiscount" onChange="addTotal()"/>%<br>
			开始时间<s:textfield name="accountView.discountStartDate" size="10" onClick="showcalendar(event, this);" readonly="true" id="discountStartDate"/>
			结束时间<s:textfield name="accountView.discountEndDate" size="10" onClick="showcalendar(event, this);" readonly="true" id="discountEndDate"/>
			<input type="button" onClick="addDiscountAction()" value="新增"/><input type="button" onClick="sexyBOX('折扣率历史','discountHistoryDiv','700');" value="历史"/>
			</td>
		</tr>
		<s:if test="account.channel.media.id==1">
		<tr id="display4">
			<td align="right">网盟折扣率：</td>
			<td>
			<font color="red"><report:bigDecimalFormatNotRMB value = "${account.currentAffDiscount}" />%</font><BR>
			<input type="text" readOnly size="6" id="totalAffDiscount">%=达闻折扣率<s:textfield name="accountView.darwinAffDiscount" size="6" id="darwinAffDiscount" onChange="addAffTotal()"/>%+项目折扣率<s:textfield name="accountView.projectAffDiscount" size="6" id="projectAffDiscount" onChange="addAffTotal()"/>%+奖励折扣率<s:textfield name="accountView.bonusAffDiscount" size="6" id="bonusAffDiscount" onChange="addAffTotal()"/>%<br>
			开始时间<s:textfield name="accountView.affDiscountStartDate" size="10" onClick="showcalendar(event, this);" readonly="true" id="affDiscountStartDate"/>
			结束时间<s:textfield name="accountView.affDiscountEndDate" size="10" onClick="showcalendar(event, this);" readonly="true" id="affDiscountEndDate"/>
			<input type="button" onClick="addAffDiscountAction()" value="新增"/><input type="button" onClick="sexyBOX('网盟折扣率历史','affDiscountHistoryDiv','700');" value="历史"/>
			</td>
		</tr>
		</s:if>
		<tr id="display2">
			<td align="right">折扣返还率：</td>
			<td>
			<font color="red"><report:bigDecimalFormatNotRMB value = "${account.currentDiscountBack}" />%</font><BR>
			<s:textfield name="accountView.discountBack" size="6" id="discountBack" />%  
			开始时间<s:textfield name="accountView.discountBackStartDate" size="10" onClick="showcalendar(event, this);" readonly="true" id="discountBackStartDate" />
			结束时间<s:textfield name="accountView.discountBackEndDate" size="10" onClick="showcalendar(event, this);" readonly="true" id="discountBackEndDate"/>
			<input type="button" onClick="addDiscountBackAction()" value="新增"/>
			<input type="button" onClick="sexyBOX('折扣返回率历史','discountBackHistoryDiv','700');" value="历史"/>
			</td>
		</tr>
		<tr>
			<td align="right"><input type="radio" name="accountView.serviceFeeType" value="1" <c:if test="${account.serviceType == '1'}">checked</c:if> onClick="displayServiceFeeTd()"/>搜索服务费：</td>
			<td id="serviceFeeTd">
			<font color="red"><report:bigDecimalFormat value = "${account.currentServiceFee}" /></font><BR>
			<s:textfield name="accountView.serviceFee" size="6" id="serviceFee" /> RMB 
			开始时间<s:textfield name="accountView.serviceFeeStartDate" size="10" onClick="showcalendar(event, this);" readonly="true" id="serviceFeeStartDate"/>
			结束时间<s:textfield name="accountView.serviceFeeEndDate" size="10" onClick="showcalendar(event, this);" readonly="true" id="serviceFeeEndDate"/>
			<input type="button" onClick="addServiceFeeAction()" value="新增"/>
			<input type="button" onClick="sexyBOX('搜索服务费历史','serviceFeeHistoryDiv','700');" value="历史"/>
			</td>
		</tr>
		<tr>
			<td align="right"><input type="radio" name="accountView.serviceFeeType" value="3" <c:if test="${account.serviceType == '3'}">checked</c:if> onClick="displayShortServiceFeeTd()"/>短期服务费：</td>
			<td id="shortServiceFeeTd">
			<font color="red"><report:bigDecimalFormat value = "${account.currentServiceFee}" /></font><BR>
			<s:textfield name="accountView.affServiceFee" size="6" id="affServiceFee" /> RMB 
			开始时间<s:textfield name="accountView.affServiceFeeStartDate" size="10" onClick="showcalendar(event, this);" readonly="true" id="affServiceFeeStartDate"/>
			结束时间<s:textfield name="accountView.affServiceFeeEndDate" size="10" onClick="showcalendar(event, this);" readonly="true" id="affServiceFeeEndDate"/>
			<input type="button" onClick="addAffServiceFeeAction()" value="新增"/>
			<input type="button" onClick="sexyBOX('短期服务费','affServiceFeeHistoryDiv','700');" value="历史"/>
			</td>
		</tr>
		<tr>
			<td align="right"><input type="radio" name="accountView.serviceFeeType" value="2" <c:if test="${account.serviceType == '2'}">checked</c:if> onClick="displayServiceFeeRateTd()"/>搜索服务费率：</td>
			<td id="serviceFeeRateTd">
			<font color="red"><report:bigDecimalFormatNotRMB value = "${account.currentServiceFeeRate}" />%</font><BR>
			<s:textfield name="accountView.serviceFeeRate" size="6" id="serviceFeeRate" />%
			开始时间<s:textfield name="accountView.serviceFeeRateStartDate" size="10" onClick="showcalendar(event, this);" readonly="readOnly" id="serviceFeeRateStartDate"/>
			结束时间<s:textfield name="accountView.serviceFeeRateEndDate" size="10" onClick="showcalendar(event, this);" readonly="readOnly" id="serviceFeeRateEndDate"/>
			<input type="button" onClick="addServiceFeeRateAction()" value="新增"/>
			<input type="button" onClick="sexyBOX('搜索服务费率历史','serviceFeeRateHistoryDiv','700');" value="历史"/>
			</td>
		</tr>
<!--
		<tr>
			<td align="right">网盟服务费率：</td>
			<td>
			<font color="red"><report:bigDecimalFormatNotRMB value = "${account.currentAffServiceFeeRate}" />%</font><BR>
			<s:textfield name="accountView.affServiceFeeRate" size="6" id="affServiceFeeRate" />%
			开始时间<s:textfield name="accountView.affServiceFeeRateStartDate" size="10" onClick="showcalendar(event, this);" readonly="readOnly" id="affServiceFeeRateStartDate"/>
			结束时间<s:textfield name="accountView.affServiceFeeRateEndDate" size="10" onClick="showcalendar(event, this);" readonly="readOnly" id="affServiceFeeRateEndDate"/>
			<input type="button" onClick="addAffServiceFeeRateAction()" value="新增"/>
			<input type="button" onClick="sexyBOX('网盟服务费率历史','affServiceFeeRateHistoryDiv','700');" value="历史"/>
			</td>
		</tr>
-->
		<tr id="display3">
			<td align="right">帐户初始余额：</td>
			<td><input type="text" name="accountView.totalBalance" size="6" id="totalBalance" value="${account.totalBalance}">=达闻<input type="text" name="accountView.darwinBalance" size="6" id="darwinBalance" onChange="addBalance()" value="${account.darwinBalance}"/>+客户<input type="text" name="accountView.clientBalance" size="6" id="clientBalance" onChange="addBalance()" value="${account.clientBalance}"/></td>
		</tr>
		<tr>
			<td align="right">货币选择：</td>
			<td><s:select list="exchanges" name="exchangeId" listKey="id" listValue="country"/></td>	
		</tr>
		<tr>
			<td align="right">预警提醒：</td>
			<td><input type="radio" name="accountView.alert" value="1" <c:if test="${account.alert == '1'}">checked</c:if>/>是<input type="radio" name="accountView.alert" value="0" <c:if test="${account.alert == '0'}">checked</c:if>/>否	</td>	
		</tr>
		<tr>
			<td align="right">状态：</td>
			<td><input type="radio" name="accountView.status" value="1" <c:if test="${account.status == '1'}">checked</c:if>/>有效<input type="radio" name="accountView.status" value="2" <c:if test="${account.status == '2'}">checked</c:if>/>无效</td>
		</tr>
		<tr>
			<td align="right">每日抓取线上数据：</td>
			<td><input type="radio" name="accountView.getData" value="1" <c:if test="${account.getData == '1'}">checked</c:if>/>是<input type="radio" name="accountView.getData" value="2" <c:if test="${account.getData == '2'}">checked</c:if>/>否</td>
		</tr>
		<tr align="center">
			<td colspan="2"><input type="button" onClick="checkAndSubmit()" value="保存"/><input type="button" onClick="document.location='accountList.action?page.pageNo=${pageNo}&page.filter=${filter}'" value="返回"></td>
		</tr>	
	</table>
</div>
<div id="pmHistoryDiv" style="VISIBILITY:hidden;position: absolute;">
<table class="report" borderColor="#f2f2f2" border="1">
	<tr>
		<td>PM</td><td>日期</td><td>操作</td>
	</tr>
	<s:iterator value="account.pmHistorys">
	<tr>
		<td id="${id}tr"><s:checkboxlist list="account.project.pms" listKey="id" listValue="name" name="accountView.pms" value="pmDisplay"/></td>
		<td>开始日期<s:textfield id="%{id}pmHistoryStartDate" name="startDate" onClick="showcalendar(event, this);" readonly="readOnly"/>
		结束日期<s:textfield id="%{id}pmHistoryEndDate" name="endDateNotNull" onClick="showcalendar(event, this);" readonly="readOnly"/></td>
		<td><input type="button" onClick="editPmHistoryAction(${id})" value="编辑"/><input type="button" onClick="deletePmHistoryAction(${id})" value="删除"/></td>
	</tr>
	</s:iterator>
	<tr align="center">
		<td colspan="3">
			<input type="button" onmouseout="setTimeout('document.onclick=function(){}',1);" onmousemove="setTimeout('sexyTOG()',1);" value="返回">
		</td>
	</tr>
</table>
</div>	
<div id="discountHistoryDiv" style="VISIBILITY:hidden;position: absolute;">
	<table class="report" borderColor="#f2f2f2" border="1">
		<tr>
			<td>达闻折扣率</td><td>项目折扣率</td><td>奖金折扣率</td><td>开始时间</td><td>结束时间</td><td>操作</td>
		</tr>
		<s:iterator value="account.searchDiscounts">
		<tr>
			<td><s:textfield id="%{id}darwinDiscount" name="darwinDiscount" size="5"/></td>
			<td><s:textfield id="%{id}projectDiscount" name="projectDiscount" size="5"/></td>
			<td><s:textfield id="%{id}bonusDiscount" name="bonusDiscount" size="5"/></td>
			<td><s:textfield id="%{id}discountStartDate" name="startDate" onClick="showcalendar(event, this);" readonly="readOnly"/></td>
			<td><s:textfield id="%{id}discountEndDate" name="endDateNotNull" onClick="showcalendar(event, this);" readonly="readOnly"/></td>
			<td><input type="button" onClick="editDiscountAction(${id})" value="编辑"/><input type="button" onClick="deleteDiscountAction(${id})" value="删除"/></td>
		</tr>
		</s:iterator>
		<tr align="center">
		<td colspan="6">
			<input type="button" onmouseout="setTimeout('document.onclick=function(){}',1);" onmousemove="setTimeout('sexyTOG()',1);" value="返回">
		</td>
	</tr>
	</table>
</div>
<div id="affDiscountHistoryDiv" style="VISIBILITY:hidden;position: absolute;">
	<table class="report" borderColor="#f2f2f2" border="1">
		<tr>
			<td>达闻折扣率</td><td>项目折扣率</td><td>奖金折扣率</td><td>开始时间</td><td>结束时间</td><td>操作</td>
		</tr>
		<s:iterator value="account.AffDiscounts">
		<tr>
			<td><s:textfield id="%{id}darwinAffDiscount" name="darwinDiscount" size="5"/></td>
			<td><s:textfield id="%{id}projectAffDiscount" name="projectDiscount" size="5"/></td>
			<td><s:textfield id="%{id}bonusAffDiscount" name="bonusDiscount" size="5"/></td>
			<td><s:textfield id="%{id}AffDiscountStartDate" name="startDate" onClick="showcalendar(event, this);" readonly="readOnly"/></td>
			<td><s:textfield id="%{id}AffDiscountEndDate" name="endDateNotNull" onClick="showcalendar(event, this);" readonly="readOnly"/></td>
			<td><input type="button" onClick="editAffDiscountAction(${id})" value="编辑"/><input type="button" onClick="deleteDiscountAction(${id})" value="删除"/></td>
		</tr>
		</s:iterator>
		<tr align="center">
		<td colspan="6">
			<input type="button" onmouseout="setTimeout('document.onclick=function(){}',1);" onmousemove="setTimeout('sexyTOG()',1);" value="返回">
		</td>
	</tr>
	</table>
</div>
<div id="discountBackHistoryDiv" style="VISIBILITY:hidden;position: absolute;">
	<table class="report" borderColor="#f2f2f2" border="1">
		<tr>
			<td>折扣返还率</td><td>日期</td><td>操作</td>
		</tr>
		<s:iterator value="account.discountBacks">
		<tr>
			<td><s:textfield id="%{id}discountBack" name="value" /></td>
			<td>开始日期<s:textfield id="%{id}discountBackStartDate" name="startDate" onClick="showcalendar(event, this);" readonly="readOnly"/>
			结束日期<s:textfield id="%{id}discountBackEndDate" name="endDateNotNull" onClick="showcalendar(event, this);" readonly="readOnly"/></td>
			<td><input type="button" onClick="editDiscountBackAction(${id})" value="编辑"/><input type="button" onClick="deleteDiscountBackAction(${id})" value="删除"/></td>
		</tr>
		</s:iterator>
		<tr align="center">
			<td colspan="3">
				<input type="button" onmouseout="setTimeout('document.onclick=function(){}',1);" onmousemove="setTimeout('sexyTOG()',1);" value="返回">
			</td>
		</tr>
	</table>
</div>
<div id="serviceFeeHistoryDiv" style="VISIBILITY:hidden;position: absolute;">
	<table class="report" borderColor="#f2f2f2" border="1">
		<tr>
			<td>服务费(RMB)</td><td>日期</td><td>操作</td>
		</tr>
		<s:iterator value="account.searchServiceFees">
		<tr>
			<td><s:textfield id="%{id}serviceFee" name="value" /></td>
			<td>开始日期<s:textfield id="%{id}serviceFeeStartDate" name="startDate" onClick="showcalendar(event, this);" readonly="readOnly"/>
			结束日期<s:textfield id="%{id}serviceFeeEndDate" name="endDateNotNull" onClick="showcalendar(event, this);" readonly="readOnly"/></td>
			<td><input type="button" onClick="editServiceFeeAction(${id})" value="编辑"/><input type="button" onClick="deleteServiceFeeAction(${id})" value="删除"/></td>
		</tr>
		</s:iterator>
		<tr align="center">
			<td colspan="3">
				<input type="button" onmouseout="setTimeout('document.onclick=function(){}',1);" onmousemove="setTimeout('sexyTOG()',1);" value="返回">
			</td>
		</tr>
	</table>
</div>
<div id="affServiceFeeHistoryDiv" style="VISIBILITY:hidden;position: absolute;">
	<table class="report" borderColor="#f2f2f2" border="1">
		<tr>
			<td>短期服务费(RMB)</td><td>日期</td><td>操作</td>
		</tr>
		<s:iterator value="account.affServiceFees">
		<tr>
			<td><s:textfield id="%{id}affServiceFee" name="value" /></td>
			<td>开始日期<s:textfield id="%{id}affServiceFeeStartDate" name="startDate" onClick="showcalendar(event, this);" readonly="readOnly"/>
			结束日期<s:textfield id="%{id}affServiceFeeEndDate" name="endDateNotNull" onClick="showcalendar(event, this);" readonly="readOnly"/></td>
			<td><input type="button" onClick="editAffServiceFeeAction(${id})" value="编辑"/><input type="button" onClick="deleteAffServiceFeeAction(${id})" value="删除"/></td>
		</tr>
		</s:iterator>
		<tr align="center">
			<td colspan="3">
				<input type="button" onmouseout="setTimeout('document.onclick=function(){}',1);" onmousemove="setTimeout('sexyTOG()',1);" value="返回">
			</td>
		</tr>
	</table>
</div>
<div id="serviceTypeHistoryDiv" style="VISIBILITY:hidden;position: absolute;">
	<table class="report" borderColor="#f2f2f2" border="1">
		<tr>
			<td>冲值类型</td><td>日期</td><td>操作</td>
		</tr>
		<s:iterator value="account.serviceTypes">
		<tr>
			<td><input type="radio" id="${id}serviceType" name="${id}serviceType" value="1" <c:if test="${type == '1'}">checked</c:if>/>达闻充值
			<input type="radio" id="${id}serviceType" name="${id}serviceType" value="2" <c:if test="${type == '2'}">checked</c:if>/>客户充值
			</td>
			<td>开始日期<s:textfield id="%{id}serviceTypeStartDate" name="startDate" onClick="showcalendar(event, this);" readonly="readOnly"/>
			结束日期<s:textfield id="%{id}serviceTypeEndDate" name="endDateNotNull" onClick="showcalendar(event, this);" readonly="readOnly"/></td>
			<td><input type="button" onClick="editServiceTypeAction(${id})" value="编辑"/><input type="button" onClick="deleteServiceTypeAction(${id})" value="删除"/></td>
		</tr>
		</s:iterator>
		<tr align="center">
			<td colspan="3">
				<input type="button" onmouseout="setTimeout('document.onclick=function(){}',1);" onmousemove="setTimeout('sexyTOG()',1);" value="返回">
			</td>
		</tr>
	</table>
</div>
<div id="serviceFeeRateHistoryDiv" style="VISIBILITY:hidden;position: absolute;">
	<table class="report" borderColor="#f2f2f2" border="1">
		<tr>
			<td>服务费率</td><td>日期</td><td>操作</td>
		</tr>
		<s:iterator value="account.searchServiceFeeRates">
		<tr>
			<td><s:textfield id="%{id}serviceFeeRate" name="value" />%</td>
			<td>开始日期<s:textfield id="%{id}serviceFeeRateStartDate" name="startDate" onClick="showcalendar(event, this);" readonly="readOnly"/>
			结束日期<s:textfield id="%{id}serviceFeeRateEndDate" name="endDateNotNull" onClick="showcalendar(event, this);" readonly="readOnly"/></td>
			<td><input type="button" onClick="editServiceFeeRateAction(${id})" value="编辑"/><input type="button" onClick="deleteServiceFeeRateAction(${id})" value="删除"/></td>
		</tr>
		</s:iterator>
		<tr align="center">
			<td colspan="3">
				<input type="button" onmouseout="setTimeout('document.onclick=function(){}',1);" onmousemove="setTimeout('sexyTOG()',1);" value="返回">
			</td>
		</tr>
	</table>
</div>
<div id="affServiceFeeRateHistoryDiv" style="VISIBILITY:hidden;position: absolute;">
	<table class="report" borderColor="#f2f2f2" border="1">
		<tr>
			<td>网盟服务费率</td><td>日期</td><td>操作</td>
		</tr>
		<s:iterator value="account.affServiceFeeRates">
		<tr>
			<td><s:textfield id="%{id}affServiceFeeRate" name="value" />%</td>
			<td>开始日期<s:textfield id="%{id}affServiceFeeRateStartDate" name="startDate" onClick="showcalendar(event, this);" readonly="readOnly"/>
			结束日期<s:textfield id="%{id}affServiceFeeRateEndDate" name="endDateNotNull" onClick="showcalendar(event, this);" readonly="readOnly"/></td>
			<td><input type="button" onClick="editAffServiceFeeRateAction(${id})" value="编辑"/><input type="button" onClick="deleteAffServiceFeeRateAction(${id})" value="删除"/></td>
		</tr>
		</s:iterator>
		<tr align="center">
			<td colspan="3">
				<input type="button" onmouseout="setTimeout('document.onclick=function(){}',1);" onmousemove="setTimeout('sexyTOG()',1);" value="返回">
			</td>
		</tr>
	</table>
</div>
</s:form>
