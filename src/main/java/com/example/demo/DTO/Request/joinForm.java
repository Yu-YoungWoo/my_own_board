package com.example.demo.DTO.Request;


import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class joinForm {
        
    @NotBlank(message = "4~20자 / 영문, 숫자만 허용합니다.")
    private String id;

    @NotBlank(message = "8~16자리/영문 대소문자,숫자,특수문자 조합만 허용합니다.")
    private String pw;

    @NotBlank(message = "공백은 허용하지 않습니다.")
    private String name;

    @NotBlank(message = "'-'를 제외한 숫자만 입력해야 합니다.")
    private String tel;

    @NotBlank(message = "이메일은 example@example.com 같이 형식에 맞게 입력해야 합니다.")
    private String email;

    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
    private String create_date;
}
