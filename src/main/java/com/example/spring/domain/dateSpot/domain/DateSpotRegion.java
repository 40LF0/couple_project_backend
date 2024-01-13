package com.example.spring.domain.dateSpot.domain;

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
public class DateSpotRegion extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dateSpotRegionId;

    private String region;

    @OneToMany (mappedBy = "dateSpotRegion", cascade = CascadeType.ALL)
    private List<DateSpot> dateSpotList = new ArrayList<>();
}