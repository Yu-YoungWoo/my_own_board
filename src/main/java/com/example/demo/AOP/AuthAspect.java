package com.example.demo.AOP;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.demo.AOP.Exceptions.AuthenticationException;

// /**
//  * 적용 안됨
//  */
// @Aspect
// @Component
// public class AuthAspect {

//     @Before(
//             "execution(* com.example.demo.Controller.*.*(..)) || " +
//             "!execution(* com.example.demo.Controller.UserContoller.GET_login(..)) || " +
//             "!execution(* com.example.demo.Controller.UserContoller.POST_login(..)) || " +
//             "!execution(* com.example.demo.Controller.UserContoller.GET_join(..)) || " +
//             "!execution(* com.example.demo.Controller.UserContoller.POST_join(..)) || " +
//             "!execution(* com.example.demo.Controller.UserContoller.GET_logout(..))"
//             )
//     public void checkAuthentication() {
//         Authentication auth = SecurityContextHolder.getContext().getAuthentication();

//         if(auth == null || !auth.isAuthenticated()) {
//             throw new AuthenticationException("인증되지 않은 사용자입니다.");
//         }
//     }
// }
