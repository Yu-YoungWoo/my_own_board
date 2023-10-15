package com.example.demo.Mybatis.DAO;

import lombok.Data;

@Data
public class pagenation {

    private static final int PAGE_SIZE = 10; // 한 페이지에 보여줄 개수
    private static final int DISPLAY_PAGENUM = 5; // 한번에 표시할 페이지 개수
    
    private int startPost; // 보여줄 게시글의 시작지점
    private int currentPage; // 현재 페이지
    private int totalPage; // 전체 페이지 개수
    private int endPage; // 마지막 페이지
    private int startPage; // 시작 페이지
    private int currentBlock;

    public pagenation(int currentPage) {
        this.startPost = (currentPage - 1) * PAGE_SIZE;
        this.currentPage = currentPage;
        this.currentPage = currentPage;
        this.currentBlock = currentPage % DISPLAY_PAGENUM == 0 ? currentPage / DISPLAY_PAGENUM : (currentPage / DISPLAY_PAGENUM) + 1;
    }


    public void setTotalPages(int totalPosts) {
        try {
            this.totalPage = totalPosts % PAGE_SIZE == 0 ? totalPosts / PAGE_SIZE : (totalPosts / PAGE_SIZE) + 1;
        } catch(NumberFormatException e) {
            this.totalPage = 1;
        }
    }

    public void setStartPage() {

        try {
            this.startPage = (currentBlock - 1) * DISPLAY_PAGENUM + 1;
        } catch(NumberFormatException e) {
            this.startPage = 1;
        }
        
    }

    public void setEndPage() {
        try {
             this.endPage = this.startPage + DISPLAY_PAGENUM - 1;
            
            if(endPage > totalPage) {
                endPage = totalPage;
            }
        } catch(NumberFormatException e) {
            this.endPage = 1;
        }
    }
}
