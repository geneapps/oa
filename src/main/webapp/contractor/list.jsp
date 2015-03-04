<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<form id="pagerForm" method="post" action="/oa/contractor/contractorlist.action">
	<input type="hidden" name="pageNum" value="${pagination.currentPage}" />
	<input type="hidden" name="numPerPage" value="${pagination.pageSize}" />
</form>



<div class="pageContent">
<div class="panelBar">
		<ul class="toolBar">
			<li><a class="edit" href="/oa/contractor/viewWorker.action?contractor.id={sid_obj}" target="navTab"><span>查看工人</span></a></li>
			<li><a class="edit" href="/oa/contractor/editContractor.action?contractor.id={sid_obj}" target="dialog"><span>编辑分包商信息</span></a></li>
		</ul>
	</div>


	<table class="table" width="100%" layoutH="76">
		<thead>
			<tr>
				<th width="120" align="center">分包商名称</th>
				<th width="120" align="center">包工头</th>
				<th width="120" align="center">联系方式</th>
				<th width="120" align="center">合作项目</th>
				<th width="120" align="center">信誉等级</th>
				
			</tr>
		</thead>
		<tbody>
		<s:iterator value="pagination.result" id="obj">
			<tr target="sid_obj" rel="<s:property value="id" />">
				<td><s:property value="contractorName"	/></td>				
	            <td><s:property value="contact" /></td>
	            <td><s:property value="phone" /></td>
	            <td><s:property value="project.name" /></td>
	            <td><s:property value="credit" /></td>
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
