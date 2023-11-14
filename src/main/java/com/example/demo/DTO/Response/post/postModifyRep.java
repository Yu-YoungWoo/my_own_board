package com.example.demo.DTO.Response.post;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class postModifyRep {
    // author
    @NotBlank(message = "유저 정보는 비어있을 수 없습니다.")
    private String name;
    
    @NotBlank(message = "제목은 비어있을 수 없습니다.")
    private String title;

    @NotNull(message = "게시글은 NULL을 허용하지 않습니다.")
    private String content;
}
