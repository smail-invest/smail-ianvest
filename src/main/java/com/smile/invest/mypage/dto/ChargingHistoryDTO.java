package com.smile.invest.mypage.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChargingHistoryDTO {

    private String mileageHistoryNum;

    private String userId;

    private String  feeNumber;
    private Date chargeDate;


}
