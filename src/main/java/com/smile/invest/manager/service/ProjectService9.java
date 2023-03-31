package com.smile.invest.manager.service;

import com.smile.invest.manager.dao.ProjectMapper9;
import com.smile.invest.manager.dto.ProjectDTO9;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class ProjectService9 {

    private final ProjectMapper9 mapper;

    public ProjectService9(ProjectMapper9 mapper) {
        this.mapper = mapper;
    }

    public List<ProjectDTO9> findAll() {

        List<ProjectDTO9> projectList = mapper.findAll();

        return projectList;

    }

    public ProjectDTO9 findByNum(String projectNumber) {

        ProjectDTO9 prodto = mapper.findByNum(projectNumber);

        return prodto;
    }

    public void deleteProject(String projectNumber) {

        mapper.deleteProject(projectNumber);
    }



    }

