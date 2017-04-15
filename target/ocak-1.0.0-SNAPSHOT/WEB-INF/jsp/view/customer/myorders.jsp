<%--@elvariable id="results" type="java.util.Map<String, com.bbm488.site.Order>"--%>
<template:customer htmlTitle="My Orders" bodyTitle="My Orders">
    <c:choose>
        <c:when test="${fn:length(results) == 0}">
            <i>There isnt any past order...</i>
        </c:when>
        <c:otherwise>
            <table class="listing">
                <tr class="listing">
                   <td class="listing">Product Name</td>
                    <td class="listing">Pcs</td>
                    <td class="listing">Total Price</td>
                    <td class="listing">Order Date</td>
                    <td class="listing">Is Sent?</td>
                    <td class="listing">Sent Date</td>
                </tr  >
                <c:forEach items="${results}" var="result">
                    <tr class="listing">
                        <td class="listing"><b><c:out value="${result.value.productName}" /></b></td>
                        <td class="listing"><c:out value="${result.value.pcs}" /></td>
                        <td class="listing"><c:out value="${result.value.totalPrice}" /></td>
                        <td class="listing"><c:out value="${result.value.orderDate}" /></td>
                        <td class="listing"><c:out value="${result.value.isSent}" /></td>
                        <td class="listing"><c:out value="${result.value.sentDate}" /></td>
                    </tr  >
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
</template:customer>