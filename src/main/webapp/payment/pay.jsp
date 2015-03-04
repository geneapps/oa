<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
${param.paramName}
<div class="pageContent">
	<form method="post" action="/oa/paymentorder/pay.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="70">
			<p>
				<label>业务类型：</label>
				<input name="paymentLog.id" type="hidden" size="30" value="${paymentLog.id}"/>
				<input name="paymentLog.paymentOrder.id" type="hidden" size="30" value="${paymentLog.paymentOrder.id}"/>
				<s:if test="paymentLog.paymentOrder.businessType=='BORROWMONEY'">借款</s:if>
				<s:if test="paymentLog.paymentOrder.businessType=='REQUESTMONEYMATERIAL'">材料请款</s:if>
				<s:if test="paymentLog.paymentOrder.businessType=='REQUESTMONEYENGINEER'">工程请款</s:if>
				<s:if test="paymentLog.paymentOrder.businessType=='EXPENSEOTHER'">费用报销</s:if>
				<s:elseif test="paymentLog.paymentOrder.businessType=='PURCHASE'">采购</s:elseif>
			</p>
			<p>
				<label>付款标题：</label>
				${paymentLog.paymentOrder.title}
			</p>
			<p>
				<label>经办人：</label>
				${paymentLog.paymentOrder.user.realName}
			</p>
			<p>
				<label>付款金额：</label>
				${paymentLog.paymentOrder.payAmount}
			</p>
			<p>
				<label>已付金额：</label>
				${paymentLog.paymentOrder.actualAmount}
			</p>
			<p>
				<label>应付金额：</label>
				${paymentLog.paymentOrder.payable}
			</p>
			<p>
				<label>收款方名称</label>
				${paymentLog.paymentOrder.payee}
			</p>
			<p>
				<label>收款方账户信息</label>
				${paymentLog.paymentOrder.account}
			</p>
			<p>
				<label>合同编号</label>
				${paymentLog.paymentOrder.contractNo}
			</p>
			<p>
				<label>付款：</label>
				<input name="paymentLog.payAmount" type="text" size="30" value="${paymentLog.payAmount}" class="number"/>
			</p>
			
	
	
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<input type="hidden" name="formAction" value="save" />
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">付款</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
