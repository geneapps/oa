<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="page">
	<div class="pageContent">
		<form method="post" action="/oa/approve/editcontractapp.action"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">
			<input name="contract.contractType" type="hidden" value="1" />
			<div class="pageFormContent" layoutH="56">
				<div>
					<dl class="nowrap">
						<dt>
							<label>合同标题：</label>
						</dt>
						<input name="application.id" type="hidden"
							value="${application.id}" />
						<input name="contract.id" type="hidden" value="${contract.id}" />
						<input name="application.title" type="text" size="50"
							value="${application.title}" class="required"></input>
					</dl>
					
					<dl class="nowrap">
					<dt>
						<label>所属项目</label>
						</dt>
						<s:select name="id" list="projectList" headerKey="-1" headerValue="请选择" listKey="id"
						listValue="name" value="%{contract.project.id}" required="true"/>				
					</dl>

					<dl class="nowrap">
						<dt>
							<label>材料合同类型：</label>
						</dt>
						<input name="contract.meterialContractType" type="radio" value="0" <s:if test="contract.meterialContractType==0">checked</s:if> required/>水电材料
						<input name="contract.meterialContractType" type="radio" value="1" <s:if test="contract.meterialContractType==1">checked</s:if> required/>装饰材料  
					</dl>
					


					<dl class="nowrap">
						<dt>
							<label>甲方：</label>
						</dt>
						<input name="contract.companyA" type="text" size="50"
							value="${contract.companyA}" class="required"/>
					</dl>
					
					<dl class="nowrap">
						<dt>
							<label>乙方：</label>
						</dt>
						<input name="contract.companyB" type="text" size="50"
							value="${contract.companyB}"  class="required"/>
					</dl>
					
					<dl class="nowrap">
						<dt>
							<label>乙方经办人：</label>
						</dt>
						<input name="contract.B_contact" type="text" size="20"
							value="${contract.b_contact}" class="required"/>
					</dl>
					
					<dl class="nowrap">
						<dt>
							<label>经办人联系方式：</label>
						</dt>
						<input name="contract.B_contact_phone" type="text" size="20"
							value="${contract.b_contact_phone}" class="required"/>
					</dl>
					
					<dl class="nowrap">
						<dt>
							<label>合同缘由：</label>
						</dt>
						<textarea name="contract.contractReason" cols="80" rows="4">${contract.contractReason}</textarea>
					</dl>
					<div class="divider"></div>			
					<dl class="nowrap">
						<table class="list nowrap itemDetail" addButton="添加明细" width="100%">
						<thead>
							<tr>
								<th type="lookup" name="materialList[#index#].materialName"
									lookupGroup="materialList[#index#]"
									lookupUrl="/oa/common/materiallookup.action"
									suggestUrl="/oa/common/materiallookupsuggest.action"
									suggestFields="materialName" postField="keywords" size="24" fieldClass="required">材料</th>
<!-- 								<th type="text" name="materialList[#index#].category" defaultVal=""
									size="12" fieldClass="required">品类</th> -->
								<th type="text" name="materialList[#index#].materialType"
									 size="12">型号</th>								
								<th type="text" name="materialList[#index#].unit" defaultVal=""
									size="12">单位</th>
								<th type="text" name="materialList[#index#].price" defaultVal=""
									size="12" fieldClass="number">单价</th>
								<th type="text" name="detailList[#index#].number" fieldClass="digits"
									defaultVal="1" size="12">数量</th>
								
				
								<th type="del" width="60">操作</th>
							</tr>
							

						</thead>
						<tbody>
						
						<s:iterator value="contract.contractDetails" id="obj" status='st'>
						<tr class="unitBox">
						<td>
						<input type="hidden" name="materialList[<s:property value='#st.index'/>].id" value="${obj.material.id}">
						<input class="required textInput" type="text" size="24" lookuppk="id" postfield="keywords" suggestfields="materialName" suggesturl="/oa/common/materiallookupsuggest.action" lookupgroup="materialList[<s:property value='#st.index'/>]" autocomplete="off" name="materialList[<s:property value='#st.index'/>].materialName" value="<s:property value='material.materialName'/>">
						<a class="btnLook" title="查找带回" lookuppk="id" postfield="keywords" suggestfields="materialName" suggesturl="/oa/common/materiallookupsuggest.action" autocomplete="off" lookupgroup="materialList[<s:property value='#st.index'/>]" href="/oa/common/materiallookup.action">查找带回</a>
						</td>
						<td>
					<%-- 	<input class="required textInput" type="text" size="12" value="<s:property value='material.category'/>" name="materialList[<s:property value='#st.index'/>].category"> --%>
						</td>
						<td>
						<input class="textInput" type="text" size="12" value="<s:property value='material.materialType'/>" name="materialList[<s:property value='#st.index'/>].materialType">
						</td>
						<td>
						<input class="textInput" type="text" size="12" value="<s:property value='material.unit'/>" name="materialList[<s:property value='#st.index'/>].unit">
						</td>
						<td>
						<input class="number textInput" type="text" size="12" value="<s:property value='budgetPrice'/>" name="materialList[<s:property value='#st.index'/>].price">
						</td>
						<td>
						<input class="digits textInput" type="text" size="12" value="<s:property value='number'/>" name="detailList[<s:property value='#st.index'/>].number">
						</td>
						<td>
						<a class="btnDel " href="javascript:void(0)">删除</a>
						</td>
						</tr>
						</s:iterator>
						</tbody>
						</table>					
					</dl>
					<div class="divider"></div>
					<dl class="nowrap">
						<table class="list nowrap itemDetail" addButton="添加附件" width="100%">
						<thead>
							<tr>
								<th type="attach" name="fileLogList[#index#].fileName" 
									lookupGroup="fileLogList[#index#]" 
									lookupUrl="/oa/common/filelookup.action?fileLog.businessType=CONTRACT" size="80">文件名</th>	
								<th type="del" width="60" title="确定要删除吗?">操作</th>
							</tr>
						</thead>
						<tbody>
						<s:iterator value="fileLogList" id="obj" status='st'>
						<tr class="unitBox">
						<td>
						<input type="hidden" name="fileLogList[<s:property value='#st.index'/>].id" value="<s:property value='id'/>">
						<input class="textInput readonly" type="text" readonly="readonly" size="80" name="fileLogList[<s:property value='#st.index'/>].fileName" value="<s:property value='fileName'/>">
						<a class="btnAttach" title="查找带回" height="300" width="560" lookuppk="id" undefined="" lookupgroup="fileLogList[0]" href="/oa/common/filelookup.action?fileLog.businessType=CONTRACT">查找带回</a>
						</td>
						<td>
						<a class="btnDel " href="javascript:void(0)">删除</a>
						</td>
						</tr>
						</s:iterator>
						</tbody>
						</table>					
					</dl>
					<div class="divider"></div>
					<dl class="nowrap">
					<a class="button"  href="/oa/material/import.action" target="dialog" title="材料导入"><span>材料导入</span></a>




				</div>

			</div>


			<div class="formBar">
				<ul>
					<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
					<input type="hidden" name="formAction" value="save" />
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">保存</button>
							</div>
						</div></li>
					<li>
						<div class="button">
							<div class="buttonContent">
								<button type="button" class="close">取消</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</form>
	</div>
</div>
