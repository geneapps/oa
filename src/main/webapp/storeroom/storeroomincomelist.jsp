<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<form id="pagerForm" method="post" action="/oa/storeroom/storeroomincomelist.action">
	<input type="hidden" name="pageNum" value="${pagination.currentPage}" />
	<input type="hidden" name="numPerPage" value="${pagination.pageSize}" />
</form>
<div class="pageContent">

	  <div class="pageFormContent"> 
	   
	          <dl class="nowrap">
					<table class="table"  width="100%" layoutH="280">
						<thead>
							<tr>
								<th align="center">采购单编号</th>
								<th align="center">所属项目</th>
								<th align="center">采购名称</th>
								<!-- <th align="center">材料名称</th> -->
								<!-- <th align="center">入库数量</th> -->
								<!-- <th align="center">入库时间</th> -->
								<th align="center">采购人</th>
							</tr>
						</thead>
						<tbody>
						<s:iterator value="pagination.result">
						
						<tr class="unitBox"  target="sid_obj" rel="<s:property value="id" />">
						<td>
						<a href="/oa/storeroom/materiallist.action?purchaseApply.id=<s:property value='applyNo'/>" target="ajax" rel="appFlow">
						<s:property value='applyNo'/>
						</a>						
						</td>
						<td>
						<s:property value='user.department.depName'/>
						</td>
						<td>
						<s:property value='title'/>
						</td>
						<%-- <td>
						<s:property value='material.materialName'/>
						</td>
						<td>
						<s:property value='number'/>
						</td>
						<td>
						<s:property value='logTime'/>
						</td> --%>
						<td>
						<s:property value='user.realName'/>
						</td>
					
						</tr>
						</s:iterator>
						
						</tbody>
					</table>
			</dl>	
</div>
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
