<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<form id="pagerForm" action="/oa/common/storeRoomMateriallookup.action">
	<input type="hidden" name="pageNum" value="${pagination.currentPage}" />
	<input type="hidden" name="numPerPage" value="${pagination.pageSize}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

 <div class="pageHeader">
	<form rel="pagerForm" method="post" action="/oa/common/storeRoomMateriallookup.action" onsubmit="return dwzSearch(this, 'dialog');">
	<div class="searchBar">
		<ul class="searchContent">
				
			<li>
				<label>材料名:</label>
				<input class="textInput" name="material.materialName" value="" type="text">
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
				<th orderfield="materialName">材料名</th>
				<th orderfield="materialType">型号</th>				
				<th orderfield="unit">单位</th>
				<th orderfield="number">库存数量</th>
				<th width="80">选择</th>
			</tr>
		</thead>
		<tbody>
		
			<s:iterator value="pagination.result" id="material">
			<tr>
			<td><s:property value="material.materialName" /></td>
			<td><s:property value="material.materialType" /></td>			
			<td><s:property value="material.unit" /></td>
			<td><s:property value="number" /></td>
			<td>
				<a class="btnSelect" href="javascript:$.bringBack({'id':'<s:property value="id" />', 'material.id':'<s:property value="material.id" />', 'material.materialType':'<s:property value="material.materialType" />', 'material.materialName':'<s:property value="material.materialName" />', 'material.unit':'<s:property value="material.unit" />', number:'<s:property value="number" />'})" title="选择">选择</a>
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