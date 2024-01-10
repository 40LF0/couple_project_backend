package com.example.spring.domain;

import com.example.spring.domain.common.BaseEntity;
import com.example.spring.domain.enums.gender;
import com.example.spring.domain.enums.status;
import com.example.spring.domain.mapping.member_agree;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String nickname;
    private int age;
    private String address;
    private String profileUrl;

    private int point;
    private int coupon;

    @Enumerated(EnumType.STRING)
    private gender gender;

    @Enumerated(EnumType.STRING)
    private status status;

    private LocalDate inactive_date;

    @OneToMany (mappedBy = "member", cascade = CascadeType.ALL)
    private List<member_agree> memberAgreeList = new ArrayList<>();

    @OneToMany (mappedBy = "member", cascade = CascadeType.ALL)
    private List<review> reviewList = new ArrayList<>();

    @OneToMany (mappedBy = "member", cascade = CascadeType.ALL)
    private List<coupon> couponList = new ArrayList<>();

    @OneToMany (mappedBy = "member", cascade = CascadeType.ALL)
    private List<point_history> pointHistoryList = new ArrayList<>();

    @OneToMany (mappedBy = "member", cascade = CascadeType.ALL)
    private List<recommendation> recommendationList = new ArrayList<>();

    @OneToMany (mappedBy = "member", cascade = CascadeType.ALL)
    private List<qna> qnaList = new ArrayList<>();
}
