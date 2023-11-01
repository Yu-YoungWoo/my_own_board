$("#search-btn").click(function() {
    getSearchList();
    
});

const searchInput = document.getElementById("search-input");
/* 한글 입력 시 2번 입력되는 문제 해결 - keypress */
searchInput.addEventListener("keyup", function(event) {
    console.log("테스트");

    if(event.key == "Enter") {
        event.preventDefault();
        getSearchList();
    }
});




function getSearchList() {
    const query = $("#search-input").val();
    const search_type = $("#search_type").val();

    const param = new URLSearchParams();

    if(query.trim() == "") {
        alert("검색어를 입력해주세요.");
    }

    param.append("query", query);
    param.append("search_type", search_type);

    const url = "http://localhost:8080/search?" + param.toString();
    $.ajax({
        type: 'GET',
        url:  url,
        dataType: "json",
        success: function(result) {
        }
    }); 
}