<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="page">

<div class="pageContent">
			
	  <div class="pageFormContent" layoutH="50"> 
	   
	         <dl class="nowrap">
	            <dt>
	            <label>请款标题</label>
	            </dt>
              ${application.title}
	          </dl>
	          
	          <dl class="nowrap">
	            <dt>
	            <label>请款类型</label>
	            </dt>
        <s:if test="application.applyType=='REQUESTMONEYENGINEER'">工程</s:if>
        <s:if test="application.applyType=='REQUESTMONEYMATERIAL'">材料</s:if>
	          </dl>
	           
	             <dl class="nowrap">
	            <label>收款方名称</label>
	            ${requestMoney.payee}
	          </dl>
	           
	          <dl class="nowrap">
	            <label>收款方账户信息</label>
	           ${requestMoney.account}
	          </dl>
	          <dl class="nowrap">
	            <label>合同编号</label>
	            ${requestMoney.contractNo}
	          </dl>
	           
	           
	           
	            <dl class="nowrap">
	            <dt>
	            <label>请款金额</label>
	            </dt>
	           <dd> ${requestMoney.requestMoney}元</dd>
	          </dl>
	
	       <dl class="nowrap">
	           <dt>
	            <label>付款时间</label>
	           </dt>
               ${requestMoney.payTime}

	      </dl> 
	
	     
         <dl class="nowrap">
	       <dt>
	         <label>请款缘由</label>
	       </dt>
	      ${requestMoney.reason}
	     </dl>
	     
	   <dl class="nowrap">
						<dt>
							<label>附件</label>
						</dt>
					
											<table class="table" width="100%">
						<thead>
							<tr>
								<th align="center">文件名</th>
								<th align="center">操作</th>
							</tr>
						</thead>
						<tbody>
						<s:iterator value="fileLogList" id="obj" status='st'>
							<tr target="sid_obj" rel="<s:property value="id" />">
							<td>
							<s:property value="fileName"/>
							</td>
							<td>
							<a href="<s:property value="fileHttpUrl"/><s:property value="filePath"/><s:property value="id"/><s:property value="fileExt"/>">查看</a>
							</td>
	
							</tr>
						</s:iterator>				
						</tbody>
					</table>
					
								
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
