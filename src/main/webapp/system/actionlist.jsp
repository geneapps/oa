<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<form id="pagerForm" method="post" action="/oa/rolemanage/actionlist.action">
	<input type="hidden" name="status" value="">
	<input type="hidden" name="keywords" value="" />
	<input type="hidden" name="pageNum" value="${pagination.currentPage}" />
	<input type="hidden" name="numPerPage" value="${pagination.pageSize}" />
	<input type="hidden" name="orderField" value="" />
	<input type="hidden" name="searchType" value="${searchType}" />
	<input type="hidden" name="searchKey" value="${searchKey}" />
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="/oa/rolemanage/actionlist.action" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>

				<input type="text" name="searchKey" value="${searchKey}"/>
				</td>
				<td>
					<button type="submit">查询</button>
				</td>
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
	</div>
	<table class="table" width="100%" layoutH="322">
		<thead>
			<tr>
				<th align="center">操作名称</th>
				<th align="center">操作代码</th>
				<th align="center">操作描述</th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="pagination.result" id="obj">
			<tr target="sid" rel="<s:property value="id" />">
			<td>
			<a href="/oa/rolemanage/actionrul.action?oaAction.id=<s:property value="id"/>" target="ajax" rel="actionUrl">
			<s:property value="name" />
			</a>
			</td>
			<td>
			<s:property value="actionValue" />
			</td>
			<td>
			<s:property value="description" />
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
	
	<div id="actionUrl" class="unitBox">
	</div>
</div>
