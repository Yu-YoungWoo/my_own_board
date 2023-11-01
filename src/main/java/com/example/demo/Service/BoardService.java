package com.example.demo.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.Request.postModifyForm;
import com.example.demo.DTO.Response.post.ajax.postDisLikeRep;
import com.example.demo.DTO.Response.post.ajax.postLikeRep;
import com.example.demo.Mybatis.DAO.pagenation;
import com.example.demo.Mybatis.DAO.post;
import com.example.demo.Mybatis.mapper.BoardMapper;
import com.example.demo.Service.Common.Post.PostFunction;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardService {

    @Autowired private BoardMapper boardMapper;
    @Autowired private PostFunction postFunction;

    LinkedHashMap<String, Object> map = null;

    /**
     * 게시판 초기 상태의 페이징 설정을 위한 메소드
     * @param page - 현제 페이지
     * @return LinkedHashMap<String, Object> -- (DTO로 수정 필요)
     */
     public LinkedHashMap<String, Object> findPostsWithPaging(String page) {

        map = new LinkedHashMap<String, Object>();

        log.info("pagenation 객체-----------------------------");
        pagenation pagenation = postFunction.rtnPagenation(page);

        log.info("게시글 찾는 페이징-----------------------------");

        List<post> posts =  boardMapper.findPostsWithPaging(pagenation.getStartPost());

        posts = postFunction.formatCreate_date(posts);

        map.put("posts", posts);
        map.put("totalPage", pagenation.getTotalPage());
        map.put("startPage", pagenation.getStartPage());
        map.put("endPage", pagenation.getEndPage());
        map.put("prev", pagenation.getPrev());
        map.put("next", pagenation.getNext());
        map.put("page", pagenation.getCurrentPage());
        
        return map;
    }

    public LinkedHashMap<String, Object> findPostWithPagingAndSearchType(String page, String query, String search_type) {
        map = new LinkedHashMap<String, Object>();

        log.info("검색 쿼리 + pagenation 객체---------------");
        pagenation pagenation = postFunction.rtnPagenation(page, query, search_type);

        log.info("검색 쿼리 + 게시글 찾는 페이징-----------------------------");
        log.info("pagenation.getStartPost() : " + pagenation.getStartPost());
        List<post> posts = boardMapper.findPostWithPagingAndSearchType(pagenation.getStartPost(), query, search_type);

        posts = postFunction.formatCreate_date(posts);

        log.info("findPostWithPagingAndSearchType --------------------------------");
        log.info(" pagenation.getStartPage() : " +  pagenation.getStartPage());
        log.info(" pagenation.getEndPage() : " +  pagenation.getEndPage());
        log.info(" pagenation.getCurrentPage() : " +  pagenation.getCurrentPage());

        map.put("posts", posts);
        map.put("query", query);
        map.put("search_type", search_type);
        map.put("totalPage", pagenation.getTotalPage());
        map.put("startPage", pagenation.getStartPage());
        map.put("endPage", pagenation.getEndPage());
        map.put("prev", pagenation.getPrev());
        map.put("next", pagenation.getNext());
        map.put("page", pagenation.getCurrentPage());
        

        return map;
    }

    public LinkedHashMap<String, Object> findPostWithPostNum(String postNum) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();

        post findPost = boardMapper.findPostWithPostNum(postNum);

        findPost.setContent(findPost.getContent().replace("\r\n", "<br>"));

        map.put("post", findPost);

        return map;
    }


    /* ----------------- INSERT ----------------- */
    
    public void insertPost(String title, String content, String name) {
        
        post newPost = new post();
        newPost.setTitle(title);
        newPost.setContent(content);
        newPost.setAuthor(name);

        boardMapper.insertPost(newPost);
    }


    /* ----------------- UPDATE ----------------- */
    
    public boolean updatePostWithPostNum(postModifyForm form, String postNum) {
        ModelMapper modelMapper = new ModelMapper();

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String formattedDate = dateFormat.format(now);

        post findPost = boardMapper.findPostWithPostNum(postNum);


        modelMapper.map(form, findPost);
        findPost.setCreate_date(formattedDate);
        
        int updateRows = boardMapper.updatePostWithPostNum(findPost);

        if(updateRows == 0) {
            return false;
        }

        return true;
    }


    public postLikeRep updatePostInLike(String pri_no, String like) {
        postLikeRep rep = new postLikeRep();
        int updateRows = boardMapper.updatePostInLike(Integer.parseInt(pri_no));

        if(updateRows == 0) {
            rep.setUpdateStatus(false);
            rep.setLike(Integer.parseInt(like));
        } else {
            rep.setUpdateStatus(true);
            rep.setLike(Integer.parseInt(like) + 1);
        }
        return rep;
    }

    public postDisLikeRep updatePostInDisLike(String pri_no, String dislike) {
        postDisLikeRep rep = new postDisLikeRep();

        int updateRows = boardMapper.updatePostInDisLike(Integer.parseInt(pri_no));

        if(updateRows == 0) {
            rep.setUpdateStatus(false);
            rep.setDislike(Integer.parseInt(dislike));
        } else {
            rep.setUpdateStatus(true);
            rep.setDislike(Integer.parseInt(dislike) + 1);
        }

        return rep;
    }

    public void updatePostView(String id) {
        boardMapper.updatePostView(Integer.parseInt(id));
    }

    /* ----------------- DELETE ----------------- */

    public boolean deletePostWithPostNum(String postNum) {
        int deleteRows = boardMapper.deletePostWithPostNum(Integer.parseInt(postNum));

        if(deleteRows == 0) {
            return false;
        }

        return true;
    }


    /**
     * 해당 게시글을 작성한 작성자가 맞는지 체크하는 메소드
     * @param author - Post 테이블의 작성자 이름
     * @param id - User 테이블의 User Id
     * @param postNum - 해당 페이지의 번호
     * @return true OR false
     */

    public boolean countPostJoinUser(String author, String id, int postNum) {

        int selectRows = boardMapper.countPostJoinUser(author, id, postNum);

        if(selectRows > 0) {
            return true;
        }
        return false;
    }

}
