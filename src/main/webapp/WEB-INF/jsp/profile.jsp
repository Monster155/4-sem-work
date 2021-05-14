<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>
<head>
    <title><%--${profile.name}--%></title>
    <link href="/css/profile.css" rel="stylesheet" type="text/css">
</head>
<body>
<%--<img src="<c:url value='/images/background.png'/>" class="background-image" id="1">--%>
<img src="${s:mvcUrl("DC#loadImage").arg(0, "background.png").build()}" class="background-image">
<div class="content" id="content">
    <div class="menu-main">
        <jsp:include page="d_menu.jsp"/>
    </div>
    <div class="main" id="main">
        <div class="posts-container">
            <div class="post-new">
                <div class="post-new-profile-photo"></div>
                <div class="post-new-text">What's new...</div>
                <div class="post-new-add-photo post-new-add-logo"></div>
                <div class="post-new-add-video post-new-add-logo"></div>
                <div class="post-new-add-music post-new-add-logo"></div>
                <div class="post-new-add-wyswig post-new-add-logo"></div>
            </div>
            <div class="post-search">
                <div class="post-search-logo"></div>
                <div class="post-search-text">Search...</div>
                <div class="post-search-voice-logo"></div>
            </div>
            <%--posts--%>
            <div class="posts-list" id="posts-list"></div>
        </div>
        <div class="profile">
            <div class="profile-info">
                <div class="profile-info-own">
                    <div class="profile-info-own-photo"></div>
                    <div class="profile-info-own-text">
                        <div class="profile-name">Damir Davletshin</div>
                        <div class="profile-location">Kazan, Russia</div>
                    </div>
                </div>
                <div class="profile-info-contacts">
                    <div class="profile-followers">
                        <div class="profile-followers-count">17K</div>
                        <div class="profile-followers-text">Followers</div>
                    </div>
                    <div class="profile-friends">
                        <div class="profile-friends-count">337</div>
                        <div class="profile-friends-text">Friends</div>
                    </div>
                    <div class="profile-follow-btn">
                        <div class="profile-follow-btn-text">
                            Follow
                        </div>
                    </div>
                </div>
            </div>
            <div class="profile-media">
                <div class="profile-media-menu">
                    <div class="profile-media-menu-btn" onclick="swipeMediaContent(this, 0)">Photo</div>
                    <div class="profile-media-menu-btn" onclick="swipeMediaContent(this, 1)">Video</div>
                    <div class="profile-media-menu-btn" onclick="swipeMediaContent(this, 2)">Music</div>
                    <div class="profile-media-menu-selector" id="profile-media-menu-selector">*</div>
                </div>
                <div class="profile-media-container" id="profile-media-container">
                    <div class="profile-media-content" id="profile-media-content">
                        <div class="profile-media-photo profile-media-content-element" id="profile-media-photo">
                            <table>
                                <tr>
                                    <td>
                                        <div class="profile-media-photo-small">
                                            <img src="${s:mvcUrl("DC#loadImage").arg(0, "background.png").build()}"
                                                 class="profile-media-photo-small-image">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="profile-media-photo-small">
                                            <img src="${s:mvcUrl("DC#loadImage").arg(0, "background.png").build()}"
                                                 class="profile-media-photo-small-image">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="profile-media-photo-small">
                                            <img src="${s:mvcUrl("DC#loadImage").arg(0, "background.png").build()}"
                                                 class="profile-media-photo-small-image">
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <div class="profile-media-photo-small">
                                            <img src="${s:mvcUrl("DC#loadImage").arg(0, "background.png").build()}"
                                                 class="profile-media-photo-small-image">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="profile-media-photo-small">
                                            <img src="${s:mvcUrl("DC#loadImage").arg(0, "background.png").build()}"
                                                 class="profile-media-photo-small-image">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="profile-media-photo-small">
                                            <img src="${s:mvcUrl("DC#loadImage").arg(0, "background.png").build()}"
                                                 class="profile-media-photo-small-image">
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <div class="profile-media-photo-small">
                                            <img src="${s:mvcUrl("DC#loadImage").arg(0, "background.png").build()}"
                                                 class="profile-media-photo-small-image">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="profile-media-photo-small">
                                            <img src="${s:mvcUrl("DC#loadImage").arg(0, "background.png").build()}"
                                                 class="profile-media-photo-small-image">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="profile-media-photo-small">
                                            <img src="${s:mvcUrl("DC#loadImage").arg(0, "background.png").build()}"
                                                 class="profile-media-photo-small-image">
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div class="profile-media-video profile-media-content-element" id="profile-media-video">
                            <table>
                                <tr>
                                    <td>
                                        <div class="profile-media-video-small">
                                            <img src="${s:mvcUrl("DC#loadImage").arg(0, "background.png").build()}"
                                                 class="profile-media-video-small-image">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="profile-media-video-small">
                                            <img src="${s:mvcUrl("DC#loadImage").arg(0, "background.png").build()}"
                                                 class="profile-media-video-small-image">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="profile-media-video-small">
                                            <img src="${s:mvcUrl("DC#loadImage").arg(0, "background.png").build()}"
                                                 class="profile-media-video-small-image">
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <div class="profile-media-video-small">
                                            <img src="${s:mvcUrl("DC#loadImage").arg(0, "background.png").build()}"
                                                 class="profile-media-video-small-image">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="profile-media-video-small">
                                            <img src="${s:mvcUrl("DC#loadImage").arg(0, "background.png").build()}"
                                                 class="profile-media-video-small-image">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="profile-media-video-small">
                                            <img src="${s:mvcUrl("DC#loadImage").arg(0, "background.png").build()}"
                                                 class="profile-media-video-small-image">
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <div class="profile-media-video-small">
                                            <img src="${s:mvcUrl("DC#loadImage").arg(0, "background.png").build()}"
                                                 class="profile-media-video-small-image">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="profile-media-video-small">
                                            <img src="${s:mvcUrl("DC#loadImage").arg(0, "background.png").build()}"
                                                 class="profile-media-video-small-image">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="profile-media-video-small">
                                            <img src="${s:mvcUrl("DC#loadImage").arg(0, "background.png").build()}"
                                                 class="profile-media-video-small-image">
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div class="profile-media-music profile-media-content-element" id="profile-media-music">
                            <div style="width: 100%; height: 50px"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.js"></script>
