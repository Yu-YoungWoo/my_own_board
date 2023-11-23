package com.example.demo.Contoller;

import java.util.LinkedHashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.DTO.Request.BasicInfoModifyForm;
import com.example.demo.DTO.Request.JoinForm;
import com.example.demo.DTO.Request.LoginForm;
import com.example.demo.DTO.Request.PasswordModifyForm;
import com.example.demo.Mybatis.DAO.User;
import com.example.demo.Service.UserService;

import lombok.extern.log4j.Log4j2;


@Controller
@Log4j2
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String GET_login(@RequestParam(value = "error", required = false) String error, 
                @RequestParam(value = "exception", required = false) String exception, Model model) {
        
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);

        return "login";
    }

    @PostMapping("/login")
    public String POST_login(LoginForm form, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("loginFailed", true);

        return "redirect:/login";
        
    }

    @GetMapping("/logout")
    public String GET_logout() {

        return "login";
    }
    
    @GetMapping("/join")
    public String GET_join(Model model) {

        return "join";
    }

    @PostMapping("/join")
    public String POST_join(@Valid JoinForm form, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

        LinkedHashMap<String, Object> map = new LinkedHashMap<>();

        if(bindingResult.hasErrors()) {
            map = userService.createFailMsg(bindingResult, "", "join");
            redirectAttributes.addFlashAttribute("map", map);
            
            return "redirect:/join";
        }

        map = userService.createUser(form);

        /* 회원가입 성공 */ 
        if((boolean) map.getOrDefault("loginSuccessStatus", false)) {
            redirectAttributes.addFlashAttribute((boolean) map.getOrDefault("loginSuccessStatus", true));
            return "redirect:/login";
        } 

        redirectAttributes.addFlashAttribute("map", map);
        return "redirect:/join";
    }


    @GetMapping("/user-detail")
    public String GET_userDetail(Model model, Authentication auth) {

        boolean authStatus = userService.validAuthUser();        
        // 유저 권한이 없으면 로그인 페이지로 리다이렉트
        if(!authStatus) {
            return "redirect:/login";
        }

        User findUser = userService.findUserById(auth.getName());

        model.addAttribute("isAuthenticated", authStatus);
        model.addAttribute("user", findUser);
        
        return "user/user_detail";

    }
 
    @PostMapping("/deleteUser")
    public String POST_deleteUser(@RequestParam(required = true) String id, RedirectAttributes redirectAttributes) {
        String deleteURL = userService.deleteUser(id);

        if(deleteURL.equals("redirect:/user-detail")) {
            redirectAttributes.addFlashAttribute("userDeleteFailStatus", true);
            redirectAttributes.addFlashAttribute("userDeleteFailMsg", "계정 삭제 실패");
        }

        return deleteURL;
    }

    /**
     * 기본 정보를 수정하는 POST
     * @param form - 기본정보(아이디, 닉네임, 전화번호, 이메일)의 수정된 정보들
     * @return - 결과에 따른 페이지 String 값 
     */
    @PostMapping("/basic-info-modify")
    public String POST_userModify(@Valid BasicInfoModifyForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model, Authentication auth) {

        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        
        boolean authStatus = userService.validAuthUser();
        
        // 유저 권한이 없으면 로그인 페이지로 리다이렉트
        if(!authStatus) {
            return "redirect:/login";
        }

        log.info("form Email : " + form.getEmail());

        User findUser = userService.findUserById(auth.getName());

        if(bindingResult.hasErrors()) {
            System.out.println("bindingResult : " + bindingResult);
            map = userService.createFailMsg(bindingResult, findUser.getId(),"BASIC-INFO-MODIFY");
            redirectAttributes.addFlashAttribute("map", map);
            
            return "redirect:/user-detail";
        }

        map = userService.updateBasicInfo(form, findUser.getId());

        if(map.containsKey("updateUserFailStatus")) {
            redirectAttributes.addFlashAttribute("map", map);
            return "redirect:/user-detail";
        }
        
        return "redirect:/user-detail";
    }

    @GetMapping("/user-password-modify")
    public String GET_userPasswordModify(Model model, Authentication auth) {

        boolean authStatus = userService.validAuthUser();
        
        // 유저 권한이 없으면 로그인 페이지로 리다이렉트
        if(!authStatus) {
            return "redirect:/login";
        }
    
        model.addAttribute("isAuthenticated", authStatus);
        return "user/user_password_modify";
    }

    @PostMapping("/user-password-modify")
    public String POST_userPasswordModify(@Valid PasswordModifyForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes, Authentication auth) {
        LinkedHashMap<String, Object> passwordModifyMap = new LinkedHashMap<>();
        boolean authStatus = userService.validAuthUser();
        
        // 유저 권한이 없으면 로그인 페이지로 리다이렉트
        if(!authStatus) {
            return "redirect:/login";
        }

        User findUser = userService.findUserById(auth.getName());

        if(bindingResult.hasErrors()) {
            String fieldErrorName = "";
            String fieldErrorMsg = "";
           
            for(FieldError error : bindingResult.getFieldErrors()) {
                fieldErrorName = error.getField();
                fieldErrorMsg = error.getDefaultMessage();

                if(fieldErrorName.equals("newPasswordCheck")) {
                    break;
                }
                passwordModifyMap = userService.createFailMsg(bindingResult, findUser.getId(), "USER-PASSWORD-MODIFY");
                passwordModifyMap.put("passWordUpdateFailMsg", fieldErrorMsg);
            }


            redirectAttributes.addFlashAttribute("map", passwordModifyMap);
            return "redirect:/user-password-modify";
        }

         passwordModifyMap = userService.updatePassword(form, findUser.getId());

         if(passwordModifyMap.containsKey("updatePasswordFailStatus")) {
            redirectAttributes.addFlashAttribute("map", passwordModifyMap);

            return "redirect:/user-password-modify";
         }

        return "redirect:/user-detail";
    }
}
