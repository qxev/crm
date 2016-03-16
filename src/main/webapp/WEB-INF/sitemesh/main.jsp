<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="head.jsp"%>
<%@ page import="cn.finance.util.DateUtil"%>
<body>
<div class="wrap">
	<div class="nav">
		<div class="main_nav"> 
			<div style="float:left"><img src="${ctx}/images/logoW.png" width="160"/></div>
			<c:if test="${sessionScope.role['1'] == 'have'}"><a id="nav_1" <c:if test="${requestScope.nav == '1'}" var="condition" scope="session">class="current"</c:if> href="${ctx}/main/repaymentList.action">首 页</a></c:if>
			<c:if test="${sessionScope.role['2'] == 'have'}"><a id="nav_2" <c:if test="${requestScope.nav == '2'}" var="condition" scope="session">class="current"</c:if> href="${ctx}/client/accountList.action">客户管理</a></c:if>
			<c:if test="${sessionScope.role['3'] == 'have'}"><a id="nav_3" <c:if test="${requestScope.nav == '3'}" var="condition" scope="session">class="current"</c:if> href="${ctx}/report/reportList.action">查看报表</a></c:if>
			<c:if test="${sessionScope.role['4'] == 'have'}"><a id="nav_4" <c:if test="${requestScope.nav == '4'}" var="condition" scope="session">class="current"</c:if> href="${ctx}/fee/totalAmountList.action">充值管理</a></c:if>
			<c:if test="${sessionScope.role['5'] == 'have'}"><a id="nav_5" <c:if test="${requestScope.nav == '5'}" var="condition" scope="session">class="current"</c:if> href="${ctx}/sys/passwordInit.action">系统管理</a></c:if>
			<c:if test="${sessionScope.role['6'] == 'have'}"><a id="nav_6" <c:if test="${requestScope.nav == '6'}" var="condition" scope="session">class="current"</c:if> href="${ctx}/client/contract!contractList.action">合同管理</a></c:if>
		</div>
		<div style="float:right">
				<font color="white">欢迎您！用户名：${sessionScope.user_session.userName}当前时间: <%=DateUtil.getCurrentDateShortStyle()%></font>
				<a class="aaad" href="${ctx}/login/logout.action">退出系统</a>
		</div>
		<div id="sub_nav_1" class="sub_nav_01" <c:if test="${requestScope.nav != '1'}" var="condition" scope="session">style="display:none"</c:if>>
			<ul>
			    <c:if test="${sessionScope.role['1.1'] != null}"><li><a href="${ctx}/main/alertList.action" <c:if test="${requestScope.nav == '1' && requestScope.subNav == '1'}" var="condition" scope="session">class="current1"</c:if>>账户充值预警</a></li></c:if>
				<c:if test="${sessionScope.role['1.2.1'] != null}"><li><a href="${ctx}/main/repaymentList.action" <c:if test="${requestScope.nav == '1' && requestScope.subNav == '2'}" var="condition" scope="session">class="current1"</c:if>>还款提醒</a></li></c:if>
			</ul>
		</div>
		<div id="sub_nav_2" class="sub_nav_01" <c:if test="${requestScope.nav != '2'}" var="condition" scope="session">style="display:none"</c:if>>
			<ul>
			    <c:if test="${sessionScope.role['2.1.1'] != null}"><li><a href="${ctx}/client/clientList.action" <c:if test="${requestScope.nav == '2' && requestScope.subNav == '1'}" var="condition" scope="session">class="current1"</c:if> >客户列表</a></li></c:if>
				<c:if test="${sessionScope.role['2.2.1'] != null}"><li><a href="${ctx}/client/projectList.action" <c:if test="${requestScope.nav == '2' && requestScope.subNav == '2'}" var="condition" scope="session">class="current1"</c:if>>项目列表</a></li></c:if>
				<c:if test="${sessionScope.role['2.3.1'] != null}"><li><a href="${ctx}/client/mediaList.action" <c:if test="${requestScope.nav == '2' && requestScope.subNav == '3'}" var="condition" scope="session">class="current1"</c:if>>媒体列表</a></li></c:if>
				<c:if test="${sessionScope.role['2.4.1'] != null}"><li><a href="${ctx}/client/channelList.action" <c:if test="${requestScope.nav == '2' && requestScope.subNav == '4'}" var="condition" scope="session">class="current1"</c:if>>渠道列表</a></li></c:if>
				<c:if test="${sessionScope.role['2.5.1'] != null}"><li><a href="${ctx}/client/accountList.action" <c:if test="${requestScope.nav == '2' && requestScope.subNav == '5'}" var="condition" scope="session">class="current1"</c:if>>账户列表</a></li></c:if>
				<c:if test="${sessionScope.role['2.5.2'] != null}"><li><a href="${ctx}/client/accountList!addInit.action" <c:if test="${requestScope.nav == '2' && requestScope.subNav == '6'}" var="condition" scope="session">class="current1"</c:if>>新建账户</a></li></c:if>
			</ul>
		</div>
		<div id="sub_nav_3" class="sub_nav_01" <c:if test="${requestScope.nav != '3'}" var="condition" scope="session">style="display:none"</c:if>>
			<ul>
			    <c:if test="${sessionScope.role['3.1.1'] != null}"><li><a href="${ctx}/report/reportList.action" <c:if test="${requestScope.nav == '3' && requestScope.subNav == '1'}" var="condition" scope="session">class="current1"</c:if>>SEM业务报表</a></li></c:if>
				<c:if test="${sessionScope.role['3.2.1'] != null}"><li><a href="${ctx}/report/pmHistoryList.action" <c:if test="${requestScope.nav == '3' && requestScope.subNav == '2'}" var="condition" scope="session">class="current1"</c:if>>SEM分析师管理帐户历史</a></li></c:if>
				<c:if test="${sessionScope.role['3.2.1'] != null}"><li><a href="${ctx}/report/discountList.action" <c:if test="${requestScope.nav == '3' && requestScope.subNav == '3'}" var="condition" scope="session">class="current1"</c:if>>折扣率历史</a></li></c:if>
				<c:if test="${sessionScope.role['3.2.1'] != null}"><li><a href="${ctx}/report/discountBackList.action" <c:if test="${requestScope.nav == '3' && requestScope.subNav == '4'}" var="condition" scope="session">class="current1"</c:if>>折扣返还率历史</a></li></c:if>
				<c:if test="${sessionScope.role['3.2.1'] != null}"><li><a href="${ctx}/report/serviceFeeList.action" <c:if test="${requestScope.nav == '3' && requestScope.subNav == '5'}" var="condition" scope="session">class="current1"</c:if>>服务费历史</a></li></c:if>
				<c:if test="${sessionScope.role['3.2.1'] != null}"><li><a href="${ctx}/report/serviceFeeRateList.action" <c:if test="${requestScope.nav == '3' && requestScope.subNav == '6'}" var="condition" scope="session">class="current1"</c:if>>服务费率历史</a></li></c:if>
				<c:if test="${sessionScope.role['3.2.1'] != null}"><li><a href="${ctx}/report/serviceFeeAdjustList.action" <c:if test="${requestScope.nav == '3' && requestScope.subNav == '7'}" var="condition" scope="session">class="current1"</c:if>>服务费调整历史</a></li></c:if>
				<c:if test="${sessionScope.role['3.8.2'] != null}"><li><a href="${ctx}/fee/serviceList.action" <c:if test="${requestScope.nav == '3' && requestScope.subNav == '8'}" var="condition" scope="session">class="current1"</c:if>>服务费调整</a></li></c:if>
				<c:if test="${sessionScope.role['3.1.1'] != null}"><li><a href="${ctx}/report/reportAccountList.action?searchView.mediaId=-1" <c:if test="${requestScope.nav == '3' && requestScope.subNav == '9'}" var="condition" scope="session">class="current1"</c:if>>SEM账户业务报表</a></li></c:if>
			</ul>
		</div>
		<div id="sub_nav_4" class="sub_nav_01" <c:if test="${requestScope.nav != '4'}" var="condition" scope="session">style="display:none"</c:if>>
			<ul>
			    <c:if test="${sessionScope.role['4.1.2'] != null}"><li><a href="${ctx}/fee/initFee.action" <c:if test="${requestScope.nav == '4' && requestScope.subNav == '1'}" var="condition" scope="session">class="current1"</c:if>>添加充值记录</a></li></c:if>
				<c:if test="${sessionScope.role['4.2.1'] != null}"><li><a href="${ctx}/fee/seAmountList.action" <c:if test="${requestScope.nav == '4' && requestScope.subNav == '2'}" var="condition" scope="session">class="current1"</c:if>>搜索引擎充值历史</a></li></c:if>
				<c:if test="${sessionScope.role['4.2.1'] != null}"><li><a href="${ctx}/fee/totalAmountList.action" <c:if test="${requestScope.nav == '4' && requestScope.subNav == '3'}" var="condition" scope="session">class="current1"</c:if>>帐户冲值历史</a></li></c:if>
				<c:if test="${sessionScope.role['4.2.1'] != null}"><li><a href="${ctx}/fee/payAmountList.action" <c:if test="${requestScope.nav == '4' && requestScope.subNav == '4'}" var="condition" scope="session">class="current1"</c:if>>达闻付款历史</a></li></c:if>
			</ul>
		</div>
	    <div id="sub_nav_5" class="sub_nav_01" <c:if test="${requestScope.nav != '5'}" var="condition" scope="session">style="display:none"</c:if>>
			<ul>
		<!--	    <li><a href="${ctx}/sys/languageInit.action" <c:if test="${requestScope.nav == '5' && requestScope.subNav == '1'}" var="condition" scope="session">class="current1"</c:if>>选择语言</a></li>-->
				<li><a href="${ctx}/sys/passwordInit.action" <c:if test="${requestScope.nav == '5' && requestScope.subNav == '2'}" var="condition" scope="session">class="current1"</c:if>>修改密码</a></li>
				<c:if test="${sessionScope.role['5.3.2'] != null}"><li><a href="${ctx}/sys/pmList.action" <c:if test="${requestScope.nav == '5' && requestScope.subNav == '3'}" var="condition" scope="session">class="current1"</c:if>>PM管理</a></li></c:if>
				<c:if test="${sessionScope.role['5.3.2'] != null}"><li><a href="${ctx}/sys/bdList.action" <c:if test="${requestScope.nav == '5' && requestScope.subNav == '4'}" var="condition" scope="session">class="current1"</c:if>>销售人员管理</a></li></c:if>
				<c:if test="${sessionScope.role['5.3.2'] != null}"><li><a href="${ctx}/sys/industryList.action" <c:if test="${requestScope.nav == '5' && requestScope.subNav == '5'}" var="condition" scope="session">class="current1"</c:if>>行业管理</a></li></c:if>
				<c:if test="${sessionScope.role['5.3.2'] != null}"><li><a href="${ctx}/sys/sysAlertInit.action" <c:if test="${requestScope.nav == '5' && requestScope.subNav == '6'}" var="condition" scope="session">class="current1"</c:if>>设置提醒参数</a></li></c:if>
				<c:if test="${sessionScope.role['5.3.2'] != null}"><li><a href="${ctx}/sys/exchangeList.action" <c:if test="${requestScope.nav == '5' && requestScope.subNav == '7'}" var="condition" scope="session">class="current1"</c:if>>汇率管理</a></li></c:if>
				<c:if test="${sessionScope.role['5.3.2'] != null}"><li><a href="${ctx}/sys/contractTemplate!list.action" <c:if test="${requestScope.nav == '5' && requestScope.subNav == '8'}" var="condition" scope="session">class="current1"</c:if>>合同模板管理</a></li></c:if>
				<c:if test="${sessionScope.role['type'] == '1'}"><li><a href="${ctx}/sys/role!list.action" <c:if test="${requestScope.nav == '5' && requestScope.subNav == '9'}" var="condition" scope="session">class="current1"</c:if>>权限管理</a></li></c:if>
				<c:if test="${(sessionScope.role['type'] == '3' || sessionScope.role['type'] == '4' || sessionScope.role['type'] == '5' || sessionScope.role['type'] == '6' || sessionScope.role['type'] == '7')&&(sessionScope.role['agent'] != '1')}"><li><a href="${ctx}/sys/role!initRoleSet.action" <c:if test="${requestScope.nav == '5' && requestScope.subNav == '10'}" var="condition" scope="session">class="current1"</c:if>>设置代理人</a></li></c:if>
			</ul>
		</div>
		<div id="sub_nav_6" class="sub_nav_01" <c:if test="${requestScope.nav != '6'}" var="condition" scope="session">style="display:none"</c:if>>
			<ul>
				<c:if test="${sessionScope.role['6.1.2'] != null}"><li><a href="${ctx}/client/contractClientList!addContractClient.action" <c:if test="${requestScope.nav == '6' && requestScope.subNav == '1'}" var="condition" scope="session">class="current1"</c:if>>新建客户</a></li></c:if>
			    <c:if test="${sessionScope.role['6.1.1'] != null}"><li><a href="${ctx}/client/contractClientList.action" <c:if test="${requestScope.nav == '6' && requestScope.subNav == '2'}" var="condition" scope="session">class="current1"</c:if> >客户列表</a></li></c:if>
				<c:if test="${sessionScope.role['6.2.1'] != null}"><li><a href="${ctx}/client/contractProject!contractProjectList.action" <c:if test="${requestScope.nav == '6' && requestScope.subNav == '3'}" var="condition" scope="session">class="current1"</c:if>>SEM项目</a></li></c:if>
				<c:if test="${sessionScope.role['6.2.1'] != null}"><li><a href="${ctx}/client/contractProject!otherContractProjectList.action?businessTypeId=5" <c:if test="${requestScope.nav == '6' && requestScope.subNav == '4'}" var="condition" scope="session">class="current1"</c:if>>SEO项目</a></li></c:if>
				<c:if test="${sessionScope.role['6.2.1'] != null}"><li><a href="${ctx}/client/contractProject!otherContractProjectList.action?businessTypeId=6" <c:if test="${requestScope.nav == '6' && requestScope.subNav == '5'}" var="condition" scope="session">class="current1"</c:if>>UT项目</a></li></c:if>
				<c:if test="${sessionScope.role['6.2.1'] != null}"><li><a href="${ctx}/client/contractProject!otherContractProjectList.action?businessTypeId=7" <c:if test="${requestScope.nav == '6' && requestScope.subNav == '6'}" var="condition" scope="session">class="current1"</c:if>>SCL项目</a></li></c:if>
				<c:if test="${sessionScope.role['6.2.1'] != null}"><li><a href="${ctx}/client/contractProject!otherContractProjectList.action?businessTypeId=13" <c:if test="${requestScope.nav == '6' && requestScope.subNav == '7'}" var="condition" scope="session">class="current1"</c:if>>OTHER项目</a></li></c:if>
				<c:if test="${sessionScope.role['2.8.1'] != null}"><li><a href="${ctx}/client/contract!contractList.action" <c:if test="${requestScope.nav == '6' && requestScope.subNav == '8'}" var="condition" scope="session">class="current1"</c:if>>我的合同列表</a></li></c:if>
			</ul>
		</div>
	</div>
	<decorator:getProperty property="body.onload" writeEntireProperty="true"/> 
	<decorator:body />
	<div class="foot">
		<p>版权归达闻文化传播所有</p>
	</div>
</div>
</body>
</html>
