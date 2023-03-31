package com.smile.invest.mypage.dto;


import lombok.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MileageDTO {

    /* 마일리지 번호*/
    private String mileageNumber;

    /*  총 마일리지 */
    private Double totalMileage;

    /* 회원 아이디 */
    private UserDTO userId;

//    public MileageDTO(Double realPoint) {
//
//    }
}
