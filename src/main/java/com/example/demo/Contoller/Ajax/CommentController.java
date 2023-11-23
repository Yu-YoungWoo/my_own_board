package com.example.demo.Contoller.Ajax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.DTO.Response.post.ajax.DeleteCommentRep;
import com.example.demo.DTO.Response.post.ajax.InsertCommentRep;
import com.example.demo.Service.CommentService;

@Controller
@RequestMapping("/post/{postNum}")
public class CommentController {

    @Autowired private CommentService commentService;

    @PostMapping("/comment/write")
    @ResponseBody
    public InsertCommentRep POST_comment(
        @PathVariable("postNum") int priNo, 
        @RequestParam(value="comment") String comment, 
        @RequestParam(value="name") String cmtName
    ) {
        
        InsertCommentRep rep = commentService.insertComment(comment, cmtName, priNo);

        return rep;
    }


    @DeleteMapping("/comment/delete")
    @ResponseBody
    public DeleteCommentRep DELETE_comment(
        @PathVariable("postNum") int priNo,
        @RequestParam(value = "cmt_no") String cmtNo
    ) {
        DeleteCommentRep rep = commentService.deleteComment(priNo, cmtNo);
        return rep;
    }

    
}
