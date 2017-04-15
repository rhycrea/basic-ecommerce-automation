<%--@elvariable id="customerDB" type="java.util.Map<String, com.bbm488.site.customer.Customer>"--%>
<template:owner htmlTitle="Customers" bodyTitle="Customers">
    <c:choose>
        <c:when test="${fn:length(customerDB) == 0}">
            <i>There are no costumers registered in the system.</i>
        </c:when>
        <c:otherwise>
            <table class="listing">
                <tr class="listing">
                    <td class="listing"><b>Username</b></td>
                    <td class="listing">Name</td>
                    <td class="listing">Surname</td>
                    <td class="listing">Floor</td>
                    <td class="listing">Apartment</td>
                    <td class="listing">Room No.</td>
                    <td class="listing"></td>
                </tr  >
            <c:forEach items="${customerDB}" var="customer">
                <tr class="listing">
                    <td class="listing"><b>${customer.key}</b></td>
                    <td class="listing"><c:out value="${customer.value.name}" /></td>
                    <td class="listing"><c:out value="${customer.value.surname}" /></td>
                    <td class="listing"><c:out value="${customer.value.floor}" /></td>
                    <td class="listing"><c:out value="${customer.value.apt}" /></td>
                    <td class="listing"><c:out value="${customer.value.room}" /></td>
                    <td class="listing"><a href="<c:url value="/owner/customer_crud/edit/${customer.key}"/>">Edit</a></td>
                </tr  >
            </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
</template:owner>