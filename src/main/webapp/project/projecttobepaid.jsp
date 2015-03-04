<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<form method="post" action="/oa/project/projecttobepaid.action"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">
<input type="hidden" name="project.id" value="${project.id}">
<input type="hidden" name="application.id" value="${application.id}">
<input type="hidden" name="paymentOrder.id" value="${paymentOrder.id}">
	<table class="table" width="100%" layoutH="167">
	<thead>
		<tr>
			<th align="center">时间</th>
			<th align="center">标题</th>
			<th align="center">类型</th>
			<th align="center">总金额</th>
			<th align="center">经办人</th>
		</tr>
	</thead>
	<tbody>	
		<s:iterator value="paymentOrderList" id="obj">
			<tr target="objId" rel="<s:property value="id" />">
			<td><s:property value="requiredPayTime" /></td>
			<td><s:property value="title" /></td>
			<td>
				<s:if test="businessType=='BORROWMONEY'">借款</s:if> 
				<s:elseif test="businessType=='REQUESTMONEYMATERIAL'">材料请款</s:elseif> 
				<s:elseif test="businessType=='REQUESTMONEYENGINEER'">工程请款</s:elseif>
				<s:elseif test="businessType=='EXPENSEOTHER'">费用报销</s:elseif>
				<s:elseif test="businessType=='PURCHASE'">采购</s:elseif>
			</td>
			<td><s:property value="payAmount" /></td>	
			<td><s:property value="user.realName" /></td>
			</tr>
		</s:iterator>
	</tbody>
</table>
</form>
