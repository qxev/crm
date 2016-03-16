<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<div class="content50">
<table class="report" borderColor="#f2f2f2" border="1">
     <font color="green"><s:actionmessage/></font>
     <tr>
	     <td align="center" width="90%" colspan="2" class="green-bg1"><h1>选择语言</h1></td>
	 </tr>
     <tr>
	   <s:if test="%{user.language==1}"><td>中文</td><td><a href="languageSet.action?language=2">english</a></td></s:if>
	   <s:if test="%{user.language==2}"><td><a href="languageSet.action?language=1">中文</a></td><td> english</td></s:if>
	 </tr>
</table>
</div>