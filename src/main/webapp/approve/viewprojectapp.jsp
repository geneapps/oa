<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="page">
<div class="pageContent">
	
		<div class="pageFormContent" layoutH="45">
			<p>
				<label>项目名称:</label>
				<input type="text" size="40" value="${Project.name}">
			</p>
			<p>
				<label>项目经理:</label>
				<input type="text" size="10" value="${project.manager.realName}">
			</p>
			<p>
				<label>联系方式:</label>
				<input type="text" size="20" value="${project.manager.mobile}">
			</p>
			<p>
				<label>项目金额:</label>
				<input type="text" size="10" value="${project.amount}">
				<span class="unit">万元</span>
			</p>
			<p>
				<label>开始时间:</label>
				<input type="text" size="10" value="${project.startDate}">
			</p>
			<p>
				<label>结束时间:</label>
				<input type="text" size="10" value="${project.endDate}">
			</p>
			<p>
				<label>项目地址:</label>
				<input type="text" size="40" value="${project.address}">
			</p>
			<p>
				<label>项目描述:</label>
				<input type="text" size="40" value="${project.description}">
			</p>
			<p>
	            <label>审批意见：</label>
	        </p>
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
