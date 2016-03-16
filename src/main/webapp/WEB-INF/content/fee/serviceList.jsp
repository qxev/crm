<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<script type="text/javascript" src="${ctx}/js/calendar.js"></script>
<script type="text/javascript" src="${ctx}/js/popdiv.js"></script>
<script type="text/javascript">
	function adjusts(channelId,pageFilter){
		if(document.getElementById("projectId").value==0){
			alert("请选择项目");
			return false;
		} else {
			document.getElementById("addChannelId").value=channelId;
			document.getElementById("addProjectId").value=document.getElementById("projectId").value
			document.getElementById("addMediaId").value=document.getElementById("mediaId").value
			document.getElementById("addChId").value=document.getElementById("channelId").value
			document.getElementById("addClientId").value=document.getElementById("clientId").value
			sexyBOX('调整服务费','adjustDiv','400');
			return false;
		}
	}
	function checkAndSubmit(){
		var startDate = document.getElementById("startDate").value;
		var endDate = document.getElementById("endDate").value;
		var adjustValue = document.getElementById('adjustValue').value;
		var m1=Number(adjustValue);
		if(startDate==''){
			alert("请输入开始时间");
			return;
		} else if(endDate==''){
			alert("请输入结束时间");
			return;
		} else if(adjustValue==''){
			alert("请输入金额");
			return;
		} if (isNaN(m1)){
			alert("请输入正确的金额");
			return;
		} else if (endDate < startDate ) {
			alert("结束时间必须大于开始时间");
			return;
		} 
		document.getElementById("serviceAdjust").submit();
	}
</script>
<div class="content">
<s:form id="serviceListForm" action="/fee/serviceList.action">
	<div style="margin:0 auto;width:65%;">
	<font color="green"><s:actionmessage/></font>
	<table align="center">
		<tr>
			<td width="65%">客户选择: <s:select list="searchView.clients" headerKey="0" headerValue="全部" name="searchView.clientId" value="searchView.clientId" listKey="id" listValue="name" id="clientId" onChange="changeProject()"/></td>
			<td width="35%">媒体选择: <s:select list="searchView.medias" headerKey="0" headerValue="全部" name="searchView.mediaId" id="mediaId" listKey="id" listValue="name" onChange="changeChannel()"/></td>
		</tr>
		<tr>
			<td>项目选择: <s:select list="searchView.projects" headerKey="0" headerValue="全部" name="searchView.projectId" value="searchView.projectId" listKey="id" listValue="name" id="projectId"/></td>
			<td>渠道选择: <s:select list="searchView.channels" headerKey="0" headerValue="全部" name="searchView.channelId" value="searchView.channelId" listKey="id" listValue="name" id="channelId"/></td>
		</tr>
		<tr>
			<td>如果要调整服务费，请先选择项目</td>
			<td><input type="image" src="${ctx}/images/search.gif" onClick="userSubmit('serviceListForm')"/></td>
		</tr>
	</table>
	</div>
	<table class="report" borderColor="#f2f2f2" border="1">
			<tr>
				<td align="center" width="80%" class="green-bg1" colspan="4"><h1>服务费调整管理</h1></td>
			</tr>
			<tr>
				<td align="right" colspan="4">
					<report:pages action="serviceList.action" excel="serviceList!excelOutput.action" page="${page}"/>
				</td>
			</tr>
			<tr align="center">
			<td><input type="checkbox" checked class="chbx" value="0"/></td>
			<td><input type="checkbox" checked class="chbx" value="1"/></td>
			<td><input type="checkbox" checked class="chbx" value="2"/></td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<th><report:tableTitle title="渠道名" action="payAmountList.action" sqlName="ch.name" page="${page}" ctx="${ctx}"/></th>
			<th><report:tableTitle title="媒体名" action="payAmountList.action" sqlName="m.name" page="${page}" ctx="${ctx}"/></th>
			<th><report:tableTitle title="状态" action="payAmountList.action" sqlName="ch.status" page="${page}" ctx="${ctx}"/></th>
			<th>操作</th>
		</tr>
		<s:iterator value="page.result" status="index">
		<s:if test="#index.odd == true">
			<tr class="table_style1">
		</s:if>
		<s:else>
			<tr class="table_style2">
		</s:else>
				<td >${name}&nbsp;</td>
				<td>${media.name}&nbsp;</td>
				<td>${statusDisplay}&nbsp;</td>
				<td align="center"><input type="button" onClick="adjusts(${id},${page.filter})" value="调整服务费" /></td>
			</tr>
		</s:iterator>
    </table>
</s:form>
<report:pages action="serviceList.action" excel="serviceList!excelOutput.action" page="${page}"/>
</div>
<div id="adjustDiv" style="VISIBILITY:hidden;position: absolute;"> 
	<s:form id="serviceAdjust" action="serviceAdjust.action">
		<table style="margin:auto;text-align:center;"> 
		    <s:hidden name="addChannelId" id="addChannelId"/> 
		    <s:hidden name="addProjectId" id="addProjectId"/> 
		    <s:hidden name="addMediaId" id="addMediaId"/> 
		    <s:hidden name="addChId" id="addChId"/> 
		    <s:hidden name="addClientId" id="addClientId"/> 
		        <tr>
					<td>
						开始日期
					</td>
					<td>
						<input type="text" id="startDate" name="startDate" onClick="showcalendar(event, this);" readonly="readOnly"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>	
				</tr>
				<tr>
					<td>
						结束日期
					</td>
					<td>
						<input type="text" id="endDate" name="endDate" onClick="showcalendar(event, this);" readonly="readOnly"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						服务费金额
					</td>
					<td>
						<input type="text" id="adjustValue" name="serviceFeeAdjust.valueStr" value="${serviceFeeAdjust.value}"/>RMB
					</td>
				</tr>
				<tr>
				   <td> </td>
				   <td>
				     <input type="button" value="新增" onClick="checkAndSubmit();" />
				     <input type="button" onmouseout="setTimeout('document.onclick=function(){}',1);" onmousemove="setTimeout('sexyTOG()',1);" value="返回">
				   </td>
				</tr>
		</table>
	</s:form>
</div> 
