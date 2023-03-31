package com.smile.invest.member.service;

import com.smile.invest.mail.dao.EmailDAO;
import com.smile.invest.mail.dto.EmailDTO;
import com.smile.invest.member.dao.MemberDAO;
import com.smile.invest.member.dto.MemberDTO;
import com.smile.invest.common.exception.member.MemberRegistException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.smile.invest.mail.TempKey;
import com.smile.invest.mail.MailHandler;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;


@Service
public class LoginService {

    private final MemberDAO memberDAO;

    private final EmailDAO emailDAO;

    private final JavaMailSender mailSender;

    public LoginService(MemberDAO memberDAO, EmailDAO emailDAO, JavaMailSender mailSender) {
        this.memberDAO = memberDAO;
        this.emailDAO = emailDAO;
        this.mailSender = mailSender;
    }

    public String findId(String name, String email) {

        return memberDAO.findId(name, email);
    }


    @Transactional
    public void registMember(MemberDTO member) throws MemberRegistException {

        int result1 = memberDAO.insertMember(member);
        memberDAO.insertAuth(member.getUserId());

        if(!(result1 > 0)) {

            throw new MemberRegistException();
        }
    }

    public int checkId(String userId){

        int result = memberDAO.checkId(userId);

        return result;
    }
}





