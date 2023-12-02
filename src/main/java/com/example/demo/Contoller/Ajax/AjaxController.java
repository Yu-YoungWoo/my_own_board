package com.example.demo.Contoller.Ajax;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.DTO.Response.post.ajax.PostDisLikeRep;
import com.example.demo.DTO.Response.post.ajax.PostLikeRep;
import com.example.demo.Service.BoardService;
import com.example.demo.Service.UserService;

@Controller
public class AjaxController {

    @Autowired
    private UserService userService;
    @Autowired
    private BoardService boardService;

    LinkedHashMap<String, Object> map = new LinkedHashMap<>();

    @GetMapping("/check/idDupCheck")
    @ResponseBody
    public String POST_idDupCheck(@RequestParam(value = "id") String pri_no) {
        String checkDupId = "init";

        if(pri_no != null) {
            checkDupId = userService.countUserById(pri_no);
            
            return checkDupId;   
        }

        return checkDupId;
    }

    @GetMapping("/check/nameDupCheck")
    @ResponseBody
    public String POST_nameDupCheck(@RequestParam(value = "name") String name) {
        String checkDupName = "init";

        if(name != null) {
            checkDupName = userService.countUserByName(name);
            
            return checkDupName;
        }

        return checkDupName;
    }

    @GetMapping("/check/like")
    @ResponseBody
    public PostLikeRep GET_like(@RequestParam("id") String pri_no, @RequestParam String like) {

        return boardService.updatePostInLike(pri_no, like);
    }

    @GetMapping("/check/dislike")
    @ResponseBody
    public PostDisLikeRep GET_dislike(@RequestParam("id") String pri_no, @RequestParam String dislike) {
        return boardService.updatePostInDisLike(pri_no, dislike);
    }

    @GetMapping("/search")
    public String GET_search(@RequestParam(required = false) String query, 
                             @RequestParam(required = false) String search_type,
                             @RequestParam(defaultValue = "1", required = false) String page,
                             Model model) {


        if(page == null) {
            map = boardService.findPostWithPagingAndSearchType("1", query, search_type);   
        } else {
            map = boardService.findPostWithPagingAndSearchType(page, query, search_type);
        }

        model.addAttribute("map", map);
        model.addAttribute("displayPagingNumber", true);
        
    
        return "board";
    }
}
