<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="page">
		<form method="post" action="/oa/project/projectinformationlist.action"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">
			<input type="hidden" name="project.id" value="${project.id}" />
			<div class="pageFormContent" style="width:98%; height:280px; border:solid 1px #CCC;">
				<div>
					<dl class="nowrap">
						<dt>
							<label>项目名称：</label>
						</dt>
						${project.name}
					</dl>
					<dl class="nowrap">
						<dt>
							<label>项目状态：</label>		
						</dt>
						
						<s:if test="project.status == 1">正常</s:if>
						<s:elseif test="project.status == 2">保修</s:elseif>
						<s:elseif test="project.status == 3">结束</s:elseif>
						<s:elseif test="project.status == 4">终止</s:elseif>
						<s:else>待审批</s:else>
					</dl>
					<dl class="nowrap">
						<dt>
							<label>合同初始价：</label>
						</dt>
					    ${project.amount}<span>万元</span>
					</dl>
					<dl class="nowrap">
						<dt>
							<label>合同实际价：</label>
						</dt>

					</dl>
					<dl class="nowrap">
						<dt>
							<label>实际付款：</label>
						</dt>

					</dl>
					<dl class="nowrap">
						<dt>
							<label>项目支出：</label>
						</dt>

					</dl>
					<dl class="nowrap">
						<dt>
							<label>支出预算：</label>
						</dt>

					</dl>
					<dl class="nowrap">
						<dt>
							<label>待付款：</label>
						</dt>

					</dl>

				</div>

			</div>
		</form>
	</div>
