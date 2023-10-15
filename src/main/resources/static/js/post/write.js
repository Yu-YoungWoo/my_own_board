$(document).ready(function() {
    $('#confirm-btn').click(function() {
        const confirmation = confirm("등록하시겠습니까?");
        
        if(confirmation) {
            
            const form = $('#post')[0];
            
            const action = form.action;
            console.log("action : " + action);
            const formData = new FormData(form);
            

            $.ajax({
                type: 'POST',
                // 요청 보낼 url
                url: action,
                data: formData,              
                processData: false,
                contentType: false,
                success: function(response) {
                    if(!formData.get("title") || !formData.get("content")) {
                        alert("제목, 글을 입력해 주세요.");
                    } else {
                        // '/'로 리다이렉트
                        window.location.href = '/';
                    }
                },
                error: function(error) {
                    alert("게시물 등록 에러! \n", error);
                }
            });
        }
    });
})