$(document).ready(function() {
    
    $("#recom_up").click(function(e) {
        e.preventDefault();
        likePost();
    });

    $("#recom_down").click(function(e) {
        e.preventDefault();
        disLikePost();
    });

    $("#deletePost").click(function(e) {
        e.preventDefault();
        deletePost();
    });

    $("#cmt_btn").click(function(e) {
        e.preventDefault();
        insertComment();
    });

    $("#cmt_content").keydown(function(e) {
        if ((e.altKey || e.metaKey) && e.keyCode == 13) {
            e.preventDefault();
            insertComment();
        }
    });
});

function likePost() {

    const postId = $('#pri_no').val();
    const like = $("#like").text();
    const isRecommend = $("#recom_box").data("isrecommend");

    if(isRecommend) {
        if (!postId || !like) {
            alert("게시글 ID 또는 추천 정보를 가져오지 못했습니다.");
            return;
        }
    
        const param = new URLSearchParams({ id: postId, like: like});
        const url = 'http://localhost:8080/check/like?' + param.toString();
        $.ajax({
            type: 'GET',
            url: url,
            dataType: "json",
            success: function(response) {
    
                if(response.updateStatus) {
                    $("#like, #header-like").text(response.like);                    
                } else {
                    alert("추천 실패! 관리자에게 문의하세요.");
                }
            },
            error: function() {
    
                alert("추천 실패! 관리자에게 문의하세요.");
                
            }
        });
    } else {
        const confirmation = confirm("로그인이 필요합니다. \n로그인 하시겠습니까?");

        if(confirmation) {
            window.location.href = "http://localhost:8080/login";
        }
    }
}

function disLikePost() {
    const postId = $('#pri_no').val();
    const dislike = $("#dislike").text();
    const isRecommend = $("#recom_box").data("isrecommend");

    if(isRecommend) {
        if (!postId || !dislike) {
            alert("게시글 ID 또는 비추천 정보를 가져오지 못했습니다.");
            return;
        }
    
        const params = new URLSearchParams({ id: postId, dislike: dislike });
        const url = 'http://localhost:8080/check/dislike?' + params.toString();
    
        $.ajax({
            type: 'GET',
            url: url,
            dataType: "json",
            success: function(response) {
    
                if(response.updateStatus) {
                    $("#dislike").text(response.dislike);
                } else {
                    alert("추천 실패! 관리자에게 문의하세요.");
                }
            },
            error: function() {
    
                alert("추천 실패! 관리자에게 문의하세요.");
                
            }
        });
    } else {
        const confirmation = confirm("로그인이 필요합니다. \n로그인 하시겠습니까?");

        if(confirmation) {
            window.location.href = "http://localhost:8080/login";
        }
    }
}

function deletePost() {
    const confirmation = confirm("삭제하시겠습니까?");
    const postId = $('#pri_no').val();

    if(confirmation && postId) {
        $.ajax({
            type: 'POST',
            url: `http://localhost:8080/post/${postId}/delete`,
            contentType: "application/json",
            success: function(response) {
                if(response === true) {
                    window.location.href = "http://localhost:8080/";   
                }
                alert("게시글 삭제 실패!");
            },
            error: function(error) {
                alert("게시물 삭제 에러! \n", error);
            }
        });   
    }
}

function insertComment() {
    const cmtContent = $("#cmt_content").val();
    const postId = $("#pri_no").val();
    const name = $("#name").val(); 

    if(!cmtContent) {
        alert("댓글을 작성해주세요!");
        return;
    } 

    $.ajax({
        type: "POST",
        url: `http://localhost:8080/post/${postId}/comment/write`, 
        data: { "comment" : cmtContent, "name" : name },
        success: function(response) {
            const cmtList = $("#cmt_list");

            if($("#no_cmt").length) {
                $("#no_cmt").remove();
            }

            if(response.cmt_status !== true) {
                alert("댓글 업데이트 에러! \n", response.cmt_status);
                return;
            }

            // 새로운 comment html 태그 생성
            const newComment = appendNewComment(response);

            // 댓글 추가
            cmtList.append(newComment);

            // 댓글 작성 textarea 초기화
            $("#cmt_content").val('');

            // 전체 댓글 개수 update
            $("#cmt_count").html(response.cmt_status);

        },
        error: function(error) {
            alert("댓글 작성 에러! \n", error);
        }
    });
}

function deleteComment(element) {
    const confirmation = confirm("댓글을 삭제하시겠습니까?");
    const postId = $("#pri_no").val();
    const commentIdx = $(element).data("idx");
    const commentLi = $(element).closest('li');
    
    if (confirmation && postId && commentIdx) {
        $.ajax({
            url: "http://localhost:8080/post/" + postId + "/comment/delete",
            type: 'DELETE',
            data: {
                "cmt_no": commentIdx
            },
            success: function (response) {
                if (response.status) {
                    commentLi.remove();
                    $("#cmt_count").html(response.cmt_count);
                }
            },
            error: function (error) {
                alert("댓글 삭제 에러! \n" + error);
            }
        });   
    }
}


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
                            <span id="updateComment" class="comment-update sep_line">수정</span>
                            <span id="deleteComment" class="comment-delete sep_line cursor" onclick="deleteComment(this)" data-idx="${comment.cmt_no}">삭제</span>                                     
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
