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
                                <input id="id" name="id" type="text" class="form-control" placeholder="4~20자/영문,숫자만 허용" value="" required/>
                                <button type="button" id="checkDupId" class="btn btn-outline-secondary ms-2">중복 확인</button>
                            </div>                            
                            <div id="invalidIdMsg" class="invalid-feedback ms-1">4~20자 / 영문, 숫자만 허용합니다.</div>

                            <!-- 아이디 중복 확인 체크 DIV -->
                            <jsp:include page="./id_dup_check.jsp"></jsp:include>

                        </div>
                        <div class="mb-4">
                            <label for="name">닉네임</label>
                            <div class="d-flex justify-content-start">
                                <input id="name" name="name" type="text" class="form-control" placeholder="2자 이상 16자 이하, 영어 또는 숫자 또는 한글 조합" value="${user.name}" required/>
                                <button id="checkDupName" type="button" class="btn btn-outline-secondary ms-2">중복 확인</button>
                            </div>
                            <div id="invalidNameEmptyMsg" class="invalid-feedback ms-1">공백은 허용하지 않습니다.</div>
                            <div id="invalidNameLengthMsg" class="invalid-feedback ms-1">닉네임의 길이는 2자 이상 16자 이하로 구성해야 합니다.</div>
                            

                            <jsp:include page="./name_dup_check.jsp"></jsp:include>
                        </div>

                        <div class="mb-4">
                            <label for="tel">전화번호</label>
                            <input id="tel" name="tel" type="text" class="form-control" placeholder="'-' 제외한 숫자만 입력" value="${user.tel}" required/>
                                                        
                            <div id="invalidTelMsg" class="invalid-feedback ms-1">'-'를 제외한 숫자만 허용합니다.</div>    
                        </div>

                        <div class="mb-4">
                            <label for="email">이메일</label>
                            <input id="email" name="email" type="text" class="form-control" placeholder="이메일은 example@example.com 형식만 허용" value="${user.email}" required/>
                                
                            <div id="invalidEmailMsg" class="invalid-feedback ms-1">이메일은 example@example.com 같이 형식에 맞게 입력해야 합니다.</div>   
                        </div>

                        <%-- 조건 미충족 --%>
                        <c:if test="${map.get('updateUserFailStatus')}">
                            <div class="d-grid text-center alert alert-danger">
                                <p>${map.get("updateUserFailMsg")}</p>
                            </div>
                        </c:if>
                        <%-- 유저 정보 삭제 실패 --%>
                        <c:if test="${userDeleteFailStatus}">
                            <div class="d-grid text-center alert alert-danger">
                                <p>${userDeleteFailMsg}</p>
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
                
                <div id="popup" class="position-fixed bottom-0 end-0 p-3" style="z-index: 11"></div>
            </form>
        </div>

        <jsp:include page="../common/svg_include.jsp"></jsp:include>
        
        <!-- <jsp:include page="../common/footer.jsp"/>       -->
        <script src="/js/user/user-detail.js"></script>
        <script src="/js/common.js"></script>
    </div>
</body>
</html>