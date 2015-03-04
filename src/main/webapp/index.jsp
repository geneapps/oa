<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="oa" uri="/oa-tags"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>OA</title>

<link href="themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
<!--[if IE]>
<link href="themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->

<!--[if lte IE 9]>
<script src="js/speedup.js" type="text/javascript"></script>
<![endif]-->

<script src="js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="js/jquery.cookie.js" type="text/javascript"></script>
<script src="js/jquery.validate.js" type="text/javascript"></script>
<script src="js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="xheditor/xheditor-1.1.14-zh-cn.min.js" type="text/javascript"></script>
<script src="uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>

<!-- svg图表  supports Firefox 3.0+, Safari 3.0+, Chrome 5.0+, Opera 9.5+ and Internet Explorer 6.0+ -->
<script type="text/javascript" src="chart/raphael.js"></script>
<script type="text/javascript" src="chart/g.raphael.js"></script>
<script type="text/javascript" src="chart/g.bar.js"></script>
<script type="text/javascript" src="chart/g.line.js"></script>
<script type="text/javascript" src="chart/g.pie.js"></script>
<script type="text/javascript" src="chart/g.dot.js"></script>

<script src="js/dwz.core.js" type="text/javascript"></script>
<script src="js/dwz.util.date.js" type="text/javascript"></script>
<script src="js/dwz.validate.method.js" type="text/javascript"></script>
<script src="js/dwz.regional.zh.js" type="text/javascript"></script>
<script src="js/dwz.barDrag.js" type="text/javascript"></script>
<script src="js/dwz.drag.js" type="text/javascript"></script>
<script src="js/dwz.tree.js" type="text/javascript"></script>
<script src="js/dwz.accordion.js" type="text/javascript"></script>
<script src="js/dwz.ui.js" type="text/javascript"></script>
<script src="js/dwz.theme.js" type="text/javascript"></script>
<script src="js/dwz.switchEnv.js" type="text/javascript"></script>
<script src="js/dwz.alertMsg.js" type="text/javascript"></script>
<script src="js/dwz.contextmenu.js" type="text/javascript"></script>
<script src="js/dwz.navTab.js" type="text/javascript"></script>
<script src="js/dwz.tab.js" type="text/javascript"></script>
<script src="js/dwz.resize.js" type="text/javascript"></script>
<script src="js/dwz.dialog.js" type="text/javascript"></script>
<script src="js/dwz.dialogDrag.js" type="text/javascript"></script>
<script src="js/dwz.sortDrag.js" type="text/javascript"></script>
<script src="js/dwz.cssTable.js" type="text/javascript"></script>
<script src="js/dwz.stable.js" type="text/javascript"></script>
<script src="js/dwz.taskBar.js" type="text/javascript"></script>
<script src="js/dwz.ajax.js" type="text/javascript"></script>
<script src="js/dwz.pagination.js" type="text/javascript"></script>
<script src="js/dwz.database.js" type="text/javascript"></script>
<script src="js/dwz.datepicker.js" type="text/javascript"></script>
<script src="js/dwz.effects.js" type="text/javascript"></script>
<script src="js/dwz.panel.js" type="text/javascript"></script>
<script src="js/dwz.checkbox.js" type="text/javascript"></script>
<script src="js/dwz.history.js" type="text/javascript"></script>
<script src="js/dwz.combox.js" type="text/javascript"></script>
<script src="js/dwz.print.js" type="text/javascript"></script>
<!--
<script src="bin/dwz.min.js" type="text/javascript"></script>
-->
<script src="js/dwz.regional.zh.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){
	DWZ.init("dwz.frag.xml", {
		loginUrl:"login_dialog.html", loginTitle:"登录",	// 弹出登录对话框
//		loginUrl:"login.html",	// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"themes"}); // themeBase 相对于index页面的主题base路径
		}
	});
});
</script>
</head>

