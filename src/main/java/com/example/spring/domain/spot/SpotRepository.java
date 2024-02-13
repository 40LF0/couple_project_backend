package com.example.spring.domain.spot;

import com.example.spring.domain.spot.domain.Spot;
import com.example.spring.domain.spot.enums.SpotArea;
import com.example.spring.domain.spot.enums.SpotType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpotRepository extends JpaRepository<Spot,String> {
    default List<Spot> findSpots(SpotArea spotArea, SpotType spotType, Double rating) {
        return findBySpotAreaAndSpotTypeAndRatingGreaterThanEqual(spotArea, spotType, rating);
    }
    List<Spot> findBySpotAreaAndSpotTypeAndRatingGreaterThanEqual(SpotArea spotArea, SpotType spotType, Double rating);
}
