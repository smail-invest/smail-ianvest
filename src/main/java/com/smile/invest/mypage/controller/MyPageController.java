package com.smile.invest.mypage.controller;

import com.smile.invest.common.exception.user.UserModifyException;
import com.smile.invest.member.dto.UserImpl;
import com.smile.invest.mypage.dto.MileageHistoryDTO;
import com.smile.invest.mypage.dto.UserDTO;
import com.smile.invest.mypage.service.UserService;
import com.smile.invest.common.util.SessionUtil;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/mypage")
public class MyPageController {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    public MyPageController(PasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @GetMapping("/main")
    public String myPageMain() {

        return "content/mypage/mypage_main";
    }

    @GetMapping("/info")
    public String myPageInfo(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        UserDTO userInfo = userService.userInfo(((UserImpl) userDetails).getUserId());

        model.addAttribute("user", userInfo);

        return "content/mypage/mypage_info";
    }

    @GetMapping("/info/update")
    public String myPageInfoChange(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        UserDTO userInfo = userService.userInfo(((UserImpl) userDetails).getUserId());
        model.addAttribute("user", userInfo);

        return "content/mypage/mypage_info_update";
    }

    @PostMapping("/info/update")
    public String modifyUser(@ModelAttribute UserDTO user, HttpServletRequest request, HttpServletResponse response, RedirectAttributes rttr) throws UserModifyException {

        System.out.println(user);
        System.out.println(user.getUserPassword());

        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));

        userService.modifyUser(user);

        SessionUtil.invalidateSession(request, response);

        rttr.addFlashAttribute("message", "회원 정보 수정에 성공하였습니다.");

        return "redirect:/";

    }

    @GetMapping("/mileage")
    public String myPageMileageCharge(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        int userMileage = userService.userMileage(((UserImpl) userDetails).getUserId());

        model.addAttribute("userMileage", userMileage);

        return "content/mypage/mileage_charge";
    }

    @GetMapping("/mileage/pay")
    public String myPageMileagePay(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        int userMileage = userService.userMileage(((UserImpl) userDetails).getUserId());

        model.addAttribute("userMileage", userMileage);

        return "content/mypage/mileage_pay";
    }

    @GetMapping("/sponsor")
    public String myPageSponsor() {

        return "content/mypage/sponsor";
    }

    @GetMapping("/support")
    public String myPageSupport() {

        return "content/mypage/support";
    }

    @GetMapping("/project")
    public String myPageMyProject() {

        return "content/mypage/my_project";
    }

    @GetMapping("/favorites")
    public String myPageFavorites() {

        return "content/mypage/favorites";
    }



    /* 충전한 금액 db에 저장하기  */
    @GetMapping("/charge/point")
    public String myCashPro(String amount, @AuthenticationPrincipal UserImpl user){ // 현재 로그인안 아이디 조회해서 가져오기

        System.out.println("amount : " + amount);
        try{
            String newAmount = amount.replaceAll(",","");

            String feeName = "충전";
            System.out.println("userService.fee() : " + userService.fee(feeName));

            double realPoint = Double.parseDouble(newAmount) - (Double.parseDouble(newAmount) * userService.fee(feeName));

            System.out.println("realPoint : " + realPoint);
            System.out.println("user.getUserId() :" + user.getUserId());

            String userId = user.getUserId();

            double userMileage = realPoint + (double)(userService.userMileage(userId));
            System.out.println("usermileage : " + userMileage);



            userService.insertMileageHistory(newAmount, realPoint, userId, feeName, userMileage);




        }catch (NumberFormatException e){

        }catch (Exception e){

        }


        return "redirect:content/mypage/mileage_charge";
    }



}
