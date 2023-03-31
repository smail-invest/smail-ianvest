package com.smile.invest.manager.dto;

import lombok.Data;

@Data
public class MemberDTO9 {

    /* 회원 아이디 */
    private String memberId;
//
//    /* 회원 비밀번호 */
//    private String userPassword;

    /* 회원 이름 */
    private String memberName;

    /* 회원 이메일 */
    private String memberEmail;

    /* 회원 휴대폰번호 */
    private String phone;

    /* 등록일 */
    private String enrollDate;

    /* 회원타입 */
    private String memberType;

    /* 탈퇴여부 */
    private String outYn;
}
