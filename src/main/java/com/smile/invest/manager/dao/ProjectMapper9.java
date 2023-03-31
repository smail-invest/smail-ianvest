package com.smile.invest.manager.dao;

import com.smile.invest.manager.dto.ProjectDTO9;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectMapper9 {

    List<ProjectDTO9> findAll();

     ProjectDTO9 findByNum(String projectNumber);

    void deleteProject(String projectNumber);




}
