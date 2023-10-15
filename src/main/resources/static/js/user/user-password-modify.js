$(document).ready(function() {

    $('#submit-btn').click(function(event) {
        const submit = confirm("수정하시겠습니까?");

        if(submit) {
            event.preventDefault();
            
            const newPassWord = $("#new-password").val();
            const newPassWordCheck = $("#new-password-check").val();

            if(newPassWord !== "" || newPassWordCheck !== "") {
                console.log("newPass : " + newPassWord);
                console.log("newPassWordCheck : " + newPassWordCheck);

                if(newPassWord !== newPassWordCheck) {
                    
                    alert("새 비밀번호와 새 비밀번호 확인이 일치하지 않습니다.");
                    // window.location.href = "/user-password-modify"; // 리다이렉트할 URL로 변경
                    return; // 중지
                }
                $("#userModifyForm").submit();
            } else {
                alert("비밀번호는 공백일 수 없습니다.");
                // window.location.href = "/user-password-modify"; // 리다이렉트할 URL로 변경
                return;
            }
        } else {
            return;
        }
    });

    $(function() {
        $('#alert-success').hide();
        $('#alert-danger').hide();

        $("#new-password-check").keyup(function() {
            const newPassWord = $('#new-password').val();
            const newPassWordCheck = $('#new-password-check').val();

            if(newPassWord != "" || newPassWordCheck != "") {
                if(newPassWord == newPassWordCheck) {
                    $('#alert-success').show();
                    $('#alert-danger').hide();
                } else {
                    $('#alert-success').hide();
                    $('#alert-danger').show();                    
                }
            }
        });
    });

    function toggleInvalidMsg(inputId, show) {
        if (show) {
            $(inputId).show(); // 메시지를 보이게 함
        } else {
            $(inputId).hide(); // 메시지를 숨김
        }
    }


    // 입력값을 검증하는 함수
    function validateInput(input, pattern, invalidId) {
        const value = input.value;
        const isValid = pattern.test(value);

        // 유효성 검증 결과에 따라 스타일을 변경하거나 사용자에게 피드백 제공
        if (isValid) {
            input.classList.add("is-valid");
            input.classList.remove("is-invalid"); // 유효한 입력
            toggleInvalidMsg(invalidId, false); // 메시지를 숨김
        } else {
            input.classList.remove("is-valid");
            input.classList.add("is-invalid"); // 유효하지 않은 입력
            toggleInvalidMsg(invalidId, true); // 메시지를 표시
        }
    }

    // 8~16자리 영문 대소문자,숫자,특수문자만 허용
    document.getElementById("cur-password").addEventListener('input', function() {
        const pattern = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$/;
        validateInput(this, pattern, "#invalidCurPasswordMsg");
    });

    // 8~16자리 영문 대소문자,숫자,특수문자만 허용
    document.getElementById("new-password").addEventListener('input', function() {
        const pattern = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$/;
        validateInput(this, pattern, "#invalidNewPasswordMsg");
    });

    // 8~16자리 영문 대소문자,숫자,특수문자만 허용
    document.getElementById("new-password-check").addEventListener('input', function() {
        const pattern = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$/;
        validateInput(this, pattern, "#invalidNewPasswordCheckMsg");
    });
});
