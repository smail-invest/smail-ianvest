package com.smile.invest.member.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class MemberRoleDTO {

    private String memberId;          // 회원아이디

    private int authorityCode;         // 권한코드
    private AuthorityDTO authority;    // 회원보유권한



}
