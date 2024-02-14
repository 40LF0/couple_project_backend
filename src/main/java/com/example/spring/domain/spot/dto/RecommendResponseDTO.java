package com.example.spring.domain.spot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class RecommendResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SpotListDTO{
        List<SpotDTO> spotDTOList;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SpotDTO{
        private String spotId;
        private String name;
        private Double lat;
        private Double lng;
        private String address;
        private Double rating;
        private String areaName;
        private String spotType;
    }
}
