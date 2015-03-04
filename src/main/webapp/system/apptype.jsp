<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="pageContent">
	<div class="panelBar">
	</div>
	<table class="table" width="100%" layoutH="322">
		<thead>
			<tr>
				<th>审批流程ID</th>
				<th>审批说明</th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="appTypes" id="obj">
			<tr target="sid" rel="${obj[0]}">
			<td>
			<a href="/oa/rolemanage/flowlist.action?appType=${obj[0]}" target="ajax" rel="flowList">
			${obj[0]}
			</a>
			</td>
			<td>
			${obj[1]}
			</td>
			</tr>
		</s:iterator>

		</tbody>
	</table>
	
	<div id="flowList" class="unitBox">
	</div>
</div>
