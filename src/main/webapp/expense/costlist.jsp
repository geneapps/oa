<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<form id="pagerForm" method="post" action="/oa/project/projectlist.action">
	<input type="hidden" name="status" value="">
	<input type="hidden" name="keywords" value="" />
	<input type="hidden" name="pageNum" value="${pagination.currentPage}" />
	<input type="hidden" name="numPerPage" value="${pagination.pageSize}" />
	<input type="hidden" name="orderField" value="" />
	<input type="hidden" name="searchType" value="${searchType}" />
	<input type="hidden" name="searchKey" value="${searchKey}" />
</form>


<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="/oa/expense/newaddcostdetails.action" target="dialog" rel="costDetails" width="800" height="480"><span>添加费用明细</span></a></li>			
			<li><a class="edit" href="/oa/expense/neweditcostdetails.action?costDetails.id={sid}" target="dialog" rel="costDetailsTab"><span>编辑费用明细</span></a></li>
			<li><a class="delete" href="/oa/expense/delcostdetails.action?costDetails.id={sid}" target="ajaxTodo" title="确定要删除吗?"><span>删除费用明细</span></a></li>
			<li><a class="add" href="/oa/approve/editexpenseapp.action" target="navTab" rel="subMain"><span>创建报销申请单</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr>
				<th align="center">时间</th>
				<th align="center">分类</th>
				<th align="center">金额</th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="pagination.result" id="obj">
			<tr target="sid" rel="<s:property value="id" />">
			<td>
			<s:property value="expenseTime" />
			</td>
			<td>
			<s:property value="expenseType" />
			</td>
			<td>
			<s:property value="payMoney" />
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
</div>
