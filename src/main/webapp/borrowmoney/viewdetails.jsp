<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="page">
<div class="pageContent">
		<div class="pageFormContent" layoutH="56">
		
					<dl class="nowrap">
						<dt>
							<label>借款标题：</label>
						</dt>
						${borrowMoney.borrowTitle}
					</dl>

				  
	          <dl class="nowrap">
	            <dt>
	            <label>借款类型</label>
	            </dt>
					 <s:if test="borrowMoney.borrowType==1">材料采购</s:if>
					 <s:if test="borrowMoney.borrowType==2">生活费</s:if>
					 <s:if test="borrowMoney.borrowType==3">招待费</s:if>
					 <s:if test="borrowMoney.borrowType==4">其他</s:if>
	          </dl>
					
					
			 <dl class="nowrap">
	            <label>收款方名称</label>
	            ${borrowMoney.payee}
	          </dl>
	           
	          <dl class="nowrap">
	            <label>收款方账户信息</label>
	           ${borrowMoney.account}
	          </dl>
	          <dl class="nowrap">
	            <label>合同编号</label>
	            ${borrowMoney.contractNo}
	          </dl>
					

					<dl class="nowrap">
						<dt><label>借款金额</label></dt>
						${borrowMoney.borrowMoney}
					</dl>
					
					<dl class="nowrap">
						<dt><label>借款日期</label></dt>
						${borrowMoney.borrowTime}
					</dl>
					
					<dl class="nowrap">
						<dt><label>借款缘由</label></dt>
						${borrowMoney.borrowReason}
					</dl>
					
					<dl class="nowrap">
						<dt><label>借款缘由</label></dt>
						${borrowMoney.applyTime}
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
