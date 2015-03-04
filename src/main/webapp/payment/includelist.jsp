<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<form id="pagerForm" method="post" action="/oa/paymentorder/listpay.action">
	<input type="hidden" name="pageNum" value="${pagination.currentPage}" />
	<input type="hidden" name="numPerPage" value="${pagination.pageSize}" />
</form>

	<table class="table" width="100%" layoutH="102">
		<thead>
			<tr>
				<th align="center">付款单号</th>
				<th align="center">业务类型</th>
				<th align="center">付款标题</th>
				<th align="center">经办人</th>
				<th align="center">付款金额</th>
				<th align="center">准备付款</th>
				<th align="center">实际付款</th>
				<th align="center">付款时间</th>
			</tr>
		</thead>


		<s:iterator value="pagination.result" id="obj">
			<tr target="objId" rel="<s:property value="id" />">
			<td>
			<s:property value="id" />
			</td>
			<td>
				<s:if test="businessType=='BORROWMONEY'">借款</s:if>
				<s:elseif test="businessType=='REQUESTMONEYMATERIAL'">材料请款</s:elseif>
				<s:elseif test="businessType=='REQUESTMONEYENGINEER'">工程请款</s:elseif>
				<s:elseif test="businessType=='EXPENSEOTHER'">费用报销</s:elseif>
				<s:elseif test="businessType=='PURCHASE'">采购</s:elseif>
			</td>		
			<td>
			<%-- <a href="/oa/paymentorder/payloglist.action?paymentOrder.id=<s:property value="id"/>" target="ajax" rel="payLog"> --%>
				<s:property value="title" />
			<!-- </a> -->
			</td>
			<td>
			<s:property value="user.realName" />
			</td>
			<td>
			<s:property value="payAmount" />
			</td>
			<td>
			<s:property value="readyAmount" />
			</td>
			<td>
			<s:property value="actualAmount" />
			</td>
			<td>
			<s:property value="requiredPayTime" />
			</td>
			</tr>
		</s:iterator>


		</tbody>
	</table>
		<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20" <s:if test="pagination.pageSize == 20">selected</s:if>>20</option>
				<option value="50" <s:if test="pagination.pageSize == 50">selected</s:if>>50</option>
			</select>
			<span>条，共${pagination.totalCount}条</span>
		</div>		
		<div class="pagination" targetType="navTab" totalCount="${pagination.totalCount}" numPerPage="${pagination.pageSize}" pageNumShown="${pagination.pageCount}" currentPage="${pagination.currentPage}"></div>
	</div>
