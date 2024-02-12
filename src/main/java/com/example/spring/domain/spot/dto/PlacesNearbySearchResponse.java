package com.example.spring.domain.spot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlacesNearbySearchResponse {
    private List<PlaceResult> results;
    private String next_page_token;
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PlaceResult {
        private String name;
        private String place_id;
        private Geometry geometry;
        private String vicinity;
        private Double rating;

    }
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Geometry {
        private Location location;
    }
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Location {
        private Double lat;
        private Double lng;
    }
}
