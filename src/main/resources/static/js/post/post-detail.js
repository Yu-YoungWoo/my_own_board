$(document).ready(function() {

    function appendNewComment(comment) {
        
        const newComment = `
            <li class="pt-3">
                <div class="mt-4 m-0">
                    <div class="comment-row m-0 p-0">
                        <div class="comment-info">
                            <div id="author" class="comment-author">
                                <span>${comment.cmt_name}</span>
                            </div>
                            <div class="comment-button">
                                <span class="comment-date">${comment.create_date}</span>
                                <span class="comment-delete">삭제</span>
                            </div>
                        </div>
                        <div class="comment-content">
                            <span>
                                ${comment.cmt_content}
                            </span>
                        </div>
                    </div>
                </div>
            </li>
        `;

        return newComment;
    }

    $("#recom_up").click(function(e) {
        e.preventDefault();
        likePost();

    });

    $("#recom_down").click(function(e) {
        e.preventDefault();
        disLikePost();
    })

    $("#deletePost").click(function(e) {
        e.preventDefault();
        deletePost();
    });

    $("#cmt_btn").click(function(e) {
        e.preventDefault();
        insertComment();
    });


    function likePost() {

        const postId = $('#pri_no').val();
        const like = $("#like").text();

        const param = new URLSearchParams();

        if(postId === undefined || postId === "") {
            alert("게시글 ID 조회 실패");   
            return;
        }

        if (like === undefined || like === "") {
            alert("추천 조회 실패");
            return;
        }

        param.append("id", postId);
        param.append("like", like);

        const url = 'http://localhost:8080/check/like?' + param.toString();
        $.ajax({
            type: 'GET',
            url: url,
            dataType: "json",
            success: function(result) {

                if(result["updateStatus"] == true) {
                    let like = document.getElementById("like");
                    let headerLike = document.getElementById("header-like");
                    like.innerText = result["like"]; 
                    headerLike.innerText = result["like"];                    
                } else {
                    alert("추천 실패! 관리자에게 문의하세요.");
                }
            },
            error: function() {

                alert("추천 실패! 관리자에게 문의하세요.");
                
            }
        });
    }

    function disLikePost() {
        const postId = $('#pri_no').val();
        const dislike = $("#dislike").text();

        const param = new URLSearchParams();

        if(postId === undefined || postId === "") {
            alert("게시글 ID 조회 실패");   
            return;
        }

        if (dislike === undefined || dislike === "") {
            alert("비추천 조회 실패");
            return;
        }
        param.append("id", postId);
        param.append("dislike", dislike);

        const url = 'http://localhost:8080/check/dislike?' + param.toString();

        $.ajax({
            type: 'GET',
            url: url,
            dataType: "json",
            success: function(result) {

                if(result["updateStatus"] == true) {
                    let dislike = document.getElementById("dislike");
                    dislike.innerText = result["dislike"]; 
                } else {
                    alert("추천 실패! 관리자에게 문의하세요.");
                }
            },
            error: function() {

                alert("추천 실패! 관리자에게 문의하세요.");
                
            }
        });


    }

    function deletePost() {
        const confirmation = confirm("삭제하시겠습니까?");

        const postId = $('#pri_no').val();

        console.log("postId : " + postId);
    
        if(confirmation) {
            
            if(postId === undefined || postId === "") {
                alert("게시글 번호 불러오기 오류!");
                return;
            }  else {
                $.ajax({
                    type: 'POST',
                    url: "http://localhost:8080/post/" + postId + "/delete",
                    contentType: "application/json",
                    success: function(response) {
                        if(response === true) {
                            window.location.href = "http://localhost:8080/";
                            return;              
                        }
                        alert("게시글 삭제 실패!");
                    },
                    error: function(error) {
                        alert("게시물 삭제 에러! \n", error);
                    }
                });
            }
        }
    }

    function insertComment() {
        const cmtContent = $("#cmt_content").val();
        const postId = $("#pri_no").val();
        const name = $("#name").val(); 

        if(cmtContent === undefined || cmtContent === "") {
            alert("댓글을 작성해주세요!");
            return;
        } else {

            $.ajax({
                type: "POST",
                url: "http://localhost:8080/post/" + postId + "/comment/write", 
                data: {
                    "comment" : cmtContent,
                    "name" : name
                },
                success: function(response) {
                    const cmtList = $("#cmt_list");
                    // 태그 존재 여부 확인
                    if($("#no_cmt").length) {
                        $("#no_cmt").remove();
                    }
                    if(response["cmt_status"] !== true) {
                        alert("댓글 업데이트 에러! \n", response["cmt_status"]);
                        return;
                    }
                    const newComment = appendNewComment(response);

                    cmtList.append(newComment);

                },
                error: function(error) {
                    alert("댓글 작성 에러! \n", error);
                }
            })
        }
    }

});