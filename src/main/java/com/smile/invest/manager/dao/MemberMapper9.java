package com.smile.invest.manager.dao;

import com.smile.invest.manager.dto.MemberDTO9;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper9 {


    List<MemberDTO9> findAll();

    MemberDTO9 findById(String memberId);

    void deleteMember(String memberId);


    List<MemberDTO9> findBlack();

    void recoveryMember(String memberId);
}
