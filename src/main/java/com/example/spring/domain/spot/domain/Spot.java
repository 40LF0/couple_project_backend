package com.example.spring.domain.spot.domain;

import com.example.spring.domain.spot.enums.SpotArea;
import com.example.spring.domain.spot.enums.SpotType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Spot {
    @Id
    private String spotId;
    private String name;
    private Double lat;
    private Double lng;
    private String address;
    private Double rating;
    @Enumerated(EnumType.STRING)
    private SpotArea spotArea;
    @Enumerated(EnumType.STRING)
    private SpotType spotType;
}
