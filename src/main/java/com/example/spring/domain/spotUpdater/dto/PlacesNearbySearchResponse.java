package com.example.spring.domain.spotUpdater.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
        private String formatted_address;
        private Double rating;
        private Integer user_ratings_total;

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
