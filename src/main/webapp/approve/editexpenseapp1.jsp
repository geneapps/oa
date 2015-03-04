<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="page">
<div class="pageContent">
<form action="/oa/approve/editexpenseapp1.action" method="post"
                 class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">

	  <div class="pageFormContent" layoutH="100"> 
	   
	         <dl class="nowrap">
	            <dt>
	            <label>报销标题</label>
	            </dt>
	            <input name="application.id" type="hidden"	value="${application.id}" />
	            <input name="expense.id" type="hidden"	value="${expense.id}" />
	           
	            <input name="application.title" type="text" size="50" value="${application.title}"/>
	          </dl>
	          
	          <dl class="nowrap">
	            <dt>
	            <label>报销类型</label>
	            </dt>
	            
	            <s:select name= "typeParma" list="#{'EXPENSEMAN':'人工费用报销','EXPENSEMATERIAL':'材料费用报销','EXPENSEOTHER':'间接费用报销'}" headerKey="no" 
	            	headerValue="请选择"   listKey="key" listValue="value"   
	            	value ="#{expense != null ? expense.expenseType : ''}" disabled ="#{application.id != null}"> </s:select>
	          </dl>
	          
	          <dl class="nowrap">
	            <dt>
	            <label>借款单号</label>
	            </dt>
	            <input type="text" name="expense.borrowNumber" value="${expense.borrowNumber}" size="50"/>
	          </dl>
	          
	          <div class="divider"></div>
					<dl class="nowrap">
						<table class="list nowrap itemDetail" addButton="添加附件"
							width="100%">
							<thead>
								<tr>
									<th type="attach" name="fileLogList[#index#].fileName"
										lookupGroup="fileLogList[#index#]"
										lookupUrl="/oa/common/filelookup.action?fileLog.businessType=CONTRACT"
										size="80">文件名</th>
									<th type="del" width="60" title="确定要删除吗?">操作</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="fileLogList" id="obj" status='st'>
									<tr class="unitBox">
										<td><input type="hidden"
											name="fileLogList[<s:property value='#st.index'/>].id"
											value="<s:property value='id'/>"> <input
											class="textInput readonly" type="text" readonly="readonly"
											size="80"
											name="fileLogList[<s:property value='#st.index'/>].fileName"
											value="<s:property value='fileName'/>"> <a
											class="btnAttach" title="查找带回" height="300" width="560"
											lookuppk="id" undefined="" lookupgroup="fileLogList[0]"
											href="/oa/common/filelookup.action?fileLog.businessType=EXPENSE">查找带回</a>
										</td>
										<td><a class="btnDel " href="javascript:void(0)">删除</a></td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</dl>
					
					
		<dl class="nowrap">
						<table class="list nowrap itemDetail" addButton="添加费用明细"
							width="100%">
							<thead>
								<tr>
									<th type="lookup" name="costDetailsList[#index#].expenseTime"
										lookupGroup="costDetailsList[#index#]"
										lookupUrl="/oa/expense/costdetaillookup.action"
										size="12">日期</th>
									<th type="text" name="costDetailsList[#index#].expenseType" size="60" fieldClass="required">分类</th>
									<th type="text" name="costDetailsList[#index#].payMoney" defaultVal="" size="12" fieldClass="required">金额</th>
									<th type="del" width="60" title="确定要删除吗?">操作</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="costDetailsList" id="obj" status='st'>
									<tr class="unitBox">
										<td><input type="hidden"
											name="costDetailsList[<s:property value='#st.index'/>].id"
											value="<s:property value='id'/>"> <input
											class="textInput readonly" type="text" readonly="readonly"
											size="12"
											name="costDetailsList[<s:property value='#st.index'/>].expenseTime"
											value="<s:property value='expenseTime'/>"> <a
											class="btnLook" title="查找带回" height="300" width="560"
											lookuppk="id" undefined="" lookupgroup="costDetailsList[<s:property value='#st.index'/>]"
											href="/oa/expense/costdetaillookup.action">查找带回</a>
										</td>
										<td>
						<input class="textInput" readonly="readonly" type="text" size="60" value="<s:property value='expenseType'/>" name="costDetailsList[<s:property value='#st.index'/>].expenseType">
						</td>
						<td>
						<input class="textInput" readonly="readonly" type="text" size="12" value="<s:property value='payMoney'/>" name="costDetailsList[<s:property value='#st.index'/>].payMoney">
						</td>
										<td><a class="btnDel " href="javascript:void(0)">删除</a></td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</dl>
	          
	  <%--   <dl class="nowrap">
	            <dt>
	            <label>合计</label>
	            </dt> 
	           <input type="hidden" name="expense.totalPrice" value="${totalPrice}"/>
	           <font color="red">${totalPrice}元</font>
	          </dl>   --%>
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
