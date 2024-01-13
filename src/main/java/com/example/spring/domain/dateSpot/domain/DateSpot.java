package com.example.spring.domain.dateSpot.domain;

import com.example.spring.domain.dateSpot.enums.InOut;
import com.example.spring.global.baseEntity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DateSpot extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dateSpotId;

    private String name;
    private String address;
    private String mapUrl;

    @Enumerated(EnumType.STRING)
    private InOut inOut;

    private int ageBand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dateSpotCategoryId")
    private DateSpotCategory dateSpotCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dateSpotCostBandId")
    private DateSpotCostBand dateSpotCostBand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dateSpotRegionId")
    private DateSpotRegion dateSpotRegion;

    @OneToMany (mappedBy = "dateSpot", cascade = CascadeType.ALL)
    private List<DateSpotImage> dateSpotImageList = new ArrayList<>();
}
