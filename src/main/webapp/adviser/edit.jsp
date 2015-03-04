<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="pageContent">
	<form method="post" action="/bolan/advisersign/edit.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>顾问：</label>
				<input name="adviserSign.id" type="hidden" value="${adviserSign.id}"/>
				
				
				<input type="hidden" name="adviserSign.adviser.id" value="${adviserSign.adviser.id}"/>
				<input type="text" class="required" name="adviserSign.adviser.realName" value="${adviserSign.adviser.realName}" lookupGroup="adviserSign.adviser" readonly="readonly"/>
				<s:if test="adviserSign==NULL">
				<a class="btnLook" href="/bolan/user/lookup.action?user.userType=2" lookupGroup="adviserSign.adviser">查找带回</a>	
				</s:if>
			</p>
			<p>
				<label>用户：</label>
				<input name="adviserSign.user.id" type="hidden" value="${adviserSign.user.id}"/>
				<input type="text" class="required" name="adviserSign.user.realName" value="${adviserSign.user.realName}" lookupGroup="adviserSign.user" readonly="readonly"/>
				<s:if test="adviserSign==NULL">
				<a class="btnLook" href="/bolan/user/lookup.action?user.userType=1" lookupGroup="adviserSign.user">查找带回</a>
				</s:if>
			</p>
	
			<p>
				<label>服务结束时间：</label>
				<input type="text" name="adviserSign.endTime" class="date" size="30" value="${adviserSign.endTime}"/><a class="inputDateButton" href="javascript:;">选择</a>
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
