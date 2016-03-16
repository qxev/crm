<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<div class="content50">
<table class="report" borderColor="#f2f2f2" border="1">
    <s:form action="passwordSet.action">
       <font color="red"><s:actionerror/></font>
       <font color="green"><s:actionmessage/></font>
       <tr>
	     <td align="center" width="90%" colspan="2" class="green-bg1"><h1>修改密码</h1></td>
	   </tr>
       <tr>
           <td>原&nbsp;密&nbsp;码&nbsp;:</td>
           <td><input type="password" value="" name="password" /></td> <br>
        </tr>
       <tr>
          <td>新&nbsp;密&nbsp;码&nbsp;: </td>
          <td><input type="password" value="" name="newPassword" /></td><br>
       </tr>
       <tr>
           <td>确认密码: </td>
           <td><input type="password" value="" name="rePassword" /><br></td>
       </tr>
       <tr>
          <td></td>
          <td><input type="submit" name="submit" value="确定" /></td>
       </tr>
     </s:form>  
</table>
</div>