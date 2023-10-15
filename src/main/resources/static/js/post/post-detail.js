$(document).ready(function() {
    
});

function deletePost() {
    $('#postDelete').click(function(e) {
        e.preventDefault(); 

        const confirmation = confirm("삭제하시겠습니까?");

        if(confirmation) {
            const postNum = $('#pri_no').val();
            console.log("postNum : " + postNum);

            $.ajax({
                type: 'POST',
                url: 'http://localhost:8080/post/' + postNum + "/delete",
                processData: false,
                contentType: false,
                success: function(response) {
                    window.location.href = "http://localhost:8080/";                
                },
                error: function(error) {
                    alert("게시물 삭제 에러! \n", error);
                }
            });
        }
    });
}