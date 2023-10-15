package com.example.demo.Mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.Mybatis.DAO.pagenation;
import com.example.demo.Mybatis.DAO.post;

@Mapper
public interface BoardMapper {
    public int countTotalPost();

    public List<post> findAll();

    // 페이징 처리
    public List<post> findListWithPaging(pagenation pagenation);

    // 글 검색
    public List<post> findPostWithSearchQuery(String query, String search_type);

    public post findPostWithPostNum(String postNum);

    // 유저 이름, 페이지 번호로 게시글 찾기
    public int countPostJoinUser(String author, String id, int pri_no);

    // 글 등록
    public void insertPost(post post);

    // 글 업데이트
    public int updatePostWithPostNum(post post);

    // 글 삭제
    public int deletePostWithPostNum(int postNum);
}
