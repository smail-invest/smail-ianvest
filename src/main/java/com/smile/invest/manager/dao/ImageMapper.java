package com.smile.invest.manager.dao;

import com.smile.invest.manager.dto.ImageDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImageMapper {
    void insertData(ImageDTO imageDTO);

}
