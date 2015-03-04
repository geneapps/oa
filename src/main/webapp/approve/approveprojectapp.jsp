<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="pageContent">
	<form method="post" action="/oa/approve/approveprojectapp.action" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="45">
		<input type="hidden" name="project.id" value="${project.id}"/>
		<input name="application.id" type="hidden" value="${application.id}"/>
			<p>
				<label>项目名称</label>
				<input readonly="readonly" name="project.Name" type="text" size="40" value="${Project.name}"/>
			</p>
			<p>
				<label>项目经理</label>
				<input readonly="readonly" name="project.manager.realName" type="text" size="15" value="${project.manager.realName}"/>
			</p>
			<p>
				<label>联系方式</label>
				<input readonly="readonly" name="project.manager.mobile"  type="text" value="${project.manager.mobile}"/>
			</p>
			<p>
				<label>项目金额</label>
				<input readonly="readonly" name="project.amount" type="text" size="10" value="${project.amount}"/>
				<span class="unit">万元</span>
			</p>
			<p>
				<label>开始时间</label>
				<input readonly="readonly" name="project.startDate" type="text" size="15" value="${project.startDate}"/>
			</p>
			<p>
				<label>结束时间</label>
				<input readonly="readonly" name="project.endDate" type="text" size="15" value="${project.endDate}"/>
			</p>
			<p>
				<label>项目地址</label>
				<input readonly="readonly" name="project.address" type="text" size="40" value="${project.address}"/>
			</p>
			<p>
				<label>项目描述</label>
				<input readonly="readonly" name="project.description" type="text" size="40" value="${project.description}" />
			</p>

			
		<dl class="nowrap">
			
				<dt><label>审批状态：</label></dt>
				<select name="approveFlow.status" class="required combox">
					<option value="1" <s:if test="user.status">selected</s:if>>通过</option>
					<option value="2" <s:if test="!user.status">selected</s:if>>不通过</option>
				</select>
			</dl>
			<dl class="nowrap">
				<dt><label>审批意见：</label></dt>
				<textarea class="textInput valid" name="approveFlow.flowView" rows="5" cols="45" style="width:390px;height:60px;"/>
			</dl>
			
	
	
		</div>
					<div class="formBar">
				<ul>

					<input  id="ipt_frmAction"  type="hidden" name="formAction" value="save" />
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">审批</button>
							</div>
						</div>
					</li>
					<s:if test="buttonFlag == true">
				<li><div class="buttonActive"><div class="buttonContent"><button onclick="$('#ipt_frmAction').val('end');return true;" type="submit">审批完成</button></div></div></li>
				</s:if>
					<li>
						<div class="button">
							<div class="buttonContent">
								<button type="button" class="close">取消</button>
							</div>
						</div></li>
				</ul>
			</div>
	</form>
</div>
