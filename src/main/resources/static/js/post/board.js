$(document).ready(function() {

    $("#search-btn").click(function() {
        const searchInput = document.getElementById("search-input");

        searchInput.sub
    });

    
    const searchInput = document.getElementById("search-input");
    /* 한글 입력 시 2번 입력되는 문제 해결 - keypress */
    searchInput.addEventListener("keypress", function(event) {
        if(event.key== "Enter") {
            event.preventDefault();
            getSearchList();
        }
    });

    function getSearchList() {
        const query = $("#search-input").val();
        const search_type = $("#search_type").val();

        $.ajax({
            type: 'GET',
            url: "http://localhost:8080/search?query=" + query + "&" + "search_type=" + search_type,
            dataType: "json",
            success: function(result) {
                const tbody = $("#posts");
                $("[id^='postRow-']").remove();

                result.search_posts.forEach((post, index) => {
                    const newRow = $("<tr></tr>")
                                    .attr("id", "postRow-" + index)
                                    .addClass("pointer")
                                    .on("click", function() {
                                        window.location.href = '/post/' + post.pri_no;  // 이벤트 핸들러 등록
                                    });
                                    
                    newRow.append($("<td></td>").addClass("b-num").text(post.pri_no))
                            .append($("<td></td>").addClass("b-title").text(post.title))
                            .append($("<td></td>").addClass("b-auth").text(post.author))
                            .append($("<td></td>").addClass("b-date").text(post.create_date))
                            .append($("<td></td>").addClass("b-views").text(post.views))
                            .append($("<td></td>").addClass("b-likes").text(post.likes));

                    tbody.append(newRow);
                    console.log(post); 
                });
            }
        });
    }
});