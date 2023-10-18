$(document).ready(function() {

    // 클릭 이벤트 핸들러 밖으로 Ajax 요청 코드를 이동합니다.
    function checkDupId() {
        const id = $("#id").val().trim();
        
        if(id != "") {
            $.ajax({
                type: 'POST',
                data: {id : id},
                dataType: "text",
                url: "http://localhost:8080/check/idDupCheck",
                success: function(result) {
                    if (result === "true") {
                        $("#IdNotDupAlert").show(); // 중복되지 않은 아이디
                        $("#IdDupAlert").hide();     // 중복된 아이디 숨기기
                    } else if(result == "false") {
                        $("#IdNotDupAlert").hide(); // 중복되지 않은 아이디 숨기기
                        $("#IdDupAlert").show();     // 중복된 아이디 표시
                    } else {
                        $("#IdNotDupAlert").hide(); 
                        $("#IdDupAlert").hide();     
                    }
                }
            });
        }
    }

    function checkDupName() {
        const name = $("#name").val().trim();

        console.log("name : " + name);
        if(name != "") {
            $.ajax({
                type: 'POST',
                data: {name : name},
                dataType: "text",
                url: "http://localhost:8080/check/nameDupCheck",
                success: function(result) {
                    console.log("result : " + result);
                    if(result == "true") {
                        $("#NameNotDupAlert").show();
                        $("#NameDupAlert").hide();
                    } else if(result == "false"){
                        $("#NameNotDupAlert").hide();
                        $("#NameDupAlert").show();
                    } else {
                        $("#NameNotDupAlert").hide();
                        $("#NameDupAlert").hide();
                    }
                },
                error: function(error) {

                }
            });
        }
    }

    // 클릭 이벤트 핸들러에서 함수를 호출하도록 합니다.
    $("#checkDupId").click(checkDupId);
    $("#checkDupName").click(checkDupName);


    $("#id-not-dup-close").click(function() {
        $("#IdNotDupAlert").hide();
    });

    $("#id-dup-close").click(function() {
        $("#IdDupAlert").hide();
    });

    $("#name-not-dup-close").click(function() {
        $("#NameNotDupAlert").hide();
    });

    $("#name-dup-close").click(function() {
        $("#NameDupAlert").hide();
    })

    $(function() {
        $("#IdNotDupAlert").hide();
        $("#IdDupAlert").hide();
        $("#invalidIdMsg").hide();
        $("#invalidPasswordMsg").hide();
        $("#invalidNameMsg").hide();
        $("#invalidTelMsg").hide();
        $("#invalidEmailMsg").hide();
        $("#NameNotDupAlert").hide();
        $("#NameDupAlert").hide();
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

    // 4~20자 영문,숫자 조합만 허용
    document.getElementById("id").addEventListener('input', function() {
        const pattern = /^[a-zA-Z0-9]{4,20}$/;

        validateInput(this, pattern, "#invalidIdMsg");
    });

    // 8~16자리 영문 대소문자,숫자,특수문자만 허용
    document.getElementById("password").addEventListener('input', function() {
        const pattern = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$/;
        validateInput(this, pattern, "#invalidPasswordMsg");
    });

    // 2자 이상 16자 이하, 영어 또는 숫자 또는 한글로 구성
    document.getElementById('name').addEventListener('input', function() {
        const pattern = /^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,16}$/;
        validateInput(this, pattern, "#invalidNameMsg");
    });

    // 전화번호는 '-'를 제외한 숫자만 허용
    document.getElementById('tel').addEventListener('input', function() {
        const pattern = /^[0-9-]+$/;
        validateInput(this, pattern, "#invalidTelMsg");
    });

    // 이메일은 example@example.com 형식만 허용
    document.getElementById('email').addEventListener('input', function() {
        const pattern = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i;
        validateInput(this, pattern, "#invalidEmailMsg");
    });
});
