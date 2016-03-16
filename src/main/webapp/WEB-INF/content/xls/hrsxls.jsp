<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<s:form action="hrs!importXls.action" method="post" enctype="multipart/form-data">
	<s:hidden name="accountId"/>
   	<s:file id="upload" name="upload"></s:file>
	<input type="submit" value="导入"/>	
</s:form>