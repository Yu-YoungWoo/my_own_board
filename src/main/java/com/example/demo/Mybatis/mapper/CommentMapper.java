package com.example.demo.Mybatis.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.Mybatis.DAO.Comment;

@Mapper
public interface CommentMapper {

    // 게시글 댓글 count 
    public int countCommentBypostId(int post_pri_id);

    // 게시글 댓글 SELECT
    public List<Comment> findAllCommentBypostId(int post_pri_no);

    // 대댓글과 함께 order by한 댓글 SELECT
    public List<Comment> findAllOrderCommentBypostId(int post_pri_no);

    public int insertComment(Comment comment);

    public int deleteComment(int post_pri_no, int cmt_no);
}