<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<form id="pagerForm" action="/oa/common/materiallookup.action">
	<!-- <input type="hidden" name="pageNum" value="1" /> -->
	<input type="hidden" name="pageNum" value="${pagination.currentPage}" />
	<input type="hidden" name="numPerPage" value="${pagination.pageSize}" />
	<%-- <input type="hidden" name="numPerPage" value="${model.numPerPage}" />  --%>
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

 <div class="pageHeader">
	<form rel="pagerForm" method="post" action="/oa/common/materiallookup.action" onsubmit="return dwzSearch(this, 'dialog');">
	<div class="searchBar">
		<ul class="searchContent">
		
			<!-- <li>
				<label>种类:</label>
				<input class="textInput" name="material.category" value="" type="text">
			</li>	 -->		
			<li>
				<label>材料名:</label>
				<input class="textInput" name="material.materialName" value="" type="text">
			</li>
			<li>
				<label>供应商:</label>
				<input class="textInput" name="material.supplier.supplyName" value="" type="text">
			</li> 
			<li>
				<label>规格:</label>
				<input class="textInput" name="material.materialType" value="" type="text">
			</li>
			<li>
				<label>品牌:</label>
				<input class="textInput" name="material.brand" value="" type="text">
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
				<!-- <th orderfield="category">种类</th> -->
				<th orderfield="materialName">材料名</th>
				<th orderfield="brand">品牌</th>
				<th orderfield="materialType">型号</th>				
				<th orderfield="price">单价</th>
				<th orderfield="unit">单位</th>
				<th orderfield="supplier.supplyName">供货商</th>
				<th width="80">选择</th>
			</tr>
		</thead>
		<tbody>
		
			<s:iterator value="pagination.result" id="material">
			<tr>
			<%-- <td><s:property value="category" /></td> --%>
			<td><s:property value="materialName" /></td>
			<td><s:property value="brand" /></td>
			<td><s:property value="materialType" /></td>			
			<td><s:property value="price" /></td>
			<td><s:property value="unit" /></td>
			<td><s:property value="supplier.supplyName" /></td>
			<td>
				<a class="btnSelect" href="javascript:$.bringBack({id:'<s:property value="id" />',category:'<s:property value="category" />', brand:'<s:property value="brand" />', materialType:'<s:property value="materialType" />', materialName:'<s:property value="materialName" />', price:'<s:property value="price" />', unit:'<s:property value="unit" />', 'supplier.supplyName':'<s:property value="supplier.supplyName" />'})" title="选择">选择</a>
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
