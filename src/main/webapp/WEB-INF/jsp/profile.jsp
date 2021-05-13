<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>
<head>
    <title><%--${profile.name}--%></title>
</head>
<body>
    <div class="content">
        <div class="menu">
            <div class="logo">Logo</div>
            <div class="menu-buttons">
                <div class="menu-btn">1</div>
                <div class="menu-btn">2</div>
                <div class="menu-btn">3</div>
                <div class="menu-btn">4</div>
                <div class="menu-btn">5</div>
            </div>
        </div>
        <div class="main">
            <div class="profile">
                <div class="profile-info">
                    <div class="profile-info-own">
                        <div class="profile-info-own-photo"></div>
                        <div class="profile-info-own-text">
                            <div class="profile-name"></div>
                            <div class="profile-location"></div>
                        </div>
                    </div>
                    <div class="profile-info-contacts">
                        <div class="profile-followers">
                            <div class="profile-followers-count"></div>
                            <div class="profile-followers-text"></div>
                        </div>
                        <div class="profile-friends">
                            <div class="profile-friends-count"></div>
                            <div class="profile-friends-text"></div>
                        </div>
                        <div class="profile-follow-btn"></div>
                    </div>
                </div>
                <div class="profile-media">
                    <div class="profile-media-menu">
                        <div class="profile-media-photos"></div>
                        <div class="profile-media-vclasseos"></div>
                        <div class="profile-media-music"></div>
                    </div>
                </div>
            </div>
            <div class="posts-container">
                <div class="post-new">
                    <div class="post-new-profile-photo"></div>
                    <div class="post-new-text"></div>
                    <div class="post-new-add-photo"></div>
                    <div class="post-new-add-vclasseo"></div>
                    <div class="post-new-add-music"></div>
                    <div class="post-new-add-wyswig"></div>
                </div>
                <div class="post-search">
                    <div class="post-search-logo"></div>
                    <div class="post-search-text"></div>
                    <div class="post-search-voice-logo"></div>
                </div>
                <div class="post">
                    <div class="post-info">
                        <div class="post-info-photo"></div>
                        <div class="post-info-profile">
                            <div class="post-info-profile-name"></div>
                            <div class="post-info-date"></div>
                        </div>
                    </div>
                    <div class="post-content"></div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
