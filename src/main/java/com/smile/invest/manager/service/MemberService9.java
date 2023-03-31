package com.smile.invest.manager.service;

import com.smile.invest.manager.dao.MemberMapper9;
import com.smile.invest.manager.dto.MemberDTO9;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService9 {

    private final MemberMapper9 mapper;

    public MemberService9(MemberMapper9 mapper) {
        this.mapper = mapper;
    }

    public List<MemberDTO9> findAll() {

        List<MemberDTO9> memberList = mapper.findAll();

        return memberList;
    }
//
    public MemberDTO9 findById(String memberId) {

        MemberDTO9 memberdto = mapper.findById(memberId);

        return memberdto;
    }

    public void deleteMember(String memberId) {

        mapper.deleteMember(memberId);
    }


    public List<MemberDTO9> findBlack() {

        List<MemberDTO9> memberList = mapper.findBlack();

        return memberList;
    }

    public void recoveryMember(String memberId) {

        mapper.recoveryMember(memberId);

    }
}
