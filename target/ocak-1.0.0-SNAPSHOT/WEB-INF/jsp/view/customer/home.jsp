<%--@elvariable id="customer" type="com.bbm488.site.customer.Customer"--%>
<template:customer htmlTitle="Home" bodyTitle="User Info">
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
        <tr class="listing">
            <td class="listing"><b><c:out value="${customer.uname}" /></b></td>
            <td class="listing"><c:out value="${customer.name}" /></td>
            <td class="listing"><c:out value="${customer.surname}" /></td>
            <td class="listing"><c:out value="${customer.floor}" /></td>
            <td class="listing"><c:out value="${customer.apt}" /></td>
            <td class="listing"><c:out value="${customer.room}" /></td>
            <td class="listing"><a href="<c:url value="/customer/edit/${customer.uname}"/>">Edit</a></td>
        </tr >
    </table>
</template:customer>