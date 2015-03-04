<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="page">
	<div class="pageContent">
		<form action="/oa/contractor/editContractor.action" method="post"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, dialogAjaxDone);">

			<div class="pageFormContent" layoutH="80">

				<dl class="nowrap">
					<dt>
						<label>分包商名称</label>
					</dt>
					<input name="contractor.id" type="hidden" value="${contractor.id}" />

					<input name="contractor.contractorName" type="text"
						value="${contractor.contractorName}" class="required" />
				</dl>

				<dl class="nowrap">
					<dt>
						<label>联系人</label>
					</dt>
					<input name="contractor.contact" type="text"
						value="${contractor.contact}" />
				</dl>

				<dl class="nowrap">
					<dt>
						<label>电话</label>
					</dt>
					<input type="text" value="${contractor.phone}"
						name="contractor.phone" />
				</dl>

				<dl class="nowrap">
					<dt>
						<label>信誉等级</label>
					</dt>
					<input type="text" value="${contractor.credit}"
						name="contractor.credit" />
				</dl>

				<dl class="nowrap">
					<dt>
						<label>合作项目</label>
						<s:select name="id" list="projectList" headerKey="-l" listKey="id"
							listValue="name" value="%{contractor.project.id}" required="true" />
					</dt>
				</dl>

			</div>
			<div class="formBar">
				<ul>

					<input type="hidden" name="formAction" value="save" />
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">确定</button>
							</div>
						</div></li>
					<li>
						<div class="button">
							<div class="buttonContent">
								<button type="button" class="close">取消</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</form>
	</div>
</div>
