<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="oa" uri="/oa-tags"%>


<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar" id="menu">
			<li><a class="add" href="/oa/organization/editdepartment.action?department.parent.id={sid}" target="dialog" rel="editdepartment"><span>创建部门</span> </a></li>
			<li><a class="edit" href="/oa/organization/editdepartment.action?department.id={sid}" target="dialog" rel=""><span>编辑部门</span> </a></li>
			<li><a class="delete" href="/oa/organization/deldepartment.action?department.id={sid}" target="ajaxTodo" title="确定要删除吗?"><span>删除部门</span> </a></li>
		</ul>
	</div>
	

	
	<div style=" float:left; display:block; margin:5px; overflow:auto; width:98%; height:200px; border:solid 1px #CCC; line-height:21px; background:#FFF;">
<oa:departtree rel="departmentRole" url="/oa/organization/departmentrolelist.action" list="departments"></oa:departtree>
</div>

	
				<div layoutH="250" id="departmentRole" style="float:left; display:block; margin:5px; overflow:auto; width:25%; border:solid 1px #CCC; line-height:21px; background:#fff">
				    
				</div>
				
				<div layoutH="250" id="departmentUser" class="unitBox" style="float:left; display:block; margin:5px; overflow:auto; width:72%; border:solid 1px #CCC; line-height:21px; background:#fff";margin-left:250px;">

				</div>




</div>





	

