<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
	<table class="table" width="100%" layoutH="352">
		<thead>
			<tr>
				<th width="200" align="center">URL</th>
				<th width="200" align="center">描述</th>
			</tr>
		</thead>
		<tbody>

		<s:iterator value="oaAction.urls" id="obj">
			<tr target="objId" rel="<s:property value="id" />">			
			<td>
				<s:property value="url" />
			</td>
			<td>
			<s:property value="description" />
			</td>
			</tr>
		</s:iterator>


		</tbody>
	</table>
