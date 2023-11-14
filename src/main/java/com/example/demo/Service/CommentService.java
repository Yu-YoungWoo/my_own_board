package com.example.demo.Service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.Response.post.ajax.commentRep;
import com.example.demo.Mybatis.DAO.comment;
import com.example.demo.Mybatis.mapper.CommentMapper;

@Service
public class CommentService {
    
    @Autowired private CommentMapper commentMapper;


    public int countCommentBypostId(int post_pri_no) {
        int cmtCount = commentMapper.countCommentBypostId(post_pri_no);
        return cmtCount;
    }

    public List<comment> findAllCommentBypostId(int post_pri_no) {
        List<comment> findRows = commentMapper.findAllCommentBypostId(post_pri_no);

        return findRows;
    }

    public commentRep insertComment(String comment, String cmt_name, int pri_no) {
        comment newComment = new comment();
        commentRep newCommentRep = new commentRep();

        comment = comment.replace("\r\n", "<br>");

        newComment.setCmt_name(cmt_name);
        newComment.setCmt_content(comment);
        newComment.setPost_pri_no(pri_no);

        int insertRows = commentMapper.insertComment(newComment);

        if(insertRows == 0) {
            newCommentRep.setCmt_status(false);
        } else {
            newCommentRep.setCmt_name(cmt_name);
            newCommentRep.setCmt_content(comment);
            newCommentRep.setCreate_date(newComment.getCreate_date());
            newCommentRep.setCmt_status(true);
        }

        return newCommentRep;
    }
    
}
