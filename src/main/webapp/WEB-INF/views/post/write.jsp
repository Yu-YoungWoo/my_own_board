<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <c:set var="title" value="게시글 작성"/>
    <%@ include file="../common/head.jsp" %>
</head>
<body class="form-background-color font-nowrap">
    
    <div class="container-lg window-size-fixed mt-5">
        <jsp:include page="../common/header.jsp">
            <jsp:param name="userId" value="${post.id}"/>
        </jsp:include>

        <div class="shadow rounded-4 p-3 bg-white panel">
            <form id="post" method="post" action="/write">
                <h2 class="text-center mt-2 m-0">게시글 작성</h2>
                <div class="row mb-3 w-25">
                    <div class="col">
                        <label for="name" class="mb-2 ms-1">글쓴이</label>
                        <input id="name" type="text" class="form-control" value="${user_name}" disabled/>
                        <input type="hidden" name="name" value="${user_name}">
                    </div>
                </div>
                <div class="row mb-5 w-55">
                    <div class="col">
                        <label for="title" class="mb-2 ms-1">제목</label>
                        <input id="title" type="text" class="form-control" name="title" placeholder="제목을 입력 해주세요."/>
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col">
                        
                        <textarea id="writeTextarea" class="form-control textarea-noresize" name="content" placeholder="타인을 비방하는 게시글은 삭제될 수 있습니다."></textarea>
                          
                    </div>
                </div>
                <div class="d-grid gap-2 d-md-flex justify-content-md-end ">
                    <div class="row">
                        <div class="col">
                            <a href="/"><button type="button" class="btn btn-secondary write-submit-btn">취소</button></a>
                        </div>
                        <div class="col">
                            <button id="confirm-btn" type="button" class="btn btn-primary form-button-color write-submit-btn">등록</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <!-- <jsp:include page="../common/footer.jsp"/> -->
    </div>

    <script src="/js/post/write.js"></script>
    <script src="/js/common.js"></script>
</body>
</html>