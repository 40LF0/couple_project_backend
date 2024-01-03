package com.example.spring.domain;

import com.example.spring.domain.common.BaseEntity;
import com.example.spring.domain.enums.plus_minus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class point_history extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int point;

    @Enumerated(EnumType.STRING)
    private plus_minus plus_minus;

    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private member member;
}
