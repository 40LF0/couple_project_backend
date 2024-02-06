package com.example.spring.domain.member.domain;

import com.example.spring.domain.dateSpot.domain.Recommendation;
import com.example.spring.domain.member.enums.Role;
import com.example.spring.domain.review.domain.Review;
import com.example.spring.global.baseEntity.BaseEntity;
import com.example.spring.domain.member.enums.Gender;
import com.example.spring.domain.member.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "members")
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

    @Builder.Default
    private int point = 0;
    @Builder.Default
    private int coupon = 0;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String providerId;

    private String provider;

    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

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

    public void updateName(String name){
        this.name = name;
    }

    public void updateProfileImage(String imageUrl){
        this.profileUrl = imageUrl;
    }
}
