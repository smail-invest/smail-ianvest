package com.smile.invest.mypage.dao;

import com.smile.invest.mypage.dto.ChargingHistoryDTO;
import com.smile.invest.mypage.dto.FeeDTO;
import com.smile.invest.mypage.dto.MileageHistoryDTO;
import com.smile.invest.mypage.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserDAO {

    UserDTO userInfo(String userId);

    int updateMember(UserDTO user);

    int userMileageSearch(String userId);

    void saveMileage(Double realPoint, String userId);

    double fee(String feeName);


    String findFee(String feeName);


    void registerMileage(ChargingHistoryDTO charge);


     void chargeHistory(MileageHistoryDTO mileageHistory);

}
