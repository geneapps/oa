<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<div class="pageHeader">

</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="edit" href="/oa/storeroom/materialout.action" target="dialog" rel="materialouting"><span>材料出库</span></a></li>
			<li><a class="edit" href="/oa/storeroom/turnstoreroom.action" target="dialog"><span>转库出库</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="80">
		<thead>
			<tr>
				<th  align="center">库房名</th>
				<th  align="center">管理员</th>
				<th align="center">所属项目</th>
				<th align="center">创建时间</th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="list" id="obj">
			<tr target="sid_obj" rel="<s:property value="id" />">
			<td>
			<a href="#">${name}</a>
			</td>
			<td>
			<s:property value="user.realName" />
			</td>
			<td><s:property value="project.name" /></td>
			<td><s:property value="createTime" /></td>
			</tr>
		</s:iterator>


		</tbody>
	</table>

</div>
