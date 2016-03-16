<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<div class="content50">
<table class="report" borderColor="#f2f2f2" border="0">
    <s:form action="pmAdd.action">
       <s:hidden name="id" id="id" value="%{pm.id}" />
       <font color="red"><s:actionerror/></font>
       <tr><td align="center"  class="green-bg1"  colspan="2"><h1><s:if test="(pm.id)">修改</s:if><s:else>新增</s:else>SEM分析师</h1></td></tr>
		<tr>
		<td align="right">业务分类：</td>
		<td><s:select list="businessTypes" name="pm.businessType.id" listKey="id" listValue="name" value="pm.businessType.id"/> <span style="color:red">*</span></td>
		</tr>
       <tr>
           <td align="right">SEM分析师</td>
           <td><input type="text" value="${pm.name}" name="pm.name" /><br></td></tr>
       <tr>
          <td></td>
          <td>
             <input type="submit" name="submit" value="确定" />
             <input type="button" value="返回" onclick="window.location.href='pmList.action'" style="margin-right:40px;" />
          </td>
       </tr>
    </s:form>
</table>
</div>