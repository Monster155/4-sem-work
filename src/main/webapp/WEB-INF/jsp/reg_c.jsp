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
            <a href="/" class="form-logout-div">
                <div class="form-logout">Log Out</div>
            </a>
        </div>
        <div class="hor form-content">
                <%--<f:label class="form-label" path="photo">&lt;%&ndash;<s:message code="photo"/>&ndash;%&gt;Photo</f:label>--%>
                <%--<f:input path="photo" type="file" accept="image/*" capture="true"/>--%>
                <%--image/png, image/jpeg--%>
                <%--<f:errors path="photo"/>--%>
            <div class="form-photo">
                <label class="form-label">Photo</label>
                <div class="photo-div" id="photo-div"></div>
                <input class="photo" id="photo" name="photo" type="file"
                       accept="image/png, image/jpeg" capture hidden>
            </div>

            <div class="ver text">
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
        <div class="info">
            Много текста, который никто не читает,
            Много текста, который никто не читает,
            Много текста, который никто не читает,
            Много текста, который никто не читает,
            Много текста, который никто не читает,
            Много текста, который никто не читает,
        </div>
        <div class="btn-div">
            <button class="btn" type="submit"><%--<s:message code="reg.submit-button"/>--%>Sign Up</button>
        </div>
    </f:form>
</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $("#photo-div").click(function () {
        $("#photo").click();
    });

    $("#photo").change(function (ev) {
        if (this.files && this.files[0]) {
            $("#photo-div").css({background: "no-repeat center/cover url(" + window.URL.createObjectURL(this.files[0]) + ")"});
        }
    });
</script>
</body>
</html>
