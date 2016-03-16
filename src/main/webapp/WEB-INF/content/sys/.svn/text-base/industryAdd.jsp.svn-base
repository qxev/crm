<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<div class="content50">
<table class="report" borderColor="#f2f2f2" border="0">
    <s:form action="industryAdd.action">
       <s:hidden name="id" id="id" value="%{industry.id}" />
       <font color="red"><s:actionerror/></font>
       <tr><td align="center"  class="green-bg1"  colspan="2"><h1><s:if test="(industry.id)">修改</s:if><s:else>新增</s:else>行业</h1></td></tr>
       <tr>
           <td>行业名 </td>
           <td><input type="text" value="${industry.name}" name="industry.name" /><br></td></tr>
       <tr>
          <td></td>
          <td>
             <input type="submit" name="submit" value="确定" />
             <input type="button" value="返回" onclick="window.location.href='industryList.action'" style="margin-right:40px;" />
          </td>
       </tr>
    </s:form>
</table>
</div>