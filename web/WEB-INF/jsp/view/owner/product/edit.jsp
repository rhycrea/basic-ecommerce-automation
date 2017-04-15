<%--@elvariable id="id" type="java.lang.Integer"--%>
<%--@elvariable id="product" type="com.bbm488.site.Product"--%>
<%--@elvariable id="productCrudForm" type="com.bbm488.ProductController.Form"--%>

<template:owner htmlTitle="Edit Product :${uname}"
                bodyTitle="Edit Product with Username=${uname}">
    <form:form method="post" modelAttribute="productCrudForm">
        <form:label path="name">Username</form:label>
        <form:input path="name"  value="${product.name}"/><br />
        <form:label path="price">Name</form:label>
        <form:input path="price"   value="${product.price}"/><br />
        <form:label path="checkbox">Delete?</form:label>
        <form:checkbox path="checkbox" /><br />
        <form:hidden path="oldKey" style="display:none" value="${id}"/><br />
        <input type="submit" value="Apply" />
    </form:form>
</template:owner>
