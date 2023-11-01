package com.example.demo.DTO.Request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class basicInfoModifyForm {

    @NotBlank(message = "아이디는 필수입니다.")
    @Pattern(regexp = "[a-zA-Z0-9]{4,20}", message = "올바른 아이디 형식을 입력해주세요.")
    private String id;

    @NotBlank(message = "닉네임은 필수입니다.")
    @Pattern(regexp = "[a-z0-9가-힣]{2,16}", message = "올바른 닉네임 형식을 입력해주세요.")
    private String name;

    @NotBlank(message = "전화번호는 필수입니다.")
    @Pattern(regexp = "[0-9-]+", message = "올바른 전화번호 형식을 입력해주세요.")
    private String tel;

    @NotBlank(message = "이메일은 필수입니다.")
    @Pattern(regexp = "[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}", message = "올바른 이메일 형식을 입력해주세요.")
    private String email;
}
