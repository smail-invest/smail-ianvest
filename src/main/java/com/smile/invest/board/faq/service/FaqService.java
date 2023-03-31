package com.smile.invest.board.faq.service;

import com.smile.invest.board.faq.dao.FaqDAO;
import com.smile.invest.board.faq.dto.FaqDTO;
import com.smile.invest.common.paging.SelectCriteria;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FaqService {

    private final FaqDAO mapper;

    public FaqService(FaqDAO mapper) {this.mapper = mapper;}

    public int selectTotalCount(Map<String, String> searchMap) {

        int result = mapper.selectTotalCount(searchMap);

        return result;
    }

    // 게시물 리스트 출력
    public List<FaqDTO> findFaqList(SelectCriteria selectCriteria) {

        List<FaqDTO> faqList = mapper.findFaqList(selectCriteria);

        return faqList;
    }

    // 게시물 상세 페이지 조회
    public FaqDTO faqDetail(String number) {

        FaqDTO detailPage = mapper.faqDetail(number);

        return detailPage;
    }

    // 게시물 등록
    public void insertFaq(FaqDTO board) {

        int result = mapper.insertFaq(board);

    }

    // 게시물 삭제
    public void deleteFaq(String number) {

        mapper.deleteFaq(number);
    }

    // 게시물 수정
    public FaqDTO selectFaqDetail(String number) {

        FaqDTO result = mapper.selectFaqDetail(number);

        return result;
    }


    public void updateFaq(FaqDTO faq) {

        int result = mapper.updateFaq(faq);

    }
}

