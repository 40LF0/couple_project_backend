package com.example.spring.domain.spot;

import com.example.spring.domain.spot.domain.Spot;
import com.example.spring.domain.spot.dto.PlacesNearbySearchResponse;
import com.example.spring.domain.spot.enums.SpotArea;
import com.example.spring.domain.spot.enums.SpotType;
import com.example.spring.global.utils.UtilsFunctions;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SpotUpdaterService {
    private final SpotRepository spotRepository;
    private final RestTemplate restTemplate;
    @Value("${google.places.api.key}")
    private String apiKey;
    private static final String GOOGLE_PLACES_SEARCH_NEARBY_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json";
    private static final int RADIUS = 1500;
    @Transactional
    public void updateSpot(String location, String keyword) {
        SpotArea spotArea = SpotArea.findByKey(location);
        SpotType spotType = SpotType.findByKey(keyword);

        String url = buildUpdateSpotUrl(spotArea.getLocationValue(), spotType.getKey());
        ResponseEntity<PlacesNearbySearchResponse> response = restTemplate.getForEntity(url, PlacesNearbySearchResponse.class);
        updateSpotInfo(response.getBody(), spotArea, spotType);

        // Check if there is a next page of results (Google Places API can paginate results).
        while (response.getBody().getNext_page_token() != null) {
            // Wait for a short period before making another request to avoid hitting Google Places API rate limits.
            // This is necessary because making requests too frequently can lead to access errors.
            UtilsFunctions.wait(5); // Wait for 10 seconds
            url = buildUpdateSpotUrlWithNextToken(response.getBody().getNext_page_token());
            response = restTemplate.getForEntity(url, PlacesNearbySearchResponse.class);
            updateSpotInfo(response.getBody(), spotArea, spotType);
        }
    }

    private void updateSpotInfo(PlacesNearbySearchResponse response, SpotArea spotArea, SpotType spotType) {
        List<Spot> spots = response.getResults().stream()
                .map(place -> SpotConverter.placeResultToSpotEntity(place, spotArea, spotType))
                .toList();
        spotRepository.saveAll(spots);
    }

    private String buildUpdateSpotUrl(String location, String keyword) {
        return GOOGLE_PLACES_SEARCH_NEARBY_URL
                + "?location=" + location
                + "&keyword=" + keyword
                + "&radius=" + RADIUS
                + "&language=" + "ko"
                + "&key=" + apiKey;
    }
    private String buildUpdateSpotUrlWithNextToken(String pageToken){
        return GOOGLE_PLACES_SEARCH_NEARBY_URL
                + "?language=" + "ko"
                + "&key=" + apiKey
                + "&pagetoken=" + pageToken;
    }

}
