<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>OA</title>
<link href="themes/css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
         function reloadImage(){
            document.getElementById("Identity").src='checkcode.jpg?ts='+new Date().getTime();
         }
  </script>
</head>

<body>
	<div id="login">
		<div id="login_header">
			<h1 class="login_logo">
				<!-- <a href="http://demo.dwzjs.com"><img src="themes/default/images/login_logo.gif" /></a> -->
			</h1>
			<div class="login_headerContent">
				<div class="navList">
					<ul><!-- 
						<li><a href="#">设为首页</a></li>
						<li><a href="http://bbs.dwzjs.com">反馈</a></li>
						<li><a href="doc/dwz-user-guide.pdf" target="_blank">帮助</a></li> -->
					</ul>
				</div>
				<h2 class="login_title"><!--  <img src="themes/default/images/login_title.png" />--></h2>
			</div>
		</div>
		<div id="login_content">
			<div class="loginForm">
				<form method="post" action="/oa/login.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
					<span font="red">${ajaxResult.message}</span>
					<p>
						<label>用户名：</label>
						<input type="text" name="user.userNo" size="20" class="login_input required" value="D1022"/>
					</p>
					<p>
						<label>密码：</label>
						<input type="password" name="user.password" size="20" class="login_input required" value="123456"/>
					</p>
					<p>
						<label>验证码：</label>
						<input type="checkCode" name="checkCode" size="4" class="login_input required" />
						<a href="#" onclick="reloadImage()"><img src="checkcode.jpg" id="Identity" border="0"/></a>
						
					</p>
					<div class="login_bar">
						<input class="sub" type="submit" value=" " />
					</div>
				</form>
			</div>
			<div class="login_banner"><img src="themes/default/images/login_banner.jpg" /></div>
			<div class="login_main">
				<div class="login_inner">
	
				</div>
			</div>
		</div>
		<div id="login_footer">
			Copyright &copy; 2012 <a href="#" target="dialog">中天云易</a> Version: 1.0
		</div>
	</div>
</body>
</html>