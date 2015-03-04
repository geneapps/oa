<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="page">
	<div class="pageContent">
		<div class="pageFormContent" >
		<input type="hidden" name="project.id" value="${project.id}" />
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
						<label>工程直接费用支出：</label>
					</dt>

				</dl>
				<dl class="nowrap">
					<dt>
						<label>人力费用：</label>
					</dt>

				</dl>
				<dl class="nowrap">
					<dt>
						<label>管理费用：</label>
					</dt>

				</dl>
				<dl class="nowrap">
					<dt>
						<label>税金：</label>
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
	</div>
</div>