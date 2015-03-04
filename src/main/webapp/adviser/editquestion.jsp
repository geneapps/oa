<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="pageContent">
	<form method="post" action="/bolan/question/edit.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<input name="question.id" type="hidden" value="${question.id}"/>
				<span>标题：${question.title}</span>
			</p>
			<p>
				<span>问题：${question.content}</span>
			</p>
			
			<dl class="nowrap">
			<dt>回复：</dt>
			<dd>
			<textarea name="question.answer" cols="80" rows="5">${question.answer}</textarea></dd>
			</dl>
	
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
