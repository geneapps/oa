<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<form id="pagerForm" method="post" action="/oa/project/projectlist.action">
	<input type="hidden" name="status" value="">
	<input type="hidden" name="keywords" value="" />
	<input type="hidden" name="pageNum" value="${pagination.currentPage}" />
	<input type="hidden" name="numPerPage" value="${pagination.pageSize}" />
	<input type="hidden" name="orderField" value="" />
	<input type="hidden" name="searchType" value="${searchType}" />
	<input type="hidden" name="searchKey" value="${searchKey}" />
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="/oa/project/projectlist.action" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
				<label>项目名称</label>
				<input type="text" name="project.name"/>
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
			<li><a class="add" href="/oa/approve/editprojectapp.action" target="dialog" rel="projectTab"><span>创建项目</span></a></li>			
			<li><a class="edit" href="/oa/approve/editprojectapp.action?project.id={sid}" target="dialog" rel="projectTab"><span>编辑项目</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr>
				<th align="center">项目名称</th>
				<th align="center">项目状态</th>
				<th align="center">项目经理</th>
				<th align="center">实施部门</th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="pagination.result" id="obj">
			<tr target="sid" rel="<s:property value="id" />">
			<td>
			<s:property value="name" />
			</td>
			<td>
			<s:if test="#obj.status == 1">正常</s:if>
			<s:elseif test="#obj.status == 2">保修</s:elseif>
			<s:elseif test="#obj.status == 3">结束</s:elseif>
			<s:elseif test="#obj.status == 4">终止</s:elseif>
			<s:else>待审批</s:else>
			</td>
			<td>
			<s:property value="manager.realName" />
			</td>
			<td>
			<s:property value="department.depName" />
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
</div>
