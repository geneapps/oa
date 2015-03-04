<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="page">
<div class="pageContent">
<form action="/oa/material/edit.action" method="post"
                 class="pageForm required-validate"
			onsubmit="return validateCallback(this, dialogAjaxDone);">
			
	  <div class="pageFormContent" layoutH="80"> 
	   
	         <dl class="nowrap">
	            <dt>
	            <label>材料名</label>
	            </dt>
	            <input name="material.id" type="hidden"	value="${material.id}" />
	           
	            <input name="material.materialName" type="text" value="${material.materialName}" class="required"/>
	          </dl>
	          
	          <dl class="nowrap">
	            <dt>
	            <label>材料类型</label>
	            </dt>
	           <input name="material.materialType" type="text"  value="${material.materialType}"/>
	          </dl>
	           
	            <dl class="nowrap">
	            <dt>
	            <label>品牌</label>
	            </dt>
	           <input type="text" value="${material.brand}" name="material.brand"/>
	          </dl>
	
	      <dl class="nowrap">
	           <dt>
	            <label>价格</label>
	           </dt>
	           <input type="text" value="${material.price}" name="material.price"/>
	      </dl>
	
	     
         <dl class="nowrap">
	       <dt>
	         <label>单位</label>
	       </dt>
	     <input type="text" value="${material.unit}" name="material.unit"/>
	     </dl>
	    <dl class="nowrap">
	       <dt>
	         <label>备注</label>
	       </dt>
	     <input type="text" value="${material.remark}" name="material.remark"/>
	     </dl>
	   
</div>
			<div class="formBar">
				<ul>

					<input type="hidden" name="formAction" value="save" />
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">确定</button>
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
