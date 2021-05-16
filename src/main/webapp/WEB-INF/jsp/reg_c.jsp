<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>
<head>
    <title>Reg C</title>
    <link href="/css/reg_c.css" rel="stylesheet" type="text/css">
</head>
<body>
<img src="${s:mvcUrl("DC#loadImage").arg(0, "background").build()}" class="background-image">
<div class="content hor">
    <div class="logo">
        <img src="${s:mvcUrl("DC#loadImage").arg(0, "logo").build()}"
             class="logo-image">
    </div>
    <f:form class="form ver" method="post" action='${s:mvcUrl("AC#regCPost").build()}'
            modelAttribute="user" enctype="multipart/form-data">
        <div class="form-head hor">
            <h2 class="form-heading">Registration</h2>
            <a href="/" class="form-logout">Log Out</a>
        </div>
        <div class="hor">
                <%--<f:label class="form-label" path="photo">&lt;%&ndash;<s:message code="photo"/>&ndash;%&gt;Photo</f:label>--%>
                <%--<f:input path="photo" type="file" accept="image/*" capture="true"/>--%>
                <%--image/png, image/jpeg--%>
                <%--<f:errors path="photo"/>--%>
            <label class="form-label">Photo</label>
            <input class="photo" id="photo" name="photo" type="file" accept="image/png, image/jpeg" capture>

            <div class="ver">
                <f:label class="form-label" path="name"><%--TODO<s:message code="name"/>--%>Name</f:label>
                <f:input path="name" type="text"/>
                <f:errors path="name"/>
                <br>
                <f:label class="form-label" path="surname"><%--<s:message code="surname"/>--%>Surname</f:label>
                <f:input path="surname" type="text"/>
                <f:errors path="surname"/>
                <br>
                <f:label class="form-label"
                         path="location"><%--<s:message code="location"/>--%>Location</f:label>
                <f:input path="location" type="text"/>
                <f:errors path="location"/>
            </div>
            <br>
        </div>
        <div>
            Много тексте, который почти никто не читает, а тех, кто читает, нужно отправлять в дурку как можно скорее
            Много тексте, который почти никто не читает, а тех, кто читает, нужно отправлять в дурку как можно скорее
            Много тексте, который почти никто не читает, а тех, кто читает, нужно отправлять в дурку как можно скорее
        </div>
        <div>
            <button class="btn" type="submit"><%--<s:message code="reg.submit-button"/>--%>Sign Up</button>
        </div>
    </f:form>
</div>
</body>
</html>
