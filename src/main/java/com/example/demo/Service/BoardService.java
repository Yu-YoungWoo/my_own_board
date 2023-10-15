package com.example.demo.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.postModifyForm;
import com.example.demo.Mybatis.DAO.pagenation;
import com.example.demo.Mybatis.DAO.post;
import com.example.demo.Mybatis.mapper.BoardMapper;

@Service
public class BoardService {

    @Autowired private BoardMapper boardMapper;

     public List<post> findListWithPaging(pagenation pagenation) {

        List<post> posts =  boardMapper.findListWithPaging(pagenation);

        posts = this.formatCreate_date(posts);

        return posts;
    }

    public List<post> findPostWithSearchQuery(String query, String search_type) {
       
        List<post> search_post = boardMapper.findPostWithSearchQuery(query, search_type);

        search_post = this.formatCreate_date(search_post);

        return search_post;
    }

    public void insertPost(String title, String content, String name) {
        
        post newPost = new post();
        newPost.setTitle(title);
        newPost.setContent(content);
        newPost.setAuthor(name);

        boardMapper.insertPost(newPost);
    }

    public LinkedHashMap<String, Object> setPagenation(String page) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        pagenation pagenation = new pagenation(Integer.parseInt(page));

        /* 개시글 전체 개수  */
        int totalPosts = boardMapper.countTotalPost();

        /* 전체 페이지 개수 계산 */
        pagenation.setTotalPages(totalPosts);
        pagenation.setStartPage();
        pagenation.setEndPage();

        boolean prev = true;
        boolean next = true;

        if(pagenation.getStartPage() == '1') {
            prev = false;
        }

        if(pagenation.getEndPage() == pagenation.getTotalPage()) {
            next = false;
        }

        
        map.put("posts", this.findListWithPaging(pagenation));
        map.put("totalPage", pagenation.getTotalPage());
        map.put("startPage", pagenation.getStartPage());
        map.put("endPage", pagenation.getEndPage());
        map.put("prev", prev);
        map.put("next", next);

        return map;
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

    public LinkedHashMap<String, Object> findPostWithPostNum(String postNum) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();

        post findPost = boardMapper.findPostWithPostNum(postNum);

        findPost.setContent(findPost.getContent().replace("\r\n", "<br>"));

        map.put("post", findPost);

        return map;
    }

    /**
     * 해당 게시글을 작성한 작성자가 맞는지 체크하는 메소드
     * @param author - Post 테이블의 작성자 이름
     * @param id - User 테이블의 User Id
     * @param postNum - 해당 페이지의 번호
     * @return true OR false
     */

    public boolean countPostJoinUser(String author, String id, int postNum) {

        int selectRows = boardMapper.countPostJoinUser(author, id, postNum);

        if(selectRows > 0) {
            return true;
        }
        return false;
    }
    

    public boolean updatePostWithPostNum(postModifyForm form, String postNum) {
        ModelMapper modelMapper = new ModelMapper();

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String formattedDate = dateFormat.format(now);

        post findPost = boardMapper.findPostWithPostNum(postNum);


        modelMapper.map(form, findPost);
        findPost.setCreate_date(formattedDate);
        
        int updateRows = boardMapper.updatePostWithPostNum(findPost);

        if(updateRows == 0) {
            return false;
        }

        return true;
    }


    public boolean deletePostWithPostNum(String postNum) {
        int deleteRows = boardMapper.deletePostWithPostNum(Integer.parseInt(postNum));

        if(deleteRows == 0) {
            return false;
        }

        return true;
    }

}
