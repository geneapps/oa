<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="oa" uri="/oa-tags"%>
			

<div class="accountInfo">
	<div class="alertInfo">
		<!--
		<h2>最新消息：</h2>
		<a href="">深交所：中小散户是股票上市首日买入主体 占比56%</a>
		-->
	</div>
	<div class="left">
							<oa:authority code="3001">				
							<p style="color:red"><a href="<oa:contextPath/>/approve/myapprove.action" target="navTab" rel="main" style="color:red">我的审批</a>：${approveNum}</p>
							</oa:authority>
							<p style="color:red"><a href="<oa:contextPath/>/approve/myapplication.action" target="navTab" rel="main" style="color:red">我的申请</a>：${applicationNum}</p>

	</div>

</div>

