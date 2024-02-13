package com.example.spring.domain.spot;

import com.example.spring.domain.spot.domain.Spot;
import com.example.spring.domain.spot.dto.PlacesNearbySearchResponse;
import com.example.spring.domain.spot.dto.RecommendResponseDTO;
import com.example.spring.domain.spot.enums.SpotArea;
import com.example.spring.domain.spot.enums.SpotType;

import java.util.List;

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

    public static RecommendResponseDTO.SpotListDTO spotListToSpotDTOList(List<Spot> spotList) {
        List<RecommendResponseDTO.SpotDTO> spotDTOS = spotList.stream().map(SpotConverter::SpotToSpotDTO).toList();
        return RecommendResponseDTO.SpotListDTO.builder()
                .spotDTOList(spotDTOS)
                .build();
    }

    public static RecommendResponseDTO.SpotDTO SpotToSpotDTO(Spot spot) {
        return RecommendResponseDTO.SpotDTO.builder()
                .spotId(spot.getSpotId())
                .name(spot.getName())
                .lat(spot.getLat())
                .lng(spot.getLng())
                .address(spot.getAddress())
                .rating(spot.getRating())
                .areaName(spot.getSpotArea().getKey())
                .spotType(spot.getSpotType().getKey())
                .build();
    }
}
