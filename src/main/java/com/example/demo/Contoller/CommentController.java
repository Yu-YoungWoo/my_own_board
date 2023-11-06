package com.example.demo.Contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Service.CommentService;

@Controller
@RequestMapping("/post/{postNum}")
public class CommentController {

    @Autowired private CommentService commentService;

    @PostMapping("/comment/write")
    public String POST_comment(@PathVariable("postNum") int pri_no, 
                             @RequestParam String content, 
                             @RequestParam("name") String cmt_name) {

        commentService.insertComment(content, cmt_name);

        return "redirect:post/" + Integer.toString(pri_no);
    }

    
}
