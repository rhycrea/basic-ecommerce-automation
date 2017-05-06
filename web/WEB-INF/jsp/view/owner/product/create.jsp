<%--@elvariable id="createFailed" type="java.lang.Boolean"--%>
<%--@elvariable id="productCrudForm" type="com.bbm488.site.owner.ProductController.Form"--%>
<template:owner htmlTitle="Product CRUD" bodyTitle="Product CRUD">
   Add any product:<br /><br />
    <c:if test="${createFailed}">
        <b>The username you entered already registered!</b><br /><br />
    </c:if>
    <form:form method="post" modelAttribute="productCrudForm">
        <form:label path="name">Name</form:label>
        <form:input path="name" /><br />
        <form:label path="price">Price</form:label>
        <form:input path="price" /><br />
        <input type="submit" value="Register" />
    </form:form>
</template:owner>
