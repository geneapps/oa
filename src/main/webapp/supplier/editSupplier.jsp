<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="page">
<div class="pageContent">
<form action="/oa/supplier/editSupplier.action" method="post"
                 class="pageForm required-validate"
			onsubmit="return validateCallback(this, dialogAjaxDone);">
			
	  <div class="pageFormContent" layoutH="80"> 
	   
	         <dl class="nowrap">
	            <dt>
	            <label>供货商名</label>
	            </dt>
	            <input name="supplier.id" type="hidden"	value="${supplier.id}" />
	           
	            <input name="supplier.supplyName" type="text" value="${supplier.supplyName}" class="required"/>
	          </dl>
	          
	          <dl class="nowrap">
	            <dt>
	            <label>联系人</label>
	            </dt>
	           <input name="supplier.contact" type="text"  value="${supplier.contact}"/>
	          </dl>
	           
	            <dl class="nowrap">
	            <dt>
	            <label>电话</label>
	            </dt>
	           <input type="text" value="${supplier.phone}" name="supplier.phone"/>
	          </dl>
	
	      <dl class="nowrap">
	           <dt>
	            <label>地址</label>
	           </dt>
	           <input type="text" value="${supplier.address}" name="supplier.address"/>
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
