<%--@elvariable id="uname" type="java.lang.String"--%>
<%--@elvariable id="customer" type="com.bbm488.site.customer.Customer"--%>
<%--@elvariable id="customerCrudForm" type="com.bbm488.CustomerRegistration.Form"--%>

<template:owner htmlTitle="Edit Customer :${uname}"
                bodyTitle="Edit Customer with Username=${uname}">
    <form:form method="post" modelAttribute="customerCrudForm">
        <form:label path="uname">Username</form:label>
        <form:input path="uname"  value="${customer.uname}"/><br />
        <form:label path="upass">Password</form:label>
        <form:password path="upass" value="${customer.upass}" /><br />
        <form:label path="name">Name</form:label>
        <form:input path="name"   value="${customer.name}"/><br />
        <form:label path="surname">Surname</form:label>
        <form:input path="surname"   value="${customer.surname}"/><br />
        <form:label path="floor">Floor No.</form:label>
        <form:input path="floor"   value="${customer.floor}"/><br />
        <form:label path="apt">Apartment</form:label>
        <form:input path="apt"   value="${customer.apt}"/><br />
        <form:label path="room">Room No.</form:label>
        <form:input path="room"  value="${customer.room}" /><br />
        <form:label path="checkbox">Delete?</form:label>
        <form:checkbox path="checkbox"  /><br />
        <form:hidden path="oldKey" style="display:none" value="${uname}"/><br />
        <input type="submit" value="Apply" />
    </form:form>
</template:owner>
