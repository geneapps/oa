<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

	<input type="hidden" name="department.id" value="${department.id}" />

	<div class="panelBar">
		<ul class="toolBar" id="menu">

			<li><a class="add" href="/oa/organization/editrole.action?department.id=${department.id}" target="dialog" rel="addrole"><span>增加岗位</span> </a></li>
			<li><a class="edit" href="/oa/organization/editrole.action?role.id={objId}&&department.id=${department.id}" target="dialog" rel="editrole"><span>编辑岗位</span> </a></li>
			<li><a class="delete" href="/oa/organization/delrole.action?role.id={objId}" target="ajaxTodo" title="确定要删除吗?"><span>删除岗位</span> </a></li>
		</ul>
	</div>

	<table class="table" width="100%" layoutH="300">
		<thead>
			<tr>
				<th width="100%" align="center">岗位</th>
			</tr>
		</thead>
		<tbody>

		<s:iterator value="department.roles" id="obj">
			<tr target="objId" rel="<s:property value="id" />">			
			<td>
			<a href="/oa/organization/departmentuserlist.action?role.id=<s:property value="id" />&&department.id=${department.id}" target="ajax" rel="departmentUser">
			<s:property value="name" />
			</a>
			</td>
			</tr>
		</s:iterator>


		</tbody>
	</table>
