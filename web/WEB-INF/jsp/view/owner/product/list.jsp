<%--@elvariable id="productDB" type="java.util.Map<Integer,com.bbm488.site.Product>"--%>
<template:owner htmlTitle="Products" bodyTitle="Products">
    <c:choose>
        <c:when test="${fn:length(productDB) == 0}">
            <i>There are no products registered in the system.</i>
        </c:when>
        <c:otherwise>
            <table class="listing">
                <tr class="listing">
                    <td class="listing"><b>ID</b></td>
                    <td class="listing">Name</td>
                    <td class="listing">Price</td>
                    <td class="listing"></td>
                </tr >
            <c:forEach items="${productDB}" var="product">
                <tr class="listing">
                    <td class="listing"><b>${product.key}</b></td>
                    <td class="listing"><c:out value="${product.value.name}" /></td>
                    <td class="listing"><c:out value="${product.value.price}" /></td>
                    <td class="listing"><a href="<c:url value="/owner/product/edit/${product.key}"/>">Edit</a></td>
                </tr  >
            </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
</template:owner>