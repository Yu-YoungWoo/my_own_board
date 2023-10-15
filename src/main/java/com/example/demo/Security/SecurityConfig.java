package com.example.demo.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.example.demo.Mybatis.VO.UserRole;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig{

    private final AuthenticationFailureHandler loginFailHandler;
    

    // @Bean
    // public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    //     return authenticationConfiguration.getAuthenticationManager();
    // }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/js/**", "/css/**", "/img/**", "/favicon.ico");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .csrf(csrf -> csrf.disable())
        .cors(cors -> cors.disable())
        .headers(headers -> headers.cacheControl(cache -> cache.disable()))
        .authorizeRequests(authorizeRequests ->
                authorizeRequests
                        .antMatchers("/join", "/login", "/help/**", "/idDupCheck").permitAll()
                        .antMatchers("/admin", "/admin/**").hasAuthority(UserRole.ADMIN.name())
                        // .anyRequest().authenticated()
                        .anyRequest().authenticated()
        )
        .formLogin(formLogin ->
                {
                    try {
                        formLogin
                                .usernameParameter("id")
                                .passwordParameter("pw")
                                .loginPage("/login")                                
                                .failureHandler(loginFailHandler)
                                // .defaultSuccessUrl("/")
                                .and()
                                .logout(logout -> logout
                                        .logoutUrl("/logout")
                                        .invalidateHttpSession(true).deleteCookies("JSESSIONID"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
