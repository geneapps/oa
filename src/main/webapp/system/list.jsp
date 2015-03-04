<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<div class="pageHeader">

</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
	<li><a class="add" href="/oa/system/edit.action" target="dialog"><span>添加</span></a></li>
			<li><a class="edit" href="/oa/system/edit.action?systemParam.id={sid_obj}" target="dialog"><span>修改</span></a></li>
			<li><a class="delete" href="/oa/system/del.action?systemParam.id={sid_obj}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr>
				<th width="120" align="center">参数名称</th>
				<th width="120" align="center">参数主键</th>
				<th align="center">参数值</th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="paramList" id="obj">
			<tr target="sid_obj" rel="<s:property value="id" />">
			<td>
			<s:property value="showName" />
			</td>
			<td>
			<s:property value="paramName" />
			</td>
			<td><s:property value="paramValue" /></td>
			</tr>
		</s:iterator>


		</tbody>
	</table>

</div>
