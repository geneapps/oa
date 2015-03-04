<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

	<script type="text/javascript">
var pc = new Array();

//pc[0]= new Array("请选择省份名","请选择城市名");
pc[1] = new Array("餐费","|出差餐费补助|本地餐费补助|聚餐补助");
pc[2] = new Array("车费","|出差车费|本地车费");
pc[3] = new Array("住宿费","|出差住宿费|本地住宿费");
pc[4] = new Array("材料费","|材料费1|材料费2");
function init() {
 var p = document.getElementById("province");
 var e = " ";
 if ( p && e ) {
  for( var i = 1; i < pc.length; i++ ) {
   e = document.createElement( "option" );
   e.setAttribute( "value", i );
   e.innerHTML = pc[i][0];
   p.appendChild(e);
  }
  showcity(1);
 }
}

function selectcity() {
 var p = document.getElementById("province");
 var idx = p.options[p.selectedIndex].value;
 if ( idx ) {
  showcity(idx);
 }
}

function showcity(idx) {
 var c = document.getElementById("city");
 if ( c ) {
  while( c.hasChildNodes() ) {
   c.removeChild(c.lastChild);
  }
 }
 if ( idx ) {
  var citys = pc[idx][1].split( "|" );
 
  for( var i = 1; i < citys.length; i++ ) {
   e = document.createElement( "option" );
   e.setAttribute( "value", citys[i] );
   e.innerHTML = citys[i];
   c.appendChild(e);
  }
 }
}
</script>
<div class="pageContent">
<form action="/oa/expense/editcostdetails.action" method="post"
            class="pageForm required-validate"
			onsubmit="return validateCallback(this, dialogAjaxDone);">
			
	  <div class="pageFormContent" layoutH="50"> 
	   
	   <dl class="nowrap">
	        <dt>
	            <label>费用分类</label>
	        </dt>
	             <input name="costDetails.expense.id" type="hidden" value="${costDetails.expense.id}" /> 
	           <input name="costDetails.id" type="hidden" value="${costDetails.id}" /> 
	           
	           
	            <select id="province" name="costDetails.expenseType" onchange="selectcity()" style="font-size:12px;width:100px;" /></select>
	            <select id="city" name="costDetails.expenseType" style="font-size:12px;width:100px;" /></select>
			
	    </dl>		
	
	     <dl class="nowrap">
	       <dt>
	        <label>费用时间</label>
	      </dt>
	       <input name="costDetails.expenseTime" type="text" class="date textInput readonly valid" value="${costDetails.expenseTime}"/>
	      <a class="inputDateButton" href="javascript:;"></a>
	     </dl>
	     
	     <dl class="nowrap">
	       <dt>
	        <label>费用金额</label>
	      </dt>
	     <input name="costDetails.payMoney" type="text"  value="${costDetails.payMoney}"/>元
	     </dl>
	   
	      <dl class="nowrap">
	       <dt>
	         <label>付款时间</label>
	       </dt>
	      <input name="costDetails.payTime" type="text" class="date textInput readonly valid" value="${costDetails.payTime}"/>
	      <a class="inputDateButton" href="javascript:;"></a>
	      </dl> 
	      
         <dl class="nowrap">
	       <dt>
	         <label>费用说明</label>
	       </dt>
	      <s:textarea name="costDetails.description" ros="25" cols="50"/>
	      </dl>
</div>

			<div class="formBar">
				<ul>

					<input type="hidden" name="formAction" value="save" />
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">修改</button>
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

<script type="text/javascript">
 init();
</script>