<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<form id="pagerForm" action="/oa/organization/edituser.action">
	<input type="hidden" name="pageNum" value="${pagination.currentPage}" />
	<input type="hidden" name="numPerPage" value="${pagination.pageSize}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
	<input type="hidden" name="role.id" value="${role.id}" /> 
	<input type="hidden" name="department.id" value="${department.id}" /> 
</form>


<div class="pageHeader">
	<form id="pagerForm" method="post" action="/oa/organization/edituser.action" class="pageForm required-validate" onsubmit="return dwzSearch(this, 'dialog');">
	
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>员工姓名:</label>
				 <input class="textInput" name="user.realName" value="" type="text">
				 <input type="hidden" name="role.id" value="${role.id}" /> 
				 <input type="hidden" name="department.id" value="${department.id}" /> 
				 </li>
			</ul>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">查询</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</form>
<form id="pagerForm" method="post" action="/oa/organization/edituser.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
        <div>
			<table class="table" targetType="dialog" layoutH="142" width="100%">
				<thead>
					<tr>
						<th orderfield="userNo">员工编号</th>
						<th orderfield="realName">员工姓名</th>
						<th orderfield="age">员工年龄</th>
						<th orderfield="mobile">联系方式</th>
						<th orderfield="role.name">岗位</th>
						<th orderfield="department.depName">岗位所在部门</th>
						<th >选择</th>
					</tr>
				</thead>
				<tbody>

					<s:iterator value="pagination.result" id="user">
						<tr target="user" rel="<s:property value="id" />">
							<td><s:property value="userNo" />
							</td>
							<td><s:property value="realName" />
							</td>
							<td><s:property value="age" />
							</td>
							<td><s:property value="mobile" />
							</td>
							<td><s:property value="role.name" />
							</td>
							<td><s:property value="department.depName" />
							</td>
							<td><input type="checkbox" name="ids" value="<s:property value='id' />"></td>
						</tr>
					</s:iterator>

				</tbody>
			</table>
			</div>
			
	<div class="panelBar">
		<div class="pages">
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
	
			<div class="formBar">
			<ul>
				<input type="hidden" name="formAction" value="save" />
	<input type="hidden" name="role.id" value="${role.id}" /> 
	<input type="hidden" name="department.id" value="${department.id}" /> 
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">添加</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	 
		

		
	</form>
</div>

