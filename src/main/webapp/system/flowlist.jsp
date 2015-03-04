<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<form id="pagerForm" method="post" action="/oa/rolemanage/actionlist.action">
	<input type="hidden" name="status" value="">
	<input type="hidden" name="keywords" value="" />
	<input type="hidden" name="pageNum" value="${pagination.currentPage}" />
	<input type="hidden" name="numPerPage" value="${pagination.pageSize}" />
	<input type="hidden" name="orderField" value="" />
	<input type="hidden" name="searchType" value="${searchType}" />
	<input type="hidden" name="searchKey" value="${searchKey}" />
</form>



<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar" id="menu">
			<li><a class="add" href="/oa/rolemanage/modifyflow.action?approveFlowConfig.appType=${appType}" target="dialog" rel="adduser"><span>添加</span> </a></li>
			<li><a class="edit" href="/oa/rolemanage/modifyflow.action?approveFlowConfig.id={sid}" target="dialog" rel="modifyuser"><span>修改</span></a></li>
			<li><a class="delete" href="/oa/rolemanage/delflow.action?approveFlowConfig.id={sid}" target="ajaxTodo" title="确定要移除吗?"><span>移除</span> </a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="212">
		<thead>
			<tr>
			<th>流程ID</th>
			<th>审批顺序</th>
				<th>审批类型</th>
				<th>审批标识</th>
				<th>审批人</th>
				</tr>
		</thead>
		<tbody>
		<s:iterator value="flowConfigList" id="obj">
			<tr target="sid" rel="<s:property value="id" />">
			<td>
			<s:property value="id" />
			</td>
			<td>
			<s:property value="orderBy" />
			</td>
			<td>
			<s:if test="actionType==0">角色</s:if>
			<s:if test="actionType==1">权限</s:if>
			<s:if test="actionType==2">用户</s:if>
			</td>
			<td>
			<s:property value="actionId" />
			</td>
			<td>
			<s:property value="remark" />
			</td>
			
			</tr>
		</s:iterator>

		</tbody>
	</table>
	
	<div id="flowList" class="unitBox">
	</div>
</div>
