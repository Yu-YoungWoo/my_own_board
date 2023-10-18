package com.example.demo.Contoller;

import java.util.LinkedHashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.DTO.postModifyForm;
import com.example.demo.Mybatis.DAO.post;
import com.example.demo.Mybatis.DAO.user;
import com.example.demo.Service.BoardService;
import com.example.demo.Service.UserService;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;




@Controller
public class BoardController {

    LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();

    @Autowired private BoardService boardService;
    @Autowired private UserService userService;
    
    @GetMapping("/")
    public String GET_board(@RequestParam(name = "page", defaultValue = "1") String page, Model model, Authentication auth) {

        map = boardService.setPagenation(page);

        model.addAttribute("posts", map.get("posts"));
        model.addAttribute("totalPage", (int) map.get("totalPage"));
        model.addAttribute("startPage", (int) map.get("startPage"));
        model.addAttribute("endPage", (int) map.get("endPage"));
        // model.addAttribute("prev", map.get("prev"));
        // model.addAttribute("next", map.get("next"));

        return "board";
    
    }

    @PostMapping("/")
    public String POST_board() {

        return "board";
    }

    @GetMapping("/post/{postNum}")
    public String GET_boardDetail(@PathVariable("postNum") String postNum, Model model, Authentication auth) {

        /* 글 정보 조회 */
        LinkedHashMap<String, Object> map =  boardService.findPostWithPostNum(postNum);

        user User = userService.returnUserByAuthentication();

        if(User == null) {
            return "redirect:/login";
        }
    
        post findPost = (post) map.get("post");

        boolean canEditOrDelete = boardService.countPostJoinUser(findPost.getAuthor(), User.getId(), Integer.parseInt(postNum));

        model.addAttribute("canEditOrDelete", canEditOrDelete);
        model.addAttribute("map", map);
    
        
        return "post/post-detail";
    }
    

    @GetMapping("/write")
    public String GET_write(Model model, Authentication auth) {
        
        user User = userService.returnUserByAuthentication();

        if(User == null) {
            return "redirect:/login";
        }

        model.addAttribute("user_name", userService.findUserById(User.getId()).getName());

        return "post/write";
    }

    @PostMapping("/write")
    public String POST_write(@RequestParam("title") String title, @RequestParam("content") String content, @RequestParam("name") String name) {
        
         boardService.insertPost(title, content, name);
        
        return "redirect:/";
    }

    @GetMapping("/post/{postNum}/modify")
    public String GET_modify(@PathVariable("postNum") String postNum, Model model) {

        LinkedHashMap<String, Object> map = boardService.findPostWithPostNum(postNum);

        model.addAttribute("map", map);

        return "post/post-modify";
    }


    @PostMapping("/post/{postNum}/modify")
    public String POST_modify(@Valid postModifyForm form, BindingResult bindingResult, @PathVariable("postNum") String postNum, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            return "redirect:/post/"+postNum+"/modify";
        }

        boolean updateStatus = boardService.updatePostWithPostNum(form, postNum);

        // 업데이트 실패
        if(!updateStatus) {
            return "redirect:/post/" +postNum+ "modify";
        }
        
        return "redirect:/post/" + postNum;
    }

    @PostMapping("/post/{postNum}/delete")
    public String POST_delete(@PathVariable("postNum") String postNum, RedirectAttributes redirectAttributes) {

        System.out.println("postNum : " + postNum);
        boolean deleteStatus = boardService.deletePostWithPostNum(postNum);
        
        // 삭제 실패
        if(!deleteStatus) {
            return "redirect:/post/" +postNum+ "delete";
        }

        return "redirect:/";
    }
    
}
