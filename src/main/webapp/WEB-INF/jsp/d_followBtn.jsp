<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<button class="profile-follow-btn" id="profile-follow-btn" onclick="changeRel(this)"
        <c:if test="${isOwn}">style="background: #C4C4C4" disabled</c:if> >
    <div class="profile-follow-btn-text">
        ${text}
    </div>
</button>