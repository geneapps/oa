<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="oa" uri="/oa-tags"%>
<div class="pageHeader">

</div>
<div class="pageContent" id="approveRel">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="edit" href="/oa/paymentorder/pay.action?paymentLog.paymentOrder.id={objId}"  target="dialog"><span>付款</span></a></li>
		</ul>
	</div>

<%@ include file="includelist.jsp"%> 

	<div id="payLog" class="unitBox">
	</div>

</div>
