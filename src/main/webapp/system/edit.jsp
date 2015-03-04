
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
${param.paramName}
<div class="pageContent">
	<form method="post" action="/oa/system/edit.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="70">
			<p>
				<label>参数标识：</label>
				<input name="systemParam.id" type="text" size="30" value="${systemParam.id}" readonly="readonly"/>
			</p>
			<p>
				<label>名称名称：</label>
				<input name="systemParam.showName" type="text" size="30" value="${systemParam.showName}"/>
			</p>
			<p>
				<label>参数主键：</label>
				<input name="systemParam.paramName" type="text" size="30" value="${systemParam.paramName}"/>
			</p>
			<p>
				<label>参数值：</label>
				<input name="systemParam.paramValue" type="text" size="30" value="${systemParam.paramValue}"/>
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
