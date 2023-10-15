<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <c:set var="title" value="개인정보 수정"/>
    <%@ include file="../common/head.jsp" %>
</head>
<body class="form-background-color font-nowrap">
    <div class="container-lg d-flex flex-column window-size-fixed">
        <jsp:include page="../common/header.jsp">
            <jsp:param name="userId" value="${post.id}"/>
        </jsp:include>

        <!-- content -->

        <div class="row justify-content-center">
            <div class="text-center mb-5">
                <h2>개인정보 수정</h2>
            </div>
            <form id="userModifyForm" method="post" action="/basic-info-modify" class="needs-validation row" novalidate>
                <div class="col d-flex justify-content-center border-end border-2">
                    <div class="w-75">
                        <div class="mb-4">
                            <label for="id">현재 아이디</label>
                            <div class="d-flex justify-content-start">
                                <input id="curId" type="text" class="form-control" placeholder="4~20자/영문,숫자만 허용" value="${user.id}" required readonly disabled/>
                            </div>            
                        </div>
                                           
                        <div class="mb-4"> 
                            <label for="id">변경할 아이디</label>
                            <div class="d-flex justify-content-start">
                                <input id="id" type="text" class="form-control" placeholder="4~20자/영문,숫자만 허용" name="id" value="" required/>
                                <button type="button" id="checkDupId" class="btn btn-outline-secondary ms-2">중복 확인</button>
                            </div>                            
                            <div id="invalidIdMsg" class="invalid-feedback ms-1">4~20자 / 영문, 숫자만 허용합니다.</div>


                            <div id="IdNotDupAlert" class="alert alert-success alert-dismissible fade show p-3 m-0 mt-2" role="alert">
                                <div class="row">
                                    <div class="col p-0" style="display: inline-block; text-align: center;">
                                        <svg class="bi flex-shrink-0 me-2 align-middle ms-2" width="24" height="24" role="img" aria-label="Success:"><use xlink:href="#check-circle-fill"/></svg>
                                        <span>중복되지 않은 아이디입니다.</span>
                                    </div>                           
                                    <div class="col p-0 me-4" style="display: inline-block; text-align: right">
                                        <i id="close" class="fa-solid fa-xmark fa-lg cursor"></i>
                                    </div>
                                </div>
                            </div>


                            <div id="IdDupAlert" class="alert alert-danger alert-dismissible fade show p-3 m-0 mt-2" role="alert">
                                <div class="row">
                                    <div class="col p-0" style="display: inline-block; text-align: center;">
                                        <svg class="bi flex-shrink-0 me-2 align-middle ms-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
                                        <span>중복된 아이디입니다.</span>
                                    </div>
                                    <div class="col p-0 me-4" style="display: inline-block; text-align: right">
                                        <i id="close" class="fa-solid fa-xmark fa-lg cursor"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="mb-4">
                            <label for="name">닉네임</label>                    
                            <input id="name" type="text" class="form-control" placeholder="2자 이상 16자 이하, 영어 또는 숫자 또는 한글 조합" name="name" value="${user.name}" required/>
        
                            <div id="invalidNameEmptyMsg" class="invalid-feedback ms-1">공백은 허용하지 않습니다.</div>
                            <div id="invalidNameLengthMsg" class="invalid-feedback ms-1">닉네임의 길이는 2자 이상 16자 이하로 구성해야 합니다.</div>
                        </div>

                        <div class="mb-4">
                            <label for="tel">전화번호</label>
                            <input id="tel" type="text" class="form-control" placeholder="'-' 제외한 숫자만 입력" name="tel" value="${user.tel}" required/>
                                                        
                            <div id="invalidTelMsg" class="invalid-feedback ms-1">'-'를 제외한 숫자만 허용합니다.</div>    
                        </div>

                        <div class="mb-4">
                            <label for="email">이메일</label>
                            <input id="email" type="text" class="form-control" placeholder="이메일은 example@example.com 형식만 허용" name="email" value="${user.email}" required/>
                                
                            <div id="invalidEmailMsg" class="invalid-feedback ms-1">이메일은 example@example.com 같이 형식에 맞게 입력해야 합니다.</div>   
                        </div>

                        <%-- 조건 미충족 --%>
                        <c:if test="${map.get('updateUserFailStatus')}">
                            <div class="d-grid text-center alert alert-danger">
                                <p>${map.get("updateUserFailMsg")}</p>
                            </div>
                        </c:if>                
                    </div>
                </div>
                <div class="col d-flex justify-content-center ">
                    <div class="w-75 d-flex flex-column justify-content-center">                        
                        <div class="d-flex flex-column justify-content-center">
                            <a href="/user-password-modify" class="mb-4"><button type="button" class="btn btn-secondary w-100">비밀번호 변경</button></a>        
                        </div>
                        <div class="d-flex flex-column justify-content-center">
                            <button id="user-delete" type="button" class="btn btn-danger w-100">계정 삭제</button>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="d-flex justify-content-center mt-4">
                        <div class="row">
                            <div class="col">                                
                                <button id="submit-btn" type="submit" class="btn btn-primary form-button-color write-submit-btn">수정</button>
                            </div>
                            <div class="col">
                                <a href="/">
                                    <button type="button" class="btn btn-secondary write-submit-btn">취소</button>
                                </a>
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
        <!-- <jsp:include page="../common/footer.jsp"/>       -->
        <script src="/js/user/user-detail.js"></script>
        <script src="/js/common.js"></script>
    </div>
</body>
</html>