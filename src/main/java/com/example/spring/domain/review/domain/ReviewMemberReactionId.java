package com.example.spring.domain.review.domain;

import com.example.spring.domain.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
public class ReviewMemberReactionId implements Serializable  {
    private Review review;
    private Member member;
}