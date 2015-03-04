<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<form id="pagerForm" method="post" action="/oa/expense/expenselist.action">
	<input type="hidden" name="pageNum" value="${pagination.currentPage}" />
	<input type="hidden" name="numPerPage" value="${pagination.pageSize}" />
</form>


<div class="pageContent">
<div class="panelBar">
		<ul class="toolBar">
			<li><a class="edit" href="/oa/expense/view.action?expense.id={sid_obj}" target="navTab" rel="subMain"><span>查看</span></a></li>
		</ul>
	</div>


	<table class="table" width="100%" layoutH="76">
		<thead>
			<tr>
				<th width="120" align="center">标题</th>
				<th align="center" width="120">申请人</th>
				<th width="120" align="center">部门</th>
				<th align="center" width="120">提交时间</th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="pagination.result" id="obj">
			<tr target="sid_obj" rel="<s:property value="id" />">
				<td>
				<a href="/oa/expense/view.action?expense.id=${obj.id}" target="navTab" rel="viewMain">${obj.payTitle}</a>
				</td>				
	            <td><s:property value="user.realName" /></td>
	            <td><s:property value="department.depName" /></td>
	            <td><s:property value="submitTime" /></td>
			</tr>
		</s:iterator>


		</tbody>
	</table>
		<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20" <s:if test="pagination.pageSize ==20">selected</s:if>>20</option>
				<option value="50" <s:if test="pagination.pageSize ==50">selected</s:if>>50</option>
			</select>
			<span>条，共${pagination.totalCount}条</span>
		</div>		
		<div class="pagination" targetType="navTab" totalCount="${pagination.totalCount}" numPerPage="${pagination.pageSize}" pageNumShown="${pagination.pageCount}" currentPage="${pagination.currentPage}"></div>
	</div>
	
</div>
