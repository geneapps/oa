<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<form id="pagerForm" method="post" action="/oa/project/projectreportlist.action">
	<input type="hidden" name="pageNum" value="${pagination.currentPage}" />
	<input type="hidden" name="numPerPage" value="${pagination.pageSize}" />
</form>
<div class="pageHeader">
</div>
<div class="pageContent">
<div class="panelBar">
		<ul class="toolBar">
	<li><a class="edit" href="/oa/project/basicinformation.action?project.id={sid}" target="navTab" rel="mainInfor"><span>基本信息</span></a></li>
	<li><a class="edit" href="/oa/project/projectexpenditures.action?project.id={sid}" target="navTab" rel="proexpend"><span>项目支出</span></a></li>
	<%-- <li><a class="edit" href="/oa/project/projectbudget.action" target="navTab" rel=""><span>项目预算</span></a></li> --%>
	<li><a class="edit" href="/oa/project/projecttobepaid.action?project.id={sid}" target="navTab" rel="tobepaid"><span>待付款</span></a></li>
	<li><a class="edit" href="/oa/project/projectstoreroom.action?project.id={sid}" target="navTab" rel="prostore"><span>项目库房</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="350">
		<thead>
			<tr>
				<th align="center">项目名称</th>
				<th align="center">状态</th>
				<th align="center">项目经理</th>
				<th align="center">联系电话</th>
				<th align="center">实施部门</th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="pagination.result" id="obj">
			<tr target="sid" rel="<s:property value="id" />">
			<td>
			<a href="/oa/project/projectinformationlist.action?project.id=<s:property value="id"/>" target="ajax" rel="projectinformation">${obj.name}</a>
			</td>
			<td>
			<s:if test="#obj.status == 1">正常</s:if>
			<s:elseif test="#obj.status == 2">保修</s:elseif>
			<s:elseif test="#obj.status == 3">结束</s:elseif>
			<s:elseif test="#obj.status == 4">终止</s:elseif>
			<s:else>待审批</s:else>
			</td>
			<td>
			<s:property value="manager.realName" />
			</td>
			<td>
			<s:property value="manager.mobile" />
			</td>
			<td>
			<s:property value="department.depName" />
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
	
</div>

<div layoutH="290" id="projectinformation" class="unitBox" style="float:left; display:block; margin:5px; overflow:auto; width:100%; border:solid 1px #CCC; line-height:21px; background:#fff">
	</div>