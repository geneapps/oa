<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="page">
<div class="pageContent">
		<div class="pageFormContent" layoutH="56">
		
					<dl class="nowrap">
						<dt>
							<label>合同标题：</label>
						</dt>
						${application.title}
					</dl>
					
					<dl>
						<dt>
							<label>所属项目</label>
						</dt>
						${contract.project.name}
					</dl>
					
					<dl>
						<dt>
							<label>材料合同类型</label>
						</dt>
						<s:if test="contract.meterialContractType==0">水电材料</s:if>
						<s:elseif test="contract.meterialContractType==1">材料合同</s:elseif>
					</dl>

					<dl class="nowrap">
						<dt><label>甲方：</label></dt>
						${contract.companyA}
					</dl>
					


					<dl class="nowrap">
						<dt><label>乙方：</label></dt>
						${contract.companyB}
					</dl>
					
					<dl class="nowrap">
						<dt><label>乙方经办人：</label></dt>
						${contract.b_contact}
					</dl>
					
					<dl class="nowrap">
						<dt><label>经办人联系方式：</label></dt>
						${contract.b_contact_phone}
					</dl>
					
					<dl class="nowrap">
						<dt><label>合同缘由：</label></dt>
						${contract.contractReason}
					</dl>
	
					<dl class="nowrap">
						<dt>
							<label>明细</label>
						</dt>
						<table class="table" width="100%">
						<thead>
							<tr>
								<th align="center">材料</th>
								<th align="center">品类</th>
								<th align="center">分类</th>
								<th align="center">单位</th>
								<th align="center">单价</th>
								<th align="center">数量</th>
							</tr>
						</thead>
						<tbody>
						<s:iterator value="contract.contractDetails" id="obj" status='st'>
							<tr target="sid_obj" rel="<s:property value="id" />">
								<td>
							<s:property value="material.materialName"/>
							</td>
							<td>
							<s:property value="material.category"/>
							</td>
							<td>
							<s:property value="material.materialType" />
							</td>
							<td><s:property value="material.unit" /></td>
							<td><s:property value="budgetPrice" /></td>
							<td><s:property value="number" /></td>
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
						<table class="table" width="100%" layoutH="324">
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
