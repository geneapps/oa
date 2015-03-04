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
						<label>项目采购总金额：</label>
					</dt>
                    ${totalPrice} <span>元</span> 
				</dl>
				<s:debug/>
				<dl class="nowrap">
					<dt>
						<label>项目入库总金额：</label>
					</dt>
					${inputTotalMoney}<span>元</span>			
				</dl>
				<dl class="nowrap">
					<dt>
						<label>项目出库总金额：</label>
					</dt>
					${outputTotalMoney}<span>元</span>	
				</dl>
				<dl class="nowrap">
					<dt>
						<label>项目剩余材料总金额：</label>
					</dt>
					${stockTotalMoney}<span>元</span>
				</dl>
			</div>
		</div>
	</div>
</div>