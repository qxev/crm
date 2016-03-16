<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<div class="content50">
<table class="report" borderColor="#f2f2f2" border="0">
    <s:form action="bdAdd.action">
       <s:hidden name="id" id="id" value="%{bd.id}" />
       <font color="red"><s:actionerror/></font>
       <tr><td align="center"  class="green-bg1"  colspan="2"><h1><s:if test="(bd.id)">修改</s:if><s:else>新增</s:else>销售人员</h1></td></tr>
       <tr>
           <td>销售人员</td>
           <td><input type="text" value="${bd.name}" name="bd.name" /><br></td></tr>
       <tr>
          <td></td>
          <td>
             <input type="submit" name="submit" value="确定" />
             <input type="button" value="返回" onclick="window.location.href='bdList.action'" style="margin-right:40px;" />
          </td>
       </tr>
    </s:form>
</table>
</div>