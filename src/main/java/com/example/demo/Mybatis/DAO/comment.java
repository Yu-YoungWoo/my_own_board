package com.example.demo.Mybatis.DAO;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

@Data
public class Comment {

    private int cmt_no;
    // 작성자
    private String cmt_name;
    // 댓글
    private String cmt_content;
    // 해당 게시글 번호
    private int post_pri_no;
    // 생성 날짜
    private String create_date;

    private int depth;

    private int cmt_group;

    private String reply_yn;

    public Comment() {
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
