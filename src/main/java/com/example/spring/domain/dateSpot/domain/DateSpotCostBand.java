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
public class DateSpotCostBand extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dateSpotCostBandId;

    private Long cost;

    @OneToMany (mappedBy = "dateSpotCostBand", cascade = CascadeType.ALL)
    private List<DateSpot> dateSpotList = new ArrayList<>();
}
