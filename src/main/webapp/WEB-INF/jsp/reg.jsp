<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>
<head>
    <title>Reg</title>
</head>
<body>
<f:form class="form-signin" method="post" action='${s:mvcUrl("AC#regPost").build()}' modelAttribute="user">
    <h2 class="form-signin-heading">Please sign in</h2>
    <f:label path="email"><s:message code="login.email"/></f:label>
    <f:input path="email"/>
    <f:errors path="email"/>
    <br>
    <f:label path="nickname"><s:message code="login.nickname"/></f:label>
    <f:input path="nickname"/>
    <f:errors path="nickname"/>
    <br>
    <f:label path="password"><s:message code="login.password"/></f:label>
    <f:input path="password"/>
    <f:errors path="password"/>
    <br>
    <button class="btn btn-lg btn-primary btn-block" type="submit"><s:message code="login.submit-button"/></button>
</f:form>
</body>
</html>
