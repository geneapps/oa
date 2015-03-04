<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<form id="pagerForm" method="post" action="/oa/supplier/supplierList.action">
	<input type="hidden" name="pageNum" value="${pagination.currentPage}" />
	<input type="hidden" name="numPerPage" value="${pagination.pageSize}" />
</form>



<div class="pageContent">
<div class="panelBar">
		<ul class="toolBar">
			<li><a class="edit" href="/oa/supplier/viewMaterial.action?supplier.id={sid_obj}" target="navTab" rel="materialDetail"><span>查看提供的货品</span></a></li>
			<li><a class="edit" href="/oa/supplier/editSupplier.action?supplier.id={sid_obj}" target="dialog"><span>修改供货商</span></a></li>
		</ul>
	</div>


	<table class="table" width="100%" layoutH="76">
		<thead>
			<tr>
				<th width="120" align="center">供应商名称</th>
				<th align="center" width="120">联系人</th>
				<th width="120" align="center">联系人电话</th>
				<th width="120" align="center">地址</th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="pagination.result" id="obj">
			<tr target="sid_obj" rel="<s:property value="id" />">
				<td>
				<a href="/oa/supplier/viewMaterial.action?supplier.id=${obj.id}" target="navTab" rel="materialDetail">${obj.supplyName}</a>
				</td>				
	            <td><s:property value="contact" /></td>
	            <td><s:property value="phone" /></td>
	            <td><s:property value="address" /></td>
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
	
	<div id="materialDetail" class="unitBox">
	</div>
	
</div>
