$(document).ready(function() {

    $("#search-btn").click(function() {
        document.getElementById("search-form").submit();
    });

    $("#search-input").keyup(function(event) {
        if(event.key== "Enter") {
            getSearchList();
        }
    });

    function getSearchList() {
        const query = $("#search-input").val();
        const search_type = $("#search_type").val();

        console.log("query : " + query);
        console.log("search_type : " + search_type);

        $.ajax({
            type: 'GET',
            url: "http://localhost:8080/search?query=" + query + "&" + "search_type=" + search_type,
            dataType: "html",
            success: function(result) {
                    // const data = JSON.parse(result);
                    const tbody = $("#board > tbody");
                    tbody.empty();
                    // data.forEach(function(item) {
                    //     let row = $("<tr></tr>");
                    //     row.append("<td>" + item.id + "</td>");
                    //     row.append("<td>" + item.title + "</td>");
                    //     row.append("<td>" + item.author + "</td>");
                    //     row.append("<td>" + item.date + "</td>");
                    //     row.append("<td>" + item.views + "</td>");
                    //     row.append("<td>" + item.likes + "</td>");
                        
                    //     tbody.append(row);
                    // });
                    // console.log(result);
            }
        });
    }
});