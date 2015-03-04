<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<form id="pagerForm" method="post" action="/bolan/question/list.action">
	<input type="hidden" name="status" value="">
	<input type="hidden" name="keywords" value="" />
	<input type="hidden" name="pageNum" value="${pagination.currentPage}" />
	<input type="hidden" name="numPerPage" value="${pagination.pageSize}" />
	<input type="hidden" name="orderField" value="" />
</form>


<div class="pageHeader">

</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="/bolan/question/edit.action?question.id={sid_obj}" target="dialog" rel="sign"><span>回复</span></a></li>			
			<li><a class="delete" href="/bolan/question/delete.action?question.id={sid_obj}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="90">
		<thead>
			<tr>
			<th align="center">状态</th>
			<th align="center">问题</th>
				<th align="center">顾问</th>
				<th align="center">用户</th>
				
				<th align="center">提问时间</th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="pagination.result" id="obj">
			<tr target="sid_obj" rel="<s:property value="id" />">
			<td>
			<s:if test="#obj.status==0">等待回复</s:if>
			<s:elseif test="#obj.status==1">已回复</s:elseif>
			<s:else>已阅读</s:else>
			</td>
			<td><s:property value="title" /></td>
			<td>
			<s:property value="adviser.name" />
			</td>
			<td><s:property value="user.name" /></td>
			
			<td><s:property value="questionTime" /></td>	
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
