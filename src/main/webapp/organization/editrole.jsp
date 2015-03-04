<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
	

		<input type="hidden" name="department.id" value="${department.id}"/>

<div class="pageContent">
	<form method="post" action="/oa/organization/editrole.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="58">
			<p>
				<label>岗位标识</label>
				<input type="text" name="role.id"  size=30 value="${role.id}" readonly="readonly"/>
			</p>
			<p>
				<label>岗位所在部门</label>
				<input type="hidden" name="role.department.id" value="${role.department.id}"/>
				<input name="role.department.depName" type="text" value="${role.department.depName}" readonly/>
			</p> 
			
			
			
			<p>
				<label>岗位名称</label>
				<input type="text" name="role.name"  size=15 value="${role.name}" class="required"/>
			</p>
			
			<p>
				<label>岗位描述</label>
				<input type="text" name="role.description"  size=40 value="${role.description}"/>
			</p>

<p>	
		<label>岗位权限</label>
</p>
<p>
		<s:iterator value="oaActionList" id="obj" status="st">
		<input type="checkbox" name="roleActionIds"  value="<s:property value="id"/>"  
		<s:iterator value="role.actions" id="obj1" >
		<s:if test="#obj.id==#obj1.id">
		 checked = "true"
		</s:if>
		</s:iterator>
		> <s:property value="name"/>
		</s:iterator>
	</p>
		</div>
		
		<div class="formBar">
			<ul>
				<input type="hidden" name="formAction" value="save" />
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">确定</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>