package com.smile.invest.allList.dto;

import com.smile.invest.projectCreate.dto.ItemOptionDTO;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProjectFileDTO {

    private String fileNumber;

    private String fileType;

    private String fileLocation;

    private String fileOriginName;

    private String fileChangeName;

    private Date fileCreateDate;

    private String fileDeleteYn;

    private String projectNumber;


}
