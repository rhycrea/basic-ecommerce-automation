<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>
<%@ attribute name="htmlTitle" type="java.lang.String" rtexprvalue="true"
              required="true" %>
<%@ attribute name="bodyTitle" type="java.lang.String" rtexprvalue="true"
              required="true" %>
<%@ attribute name="headContent" fragment="true" required="false" %>
<%@ attribute name="navigationContent" fragment="true" required="true" %>
<%@ include file="/WEB-INF/jsp/header.jspf" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
        <title>E-commerce - <c:out value="${fn:trim(htmlTitle)}" /></title>
        <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.3.1/css/bootstrap.min.css" />
        <link rel="stylesheet"
              href="<c:url value="/resources/css/main.css" />" />
        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script src="http://cdnjs.cloudflare.com/ajax/libs/moment.js/2.0.0/moment.min.js"></script>
        <jsp:invoke fragment="headContent" />
    </head>
    <body>
        <h1>E-commerce</h1>
        <table border="0" id="bodyTable">
            <tbody>
                <tr>
                    <td class="sidebarCell">
                        <jsp:invoke fragment="navigationContent" />
                    </td>
                    <td class="contentCell">
                        <h2><c:out value="${fn:trim(bodyTitle)}" /></h2>
                        <jsp:doBody />
                    </td>
                </tr>
            </tbody>
        </table>
    </body>
</html>
