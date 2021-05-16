<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<link href="/css/d_menu.css" rel="stylesheet" type="text/css">
<div class="menu">
    <a class="logo" href="/">
        <img src="${s:mvcUrl("DC#loadImage").arg(0, "logo").build()}" class="logo-image">
    </a>
    <div class="menu-buttons">

        <a class="menu-btn" href="${s:mvcUrl("DC#ownProfile").build()}">
            <img src="${s:mvcUrl("DC#loadImage").arg(0, "profile_icon").build()}" class="menu-btn-image">
        </a>
        <a class="menu-btn" href="${s:mvcUrl("CEC#notReady").build()}">
            <img src="${s:mvcUrl("DC#loadImage").arg(0, "news_icon").build()}" class="menu-btn-image">
        </a>
        <a class="menu-btn" href="${s:mvcUrl("DC#messages").build()}">
            <img src="${s:mvcUrl("DC#loadImage").arg(0, "messages_icon").build()}" class="menu-btn-image">
        </a>
        <a class="menu-btn" href="${s:mvcUrl("CEC#notReady").build()}">
            <img src="${s:mvcUrl("DC#loadImage").arg(0, "friends_icon").build()}" class="menu-btn-image">
        </a>
        <a class="menu-btn" href="${s:mvcUrl("CEC#notReady").build()}">
            <img src="${s:mvcUrl("DC#loadImage").arg(0, "music_icon").build()}" class="menu-btn-image">
        </a>
        <a class="menu-btn" href="/logout">
            <img src="${s:mvcUrl("DC#loadImage").arg(0, "exit_icon").build()}" class="menu-btn-image">
        </a>
    </div>
</div>
