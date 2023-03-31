package com.smile.invest.allList.dto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemOption1DTO {



    private String optionNumber; // 옵션 번호

    private int price; // 가격

    private String projectNumber; // 프로젝트번호

    private String optionName; // 옵션명

    private String optionComponents; // 옵션내용
}