$(document).ready(function() {
    
    $("#recom_up").click(function(e) {
        likePost();
    });

    $("#recom_down").click(function(e) {
        disLikePost();
    });

    $("#deletePost").click(function(e) {
        e.preventDefault();
        deletePost();
    });

    $("#cmtBtn").click(function(e) {
        insertComment();
    });

    $("#cmtTextArea").keydown(function(e) {
        if ((e.altKey || e.metaKey) && e.keyCode == 13) {
            insertComment();
        }
    });

    $("#replyBtn").click(function(e) {
        if ((e.altKey || e.metaKey) && e.keyCode == 13) {
            insertReply();
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
            },
            error: function(error) {
                alert("게시물 삭제 에러! \n", error);
            }
        });   
    }
}

function insertComment() {
    const cmtContent = $("#cmtTextArea").val();
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

            if($("#no_cmt").length) {
                $("#no_cmt").remove();
            }

            if(response.cmt_status !== true) {
                alert("댓글 업데이트 에러! \n", response.cmt_status);
                return;
            }
            location.reload();
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
    // const commentLi = $(element).closest('li');
    
    if (confirmation && postId && commentIdx) {
        $.ajax({
            url: "http://localhost:8080/post/" + postId + "/comment/delete",
            type: 'DELETE',
            data: {
                "cmt_no": commentIdx
            },
            success: function (response) {
                if (response.status) {
                    $("#cmt-" + commentIdx).remove();
                    $("#cmt_count").html(response.cmt_count);
                }
            },
            error: function (error) {
                alert("댓글 삭제 에러! \n" + error);
            }
        });   
    }
}

function insertReply() {
    const postId = $("#pri_no").val();
    const name = $("#name").val(); 
    const replyConent = $("#replyTextArea").val();


}

function replyComment(element) {
    const commentIdx = $(element).data("idx");
    const name = $(element).data("name");
    const loginStatus = $(element).data("status");
    const commentId = "cmt-" + commentIdx;
    const replyBox = getReplyBox(name, commentIdx);
 
    if(loginStatus === false) {
        const confirmation = confirm("로그인이 필요합니다.\n로그인 페이지로 이동하시겠습니까?");

        if(confirmation) {
            window.location.href = "/login";
        }
        return;
    }

    const nextComment = $("#" + commentId).next();
    const nextCommentId = nextComment.attr("id");
    
    if(nextCommentId === "cmtReply-write") {
        nextComment.remove();
    } else {
        $("#" + commentId).after(replyBox);
    }
}

function getReplyBox(userName, commentIdx) {
    const replyBox =
    `   
        <li id="cmtReply-write" class="reply_li underline">
            <div id="cmtWriteBox" class="d-flex flex-column bg-light rounded p-3 m-2 comment-wirtebox">
                <div class="mb-2">
                    <a href="/user-detail" class="link-secondary link-underline-opacity-0">
                        <span class="align-middle fs-2 me-2">
                            <i class="fa-regular fa-circle-user fa-lg"></i>
                        </span>
                        <span>${userName}</span>
                    </a>
                </div>
                <div class="mb-2">
                    <textarea id="replyTextArea" class="form-control textarea-noresize" placeholder="ALT+ENTER로 댓글을 등록할 수 있습니다."></textarea>
                </div>
                <div class="d-flex justify-content-end">
                    <button class="btn btn-dark submit-reply me-2">취소</button>
                    <button id="replyBtn" class="btn btn-secondary submit-reply" data-idx="${commentIdx}" data-name="${userName}">등록</button>
                </div>
            </div>
        </li>
    `;

    return replyBox;
}