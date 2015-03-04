<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<form id="pagerForm" method="post" action="/oa/borrowmoney/borrowmoneylist.action">
	<input type="hidden" name="pageNum" value="${pagination.currentPage}" />
	<input type="hidden" name="numPerPage" value="${pagination.pageSize}" />
</form>


<div class="pageHeader">

</div>
<div class="pageContent">
<div class="panelBar">
		<ul class="toolBar">
			<li><a class="edit" href="/oa/borrowmoney/view.action?borrowMoney.id={sid_obj}" target="navTab" rel="subMain"><span>查看</span></a></li>
		</ul>
	</div>

	<table class="table" width="100%" layoutH="102">
		<thead>
			<tr>
				<th width="120" align="center">借款标题</th>
				<th align="center" width="120">借款类型</th>
				<th width="30" align="center">借款金额</th>
				<th align="center" width="120">借款日期</th>
				<th align="center" width="120">借款缘由</th>
				<th align="center" width="120">提交时间</th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="pagination.result" id="obj">
			<tr target="sid_obj" rel="<s:property value="id" />">
				<td>
				<a href="/oa/borrowmoney/view.action?borrowMoney.id=${obj.id}" target="navTab" rel="viewMain">${obj.borrowTitle}</a>
				</td>				
	           <td><s:if test="#obj.borrowType == 1">材料采购</s:if>
				<s:elseif test="#obj.borrowType == 2">生活费</s:elseif> 
				<s:elseif test="#obj.borrowType == 3">招待费</s:elseif> 
				<s:elseif test="#obj.borrowType == 4">其他</s:elseif> 
				</td>
	            <td><s:property value="borrowMoney" /></td>
	            <td><s:property value="borrowTime" /></td>
	            <td><s:property value="borrowReason" /></td>
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
