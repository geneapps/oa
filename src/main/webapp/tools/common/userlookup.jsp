<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<form id="pagerForm" action="/oa/common/userlookup.action">
	<input type="hidden" name="pageNum" value="${pagination.currentPage}" />
	<input type="hidden" name="numPerPage" value="${pagination.pageSize}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" method="post" action="/oa/common/lookup.action" onsubmit="return dwzSearch(this, 'dialog');">
	<div class="searchBar">
		<ul class="searchContent">  
			<li>
				<label>员工姓名:</label>
				<input class="textInput" name="user.realName" value="" type="text">
			</li>
		    
		</ul>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">

	<table class="table" layoutH="118" targetType="dialog" width="100%">
		<thead>
			<tr>
				<th orderfield="userNo">员工编号</th>
				<th orderfield="realName">员工姓名</th>
				<th orderfield="mobile">联系方式</th>
				<th orderfield="department.depName">所属部门</th>
				<th orderfield="role.name">职务</th>
				<th width="80">选择</th>
			</tr>
		</thead>
		<tbody>
		
			<s:iterator value="pagination.result" id="user">
			<tr>
			<td><s:property value="userNo" /></td>
			<td><s:property value="realName" /></td>
			<td><s:property value="mobile" /></td>
			<td><s:property value="department.depName" /></td>
			<td><s:property value="role.name" /></td>
			<td>
				<a class="btnSelect" href="javascript:$.bringBack({id:'<s:property value="id" />',realName:'<s:property value="realName" />', mobile:'<s:property value="mobile" />', 'department.depName':'<s:property value="department.depName" />', 'role.name':'<s:property value="role.name" />' })" title="选择">选择</a>
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
		<div class="pagination" targetType="dialog" totalCount="${pagination.totalCount}" numPerPage="${pagination.pageSize}" pageNumShown="${pagination.pageCount}" currentPage="${pagination.currentPage}"></div>
	</div>
</div>