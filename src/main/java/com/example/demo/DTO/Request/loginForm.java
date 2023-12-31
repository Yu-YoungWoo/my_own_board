package com.example.demo.DTO.Request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginForm {

    @NotBlank(message = "아이디는 필수 입니다.")
    private String id;

    @NotBlank(message = "비밀번호는 필수 입니다.")
    private String pw;
}
