<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/table.jsp"%>
<div class="content50">
<table class="report" borderColor="#f2f2f2" border="0">
    <s:form action="exchangeList!add.action">
       <s:hidden name="exchangeId" id="exchangeId" value="%{exchangeId}" />
       <s:hidden name="id" id="id" value="%{exchangeHistory.id}" />
       	<s:hidden name="filter" value="%{filter}" />
		<s:hidden name="pageNo" value="%{pageNo}" />
       <font color="red"><s:actionerror/></font>
       <tr><td align="center"  class="green-bg1"  colspan="2"><h1><s:if test="(exchangeHistory.id)">修改</s:if><s:else>新增</s:else>汇率</h1></td></tr>
<!--      <tr>
           <td>国家</td>
           <td><s:select list="exchanges" name="exchangeHistory.exchange.id" listKey="id" listValue="country"/></td>
       </tr>
       <tr>
           <td>日期</td>
           <td><input type="text" name="year" size="5" value="${year}">年<input type="text" name="month" size="3" value="${month}">月</td>
       </tr> 
       -->
       <tr>
           <td align="right">国家：</td>
           <td>${exchangeHistory.exchange.country}</td>
       </tr> 
       <tr>
           <td align="right">日期：</td>
           <td>${exchangeHistory.exchangeDateDisplay}</td>
       </tr> 
       <tr>
           <td align="right">汇率：</td>
           <td><input type="text" name="exchangeHistory.valueDisplay" size="6" id="totalBalance" value="${exchangeHistory.value}"></td>
       </tr>
       <tr>
          <td></td>
          <td>
             <input type="submit" name="submit" value="确定" />
             <input type="button" value="返回" onclick="window.location.href='exchangeList!oneExchangeList.action?exchangeId=${exchangeId}&page.pageNo=${pageNo}&page.filter=${filter}'" style="margin-right:40px;" />
          </td>
       </tr>
    </s:form>
</table>
</div>