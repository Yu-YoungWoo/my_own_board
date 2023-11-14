$(document).ready(function() {

    // $('#modify-btn').click(function() {
    //     const confirmation = confirm("수정하시겠습니까?");
    //     const postNum = $('#pri_no').val();

    //     if(confirmation) {
    //         const form = $('#modify-post')[0];
    //         console.log(postNum)
    //         const action = form.action;
    //         console.log("action : " + action);
    //         const formData = new FormData(form);

    //         $.ajax({
    //             type: 'POST',
    //             url: "http://localhost:8080/post/" + postNum + "/modify",
    //             data: formData,
    //             processData: false,
    //             contentType: false,
    //             success: function(response) {
    //                 if(!formData.get("title")) {
    //                     alert("제목이 비어있습니다.");
    //                 } else {
    //                     window.location.href = "http://localhost:8080/post" + "/".concat(postNum);
    //                 }
    //             },
    //             error: function(error) {
    //                 alert("게시물 수정 에러! \n", error);
    //             }
    //         });
    //     }
    // });
});