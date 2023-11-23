package com.example.demo.DTO.Response.post.ajax;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DeleteCommentRep {

    private boolean status;

    private int cmt_count;


    public DeleteCommentRep() {
        this.status = false;
    }
    
}
