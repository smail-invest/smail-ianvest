package com.smile.invest.board.faq.controller;

import com.smile.invest.board.faq.dto.FaqDTO;
import com.smile.invest.board.faq.service.FaqService;
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
public class FaqController {

    private FaqService faqService;

    @Autowired
    public FaqController(FaqService faqService) { this.faqService = faqService; }

    @GetMapping("/faq/list")
    public ModelAndView faqMain(HttpServletRequest request, ModelAndView mv) {

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
        int totalCount = faqService.selectTotalCount(searchMap);


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


        List<FaqDTO> lists = faqService.findFaqList(selectCriteria);

        mv.addObject("faqList", lists);
        mv.addObject("selectCriteria", selectCriteria);

        mv.setViewName("content/board/faq/faq_list");

        return mv;
    }


    // 게시물 상세 페이지
    @GetMapping("/faq/detail/{number}")
    public String faqDetail (@PathVariable String number, Model model) {

        FaqDTO detailPage = faqService.faqDetail(number);

        model.addAttribute("detail", detailPage);

        return "content/board/faq/faq_detail";
    }



    // 게시물 작성
    @GetMapping("/faq/create")
    public String faqCreateForm() {

        return "content/board/faq/faq_create";
    }

    @PostMapping("/faq/create")
    public String faqCreate(@ModelAttribute FaqDTO board, RedirectAttributes rttr, @AuthenticationPrincipal UserDetails userDetails) {

        board.setMemberId(((UserImpl)userDetails).getUserId());

        System.out.println(board);

        rttr.addFlashAttribute("message", "createSuccess");

        faqService.insertFaq(board);

        return "redirect:/board/faq/list";
    }



    // 게시물 삭제
    @GetMapping("/faq/delete/{number}")
    public String faqDelete(@PathVariable String number, RedirectAttributes rttr) {

        rttr.addFlashAttribute("message", "deleteSuccess");

        faqService.deleteFaq(number);

        return "redirect:/board/faq/list";
    }


    // 게시물 수정
    @GetMapping("/faq/update/{number}")
    public String faqUpdateForm(@PathVariable String number, Model model) {

        FaqDTO faq = faqService.selectFaqDetail(number);

        model.addAttribute("faq", faq);

        return "content/board/faq/faq_update";
    }

    //     로그인 시 로그인 정보 가져오는 내용이 포함된 메소드
    @PostMapping("/faq/update")
    public String faqUpdate(@ModelAttribute FaqDTO faq, RedirectAttributes rttr, @AuthenticationPrincipal UserDetails userDetails) {

        faq.setMemberId(((UserImpl)userDetails).getUserId());

        faqService.updateFaq(faq);

        rttr.addFlashAttribute("message", "updateSuccess");

        return "redirect:/board/faq/list";
    }




}
