package com.smile.invest.allList.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PermissionStatusHistoryDTO {

    private Date permissionDate;

    private String projectStatusNumber;

    private String projectNumber;
}
