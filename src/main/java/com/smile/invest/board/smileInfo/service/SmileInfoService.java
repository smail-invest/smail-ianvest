package com.smile.invest.board.smileInfo.service;

import com.smile.invest.board.smileInfo.dao.SmileInfoDAO;
import com.smile.invest.board.smileInfo.dto.SmileInfoDTO;
import com.smile.invest.common.paging.SelectCriteria;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SmileInfoService {

    private final SmileInfoDAO mapper;

    public SmileInfoService(SmileInfoDAO mapper) {this.mapper = mapper;}

    public int selectTotalCount(Map<String, String> searchMap) {

        int result = mapper.selectTotalCount(searchMap);

        return result;
    }

    // 게시물 리스트 출력
    public List<SmileInfoDTO> findSmileInfoList(SelectCriteria selectCriteria) {

        List<SmileInfoDTO> smileInfoList = mapper.findSmileInfoList(selectCriteria);

        return smileInfoList;
    }

    // 게시물 상세 페이지 조회
    public SmileInfoDTO smileInfoDetail(String number) {

        SmileInfoDTO detailPage = mapper.smileInfoDetail(number);

        return detailPage;
    }

    // 게시물 등록
    public void insertSmileInfo(SmileInfoDTO board) {

        int result = mapper.insertSmileInfo(board);

    }

    // 게시물 삭제
    public void deleteSmileInfo(String number) {

        mapper.deleteSmileInfo(number);
    }

    // 게시물 수정
    public SmileInfoDTO selectSmileInfoDetail(String number) {

        SmileInfoDTO result = mapper.selectSmileInfoDetail(number);

        return result;
    }


    public void updateSmileInfo(SmileInfoDTO smileInfo) {

        int result = mapper.updateSmileInfo(smileInfo);

    }
}

