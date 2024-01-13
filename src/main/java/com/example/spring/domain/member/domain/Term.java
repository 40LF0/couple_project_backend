package com.example.spring.domain.member.domain;

import com.example.spring.global.baseEntity.BaseEntity;
import com.example.spring.domain.member.enums.TermsStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Term extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long termsId;

    private String title;
    private String body;

    @Enumerated(EnumType.STRING)
    private TermsStatus termsStatus;
}
