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

					<dl class="nowrap">
						<dt><label>甲方：</label></dt>
						${contract.companyA}
					</dl>

					<dl class="nowrap">
						<dt><label>乙方：</label></dt>
						${contract.companyB}
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
			
			审批意见：
			<s:iterator value="application.approveFlows" id="obj" status='st'>
			<s:if test="status==1 or status==2">
			<div class="divider"></div>
			<dl class="nowrap">
				<s:property value="flowDate"/> <s:property value="user.realName"/> 审批<s:if test="status==2">不同意</s:if><s:if test="status==1">同意</s:if>：<s:property value="flowView"/>
			</dl>
			</s:if>
			</s:iterator>
			
			

			
	
	
		</div>
</div>
</div>
