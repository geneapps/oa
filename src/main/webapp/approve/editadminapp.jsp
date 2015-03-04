<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="page">
<div class="pageContent">
<form action="/oa/approve/editadminapp.action" method="post"
                 class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">
			
	  <div class="pageFormContent" layoutH="50"> 
	   
	         <dl class="nowrap">
	            <dt>
	            <label>行政申请标题</label>
	            </dt>
	            <input name="application.id" type="hidden"	value="${application.id}" />
	            <input name="admin.id" type="hidden"  value="${admin.id}" />
	           
	            <input name="application.title" type="text" size="50" value="${application.title}" class="required"/>
	          </dl>
	          
	          <dl class="nowrap">
	            <dt>
	            <label>行政申请类型</label>
	            </dt>
	             <select name="admin.adminType" class="required combox">
					<option value="1" <s:if test="admin.adminType==1">selected</s:if>>办公用品</option>
					<option value="2" <s:if test="admin.adminType==2">selected</s:if>>招待费</option>
					<option value="3" <s:if test="admin.adminType==3">selected</s:if>>交通费</option>
					<option value="4" <s:if test="admin.adminType==4">selected</s:if>>生活费</option>
					<option value="5" <s:if test="admin.adminType==5">selected</s:if>>差旅费</option>
					<option value="6" <s:if test="admin.adminType==6">selected</s:if>>其他</option>
				</select>
	            
	          </dl>
	          
	           
	     
         <dl class="nowrap">
	       <dt>
	         <label>申请说明</label>
	       </dt>
	      <s:textarea name="admin.instruction" ros="80" cols="60"  />
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
						<a class="btnAttach" title="查找带回" height="300" width="560" lookuppk="id" undefined="" lookupgroup="fileLogList[0]" href="/oa/common/filelookup.action?fileLog.businessType=ADMIN">查找带回</a>
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
