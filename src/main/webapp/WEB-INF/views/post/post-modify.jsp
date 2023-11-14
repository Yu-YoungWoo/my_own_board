<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <c:set var="title" value="게시글 수정"/>
    <%@ include file="../common/head.jsp" %>
</head>
<body class="form-background-color font-nowrap">
    
    <div class="container-lg d-flex flex-column window-size-fixed" style="min-height: 100vh;">
        <jsp:include page="../common/header.jsp">
            <jsp:param name="userId" value="${post.id}"/>
        </jsp:include>

        <div class="shadow rounded-4 p-3 bg-white panel">
            <form id="modify-post" method="post" action="/post/${map.get('post').pri_no}/modify">
                <h2 class="text-center mt-2 m-0">게시글 수정</h2>
                <div class="row mb-3 w-25">
                    <div class="col">
                        <label for="name" class="mb-2 ms-1">글쓴이</label>
                        <input id="name" type="text" class="form-control" value="${map.get('post').author}" disabled/>
                        <input type="hidden" name="name" value="${map.get('post').author}">
                    </div>
                </div>
                <div class="row mb-5 w-55">
                    <div class="col">
                        <label for="title" class="mb-2 ms-1">제목</label>
                        <input id="title" type="text" class="form-control" name="title" value="${map.get('post').title}" placeholder="제목을 입력 해주세요."/>
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col">
                        <textarea id="write-Textarea" class="form-control" name="content" placeholder="타인을 비방하는 게시글은 삭제될 수 있습니다.">${map.get('post').content}</textarea>
                    </div>
                </div>
                <!-- 게시글 번호 -->
                <input type="hidden" id="pri_no" value="${map.get('post').pri_no}">
                
                <div class="d-grid gap-2 d-md-flex justify-content-md-end ">
                    <div class="row">
                        <div class="col">
                            <a href="/post/${map.get('post').pri_no}"><button type="button" class="btn btn-secondary write-submit-btn">취소</button></a>
                        </div>
                        <div class="col">
                            <button id="modify-btn" type="submit" class="btn btn-primary form-button-color write-submit-btn">등록</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <!-- <jsp:include page="../common/footer.jsp"/> -->
    </div>

    <script src="/js/post/post-modify.js"></script>
    <script src="/js/common.js"></script>
</body>
</html>