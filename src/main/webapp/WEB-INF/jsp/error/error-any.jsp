<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>
<head>
    <title>Any</title>
    <link href="/css/error/not-ready.css" rel="stylesheet" type="text/css">
</head>
<body>
<img src="${s:mvcUrl("DC#loadImage").arg(0, "background").build()}" class="background-image">
<div class="content">
    <div class="message">
        <div class="text">
            Небольшие технические шоколадки
        </div>
    </div>
    <a href="/" class="homepage">
        <div class="homepage-text">
            To Home Page
        </div>
    </a>
</div>
</body>
</html>