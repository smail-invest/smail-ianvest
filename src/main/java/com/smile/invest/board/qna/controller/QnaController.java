package com.smile.invest.board.qna.controller;

import com.smile.invest.board.notice.dto.NoticeDTO;
import com.smile.invest.board.qna.dto.QnaDTO;
import com.smile.invest.board.qna.dto.ReplyDTO;
import com.smile.invest.board.qna.service.QnaService;
import com.smile.invest.common.paging.Pagenation;
import com.smile.invest.common.paging.SelectCriteria;
import com.smile.invest.member.dto.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
public class QnaController {

    private final QnaService qnaService;

    @Autowired
    public QnaController(QnaService qnaService) { this.qnaService = qnaService; }

    @GetMapping("/qna/list")
    public ModelAndView noticeMain(HttpServletRequest request, ModelAndView mv, @AuthenticationPrincipal UserDetails userDetails) {
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
        int totalCount = qnaService.selectTotalCount(searchMap);


        /* 한 페이지에 보여 줄 게시물 수 */
        int limit = 3;      //얘도 파라미터로 전달받아도 된다.

        /* 한 번에 보여질 페이징 버튼의 갯수 */
        int buttonAmount = 3;

        /* 페이징 처리를 위한 로직 호출 후 페이징 처리에 관한 정보를 담고 있는 인스턴스를 반환받는다. */
        SelectCriteria selectCriteria = null;

        if(searchCondition != null && !"".equals(searchCondition)) {
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
        } else {
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
        }

        List<QnaDTO> lists = qnaService.findqnaList(selectCriteria);

        mv.addObject("qnaList", lists);
        mv.addObject("selectCriteria", selectCriteria);

        mv.setViewName("content/board/qna/qna_list");

        System.out.println("lists = " + lists);

        for(int i = 0; i < lists.size(); i++) {

            if (lists.get(i).getSecretYn().equals("secretPost")) {
                lists.get(i).setTitle("비밀글입니다. 작성자와 관리자만 볼 수 있습니다.");
            }
            // 유저와 관리자는 비밀글이어도 제목이 보이게 해야한다 - > 에러남 -> 구현 안하기로
//            if((lists.get(i).getSecretYn().equals("secretPost")) ||(lists.get(i).getMemberId().equals(((UserImpl)userDetails).getUserId()))) {
//                lists.get(i).getTitle();
//            }
        }
        return mv;
    }



    @GetMapping("/qna/detail/{number}")
    public ModelAndView qnaDetail (@PathVariable String number, ModelAndView mv, RedirectAttributes rttr, @AuthenticationPrincipal UserDetails user) {

        QnaDTO detailPage = qnaService.qnaDetail(number);
        System.out.println("detailPage = " + detailPage);
        System.out.println("user = " + user);

        // 글 작성자 본인과 관리자라면 문의하기 게시글 상세페이지를 볼 수 있다
        if(((UserImpl)user).getUserId().equals(detailPage.getMemberId()) || (user.getAuthorities()+"").equals("[ROLE_ADMIN]")){

            mv.addObject("detail", detailPage);
            mv.setViewName("/content/board/qna/qna_detail");
        } else {

            rttr.addFlashAttribute("message", "secretMsg");
            /* 불일치시 어떻게 할것인가? 로그인한 다른 유저는 되는데*/
            mv.setViewName("redirect:/board/qna/list");
        }
        return mv;
    }


    @GetMapping("/qna/create")
    public String qnaCreateForm() {

        return "content/board/qna/qna_create";
    }

    @PostMapping("/qna/create")
    public String qnaCreate(@ModelAttribute QnaDTO board, RedirectAttributes rttr, @AuthenticationPrincipal UserDetails userDetails) {

        board.setMemberId(((UserImpl)userDetails).getUserId());

        System.out.println(board);

        rttr.addFlashAttribute("message", "createSuccess");

        qnaService.insertQna(board);

        return "redirect:/board/qna/list";
    }

    @GetMapping("/qna/delete/{number}")
    public String qnaDelete(@ModelAttribute QnaDTO board, RedirectAttributes rttr, @AuthenticationPrincipal UserDetails userDetails) {

        board.setMemberId(((UserImpl)userDetails).getUserId());

        rttr.addFlashAttribute("message", "deleteSuccess");

        qnaService.deleteQna(board);

        return "redirect:/board/qna/list";
    }

    @GetMapping("/qna/update/{number}")
    public String qnaUpdateForm(@PathVariable String number, Model model) {

        QnaDTO qna = qnaService.selectQnaDetail(number);

        model.addAttribute("qna", qna);

        return "content/board/qna/qna_update";
    }

    @PostMapping("/qna/update")
    public String qnaUpdate(@ModelAttribute QnaDTO notice, RedirectAttributes rttr, @AuthenticationPrincipal UserDetails userDetails) {

        notice.setMemberId(((UserImpl)userDetails).getUserId());

        System.out.println("여기서 떠야함" + notice);

        qnaService.updateQna(notice);

        rttr.addFlashAttribute("message", "updateSuccess");

        return "redirect:/board/qna/list";
    }

}