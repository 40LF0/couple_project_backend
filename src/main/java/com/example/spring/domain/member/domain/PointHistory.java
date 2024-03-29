package com.example.spring.domain.member.domain;

import com.example.spring.global.baseEntity.BaseEntity;
import com.example.spring.domain.member.enums.PlusMinus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PointHistory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pointHistoryId;

    private int point;

    @Enumerated(EnumType.STRING)
    private PlusMinus plusMinus;

    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;
}
