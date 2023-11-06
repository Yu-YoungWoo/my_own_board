package com.example.demo.Mybatis.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.Mybatis.DAO.comment;

@Mapper
public interface CommentMapper {

    // 게시글 댓글 count 
    public int countCommentBypostId(int post_pri_id);


    // 게시글 댓글 SELECT
    public List<comment> findAllCommentBypostId(int post_pri_no);

    public int insertComment(comment comment);

    
}