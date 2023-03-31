package com.smile.invest.allList.dto;

import com.smile.invest.projectCreate.dto.ItemOptionDTO;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Project1DTO {

    private String projectNumber;

    private ProjectCategoryDTO categoryCode;

    private String title;

    private String writerIntroduction;

    private String shortIntroduction;

    private String longIntroduction;

    private Long targetMileage;

    private Date startDate;

    private Date endDate;

    private Date settlementDate;

    private String expectedUse;

    private String businessLicenseNumi;

    private String expectedDifficulty;

    private String additionalPolicy;

    private String projectDeleteYn;

    private String projectSchedule;

    private List<PermissionStatusHistoryDTO> permissionStatusHistoryList;   // 1:N 조인

    private List<ProjectFileDTO> projectFileList;

    private List<MemberInfoDTO> memberInfoList;

    private List<ItemOption1DTO> itemOption;
}
