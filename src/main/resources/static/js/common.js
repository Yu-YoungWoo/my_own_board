$(document).ready(function() {
    $("#logout").click(function() {
        logoutUser();
    });

    function logoutUser() {
        
        $.ajax({
            type: 'GET',
            url: "http://localhost:8080/logout",
            success: function() {
                alert("로그아웃이 완료되었습니다.");
                window.location.href = '/login';
            }
        });
    }
});