<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="page">
<div class="pageContent">
<form action="/oa/instore/editinstorehouse.action" method="post"
                 class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">
			
	  <div class="pageFormContent" layoutH="50"> 
	   
	         <dl class="nowrap">
	            <dt>
	            <label>采购单编号</label>
	            </dt>
	            <input name="instoreHouse.id" type="hidden" value="${instoreHouse.id}" />
	           
	            <input name="instoreHouse.purchaseApply.id" type="text" size="50" value="${instoreHouse.purchaseApply.id}" class="required"/>
	          </dl>
	          
	          <dl class="nowrap">
	            <dt>
	            <label>供货商</label>
	            </dt>
                 <input name="instoreHouse.supplier.name" type="text" size="50" value="${instoreHouse.supplier.name}" class="required"/>
	          </dl>
	           
	            <dl class="nowrap">
	            <dt>
	            <label>采购员</label>
	            </dt>
	           <input name="instoreHouse.user.name" type="text" size="50" value="${instoreHouse.user.name}" class="required"/>
	          </dl>
	
         <dl class="nowrap">
	          <dt>  <label style="font-size: 14px;">采购清单</label></dt>
	          </dl>
	          <dl class="nowrap">
	             
					<table class="list nowrap itemDetail" width="100%">
						<thead>
							<tr>
								<th>品类</th>
								<th>型号</th>
								<th >单位</th>
								<th >实际购买价格</th>
								<th >购买数量</th>
							</tr>
						</thead>
						<tbody>
						<s:iterator value="purchaseApply.purchaseApplyDetails" id="obj" status='st'>
						<tr class="unitBox">
						
						<td>
						<input class="required textInput" type="text" size="12" value="<s:property value='category'/>" name="list[<s:property value='#st.index'/>].category">
						</td>
						<td>
						<input class="textInput" type="text" size="12" value="<s:property value='type'/>" name="list[<s:property value='#st.index'/>].type">
						</td>
						<td>
						<input class="textInput" type="text" size="12" value="<s:property value='unit'/>" name="list[<s:property value='#st.index'/>].unit">
						</td>
						<td>
						<input class="number textInput" type="text" size="12"  name="list[<s:property value='#st.index'/>].actualPrice">
						</td>
						
						<td>
						<input class="digits textInput" type="text" size="12"  name="list[<s:property value='#st.index'/>].houseNumber">
						</td>
						
				
						</tr>
						</s:iterator>
						
				
						</tbody>
					</table>
			</dl>			
	   
			<div class="formBar">
				<ul>

					<input type="hidden" name="formAction" value="save" />
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">添加</button>
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
</div>