<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">    
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
	<head>
		<title>用户登录</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css"/>
	</head>
	<body>
		<div style="height:150px"/>
		<div id="header1" >
			<div id="logo" class="bg-header">
				<a href="#"><span>LinkValue</span></a>
			</div>
 			<div id="top_nav" class="bg-header">
				<div id="quick_links">
					<ul></ul>
				</div>
			</div>
    	</div>
    	
    	<s:form action="/sf/login.action" method="post">
		<div id="main_login">
  			<div class="rc">
    		    <div class="login_div">
                    <div class="login_head">财务系统</div>
                    <div class="login_right">
                    	<s:actionerror />
                        <div class="login_body">
                            <dl class="login_dl">
                             
	                            <dt>用户名：</dt>
								<dd><s:textfield name="userLoginView.username" style="width:140px;"></s:textfield></dd>
	                            <dt>密码：</dt>
	    						<dd><s:password name="userLoginView.password" style="width:140px;"></s:password>
								</dd>
								<dd><input type="submit" value="" class="login_sub" />	
	                            </dd>
	                           <input type="hidden" name="userLoginView.contractId" value="${userLoginView.contractId}"> 
                           </dl>
                    </div>
                    <div class="login_bottom"></div>
                </div>
		  	</div>
		</div>
		</s:form>
    </body>
</html>