<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<form id="pagerForm" method="post" action="/oa/storeroom/stocklist.action">
	<input type="hidden" name="pageNum" value="${pagination.currentPage}" />
	<input type="hidden" name="numPerPage" value="${pagination.pageSize}" />
</form>
<div class="pageContent">

	  <div class="pageFormContent" layoutH="50"> 
	   
	          <dl class="nowrap">
	             
					<table class="table" width="100%" >
						<thead>
							<tr>
								<th align="center">所属库房</th>
								<th align="center">材料名称</th>
								<th align="center">型号</th>
								<th align="center">单位</th>
								<th align="center">库房数量</th>

							</tr>
						</thead>
						<tbody>
						<s:iterator value="pagination.result">
						
						<tr class="unitBox">
						<td>
						<s:property value='storeRoom.name'/>
						</td>
						<td>
						<s:property value='material.materialName'/>						
						</td>
						<%-- <td>
						<s:property value='material.category'/>
						</td> --%>
						<td>
						<s:property value='material.materialType'/>
						</td>
						<td>
						<s:property value='material.unit'/>
						</td>
						<td>
						<s:property value='number'/>
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
	</div>
