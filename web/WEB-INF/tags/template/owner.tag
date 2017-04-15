<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>
<%@ attribute name="htmlTitle" type="java.lang.String" rtexprvalue="true"
              required="true" %>
<%@ attribute name="bodyTitle" type="java.lang.String" rtexprvalue="true"
              required="true" %>
<%@ attribute name="extraHeadContent" fragment="true" required="false" %>
<%@ attribute name="extraNavigationContent" fragment="true" required="false" %>
<%@ include file="/WEB-INF/jsp/header.jspf" %>
<template:main htmlTitle="${htmlTitle}" bodyTitle="${bodyTitle}">
    <jsp:attribute name="headContent">
        <jsp:invoke fragment="extraHeadContent" />
    </jsp:attribute>
    <jsp:attribute name="navigationContent">
        <a href="<c:url value="/owner/customer_crud/list" />">List Customers</a><br />
        <a href="<c:url value="/owner/customer_crud/create" />">Register a Customer</a><br />
        <a href="<c:url value="/owner/product/list" />">List Products</a><br />
        <a href="<c:url value="/owner/product/create" />">Register a Product</a><br />
        <a href="<c:url value="/owner/delivery" />">Delivery</a><br />
        <a href="<c:url value="/logout" />">Log Out</a><br />
        <jsp:invoke fragment="extraNavigationContent" />
    </jsp:attribute>
    <jsp:body>
        <jsp:doBody />
    </jsp:body>
</template:main>
