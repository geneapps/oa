<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<div class="page">
<div class="pageContent">

	<div class="pageFormContent" layoutH="79"> 
	      <div>
	         <dl class="nowrap">
	            <dt>
	            <label>采购计划标题</label>
	            </dt>
	            ${application.title}
	          </dl>
	          
	          <dl class="nowrap">
	            <dt>
	            <label>项目</label>
	            </dt>
	           <input name="user.id" type="hidden"	value="${user.id}" />
	           ${user.department.depName}
	          </dl>
	          
	 <h1>计划清单</h1>
	
		<table class="table" width="100%" layoutH="auto">
		<thead>
			<tr>
				<th width="120" align="center">品类</th>
				<th width="120" align="center">型号</th>
				<th align="center" width="120">单位</th>
				<th align="center" width="120">数量</th>
				<th width="120" align="center">预算单价</th>
			</tr>
		</thead>
		<tbody>
        
 		<s:iterator value="purchasePlandetails" id="obj">
	  		<tr>
			<td>
			${obj.category}
			</td>
			<td>
			<s:property value="type" />
			</td>
			<td><s:property value="unit" /></td>
			<td><s:property value="number" /></td>
			<td><s:property value="budgetPrice" /></td>
			</tr>
		</s:iterator>

		</tbody>
	</table>
				
	
	  <dl class="nowrap">
	   <dt>
	    <label>预算采购总价</label>
	  </dt>
	     ${purchasePlan.totalPrice} 
	   </dl>
	
     <dl class="nowrap">
	     <dt>
	      <label>采购计划说明</label>
	     </dt>
	    ${purchasePlan.instruction}
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
