<%--@elvariable id="createFailed" type="java.lang.Boolean"--%>
<%--@elvariable id="customerCrudForm" type="com.bbm488.CustomerRegistration.Form"--%>
<template:owner htmlTitle="Customer CRUD" bodyTitle="Customer CRUD">
    Use patron/patron for owner of the shop. This record can be changed in Owner class.<br /><br />
    <c:if test="${createFailed}">
        <b>The username you entered already registered!</b><br /><br />
    </c:if>
    <form:form method="post" modelAttribute="customerCrudForm">
        <form:label path="uname">Username</form:label>
        <form:input path="uname" /><br />
        <form:label path="upass">Password</form:label>
        <form:password path="upass" /><br />
        <form:label path="name">Name</form:label>
        <form:input path="name" /><br />
        <form:label path="surname">Surname</form:label>
        <form:input path="surname" /><br />
        <form:label path="floor">Floor No.</form:label>
        <form:input path="floor" /><br />
        <form:label path="apt">Apartment</form:label>
        <form:input path="apt" /><br />
        <form:label path="room">Room No.</form:label>
        <form:input path="room" /><br />
        <input type="submit" value="Register" />
    </form:form>
</template:owner>
