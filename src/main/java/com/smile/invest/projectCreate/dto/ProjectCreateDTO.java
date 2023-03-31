package com.smile.invest.projectCreate.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProjectCreateDTO implements Serializable {

    private String projectNum;          // 프로젝트 번호

    private String userId;  // 등록자 아이디

    private String categoryCode;        // 카테고리 번호

    private String projectTitle;        // 프로젝트 명

    private String writerInfo;          // 프로젝트 팀 소개

    private String shortInfo;           // 프로젝트 간단 소개

    private String longInfo;           // 프로젝트 구체적 소개

    private int targetMileage;      // 목표 마일리지

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startDate;         // 프로젝트 시작일

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate;           // 프로젝트 종료일

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date settlementDate;    // 정산일

    private String expectedUse;     // 예산

    private String businessNum;     // 사업자등록번호

    private String expectedDifficulty;  // 예상되는 어려움

    private String additionalPolicy;    // 추가정책

    private String projectDeleteYn;     // 프로젝트 삭제여부

    private String projectSchedule;     // 프로젝트 일정

    private ItemOptionDTO itemOption;

    private ItemInformationNoticeDTO itemInformationNotice;

    private ProjectFile1DTO projectFile;

}
