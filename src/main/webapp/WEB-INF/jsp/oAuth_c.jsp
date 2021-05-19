<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>
<head>
    <title>OAuth Continue</title>
</head>
<body>
<f:form class="form-signin" method="post" action='${s:mvcUrl("AC#oAuthCon").build()}' modelAttribute="user">
    <f:label path="password"><s:message code="password"/></f:label>
    <f:input path="password" type="password"/>
    <f:errors path="password"/>
    <br>
    <f:label path="nickname"><s:message code="nickname"/></f:label>
    <f:input path="nickname" type="text"/>
    <f:errors path="nickname"/>
    <br>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign Up</button>
</f:form>
</body>
</html>
