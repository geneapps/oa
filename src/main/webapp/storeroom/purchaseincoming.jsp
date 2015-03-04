<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="pageContent">
<form action="/oa/storeroom/pruchaseincoming.action" method="post"
                 class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">
			
	  <div class="pageFormContent" layoutH="50"> 
	   
	         <dl class="nowrap">
	            <dt>
	            <label>采购标题</label>
	            </dt>
	            <input name="purchaseApply.id" type="hidden" value="${purchaseApply.id}" />
	            ${purchaseApply.title}
	          </dl>
	          <div class="divider"></div>
	          <dl class="nowrap">
	            <dt>
	            <label>入库状态</label>
	            </dt>
	            <s:if test="purchaseApply.status==2">入库未完成</s:if>
	            <s:elseif test="purchaseApply.status==3">入库已完成</s:elseif> 
	          </dl>
	          <div class="divider"></div>
	          <dl class="nowrap">
	          <dt>  <label style="font-size: 14px;">采购清单</label></dt>
	          </dl>
	          <dl class="nowrap">
	             
					<table class="list nowrap" width="100%">
						<thead>
							<tr>
								<th>材料名称</th>
								<!-- <th>种类</th> -->
								<th>型号</th>
								<th>单位</th>
								<th>预算单价</th>
								<th>参考单价</th>
								<th>数量</th>
								<th>已经入库</th>
								<th>入库数量</th>
								<th>购买价格</th>
								<th>供应商</th>
							</tr>
						</thead>
						<tbody>
						<s:iterator value="purchaseApply.purchaseApplyDetails" id="obj" status='st'>
						<tr class="unitBox">
						<td>
						<input type="hidden" name="purchaseApplyDetails[<s:property value='#st.index'/>].id" value="${obj.id}">
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
						<s:property value='budgetPrice'/>
						</td>
						<td>
						<s:property value='refPrice'/>
						</td>
						<td>
						<s:property value='number'/>
						</td>
						<td>
						<s:property value='houseNumber'/>
						</td>
						<td>
						<input type="text" name="purchaseApplyDetails[<s:property value='#st.index'/>].houseNumber" >
						</td>
						<td>
						<input type="text" name="purchaseApplyDetails[<s:property value='#st.index'/>].actualPrice" >
						</td>
						<td>
						<s:property value='material.supplier.supplyName'/>
						</td>
						</tr>
						</s:iterator>
						
				
						</tbody>
					</table>
			</dl>			
	      <div class="divider"></div>
         <dl class="nowrap">
	       <dt>
	         <label>采购说明</label>
	       </dt>
	        <textarea name="purchaseApply.instruction" value="${purchaseApply.instruction}" ros="30" cols="60" class="readonly"/>
	     
	      </dl>

</div>
			<div class="formBar">
				<ul>

					<input type="hidden" name="formAction" value="save" />
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">入库</button>
							</div>
						</div>
					</li>
					<li>
						<div class="button">
							<div class="buttonContent">
								<button type="button" class="close">取消</button>
							</div>
						</div></li>
				</ul>
			</div>
		</form>
	</div>
