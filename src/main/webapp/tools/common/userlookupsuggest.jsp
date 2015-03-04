<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
[
<s:iterator value="userList" id="user" status="st">

	
	{id:'<s:property value="id" />',realName:'<s:property value="realName" />', mobile:'<s:property value="mobile" />', department.depName:'<s:property value="department.depName" />', role.name:'<s:property value="role.name" />' }
	<s:if test="#st.Last">
    </s:if>
    <s:else>
    ,
    </s:else>
 </s:iterator>
]