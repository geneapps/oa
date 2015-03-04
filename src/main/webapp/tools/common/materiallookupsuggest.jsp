<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
[
<s:iterator value="materialList" id="material" status="st">

	
	{"id":"<s:property value="id" />","category":"<s:property value="category" />","brand":"<s:property value="brand" />", "materialType":"<s:property value="materialType" />", "materialName":"<s:property value="materialName" />", "price":"<s:property value="price" />", "unit":"<s:property value="unit" />", "supplier":{"supplyName":"<s:property value="supplier.supplyName" />"}}
	<s:if test="#st.Last">
    </s:if>
    <s:else>
    ,
    </s:else>
 </s:iterator>
]
