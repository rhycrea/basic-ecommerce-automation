<%--@elvariable id="orderDB" type="java.util.Map<Integer, com.bbm488.site.Order>"--%>
<%--@elvariable id="deliveryForm" type="com.bbm488.site.owner.DeliveryController.Form"--%>

<template:owner htmlTitle="Delivery" bodyTitle="Delivery">
    <c:choose>
        <c:when test="${fn:length(orderDB) == 0}">
            <i>There is no order in the system.</i>
        </c:when>
        <c:otherwise>
            <table class="listing">
                <tr class="listing">
                    <td class="listing">Product Name</td>
                    <td class="listing">Customer</td>
                    <td class="listing">Pcs</td>
                    <td class="listing">Total Price</td>
                    <td class="listing">Order Date</td>
                    <td class="listing">Is Sent?</td>
                    <td class="listing">Sent Date</td>
                    <td class="listing">Delivery</td>
                </tr  >
                <c:forEach items="${orderDB}" var="order">
                    <form:form method="post" modelAttribute="deliveryForm">
                        <tr class="listing">
                            <td class="listing"><b><c:out value="${order.value.productName}" /></b></td>
                            <td class="listing"><b><c:out value="${order.value.buyer}" /></b></td>
                            <td class="listing"><c:out value="${order.value.pcs}" /></td>
                            <td class="listing"><c:out value="${order.value.totalPrice}" /></td>
                            <td class="listing"><c:out value="${order.value.orderDate}" /></td>
                            <td class="listing"><c:out value="${order.value.isSent}" /></td>
                            <td class="listing"><c:out value="${order.value.sentDate}" /></td>
                            <td class="listing">
                                <c:if test="${!order.value.isSent}">
                                    <input type="submit" value="Deliver!" />
                                </c:if>
                            </td>
                            <form:hidden path="orderID" style="display:none" value="${order.key}"/>
                        </tr  >
                    </form:form>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
</template:owner>