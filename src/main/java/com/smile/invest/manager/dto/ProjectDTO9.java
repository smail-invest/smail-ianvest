package com.smile.invest.manager.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ProjectDTO9 {
//
//    private String projectCategoryCode;
//    private String projectCategoryName;

//    private String number; // 프로젝트 번호
    private String projectNumber; //프로젝트 일련번호
    private String projectCategoryCode;  //카테고리코드
    private String projectTitle; // 제목
    private String writerIntroduction; //작성자 소개
    private String shortIntroduction; //요약소개
    private String longIntroduction;// 구체적소개
    private int targetMileage;// 목표마일리지
    private Date startDate;//시작일
    private Date endDate;//종료일
    private String projectDeleteYn; //

//    private List<ProjectFileDTO> projectFileDTOList;


//    private UploadFile attachFile; //첨부파일 하나
//    private List<UploadFile> imageFiles; //첨부파일 여러개(이미지들)
//    private Date settlementDate; //정산일
//    private String expectedUse; //예산
//    private String businessLicenseNumber;//사업자등록번호
//    private String expectDifficulty; //예상어려움
//    private String additionalPolicy; //추가정책

}