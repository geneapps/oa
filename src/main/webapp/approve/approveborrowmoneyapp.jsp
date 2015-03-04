<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="page">
<div class="pageContent">
<form action="/oa/approve/approveborrowmoneyapp.action" method="post"
                 class="pageForm required-validate"
			onsubmit="return validateCallback(this,dialogAjaxDone,'确认提交审批吗？');">
			
	  <div class="pageFormContent" layoutH="50"> 
	   
	         <dl class="nowrap">
	            <dt>
	            <label>借款标题</label>
	            </dt>
	            <input name="application.id" type="hidden"	value="${application.id}" />
	            <input name="borrowMoney.id" type="hidden"	value="${borrowMoney.id}" />
	           
	            <input name="application.title" type="text" size="50" value="${application.title}" readonly/>
	          </dl>
	          
	          <dl class="nowrap">
	            <dt>
	            <label>借款类型</label>
	            </dt>
					 <s:if test="borrowMoney.borrowType==1">材料采购</s:if>
					 <s:if test="borrowMoney.borrowType==2">生活费</s:if>
					 <s:if test="borrowMoney.borrowType==3">招待费</s:if>
					 <s:if test="borrowMoney.borrowType==4">其他</s:if>
	          </dl>
	          
	          
	              <dl class="nowrap">
	            <label>收款方名称</label>
	            <input type="text" value="${borrowMoney.payee}" name="borrowMoney.payee"  class="required" size="30" readonly>
	          </dl>
	           
	          <dl class="nowrap">
	            <label>收款方账户信息</label>
	            <input type="text" value="${borrowMoney.account}" name="borrowMoney.account"  class="required" size="30" readonly>
	          </dl>
	          <dl class="nowrap">
	            <label>合同编号</label>
	            <input type="text" value="${borrowMoney.contractNo}" name="borrowMoney.contractNo"  class="required" size="30" readonly>
	          </dl>
	           
	          
	          
	          
	           
	            <dl class="nowrap">
	            <dt>
	            <label>借款金额</label>
	            </dt>
	           <dd> <input type="text" value="${borrowMoney.borrowMoney}" name="borrowMoney.borrowMoney" readonly/>元</dd>
	          </dl>
	
	      <dl class="nowrap">
	           <dt>
	            <label>付款时间</label>
	           </dt>
	           <input name="borrowMoney.borrowTime" type="text" class="date textInput readonly valid" value="${borrowMoney.borrowTime}" readonly/>
	            <a class="inputDateButton" href="javascript:;"></a>
	      </dl>
	
	     
         <dl class="nowrap">
	       <dt>
	         <label>借款缘由</label>
	       </dt>
	      <s:textarea name="borrowMoney.borrowReason" ros="40" cols="60"/>
	     </dl>
	     
					
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
								<button type="submit" tar>审批</button>
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
