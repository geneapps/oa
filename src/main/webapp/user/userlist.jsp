<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<form id="pagerForm" method="post" action="/oa/user/userlist.action">
	<input type="hidden" name="status" value="">
	<input type="hidden" name="keywords" value="" />
	<input type="hidden" name="pageNum" value="${pagination.currentPage}" />
	<input type="hidden" name="numPerPage" value="${pagination.pageSize}" />
	<input type="hidden" name="orderField" value="" />
	<input type="hidden" name="searchType" value="${searchType}" />
	<input type="hidden" name="searchKey" value="${searchKey}" />
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="/oa/user/userlist.action" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
<!-- 			
			<td>
				<label>员工编号</label>
				<input type="text" name="user.userNo"/>
			</td> -->
			
			<td>
				<label>员工姓名</label>
				<input type="text" name="user.realName"/>
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
			<li><a class="add"  href="/oa/user/addUser.jsp" target="navTab" rel="subMain"><span>创建用户</span></a></li>			
			<li><a class="edit" href="/oa/organization/modifyuser.action?user.id={objId}" target="dialog" rel="modifyuser"><span>修改员工信息</span></a></li>
			<li><a class="delete" href="/oa/user/deleteuser.action?user.id={objId}" target="ajaxTodo" title="确定要 删除该员工吗?"><span>删除员工</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr>
				<th width="12" align="center">员工号</th>
				<th width="12" align="center">员工姓名</th>
				<th width="10" align="center">年龄</th>
				<th width="15" align="center">联系方式</th>
				<th width="20" align="center">地址</th>
				<th width="10" align="center">部门</th>
				<th width="10" align="center">岗位</th>
			</tr>
		</thead>
		<tbody>

		<s:iterator value="pagination.result" id="obj">
			<tr target="objId" rel="<s:property value="id" />">			
			<td>
			<s:property value="userNo" />
			</td>
			<td>
			<s:property value="realName" />
			</td>
			<td>
			<s:property value="age" />
			</td>
			<td>
			<s:property value="mobile" />
			</td>
			<td>
			<s:property value="address" />
			</td>
			<td>
			<s:property value="role.name" />
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
