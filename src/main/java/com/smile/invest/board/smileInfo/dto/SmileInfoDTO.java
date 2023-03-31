package com.smile.invest.board.smileInfo.dto;

import com.smile.invest.member.dto.MemberDTO;
//import com.smile.invest.board.smileInfo.dto.BoardCategoryDTO;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SmileInfoDTO {

    private String number;                  // 게시글 번호

    private String title;                   // 게시글 제목

    private Date writeDate;                 // 작성일시

    private String viewCount;               // 조회수

    private String memberId;               // 작성자 아이디

    private String content;                 // 게시글 내용

    private String boardCode;               // 게시판 코드

    private String deleteYn;                // 삭제 여부

}
