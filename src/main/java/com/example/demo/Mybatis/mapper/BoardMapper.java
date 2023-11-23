package com.example.demo.Mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.Mybatis.DAO.Post;

@Mapper
public interface BoardMapper {

    public int countTotalPost();

    // 검색 query + search_type
    public int countTotalPostWithSearch(String query, String search_type);

    public List<Post> findAll();

    // 페이징 처리
    public List<Post> findPostsWithPaging(int startPost);

    // 페이징 처리 + 검색 기능
    public List<Post> findPostWithPagingAndSearchType(int startPost, String query, String search_type);

    public Post findPostWithPostNum(String postNum);

    // 유저 이름, 페이지 번호로 게시글 찾기
    public int countPostJoinUser(String id, int pri_no);

    // 글 등록
    public void insertPost(Post post);

    // 글 업데이트
    public int updatePostWithPostNum(Post post);

    // 추천
    public int updatePostInLike(int pri_no);

    // 비추천
    public int updatePostInDisLike(int pri_no);

    // 조회수 증가
    public int updatePostView(int pri_no);
    
    // 글 삭제
    public int deletePostWithPostNum(int pri_no);
}
