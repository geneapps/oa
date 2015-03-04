<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
[
<s:iterator value="storeRoomMaterialList" id="material" status="st">

	
	{"id":"<s:property value="id" />", "materialType":"<s:property value="materialType" />", "materialName":"<s:property value="materialName" />", "unit":"<s:property value="unit" />", "number":"<s:property value="number" />"}
	<s:if test="#st.Last">
    </s:if>
    <s:else>
    ,
    </s:else>
 </s:iterator>
]