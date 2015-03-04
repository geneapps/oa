<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<div class="pageContent">
<form action="/oa/approve/approvepurchaseapplyapp.action" method="post"
            class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">
	<div class="pageFormContent" layoutH="79"> 
	         <dl class="nowrap">
	            <dt>
	            <label>采购标题</label>
	            </dt>
	            <input name="application.id" type="hidden"	value="${application.id}" />
	            <input name="purchaseApply.id" type="hidden"  value="${purchaseApply.id}" />
	            <input name="application.title" type="text"	value="${application.title}" readonly/> 
	          </dl>
	          
	          <dl class="nowrap">
	            <dt>
	            <label>项目</label>
	            </dt>
	           <input name="user.id" type="hidden"	value="${user.id}" />
	           <input name="user.department.depName" type="text" value="${user.department.depName}" readonly/>
	          </dl>
	       <div class="divider"></div>   
	 <h1>采购清单</h1>
	
		<table class="table" width="100%">
		<thead>
			<tr>
				<th width="120" align="center">材料</th>
				<th width="120" align="center">型号</th>
				<th width="120" align="center">单位</th>
				<th width="120" align="center">品牌</th>
				<th width="120" align="center">参考单价</th>
				<th width="120" align="center">预算单价</th>
				<th width="120" align="center">数量</th>
				<th width="120" align="center">有无合同</th>
				<th width="120" align="center">有无发票</th>
				
			</tr>
		</thead>
		<tbody>
        
 		<s:iterator value="purchaseApply.purchaseApplyDetails" id="obj">
	  		<tr>
			<td><s:property value="material.materialName"/></td>
			<td><s:property value="material.materialType"/></td>
			<td><s:property value="unit" /></td>
			<td><s:property value="brand" /></td>
			<td><s:property value="refPrice" /></td>
			<td><s:property value="budgetPrice" /></td>
			<td><s:property value="number" /></td>			
			<td><s:property value="isContract" /></td>
			<td><s:property value="isBill" /></td>
			</tr>
		</s:iterator>

		</tbody>
	</table>
				
	
	  <%-- <dl class="nowrap">
	   <dt>
	    <label>预算采购总价</label>
	  </dt>
	     ${purchaseApply.totalPrice}
	  </dl>
	     
	   <dl class="nowrap">
	   <dt>
	     <label>预付款</label>
	   </dt>
	      ${purchaseApply.advancePay}
	   </dl> --%>
	<div class="divider"></div> 
     <dl class="nowrap">
	     <dt>
	      <label>采购计划说明</label>
	     </dt>
	  	${purchaseApply.instruction}
	  </dl>
	  <div class="divider"></div> 
      <dl class="nowrap">
				
				<label>审批状态：</label>
				<select name="approveFlow.status" class="required combox">
					<option value="1" <s:if test="user.status">selected</s:if>>通过</option>
					<option value="2" <s:if test="!user.status">selected</s:if>>不通过</option>
				</select>
		</dl>
		<div class="divider"></div> 
		<dl class="nowrap">
				<label>审批意见：</label>
				<textarea class="textInput valid" name="approveFlow.flowView" rows="5" cols="45" style="width:390px;height:60px;"/>
		</dl>
		
		</div>
						<div class="formBar">
				<ul>

					<input  id="ipt_frmAction"  type="hidden" name="formAction" value="save" />
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">审批</button>
							</div>
						</div>
					</li>
					<s:if test="buttonFlag == true">
				<li><div class="buttonActive"><div class="buttonContent"><button onclick="$('#ipt_frmAction').val('end');return true;" type="submit">审批完成</button></div></div></li>
				</s:if>
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
