<%--@elvariable id="loginFailed" type="java.lang.Boolean"--%>
<%--@elvariable id="loginForm" type="com.bbm488.site.MainController.Form"--%>
<template:login htmlTitle="Log In" bodyTitle="Log In">
    Use patron/patron for owner of the shop. This record can be changed in Owner class.<br /><br />
    <c:if test="${loginFailed}">
        <b>The username and password you entered are not correct. Please try
            again.</b><br /><br />
    </c:if>
    <form:form method="post" modelAttribute="loginForm">
        <form:label path="username">Username</form:label><br />
        <form:input path="username" /><br /><br />
        <form:label path="password">Password</form:label><br />
        <form:password path="password" /><br /><br />
        <input type="submit" value="Log In" />
    </form:form>
</template:login>
