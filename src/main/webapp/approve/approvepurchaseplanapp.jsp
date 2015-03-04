<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<div class="pageHeader">

</div>
<div class="pageContent">
<form action="/oa/approve/approvepurchaseplanapp.action" method="post"
            class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">
	<div class="pageFormContent" layoutH="79"> 
	      <div>
	         <dl class="nowrap">
	            <dt>
	            <label>采购计划标题</label>
	            </dt>
	            <input name="application.id" type="hidden"	value="${application.id}" />
	            <input name="application.title" type="text"	value="${application.title}" readonly/> 
	          </dl>
	          
	          <dl class="nowrap">
	            <dt>
	            <label>项目</label>
	            </dt>
	           <input name="user.id" type="hidden"	value="${user.id}" />
	           ${user.department.depName}
	          </dl>
	          
	 <h1>计划清单</h1>
	
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
        
 		<s:iterator value="purchasePlandetails" id="obj">
	  		<tr>
			<td>
			${obj.category}
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
	     ${purchasePlan.totalPrice} 
	   </dl>
	
     <dl class="nowrap">
	     <dt>
	      <label>采购计划说明</label>
	     </dt>
	    ${purchasePlan.instruction}
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
				<textarea class="textInput valid" name="approveFlow.flowView" rows="5" cols="45" style="width:390px;height:60px;"/>
			</p>
   

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
