<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="pageContent">
	<form method="post" action="/oa/organization/modifyuser.action" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="58">
		<input type="hidden" name="user.id" value="${user.id}"/>
			<p>
				<label>员工编号</label>
				<input name="user.userNo" type="text" size="20" value="${user.userNo}"/>
			</p>
			<p>
				<label>员工姓名</label>
				<input name="user.realName" type="text" size="20" value="${user.realName}" />
			</p>
			<p>
				<label>年龄</label>
				<input name="user.age" type="text" size="10" value="${user.age}">
			</p>
			<p>
				<label>联系方式</label>
				<input name="user.mobile"  type="text" size="20"value="${user.mobile}"/>
			</p>
			<p>
				<label>地址</label>
				<input name="user.address" type="text" size="40" value="${user.address}" />
			</p>
	
		</div>
		<div class="formBar">
			<ul>
				<input type="hidden" name="formAction" value="save" />
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
