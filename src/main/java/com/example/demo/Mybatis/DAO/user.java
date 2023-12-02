package com.example.demo.Mybatis.DAO;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.demo.Mybatis.VO.UserRole;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class User implements Serializable {
    private int pri_no;
    private String id;
    private String pw;
    private String name;
    private String tel;
    private String email;
    private String create_date;
    private UserRole role;


    public void setCreateDate() {
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
