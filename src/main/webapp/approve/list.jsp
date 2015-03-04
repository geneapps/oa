<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<div class="pageHeader">

</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
	<li><a class="add" href="/oa/approve/edit.action" target="dialog"><span>添加</span></a></li>
			<li><a class="edit" href="/oa/approve/edit.action?approve.id={sid_obj}" target="dialog"><span>修改</span></a></li>
			<li><a class="delete" href="/oa/approve/del.action?approve.id={sid_obj}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="250">
		<thead>
			<tr>
				<th width="120" align="center">申请ID</th>
				<th width="120" align="center">申请名称</th>
				<th align="center">申请类型</th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="pagination.result" id="obj">
			<tr target="sid_obj" rel="<s:property value="id" />">
			<td>
			<a href="/oa/approve/appflowlist.action?application.id=<s:property value="id"/>" target="ajax" rel="appFlow">${obj.id}</a>
			</td>
			<td>
			<s:property value="title" />
			</td>
			<td><s:property value="applyType" /></td>
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
	
	<div id="appFlow" class="unitBox">
	</div>

</div>
