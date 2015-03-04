<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<div class="pageHeader">

</div>
<div class="pageContent">
	<table class="table" width="100%" layoutH="250">
		<thead>
			<tr>
				<th width="100" align="center">品类</th>
				<th width="100" align="center">型号</th>
				<th align="center" width="100">单位</th>
				<th align="center" width="60">数量</th>
				<th width="60" align="center">预算单价</th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="purchasePlandetails" id="obj">
			<tr target="sid_obj" rel="<s:property value="id" />">
			<td>
			${obj.category}
			</td>
			<td>
			<s:property value="type" />
			</td>
			<td><s:property value="unit" /></td>>
			<td><s:property value="number" /></td>
			<td><s:property value="budgetPrice" /></td>
			</tr>
		</s:iterator>


		</tbody>
	</table>
	
</div>
