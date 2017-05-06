<%--@elvariable id="customerDB" type="java.util.List<com.bbm488.site.Customer>"--%>
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
                    <td class="listing"><b>${customer.uname}</b></td>
                    <td class="listing"><c:out value="${customer.name}" /></td>
                    <td class="listing"><c:out value="${customer.surname}" /></td>
                    <td class="listing"><c:out value="${customer.floor}" /></td>
                    <td class="listing"><c:out value="${customer.apt}" /></td>
                    <td class="listing"><c:out value="${customer.room}" /></td>
                    <td class="listing"><a href="<c:url value="/owner/customer_crud/edit/${customer.uname}"/>">Edit</a></td>
                </tr  >
            </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
</template:owner>