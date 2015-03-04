<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="pageContent">

	  <div class="pageFormContent" layoutH="50"> 
	   
	          
	          <dl class="nowrap">
	             
					<table class="table" width="100%">
						<thead>
							<tr>
							    <th align="center">材料名称</th>
								<th align="center">型号</th>
								<th align="center">单位</th>
								<th align="center">数量</th>
							</tr>
						</thead>
						<tbody>
						<s:iterator value="pagination.result">
						
						<tr class="unitBox"  target="sid_obj" rel="<s:property value="id" />">
						 <td>
						<s:property value='material.materialName'/>						
						</td>
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
			
	</div>
