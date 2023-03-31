package com.smile.invest.projectCreate.dao;

import com.smile.invest.projectCreate.dto.ItemInformationNoticeDTO;
import com.smile.invest.projectCreate.dto.ItemOptionDTO;
import com.smile.invest.projectCreate.dto.ProjectCreateDTO;
import com.smile.invest.projectCreate.dto.ProjectFile1DTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProjectCreateDAO {

    int insertProject(ProjectCreateDTO project);

    int insertOption(ItemOptionDTO option);

    int insertFile(ProjectFile1DTO file);

    int insertProjectNum(ProjectCreateDTO projectNum);

    int insertItemNotice(ItemInformationNoticeDTO itemInformationNotice);
}
