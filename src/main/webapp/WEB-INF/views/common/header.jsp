<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- TOP Element -->
<div class="d-flex mb-4">
    <div class="d-flex justify-content-start align-items-center flex-grow-1">
        <p class="mb-0"><span class="align-middle"><i class="fa-solid fa-bars fa-xl"></i></span></p>
        <a href="/" class="link-secondary link-underline-opacity-0"><p class="fs-2 mb-0 ms-4 text-center align-items-center"><span class="align-middle">나만의 보더</span></p></a>
    </div>
    <div class="d-flex align-items-center">
        <c:choose>
            <c:when test="${isAuthenticated}">
                <button id="logout" class="btn btn-outline-secondary">로그아웃</button>
                <a href="/user-detail" class="link-secondary link-underline-opacity-0">
                    <p class="fs-2 mb-0 ms-3 text-center align-items-center">
                        <span class="align-middle">
                            <i class="fa-regular fa-circle-user fa-lg"></i>
                        </span>
                    </p>
                </a>
            </c:when>
            <c:otherwise>
                <button id="login" class="btn btn-outline-secondary">로그인</button>
            </c:otherwise>
        </c:choose>    
    </div>
</div>