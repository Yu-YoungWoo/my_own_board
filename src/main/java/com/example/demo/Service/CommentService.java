package com.example.demo.Service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public int insertComment(String content, String cmt_name) {
        comment newComment = new comment();

        content = content.replace("\r\n", "<br>");

        newComment.setCmt_name(cmt_name);
        newComment.setCmt_content(content);

        int insertRows = commentMapper.insertComment(newComment);

        return insertRows;
    }
    
}
