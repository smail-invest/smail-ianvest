package com.smile.invest.member.dto;


import com.smile.invest.mail.dto.EmailDTO;
import lombok.*;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {

    /* 회원 아이디 */
    private String userId;

    /* 회원 비밀번호 */
    private String userPassword;

    /* 회원 이름 */
    private String userName;

    /* 회원 이메일 */
    private String userEmail;

    /* 회원 휴대폰번호 */
    private String userPhone;

    /* 등록일 */
    private Date userEnrollDate;

    /* 회원타입 */
    private String userType;

    /* 탈퇴여부 */
    private String outYn;

    private List<MemberRoleDTO> userRoleList;  // 회원별권한리스트


}
