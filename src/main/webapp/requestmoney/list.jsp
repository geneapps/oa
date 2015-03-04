<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>

<form id="pagerForm" method="post" action="/oa/requestmoney/requestmoneylist.action">
	<input type="hidden" name="pageNum" value="${pagination.currentPage}" />
	<input type="hidden" name="numPerPage" value="${pagination.pageSize}" />
</form>



<div class="pageHeader">
<%--  <script type="text/javascript" src="/oa/src/main/webapp/js/jquery-1.7.2.js"></script> --%>
</div>
<div class="pageContent">
<div class="panelBar">
		<ul class="toolBar">
			<li><a class="edit" href="/oa/requestmoney/view.action?requestMoney.id={sid_obj}" target="navTab" rel="subMain"><span>查看</span></a></li>
		</ul>
	</div>

	<table class="table" id="resultTb1" width="100%" layoutH="102"  >
		<thead>
			<tr>
				<th width="120" align="center">请款申请标题</th>
				<th align="center" width="120">请款类型</th>
				<th width="120" align="center">请款金额</th>
				<th align="center" width="120">付款时间</th>
				<th align="center" width="120">请款缘由</th>
				<th align="center" width="120">申请时间</th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="pagination.result" id="obj">
						<tr target="sid_obj" rel="<s:property value="id" />">
	            <td>
				<%-- <a href="/oa/requestmoney/view.action?requestMoney.id=${obj.id}" target="navTab" rel="viewMain"> --%>${obj.requestTitle}<!-- </a> -->
				</td>	
				<td><s:if test="#obj.requestType == 'REQUESTMONEYENGINEER' ">工程请款</s:if>
				<s:else>材料请款</s:else> </td>
	            <td><s:property value="requestMoney" /></td>
	            <td><s:property value="payTime" /></td>
	            <td><s:property value="reason" /></td>
	            <td><s:property value="applyTime" /></td>
			</tr>
		</s:iterator>


		</tbody>
	</table>

		<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20" <s:if test="pagination.pageSize ==20">selected</s:if>>20</option>
				<option value="50" <s:if test="pagination.pageSize ==50">selected</s:if>>50</option>
			</select>
			<span>条，共${pagination.totalCount}条</span>
		</div>		
		<div class="pagination" targetType="navTab" totalCount="${pagination.totalCount}" numPerPage="${pagination.pageSize}" pageNumShown="${pagination.pageCount}" currentPage="${pagination.currentPage}"></div>
	</div>
	
</div>
