package com.example.spring.domain.member.domain;

import com.example.spring.global.baseEntity.BaseEntity;
import com.example.spring.domain.member.enums.AnswerStatus;
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

    @Enumerated(EnumType.STRING)
    private AnswerStatus answerStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;
}
