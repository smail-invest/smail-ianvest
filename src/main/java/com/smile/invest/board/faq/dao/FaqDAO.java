package com.smile.invest.board.faq.dao;

import com.smile.invest.board.faq.dto.FaqDTO;
import com.smile.invest.common.paging.SelectCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface FaqDAO {

    int selectTotalCount(Map<String, String> searchMap);
    List<FaqDTO> findFaqList(SelectCriteria selectCriteria);

    FaqDTO faqDetail(String number);


    int insertFaq(FaqDTO board);

    void deleteFaq(String number);

    FaqDTO selectFaqDetail(String number);

    int updateFaq(FaqDTO faq);
}