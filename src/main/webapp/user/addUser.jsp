<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="page">
	<div class="pageContent">
		
		<form method="post" action="/oa/user/addUser.action" class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent nowrap" layoutH="97">
         <input type="hidden" name="user.id" value="${user.id}"/>
			<dl>
				<dt>员工编号:</dt>
				<dd>
					<input type="text" name="user.userNo" class="required" size="20" minlength="5"/>
				</dd>
			</dl>
			<dl>
				<dt>姓名:</dt>
				<dd>
					<input type="text" name="user.realName" class="required" size="20"/>
				</dd>
			</dl>
			<dl>
				<dt>性别:</dt>
				<dd>
					<input type="radio" name="user.sex" value="1"/>男
					<input type="radio" name="user.sex" value="2"/>女
					
				</dd>
			</dl>
			
			<dl>
				<dt>生日</dt>
				<dd>
					<input class="date textInput valid" type="text" maxdate="2100-12-31" mindate="1900-01-01" datefmt="y年M月d日" name="user.birthday"></input> <a class="inputDateButton" href="javascript:;"> 选择 </a>
					</dd>
			</dl>
			
			<dl>
				<dt>年龄</dt>
				<dd>
					<input type="text" name="user.age" min="18" max="100" size="20"/>
				</dd>
			</dl>
			
			<dl>
				<dt>邮箱:</dt>
				<dd>
					<input type="text" name="user.email" class="email"  size="40"/>
				</dd>
			</dl>
			<dl>
				<dt>电话:</dt>
				<dd>
					<input type="text" name="user.mobile" class="phone"  size="40"/>
				</dd>
			</dl>
			<dl>
				<dt>密码:</dt>
				<dd>
					<input id="w_validation_pwd" type="password" class="required alphanumeric" minlength="6" maxlength="20" alt="格式：字母、数字、下划线 6-20位" size="40"/>
				</dd>
			</dl>
			<dl>
				<dt>确认密码:</dt>
				<dd>
					<input type="password" name="user.password" class="required" equalto="#w_validation_pwd" size="40"/>
				</dd>
			</dl>
			
			
			<div class="divider"></div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
	
			
	</div>
</div>
