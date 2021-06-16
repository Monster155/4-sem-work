<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty text}">
    <button class="profile-follow-btn" id="profile-follow-btn" onclick="changeRel(this)">
        <div class="profile-follow-btn-text">
                ${text}
        </div>
    </button>
</c:if>