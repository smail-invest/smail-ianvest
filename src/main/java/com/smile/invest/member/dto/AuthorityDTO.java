package com.smile.invest.member.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthorityDTO {

    private int code;                                         // 권한코드
    private String name;                                      // 권한명
    private String desc;                                      // 권한 설명

//    private List<AuthenticatedMenuDTO> authenticatedMenuList; // 권한별 인가된 메뉴 목록 .. 1대 다..


}
