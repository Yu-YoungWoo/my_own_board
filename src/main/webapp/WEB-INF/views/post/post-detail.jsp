<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
   <c:set var="title" value="나만의 보더"/>
   <%@ include file="../common/head.jsp" %>
</head>
<body class="form-background-color">

    <div class="container-lg d-flex flex-column window-size-fixed">
        <!-- header -->
        <jsp:include page="../common/header.jsp"></jsp:include>

        <!-- Post Detail -->
        <div class="shadow rounded-4 p-4 bg-white panel">
            <div class="row text-start pb-3" style="border-bottom: 1px solid #eee;">
                <h3>${map.get("post").title}</h3>
                <div class="post_author">
                    <div class="d-flex justify-content-start fl">
                        <!-- 글쓴이 -->
                        <span class="">${map.get("post").author}</span>
                        <!-- 생성일 -->
                        <span class="create_date">${map.get("post").create_date}</span>
                    </div>
                    <div class="d-flex justify-content-end fr">
                        <span class="views">
                            <span class="me-1">조회</span>
                            <span>${map.get("post").views}</span>
                        </span>
                        <span class="create_date">
                            <span class="me-1">추천</span>
                            <span>${map.get("post").likes}</span>
                        </span>
                    </div>
                </div>
            </div>
            <div class="row mt-5">
                <div class="col">
                    <p>${map.get("post").content}</p>
                </div>
            </div>
            <div class="row" style="margin-top: 70px;">
                <div class="col text-center">
                    <span class="me-3 fs-3 align-middle fw-bold">${map.get("post").likes}</span>
                    <button id="recom_up" type="button" class="btn btn-success me-3 recom_up"><span class="align-middle">추천</span></button>
                    <button id="recom_down" type="button" class="btn btn-secondary recom_down"><span class="align-middle">비추천</span></button>
                    <span class="ms-3 fs-3 align-middle fw-bold">${map.get("post").dislikes}</span>
                </div>
            </div>

            <div class="mt-5">
                <div class="row" style="border-bottom: 1px solid #eee;">
                    <div class="col text-start">
                        <div class="d-flex align-items-center">
                            <span class="me-2 fw-bold">전체 댓글</span>
                            <span class="fw-bold">0</span>
                        </div>
                    </div>
                    <c:if test="${canEditOrDelete}">
                        <div class="col text-end">
                            <a id="PostModify" class="fw-semibold link-dark link-underline link-underline-opacity-0 me-2" href="/post/${map.get('post').pri_no}/modify">수정</a>
                            <a id="postDelete" class="fw-semibold link-dark link-underline link-underline-opacity-0" onclick="deletePost(); return false;" href="javascript:void(0);">삭제</a>
                            <input type="hidden" id="pri_no" value="${map.get('post').pri_no}">
                        </div>
                    </c:if>
                </div>
                <div class="row mt-4">
                    <c:choose>
                        <c:when test=""></c:when>
                        <c:otherwise>
                            <p class="text-center">아직 댓글이 없습니다.</p>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="row mt-4">
                    <textarea id="comment-Textarea" class="form-control" name="comment" placeholder="타인의 권리를 침해하거나 명예를 훼손하는 댓글은 운영원칙 및 관련 법률에 제재를 받을 수 있습니다."></textarea>
                </div>

                <div class="row mt-2">
                    <button class="btn btn-secondary">등록</button>
                </div>
            </div>
        </div>
        

    </div>

    <!-- <jsp:include page="../common/footer.jsp"/> -->
    <script src="/js/post/post-detail.js"></script>
    <script src="/js/common.js"></script>
</body>
</html>