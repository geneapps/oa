<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="pageContent">
<form action="/oa/approve/editpurchaseplanapp.action" method="post"
            class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">
	  <div class="pageFormContent" layoutH="50"> 
	   
	         <dl class="nowrap">
	            <dt>
	            <label>采购计划标题</label>
	            </dt>
	            <input name="application.id" type="hidden"	value="${application.id}" />
	            <input name="purchasePlan.id" type="hidden"	value="${purchasePlan.id}" />
	           
	            <input name="application.title" type="text" size="50" value="${application.title}" class="required"/>
	          </dl>
	          
	          <dl class="nowrap">
	            <dt>
	            <label>项目</label>
	            </dt>
	            <input name="user.id" type="hidden"	value="${user.id}" />
	            <input name="department.id" type="hidden" value="${department.id}" />
	            <input name="department.depName" type="text" size="50" value="${department.depName}" readonly="readonly">
	          </dl>
	          <h1>计划清单</h1>
			<dl class="nowrap">
	
					<table class="list nowrap itemDetail" addButton="添加明细" width="100%">
						<thead>
							<tr>
							<th type="lookup" name="materialList[#index#].materialName"
									lookupGroup="materialList[#index#]"
									lookupUrl="/oa/common/materiallookup.action"
									suggestUrl="/oa/common/materiallookupsuggest.action"
									suggestFields="materialName" postField="keywords" size="24" fieldClass="required">材料</th>
								<th type="text" name="materialList[#index#].category" size="12" fieldClass="required">品类</th>
								<th type="text" name="materialList[#index#].materialType" defaultVal="" size="12" fieldClass="required">型号</th>
								<th type="text" name="materialList[#index#].unit" defaultVal="" size="12" fieldClass="required">单位</th>
								<th type="text" name="materialList[#index#].price" defaultVal="" size="12" fieldClass="required number">预算单价</th>
								<th type="text" name="purchasePlanList[#index#].number" defaultVal="" fieldClass="required digits" size="12">数量</th>
								<th type="text" name="materialList[#index#].supplier.supplyName" size="12" fieldClass="required">供应商</th>
								
								<th type="del" width="60">操作</th>
							</tr>
						</thead>
						<tbody>
						<s:iterator value="purchasePlan.purchasePlanDetails" id="obj" status='st'>
						<tr class="unitBox">
						<td>
						<input type="hidden" name="materialList[<s:property value='#st.index'/>].id" value="${obj.material.id}">
						<input class="required textInput" type="text" size="24" lookuppk="id" postfield="keywords" suggestfields="materialName" suggesturl="/oa/common/materiallookupsuggest.action" lookupgroup="materialList[<s:property value='#st.index'/>]" autocomplete="off" name="materialList[<s:property value='#st.index'/>].materialName" value="<s:property value='material.materialName'/>">
						<a class="btnLook" title="查找带回" lookuppk="id" postfield="keywords" suggestfields="materialName" suggesturl="/oa/common/materiallookupsuggest.action" autocomplete="off" lookupgroup="materialList[<s:property value='#st.index'/>]" href="/oa/common/materiallookup.action">查找带回</a>
						</td>
						<td>
						<input class="required textInput" type="text" size="12" value="<s:property value='material.category'/>" name="materialList[<s:property value='#st.index'/>].category">
						</td>
						<td>
						<input class="textInput" type="text" size="12" value="<s:property value='material.materialType'/>" name="materialList[<s:property value='#st.index'/>].materialType">
						</td>
						<td>
						<input class="textInput" type="text" size="12" value="<s:property value='material.unit'/>" name="materialList[<s:property value='#st.index'/>].unit">
						</td>
						<td>
						<input class="required textInput number" type="text" size="12" value="<s:property value='budgetPrice'/>" name="materialList[<s:property value='#st.index'/>].price">
						</td>
						<td>
						<input class="digits textInput" type="text" size="12" value="<s:property value='number'/>" name="purchasePlanList[<s:property value='#st.index'/>].number">
						</td>
						<td>
						<input class="textInput" type="text" size="12" value="<s:property value='material.supplier.supplyName'/>" name="materialList[<s:property value='#st.index'/>].supplier.supplyName">
						</td>
						
						<td>
						<a class="btnDel " href="javascript:void(0)">删除</a>
						</td>
						</tr>
						</s:iterator>
						
						</tbody>
					</table>
			</dl>			
	     <dl class="nowrap">
	       <dt>
	        <label>预算采购总价</label>
	      </dt>
	       <input type="text" name="purchasePlan.totalPrice" size="30" value="${purchasePlan.totalPrice}" class="required number"/>
	     </dl>
	
         <dl class="nowrap">
	       <dt>
	         <label>采购计划说明</label>
	       </dt>
	      <s:textarea name="purchasePlan.instruction" ros="30" cols="60"/>
	      </dl>


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