<script>

    $(document).ready(function () {
        $(".profile-media-menu-btn").first().css("font-weight", "bold");
        let l2 = ($("#profile-media-container").width() - $(".profile-media-content-element").first().width()) / 2;
        $("#profile-media-content").css({left: l2});

        $.ajax({
            <%--url: "${s:mvcUrl("DC#loadPost").arg(0, 1).build()}=1",--%>
            url: "${s:mvcUrl("DC#loadAllPosts").arg(0, 'dajjsand').arg(1, 2).build()}",
            method: 'GET',
            cache: false,
            type: "text/json",

            success: function (res) {
                console.log(res);
                $("#posts-list").html(res);
            },
            error: function (res) {

            }
        })
            .always(function () {

            })
            .then(function () {

            });
    });

    function swipeMediaContent(button, num) {
        // change font for text
        $('.profile-media-menu-btn').each(function () {
            $(this).css("font-weight", "normal");
        });
        // move *
        let l = $(button).position().left + $(button).width() / 2;
        $("#profile-media-menu-selector").css({left: l});

        $(button).css("font-weight", "bold");
        // move content
        let l2 = $(".profile-media-content-element").first().width() * num * (-1) + ($("#profile-media-container").width() - $(".profile-media-content-element").first().width()) / 2;
        console.log(l2);
        $("#profile-media-content").css({left: l2});
    }
</script>
</body>
</html>
