<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="page">
<div class="pageContent">
<form action="/oa/approve/editexpenseapp.action" method="post"
                 class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">
	<%-- <div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="/oa/expense/editcostdetails.action" target="dialog" rel="costDetailsTab"><span>添加费用明细</span></a></li>
			<li><a class="edit" href="/oa/expense/editcostdetails.action?costDetails.id={objId}" target="dialog" rel="costDetailsTab"><span>编辑费用明细</span></a></li>
		</ul>
	</div>	 --%>	
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
											href="/oa/common/filelookup.action?fileLog.businessType=CONTRACT">查找带回</a>
										</td>
										<td><a class="btnDel " href="javascript:void(0)">删除</a></td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</dl>
	          
	     <dl class="nowrap">  
	     <a class="button" href="/oa/expense/editcostdetails.action" target="dialog" rel="costDetailsTab"><span>添加费用明细</span></a>
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
	           <input type="hidden" name="expense.totalPrice" value="${totalPrice}"/>
	           <font color="red">${totalPrice}元</font>
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
