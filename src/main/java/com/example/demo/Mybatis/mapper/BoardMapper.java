package com.example.demo.Mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


import com.example.demo.Mybatis.DAO.post;

@Mapper
public interface BoardMapper {

    public int countTotalPost();

    public int countTotalPostWithSearch(String query, String search_type);

    public List<post> findAll();

    // 페이징 처리
    public List<post> findPostsWithPaging(int startPost);

    // 페이징 처리 + 검색 기능
    public List<post> findPostWithPagingAndSearchType(int startPost, String query, String search_type);

    public post findPostWithPostNum(String postNum);

    // 유저 이름, 페이지 번호로 게시글 찾기
    public int countPostJoinUser(String author, String id, int pri_no);

    // 글 등록
    public void insertPost(post post);

    // 글 업데이트
    public int updatePostWithPostNum(post post);

    // 추천
    public int updatePostInLike(int pri_no);

    // 비추천
    public int updatePostInDisLike(int pri_no);

    // 조회수 증가
    public int updatePostView(int pri_no);
    
    // 글 삭제
    public int deletePostWithPostNum(int pri_no);
}
