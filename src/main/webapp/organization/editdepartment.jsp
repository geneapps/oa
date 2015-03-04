<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="pageContent">
	<form method="post" action="/oa/organization/editdepartment.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="67">
		<dl class="nowrap">
			<dt>上级部门：</dt>
			<dd>
				<input type="hidden" name="department.parent.id" value="${department.parent.id}"/>
				<input name="department.parent.depName" type="text" value="${department.parent.depName}" readonly/>
			 	<a class="btnLook" href="/oa/organization/departmentlookup.action" lookupGroup="department.parent" rel="departlookup">选择部门</a>
			</dd>
		</dl>
		
		<dl class="nowrap">
			<dt>部门名称：</dt>
			<dd>
				<input type="hidden" name="department.id" value="${department.id}"/>
				<input type="text" name="department.depName"  size=15 value="${department.depName}"/>
			</dd>
		</dl>
		
		<dl class="nowrap">
			<dt>部门描述：</dt>
			<dd>
				<input type="text" name="department.description"  size=40 value="${department.description}"/>
			</dd>
		</dl>
		</div>

		<div class="formBar">
			<ul>
				<input type="hidden" name="formAction" value="save" />
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">确定</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>