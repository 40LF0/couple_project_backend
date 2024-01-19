package com.example.spring.domain.review.domain;
import com.example.spring.domain.member.domain.Member;
import com.example.spring.global.apiResponse.code.status.ErrorStatus;
import com.example.spring.global.apiResponse.exception.GeneralException;
import com.example.spring.global.baseEntity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;
import java.util.stream.Collectors;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    private String title;
    private String body;

    private String spot1;
    private String spot2;
    private String spot3;

    private int heart = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    @OneToMany (mappedBy = "review", cascade = CascadeType.ALL)
    private List<ReviewImage> reviewImageList = new ArrayList<>();

    public void updateSpots(List<String> spots){
        Set<String> uniqueSpots = new HashSet<>(spots);

        if(uniqueSpots.isEmpty() || uniqueSpots.size() > 3) {
            throw new GeneralException(ErrorStatus.REVIEW_SPOT_QUANTITY_ERROR);
        }

        Iterator<String> spotIterator = uniqueSpots.iterator();

        spot1 = spotIterator.hasNext() ? spotIterator.next() : null;
        spot2 = spotIterator.hasNext() ? spotIterator.next() : null;
        spot3 = spotIterator.hasNext() ? spotIterator.next() : null;
    }
    public void updateReviewImages(List<String> reviewImages){
        Set<String> uniqueImages = new HashSet<>(reviewImages);

        if(uniqueImages.isEmpty() || uniqueImages.size() > 5) {
            throw new GeneralException(ErrorStatus.REVIEW_IMAGE_QUANTITY_ERROR);
        }

        reviewImageList = uniqueImages.stream()
                .map(imageUrl -> ReviewImage.builder().imgUrl(imageUrl).build())
                .collect(Collectors.toList());
    }
}
