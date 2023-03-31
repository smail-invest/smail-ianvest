package com.smile.invest.board.notice.service;

import com.smile.invest.board.notice.dao.NoticeDAO;
import com.smile.invest.board.notice.dto.NoticeDTO;
import com.smile.invest.common.paging.SelectCriteria;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NoticeService {

    private final NoticeDAO mapper;

    public NoticeService(NoticeDAO mapper) {
        this.mapper = mapper;
    }

    // 총 게시물 수 카운트 (페이징 처리 시 필요
    public int selectTotalCount(Map<String, String> searchMap) {

        int result = mapper.selectTotalCount(searchMap);

        return result;
    }

    // 게시물 리스트 출력
    public List<NoticeDTO> findNoticeList(SelectCriteria selectCriteria) {

        List<NoticeDTO> noticeList = mapper.findNoticeList(selectCriteria);

        return noticeList;
    }

    // 게시물 상세 페이지 조회
    public NoticeDTO noticeDetail(String number) {

        NoticeDTO detailPage = mapper.noticeDetail(number);

        return detailPage;
    }

    // 게시물 등록
    public void insertNotice(NoticeDTO board) {

        int result = mapper.insertNotice(board);

    }

    // 게시물 삭제
    public void deleteNotice(String number) {

        mapper.deleteNotice(number);
    }

    // 게시물 수정
    public NoticeDTO selectNoticeDetail(String number) {

        NoticeDTO result = mapper.selectNoticeDetail(number);

        return result;
    }


    public void updateNotice(NoticeDTO notice) {

        int result = mapper.updateNotice(notice);

    }
}
