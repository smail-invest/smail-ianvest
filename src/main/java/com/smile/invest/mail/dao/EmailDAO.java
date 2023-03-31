package com.smile.invest.mail.dao;

import com.smile.invest.mail.dto.EmailDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmailDAO {

    int updateMailAuth(String email, String mailKey);

    int insertMailKey(EmailDTO email);

//    int emailAuthFail(String userId);
}
