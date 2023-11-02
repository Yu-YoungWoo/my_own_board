package com.example.demo.Mybatis.DAO;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

@Data
public class comment {
    private int cmt_no;
    private int post_pri_no;
    private String cmt_content;
    private String author;
    private String create_date;


    public comment() {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String formattedDate = dateFormat.format(now);

        try {
            this.create_date = formattedDate;
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}