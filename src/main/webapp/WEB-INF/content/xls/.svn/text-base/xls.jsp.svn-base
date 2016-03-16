<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<s:form action="xls!importXls.action" method="post" enctype="multipart/form-data">
	<s:hidden name="accountId"/>
   	<s:actionerror />
   	<s:actionmessage />
   	<s:select name="type"  list="#{1:'sogou',2:'网盟',3:'soso',4:'yahoo'}"  key="{1,2,3}" headerKey="0" headerValue="请选择" />
   	<s:file id="upload" name="upload"></s:file>
	<input type="submit" value="导入"/>	
</s:form>