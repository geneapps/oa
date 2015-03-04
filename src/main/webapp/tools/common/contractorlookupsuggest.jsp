<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
[
<s:iterator value="contractorList" id="contractor" status="st">

	
	{"id":"<s:property value="id" />","contractorName":"<s:property value="contractorName" />","contact":"<s:property value="contact" />", "phone":"<s:property value="phone" />", "credit":"<s:property value="credit" />"}}
	<s:if test="#st.Last">
    </s:if>
    <s:else>
    ,
    </s:else>
 </s:iterator>
]
