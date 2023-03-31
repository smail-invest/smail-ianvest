package com.smile.invest.mail.service;

import com.smile.invest.common.exception.member.MemberRegistException;
import com.smile.invest.mail.MailHandler;
import com.smile.invest.mail.TempKey;
import com.smile.invest.mail.dao.EmailDAO;
import com.smile.invest.mail.dto.EmailDTO;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Service
public class EmailService {

    private final EmailDAO mapper;

    private final JavaMailSender mailSender;

    public EmailService(EmailDAO mapper, JavaMailSender mailSender) {
        this.mapper = mapper;
        this.mailSender = mailSender;
    }

    public void updateMailAuth(String email, String mailKey) throws MemberRegistException {

        mapper.updateMailAuth(email, mailKey);
    }

    public void checkEmail(EmailDTO email) throws MessagingException, UnsupportedEncodingException {

        String mailKey = new TempKey().getKey(30,false); //랜덤키 길이 설정
        email.setMailKey(mailKey);

        //이메일 인증키 생성
        mapper.insertMailKey(email);

        //회원가입 완료하면 인증을 위한 이메일 발송
        MailHandler sendMail = new MailHandler(mailSender);
        sendMail.setSubject("[SMILE INVEST 인증메일 입니다.]"); //메일제목
        sendMail.setText(
                "<h1>SMILE INVEST 메일인증</h1>" +
                        "<br>SMILE INVEST에 오신것을 환영합니다!" +
                        "<div>" +
                        " <span>아래</span>" +
                        "<span style=\"color: #F75D59; font-weight: bold;\">[이메일 인증 확인]</span>" +
                        "<span>을 눌러주세요.</span>" +
                        "</div>" +
                        "<br><a href='http://localhost:8889/member/registerEmail?email=" + email.getUserEmail() +
                        "&mail_key=" + mailKey +
                        "' target='_blank'>이메일 인증 확인</a>");
        sendMail.setFrom("codigtest0429@gmail.com", "SMILE INVEST");
        sendMail.setTo(email.getUserEmail());
        sendMail.send();
    }
}
