package com.smile.invest.board.event.service;

import com.smile.invest.board.event.dao.EventDAO;
import com.smile.invest.board.event.dto.EventDTO;
import com.smile.invest.common.paging.SelectCriteria;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EventService {

    private final EventDAO mapper;

    public EventService(EventDAO mapper) {

        this.mapper = mapper;
    }

    public int selectTotalCount(Map<String, String> searchMap) {

        int result = mapper.selectTotalCount(searchMap);

        return result;
    }

    // 게시물 리스트 출력
    public List<EventDTO> findEventList(SelectCriteria selectCriteria) {

        List<EventDTO> eventList = mapper.findEventList(selectCriteria);

        return eventList;
    }

    // 게시물 상세 페이지 조회
    public EventDTO eventDetail(String number) {

        EventDTO detailPage = mapper.eventDetail(number);

        return detailPage;
    }

    // 게시물 등록
    public void insertEvent(EventDTO board) {

        int result = mapper.insertEvent(board);

    }

    // 게시물 삭제
    public void deleteEvent(String number) {

        mapper.deleteEvent(number);
    }

    // 게시물 수정
    public EventDTO selectEventDetail(String number) {

        EventDTO result = mapper.selectEventDetail(number);

        return result;
    }


    public void updateEvent(EventDTO event) {

        int result = mapper.updateEvent(event);

    }
}

