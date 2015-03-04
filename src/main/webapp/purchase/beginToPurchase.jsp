<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<div class="pageHeader">

</div>
<div class="pageContent">
<form action="/oa/approve/begintopurchase.action" method="post"
            class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">
	<div class="pageFormContent" layoutH="79"> 
	      <div>
	         <dl class="nowrap">
	            <dt>
	            <label>采购标题</label>
	            </dt>
	            <input name="application.id" type="hidden"	value="${application.id}" />
	            <input name="application.title" type="text"	value="${application.title}" readonly/> 
	          </dl>
	          
	          <dl class="nowrap">
	            <dt>
	            <label>项目</label>
	            </dt>
	            AAAAA
	          </dl>
	          
	 <h1>采购清单</h1>
	
		<table class="table" width="100%" layoutH="auto">
		<thead>
			<tr>
				<th width="120" align="center">品类</th>
				<th width="120" align="center">型号</th>
				<th align="center" width="120">单位</th>
				<th align="center" width="120">数量</th>
				<th width="120" align="center">预算单价</th>
			</tr>
		</thead>
		<tbody>
        
 		<s:iterator value="purchaseApplydetails" id="obj">
	  		<tr>
			<td>
			${obj.catagory}
			</td>
			<td>
			<s:property value="type" />
			</td>
			<td><s:property value="unit" /></td>
			<td><s:property value="number" /></td>
			<td><s:property value="budgetPrice" /></td>
			</tr>
		</s:iterator>

		</tbody>
	</table>
				
	
	  <dl class="nowrap">
	   <dt>
	    <label>预算采购总价</label>
	  </dt>
	     ${purchaseApply.totalPrice} 
	   </dl>
	
     <dl class="nowrap">
	     <dt>
	      <label>采购计划说明</label>
	     </dt>
	    ${purchaseApply.instruction}
	  </dl>
      <p>
				
				<label>审批状态：</label>
				<select name="approveFlow.status" class="required combox">
					<option value="1" <s:if test="user.status">selected</s:if>>通过</option>
					<option value="2" <s:if test="!user.status">selected</s:if>>不通过</option>
				</select>
			</p>
			<p>
				<label>审批意见：</label>
				<input name="approveFlow.flowView" type="text" value=""/>
			</p>
   

			<div class="formBar">
				<ul>

					<input type="hidden" name="formAction" value="save" />
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">开始采购</button>
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
