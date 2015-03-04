<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="page">
<div class="pageContent">
	  <div class="pageFormContent" layoutH="50"> 
	   
	         <dl class="nowrap">
	            <dt>
	            <label>报销标题</label>
	            </dt>
	       ${application.title}
	          </dl>
	          
	          <dl class="nowrap">
	            <dt>
	            <label>借款单号</label>
	            </dt>
	           ${expense.borrowNumber}
	          </dl>
	     <dl class="nowrap">     
	    <table class="table" width="100%">
		<thead>
			<tr>
				<th width="120" align="center">日期</th>
				<th width="120" align="center">分类</th>
				<th align="center" width="120">金额</th>
			</tr>
		</thead>
		<tbody>
        
 		<s:iterator value="costDetailsList" id="obj">
	  		<tr target="objId" rel="<s:property value="id"/>">
			<td>
			<s:property value="expenseTime" />
			</td>
			<td><s:property value="expenseType" /></td>
			<td><s:property value="payMoney" /></td>
			</tr>
		</s:iterator>

		</tbody>
	  </table>  
	   </dl>   
	    <dl class="nowrap">
	            <dt>
	            <label>合计</label>
	            </dt> 
	          
	           <font color="red">${expense.totalPrice}元</font>
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
