<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<link href="/css/d_post.css" rel="stylesheet" type="text/css">
<c:forEach items="${posts}" var="post">
    <div class="post">
        <div class="post-info">
            <div class="post-info-photo">
                <c:set var="photo" value="${post.owner.photo}"/>
                    <%--                <img src="${s:mvcUrl("DC#loadImage").arg(0, photo).build()}" class="post-info-photo-element">--%>
            </div>
            <div class="post-info-profile">
                <div class="post-info-profile-name">${post.owner.name}</div>
                <div class="post-info-date">${post.timestamp}</div>
            </div>
        </div>
        <div class="post-content">
            <div class="post-content-images">
                    <%--loop for images--%>
                <c:forEach items="${post.images}" var="image">
                    <%--                <img src="${s:mvcUrl("DC#loadImage").arg(1, image).build()}" class="post-content-images-element">--%>
                    <img src="${image}" class="post-content-images-element">
                </c:forEach>
            </div>
            <div class="post-content-text">${post.text}</div>
            <div class="post-content-music">
                    <%--loop for music--%>
                    <%--<c:forEach items="${post.musics}" var="music">--%>
                    <%--TODO load music--%>
                    <%--</c:forEach>--%>
            </div>
        </div>
    </div>
</c:forEach>
