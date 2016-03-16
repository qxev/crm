<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<div class="content50">
<table class="report" borderColor="#f2f2f2" border="0">
    <s:form action="contractTemplate!save.action">
       <s:hidden name="id" id="id" value="%{id}" />
       <font color="red"><s:actionerror/></font>
       <tr><td align="center"  class="green-bg1" ><h1>修改合同模板</h1></td></tr>
       <tr>
           <td><s:textarea name="contractTemplate.context" cols="80" rows="20"/></td></tr>
       <tr>
          <td align="center">
             <input type="submit" name="submit" value="确定" />
             <input type="button" value="返回" onclick="window.location.href='contractTemplate!list.action'" style="margin-right:40px;" />
          </td>
       </tr>
    </s:form>
</table>
</div>