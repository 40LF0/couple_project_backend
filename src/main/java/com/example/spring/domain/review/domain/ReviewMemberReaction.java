package com.example.spring.domain.review.domain;

import com.example.spring.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@IdClass(ReviewMemberReactionId.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ReviewMemberReaction {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewId")
    private Review review;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    private boolean isLiked = false;

    public void enableLike() {
        if(!isLiked){
            review.incrementLikeCount();
        }
        this.isLiked = true;
    }
    public void disableLike(){
        if(isLiked){
            review.decrementLikeCount();
        }
        this.isLiked = false;
    }
}
