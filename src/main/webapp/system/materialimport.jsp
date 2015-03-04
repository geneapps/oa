
<h2 class="contentTitle">请选择需要导入的材料文件</h2>

<form action="/oa/material/importupload.action" method="post" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return iframeCallback(this, $.bringBack)">
<input name="fileLog.id" type="hidden" value="${filelog.id}" />
<input name="fileLog.businessType" type="hidden" value="${fileLog.businessType}" />
<input name="fileLog.businessNo" type="hidden" value="${fileLog.businessNo}" />
						
<div class="pageContent">
	<div class="pageFormContent" layoutH="97">
		<dl>
			<dt>附件：</dt><dd><input type="file" name="uploadFile" class="required" size="30" />(文件格式要求是.xls)</dd>
		</dl>
	</div>
	<div class="formBar">
		<ul>
			<li><div class="buttonActive"><div class="buttonContent"><button type="submit">导入</button></div></div></li>
			<li><div class="button"><div class="buttonContent"><button class="close" type="button">关闭</button></div></div></li>
		</ul>
	</div>
</div>
</form>