<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="page">
<div class="pageContent">
<form action="/oa/approve/editborrowmoneyapp.action" method="post"
                 class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">
			
	  <div class="pageFormContent" layoutH="50"> 
	   
	         <dl class="nowrap">
	            <dt>
	            <label>借款标题</label>
	            </dt>
	            <input name="application.id" type="hidden"	value="${application.id}" />
	            <input name="borrowMoney.id" type="hidden"	value="${borrowMoney.id}" />
	           
	            <input name="application.title" type="text" size="50" value="${application.title}" class="required"/>
	          </dl>
	          
	          <dl class="nowrap">
	            <dt>
	            <label>借款类型</label>
	            </dt>
	           <select name="borrowMoney.borrowType" class="required combox">
					<option value="1" <s:if test="borrowMoney.borrowType==1">selected</s:if>>材料采购</option>
					<option value="2" <s:if test="borrowMoney.borrowType==2">selected</s:if>>生活费</option>
					<option value="3" <s:if test="borrowMoney.borrowType==3">selected</s:if>>招待费</option>
					<option value="4" <s:if test="borrowMoney.borrowType==4">selected</s:if>>其他</option>
				</select>
	          </dl>
	           
	           
	               <dl class="nowrap">
	            <label>收款方名称</label>
	            <input type="text" value="${borrowMoney.payee}" name="borrowMoney.payee"  class="required" size="30">
	          </dl>
	           
	          <dl class="nowrap">
	            <label>收款方账户信息</label>
	            <input type="text" value="${borrowMoney.account}" name="borrowMoney.account"  class="required" size="30">
	          </dl>
	          <dl class="nowrap">
	            <label>合同编号</label>
	            <input type="text" value="${borrowMoney.contractNo}" name="borrowMoney.contractNo"  class="required" size="30">
	          </dl>
	           
	           
	            <dl class="nowrap">
	            <dt>
	            <label>借款金额</label>
	            </dt>
	           <dd> <input type="text" value="${borrowMoney.borrowMoney}" name="borrowMoney.borrowMoney" class="required number"/>元</dd>
	          </dl>
	
	      <dl class="nowrap">
	           <dt>
	            <label>付款时间</label>
	           </dt>
	           <input name="borrowMoney.borrowTime" type="text" class="date textInput readonly valid" value="${borrowMoney.borrowTime}"/>
	            <a class="inputDateButton" href="javascript:;"></a>
	      </dl>
	
	     
         <dl class="nowrap">
	       <dt>
	         <label>借款缘由</label>
	       </dt>
	      <s:textarea name="borrowMoney.borrowReason" ros="40" cols="60"/>
	     </dl>
	    
</div>

			<div class="formBar">
				<ul>

					<input type="hidden" name="formAction" value="save" />
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">申请</button>
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
