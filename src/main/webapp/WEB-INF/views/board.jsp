<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
   <c:set var="title" value="나만의 보더"/>
   <%@ include file="common/head.jsp" %>
</head>
<body class="form-background-color">
    
    <div class="container-lg d-flex flex-column window-size-fixed">
        <jsp:include page="./common/header.jsp"></jsp:include>
        <!-- post -->
        <div class="shadow rounded-4 p-3 bg-white text-center panel">
            <table id="board" class="board-list">
                <colgroup>
                    <col style="width: 7%;">
                    <col>
                    <col style="width: 15%;">
                    <col style="width: 6%;">
                    <col style="width: 6%;">
                    <col style="width: 6%;">
                </colgroup>
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">제목</th>
                        <th scope="col">글쓴이</th>
                        <th scope="col">작성일</th>
                        <th scope="col">조회</th>
                        <th scope="col">추천</th>
                    </tr>
                </thead>
                <tbody id="posts">
                    <c:forEach items="${map.get('posts')}" var="post" varStatus="status">
                        <tr id="postRow-${status.count}" class="pointer post-row" onclick="window.location.href='/post/${post.pri_no}'">
                            <td class="b-num">${post.pri_no}</td>
                            <td class="b-title">${post.title}</td>
                            <td class="b-auth">${post.author}</td>
                            <td class="b-date">${post.create_date}</td>
                            <td class="b-views">${post.views}</td> 
                            <td class="b-likes">${post.likes}</td> 
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        
            <div class="d-flex flex-column align-items-center">
                <div class="d-felx flex-column align-items-center mt-4">
                    <ul class="pagination justify-content-center">

                        <!-- <c:if test="${map.get('prev')}">
                            <li class="page-item">
                                <a class="page-link">&laquo;</a>
                            </li>
                        </c:if> -->

                        <c:forEach begin="${map.get('startPage')}" end="${map.get('endPage')}" var="pageNumber">
                            <c:choose>
                                <c:when test="${displayPagingNumber}">
                                    <li class="page-item">
                                        <a class="page-link" href="/search?page=${pageNumber}&query=${map.get('query')}&search_type=${map.get('search_type')}">${pageNumber}</a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item">
                                        <a class="page-link" href="/?page=${pageNumber}">${pageNumber}</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>   
                        </c:forEach>

                        <!-- <c:if test="${map.get('next')}">
                            <li class="page-item">
                                <a class="page-link">&raquo;</a>
                            </li>
                        </c:if> -->

                    </ul>
                </div>

                <div class="row align-items-center">
                    <div class="col">
                        <form id="search-form" method="get" class="form-container search" name="search-form" action="/search">
                            <div class="d-flex justify-content-center align-items-center" style="width: 500px; min-width: 350px;">
                                <select id="search_type" class="form-select me-2" name="search_type" style="width: 200px;" aria-label="Default select">
                                    <option selected value="title">제목</option>
                                    <option value="author">글쓴이</option>
                                    <option value="author_title">글쓴이 + 제목</option>
                                </select>
                                <input id="search-input" type="text" class="form-control" name="query" placeholder="검색" style="display: inline;" value/>

                                <p id="search-btn" class="mb-0 link-secondary" style="cursor: pointer; width: 80px;">
                                    <i class="fas fa-regular fa-magnifying-glass fa-xl"></i>
                                </p>                                                       
                            </div>                            
                        </form>
                    </div>
                    <div class="col">
                        <a href="/write" class="link-secondary" style="cursor: pointer;">
                            <p id="write-btn" class="mb-0 link-secondary" style="display: inline-block; cursor: pointer;">
                                <i class="fas fa-pen fa-xl"></i>
                            </p>
                        </a>     
                    </div>
                </div>
            </div>
        </div>
        
        <!-- <jsp:include page="./common/footer.jsp"/> -->
    </div>

    <script src="/js/post/board.js"></script>
    <script src="/js/common.js"></script>
</body>
</html>