package com.example.demo.DTO.Response.post.ajax;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class InsertCommentRep {
    
    private int cmt_no;
    // 작성자
    private String cmt_name;
    // 댓글
    private String cmt_content;

    //생성 날짜
    private String create_date;

    // 댓글 개수
    private int cmt_count;

    // 댓글 생성 여부
    private boolean cmt_status;

    public InsertCommentRep() {
        this.cmt_status = false;
    }
}
