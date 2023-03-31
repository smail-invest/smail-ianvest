package com.smile.invest.mypage.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FeeDTO {

    private String feeNumber;

    private String feeName;

    private double fee;
}
