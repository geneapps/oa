<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="pageContent">
	<form method="post" action="/bolan/suggest/edit.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>标题：</label>
				<input name="suggest.id" type="hidden" value="${suggest.id}"/>
				<input name="suggest.adviser.id" type="hidden" value="${suggest.adviser.id}"/>
				<input name="suggest.suggestTime" type="hidden" value="${suggest.suggestTime}"/>
				<input name="suggest.title" type="text" size="40" value="${suggest.title}"/>
			</p>
			<p>
				<label>分类：</label>
				<select name="suggest.suggestType" class="required combox">
					<option value="0" <s:if test="suggest.suggestType==0">selected</s:if>>持仓诊断报告</option>
					<option value="1" <s:if test="suggest.suggestType==1">selected</s:if>>资产配置报告</option>
					<option value="2" <s:if test="suggest.suggestType==2">selected</s:if>>个股诊断报告</option>
				</select>
			</p>
			<div class="unit">
			<textarea class="editor" name="suggest.content" rows="10" cols="76" tools="mini">${suggest.content}</textarea>
			</div>
			<p>
				<label>发送用户：</label><a class="btnLook" href="/bolan/advisersign/lookup.action" lookupGroup="suggest">查找带回</a><a href="/bolan/advisersign/lookup.action" lookupGroup="suggest">查找</a>
				<textarea name="suggest.senderStr" rows="2" cols="88">${suggest.senderStr}</textarea>
					
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
