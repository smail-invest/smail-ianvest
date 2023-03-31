package com.smile.invest.board.smileInfo.controller;

import com.smile.invest.board.smileInfo.dto.SmileInfoDTO;
import com.smile.invest.board.smileInfo.service.SmileInfoService;
import com.smile.invest.common.paging.Pagenation;
import com.smile.invest.common.paging.SelectCriteria;
import com.smile.invest.member.dto.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class SmileInfoController {

    private SmileInfoService smileInfoService;

    @Autowired
    public SmileInfoController(SmileInfoService smileInfoService) { this.smileInfoService = smileInfoService; }

    @GetMapping("/smileInfo/list")
    public ModelAndView smileInfoMain(HttpServletRequest request, ModelAndView mv) {

        String currentPage = request.getParameter("currentPage");
        int pageNo = 1;

        if(currentPage != null && !"".equals(currentPage)) {
            pageNo = Integer.parseInt(currentPage);
        }

        String searchCondition = request.getParameter("searchCondition");
        String searchValue = request.getParameter("searchValue");

        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);


        /*
         * 전체 게시물 수가 필요하다.
         * 데이터베이스에서 먼저 전체 게시물 수를 조회해올 것이다.
         * 검색조건이 있는 경우 검색 조건에 맞는 전체 게시물 수를 조회한다.
         */
        int totalCount = smileInfoService.selectTotalCount(searchMap);


        /* 한 페이지에 보여 줄 게시물 수 */
        int limit = 3;		//얘도 파라미터로 전달받아도 된다.

        /* 한 번에 보여질 페이징 버튼의 갯수 */
        int buttonAmount = 3;

        /* 페이징 처리를 위한 로직 호출 후 페이징 처리에 관한 정보를 담고 있는 인스턴스를 반환받는다. */
        SelectCriteria selectCriteria = null;

        if(searchCondition != null && !"".equals(searchCondition)) {
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
        } else {
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
        }


        List<SmileInfoDTO> lists = smileInfoService.findSmileInfoList(selectCriteria);

        mv.addObject("smileInfoList", lists);
        mv.addObject("selectCriteria", selectCriteria);

        mv.setViewName("content/board/smileInfo/smileInfo_list");

        return mv;
    }


    // 게시물 상세 페이지
    @GetMapping("/smileInfo/detail/{number}")
    public String smileInfoDetail (@PathVariable String number, Model model) {

        SmileInfoDTO detailPage = smileInfoService.smileInfoDetail(number);

        model.addAttribute("detail", detailPage);

        return "content/board/smileInfo/smileInfo_detail";
    }



    // 게시물 작성
    @GetMapping("/smileInfo/create")
    public String smileInfoCreateForm() {

        return "content/board/smileInfo/smileInfo_create";
    }

    @PostMapping("/smileInfo/create")
    public String smileInfoCreate(@ModelAttribute SmileInfoDTO board, RedirectAttributes rttr, @AuthenticationPrincipal UserDetails userDetails) {

        board.setMemberId(((UserImpl)userDetails).getUserId());

        System.out.println(board);

        rttr.addFlashAttribute("message", "createSuccess");

        smileInfoService.insertSmileInfo(board);

        return "redirect:/board/smileInfo/list";
    }



    // 게시물 삭제
    @GetMapping("/smileInfo/delete/{number}")
    public String smileInfoDelete(@PathVariable String number, RedirectAttributes rttr) {

        rttr.addFlashAttribute("message", "deleteSuccess");

        smileInfoService.deleteSmileInfo(number);

        return "redirect:/board/smileInfo/list";
    }


    // 게시물 수정
    @GetMapping("/smileInfo/update/{number}")
    public String smileInfoUpdateForm(@PathVariable String number, Model model) {

        SmileInfoDTO smileInfo = smileInfoService.selectSmileInfoDetail(number);

        model.addAttribute("smileInfo", smileInfo);

        return "content/board/smileInfo/smileInfo_update";
    }

    //     로그인 시 로그인 정보 가져오는 내용이 포함된 메소드
    @PostMapping("/smileInfo/update")
    public String smileInfoUpdate(@ModelAttribute SmileInfoDTO smileInfo, RedirectAttributes rttr, @AuthenticationPrincipal UserDetails userDetails) {

        smileInfo.setMemberId(((UserImpl)userDetails).getUserId());

        smileInfoService.updateSmileInfo(smileInfo);

        rttr.addFlashAttribute("message", "updateSuccess");

        return "redirect:/board/smileInfo/list";
    }




}
