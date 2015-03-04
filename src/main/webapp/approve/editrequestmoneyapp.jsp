<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="page">
<div class="pageContent">
<form action="/oa/approve/editrequestmoneyapp.action" method="post"
                 class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">
			
	  <div class="pageFormContent" layoutH="50"> 
	   
	         <dl class="nowrap">
	            <dt>
	            <label>请款标题</label>
	            </dt>
	            <input name="application.id" type="hidden"	value="${application.id}" />
	            <input name="requestMoney.id" type="hidden"	value="${requestMoney.id}" />
	           
	            <input name="application.title" type="text" size="50" value="${application.title}" class="required"/>
	          </dl>
	          
	          <dl class="nowrap">
	            <dt>
	            <label>请款类型</label>
	            </dt>
	            <input type="radio" name="requestMoney.requestType" value="REQUESTMONEYENGINEER" <s:if test="application.applyType=='REQUESTMONEYENGINEER'">checked</s:if> class="required"/>工程
	            <input type="radio"  name="requestMoney.requestType" value="REQUESTMONEYMATERIAL" <s:if test="application.applyType=='REQUESTMONEYMATERIAL'">checked</s:if> class="required"/>材料
	          </dl>
	          
	           
	          <dl class="nowrap">
	            <label>收款方名称</label>
	            <input type="text" value="${requestMoney.payee}" name="requestMoney.payee"  class="required" size="30">
	          </dl>
	           
	          <dl class="nowrap">
	            <label>收款方账户信息</label>
	            <input type="text" value="${requestMoney.account}" name="requestMoney.account"  class="required" size="30">
	          </dl>
	          <dl class="nowrap">
	            <label>合同编号</label>
	            <input type="text" value="${requestMoney.contractNo}" name="requestMoney.contractNo"  class="required" size="30">
	          </dl>
	           
	            <dl class="nowrap">
	            <dt>
	            <label>请款金额</label>
	            </dt>
	           <dd> <input type="text" value="${requestMoney.requestMoney}" name="requestMoney.requestMoney"  class="required number"/>元</dd>
	          </dl>
	
	      <dl class="nowrap">
	           <dt>
	            <label>付款时间</label>
	           </dt>
	           <input name="requestMoney.payTime" type="text" class="date textInput readonly valid" value="${requestMoney.payTime}"/>
	            <a class="inputDateButton" href="javascript:;"></a>
	      </dl>
	
	     
         <dl class="nowrap">
	       <dt>
	         <label>请款缘由</label>
	       </dt>
	      <s:textarea name="requestMoney.reason" ros="40" cols="60"/>
	     </dl>
	     
	     <dl class="nowrap">
						<table class="list nowrap itemDetail" addButton="添加附件" width="100%">
						<thead>
							<tr>
								<th type="attach" name="fileLogList[#index#].fileName" 
									lookupGroup="fileLogList[#index#]" 
									lookupUrl="/oa/common/filelookup.action?fileLog.businessType=REQUESTMONEY" size="80">文件名</th>	
								<th type="del" width="60" title="确定要删除吗?">操作</th>
							</tr>
						</thead>
						<tbody>
						<s:iterator value="fileLogList" id="obj" status='st'>
						<tr class="unitBox">
						<td>
						<input type="hidden" name="fileLogList[<s:property value='#st.index'/>].id" value="<s:property value='id'/>">
						<input class="textInput readonly" type="text" readonly="readonly" size="80" name="fileLogList[<s:property value='#st.index'/>].fileName" value="<s:property value='fileName'/>">
						<a class="btnAttach" title="查找带回" height="300" width="560" lookuppk="id" undefined="" lookupgroup="fileLogList[0]" href="/oa/common/filelookup.action?fileLog.businessType=REQUESTMONEY">查找带回</a>
						</td>
						<td>
						<a class="btnDel " href="javascript:void(0)">删除</a>
						</td>
						</tr>
						</s:iterator>
						
						</tbody>
						</table>					
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
