package com.example.demo.Contoller.Ajax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.DTO.Response.post.ajax.commentRep;
import com.example.demo.Service.CommentService;

@Controller
@RequestMapping("/post/{postNum}")
public class CommentController {

    @Autowired private CommentService commentService;

    @PostMapping("/comment/write")
    @ResponseBody
    public commentRep POST_comment(@PathVariable("postNum") int pri_no, 
                             @RequestParam(value="comment", required=false) String comment, 
                             @RequestParam(value="name", required = false) String cmt_name) {


        System.out.println("comment : " + comment);
        System.out.println("name : " + cmt_name);
        commentRep rep = commentService.insertComment(comment, cmt_name, pri_no);

        return rep;
    }

    
}
