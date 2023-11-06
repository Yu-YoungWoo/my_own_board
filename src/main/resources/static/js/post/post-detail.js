$(document).ready(function() {

    function formatTimeDifference(timeDiff) {
        const timeUnits = [
            { unit: "일", divisor: 24 * 60 * 60 * 1000 },
            { unit: "시간", divisor: 60 * 60 * 1000 },
            { unit: "분", divisor: 60 * 1000 },
            { unit: "초", divisor: 1000 },
        ];
    
        for(const unitData of timeUnits) {
            const unitDiff = Math.floor(timeDiff / unitData.divisor);
    
            if(unitDiff >= 1) {
                return unitDiff + unitData.unit + " 전";
            }
        }
        return "방금 전";
    }

    function displayUserDeleteFailToast(timeDiff) {
        const toastHTML = `
            <div id="UserDeleteFailToast" class="toast hide fr" role="alert" aria-live="assertive" aria-atomic="true">
                <div class="toast-header">
                    <svg class="bi flex-shrink-0 me-2 align-middle ms-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
                    <strong class="me-auto">좋아요 등록 실패</strong>
                    <small>${formatTimeDifference(timeDiff)}</small>
                    <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
                </div>
                <div class="toast-body">
                    좋아요 등록 실패! 관리자에게 문의하세요.
                </div>
            </div>
        `;
    
        return toastHTML;
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
                    url: 'http://localhost:8080/post/' + postId + "/delete",
                    contentType: 'application/json',
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
        console.log("comment : " + cmtContent);
    }

});