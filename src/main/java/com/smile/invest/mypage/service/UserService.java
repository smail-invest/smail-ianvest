package com.smile.invest.mypage.service;

import com.smile.invest.mypage.dao.UserDAO;
import com.smile.invest.mypage.dto.ChargingHistoryDTO;
import com.smile.invest.mypage.dto.MileageHistoryDTO;
import com.smile.invest.mypage.dto.UserDTO;
import org.springframework.stereotype.Service;
import com.smile.invest.common.exception.user.UserModifyException;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private final UserDAO mapper;


    public UserService(UserDAO mapper) {
        this.mapper = mapper;
    }

    /* 유저 정보 조회 */
    public UserDTO userInfo(String userId){

        UserDTO userInfo = mapper.userInfo(userId);

        return userInfo;
    }

    /* 유저 정보 변경 */
    public void modifyUser(UserDTO user) throws UserModifyException {

        int result = mapper.updateMember(user);

        if(!(result > 0)) {
            throw new UserModifyException("회원 정보 수정에 실패하셨습니다.");
        }
    }

    /* 유저 마일리지 조회 */
    public int userMileage(String userId){

        int userMileage = mapper.userMileageSearch(userId);

        return userMileage;
    }

    /* 카카오페이 젤리 충전 (마일리지현황 변경)*/
//    public void chargePoint(Double userMileage, String userId){
//
//        mapper.saveMileage(userMileage, userId);
//
//    }


    /* 수수료 찾기 */
    public double fee(String feeName) {
        return mapper.fee(feeName);
    }





      /* 충전 마일리지 이력 쌓기*/

    public void insertMileageHistory(String newAmount, double realPoint, String userId, String feeName, double userMileage) {


        System.out.println("매개변수 값들 : "+ newAmount + realPoint + userId + feeName + userMileage);


        MileageHistoryDTO mileageHistory = new MileageHistoryDTO();
        mileageHistory.setMileageGiven(realPoint);
        mileageHistory.setPaymentAmount( Double.parseDouble(newAmount));


        System.out.println("mileageHistory" + mileageHistory);




        mapper.chargeHistory(mileageHistory); // 부모 테이블 먼저 insert

        System.out.println("insert1 ");


        ChargingHistoryDTO charge = new ChargingHistoryDTO();
        charge.setUserId(userId);
        charge.setFeeNumber(mapper.findFee(feeName));
        charge.setMileageHistoryNum(mileageHistory.getMileageHistoryNum());

        System.out.println("charge" + charge);


        mapper.registerMileage(charge); // (자식)마일리지 충전내역 테이블 insert!
        System.out.println("insert2");




        mapper.saveMileage(userMileage, userId); // 마일리지 테이블 업데이트~
        System.out.println("완료:!");
    }
}
