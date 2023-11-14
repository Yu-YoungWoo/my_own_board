package com.example.demo.Mybatis.DAO;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

@Data
public class comment {

    private int cmt_no;
    // 작성자
    private String cmt_name;
    // 댓글
    private String cmt_content;
    // 해당 게시글 번호
    private int post_pri_no;
    // 생성 날짜
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
