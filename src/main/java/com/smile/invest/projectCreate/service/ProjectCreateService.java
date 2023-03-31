package com.smile.invest.projectCreate.service;

import com.smile.invest.projectCreate.dao.ProjectCreateDAO;
import com.smile.invest.projectCreate.dto.ItemInformationNoticeDTO;
import com.smile.invest.projectCreate.dto.ItemOptionDTO;
import com.smile.invest.projectCreate.dto.ProjectCreateDTO;
import com.smile.invest.projectCreate.dto.ProjectFile1DTO;
import org.springframework.stereotype.Service;

@Service
public class ProjectCreateService {

    private final ProjectCreateDAO mapper;

    public ProjectCreateService(ProjectCreateDAO mapper) {
        this.mapper = mapper;
    }

    /* 프로젝트 등록*/
    public void projectCreate(ProjectCreateDTO project) {

        mapper.insertProject(project);

        project.setProjectNum(project.getProjectNum());
        ItemOptionDTO option = project.getItemOption();
        ItemInformationNoticeDTO itemInformationNotice = project.getItemInformationNotice();

        option.setProjectNum(project.getProjectNum());
        itemInformationNotice.setProjectNum(project.getProjectNum());

        mapper.insertOption(option);
        mapper.insertItemNotice(itemInformationNotice);

    }

    public void insertProjectNum(ProjectCreateDTO projectNum) {

        mapper.insertProjectNum(projectNum);
    }

    public void insertFile(ProjectFile1DTO file) {

        mapper.insertFile(file);
    }
}
