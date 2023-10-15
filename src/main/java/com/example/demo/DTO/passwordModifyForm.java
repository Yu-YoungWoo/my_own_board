package com.example.demo.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class passwordModifyForm {

    
    @NotBlank(message = "현재 비밀번호는 공백일 수 없습니다.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$", message= "현재 비밀번호의 양식이 맞지 않습니다.")
    private String curPassword;

    @NotBlank(message =  "변경할 비밀번호는 필수 입니다.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$", message = "새로운 비밀번호의 조건이 맞지 않습니다.")
    private String newPassword;

    @NotBlank(message =  "비밀번호 확인을 위해 공백일 수 없습니다.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$", message = "새로운 비밀번호 확인의 조건이 맞지 않습니다.")
    private String newPasswordCheck;
    
}
