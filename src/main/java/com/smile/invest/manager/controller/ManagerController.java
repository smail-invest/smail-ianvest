package com.smile.invest.manager.controller;

import com.smile.invest.manager.dto.ImageDTO;
import com.smile.invest.manager.dto.MemberDTO9;
import com.smile.invest.manager.dto.ProjectDTO9;
import com.smile.invest.manager.service.ImageService;
import com.smile.invest.manager.service.MemberService9;
import com.smile.invest.manager.service.ProjectService9;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerController {

    private final ProjectService9 projectService9;

    private final MemberService9 memberService9;

    private final ImageService imageService;

    @GetMapping("/main")
    public String managerMain() {
        return "content/manager/mg_manager_main";
    }


    //프로젝트 관리
    @GetMapping("/project_management")
    public String projectManagement(Model model) {
        List<ProjectDTO9> projectList = projectService9.findAll(); //select

        model.addAttribute("projectList", projectList);
        return "content/manager/mg_project_managerment";
    }

    @GetMapping("/detail_page/{projectNumber}")
    public String managerDetail(@PathVariable String projectNumber, Model model){
        ProjectDTO9 prodto = projectService9.findByNum(projectNumber); //select
        model.addAttribute("prodto", prodto);
        return "content/manager/manager_detail/detail_page";
    }

    @GetMapping("/detail_page/delete/{projectNumber}")
    public String deleteProject(@PathVariable String projectNumber) {
        projectService9.deleteProject(projectNumber);

        return "redirect:/manager/project_management";
    }


    //사용자 관리
    @GetMapping("/user_management") //localhost:8889/manager/user_management
    public String userManagement(Model model) {
        List<MemberDTO9> memberList = memberService9.findAll(); //select

        model.addAttribute("memberList", memberList);
        return "content/manager/mg_user_management";
    }

    @GetMapping("/user/detail/{memberId}")
    public String memberDetail(@PathVariable String memberId, Model model){
        MemberDTO9 memdto = memberService9.findById(memberId); //select
        model.addAttribute("memdto", memdto);

        return "content/manager/mg_user_detail";
    }


    @GetMapping("/user/detail/delete/{memberId}")
    public String deleteMember(@PathVariable String memberId) {
        System.out.println("memberId = " + memberId);
        memberService9.deleteMember(memberId);
        return "redirect:/manager/user_management";
    }


    //블랙 리스트 관리
    @GetMapping("/user_blacklist")
    public String userBlacklist(Model model) {
        List<MemberDTO9> memberList = memberService9.findBlack(); //select
        model.addAttribute("memberList", memberList);

        return "content/manager/mg_blacklist_management";
    }

    @GetMapping("/blackuser/detail/{memberId}")
    public String blackMemberDetail(@PathVariable String memberId, Model model){
        MemberDTO9 memdto = memberService9.findById(memberId); //select
        model.addAttribute("memdto", memdto);

        return "content/manager/mg_blackuser_detail";
    }


    @GetMapping("/blackuser/detail/recovery/{memberId}")
    public String recoveryMember(@PathVariable String memberId) {
        memberService9.recoveryMember(memberId);
        return "redirect:/manager/user_blacklist";
    }


    //파일업로드

    @GetMapping("/fileUpload")
    public String fileUpload() {

        return "content/manager/fileUpload";

    }

    @PostMapping("/fileUpload")
    public String insertData(@RequestParam String text,
                             @RequestParam MultipartFile file,
                             Model model) throws IOException {

        String fileName = file.getOriginalFilename();
        file.transferTo(new File("/Users/kks/smile_invest/src/main/resources/templates/content/images9/", fileName));


        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setText(text);
        imageDTO.setFileName(fileName);

        imageService.insertData(imageDTO);

        model.addAttribute("ImageDTO", imageDTO);

        return "content/manager/imageView";

    }






    @GetMapping("/board_management_notice")
    public String boardManagementNotice() {

        return "content/manager/manager_board/board_mgm_notice";
    }

    @GetMapping("/board_management_event")
    public String boardManagementEvent() {

        return "content/manager/manager_board/board_mgm_event";
    }

    @GetMapping("/board_management_faq")
    public String boardManagementFaq() {

        return "content/manager/manager_board/board_mgm_faq";
    }

    @GetMapping("/board_management_introduction")
    public String boardManagementIntroduction() {

        return "content/manager/manager_board/board_mgm_introduction";
    }


}