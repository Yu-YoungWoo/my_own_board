package com.example.demo.Service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.Response.post.ajax.DeleteCommentRep;
import com.example.demo.DTO.Response.post.ajax.InsertCommentRep;
import com.example.demo.Mybatis.DAO.Comment;
import com.example.demo.Mybatis.mapper.BoardMapper;
import com.example.demo.Mybatis.mapper.CommentMapper;

@Service
public class CommentService {
    
    @Autowired private CommentMapper commentMapper;
    @Autowired private BoardMapper boardMapper;

    private static final int CMT_ADD = 1;
    private static final int CMT_DEL = -1;
    
    public int countCommentBypostId(int post_pri_no) {
        int cmtCount = commentMapper.countCommentBypostId(post_pri_no);
        return cmtCount;
    }

    public List<Comment> findAllOrderCommentBypostId(int post_pri_no) {
        List<Comment> findRows = commentMapper.findAllOrderCommentBypostId(post_pri_no);

        return findRows;
    }

    public InsertCommentRep insertComment(String comment, String cmt_name, int pri_no) {
        Comment newComment = new Comment();
        InsertCommentRep newCommentRep = new InsertCommentRep();

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

        // 댓글 개수 조회
        int cmtCount = commentMapper.countCommentBypostId(pri_no);

        boardMapper.increaseCmtCount(pri_no);

        newCommentRep.setCmt_count(cmtCount);

        return newCommentRep;
    }

    public DeleteCommentRep deleteComment(int pri_no, String cmt_no) {
        DeleteCommentRep rep = new DeleteCommentRep();
        int cmtNo = Integer.parseInt(cmt_no);
        int deleteRows = commentMapper.deleteComment(pri_no, cmtNo);

        if(deleteRows > 0) {
            int cmtCount = commentMapper.countCommentBypostId(pri_no);
            boardMapper.decreaseCmtCount(pri_no);

            rep.setCmt_count(cmtCount);
            rep.setStatus(true);
            
            return rep;
        }
        rep.setStatus(false);
        
        return rep;
    }
    
}
