package com.smile.invest.board.event.dao;

import com.smile.invest.board.event.dto.EventDTO;
import com.smile.invest.common.paging.SelectCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface EventDAO {

    int selectTotalCount(Map<String, String> searchMap);
    List<EventDTO> findEventList(SelectCriteria selectCriteria);

    EventDTO eventDetail(String number);

    int insertEvent(EventDTO board);

    void deleteEvent(String number);

    EventDTO selectEventDetail(String number);

    int updateEvent(EventDTO event);
}