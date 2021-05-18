<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>
<head>
    <title>${profile.fullname}</title>
    <link href="/css/profile.css" rel="stylesheet" type="text/css">
</head>
<body>
<%--<img src="<c:url value='/images/background.png'/>" class="background-image" id="1">--%>
<img src="${s:mvcUrl("DC#loadImage").arg(0, "background").build()}" class="background-image">
<div class="content" id="content">
    <div class="menu-main">
        <jsp:include page="d_menu.jsp"/>
    </div>
    <div class="main" id="main">
        <div class="posts-container">
            <div class="post-new">
                <div class="post-new-profile-photo">
                    <img src="data: image/png; base64, ${profile.photo.photo}"
                         class="post-new-profile-photo-element">
                </div>
                <f:form class="post-form hor" method="post" enctype="multipart/form-data"
                        action='${s:mvcUrl("PC#addPost").build()}' modelAttribute="post">
                    <label class="post-new-text">
                        <f:input path="text" class="post-new-text-input" placeholder="What's new..."/>
                    </label>
                    <div class="ver" style="align-items: center;">
                        <div class="hor">
                            <div>
                                <button class="post-new-add-photo post-new-add-logo" id="post-new-add-photo"
                                        type="button">
                                    <img src="${s:mvcUrl("DC#loadImage").arg(0, "photo_icon").build()}"
                                         class="post-new-add-logo-image" id="post-new-add-photos-image">
                                </button>
                                <input id="images" name="images" type="file" accept="image/png, image/jpeg"
                                       multiple capture hidden/>
                            </div>
                            <div class="post-new-add-video post-new-add-logo">
                                <img src="${s:mvcUrl("DC#loadImage").arg(0, "video_icon").build()}"
                                     class="post-new-add-logo-image">
                            </div>
                            <div class="post-new-add-music post-new-add-logo">
                                <img src="${s:mvcUrl("DC#loadImage").arg(0, "music_icon").build()}"
                                     class="post-new-add-logo-image">
                            </div>
                            <div class="post-new-add-wyswig post-new-add-logo">
                                <img src="${s:mvcUrl("DC#loadImage").arg(0, "wyswig_icon").build()}"
                                     class="post-new-add-logo-image">
                            </div>
                        </div>
                        <button class="post-new-add-btn" id="post-new-add-btn" type="submit" hidden>
                            Post
                        </button>
                    </div>
                </f:form>
            </div>
            <div class="post-search">
                <div class="post-search-logo">
                    <img src="${s:mvcUrl("DC#loadImage").arg(0, "search_icon").build()}"
                         class="post-search-logo-image">
                </div>
                <div class="post-search-text">Search...</div>
                <div class="post-search-voice-logo">
                    <img src="${s:mvcUrl("DC#loadImage").arg(0, "voice_icon").build()}"
                         class="post-search-voice-logo-image">
                </div>
            </div>
            <%--posts--%>
            <div class="posts-list" id="posts-list"></div>
        </div>
        <div class="profile">
            <div class="profile-info">
                <div class="profile-info-own">
                    <div class="profile-info-own-photo">
                        <img src="data: image/png; base64, ${profile.photo.photo}"
                             class="profile-info-own-photo-element">
                    </div>
                    <div class="profile-info-own-text">
                        <div class="profile-name">${profile.fullname}</div>
                        <div class="profile-location">${profile.location}</div>
                    </div>
                </div>
                <div class="profile-info-contacts">
                    <div class="profile-followers">
                        <div class="profile-followers-count">${profile.followersCount}</div>
                        <div class="profile-followers-text">Followers</div>
                    </div>
                    <div class="profile-friends">
                        <div class="profile-friends-count">${profile.friendsCount}</div>
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
                            <c:set var="numCols" value="3"/>
                            <c:set var="colsCount" value="0"/>
                            <table>
                                <tr>
                                    <c:forEach items="${profile.images}" var="img" varStatus="status">
                                    <c:if test="${colsCount lt numCols}">
                                    <td>
                                        <img src="data: image/png; base64, ${img.photo}"
                                             class="profile-media-photo-small-image">
                                    </td>
                                    <c:set var="colsCount" value="${colsCount + 1}"/>
                                    <c:if test="${colsCount ge numCols}">
                                    <c:set var="colsCount" value="0"/>
                                </tr>
                                <tr>
                                    </c:if>
                                    </c:if>
                                    </c:forEach>
                                </tr>
                            </table>
                        </div>
                        <div class="profile-media-video profile-media-content-element" id="profile-media-video">
                            <table>
                                <tr>
                                    <td>
                                        <div class="profile-media-video-small">
                                            <img src="${s:mvcUrl("DC#loadImage").arg(0, "background").build()}"
                                                 class="profile-media-video-small-image">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="profile-media-video-small">
                                            <img src="${s:mvcUrl("DC#loadImage").arg(0, "background").build()}"
                                                 class="profile-media-video-small-image">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="profile-media-video-small">
                                            <img src="${s:mvcUrl("DC#loadImage").arg(0, "background").build()}"
                                                 class="profile-media-video-small-image">
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <div class="profile-media-video-small">
                                            <img src="${s:mvcUrl("DC#loadImage").arg(0, "background").build()}"
                                                 class="profile-media-video-small-image">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="profile-media-video-small">
                                            <img src="${s:mvcUrl("DC#loadImage").arg(0, "background").build()}"
                                                 class="profile-media-video-small-image">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="profile-media-video-small">
                                            <img src="${s:mvcUrl("DC#loadImage").arg(0, "background").build()}"
                                                 class="profile-media-video-small-image">
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <div class="profile-media-video-small">
                                            <img src="${s:mvcUrl("DC#loadImage").arg(0, "background").build()}"
                                                 class="profile-media-video-small-image">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="profile-media-video-small">
                                            <img src="${s:mvcUrl("DC#loadImage").arg(0, "background").build()}"
                                                 class="profile-media-video-small-image">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="profile-media-video-small">
                                            <img src="${s:mvcUrl("DC#loadImage").arg(0, "background").build()}"
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
<%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.js"></script>--%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>

    $("#post-new-add-photo").click(function () {
        $("#images").click();
    });

    $("#images").change(function (ev) {
        if (this.files) {
            $("#post-new-add-photos-image").prop('disabled', true);
            $("#post-new-add-photo").html(this.files.length);
        } else {
            $("#post-new-add-photos-image").prop('disabled', false);
            $("#post-new-add-photo").html();
        }
    });

    $('#images').change(function () {
        if (this.files.length > 10)
            alert('Too many files')
    });

    // Prevent submission if limit is exceeded.
    $('form').submit(function () {
        if (this.files.length > 10)
            return false;
    });

    $("#text").focus(function () {
        $("#post-new-add-btn").show();
        $(".post-new").css({height: '12vh'});
    });
</script>
<script>

    $(document).ready(function () {
        $(".profile-media-menu-btn").first().css("font-weight", "bold");
        let l2 = ($("#profile-media-container").width() - $(".profile-media-content-element").first().width()) / 2;
        $("#profile-media-content").css({left: l2});

        $.ajax({
            url: "${s:mvcUrl("PC#loadAllPosts").arg(0, profile.nickname).arg(1, '4').build()}",
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
