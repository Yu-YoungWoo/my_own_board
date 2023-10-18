<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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