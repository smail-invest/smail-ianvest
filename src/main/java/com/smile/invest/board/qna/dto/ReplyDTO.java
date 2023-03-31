package com.smile.invest.board.qna.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReplyDTO {

    private String number; // post넘버, fk

    private String memberId; // 댓글 작성자

    private String replyContent; // 댓글 내용

    private String replyDeleteYn; // 댓글 삭제 여부

    private QnaDTO writeDate; // 작성일시

    private long replyNum; // 댓글 넘버





}
