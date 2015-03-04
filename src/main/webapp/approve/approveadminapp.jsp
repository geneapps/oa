<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="page">
<div class="pageContent">
<form action="/oa/approve/approveadminapp.action" method="post"
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
					<label>申请类型</label>
				</dt>
				<s:if test="admin.adminType==1">办公用品</s:if>
				<s:if test="admin.adminType==2">招待费</s:if>
				<s:if test="admin.adminType==3">交通费</s:if>
				<s:if test="admin.adminType==4">生活费</s:if>
				<s:if test="admin.adminType==5">差旅费</s:if>
				<s:if test="admin.adminType==6">其他</s:if>
			</dl>
	           
	           
	     
           <dl class="nowrap">
	       <dt>
	         <label>申请说明</label>
	       </dt>
	      <s:textarea name="admin.instruction" ros="80" cols="60"  />
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
								<button type="submit">审批</button>
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
