package com.smile.invest.board.notice.controller;

import com.smile.invest.board.notice.dto.NoticeDTO;
import com.smile.invest.board.notice.service.NoticeService;
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
public class NoticeController {

    private final NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    // 게시물 리스트 출력
    @GetMapping("/notice/list")
    public ModelAndView noticeMain(HttpServletRequest request, ModelAndView mv) {
        // 1. 전체 게시물을 조회 - 완료
        // 2. 페이징적용
        // 3. 검색이 있으면 검색을 추가

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
        int totalCount = noticeService.selectTotalCount(searchMap);


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


        List<NoticeDTO> lists = noticeService.findNoticeList(selectCriteria);

        mv.addObject("noticeList", lists);
        mv.addObject("selectCriteria", selectCriteria);

        mv.setViewName("content/board/notice/notice_list");

        return mv;
    }


    // 게시물 상세 페이지
    @GetMapping("/notice/detail/{number}")
    public String noticeDetail (@PathVariable String number, Model model) {

        NoticeDTO detailPage = noticeService.noticeDetail(number);

        model.addAttribute("detail", detailPage);

        return "content/board/notice/notice_detail";
    }


    // 게시물 작성
    @GetMapping("/notice/create")
    public String noticeCreateForm() {

        return "content/board/notice/notice_create";
    }

    @PostMapping("/notice/create")
    public String noticeCreate(@ModelAttribute NoticeDTO board, RedirectAttributes rttr, @AuthenticationPrincipal UserDetails userDetails) {

        board.setMemberId(((UserImpl)userDetails).getUserId());

        System.out.println(board);

        rttr.addFlashAttribute("message", "createSuccess");

        noticeService.insertNotice(board);

        return "redirect:/board/notice/list";
    }



    // 게시물 삭제
    @GetMapping("/notice/delete/{number}")
    public String noticeDelete(@PathVariable String number, RedirectAttributes rttr) {

        rttr.addFlashAttribute("message", "deleteSuccess");

        noticeService.deleteNotice(number);

        return "redirect:/board/notice/list";
    }


    // 게시물 수정
    @GetMapping("/notice/update/{number}")
    public String noticeUpdateForm(@PathVariable String number, Model model) {

        NoticeDTO notice = noticeService.selectNoticeDetail(number);

        model.addAttribute("notice", notice);

        return "content/board/notice/notice_update";
    }

    //     로그인 시 로그인 정보 가져오는 내용이 포함된 메소드
    @PostMapping("/notice/update")
    public String noticeUpdate(@ModelAttribute NoticeDTO notice, RedirectAttributes rttr, @AuthenticationPrincipal UserDetails userDetails) {

        notice.setMemberId(((UserImpl)userDetails).getUserId());

        System.out.println("여기서 떠야함" + notice);

        noticeService.updateNotice(notice);

        rttr.addFlashAttribute("message", "updateSuccess");

        return "redirect:/board/notice/list";
    }




}
