package com.example.demo.Contoller.Ajax;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Mybatis.DAO.post;
import com.example.demo.Service.BoardService;
import com.example.demo.Service.UserService;

@Controller
public class AjaxController {

    @Autowired
    private UserService userService;
    @Autowired
    private BoardService boardService;

    LinkedHashMap<String, Object> map = new LinkedHashMap<>();

    @PostMapping("/check/idDupCheck")
    @ResponseBody
    public String POST_idDupCheck(@RequestParam(required = false) String id) {
        String checkDupId = "init";

        if(id != null) {
            checkDupId = userService.countUserById(id);
            
            return checkDupId;   
        }

        return checkDupId;
    }

    @PostMapping("/check/nameDupCheck")
    @ResponseBody
    public String POST_nameDupCheck(@RequestParam(required = false) String name) {
        String checkDupName = "init";

        if(name != null) {
            checkDupName = userService.countUserByName(name);
            
            System.out.println("checkDupName : " + checkDupName);
            return checkDupName;
        }

        return checkDupName;
    }


    @GetMapping("/search")
    @ResponseBody
    public LinkedHashMap<String, Object> GET_search(@RequestParam(required = false) String query, @RequestParam(required = false) String search_type, Model model) {

        List<post> findPost = boardService.findPostWithSearchQuery(query, search_type);

        map.put("search_posts", findPost);

        model.addAttribute("map", map);

        return map;
    }
    
}
