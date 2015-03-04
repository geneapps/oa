<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<form id="pagerForm" method="post" action="/oa/approve/myapplication.action">
	<input type="hidden" name="pageNum" value="${pagination.currentPage}" />
	<input type="hidden" name="numPerPage" value="${pagination.pageSize}" />
</form>

<div class="pageHeader">

</div>
<div class="pageContent" id="approveRel">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="edit" href="/oa/approve/editapp.action?application.id={sid_obj}" target="navTab" rel="editcontract"><span>修改审批申请</span></a></li>
			<li><a class="delete" href="/oa/approve/delapp.action?application.id={sid_obj}" target="ajaxTodo" title="确定要删除吗?"><span>删除审批申请</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="342">
		<thead>
			<tr>
				<th width="100" align="center">申请类型</th>
				<th width="400" align="center">申请名称</th>				
				<th width="100" align="center">经手人</th>
				<th width="100" align="center">申请时间</th>
				<th width="100" align="center">审批状态</th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="pagination.result" id="obj">
			<tr target="sid_obj" rel="<s:property value="id" />">
			<td>
			<s:if test="#obj.applyType == 'PURCHASE'">采购申请</s:if>
			<s:elseif test="#obj.applyType == 'CONTRACT'">材料合同审批</s:elseif>
			<s:elseif test="#obj.applyType == 'MANCONTRACT'">人工合同审批</s:elseif>
			<s:elseif test="#obj.applyType == 'PURCHASEPLAN'">采购计划申请</s:elseif>
			<s:elseif test="#obj.applyType == 'PROJECT'">项目申请</s:elseif>
			<s:elseif test="#obj.applyType == 'REQUESTMONEYMATERIAL'">材料请款申请</s:elseif> 
			<s:elseif test="#obj.applyType == 'REQUESTMONEYENGINEER'">工程请款申请</s:elseif> 
			<s:elseif test="#obj.applyType == 'BORROWMONEY'">借款申请</s:elseif> 
			<s:elseif test="#obj.applyType == 'EXPENSEMAN'">人工费用报销申请</s:elseif> 
			<s:elseif test="#obj.applyType == 'EXPENSEOTHER'">间接费用报销申请</s:elseif> 
			<s:elseif test="#obj.applyType == 'EXPENSEMATERIAL'">材料费用报销申请</s:elseif> 
			<s:elseif test="#obj.applyType == 'ADMIN'">行政申请</s:elseif> 
			<s:else>未知申请</s:else>
			</td>
			<td>
			<a href="/oa/approve/appflowlist.action?application.id=<s:property value="id"/>" target="ajax" rel="appFlow">${obj.title}</a>
			</td>			
			<td><s:property value="user.realName" /></td>
			<td><s:property value="createTime" /></td>
			<td>
				<s:if test="#obj.status == -1">等待审批</s:if>
				<s:elseif test="#obj.status == 1">审批通过</s:elseif>
				<s:elseif test="#obj.status == 2">审批不通过</s:elseif>
				<s:elseif test="#obj.status == 0">正在审批 </s:elseif>
				<s:elseif test="#obj.status == 4">终止</s:elseif>
				<s:else>等待审批</s:else>
			</td>
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
	
	<div id="appFlow" class="unitBox">
	</div>

</div>
