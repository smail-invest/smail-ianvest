package com.smile.invest.allList.service;

import com.smile.invest.allList.dao.ListDAO;
import com.smile.invest.allList.dto.Project1DTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ListService {

    private final ListDAO mapper;

    public ListService(ListDAO mapper) { this.mapper = mapper;}

    public List<Project1DTO> selectAllProject() {

        List<Project1DTO> projectList = mapper.selectAllProjectList();

        return projectList;

    }

    public List<Project1DTO>  categoryProjectList(String category){

        List<Project1DTO> projectList = mapper.categoryProjectList(category);

        return projectList;
    }


    public  List<Project1DTO> searchProjectList(String searchProject) throws Exception {

        List<Project1DTO> projectList = mapper.searchProjectList(searchProject);

        if(projectList == null){
            throw new Exception();
        }

        return projectList;
    }

    public Project1DTO listDetail(String projectNumber) {

        Project1DTO detailPage = mapper.listDetail(projectNumber);

        return detailPage;
    }


    public void projectPay(String userId, int payMileage) {

        mapper.projectPay(userId, payMileage);
    }
}
