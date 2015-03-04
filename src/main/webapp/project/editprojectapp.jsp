<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="pageContent">
	<form method="post" action="/oa/approve/editprojectapp.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="58">
		<input type="hidden" name="application.id" value="${application.id}"/>
		 <input type="hidden" name="project.id" value="${project.id}"/> 
		<input type="hidden" name="project.status" value="${project.status}"/>
			<p>
				<label>项目名称</label>
				<input name="project.name" type="text" size="40" value="${project.name}"/>
			</p>
			<p>
				<label>项目经理</label>
				<input name="project.manager.id" value="${project.manager.id}" type="hidden"/>
				<input class="required" name="project.manager.realName" type="text" value="${project.manager.realName}"/>
				<a class="btnLook" href="/oa/common/userlookup.action" lookupGroup="project.manager">查找带回</a>	
			</p>
			<p>
			<label>联系方式</label>
				<input name="project.manager.mobile"  type="text" value="${project.manager.mobile}"/>
			</p>
			<p>
				<label>项目金额</label>
				<input name="project.amount" type="text" size="10" value="${project.amount}" class="digits"/>
				<span class="unit">万元</span>
			</p>
			<p>
				<label>开始时间</label>
				<input  class="date textInput readonly valid" name="project.startDate" type="text" size="15" value="${project.startDate}"/>
				 <a class="inputDateButton" href="javascript:;"></a>
			</p>
			<p>
				<label>结束时间</label>
				<input class="date textInput readonly valid" name="project.endDate" type="text" size="15" value="${project.endDate}"/>
				 <a class="inputDateButton" href="javascript:;"></a>
			</p>
			<p>
				<label>项目地址</label>
				<input name="project.address" type="text" size="40" value="${project.address}"/>
			</p>
			<p>
				<label>项目描述</label>
				<input name="project.description" type="text" size="40" value="${project.description}" />
			</p>

	
	
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>提交审批</span></a></li>-->
				<input type="hidden" name="formAction" value="save" />
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交审批</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
