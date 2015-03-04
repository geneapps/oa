<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="page">
<div class="pageContent">
		<div class="pageFormContent" layoutH="56">
		
					<dl class="nowrap">
						<dt>
							<label>采购计划标题</label>
						</dt>
						${purchasePlan.title}
					</dl>

					<dl class="nowrap">
						<dt><label>预算采购总价</label></dt>
						${purchasePlan.totalPrice}
					</dl>

					
				<dl class="nowrap">
					<dt><label>采购计划说明</label></dt>
					${purchasePlan.instruction}
				</dl>
	
					
					<dl class="nowrap">
						<dt><label>申请时间</label></dt>
						${purchasePlan.applytime}
					</dl>
	
	
	
				
					<dl class="nowrap">
						<dt>
							<label>审批记录如下</label>
						</dt>
						<table class="table" width="100%" layoutH="412">
		<thead>
			<tr>
				<th width="200" align="center">审批状态</th>
				<th width="200" align="center">审批岗位</th>
				<th width="200" align="center">审批人</th>
				<th width="200" align="center">审批时间</th>
				<th width="200" align="center">审批意见</th>
			</tr>
		</thead>
		<tbody>

		<s:iterator value="application.approveFlows" id="obj">
			<tr target="objId" rel="<s:property value="id" />">			
			<td>
				<s:if test="activate">--> </s:if>
				<s:if test="#obj.status == 1">审批通过</s:if>
				<s:elseif test="#obj.status == 2">审批不通过</s:elseif>
				<s:elseif test="#obj.status == 0">等待审批 </s:elseif>
				<s:elseif test="#obj.status == 4">终止</s:elseif>
				<s:else>等待审批</s:else>
			</td>
			<td>
			<s:property value="user.role.name" />
			</td>
			<td>
			<s:property value="user.realName" />
			</td>
			<td>
			<s:property value="flowDate" />
			</td>
			<td>
			<s:property value="flowView" />
			</td>
			</tr>
		</s:iterator>


		</tbody>
	</table>

					</dl>
			
		</div>
</div>
</div>