<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<a class="logo" href="#">标志</a>
				<ul class="nav">
					<li><a href="#">当前登录用户：${user.realName}</a></li>
					<li><a href="/oa/user/editpwd.action" target="dialog">修改密码</a></li>
					<li><a href="/oa/logout.action">退出</a></li>
				</ul>
				<ul class="themeList" id="themeList">
					<li theme="default"><div class="selected">蓝色</div></li>
					<li theme="green"><div>绿色</div></li>
					<!--<li theme="red"><div>红色</div></li>-->
					<li theme="purple"><div>紫色</div></li>
					<li theme="silver"><div>银色</div></li>
					<li theme="azure"><div>天蓝</div></li>
				</ul>
			</div>

			<!-- navMenu -->
			
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>

				<div class="accordion" fillSpace="sidebar">
					<div class="accordionHeader">
						<h2><span>Folder</span>我的菜单</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li>
							<a href="<oa:contextPath/>/main.action" target="navTab" rel="main">待办事项</a>
							</li>
						
							<oa:authority code="1002">
								<li>
								<a>用户管理</a>
								<ul>
									<li><a href="/oa/user/userlist.action" target="navTab" rel="main">用户管理</a></li>
									<li><a href="<oa:contextPath/>/organization/organizationlist.action" target="navTab" rel="main">组织管理</a></li>
								</ul>
								</li>
							</oa:authority> 
							
							<oa:authority code="2001,2002,2003">
							<li>
							<a>项目管理</a>
								<ul>
								    <oa:authority code="2002">									
									<li><a href="<oa:contextPath/>/project/projectlist.action" target="navTab" rel="main">项目信息</a></li>
									</oa:authority>
									<oa:authority code="2003">
									<li><a href="<oa:contextPath/>/project/projectreportlist.action" target="navTab" rel="main">项目报表</a></li>
									</oa:authority>
									<oa:authority code="2001">	
									<li><a href="<oa:contextPath/>/contractor/contractorlist.action" target="navTab" rel="main">分包商管理</a></li>
									</oa:authority>
								</ul>
							</li>
							</oa:authority>
							
							
							<li>
							<a>创建申请</a>
								<ul>
									<oa:authority code="2004">									
									<li><a href="/oa/approve/editcontractapp.action" target="navTab" rel="main">创建材料合同审批申请</a></li>
									<li><a href="/oa/approve/editmancontractapp.action" target="navTab" rel="main">创建人工合同审批申请</a></li>
									</oa:authority>
									<oa:authority code="2102">
									<li><a href="/oa/approve/editpurchaseplanapp.action" target="navTab" rel="main">创建采购计划申请</a></li>
									<li><a href="/oa/approve/editpurchaseapplyapp.action" target="navTab" rel="main">创建采购申请</a></li>
									</oa:authority>
									<li><a href="/oa/approve/editrequestmoneyapp.action" target="navTab" rel="main">创建请款申请</a></li>
									<li><a href="/oa/approve/editborrowmoneyapp.action" target="navTab" rel="main">创建借款申请</a></li>
                                    <li><a href="/oa/expense/costlist.action" target="navTab" rel="main">创建费用报销申请</a></li>
                                    <li><a href="/oa/approve/editadminapp.action" target="navTab" rel="main">创建行政申请</a></li>
								</ul>
							</li>


							<li>
							<a>审批管理</a>
								<ul>
									<li><a href="/oa/approve/myapplication.action" target="navTab" rel="main">我的申请</a></li>
									<li><a href="/oa/approve/historyapplication.action" target="navTab" rel="main">历史申请</a></li>
									<li><a href="/oa/approve/myapprove.action" target="navTab" rel="main">我的审批</a></li>
									<li><a href="/oa/approve/historyapprove.action" target="navTab" rel="main">历史审批</a></li>
								</ul>
							</li>
							
							<li>
							<a>财务管理</a>
								<ul>
									<oa:authority code="4001">
										<li><a href="/oa/paymentorder/listapprove.action" target="navTab" rel="main">付款审批</a></li>
									</oa:authority>
									<oa:authority code="4002">
										<li><a href="/oa/paymentorder/listpay.action" target="navTab" rel="main">等待付款</a></li>
										<li><a href="/oa/paymentorder/historypay.action" target="navTab" rel="main">付款历史</a></li>
									</oa:authority>
										<li><a href="/oa/paymentorder/mylist.action" target="navTab" rel="main">我的付款</a></li>
								</ul>
							</li>
							
							
							
							<oa:authority code="5001,5002">
								<li>
								<a>库存管理</a>
								<ul>
									<oa:authority code="5001">
									<li><a href="/oa/storeroom/list.action" target="navTab" rel="main">材料出库</a></li>
									<li><a href="/oa/storeroom/turnstoreroom.action" target="navTab" rel="main">转库出库</a></li>
									<li><a href="/oa/storeroom/purchaselist.action" target="navTab" rel="main">采购单入库</a></li>
									</oa:authority>

									<oa:authority code="5002">
									<li><a href="/oa/storeroom/stocklist.action" target="navTab" rel="main">库房库存列表</a></li>
									<li><a href="/oa/storeroom/storeroomoutlist.action" target="navTab" rel="main">库房出库列表</a></li>
									<li><a href="/oa/storeroom/storeroomincomelist.action" target="navTab" rel="main">库房入库列表</a></li>
									</oa:authority>
								</ul>
							
							</li> 
							</oa:authority>
							
							<oa:authority code="2008,2010">
							<li>
							<a>材料管理</a>
							<ul>
								<oa:authority code="2010"><!-- 具有修改和删除权限的材料维护 -->
								<li><a href="/oa/material/list.action" target="navTab" rel="main">材料维护</a></li>
								</oa:authority>
								
								<oa:authority code="2008">
									<li><a href="/oa/material/commonlist.action" target="navTab" rel="main">材料维护</a></li>
									<li><a href="<oa:contextPath/>/supplier/supplierList.action" target="navTab" rel="main">供应商管理</a></li>
								</oa:authority>
							</ul>
							</li>
							</oa:authority>
							
							<oa:authority code="2003,2104,2007">
							<li>
							<a>文件夹</a>
								<ul>
								<oa:authority code="2003">
									<li><a href="/oa/contract/contractList.action" target="navTab" rel="main">材料合同</a></li>
									<li><a href="/oa/contract/mancontractList.action" target="navTab" rel="main">人工合同</a></li>					
								</oa:authority>
								<oa:authority code="2104">
									<li><a href="/oa/purchaseapply/mylist.action" target="navTab" rel="main">采购单</a></li>
									<li><a href="/oa/purchaseplan/list.action" target="navTab" rel="main">采购计划单</a></li>																   																	   
								</oa:authority>
								<oa:authority code="2007">
								    <li><a href="/oa/expense/expenselist.action" target="navTab" rel="main">费用报销单</a></li>
								    <li><a href="/oa/requestmoney/requestmoneylist.action" target="navTab" rel="main">请款单</a></li>
								    <li><a href="/oa/borrowmoney/borrowmoneylist.action" target="navTab" rel="main">借款单</a></li>
								</oa:authority>
									<li><a href="/oa/admin/adminlist.action" target="navTab" rel="main">行政申请单</a></li>
								</ul>
							</li>
							</oa:authority>
							
							<oa:authority code="1001">
							<li><a>系统配置</a>
								<ul>
									<li><a href="/oa/system/list.action" target="navTab" rel="main">参数列表</a></li>
									<li><a href="/oa/rolemanage/actionlist.action" target="navTab" rel="main">权限清单</a></li>
									<li><a href="<oa:contextPath/>/rolemanage/apptype.action" target="navTab" rel="main">审批流程配置</a></li>
								</ul>
							</li>
							</oa:authority>


