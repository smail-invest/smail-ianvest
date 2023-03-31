package com.smile.invest.board.qna.service;

import com.smile.invest.board.qna.dao.QnaDAO;
import com.smile.invest.board.qna.dto.QnaDTO;
import com.smile.invest.board.qna.dto.ReplyDTO;
import com.smile.invest.common.paging.SelectCriteria;
import com.smile.invest.member.dto.UserImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class QnaService {

    private final QnaDAO mapper;

    public QnaService(QnaDAO mapper) {this.mapper = mapper;}

    public int selectTotalCount(Map<String, String> searchMap) {

        int result = mapper.selectTotalCount(searchMap);

        return result;
    }


    public List<QnaDTO> findqnaList(SelectCriteria selectCriteria) {

        List<QnaDTO> qnaList = mapper.findQnaList(selectCriteria);

        System.out.println("qnaList = " + qnaList);

        return qnaList;
    }


    public QnaDTO qnaDetail(String number) {

        QnaDTO detailPage = mapper.qnaDetail(number);

        return detailPage;
    }



    public void insertQna(QnaDTO board) {

        int result = mapper.insertQna(board);

    }

    public void deleteQna(QnaDTO board) {

        mapper.deleteQna(board);
    }


    public QnaDTO selectQnaDetail(String number) {

        QnaDTO result = mapper.seleteQnaDetail(number);

        return result;
    }

    public void updateQna(QnaDTO qna) {

        int result = mapper.updateQna(qna);
    }
}