<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>
<head>
    <title><s:message code="messages.title"/></title>
    <link href="/css/messages.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="content">
    <jsp:include page="d_menu.jsp"/>

    <div class="chats">
        <div class="chats-list">
            <div class="chat-new">
                <div class="chat-new-logo"></div>
                <div class="chat-new-text">Create new</div>
            </div>
            <div class="chat-search">
                <div class="chat-search-logo"></div>
                <div class="chat-search-text">Search</div>
                <div class="chat-search-voice"></div>
            </div>
            <%--            <div class="chat">--%>
            <%--                <div class="chat-profile-photo"></div>--%>
            <%--                <div class="chat-profile-info">--%>
            <%--                    <div class="chat-profile-name"></div>--%>
            <%--                    <div class="chat-last-message"></div>--%>
            <%--                </div>--%>
            <%--            </div>--%>
        </div>
        <div class="messages">
            <div class="messages-all">
                <%--                <div class="message-own">--%>
                <%--                    <div class="message-own-photo"></div>--%>
                <%--                    <div class="message-own-content">--%>
                <%--                        <div class="message-own-text"></div>--%>
                <%--                        <div class="message-own-time"></div>--%>
                <%--                    </div>--%>
                <%--                </div>--%>

                <%--                <div class="message-foreign">--%>
                <%--                    <div class="message-foreign-photo"></div>--%>
                <%--                    <div class="message-foreign-content">--%>
                <%--                        <div class="message-foreign-text"></div>--%>
                <%--                        <div class="message-foreign-time"></div>--%>
                <%--                    </div>--%>
                <%--                </div>--%>
            </div>
            <div class="messages-input">
                <div class="messages-input-add-any"></div>
                <div class="messages-input-add-smile"></div>
                <div class="messages-input-text"></div>
                <div class="messages-input-send-btn"></div>
            </div>
        </div>
    </div>
    <div class="profile">
        <div class="profile-photo"></div>
        <div class="profile-name"></div>
        <div class="profile-email"></div>
        <div class="profile-call-btns">
            <div class="profile-voice-call-btn"></div>
            <div class="profile-video-call-btn"></div>
        </div>
        <div class="profile-content">
            <div class="profile-content-photos">
                <div class="profile-content-photos-logo"></div>
                <div class="profile-content-photos-text"></div>
                <div class="profile-content-photos-dropdown-logo"></div>
            </div>
            <div class="profile-content-files">
                <div class="profile-content-photos-logo"></div>
                <div class="profile-content-photos-text"></div>
                <div class="profile-content-photos-dropdown-logo"></div>
            </div>
            <div class="profile-content-music">
                <div class="profile-content-photos-logo"></div>
                <div class="profile-content-photos-text"></div>
                <div class="profile-content-photos-dropdown-logo"></div>
            </div>
            <div class="profile-content-links">
                <div class="profile-content-photos-logo"></div>
                <div class="profile-content-photos-text"></div>
                <div class="profile-content-photos-dropdown-logo"></div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
