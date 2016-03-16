<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<div class="content50">
<font color="red"><s:actionerror/></font>
<font color="green"><s:actionmessage/></font>
<s:form id="editClient" action="contractClientList!editContractClient.action">
<s:hidden name="contractClientId" id="contractClientId" value="%{contractClientId}" />
<s:hidden name="page.pageNo" value="%{page.pageNo}" />
<table class="report" borderColor="#f2f2f2" border="1">
	<s:if test="%{contractClientId!=null}">
	<tr>
		<td align="center" width="80%" colspan="8" class="green-bg1"><h1>编辑客户</h1></td>
	</tr>
	</s:if>
	<s:if test="%{contractClientId==null}">
	<tr>
		<td colspan="8"  width="80%" align="center"><img src="${ctx}/images/news01.jpg" /></td>
	</tr>
	</s:if>
	<tr>
		<td width="30%">客户名</td>
		<td><input type="text" id="clientName" value="${contractClient.name}" name="searchView.clientName" size="50"/> <span style="color:red">*</span></td>
	</tr>
	<tr>
		<td>客户类型</td>
		<td>
			<s:select list="searchView.clientTypes"  listKey="key" listValue="value" name="searchView.clientTypeId" value="contractClient.type"/><span style="color:red">*</span>
		</td>
	</tr>
	<tr>
		<td></td>
		<td>
			<s:if test="%{contractClientId!=null||contractClientId==0}"><input type="button" onclick="checkSubmit()" value="保存"/></s:if>
			<s:else><input type="button" onclick="checkSubmit()" value="新建"/></s:else>
			<input type="button" value="返回" onClick="document.location='contractClientList.action?page.pageNo=${page.pageNo}'"/></td>
		</td>
	</tr>
</table>
</s:form>
<!--
<s:if test="%{contractClientId!=null}">
<s:form id="editPerson" action="contactPerson!editContactPerson.action">
<s:hidden name="contractClientId" id="contractClientId" value="%{contractClientId}" />
<s:hidden name="personId" id="personId" value="%{personId}" />
<s:hidden name="pageNo" value="%{pageNo}" />
<table class="report" borderColor="#f2f2f2" border="1">
	<tr>
		<td colspan="2">
			<input id="userType" name="chargePerson.userType" type="radio" value="1" checked="checked" />常规联系人
			<input id="userType" type="radio" name="chargePerson.userType" value="2" />财务联系人
			<input id="userType" type="radio" name="chargePerson.userType" value="3" />发票联系人</td>
	</tr>
	<tr>
		<td>姓名：</td>
		<td><s:textfield id="name" name="chargePerson.name"/> <span style="color:red">*</span></td>
	</tr>
	<tr>
    	<td>地址：</td>
		<td><s:textfield id="address" name="chargePerson.address"/> <span style="color:red">*</span></td>
	</tr>
	<tr>	
		<td>电话：</td>
		<td><s:textfield id="phone" name="chargePerson.phone"/> <span style="color:red">*</span></td>
	</tr>
	<tr>
		<td>电子邮件：</td>
		<td><s:textfield id="email" name="chargePerson.email"/></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td ><input type="button" onclick="checkPerson()" id="sbmit" value="新增" class="new_construction"/></td>
	</tr>
</table>
<table class="report" borderColor="#f2f2f2" border="1">
	<tr>
		<td align="center" colspan="5" class="green-bg1"><h1>常规联系人</h1></td>
	</tr>
	<tr>
		<th width="15%">姓名</th>
		<th width="35%">地址</th>
		<th width="15%">电话</th>
		<th width="15%">电子邮件</th>
		<th width="20%">操作</th>
	</tr>
	<s:iterator value="contractClient.normalPersons" status="index">
	<tr>
		<td>${name}</td>
		<td>${address}</td>
		<td>${phone}</td>
		<td>${email}</td>
		<td>
		<input type="button" value="编辑" onClick="editPerson(1,${id},'${name}','${address}','${phone}','${email}')" />
		<input type="button" value="删除" onClick="document.location='contactPerson!deleteContactPerson.action?personId=${id}&contractClientId=${contractClientId}'" /></td>
	</tr>	
	</s:iterator>
	<tr>
		<td align="center" colspan="5" class="green-bg1"><h1>财务联系人</h1></td>
	</tr>
	<tr>
		<th width="15%">姓名</th>
		<th width="35%">地址</th>
		<th width="15%">电话</th>
		<th width="15%">电子邮件</th>
		<th width="20%">操作</th>
	</tr>
	<s:iterator value="contractClient.financePersons" status="index">
	<tr>
		<td>${name}</td>
		<td>${address}</td>
		<td>${phone}</td>
		<td>${email}</td>
		<td>
		<input type="button" value="编辑" onClick="editPerson(2,${id},'${name}','${address}','${phone}','${email}')"/>
		<input type="button" value="删除" onClick="document.location='contactPerson!deleteContactPerson.action?personId=${id}&contractClientId=${contractClientId}'" /></td>
	</tr>	
	</s:iterator>
	<tr>
		<td align="center" colspan="5" class="green-bg1"><h1>发票联系人</h1></td>
	</tr>
	<tr>
		<th width="15%">姓名</th>
		<th width="35%">地址</th>
		<th width="15%">电话</th>
		<th width="15%">电子邮件</th>
		<th width="20%">操作</th>
	</tr>	
	<s:iterator value="contractClient.invoicePersons" status="index">
	<tr>
		<td>${name}</td>
		<td>${address}</td>
		<td>${phone}</td>
		<td>${email}</td>
		<td>
		<input type="button" value="编辑" onClick="editPerson(3,'${id}','${name}','${address}','${phone}','${email}')"/>
		<input type="button" value="删除" onClick="document.location='contactPerson!deleteContactPerson.action?personId=${id}&contractClientId=${contractClientId}'" /></td>
	</tr>	
	</s:iterator>
</table>
</s:form>
</s:if>
-->
</div>


<script type="text/javascript">
function trim(str){ //删除左右两端的空格
	return str.replace(/(^\s*)|(\s*$)/g, "");
}
function checkSubmit(){
	var clientName = document.getElementById("clientName").value;
	if (trim(clientName) == ''){
		alert("客户名不能为空");
		return;
	}
	document.getElementById("editClient").submit();
}

function checkPerson(){
	var name = document.getElementById("name").value;
	if (name == ''){
		alert("姓名不能为空");
		return;
	}
	var address = document.getElementById("address").value;
	if (address == ''){
		alert("地址不能为空");
		return;
	}
	var phone = document.getElementById("phone").value;
	if (phone == ''){
		alert("电话不能为空");
		return;
	}
	document.getElementById("editPerson").submit();
}


function editPerson(type, id, name, address, phone, email){
	document.getElementById("userType").value=type;
	document.getElementById("personId").value=id;
	document.getElementById("name").value=name;
	document.getElementById("address").value=address;
	document.getElementById("phone").value=phone;
	document.getElementById("email").value=email;
	for(var i=0;i<document.getElementsByName("chargePerson.userType").length;i++){
	if (type==document.getElementsByName("chargePerson.userType")[i].value) 
		document.getElementsByName("chargePerson.userType")[i].checked=true; 
	}
	document.getElementById("sbmit").value="修改";
}
</script>