package com.smile.invest.board.smileInfo.dao;

import com.smile.invest.board.smileInfo.dto.SmileInfoDTO;
import com.smile.invest.common.paging.SelectCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SmileInfoDAO {

    int selectTotalCount(Map<String, String> searchMap);
    List<SmileInfoDTO> findSmileInfoList(SelectCriteria selectCriteria);

    SmileInfoDTO smileInfoDetail(String number);


    int insertSmileInfo(SmileInfoDTO board);

    void deleteSmileInfo(String number);

    SmileInfoDTO selectSmileInfoDetail(String number);

    int updateSmileInfo(SmileInfoDTO smileInfo);
}