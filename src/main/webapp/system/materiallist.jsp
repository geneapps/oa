<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<form id="pagerForm" method="post" action="/oa/material/list.action">
	<input type="hidden" name="status" value="">
	<input type="hidden" name="keywords" value="" />
	<input type="hidden" name="pageNum" value="${pagination.currentPage}" />
	<input type="hidden" name="numPerPage" value="${pagination.pageSize}" />
	<input type="hidden" name="orderField" value="" />
	<input type="hidden" name="searchType" value="${searchType}" />
	<input type="hidden" name="searchKey" value="${searchKey}" />
</form>

<div class="pageHeader">
    <form onsubmit="return navTabSearch(this);" action="/oa/material/materiallookup.action" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
				<label>材料名称</label>
				<input type="text" name="material.materialName"/>
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
		<ul class="toolBar">
			<li><a class="add" href="/oa/material/import.action" target="dialog"><span>批量导入</span></a></li>
			<li><a class="add" href="/oa/material/edit.action" target="dialog"><span>添加</span></a></li>
			<li><a class="edit" href="/oa/material/edit.action?material.id={sid_obj}" target="dialog"><span>修改</span></a></li>
			<li><a class="delete" href="/oa/material/delmaterial.action?material.id={sid_obj}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>

		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr>
				<th width="120">材料名</th>
				<th width="120" align="center">材料类型</th>
				<th align="center">品牌</th>
				<th align="center">价格</th>
				<!-- <th align="center">种类</th> -->
				<th align="center">单位</th>
				<th align="center">备注</th>
				<th align="center">供应商</th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="pagination.result" id="obj">
			<tr target="sid_obj" rel="<s:property value="id" />">
			<td>
			<s:property value="materialName" />
			</td>
			<td>
			<s:property value="materialType" />
			</td>
			<td><s:property value="brand" /></td>
			<td><s:property value="price" /></td>
			<%-- <td><s:property value="category" /></td> --%>
			<td><s:property value="unit" /></td>
			<td><s:property value="remark" /></td>
			<td><s:property value="supplier.supplyName" /></td>
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
		<div class="pagination" targetType="navTab" totalCount="${pagination.totalCount}" numPerPage="${pagination.pageSize}" pageNumShown="20" currentPage="${pagination.currentPage}"></div>
	</div>

</div>
