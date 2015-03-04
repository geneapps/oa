<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<form id="pagerForm" action="/bolan/user/lookup.action">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${model.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" method="post" action="/bolan/advisersign/lookup.action" onsubmit="return dwzSearch(this, 'dialog');">
	<div class="searchBar">
		<div class="subBar">
			<ul>
				<li><div class="button"><div class="buttonContent"><button type="button" multLookup="userId" warn="请选择用户">选择</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">

	<table class="table" layoutH="118" targetType="dialog" width="100%">
		<thead>
			<tr>			
			    <th width="30"><input type="checkbox" class="checkboxCtrl" group="userId" /></th>	
				<th orderfield="name" align="center">注册名</th>
				<th orderfield="realName" align="center">客户姓名</th>
				<th orderfield="sex" align="center">性别</th>
				<th orderfield="endTime" align="center">服务结束时间</th>
			</tr>
		</thead>
		<tbody>
		
		<s:iterator value="pagination.result" id="obj">
			<tr>
			<td><input type="checkbox" name="userId" value="{senderStr:'<s:property value="user.name" />', realName:'<s:property value="user.realName" />'}"/></td>
			<td><s:property value="user.name" /></td>
			<td><s:property value="user.realName" /></td>	
			<td>
			<s:if test="#obj.user.sex==1">男</s:if>
			<s:else>女</s:else>
			</td>
			<td><s:property value="endTime" /></td>
			</tr>
		</s:iterator>	
		</tbody>
	</table>

	<div class="panelBar">
		<div class="pages">
			<span>每页</span>

			<select name="numPerPage" onchange="dwzPageBreak({targetType:dialog, numPerPage:'20'})">
				<option value="20" <s:if test="pagination.pageSize == 20">selected</s:if>>20</option>
				<option value="50" <s:if test="pagination.pageSize == 50">selected</s:if>>50</option>
			</select>
			<span>条，共${pagination.totalCount}条</span>
		</div>
		<div class="pagination" targetType="dialog" totalCount="${pagination.totalCount}" numPerPage="${pagination.pageSize}" pageNumShown="${pagination.pageCount}" currentPage="${pagination.currentPage}"></div>
	</div>
</div>