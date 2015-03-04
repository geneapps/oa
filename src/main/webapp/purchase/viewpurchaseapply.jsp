<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="page">
	<div class="pageContent">
		<form method="post" action="/oa/purchase/purchaseflow.action"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">
			<div class="pageFormContent" layoutH="56">
				<div>
					<dl class="nowrap">
						<dt>
							<label>进度：</label>
						</dt>
						<s:if test="purchaseApply.status==0">等待审批</s:if>
						<s:elseif test="purchaseApply.status==1">等待付款</s:elseif>
						<s:elseif test="purchaseApply.status==2">开始采购</s:elseif>
						<s:elseif test="purchaseApply.status==3">等待报销</s:elseif>
						<s:elseif test="purchaseApply.status==4">结束</s:elseif>
						<s:elseif test="purchaseApply.status==5">审批不通过</s:elseif>
					</dl>
					<dl class="nowrap">
						<dt>
							<label>标题：</label>
						</dt>
						<input name="purchaseApply.id" type="hidden"
							value="${purchaseApply.id}" />
						${purchaseApply.title}
					</dl>

					<dl class="nowrap">
						<dt>
							<label>采购说明：</label>
						</dt>
						${purchaseApply.instruction}
					</dl>

					<dl class="nowrap">
						<dt>
							<label>采购总价：</label>
						</dt>
						${purchaseApply.totalPrice}
					</dl>
					<%-- <dl class="nowrap">
						<dt>
							<label>预支金额：</label>
						</dt>
						${purchaseApply.advancePay}
					</dl> --%>
					<dl class="nowrap">
						<dt>
							<label>采购时间：</label>
						</dt>
						${purchaseApply.prePurchaseTime}
					</dl>
					<div class="divider"></div>	
					
					<dl class="nowrap">
						<table class="table" width="100%" layoutH="167">
							<thead>
								<tr>
									<th align="center">材料名称</th> 
									<th align="center">型号</th>
									<th align="center">单位</th>
									<th align="center">预算单价</th>
									<th align="center">参考单价</th>
									<th align="center">数量</th>
									<th align="center">采购单价</th>
									<th align="center">入库数量</th>
								</tr>
							</thead>
							<tbody>

							<s:iterator value="purchaseApply.purchaseApplyDetails" id="obj">
								<tr target="objId" rel="<s:property value="id" />">
								<td>
									<s:property value="material.materialName" />
								</td>		
								<td>
									<s:property value="type" />
								</td>
								<td>
								<s:property value="unit" />
								</td>
								<td>
								<s:property value="budgetPrice" />
								</td>
								<td>
								<s:property value="refPrice" />
								</td>
								<td>
								<s:property value="number" />
								</td>
								<td>
								<s:property value="actualPrice" />
								</td>
								<td>
								<s:property value="houseNumber" />
								</td>
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
					<li><s:if test="purchaseApply.status==3"><div class="buttonActive">
								
								<div class="buttonContent">
								<button type="submit">报销</button>
								</div>							
						</div></li></s:if>
				</ul>
			</div>
		</form>
	</div>
</div>
