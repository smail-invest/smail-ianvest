package com.smile.invest.projectCreate.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemOptionDTO {

    private String optionNumber;
    private int price;
    private String projectNum;
    private String optionName;
    private String optionComponents;
}
