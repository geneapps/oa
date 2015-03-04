<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
	<table class="table" width="100%" layoutH="412">
		<thead>
			<tr>
				<th width="200" align="center">付款时间</th>
				<th width="200" align="center">付款金额</th>
				<th width="200" align="center">操作人</th>
			</tr>
		</thead>
		<tbody>

		<s:iterator value="paymentOrder.paymentLogs" id="obj">
			<tr target="objId" rel="<s:property value="id" />">			
			<td>
				<s:property value="payTime" />
			</td>
			<td>
			<s:property value="payAmount" />
			</td>
			<td>
			<s:property value="user.realName" />
			</td>
			</tr>
		</s:iterator>


		</tbody>
	</table>
