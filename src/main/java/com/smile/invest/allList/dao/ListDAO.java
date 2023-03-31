package com.smile.invest.allList.dao;

import com.smile.invest.allList.dto.Project1DTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ListDAO {

    List<Project1DTO> selectAllProjectList();

    List<Project1DTO> categoryProjectList(String category);

    List<Project1DTO> searchProjectList(String searchProject);

    void projectPay(String userId, int payMileage);
    Project1DTO listDetail(String projectNumber);
}
