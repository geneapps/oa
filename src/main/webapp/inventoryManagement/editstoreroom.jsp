<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="page">
<div class="pageContent">
<form action="/oa/storeroom/editstoreroom.action" method="post"
                class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			
	  <div class="pageFormContent" layoutH="50"> 
	   
	         <dl class="nowrap">
	            <dt>
	            <label>库房名</label>
	            </dt>
	            <input name="storeRoom.id" type="hidden" value="${storeRoom.id}" />
	           
	            <input name="storeRoom.name" type="text" size="50" value="${storeRoom.name}" class="required"/>
	          </dl>
	          
	          <dl class="nowrap">
	            <dt>
	            <label>管理员</label>
	            </dt>
                 <input name="storeRoom.user.realName" type="text" size="50" value="${storeRoom.user.realName}" class="required"/>
	          </dl>
	           
	            <dl class="nowrap">
	            <dt>
	            <label>所属项目</label>
	            </dt>
	           <input name="storeRoom.project.name" type="text" size="50" value="${storeRoom.project.name}" class="required"/>
	          </dl>
	
         <dl class="nowrap">
	       <dt>
	         <label>库房描述</label>
	       </dt>
	      <s:textarea name="storeRoom.description" ros="30" cols="50"/>
	     </dl>
	     </div>
	   
			<div class="formBar">
				<ul>

					<input type="hidden" name="formAction" value="save" />
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">添加</button>
							</div>
						</div>
					</li>
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
</div>