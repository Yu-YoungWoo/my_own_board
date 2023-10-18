
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <c:set var="title" value="회원가입 - 나만의 보더"/>
    <%@ include file="common/head.jsp" %>
</head>
<body class="form-background-color font-nowrap">

    <div class="d-flex justify-content-center align-items-center mb-4 flex-column" style="min-height: 100vh;">
        <div class="d-flex mb-3 mt-4">
            <a href="/" class="link-secondary link-underline-opacity-0"><h1>나만의 보더</h1></a>
        </div>
        <form method="post" action="/join" style="width: 400px;" class="needs-validation" novalidate>
            <div class="row mb-3">
                <div class="col">
                    <label for="id">아이디</label>
                    <div class="d-flex justify-content-start">
                        <input id="id" type="text" class="form-control" placeholder="4~20자/영문,숫자만 허용" name="id" required/>
                        <button type="button" id="checkDupId" class="btn btn-outline-secondary ms-2">중복 확인</button>
                    </div>

                    <div id="invalidIdMsg" class="invalid-feedback ms-1">4~20자 / 영문, 숫자만 허용합니다.</div>

                    <div id="IdNotDupAlert" class="alert alert-success alert-dismissible fade show p-3 m-0 mt-2 cursor" role="alert">
                        <div class="d-flex justify-content-between align-items-center">
                            <span>중복되지 않은 아이디입니다.</span>
                            <i id="id-not-dup-close" class="fa-solid fa-xmark fa-lg"></i>
                        </div>
                    </div>
                    <div id="IdDupAlert" class="alert alert-danger alert-dismissible fade show p-3 m-0 mt-2 cursor" role="alert">
                        <div class="d-flex justify-content-between align-items-center">
                            <span>중복된 아이디입니다.</span>
                            <i id="id-dup-close" class="fa-solid fa-xmark fa-lg"></i>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col">
                    <label for="password">비밀번호</label>
                    <input id="password" type="password" class="form-control" placeholder="8~16자리 영문 대소문자,숫자,특수문자 조합" name="pw" required/>

                    <div id="invalidPasswordMsg" class="invalid-feedback ms-1">8~16자리/영문 대소문자,숫자,특수문자 조합만 허용합니다.</div>
                
                </div>
            </div>
            <div class="row mb-3">
                <div class="col">
                    <label for="name">닉네임</label>
                    <div class="d-flex justify-content-start">                    
                        <input id="name" type="text" class="form-control" placeholder="2자 이상 16자 이하, 영어 또는 숫자 또는 한글 조합" name="name" required/>
                        <button type="button" id="checkDupName" class="btn btn-outline-secondary ms-2">중복 확인</button>
                    </div>

                    <div id="invalidNameMsg" class="invalid-feedback ms-1">공백은 허용하지 않습니다.</div> 

                    <div id="NameNotDupAlert" class="alert alert-success alert-dismissible fade show p-3 m-0 mt-2 cursor" role="alert">
                        <div class="d-flex justify-content-between align-items-center">
                            <span>중복되지 않은 닉네임입니다.</span>
                            <i id="name-not-dup-close" class="fa-solid fa-xmark fa-lg"></i>
                        </div>
                    </div>
                    <div id="NameDupAlert" class="alert alert-danger alert-dismissible fade show p-3 m-0 mt-2 cursor" role="alert">
                        <div class="d-flex justify-content-between align-items-center">
                            <span>중복된 닉네임입니다.</span>
                            <i id="name-dup-close" class="fa-solid fa-xmark fa-lg"></i>
                        </div>
                    </div>                   
                </div>
            </div>
            <div class="row mb-3">
                <div class="col">
                    <label for="tel">전화번호</label>
                    <input id="tel" type="text" class="form-control" placeholder="'-' 제외한 숫자만 입력" name="tel" required/>
                    
                    <div id="invalidTelMsg" class="invalid-feedback ms-1">'-'를 제외한 숫자만 허용합니다.</div>
                    
                </div>
            </div>
            <div class="row mb-3">
                <div class="col">
                    <label for="email">이메일</label>
                    <input id="email" type="text" class="form-control" placeholder="example@example.com" name="email" required/>

                    <div id="invalidEmailMsg" class="invalid-feedback ms-1">이메일은 example@example.com 같이 형식에 맞게 입력해야 합니다.</div>
                
                </div>
            </div>

            <%-- 조건 미충족 --%>
            <c:if test="${map.get('formValidFailStatus')}">
                <div class="d-grid text-center alert alert-danger">
                    <p>${map.get("formValidFailMsg")}</p>
                </div>
            </c:if>

            <%-- DB insert 실패 --%>
            <c:if test="${map.get('createUserFailStatus')}">
                <div class="d-grid text-center alert alert-danger">
                    <p>${map.get("createUserFailMsg")}</p>
                </div>
            </c:if>

            <!-- 중복된 아이디 -->
            <c:if test="${map.get('duplicateUserByIdStatus')}">
                <div class="d-grid text-center alert alert-danger">
                    <p>${map.get("duplicateUserByIdFailMsg")}</p>
                </div>
            </c:if>            
            
            <!-- 중복된 닉네임 -->
            <c:if test="${map.get('duplicateUserByNameStatus')}">
                <div class="d-grid text-center alert alert-danger">
                    <p>${map.get("duplicateUserByNameFailMsg")}</p>
                </div>
            </c:if>   

            <div class="d-grid gap-2 col-6 mx-auto mt-3">
                <button class="btn btn-primary form-button-color" type="submit">회원가입</button>
            </div>
        </form>
    </div>

    <script src="/js/user/join.js"></script>
</body>
</html>