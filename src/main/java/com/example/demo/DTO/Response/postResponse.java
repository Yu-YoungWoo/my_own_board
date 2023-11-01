package com.example.demo.DTO.Response;

import java.util.List;

import com.example.demo.Mybatis.DAO.pagenation;
import com.example.demo.Mybatis.DAO.post;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class postResponse {
    private List<post> posts;
    private pagenation pagenation;

}
