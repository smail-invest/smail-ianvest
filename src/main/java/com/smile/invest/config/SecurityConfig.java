package com.smile.invest.config;


import com.smile.invest.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Properties;

@EnableWebSecurity
public class SecurityConfig  {

    private MemberService memberService;

    @Autowired
    public SecurityConfig(MemberService memberService){

        this.memberService = memberService;
    }

    // 비밀번호 암호화에 사용할 객체 : BCryptPasswordEncoder bean 등록
    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer configure(){

        return (web) -> web.ignoring().antMatchers("/css/**", "/js/**", "/images/**", "/lib/**");
    }

    // HTTP 요청에 대한 권한 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


        // csrf: 토큰 위주의 공격을 막기 위한 작업 ( default가 'on'인 상태)
        return http.csrf().disable()
                .authorizeRequests()   // 요청에 대한 권한 체크를 어떻게 할건지
//                .antMatchers("/board/**").authenticated()  // /menu/**에 대해서는 하나하나 권한을 등록하겠다.
//                .antMatchers(HttpMethod.GET, "/board/**").hasRole("USER") // 디비에서 헤즈롤메소드를 통해 롤언더바 붙어있는지 확인
                //hasRole은 ROLE_를 달아주며 ROLE_MEBER와 같은지
//                .antMatchers(HttpMethod.POST, "/board/**").hasRole("ADMIN")
//                .antMatchers("/user/**").hasRole("USER")
//                .antMatchers("/manager/**").hasRole("ADMIN")
                .anyRequest().permitAll() // 등록되지 않은 경우로는 누구나 접근 가능
                .and()
                .formLogin() // 로그인 form을 따로 이용해 로그인 처리할 것이다.
                .loginPage("/member/login") // login page로 해당 로그인페이지에서 submit 요청하는 경로로 지정하겠다는 의미
                .successForwardUrl("/")     // 성공 시 페이지 설정
                .and()
                .logout() // 로그아웃 설정
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout")) // 로그아웃시 요청 경로
                .deleteCookies("JSESSIONID")   // 쿠키 제거
                .invalidateHttpSession(true)                     // session 정보 무효화
                .and()
                .exceptionHandling()                               // 인가/인증 exception 핸들러 설정
                .accessDeniedPage("/common/denied")   // 인가되지 않았을 때 - 권한이 없는 기능을 요청했을 때
                // 호출될 페이지
                .and()
                .build();




        // 권한이 필요한 창 -> manager , board , mypage

    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {

        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(memberService)   // 사용자정보 가져오고
                .passwordEncoder(passwordEncoder())
                .and().build();
    }


//    @Bean
//    public JavaMailSender javaMailSender() {
//
//        Properties mailProperties = new Properties();
//        mailProperties.put("mail.transport.protocol", "smtp");
//        mailProperties.put("mail.smtp.auth", "true");
//        mailProperties.put("mail.smtp.starttls.enable", "true");
//        mailProperties.put("mail.smtp.debug", "true");
//        mailProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
//        mailProperties.put("mail.smtp.ssl.protocols", "TLSv1.2");
//
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setJavaMailProperties(mailProperties);
//        mailSender.setHost("smtp.gmail.com");
//        mailSender.setPort(587);
//        mailSender.setUsername("condigtest0429@gmail.com");
//        mailSender.setPassword("vemkyaghmtyivodq");
//        mailSender.setDefaultEncoding("utf-8");
//        return mailSender;
//    }
}
