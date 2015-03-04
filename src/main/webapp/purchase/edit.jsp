<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="pageContent">
	<form method="post" action="/oa/purchasePlanDetails/edit.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
		<input name="purchasePlanDetails.id" type="hidden" value="${purchasePlanDetails.id}"/>
			<p>
				<label>品类：</label>
				<input name="purchasePlanDetails.category" type="text" size="30" value="${purchasePlanDetails.category}"/>
			</p>
			<p>
				<label>型号：</label>
				<input name="purchasePlanDetails.type" type="text" size="30" value="${purchasePlanDetails.type}"/>
			</p>
			<p>
				<label>单位：</label>
				<input name="purchasePlanDetails.unit" type="text" size="30" value="${purchasePlanDetails.unit}"/>
			</p>
			<p>
				<label>预算单价：</label>
				<input name="purchasePlanDetails.budgetPrice" type="text" size="30" value="${purchasePlanDetails.budgetPrice}"/>
			</p>
			<p>
				<label>数量：</label>
				<input name="purchasePlanDetails.number" type="text" size="30" value="${purchasePlanDetails.number}"/>
			</p>
			<p>
				<label>库存数量：</label>
				<input name="purchasePlanDetails.houseNumber" type="text" size="30" value="${purchasePlanDetails.houseNumber}"/>
			</p>
	      <input name="purchasePlanDetails.purchasePlan.id" type="hidden" value="2"/>
	
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<input type="hidden" name="formAction" value="save" />
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
