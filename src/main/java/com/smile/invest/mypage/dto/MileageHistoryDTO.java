package com.smile.invest.mypage.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MileageHistoryDTO {

    private String mileageHistoryNum;

    private String mileageUseYn;   // 마일리지 카테고리 4가지로 용도 변경 ( 충전, 주문, 환불, 환급)

    private Double mileageGiven;  // 해당 건당 실제 마일리지 내역

    private Double paymentAmount; // 실제 결제 한 금액 ( 현금 )


}
