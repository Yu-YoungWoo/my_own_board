<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <c:set var="title" value="로그인 - 나만의 보더"/>
    <%@ include file="common/head.jsp" %>
</head>
<body class="form-background-color font-nowrap">
    <div class="container d-flex align-items-center justify-content-center flex-column window-size-fixed" style="min-height: 100vh;">
        <div class="shadow rounded-4 p-3 m-5 bg-white">
            <div class="row m-4">
                <div class="col text-center">
                    <img class="rounded img-fluid" src="/img/sample.jpg" alt="Sample Image" width="75%" height="75%"/>
                </div>
            </div>
            <div class="row justify-content-center mb-4">
                <form id="login-form" action="/login" method="post" class="col-6 w-50">
                        <div class="form-group mb-3">
                            <label class="form-label" for="id">아이디</label>
                            <input type="text" class="form-control" id="id" aria-describedby="Id-Help" name="id" placeholder="아이디"/>
                        </div>
                        
                        <div class="form-group mb-3">
                            <label class="form-label" for="password">비밀번호</label>
                            <input type="password" class="form-control" id="password" name="pw" placeholder="비밀번호"/>
                        </div>

                        <c:if test="${error}">
                            <div class="row justify-content-center mb-4">
                                <p class="text-danger">${exception}</p>
                            </div>
                        </c:if>
                        <div class="d-grid gap-2 col-6 mx-auto">
                            <button id="login-btn" class="btn btn-primary form-button-color" type="submit">로그인</button>
                        </div>

                        <hr class="border border-dark"/>

                        <div class="row justify-content-center align-items-center text-center align-middle">
                            <div class="col">
                                <a class="link-underline link-underline-opacity-0 link-secondary" href="/join">회원가입</a>
                            </div>
                            <div class="col">
                                <a class="link-underline link-underline-opacity-0 link-secondary" href="/help/id">아이디 찾기</a>
                            </div>
                            <div class="col">
                                <a class="link-underline link-underline-opacity-0 link-secondary" href="/help/pw">비밀번호 찾기</a>
                            </div>
                        </div>
                </form>
            </div>
        </div>
    </div>
    <!-- <jsp:include page="./common/footer.jsp"/> -->

    <script src="/js/user/login.js"></script>
</body>
</html>