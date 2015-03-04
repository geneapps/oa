
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
${param.paramName}
<div class="pageContent">
	<form method="post" action="/oa/user/editpwd.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="70">
			<p>
				<label>旧密码：</label>
				<input name="oldPwd" type="password" size="30" value=""/>
			</p>
			<p>
				<label>新密码：</label>
				<input name="newPwd" type="password" size="30" value=""/>
			</p>
			<p>
				<label>新密码：</label>
				<input name="reNewPwd" type="password" size="30" value=""/>
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
