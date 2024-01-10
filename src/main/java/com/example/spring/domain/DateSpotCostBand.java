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
public class DateSpotCostBand extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dateSpotCostBandId;

    private Long cost;

    @OneToMany (mappedBy = "dateSpotCostBand", cascade = CascadeType.ALL)
    private List<DateSpot> dateSpotList = new ArrayList<>();
}
