package com.smile.invest.projectCreate.controller;

import com.smile.invest.member.dto.UserImpl;
import com.smile.invest.projectCreate.dto.ItemInformationNoticeDTO;
import com.smile.invest.projectCreate.dto.ItemOptionDTO;
import com.smile.invest.projectCreate.dto.ProjectCreateDTO;
import com.smile.invest.projectCreate.dto.ProjectFile1DTO;
import com.smile.invest.projectCreate.service.ProjectCreateService;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.ResourceUtils;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import java.time.LocalDate;
import java.util.Date;

@Controller
@RequestMapping("/project")
public class ProjectCreateController {

    private final ProjectCreateService projectCreateService;

    public ProjectCreateController(ProjectCreateService projectCreateService) {
        this.projectCreateService = projectCreateService;
    }

    @GetMapping("/create/standardInfo")
    public String standardInfo(){

        return "content/projectCreate/standardInfo";
    }

    @GetMapping("/create/basicInfo")
    public String basicInfo() {

        return "/content/projectCreate/basicInfo";
    }

    @PostMapping("/create/projectOption")
    public String basicInfoForm(@ModelAttribute ProjectCreateDTO project, @AuthenticationPrincipal UserDetails userDetails, Model model){

        /* 로그인한 사용자 아이디 넣기 */ // 이거는 왜 반복해서 안쓰는걸까
        project.setUserId(((UserImpl)userDetails).getUserId());

        model.addAttribute("project", project);

        return "content/projectCreate/projectOption";
    }

    @PostMapping("/create/projectPlan")
    public String projectPlan(@ModelAttribute ItemOptionDTO option, @ModelAttribute ProjectCreateDTO project, Model model){

        project.setItemOption(option);

        model.addAttribute("project", project);

        return "content/projectCreate/projectPlan";
    }

    @PostMapping("/create/safety")
    public String safety(@ModelAttribute ItemInformationNoticeDTO itemInformationNotice, @ModelAttribute ItemOptionDTO option, @ModelAttribute ProjectCreateDTO project, Model model){

        project.setItemInformationNotice(itemInformationNotice);

        project.setItemOption(option);

        model.addAttribute("project", project);

        return "content/projectCreate/safety";
    }

    @PostMapping("/create/projectSuccess")
    public String testPage(@ModelAttribute ItemInformationNoticeDTO itemInformationNotice, @ModelAttribute ItemOptionDTO option, @ModelAttribute ProjectCreateDTO project, Model model){

        project.setItemInformationNotice(itemInformationNotice);

        project.setItemOption(option);

        projectCreateService.projectCreate(project);
        projectCreateService.insertProjectNum(project);

        model.addAttribute("project", project);

        return "content/projectCreate/projectSuccess";
    }

    @PostMapping("/fileUpload")
    public String fileUpload(@ModelAttribute ProjectFile1DTO file, @RequestParam MultipartFile singleFile, Model model) throws FileNotFoundException {

        String root = ResourceUtils.getURL("src/main/resources/static/").getPath();// request.getSession().getServletContext().getRealPath("/img/upload");

        System.out.println("root : " + root);

        String filePath = root + "images/uploadFiles/";
        File mkdir = new File(filePath);
        if(!mkdir.exists()) {
            mkdir.mkdirs();
        }

        /* 파일명 변경 처리 */
        String originFileName = singleFile.getOriginalFilename();
        String ext = originFileName.substring(originFileName.lastIndexOf("."));
        String savedName = UUID.randomUUID().toString().replace("-", "") + ext;

        System.out.println(singleFile);
        System.out.println(singleFile.getOriginalFilename());
        System.out.println(singleFile.getName());
        System.out.println(singleFile.getContentType());
        System.out.println(singleFile.getSize());

        file.setFileType(singleFile.getContentType());
        file.setFileLocation(filePath);
        file.setFileOriginName(singleFile.getOriginalFilename());
        file.setFileChangeName(savedName);

        System.out.println(file);

        /* 파일을 저장 */
        try {
            singleFile.transferTo(new File(filePath + "/" + savedName));
            model.addAttribute("message", "파일 업로드 성공!!");

            projectCreateService.insertFile(file);

        } catch (IllegalStateException | IOException e) {

            e.printStackTrace();
            /* 실패시 파일 삭제 */
            new File(filePath + "\\" + savedName).delete();
            model.addAttribute("message", "파일 업로드 실패!!");
        }

        return "content/projectCreate/basicInfo";
    }
}
