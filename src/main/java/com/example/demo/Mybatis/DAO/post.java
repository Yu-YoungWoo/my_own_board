package com.example.demo.Mybatis.DAO;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

@Data
public class Post {
    private int pri_no;
    private String title;
    private String content;
    private String author;
    private Long views;
    private int likes;
    private int dislikes;
    private String create_date;

    public Post() {

        this.views = 0L;
        this.likes = 0;
        this.dislikes = 0;

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String formattedDate = dateFormat.format(now);

        try {
            this.create_date = formattedDate;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
