<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <c:set var="title" value="유저 정보수정"/>
    <%@ include file="../common/head.jsp" %>
</head>
<body class="form-background-color font-nowrap">
    <div class="container-lg window-size-fixed mt-5">
        <jsp:include page="../common/header.jsp">
            <jsp:param name="userId" value="${post.id}"/>
        </jsp:include>

        <!-- content -->

        <div class="row justify-content-center">
            <div class="text-center mb-1www">
                <h2>비밀번호 변경</h2>
            </div>
            <div class="alert alert-secondary w-75 text-center" role="alert">
                비밀번호는 <strong>8~16자리/영문 대소문자,숫자,특수문자 조합</strong>만 허용합니다.
            </div>
            <form id="userModifyForm" method="post" action="/user-password-modify" class="needs-validation row justify-content-center" novalidate>
                <div class="d-flex flex-column w-55 p-2 mt-3">           
                    <div class="row mb-3">
                        <div class="col">
                            <label for="cur-password" class="">현재 비밀번호</label>
                            <input id="cur-password" type="password" class="form-control" name="curPassword" placeholder="현재 비밀번호 입력" required/>

                            <!-- <div id="invalidCurPasswordMsg" class="invalid-feedback ms-1">8~16자리/영문 대소문자,숫자,특수문자 조합만 허용합니다.</div> -->
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col">
                            <label for="new-password" class="">새 비밀번호</label>
                            <input id="new-password" type="password" class="form-control" name="newPassword" placeholder="새 비밀번호 입력" name="pw" required/> 

                            <!-- <div id="invalidNewPasswordMsg" class="invalid-feedback ms-1">8~16자리/영문 대소문자,숫자,특수문자 조합만 허용합니다.</div> -->
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col">
                            <label for="new-password-check" class="">새 비밀번호 확인</label>
                            <input id="new-password-check" type="password" class="form-control" name="newPasswordCheck" placeholder="새 비밀번호 확인 입력" required/>
                            <!-- <div id="invalidNewPasswordCheckMsg" class="invalid-feedback ms-1">8~16자리/영문 대소문자,숫자,특수문자 조합만 허용합니다.</div> -->

                            <div class="alert alert-success mt-3" id="alert-success">비밀번호가 일치합니다.</div>
                            <div class="alert alert-danger mt-3" id="alert-danger">비밀번호가 일치하지 않습니다.</div>
                        </div>
                    </div>
                    <c:if test="${map.get('passWordUpdateFailStatus')}">
                        <div class="row">
                            <div class="col">
                                <div id="IdNotDupAlert" class="alert alert-danger alert-dismissible fade show p-3 m-0 mt-2 cursor" role="alert">
                                    <div class="d-flex justify-content-start align-items-center">
                                        <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
                                        <span>${map.get('passWordUpdateFailMsg')}</span>
                                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>
        
                    <div class="row">
                        <div class="d-flex justify-content-center mt-4">
                            <div class="row">
                                <div class="col">                                
                                    <button id="submit-btn" class="btn btn-primary form-button-color write-submit-btn">수정</button>
                                </div>

                                <div class="col">
                                    <a href="/user-detail">
                                        <button type="button" class="btn btn-secondary write-submit-btn">취소</button>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>  
            </form>
        </div> 

        <jsp:include page="../common/svg_include.jsp"></jsp:include>

        <!-- <jsp:include page="../common/footer.jsp"/> -->
        <script src="/js/user/user-password-modify.js"></script>
        <script src="/js/common.js"></script>
    </div>
</body>
</html>