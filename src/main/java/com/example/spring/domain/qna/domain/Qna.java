package com.example.spring.domain.qna.domain;

import com.example.spring.domain.member.domain.Member;
import com.example.spring.global.baseEntity.BaseEntity;
import com.example.spring.domain.qna.enums.AnswerStatus;
//import com.example.spring.domain.member.enums.AnswerStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Qna extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qnaId;

    private String title;
    private String body;
    private String answer;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private AnswerStatus answerStatus = AnswerStatus.WAITING;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;
}
