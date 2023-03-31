package com.smile.invest.mail.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmailDTO {

    private String userEmail;

    private String mailKey;

    private int mailAuth;

    private String userId;
}
