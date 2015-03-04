<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="page">
	<div class="pageContent">
		<div class="pageFormContent" layoutH="56">

			<dl class="nowrap">
				<dt>
					<label>行政申请标题：</label>
				</dt>
				${admin.title}
			</dl>

			<dl class="nowrap">
			<dt>
					<label>申请类型</label>
				</dt>
				<s:if test="admin.adminType==1">办公用品</s:if>
				<s:if test="admin.adminType==2">招待费</s:if>
				<s:if test="admin.adminType==3">交通费</s:if>
				<s:if test="admin.adminType==4">生活费</s:if>
				<s:if test="admin.adminType==5">差旅费</s:if>
				<s:if test="admin.adminType==6">其他</s:if>
			</dl>

			<dl class="nowrap">
				<dt>
					<label>申请说明</label>
				</dt>
				${admin.instruction}
			</dl>

<dl class="divider"></dl>

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
				<table class="table" width="100%" layoutH="412" id="resultTb">
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
								<td><s:if test="activate">--> </s:if> <s:if
										test="#obj.status == 1">审批通过</s:if> <s:elseif
										test="#obj.status == 2">审批不通过</s:elseif> <s:elseif
										test="#obj.status == 0">等待审批 </s:elseif> <s:elseif
										test="#obj.status == 4">终止</s:elseif> <s:else>等待审批</s:else></td>
								<td><s:property value="user.role.name" /></td>
								<td><s:property value="user.realName" /></td>
								<td><s:property value="flowDate" /></td>
								<td><s:property value="flowView" /></td>
							</tr>
						</s:iterator>


					</tbody>
				</table>

			</dl>
		</div>
	</div>
</div>
