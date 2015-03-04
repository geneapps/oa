<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<form id="pagerForm" method="post" action="/oa/contract/contractList.action">
	<input type="hidden" name="pageNum" value="${pagination.currentPage}" />
	<input type="hidden" name="numPerPage" value="${pagination.pageSize}" />
</form>



<div class="pageContent">
<div class="panelBar">
		<ul class="toolBar">
			<li><a class="edit" href="/oa/contract/view.action?contract.id={sid_obj}" target="navTab" rel="subMain"><span>查看</span></a></li>
		</ul>
	</div>


	<table class="table" width="100%" layoutH="76">
		<thead>
			<tr>
				<th width="120" align="center">标题</th>
				<th align="center" width="120">甲方</th>
				<th width="120" align="center">乙方</th>
				<th align="center" width="120">上传时间</th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="pagination.result" id="obj">
			<tr target="sid_obj" rel="<s:property value="id" />">
				<td>
				<a href="/oa/contract/view.action?contract.id=${obj.id}" target="navTab" rel="viewMain">${obj.title}</a>
				</td>				
	            <td><s:property value="companyA" /></td>
	            <td><s:property value="companyB" /></td>
	            <td><s:property value="updateTime" /></td>
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
	
	<div id="contractDetailbyID" class="unitBox">
	</div>
	
</div>
