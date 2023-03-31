package com.smile.invest.board.notice.dao;

import com.smile.invest.board.notice.dto.NoticeDTO;
import com.smile.invest.common.paging.SelectCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface NoticeDAO {

    int selectTotalCount(Map<String, String> searchMap);
    List<NoticeDTO> findNoticeList(SelectCriteria selectCriteria);

    NoticeDTO noticeDetail(String number);


    int insertNotice(NoticeDTO board);

    void deleteNotice(String number);

    NoticeDTO selectNoticeDetail(String number);

    int updateNotice(NoticeDTO notice);
}
