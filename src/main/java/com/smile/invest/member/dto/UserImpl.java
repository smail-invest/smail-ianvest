package com.smile.invest.member.dto;

import com.smile.invest.mail.dto.EmailDTO;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@ToString
public class UserImpl extends User {

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

    public UserImpl(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public void setDetails(MemberDTO user) {

        this.userId = user.getUserId();
        this.userPassword = user.getUserPassword();
        this.userName = user.getUserName();
        this.userEmail = user.getUserEmail();
        this.userPhone = user.getUserPhone();
        this.userEnrollDate = user.getUserEnrollDate();
        this.userType = user.getUserType();
        this.outYn = user.getOutYn();
        this.userRoleList = user.getUserRoleList();
    }


}
