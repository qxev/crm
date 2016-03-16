<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<script type="text/javascript" src="${ctx}/js/calendar.js"></script>
<table class="fee" borderColor="#f2f2f2" border="1">
<s:form action="serviceAdjust.action">
   <font color="red"><s:actionerror/></font>
   <font color="green"><s:actionmessage/></font>
                 <s:hidden name="channelId" id="channelId" value="%{channelId}" />
				<tr>
					<td>
						开始日期
					</td>
					<td>
						<input type="text" name="startDate" onClick="showcalendar(event, this);" readonly="readOnly"/>
					</td>	
				</tr>
				<tr>
					<td>
						结束日期
					</td>
					<td>
						<input type="text" name="endDate" onClick="showcalendar(event, this);" readonly="readOnly"/>
					</td>
				</tr>
				<tr>
					<td>
						服务费金额
					</td>
					<td>
						<input type="text" name="serviceFeeAdjust.value" value="${serviceFeeAdjust.value}"/>RMB
					</td>
				</tr>
				<tr>
				   <td> </td>
				   <td>
				     <input type="submit" name="submit" value="新增" />
				     <INPUT TYPE='BUTTON' VALUE='返回' onClick='window.close()'>
				   </td>
				</tr>
</s:form>
</table>
