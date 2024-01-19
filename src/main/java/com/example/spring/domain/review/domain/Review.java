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

    public List<String> fetchSpotList(){
        List<String> spotList = new ArrayList<>();
        if(spot1 != null && !spot1.isBlank()){
            spotList.add(spot1);
        }
        if(spot2 != null && !spot2.isBlank()){
            spotList.add(spot2);
        }
        if(spot3 != null && !spot3.isBlank()){
            spotList.add(spot3);
        }
        return spotList;
    }
    public List<String> fetchImageUrlList(){
        List<String> imageLists = new ArrayList<>();
        for (ReviewImage image : reviewImageList) {
            if(image.getImgUrl() != null && !image.getImgUrl().isBlank()){
                imageLists.add(image.getImgUrl());
            }
        }
        return imageLists;
    }
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
        if(reviewImageList != null){
            reviewImageList.clear();
        }
        reviewImageList = uniqueImages.stream()
                .map(imageUrl -> ReviewImage.builder()
                        .imgUrl(imageUrl)
                        .review(this)
                        .build())
                .collect(Collectors.toList());
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateBody(String body) {
        this.body = body;
    }
}
