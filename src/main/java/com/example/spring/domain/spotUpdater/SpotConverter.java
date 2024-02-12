package com.example.spring.domain.spotUpdater;

import com.example.spring.domain.spotUpdater.domain.Spot;
import com.example.spring.domain.spotUpdater.dto.PlacesNearbySearchResponse;
import com.example.spring.domain.spotUpdater.enums.SpotArea;
import com.example.spring.domain.spotUpdater.enums.SpotType;

public class SpotConverter {

    public static Spot placeResultToSpotEntity(PlacesNearbySearchResponse.PlaceResult place, SpotArea spotArea, SpotType spotType){
        return Spot.builder()
                .spotId(place.getPlace_id())
                .name(place.getName())
                .lat(place.getGeometry().getLocation().getLat())
                .lng(place.getGeometry().getLocation().getLng())
                .address(place.getVicinity())
                .rating(place.getRating())
                .spotArea(spotArea)
                .spotType(spotType)
                .build();
    }
}