<!--  
							<li><a>测试</a>
								<ul>
									<li><a href="main.html" target="navTab" rel="main">我的主页</a></li>
									<li><a href="demo/pagination/layout1.html" target="navTab" rel="pagination1">局部刷新分页1</a></li>
									<li><a href="demo/pagination/layout2.html" target="navTab" rel="pagination2">局部刷新分页2</a></li>
									<li><a href="http://www.baidu.com" target="navTab" rel="page1">页面一(外部页面)</a></li>
									<li><a href="demo_page2.html" target="navTab" rel="external" external="true">iframe navTab页面</a></li>
									<li><a href="demo_page1.html" target="navTab" rel="page1" fresh="false">替换页面一</a></li>
									<li><a href="demo_page2.html" target="navTab" rel="page2">页面二</a></li>
									<li><a href="demo_page4.html" target="navTab" rel="page3" title="页面三（自定义标签名）">页面三</a></li>
									<li><a href="demo_page4.html" target="navTab" rel="page4" fresh="false">测试页面（fresh="false"）</a></li>
									<li><a href="w_editor.html" target="navTab">表单提交会话超时</a></li>
									<li><a href="demo/common/ajaxTimeout.html" target="navTab">navTab会话超时</a></li>
									<li><a href="demo/common/ajaxTimeout.html" target="dialog">dialog会话超时</a></li>
									<li><a href="index_menu.html" target="_blank">横向导航条</a></li>
									<li><a href="w_panel.html" target="navTab" rel="w_panel">面板</a></li>
									<li><a href="w_tabs.html" target="navTab" rel="w_tabs">选项卡面板</a></li>
									<li><a href="w_dialog.html" target="navTab" rel="w_dialog">弹出窗口</a></li>
									<li><a href="w_alert.html" target="navTab" rel="w_alert">提示窗口</a></li>
									<li><a href="w_list.html" target="navTab" rel="w_list">CSS表格容器</a></li>
									<li><a href="demo_page1.html" target="navTab" rel="w_table">表格容器</a></li>
									<li><a href="w_removeSelected.html" target="navTab" rel="w_table">表格数据库排序+批量删除</a></li>
									<li><a href="w_tree.html" target="navTab" rel="w_tree">树形菜单</a></li>
									<li><a href="w_accordion.html" target="navTab" rel="w_accordion">滑动菜单</a></li>
									<li><a href="w_editor.html" target="navTab" rel="w_editor">编辑器</a></li>
									<li><a href="w_datepicker.html" target="navTab" rel="w_datepicker">日期控件</a></li>
									<li><a href="demo/database/db_widget.html" target="navTab" rel="db">suggest+lookup+主从结构</a></li>
									<li><a href="demo/database/test.html" target="navTab" rel="db">suggest+lookup+主从结构</a></li>
									<li><a href="demo/database/treeBringBack.html" target="navTab" rel="db">tree查找带回</a></li>
									<li><a href="demo/sortDrag/1.html" target="navTab" rel="sortDrag">单个sortDrag示例</a></li>
									<li><a href="demo/sortDrag/2.html" target="navTab" rel="sortDrag">多个sortDrag示例</a></li>
									<li><a href="w_validation.html" target="navTab" rel="w_validation">表单验证</a></li>
									<li><a href="w_button.html" target="navTab" rel="w_button">按钮</a></li>
									<li><a href="w_textInput.html" target="navTab" rel="w_textInput">文本框/文本域</a></li>
									<li><a href="w_combox.html" target="navTab" rel="w_combox">下拉菜单</a></li>
									<li><a href="w_checkbox.html" target="navTab" rel="w_checkbox">多选框/单选框</a></li>
									<li><a href="demo_upload.html" target="navTab" rel="demo_upload">iframeCallback表单提交</a></li>
									<li><a href="w_uploadify.html" target="navTab" rel="w_uploadify">uploadify多文件上传</a></li>
									<li><a href="demo/pagination/layout1.html" target="navTab" rel="pagination1">局部刷新分页1</a></li>
									<li><a href="demo/pagination/layout2.html" target="navTab" rel="pagination2">局部刷新分页2</a></li>
								</ul>
							</li>
							<li><a href="dwz.frag.xml" target="navTab" external="true">dwz.frag.xml</a></li> 
							--> 
						</ul>
					</div>

				</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">待办事项</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">待办事项</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
<div class="accountInfo">
	<div class="alertInfo">
		<!--
		<h2>最新消息：</h2>
		<a href="">深交所：中小散户是股票上市首日买入主体 占比56%</a>
		-->
	</div>
	<div class="left">
	
							<p style="color:red"><a href="<oa:contextPath/>/approve/myapprove.action" target="navTab" rel="main" style="color:red">我的审批</a>：${approveNum}</p>
							<p style="color:red"><a href="<oa:contextPath/>/approve/myapplication.action" target="navTab" rel="main" style="color:red">我的申请</a>：${applicationNum}</p>

	</div>

</div>
					
					</div>
					
				</div>
			</div>
		</div>

	</div>

	<div id="footer">Copyright &copy; 2013 <a href="#" target="dialog">中天云易</a> Version: 1.0</div>

</body>
</html>