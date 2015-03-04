<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<form id="pagerForm" method="post" action="/oa/supplier/viewMaterial.action">
	<input type="hidden" name="pageNum" value="${pagination.currentPage}" />
	<input type="hidden" name="numPerPage" value="${pagination.pageSize}" />
	<input type="hidden" name="supplier.id" value="${supplier.id}" >
	
</form>

<div class="pageContent">
	<table class="table" width="100%" layoutH="50">
		<thead>
			<tr>
				<th width="120" align="center">材料名</th>
				<th width="120" align="center">材料类型</th>
				<th align="center">品牌</th>
				<th align="center">价格</th>
				<!-- <th align="center">种类</th> -->
				<th align="center">单位</th>
				<th align="center">备注</th>
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
