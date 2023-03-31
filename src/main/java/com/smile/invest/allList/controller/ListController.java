package com.smile.invest.allList.controller;

import com.smile.invest.allList.dto.Project1DTO;
import com.smile.invest.allList.service.ListService;
import com.smile.invest.member.dto.UserImpl;
import com.smile.invest.mypage.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/")
public class ListController {

    private final ListService listService;

    private final UserService userService;

    public ListController(ListService listService, UserService userService) { this.listService = listService;
        this.userService = userService;
    }


    @GetMapping({"/", "index"})
    public ModelAndView defaultLocation(ModelAndView mv){

        List<Project1DTO> projectList = listService.selectAllProject();

        System.out.println("controller : " + projectList);

        mv.addObject("projectList", projectList);

        mv.setViewName("content/main/index");

        return mv;
    }

    @GetMapping("/all_list")
    public ModelAndView selectList(ModelAndView mv){

        List<Project1DTO> projectList = listService.selectAllProject();

        System.out.println("controller : " + projectList);

        mv.addObject("projectList", projectList);

        mv.setViewName("content/allList/project_all_list");

        return mv;
    }

    @GetMapping("/food_list")
    public ModelAndView foodList(ModelAndView mv){

        String category = "pcc01";

        List<Project1DTO> projectList = listService.categoryProjectList(category);

        System.out.println("controller : " + projectList);

        mv.addObject("projectList", projectList);

        mv.setViewName("content/allList/project_all_list");

        return mv;
    }

    @GetMapping("/ware_list")
    public ModelAndView wareList(ModelAndView mv){

        String category = "pcc02";

        List<Project1DTO> projectList = listService.categoryProjectList(category);

        System.out.println("controller : " + projectList);

        mv.addObject("projectList", projectList);

        mv.setViewName("content/allList/project_all_list");

        return mv;
    }

    @GetMapping("/style_list")
    public ModelAndView styleList(ModelAndView mv){

        String category = "pcc03";

        List<Project1DTO> projectList = listService.categoryProjectList(category);

        System.out.println("controller : " + projectList);

        mv.addObject("projectList", projectList);

        mv.setViewName("content/allList/project_all_list");

        return mv;
    }

    @GetMapping("/service_list")
    public ModelAndView serviceList(ModelAndView mv){

        String category = "pcc04";

        List<Project1DTO> projectList = listService.categoryProjectList(category);

        System.out.println("controller : " + projectList);

        mv.addObject("projectList", projectList);

        mv.setViewName("content/allList/project_all_list");

        return mv;
    }

    @PostMapping("/search_list")
    public ModelAndView searchList(HttpServletRequest request, ModelAndView mv) throws Exception {

        String searchProject = request.getParameter("searchProject");

        List<Project1DTO> projectList = listService.searchProjectList(searchProject);

        System.out.println("controller : " + projectList);

        mv.addObject("projectList", projectList);

        mv.setViewName("content/allList/project_search_list");

        return mv;
    }

    // 프로젝트 상세 페이지
    @GetMapping("/allList/detail/{projectNumber}")
    public String listDetail (@PathVariable String projectNumber, Model model) {

        Project1DTO detailPage = listService.listDetail(projectNumber);
        System.out.println(detailPage);

        model.addAttribute("detail", detailPage);

        return "content/allList/project_all_detail";
    }


    // 상품 결제 페이지 - 미구현
    @PostMapping("/projectPay/{projectNumber}")
    public ModelAndView selectPayInfo (@AuthenticationPrincipal UserDetails userDetails ,@PathVariable String projectNumber, ModelAndView mv) {

        int userMileage = userService.userMileage(((UserImpl) userDetails).getUserId());

        mv.addObject("userMileage", userMileage);

        Project1DTO detailPage = listService.listDetail(projectNumber);

        mv.addObject("detail", detailPage);
        mv.setViewName("content/allList/projectPay");

        return mv;
    }

    @PostMapping("/projectPay/success")
    public String paySuccess(HttpServletRequest request, @AuthenticationPrincipal UserImpl user){

        int userMileage = Integer.parseInt(request.getParameter("userMileage"));
        int option = Integer.parseInt(request.getParameter("option"));
        String userId = user.getUserId();

        int payMileage = userMileage - option;

        listService.projectPay(userId, payMileage);

        return "content/allList/payAuthsuccess";
    }


}
