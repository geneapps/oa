<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="page">
	<div class="pageContent">
		<form method="post" action="/oa/approve/editmancontractapp.action"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">
			
			<input name="contract.contractType" type="hidden" value="0" />
			<input name="contract.meterialContractType" type="hidden" value="-1" />
			
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
							<label>甲方：</label>
						</dt>
						<input name="contract.companyA" type="text" size="50"
							value="${contract.companyA}" class="required" />
					</dl>

					<dl class="nowrap">
						<dt>
							<label>乙方：</label>
						</dt>
						<input name="contract.companyB" type="text" size="50"
							value="${contract.companyB}" class="required" />
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
					<a class="button" href="/oa/worker/import.action" target="dialog"
						title="人工清单导入"><span>人工清单导入</span>
					</a>
					<div class="divider"></div>
					<dl class="nowrap">
						<table class="list nowrap itemDetail" addButton="添加人工清单"
							width="100%">
							<thead>
								<tr>
									<th type="lookup" name="contractorList[#index#].contractorName"
										lookupGroup="contractorList[#index#]"
										lookupUrl="/oa/common/contractorlookup.action"
										suggestUrl="/oa/common/contractorlookupsuggest.action"
										suggestFields="contractorName" postField="keywords" size="36"
										fieldClass="required">分包商</th>
									<th type="text" name="contractorList[#index#].contact"
										defaultVal="" size="24" fieldClass="required">负责人</th>
									<th type="text" name="contractorList[#index#].phone"
										defaultVal="" size="24">联系方式</th>
									<th type="text" name="contractorList[#index#].credit" 
									    defaultVal="" size="24">信誉等级</th>
									<th type="del" width="60">操作</th>
								</tr>


							</thead>
							<tbody>

								<s:iterator value="contract.manContractDetails" id="obj"
									status='st'>
									<tr class="unitBox">
										<td><input type="hidden"
											name="contractorList[<s:property value='#st.index'/>].id"
											value="${obj.contractor.id}"> <input
											class="required textInput" type="text" size="36"
											lookuppk="id" postfield="keywords"
											suggestfields="contractorName"
											suggesturl="/oa/common/contractorlookupsuggest.action"
											lookupgroup="contractorList[<s:property value='#st.index'/>]"
											autocomplete="off"
											name="contractorList[<s:property value='#st.index'/>].contractorName"
											value="<s:property value='contractor.contractorName'/>">
											<a class="btnLook" title="查找带回" lookuppk="id"
											postfield="keywords" suggestfields="contractorName"
											suggesturl="/oa/common/contractorlookupsuggest.action"
											autocomplete="off"
											lookupgroup="contractorList[<s:property value='#st.index'/>]"
											href="/oa/common/contractorlookup.action">查找带回</a></td>
										<td><input class="required textInput" type="text"
											size="24" value="<s:property value='contractor.contact'/>"
											name="contractorList[<s:property value='#st.index'/>].contact">
										</td>
										<td><input class="textInput" type="text" size="24"
											value="<s:property value='contractor.phone'/>"
											name="contractorList[<s:property value='#st.index'/>].phone">
										</td>
										<td><input class="textInput" type="text" size="24"
											value="<s:property value='contractor.credit'/>"
											name="contractorList[<s:property value='#st.index'/>].credit">
										</td>
										<td><a class="btnDel " href="javascript:void(0)">删除</a></td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
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
</div>
