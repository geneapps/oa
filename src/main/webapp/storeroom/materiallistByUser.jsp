<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="pageContent">

	  <div class="pageFormContent" layoutH="50"> 
	   
	          <dl class="nowrap">
	             
					<table class="list nowrap" width="100%">
						<thead>
							<tr>
								<th>材料名称</th>
								<!-- <th>种类</th> -->
								<th>型号</th>
								<th>单位</th>
								<th>数量</th>
                                <th>出库时间</th>
							</tr>
						</thead>
						<tbody>
						<s:iterator value="pagination.result">
						
						<tr class="unitBox">
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
						<s:property value='unit'/>
						</td>
						<td>
						<s:property value='number'/>
						</td>
						<td>
						<s:property value='logTime'/>
						</td>
						</tr>
						</s:iterator>
						
						</tbody>
					</table>
			</dl>	
			
	     </div>
			
	</div>
