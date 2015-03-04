<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="page">
<div class="pageContent">

	  <div class="pageFormContent" layoutH="50"> 
	   
	         <dl class="nowrap">
	            <dt>
	            <label>借款标题</label>
	            </dt>
	            ${application.title}
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
	           <dd> ${borrowMoney.borrowMoney}元</dd>
	          </dl>
	
	      <dl class="nowrap">
	           <dt>
	            <label>付款时间</label>
	           </dt>
	           ${borrowMoney.borrowTime}

	      </dl>
	
	     
         <dl class="nowrap">
	       <dt>
	         <label>借款缘由</label>
	       </dt>
	      ${borrowMoney.borrowReason}
	     </dl>
	     
					
	        		
	                  审批意见：
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
