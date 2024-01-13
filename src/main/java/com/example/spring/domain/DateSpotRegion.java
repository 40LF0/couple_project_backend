package com.example.spring.domain;

import com.example.spring.domain.common.BaseEntity;
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