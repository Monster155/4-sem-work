<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>
<head>
    <title>Login</title>
    <link href="/css/login.css" rel="stylesheet" type="text/css">
</head>
<csrf disabled="true"/>
<body>
<img src="${s:mvcUrl("DC#loadImage").arg(0, "background").build()}" class="background-image">
<div class="content">
    <c:if test="${not empty message}">
        <div class="message">
            <div class="message-text">
                    ${message}
            </div>
        </div>
    </c:if>
    <div class="forms">
        <%--<f:form class="form-signin" method="post" action='${s:mvcUrl("AC#loginPost").build()}' modelAttribute="userIn">--%>
        <f:form class="form-signin" method="post" action='/sign/in' modelAttribute="userIn">
            <h2 class="form-signin-heading">Sign in</h2>
            <f:label path="email"><s:message code="email"/></f:label>
            <f:input path="email" type="email"/>
            <f:errors path="email"/>
            <br>
            <f:label path="password"><s:message code="password"/></f:label>
            <f:input path="password" type="password"/>
            <f:errors path="password"/>
            <br>
            <button class="btn-sign" type="submit"><s:message code="login.submit-button"/></button>
            <br>
            <a href="${oauth}" class="oauth">
                <img src="${s:mvcUrl("DC#loadImage").arg(0, "vk_oauth").build()}" class="oauth-image">
            </a>
        </f:form>

        <f:form class="form-signin" method="post" action='${s:mvcUrl("AC#regPost").build()}' modelAttribute="userUp">
            <h2 class="form-signin-heading">Sign up</h2>
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
            <button class="btn-sign" type="submit">Sign Up</button>
        </f:form>
    </div>
</div>
</body>
</html>
