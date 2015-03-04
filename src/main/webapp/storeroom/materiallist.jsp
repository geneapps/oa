<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="pageContent">

	  <div class="pageFormContent" layoutH="50"> 
	   
	          <dl class="nowrap">
	             
					<table class="table" width="100%">
						<thead>
							<tr>
								<th width="100" align="center">材料名称</th>
								<th width="100" align="center">型号</th>
								<th width="100" align="center">单位</th>
								<th width="100" align="center">采购数量</th>
								<th width="100" align="center">预算单价</th>
								<th width="100" align="center">参考单价</th>
								<th width="100" align="center">实际购买价格</th>
								<th width="100" align="center">入库数量</th>
								
							</tr>
						</thead>
						<tbody>
						<s:iterator value="purchaseApply.purchaseApplyDetails">
						
						<tr class="unitBox">
						<td>
						<s:property value='material.materialName'/>						
						</td>
						<td>
						<s:property value='type'/>
						</td>
						<td>
						<s:property value='unit'/>
						</td>
						<td>
						<s:property value='number'/>
						</td>
						<td>
						<s:property value='budgetPrice'/>
						</td>
						<td>
						<s:property value='refPrice'/>
						</td>
						<td>
						<s:property value='actualPrice'/>
						</td>
						<td>
						<s:property value='houseNumber'/>
						</td>
						
						</tr>
						</s:iterator>
						
						</tbody>
					</table>
			</dl>	
			
	     </div>
			
	</div>
