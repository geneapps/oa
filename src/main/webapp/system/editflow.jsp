
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="pageContent">
	<form method="post" action="/oa/rolemanage/modifyflow.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="70">
			<p>
				<label>流程ID：</label>
				<input name="approveFlowConfig.id" type="text" size="30" value="${approveFlowConfig.id}" readonly="readonly"/>
			</p>
			<p>
				<label>申请类型：</label>
				<input name="approveFlowConfig.appType" type="text" size="30" value="${approveFlowConfig.appType}" readonly="readonly"/>
			</p>
			<p>
				<label>审批顺序：</label>
				<input name="approveFlowConfig.orderBy" type="text" size="30" value="${approveFlowConfig.orderBy}" />
			</p>
			<p>
				<label>审批类型：</label>
				<input type="radio" name="approveFlowConfig.actionType" value="0" <s:if test="approveFlowConfig.actionType==0">checked</s:if> />角色
				<input type="radio" name="approveFlowConfig.actionType" value="1" <s:if test="approveFlowConfig.actionType==1">checked</s:if> />操作
				<input type="radio" name="approveFlowConfig.actionType" value="2" <s:if test="approveFlowConfig.actionType==2">checked</s:if> />用户
			</p>
			<p>
				<label>审批标识：</label>
				<input name="approveFlowConfig.actionId" type="text" size="30" value="${approveFlowConfig.actionId}"/>
			</p>

	
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
