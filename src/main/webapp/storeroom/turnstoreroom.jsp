<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="pageContent">
<form action="/oa/storeroom/turningstoreroom.action" method="post"
                class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			
	  <div class="pageFormContent" layoutH="50"> 
	    <input type="hidden" name="storeRoom.id"/>
	           <dl class="nowrap">
	              <dt>  <label style="font-size: 14px;">转出库房</label></dt>
	               <select  class="required combox" disabled="disabled" size="20">
					<option value="${storeRoom.name}" selected >${storeRoom.name}</option>
				</select>
	           </dl>
	            <dl class="nowrap">
	              <dt>  <label style="font-size: 14px;">转入库房</label></dt>
	             <s:select list="listall" name="name"   headerKey="1"
	                listKey="name" listValue="name" 
                      class="required combox" /> 
	           </dl>
	          <dl class="nowrap">
	          <dt>  <label style="font-size: 14px;">出库清单</label></dt>
	          </dl>
	           <dl class="nowrap">
	             
					<table class="list nowrap itemDetail" addButton="添加明细" width="100%">
						<thead>
							<tr id="info">
							<th type="lookup" name="storeRoomMaterialList[#index#].material.materialName"
									lookupGroup="storeRoomMaterialList[#index#]"
									lookupUrl="/oa/storeroom/storeRoomMateriallookup.action"
									suggestUrl="/oa/common/storeRoomMateriallookupsuggest.action"
									suggestFields="materialName" postField="keywords" size="20" fieldClass="readonly">材料</th>
								<th type="text" name="storeRoomMaterialList[#index#].material.materialType"  size="12" fieldClass="readonly">规格</th>
								<th type="text" name="storeRoomMaterialList[#index#].material.unit"  size="12" fieldClass="readonly">单位</th>							
								<th type="text" name="storeRoomMaterialList[#index#].number" size="12" fieldClass="readonly">库房数量</th>
								<th type="text" name="outNumbers[#index#]"  fieldClass="required digits" size="12"  id="check" onblur="change()">出库数量</th>
								<th type="del" width="60">操作</th>
							</tr>
						</thead>
						<tbody>
			
						</tbody>
					</table>
			</dl>				
		
	     </div>
			<div class="formBar">
				<ul>

					<input type="hidden" name="formAction" value="save" />
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">确定</button>
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
