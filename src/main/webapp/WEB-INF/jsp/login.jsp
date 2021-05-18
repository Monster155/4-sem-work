<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>
<head>
    <title>Login</title>
</head>
<csrf disabled="true"/>
<body>
<c:if test="${not empty message}">
    ${message}
</c:if>
<br>
<%--<f:form class="form-signin" method="post" action='${s:mvcUrl("AC#loginPost").build()}' modelAttribute="userIn">--%>
<f:form class="form-signin" method="post" action='/sign/in' modelAttribute="userIn">
    <h2 class="form-signin-heading">Please sign in</h2>
    <f:label path="email"><s:message code="email"/></f:label>
    <f:input path="email" type="email"/>
    <f:errors path="email"/>
    <br>
    <f:label path="password"><s:message code="password"/></f:label>
    <f:input path="password" type="password"/>
    <f:errors path="password"/>
    <br>
    <button class="btn btn-lg btn-primary btn-block" type="submit"><s:message code="login.submit-button"/></button>
</f:form>

<f:form class="form-signin" method="post" action='${s:mvcUrl("AC#regPost").build()}' modelAttribute="userUp">
    <h2 class="form-signin-heading">Please sign in</h2>
    <f:label path="email"><s:message code="email"/></f:label>
    <f:input path="email" type="email"/>
    <f:errors path="email"/>
    <br>
    <f:label path="nickname"><s:message code="nickname"/></f:label>
    <f:input path="nickname" type="text" required="required"/>
    <f:errors path="nickname"/>
    <br>
    <f:label path="password"><s:message code="password"/></f:label>
    <f:input path="password" type="password"/>
    <f:errors path="password"/>
    <br>
    <button class="btn btn-lg btn-primary btn-block" type="submit"><s:message code="login.submit-button"/></button>
</f:form>
<br>
<a href="${oauth}">OAuth</a>
</body>
</html>
