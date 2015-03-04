<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

	<div class="panelBar">
		<ul class="toolBar" id="menu">
			<li><a class="add" href="/oa/organization/edituser.action?role.id=${role.id}&department.id=${department.id}" target="dialog" rel="adduser"><span>添加员工</span> </a></li>
			<%-- <li><a class="edit" href="/oa/organization/modifyuser.action?user.id={objId}" target="dialog" rel="modifyuser"><span>修改员工信息</span></a></li> --%>
			<li><a class="delete" href="/oa/organization/deluser.action?user.id={objId}" target="ajaxTodo" title="确定要移除该员工吗?"><span>移除员工</span> </a></li>
		</ul>
	</div>

	<table class="table" width="100%" layoutH="412">
		<thead>
			<tr>
				<th width="12" align="center">员工号</th>
				<th width="12" align="center">员工姓名</th>
				<th width="10" align="center">年龄</th>
				<th width="15" align="center">联系方式</th>
				<th width="20" align="center">地址</th>
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
			</tr>
		</s:iterator>


		</tbody>
	</table>
