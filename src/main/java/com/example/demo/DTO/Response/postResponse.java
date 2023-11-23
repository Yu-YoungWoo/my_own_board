package com.example.demo.DTO.Response;

import java.util.List;

import com.example.demo.Mybatis.DAO.Pagenation;
import com.example.demo.Mybatis.DAO.Post;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostResponse {
    private List<Post> posts;
    private Pagenation pagenation;

}
