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
                    <strong class="me-auto">계정 삭제 실패</strong>
                    <small>${formatTimeDifference(timeDiff)}</small>
                    <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
                </div>
                <div class="toast-body">
                    계정 삭제 실패! 관리자에게 문의하세요.
                </div>
            </div>
        `;
    
        // 동적으로 toast를 추가합니다.
        $("#popup").append(toastHTML);
    
        // toast를 표시합니다.
        $("#UserDeleteFailToast").toast('show');
    }
    

    function deleteUser() {
        const id = $("#curId").val();
        const currentTime = new Date(); 

        $.ajax({
            type: 'POST',
            data: {id : id},
            url: "http://localhost:8080/deleteUser",
            success: function() {
                alert("계정 삭제가 완료되었습니다.");
                window.location.href = "/login";
            },
            error: function() {
                const errorTime = new Date();

                const timeDiff = Math.floor((currentTime - errorTime) / 1000);
                
                displayUserDeleteFailToast(timeDiff);
                return;
            }
        });
    }

    $("#user-delete").click(function() {
        const deleteConfirm = confirm("삭제하시겠습니까?");
        
        if(deleteConfirm) {
            deleteUser();
        } else {
            return;
        }
    });


    // 클릭 이벤트 핸들러 밖으로 Ajax 요청 코드를 이동합니다.
    function checkDupId() {
        const id = $("#id").val().trim();
        let pattern = /^[a-zA-Z0-9]{4,20}$/
        
        
        if(id != "") {
            const isvalid = pattern.test(id);
            
            if(isvalid) {
                $.ajax({
                    type: 'POST',
                    data: {id : id},
                    url: "http://localhost:8080/check/idDupCheck",
                    success: function(result) {
                        
                        if (result === "true") {
                            $("#IdNotDupAlert").show(); // 중복되지 않은 아이디
                            $("#IdDupAlert").hide();     // 중복된 아이디 숨기기
                        } else {
                            $("#IdNotDupAlert").hide(); // 중복되지 않은 아이디 숨기기
                            $("#IdDupAlert").show();     // 중복된 아이디 표시
                        }
                    }
                });
            }
        }
    }

    // 클릭 이벤트 핸들러에서 함수를 호출하도록 합니다.
    $("#checkDupId").click(checkDupId);

    $("[id='close']").click(function() {
        console.log("닫기 버튼 클릭 : ");
        $("#IdNotDupAlert").hide();
        $("#IdDupAlert").hide();
    });

    $(function() {

        // 아이디
        $("#IdNotDupAlert").hide();
        $("#IdDupAlert").hide();
        $("#invalidIdMsg").hide();

        // 닉네임
        $("#invalidNameEmptyMsg").hide();
        $("#invalidNameLengthMsg").hide();

        // 전화번호
        $("#invalidTelMsg").hide();

        // 이메일
        $("#invalidEmailMsg").hide();
    });

    function toggleInvalidMsg(inputId, show) {
        if (show) {
            $(inputId).show(); // 메시지를 보이게 함
        } else {
            $(inputId).hide(); // 메시지를 숨김
        }
    }

    // 입력값을 검증하는 함수
    function validateInput(input, options) {
        const value = input.value;
        const isValid = options.pattern.test(value);

        // 유효성 검증 결과에 따라 스타일을 변경하거나 사용자에게 피드백 제공
        if (isValid) {
            input.classList.add("is-valid");
            input.classList.remove("is-invalid"); // 유효한 입력
            toggleInvalidMsg(options.invalidId, false); // 메시지를 숨김
            toggleInvalidMsg(options.altInvalidId, false); // 메시지를 숨김
        } else {
            input.classList.remove("is-valid");
            input.classList.add("is-invalid"); // 유효하지 않은 입력
            toggleInvalidMsg(options.invalidId, true); // 메시지를 표시
            toggleInvalidMsg(options.altInvalidId, false); // 다른 메시지를 숨김
        }
    }
    
     // 4~20자 영문,숫자 조합만 허용
     document.getElementById("id").addEventListener('input', function() {
        const options = {
            pattern: /^[a-zA-Z0-9]{4,20}$/,
            invalidId: "#invalidIdMsg"
        };
    
        validateInput(this, options);
    });
    
    // 2자 이상 16자 이하, 영어 또는 숫자 또는 한글로 구성
    document.getElementById('name').addEventListener('input', function() {
        const name = $("#name").val();
        let options = {};
        
        if(name.length === 0) {
            options = {
                pattern: /^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,16}$/,
                invalidId: "#invalidNameEmptyMsg",
                altInvalidId : "#invalidNameLengthMsg"
            };
        } else if (name.length < 2 || name.length > 16) {
            options = {
                pattern: /^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,16}$/,
                invalidId: "#invalidNameLengthMsg",
                altInvalidId : "#invalidNameEmptyMsg"
            };
        } else {
            options = {
                pattern: /^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,16}$/,
                invalidId: "#invalidNameLengthMsg",
                altInvalidId : "#invalidNameEmptyMsg"
            };
        }
        validateInput(this, options);
    });

    // 전화번호는 '-'를 제외한 숫자만 허용
    document.getElementById('tel').addEventListener('input', function() {
        const options = {
            pattern: /^[0-9-]+$/,
            invalidId: "#invalidTelMsg"
        };
        validateInput(this, options);
    });

    // 이메일은 example@example.com 형식만 허용
    document.getElementById('email').addEventListener('input', function() {

        const options = {
            pattern: /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i,
            invalidId: "#invalidEmailMsg"
        };
        validateInput(this, options);
    });
});