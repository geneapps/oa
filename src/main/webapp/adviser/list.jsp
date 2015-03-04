<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<form id="pagerForm" method="post" action="/bolan/advisersign/list.action">
	<input type="hidden" name="status" value="">
	<input type="hidden" name="keywords" value="" />
	<input type="hidden" name="pageNum" value="${pagination.currentPage}" />
	<input type="hidden" name="numPerPage" value="${pagination.pageSize}" />
	<input type="hidden" name="orderField" value="" />
	<input type="hidden" name="searchType" value="${searchType}" />
	<input type="hidden" name="searchKey" value="${searchKey}" />
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="/bolan/advisersign/list.action" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					<select name="searchType" class="required combox">
					<option value="adviser_name" <s:if test="searchType=='adviser_name'">selected</s:if>>投顾</option>
					<option value="adviser_realName" <s:if test="searchType=='adviser_realName'">selected</s:if>>投顾姓名</option>
					<option value="adviser_mobile" <s:if test="searchType=='adviser_mobile'">selected</s:if>>投顾手机号</option>
					<option value="user_name" <s:if test="searchType=='user_name'">selected</s:if>>客户</option>
					<option value="user_realName" <s:if test="searchType=='user_realName'">selected</s:if>>客户姓名</option>
					<option value="user_mobile" <s:if test="searchType=='user_mobile'">selected</s:if>>客户手机号</option>
					</select>
				<input type="text" name="searchKey" value="${searchKey}"/>
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
			<li><a class="add" href="/bolan/advisersign/edit.action" target="dialog" rel="sign"><span>签约</span></a></li>			
			<li><a class="edit" href="/bolan/advisersign/edit.action?adviserSign.id={sid_user}" target="dialog" rel="sign"><span>延长服务</span></a></li>
			<li><a class="delete" href="/bolan/advisersign/delete.action?adviserSign.id={sid_user}" target="ajaxTodo" title="确定要取消签约吗?"><span>取消签约</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr>
				<th align="center">顾问</th>
				<th align="center">顾问姓名</th>
				<th align="center">顾问手机号</th>
				<th align="center">客户</th>
				<th align="center">客户姓名</th>
				<th align="center">客户手机号</th>
				<th align="center">签约时间</th>
				<th align="center">服务结束时间</th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="pagination.result" id="obj">
			<tr target="sid_user" rel="<s:property value="id" />">
			<td>
			<s:property value="adviser.name" />
			</td>
			<td>
			<s:property value="adviser.realName" />
			</td>
			<td>
			<s:property value="adviser.mobile" />
			</td>
			<td><s:property value="user.name" /></td>
			<td><s:property value="user.realName" /></td>
			<td><s:property value="user.mobile" /></td>
			<td><s:property value="signTime" /></td>
			<td><s:property value="endTime" /></td>	
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
