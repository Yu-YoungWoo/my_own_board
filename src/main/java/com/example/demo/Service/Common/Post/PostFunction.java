package com.example.demo.Service.Common.Post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.Mybatis.DAO.pagenation;
import com.example.demo.Mybatis.DAO.post;
import com.example.demo.Mybatis.mapper.BoardMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PostFunction {

    @Autowired
    private BoardMapper boardMapper;

    public pagenation rtnPagenation(String page) {
        
        pagenation pagenation = new pagenation(Integer.parseInt(page));


        /* 개시글 전체 개수  */
        int totalPosts = boardMapper.countTotalPost();

        /* 전체 페이지 개수 계산 */
        pagenation.setTotalPages(totalPosts);
        pagenation.setStartPage();
        pagenation.setEndPage();


        if(pagenation.getStartPage() == '1') {
            pagenation.setPrev(false);
        }

        if(pagenation.getEndPage() == pagenation.getTotalPage()) {
            pagenation.setNext(false);
        }

        log.info("pagenation : " + pagenation);

        return pagenation;
    }

    public pagenation rtnPagenation(String page, String query, String search_type) {
        pagenation pagenation = new pagenation(Integer.parseInt(page));

        int totalPosts = 0;

        if(query != null && !query.isEmpty()) {
            totalPosts = boardMapper.countTotalPostWithSearch(query, search_type);

            /* 전체 페이지 개수 계산 */
            pagenation.setTotalPages(totalPosts);
            pagenation.setStartPage(totalPosts);
            pagenation.setEndPage();

            log.info("rtnPagenation(String page, String query, String search_type) --------------");
            log.info("전체 페이지 : " + pagenation.getTotalPage());
            log.info("시작 페이지 : " + pagenation.getStartPage());
            log.info("끝 페이지 : " + pagenation.getEndPage());
        } else {
            totalPosts = boardMapper.countTotalPost();

            /* 전체 페이지 개수 계산 */
            pagenation.setTotalPages(totalPosts);
            pagenation.setStartPage();
            pagenation.setEndPage();
        }
        

        if(pagenation.getStartPage() == '1') {
            pagenation.setPrev(false);
        }

        if(pagenation.getEndPage() == pagenation.getTotalPage()) {
            pagenation.setNext(false);
        }

        return pagenation;
    }


    public List<post> formatCreate_date(List<post> posts) {
        posts.forEach(post -> {
            String[] split = post.getCreate_date().split(" ");
            
            String[] dateSplit = split[0].split("-");
            // String[] timeSplit = split[1].split(":");

            post.setCreate_date(dateSplit[1].toString() + "." + dateSplit[2].toString());
        });

        return posts;
    }
}
