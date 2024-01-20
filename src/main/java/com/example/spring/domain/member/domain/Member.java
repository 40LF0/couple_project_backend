package com.example.spring.domain.member.domain;

import com.example.spring.domain.dateSpot.domain.Recommendation;
import com.example.spring.domain.qna.domain.Qna;
import com.example.spring.domain.review.domain.Review;
import com.example.spring.global.baseEntity.BaseEntity;
import com.example.spring.domain.member.enums.Gender;
import com.example.spring.domain.member.enums.Status;
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
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String name;
    private String profileUrl;

    private int point = 0;
    private int coupon = 0;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDate inactiveDate;

    @OneToMany (mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberAgree> memberAgreeList = new ArrayList<>();

    @OneToMany (mappedBy = "member", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany (mappedBy = "member", cascade = CascadeType.ALL)
    private List<Coupon> couponList = new ArrayList<>();

    @OneToMany (mappedBy = "member", cascade = CascadeType.ALL)
    private List<PointHistory> pointHistoryList = new ArrayList<>();

    @OneToMany (mappedBy = "member", cascade = CascadeType.ALL)
    private List<Recommendation> recommendationList = new ArrayList<>();

    @OneToMany (mappedBy = "member", cascade = CascadeType.ALL)
    private List<Qna> qnaList = new ArrayList<>();
}
