package com.smile.invest.member.controller;


import com.smile.invest.common.exception.member.MemberRegistException;
import com.smile.invest.mail.dto.EmailDTO;
import com.smile.invest.mail.service.EmailService;
import com.smile.invest.member.dto.MemberDTO;
import com.smile.invest.member.service.LoginService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;


@Controller
@RequestMapping("/member")
public class LoginController {

    private final LoginService loginService;

    private final PasswordEncoder passwordEncoder;

    private final EmailService emailService;

    public LoginController(PasswordEncoder passwordEncoder, LoginService loginService, EmailService emailService) {

        this.passwordEncoder = passwordEncoder;
        this.loginService = loginService;
        this.emailService = emailService;
    }


    @GetMapping("/login")
    public String memberLoginForm() {

        return "content/member/login";
    }

    //아이디 찾기 폼 불러오기
    @GetMapping("/find_id")
    public String findIdForm() {

        return "content/member/find_id";
    }

    //아이디 찾는 사이트
    @GetMapping("/find_id_print")
    public String findId(String name, String email, Model model) throws Exception {

        String id = loginService.findId(name, email);

        model.addAttribute("userId", id);

        return "content/member/find_id_print";
    }


    //회원가입 폼 불러오기
    @GetMapping("/sign_up_form")
    public String goSignUp(String email) {
        return "content/member/sign_up_form";
    }

    // 사용자가 입력한 회원정보 비번 암호화, 전화번호 형식 변경하여 회원가입
    @PostMapping("/sign_up_form")
    public String signUp(@ModelAttribute MemberDTO member, RedirectAttributes rttr) throws MemberRegistException {

        member.setUserPassword(passwordEncoder.encode(member.getUserPassword()));

        System.out.println("member : " + member);
        loginService.registMember(member);

        String message = "스마일 인베스트에 가입하신걸 환영합니다~!!";
        rttr.addFlashAttribute("message", message);

        return "redirect:/";
    }

//     아이디 중복확인
    @PostMapping(  "/idCheck")
    @ResponseBody
    public int checkDuplication(@RequestParam("userId") String userId){

      int result = loginService.checkId(userId);

        return result;
    }

    /* 회원가입 - 이메일 인증 보내기 */
    @PostMapping("/email_certification")
    @ResponseBody
    public String emailCertification(@ModelAttribute EmailDTO email) throws MessagingException, UnsupportedEncodingException {

        emailService.checkEmail(email);
        System.out.println(email);

        return "redirect:/";
    }

    /* 이메일 인증 확인 */
    @GetMapping("/registerEmail")
    public String emailConfirm(HttpServletRequest request) throws MemberRegistException {

        String email = request.getParameter("email");
        String mailKey = request.getParameter("mail_key");

        emailService.updateMailAuth(email, mailKey);
        System.out.println("이메일 인증 확인합니다.");
        System.out.println(email);
        System.out.println(mailKey);

        return "/content/member/emailAuthsuccess";
    }
}
