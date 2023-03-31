package com.smile.invest.member.dao;

import com.smile.invest.mail.dto.EmailDTO;
import com.smile.invest.member.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface MemberDAO {

    MemberDTO findMemberById(String memberId);

    String findId(String name, String email);

    int insertMember(MemberDTO member);

    int checkId(String userId);

    int insertAuth(String userId);
}
