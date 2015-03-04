<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<script src="js/jquery-1.7.1.js" type="text/javascript"></script>
 <script type="text/javascript">
  function change() {
	  var Check = $('#check').val();
	  alert(Check);
      var projectId=$("#projectId").attr('value');
      var materialId=$("#materialID").attr('value');
	 $.ajax({ 
         type: "post", 
         url: "/oa/purchaseApply/ajaxselect", 
         data: "{'number':"+Check+",'project.id':"+projectId+",'material.id':"+materialId+"}", 
         contentType: "application/json; charset=utf-8", 
         dataType: "json", 
         success: function(data){    //返回值要改
        		if(data.flag) {
        			$("#info").css("background-color","red"); 
        			}else {
        				$("#info").css("background-color","black"); 
        				}
        			}   
   
     }); 
  }
	 


	</script>  
<div class="pageContent">
<form action="/oa/approve/editpurchaseapplyapp.action" method="post"
                 class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">
			
	  <div class="pageFormContent" layoutH="50"> 
	   
	         <dl class="nowrap">
	            <dt>
	            <label>采购标题</label>
	            </dt>
	            <input name="application.id" type="hidden"	value="${application.id}" />
	            <input name="purchaseApply.id" type="hidden"  value="${purchaseApply.id}" />
	            <input name="application.title" type="text" size="50" value="${application.title}" class="required"/>
	          </dl>
	           <%-- <dl class="nowrap">
	            <dt>
	            <label>所属项目</label>
	            </dt> 
	            <input name="purchaseApply.project.name" type="text" size="50" value="${purchaseApply.project.name}" class="required"/>
	          </dl> --%>
	          <!-- <input type="text" name="number" id="check" onblur="change()"/> -->
	          <dl class="nowrap">
	          <dt>  <label style="font-size: 14px;">采购清单</label></dt>
	          
	          </dl>
	          <dl class="nowrap">
	             
					<table class="list nowrap itemDetail" addButton="添加明细" width="100%">
						<thead>
					        <input type="hidden" name="material.id" id="materialID" value="2">
							<tr id="info">
							
							<th type="lookup" name="materialList[#index#].materialName"
									lookupGroup="materialList[#index#]"
									lookupUrl="/oa/common/materiallookup.action"
									suggestUrl="/oa/common/materiallookupsuggest.action"
									suggestFields="materialName" postField="keywords" size="20" fieldClass="required">材料</th>
								<!-- <th type="text" name="materialList[#index#].category" size="12">品类</th> -->
								<th type="text" name="materialList[#index#].materialType" defaultVal="" size="12" fieldClass="required">规格</th>
								<th type="text" name="materialList[#index#].unit" defaultVal="" size="12" fieldClass="required">单位</th>
								
								<th type="text" name="materialList[#index#].brand" defaultVal="" size="12">品牌</th>
								<th type="text" name="materialList[#index#].price" defaultVal="" size="12" >参考单价</th>
								<th type="text" name="purchaseApplyList[#index#].budgetPrice" defaultVal="" size="12" fieldClass="required number">预算单价</th>
								<th type="text" name="purchaseApplyList[#index#].number"  fieldClass="required digits" size="12"  id="check" onblur="change()">数量</th>
								<th type="text" name="materialList[#index#].supplier.supplyName"  size="12" >供应商</th>
								<th type="enum" enumurl="demo/database/db_select1.html" name="purchaseApplyList[#index#].isContract" defaultVal=""  size="12">有无合同</th>
								<th type="enum" enumurl="demo/database/db_select2.html" name="purchaseApplyList[#index#].isBill" defaultVal=""  size="12">有无发票</th>
								<th type="del" width="60">操作</th>
							</tr>
						</thead>
						<tbody>
						<s:iterator value="purchaseApply.purchaseApplyDetails" id="obj" status='st'>
						<tr class="unitBox">
						<td>
						<input type="hidden" name="materialList[<s:property value='#st.index'/>].id" value="${obj.material.id}">
						<input class="required textInput" type="text" size="12" lookuppk="id" postfield="keywords" suggestfields="materialName" suggesturl="/oa/common/materiallookupsuggest.action" lookupgroup="materialList[<s:property value='#st.index'/>]" autocomplete="off" name="materialList[<s:property value='#st.index'/>].materialName" value="<s:property value='material.materialName'/>">
						<a class="btnLook" title="查找带回" lookuppk="id" postfield="keywords" suggestfields="materialName" suggesturl="/oa/common/materiallookupsuggest.action" autocomplete="off" lookupgroup="materialList[<s:property value='#st.index'/>]" href="/oa/common/materiallookup.action">查找带回</a>
						</td>
						<%-- <td>
						<input class="required textInput" type="text" size="12" value="<s:property value='material.category'/>" name="materialList[<s:property value='#st.index'/>].category">
						</td> --%>
						<td>
						<input class="textInput" type="text" size="12" value="<s:property value='material.materialType'/>" name="materialList[<s:property value='#st.index'/>].materialType">
						</td>
						<td>
						<input class="textInput" type="text" size="12" value="<s:property value='material.unit'/>" name="materialList[<s:property value='#st.index'/>].unit">
						</td>
						<td>
						<input class="textInput" type="text" size="12" value="<s:property value='material.brand'/>" name="materialList[<s:property value='#st.index'/>].brand">
						</td>
						<td>
						<input class=" textInput" type="text" size="12" value="<s:property value='refPrice'/>" name="materialList[<s:property value='#st.index'/>].price">
						</td>
						<td>
						<input class="number textInput" type="text" size="12" value="<s:property value='budgetPrice'/>" name="purchaseApplyList[<s:property value='#st.index'/>].budgetPrice">
						</td>
						<td>
						<input class="digits textInput" type="text" size="12" value="<s:property value='number'/>" name="purchaseApplyList[<s:property value='#st.index'/>].number">
						</td>
						<td>
						<input class="textInput" type="text" size="12" value="<s:property value='material.supplier.supplyName'/>" name="materialList[<s:property value='#st.index'/>].supplier.supplyName">
						</td>
						<td>
						<select  name="purchaseApplyList[<s:property value='#st.index'/>].isContract">
						      <option value="有合同" <s:if test="isContract=='有合同'">selected</s:if>>是</option>
					           <option value="没有合同" <s:if test="isContract=='没有合同'">selected</s:if> >否</option>
					    </select>
						</td>
						<td>
						<select   name="purchaseApplyList[<s:property value='#st.index'/>].isBill">
						      <option value="有发票" <s:if test="isBill=='有发票'">selected</s:if>>是</option>
					          <option value="没有发票" <s:if test="isBill=='没有发票'">selected</s:if>>否</option>
				         </select>
						</td>
						<td>
						<a class="btnDel " href="javascript:void(0)">删除</a>
						</td>
						</tr>
						</s:iterator>
						
				
						</tbody>
					</table>
			</dl>			
	
<%-- 	     <dl class="nowrap">
	       <dt>
	        <label>预算采购总价</label>
	      </dt>
	       <input type="text" name="purchaseApply.totalPrice" size="30" value="${purchaseApply.totalPrice }" class="required number"/>	   
	     </dl> --%>
	     
	     <dl class="nowrap">
	       <input type="hidden" name="purchaseApply.advancePay" size="30" value="null" class="text"/>
	     </dl>
	     
	   
	     <dl class="nowrap">
	       <dt>
	         <label>预计采购时间</label>
	       </dt>
	      <input name="purchaseApply.prePurchaseTime" type="text" class="required date textInput readonly valid" value="${purchaseApply.prePurchaseTime}"/>
	      <a class="inputDateButton" href="javascript:;"></a>
	      </dl>
	      
         <dl class="nowrap">
	       <dt>
	         <label>采购说明</label>
	       </dt>
	      <s:textarea name="purchaseApply.instruction"  ros="4" cols="80">${purchaseApply.instruction}</s:textarea>
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
