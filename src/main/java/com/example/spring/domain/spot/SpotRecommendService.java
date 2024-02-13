package com.example.spring.domain.spot;

import com.example.spring.domain.spot.domain.Spot;
import com.example.spring.domain.spot.dto.RecommendResponseDTO;
import com.example.spring.domain.spot.enums.SpotArea;
import com.example.spring.domain.spot.enums.SpotType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SpotRecommendService {
    private final SpotRepository spotRepository;
    private static final Double MIN_RATING = 3.5;
    public RecommendResponseDTO.SpotListDTO recommendSpots(String location) {
        SpotArea spotArea = SpotArea.findByKey(location);
        List<Spot> spotList = recommendCasualDateCourse(spotArea);
        return SpotConverter.spotListToSpotDTOList(spotList);
    }

    private List<Spot> recommendCasualDateCourse(SpotArea spotArea) {
        List<Spot> spotList = new ArrayList<>();
        spotList.add(recommendRestaurant(spotArea));
        spotList.add(recommendCafe(spotArea));
        spotList.add(recommendBar(spotArea));
        return spotList;
    }

    private Spot recommendBar(SpotArea spotArea) {
        List<Spot> spots = spotRepository.findSpots(spotArea, SpotType.BAR, MIN_RATING);
        return spots.get(new Random().nextInt(spots.size()));
    }

    private Spot recommendCafe(SpotArea spotArea) {
        List<Spot> spots = spotRepository.findSpots(spotArea, SpotType.CAFE, MIN_RATING);
        return spots.get(new Random().nextInt(spots.size()));
    }

    private Spot recommendRestaurant(SpotArea spotArea) {
        List<Spot> spots = spotRepository.findSpots(spotArea, SpotType.RESTAURANT, MIN_RATING);
        return spots.get(new Random().nextInt(spots.size()));
    }

}
