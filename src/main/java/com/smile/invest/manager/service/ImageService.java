package com.smile.invest.manager.service;

import com.smile.invest.manager.dao.ImageMapper;
import com.smile.invest.manager.dto.ImageDTO;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    private final ImageMapper mapper;

    public ImageService(ImageMapper mapper) {
        this.mapper = mapper;
    }


    public void insertData(ImageDTO imageDTO) {

        mapper.insertData(imageDTO);

    }
}
