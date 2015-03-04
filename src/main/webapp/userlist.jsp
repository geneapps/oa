<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<form id="pagerForm" method="post" action="userlist.html">
	<input type="hidden" name="status" value="${param.status}">
	<input type="hidden" name="keywords" value="${param.keywords}" />
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${model.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="demo_page1.html" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					用户姓名：<input type="text" name="keyword" />
				</td>
				<td>
					<select class="combox" name="province">
						<option value="">所有用户</option>
						<option value="北京">系统管理员</option>
						<option value="上海">研究员</option>
						<option value="天津">投资顾问</option>
						<option value="重庆">普通用户</option>
						<option value="广东">试用用户</option>
					</select>
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
			<li><a class="add" href="demo_page4.html" target="navTab"><span>注册</span></a></li>			
			<li><a class="edit" href="demo_page4.html?uid={sid_user}" target="navTab"><span>修改</span></a></li>
			<li><a class="delete" href="demo/common/ajaxDone.html?uid={sid_user}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="80">用户标识</th>
				<th width="120">注册名</th>
				<th>客户姓名</th>
				<th>邮箱</th>
				<th width="40" align="center">性别</th>
				<th width="100">客户类型</th>
				<th width="100" align="center">手机号码</th>
				<th width="80">注册日期</th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="users" status="rowstatus">
			<tr target="<s:property value="id" />" rel="<s:property value="id" />">
			<td><s:property value="id" /></td>
			<td><s:property value="name" /></td>
			<td><s:property value="realName" /></td>
			<td><s:property value="email" /></td>
			<td><s:property value="sex" /></td>
			<td><s:property value="userType" /></td>
			<td><s:property value="mobile" /></td>
			<td><s:property value="fullName" /></td>
			<td><s:property value="lastLogin" /></td>
			</tr>
		</s:iterator>


		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20">20</option>
				<option value="50">50</option>
			</select>
			<span>条，共${page.totalCount}条</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="${pagination.totalCount}" numPerPage="${pagination.numPerPage}" pageNumShown="${pagination.totalPage}" currentPage="${pagination.currentPage}"></div>

	</div>
</div>
