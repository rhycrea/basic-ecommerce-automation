<%--@elvariable id="productDB" type="java.util.Map<java.lang.Integer, com.bbm488.site.Product>"--%>
<%--@elvariable id="orderForm" type="com.bbm488.site.customer.OrderController.Form"--%>

<template:customer htmlTitle="Order" bodyTitle="Order">
    <c:choose>
        <c:when test="${fn:length(productDB) == 0}">
            <i>There are no product registered in the system.</i>
        </c:when>
        <c:otherwise>
            <table class="listing">
                <tr class="listing">
                    <td class="listing">Product</td>
                    <td class="listing">Price</td>
                    <td class="listing">Count</td>
                    <td class="listing"></td>
                </tr  >
                <c:forEach items="${productDB}" var="product">
                    <form:form method="post" modelAttribute="orderForm">
                        <tr class="listing">
                            <td class="listing"><b><c:out value="${product.value.name}" /></b></td>
                            <td class="listing"><c:out value="${product.value.price}" /></td>
                            <td class="listing">
                                <form:label path="pcs">Pcs</form:label>
                                <form:input path="pcs" value="1"/>
                            </td>
                            <td class="listing"><input type="submit" value="Order!" /></td>
                        </tr  >
                        <form:hidden path="productID" style="display:none" value="${product.value.ID}"/>
                    </form:form>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
</template:customer>