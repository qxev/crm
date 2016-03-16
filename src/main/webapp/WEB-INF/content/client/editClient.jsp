<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<div class="content50">
<s:form id="editClient" action="clientList!editClient.action">
<s:hidden name="clientId" id="clientId" value="%{clientId}" />
<s:hidden name="pageNo" value="%{pageNo}" />
<table class="report" borderColor="#f2f2f2" border="1">
	<tr>
		<td align="center" width="80%" colspan="8" class="green-bg1"><h1><s:if test="%{clientId!=null||clientId==0}">编辑</s:if><s:else>新建</s:else>客户</h1></td>
	</tr>
	<tr>
		<td width="30%">客户名</td>
		<td><input type="text" id="clientName" value="${client.name}" name="searchView.clientName" size="50"/> <span style="color:red">*</span></td>
	</tr>
	<tr>
		<td>客户类型</td>
		<td>
			<s:select list="searchView.clientTypes"  listKey="key" listValue="value" name="searchView.clientTypeId" value="client.type"/><span style="color:red">*</span>
		</td>
	</tr>
	<tr align="center">
		<td colspan="2">
			<s:if test="%{clientId!=null||clientId==0}"><input type="button" onclick="checkSubmit()" value="保存"/></s:if>
			<s:else><input type="button" onclick="checkSubmit()" value="新建"/></s:else>
			<input type="button" value="返回" onClick="document.location='clientList.action?page.pageNo=${pageNo}'" /></td>
		</td>
	</tr>
</table>
</s:form>
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