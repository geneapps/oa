<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<form id="pagerForm" method="post" action="/oa/storeroom/purchaselist.action">
	<input type="hidden" name="pageNum" value="${pagination.currentPage}" />
	<input type="hidden" name="numPerPage" value="${pagination.pageSize}" />
</form>

<div class="pageHeader">

</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="edit" href="/oa/storeroom/pruchaseincoming.action?purchaseApply.id={sid_obj}" target="navTab" rel="subMain"><span>入库</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="250">
		<thead>
			<tr>
				<th width="100" align="center">状态</th>
				<th width="100" align="center">标题</th>
				<th width="100" align="center">总金额</th>
				<!-- <th align="center" width="100">预支金额</th> -->
				<th width="100" align="center">采购时间</th>
				<th width="100" align="center">申请时间</th>
				<th align="center" width="60">采购人</th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="pagination.result" id="obj">
			<tr target="sid_obj" rel="<s:property value="id" />">
			<td>
				<s:if test="status==0">等待审批</s:if>
				<s:elseif test="status==1">等待付款</s:elseif>
				<s:elseif test="status==2">正在采购</s:elseif>
				<s:elseif test="status==3">等待报销</s:elseif>
				<s:elseif test="status==4">结束</s:elseif>
				<s:elseif test="status==5">审批不通过</s:elseif>
			</td>
			<td>
			${obj.title}
			</td>
			<td>
			<s:property value="totalPrice" />
			</td>
			<%-- <td><s:property value="advancePay" /></td> --%>
			<td><s:property value="prePurchaseTime" /></td>
			<td><s:property value="applyTime" /></td>
			<td><s:property value="user.realName" /></td>
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
