<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="page">
<div class="pageContent">
		<div class="pageFormContent" layoutH="56">
		
					<dl class="nowrap">
						<dt>
							<label>标题：</label>
						</dt>
						${expense.payTitle}
					</dl>

				<!-- <dl class="nowrap">
						<dt><label>费用类型</label></dt>
						${expense.expenseType}
					</dl>   -->	

					<dl class="nowrap">
						<dt><label>提交时间</label></dt>
						${expense.submitTime}
					</dl>
					
					<dl class="nowrap">
						<dt><label>申请人</label></dt>
						${expense.user.realName}
					</dl>
					
					<dl class="nowrap">
						<dt><label>项目名称</label></dt>
						${expense.department.depName}
					</dl>
	
					<dl class="nowrap">
						<dt>
							<label>费用清单如下</label>
						</dt>
						<table class="table" width="100%">
						<thead>
							<tr>
								<th align="center">费用类型</th>
								<th align="center">费用金额</th>
								<th align="center">费用说明</th>
								<th align="center">报销时间</th>
								<th align="center">付款时间</th>
							</tr>
						</thead>
						<tbody>
						<s:iterator value="expense.costDetails" id="obj" status='st'>
							<tr target="sid_obj" rel="<s:property value="id" />">
								<td>
							<s:property value="expenseType"/>
							</td>
							<td>
							<s:property value="payMoney"/>
							</td>
							<td>
							<s:property value="description" />
							</td>
							<td><s:property value="expenseTime" /></td>
							<td><s:property value="payTime" /></td>
							</tr>
						</s:iterator>				
						</tbody>
					</table>
					</dl>
					
				
				 <dl class="nowrap">
						<dt>
							<label>报销记录如下</label>
						</dt>
					<table class="table" width="100%">
						<thead>
							<tr>
								<th align="center">报销状态</th>
								<th align="center">付款单标题</th>
								<th align="center">已经付款金额</th>
								<th align="center">付款金额</th>
								<th align="center">准备付款付款金额</th>
								<th align="center">要求付款时间</th>
								<th align="center">付款时间</th>
							</tr>
						</thead>
						<tbody>
						<s:iterator value="paymentOrder" id="obj" status='st'>
							<tr target="sid_obj" rel="<s:property value="id" />">
								<td>
							
				<s:if test="#obj.status == 0">等待付款</s:if>
				<s:elseif test="#obj.status == 1">全部付清</s:elseif>
				<s:elseif test="#obj.status == 2">部分付款 </s:elseif>
				<s:elseif test="#obj.status == -1">等待审批</s:elseif>
				<s:else>等待审批</s:else>
			
							</td>
							<td>
							<s:property value="title"/>
							</td>
							<td>
							<s:property value="actualAmount" />
							</td>
							<td>
							<s:property value="payAmount" />
							</td>
							<td><s:property value="readyAmount" /></td>
							<td><s:property value="requiredPayTime" /></td>
							<td><s:property value="payTime" /></td>
							</tr>
						</s:iterator>				
						</tbody>
					</table>
					</dl>
					
					<dl class="nowrap">
						<dt>
							<label>附件</label>
						</dt>
					
											<table class="table" width="100%">
						<thead>
							<tr>
								<th align="center">文件名</th>
								<th align="center">操作</th>
							</tr>
						</thead>
						<tbody>
						<s:iterator value="fileLogList" id="obj" status='st'>
							<tr target="sid_obj" rel="<s:property value="id" />">
							<td>
							<s:property value="fileName"/>
							</td>
							<td>
							<a href="<s:property value="fileHttpUrl"/><s:property value="filePath"/><s:property value="id"/><s:property value="fileExt"/>">查看</a>
							</td>
	
							</tr>
						</s:iterator>				
						</tbody>
					</table>
					
								
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
