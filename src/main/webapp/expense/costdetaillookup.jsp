<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="pageContent">

	<table class="table" layoutH="118" targetType="dialog" width="100%">
		<thead>
			<tr>
				<!-- <th orderfield="category">种类</th> -->
				<th>日期</th>
				<th>分类</th>
				<th>金额</th>
				<th width="80">选择</th>
			</tr>
		</thead>
		<tbody>
		
			<s:iterator value="pagination.result" id="costDetail">
			<tr>
			<td><s:property value="expenseTime" /></td>
			<td><s:property value="expenseType" /></td>
			<td><s:property value="payMoney" /></td>			
			<td>
				<a class="btnSelect" href="javascript:$.bringBack({id:'<s:property value="id" />',expenseTime:'<s:property value="expenseTime" />', expenseType:'<s:property value="expenseType" />', payMoney:'<s:property value="payMoney" />'})" title="选择">选择</a>
			</td>
			</tr>
			</s:iterator>	
			
		</tbody>
	</table>
	</div>

	<div class="panelBar">
		<div class="pages">
		<div class="pages">
			<span>每页</span>

			<select name="numPerPage" onchange="dwzPageBreak({targetType:dialog, numPerPage:'20'})">
				<option value="20" <s:if test="pagination.pageSize == 20">selected</s:if>>20</option>
				<option value="50" <s:if test="pagination.pageSize == 50">selected</s:if>>50</option>
			</select>
			<span>条，共${pagination.totalCount}条</span>
		</div>
		<div class="pagination" targetType="dialog" totalCount="${pagination.totalCount}" numPerPage="${pagination.pageSize}" pageNumShown="10" currentPage="${pagination.currentPage}"></div>
	</div>
</div>
