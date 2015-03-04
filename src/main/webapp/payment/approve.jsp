<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
${param.paramName}
<div class="pageContent">
	<form method="post" action="/oa/paymentorder/approve.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="70">
			<p>
				<label>业务类型：</label>
				<input name="paymentOrder.id" type="hidden" size="30" value="${paymentOrder.id}" readonly="readonly"/>
				<s:if test="paymentOrder.businessType=='BORROWMONEY'">借款</s:if>
				<s:if test="paymentOrder.businessType=='REQUESTMONEYMATERIAL'">材料请款</s:if>
				<s:if test="paymentOrder.businessType=='REQUESTMONEYENGINEER'">工程请款</s:if>
				<s:if test="paymentOrder.businessType=='EXPENSEOTHER'">费用报销</s:if>
				<s:elseif test="paymentOrder.businessType=='PURCHASE'">采购</s:elseif>
			</p>
			<p>
				<label>付款标题：</label>
				${paymentOrder.title}
			</p>
			<p>
				<label>经办人：</label>
				${paymentOrder.user.realName}
			</p>
			<p>
				<label>付款金额：</label>
				${paymentOrder.payAmount}
			</p>
			<p>
				<label>已经付款金额：</label>
				${paymentOrder.actualAmount}
			</p>
			<p>
				<label>准备付款：</label>
				<input name="paymentOrder.readyAmount" type="text" size="30" value="${paymentOrder.readyAmount}" class="number" readonly/>
			</p>
			
	
	
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<input type="hidden" name="formAction" value="save" />
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">同意付款</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
