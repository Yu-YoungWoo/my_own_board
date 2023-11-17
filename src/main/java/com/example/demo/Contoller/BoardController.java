package com.example.demo.Contoller;

import java.util.LinkedHashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.DTO.Response.post.postModifyRep;
import com.example.demo.Mybatis.DAO.comment;
import com.example.demo.Mybatis.DAO.user;
import com.example.demo.Service.BoardService;
import com.example.demo.Service.CommentService;
import com.example.demo.Service.UserService;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;




@Controller
public class BoardController {

    @Autowired private BoardService boardService;
    @Autowired private UserService userService;
    @Autowired private CommentService commentService;
    
    
    @GetMapping("/")
    public String GET_board( @RequestParam(name = "page", required = false) String page, Model model, Authentication auth) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();

        // SecurityContextHolder의 유저 권한을 통해 로그인 확인
        model.addAttribute("isAuthenticated", userService.validAuthUser());
        

        if(page == null) {
            map = boardService.findPostsWithPaging("1");
        } else {
            map = boardService.findPostsWithPaging(page);
        }
        
        model.addAttribute("map", map);

        return "board";
    }

    @PostMapping("/")
    public String POST_board() {

        return "board";
    }

    @GetMapping("/post/{postNum}")
    public String GET_boardDetail(@PathVariable("postNum") String pri_no, Model model, Authentication auth) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        String userName = "";
        String id = "";
        boolean userAuth = userService.validAuthUser();

        // SecurityContextHolder의 유저 권한을 통해 로그인 확인
        model.addAttribute("isAuthenticated", userAuth);
    
        // 유저 정보 조회
        if(userAuth) {
            user findUser = userService.findUserById(auth.getName());
            userName = findUser.getName();
            id = auth.getName();
        }

        /* 글 정보 조회 */
        map =  boardService.findPostWithPostNum(pri_no);
        boolean canEditOrDelete = false;

        /* 댓글 정보 조회 */
        List<comment> comments = commentService.findAllCommentBypostId(Integer.parseInt(pri_no));
        map.put("comments", comments);
        
        /* 댓글 개수 count */
        map.put("cmtCount", Integer.valueOf(comments.size()));
        
        // user id 정보 조회 (글 수정, 삭제 가능 여부 판단)
        canEditOrDelete = boardService.countPostJoinUser(id , Integer.parseInt(pri_no));
        
        // 유저 닉네임 추가 (댓글)
        map.put("name", userName);
        /* 
         * 조회 수 증가 시 동시성 문제 있음 
         * 어떻게 해결 할지 고민해봐야 할 문제...
         * DB LOCK을 걸건지.. 트렌젝션 제어를 할지.. Java synchronize로 단일 실행을 보장할지..
         */
        boardService.updatePostView(pri_no);

        model.addAttribute("canEditOrDelete", canEditOrDelete);
        model.addAttribute("map", map);

        return "post/post-detail";
    }
    

    @GetMapping("/write")
    public String GET_write(Model model, Authentication auth) {
        
        // SecurityContextHolder의 유저 권한을 통해 로그인 확인
        model.addAttribute("isAuthenticated", userService.validAuthUser());

        boolean authStatus = userService.validAuthUser();
        
        // 유저 권한이 없으면 로그인 페이지로 리다이렉트
        if(!authStatus) {
            return "redirect:/login";
        }

        model.addAttribute("user_name", userService.findUserById(auth.getName()).getName());

        return "post/write";
    }

    @PostMapping("/write")
    public String POST_write(@RequestParam("title") String title, @RequestParam("content") String content, @RequestParam("name") String name) {
        
        boardService.insertPost(title, content, name);
        
        return "redirect:/";
    }

    @GetMapping("/post/{postNum}/modify")
    public String GET_modify(@PathVariable("postNum") String postNum, Model model, Authentication auth) {

        // SecurityContextHolder의 유저 권한을 통해 로그인 확인
        model.addAttribute("isAuthenticated", userService.validAuthUser());

        boolean authStatus = userService.validAuthUser();
        
        // 유저 권한이 없으면 로그인 페이지로 리다이렉트
        if(!authStatus) {
            return "redirect:/login";
        }

        LinkedHashMap<String, Object> map = boardService.findPostWithPostNum(postNum);

        model.addAttribute("map", map);

        return "post/post-modify";
    }


    @PostMapping("/post/{postNum}/modify")
    public String POST_modify(@Valid postModifyRep form, BindingResult bindingResult, @PathVariable("postNum") String postNum, RedirectAttributes redirectAttributes) {

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
}
