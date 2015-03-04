<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<form id="pagerForm" action="/oa/common/contractorlookup.action">
	<input type="hidden" name="pageNum" value="${pagination.currentPage}" />
	<input type="hidden" name="numPerPage" value="${pagination.pageSize}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" method="post" action="/oa/common/contractorlookup.action" onsubmit="return dwzSearch(this, 'dialog');">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>分包商:</label>
				<input class="textInput" name="contractor.contractorName" value="" type="text">
			</li>
		</ul>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">

	<table class="table" layoutH="118" targetType="dialog" width="100%">
		<thead>
			<tr>
				<th orderfield="contractorName">分包商</th>
				<th orderfield="contact">负责人</th>
				<th orderfield="phone">联系方式</th>
				<th orderfield="credit">信誉等级</th>				
				<th width="80">选择</th>
			</tr>
		</thead>
		<tbody>
		
			<s:iterator value="pagination.result" id="contractor">
			<tr>
			<td><s:property value="contractorName" /></td>
			<td><s:property value="contact" /></td>
			<td><s:property value="phone" /></td>
			<td><s:property value="credit" /></td>			
			<td>
				<a class="btnSelect" href="javascript:$.bringBack({id:'<s:property value="id" />',contractorName:'<s:property value="contractorName" />', contact:'<s:property value="contact" />', phone:'<s:property value="phone" />', credit:'<s:property value="credit" />'})" title="选择">选择</a>
			</td>
			</tr>
			</s:iterator>	
			
		</tbody>
	</table>
	</div>

	<div class="panelBar">
		<div class="pages">
		<div class="pages">
			<span>每页</span>

			<select name="numPerPage" onchange="dwzPageBreak({targetType:dialog, numPerPage:'20'})">
				<option value="20" <s:if test="pagination.pageSize == 20">selected</s:if>>20</option>
				<option value="50" <s:if test="pagination.pageSize == 50">selected</s:if>>50</option>
			</select>
			<span>条，共${pagination.totalCount}条</span>
		</div>
		<div class="pagination" targetType="dialog" totalCount="${pagination.totalCount}" numPerPage="${pagination.pageSize}" pageNumShown="${pagination.pageCount}" currentPage="${pagination.currentPage}"></div>
	</div>
</div>
