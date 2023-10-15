<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <c:set var="title" value="유저 정보수정"/>
    <%@ include file="../common/head.jsp" %>
</head>
<body class="form-background-color font-nowrap">
    <div class="container-lg d-flex flex-column window-size-fixed">
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

        <svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
            <symbol id="check-circle-fill" fill="currentColor" viewBox="0 0 16 16">
              <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
            </symbol>
            <symbol id="info-fill" fill="currentColor" viewBox="0 0 16 16">
              <path d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zm.93-9.412-1 4.705c-.07.34.029.533.304.533.194 0 .487-.07.686-.246l-.088.416c-.287.346-.92.598-1.465.598-.703 0-1.002-.422-.808-1.319l.738-3.468c.064-.293.006-.399-.287-.47l-.451-.081.082-.381 2.29-.287zM8 5.5a1 1 0 1 1 0-2 1 1 0 0 1 0 2z"/>
            </symbol>
            <symbol id="exclamation-triangle-fill" fill="currentColor" viewBox="0 0 16 16">
              <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
            </symbol>
        </svg>

        <!-- <jsp:include page="../common/footer.jsp"/> -->
        <script src="/js/user/user-password-modify.js"></script>
        <script src="/js/common.js"></script>
    </div>
</body>
</html>