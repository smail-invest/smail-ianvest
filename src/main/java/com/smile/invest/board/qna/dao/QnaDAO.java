package com.smile.invest.board.qna.dao;

import com.smile.invest.board.qna.dto.QnaDTO;
import com.smile.invest.board.qna.dto.ReplyDTO;
import com.smile.invest.common.paging.SelectCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface QnaDAO {

    int selectTotalCount(Map<String, String> searchMap);

    List<QnaDTO> findQnaList(SelectCriteria selectCriteria);

    QnaDTO qnaDetail(String number);

    int insertQna(QnaDTO board);

    void deleteQna(QnaDTO board);

    QnaDTO seleteQnaDetail(String number);

    int updateQna(QnaDTO qna);
}