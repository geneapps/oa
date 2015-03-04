<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<div class="pageHeader">

</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
	<li><a class="add" href="/oa/purchasePlanDetails/edit.action" target="dialog"><span>添加</span></a></li>
			<li><a class="edit" href="/oa/purchasePlanDetails/edit.action?purchasePlanDetails.id={sid_obj}" target="dialog"><span>修改</span></a></li>
			<li><a class="delete" href="/oa/purchasePlanDetails/del.action?purchasePlanDetails.id={sid_obj}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="250">
		<thead>
			<tr>
				<th width="100" align="center">品类</th>
				<th width="100" align="center">型号</th>
				<th align="center" width="100">单位</th>
				<th width="100" align="center">库房数量</th>
				<th align="center" width="60">数量</th>
				<th width="60" align="center">预算单价</th>
				<th width="60" align="center">采购计划编号</th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="pagination.result" id="obj">
			<tr target="sid_obj" rel="<s:property value="id" />">
			<td>
			${obj.category}
			</td>
			<td>
			<s:property value="type" />
			</td>
			<td><s:property value="unit" /></td>
			<td><s:property value="houseNumber" /></td>
			<td><s:property value="number" /></td>
			<td><s:property value="budgetPrice" /></td>
			<td><s:property value="purchasePlan.id" /></td>
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
