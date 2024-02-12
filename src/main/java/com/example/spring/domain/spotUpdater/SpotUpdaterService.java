package com.example.spring.domain.spotUpdater;

import com.example.spring.domain.spotUpdater.dto.PlacesNearbySearchResponse;
import com.example.spring.global.utils.UtilsFunctions;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SpotUpdaterService {
    private final RestTemplate restTemplate;
    @Value("${google.places.api.key}")
    private String apiKey;
    private final String GOOGLE_PLACES_SEARCH_NEARBY_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json";
    private final int RADIUS = 1500;
    @Transactional
    public void updateSpot(String location, String keyword) {
        String url = buildUpdateSpotUrl(location, keyword);
        // ResponseEntity를 PlacesSearchResponse DTO로 변경
        ResponseEntity<PlacesNearbySearchResponse> response = restTemplate.getForEntity(url, PlacesNearbySearchResponse.class);
        PlacesNearbySearchResponse placesSearchResponse = response.getBody();
        String pageToken = placesSearchResponse.getNext_page_token();
        System.out.println("Next Page Token: " + placesSearchResponse.getNext_page_token());
        placesSearchResponse.getResults().forEach(place -> System.out.println("Place Name: " + place.getName()));

        while (pageToken != null) {
            UtilsFunctions.wait(5);
            url = buildUpdateSpotUrlWithNextToken(pageToken);
            response = restTemplate.getForEntity(url, PlacesNearbySearchResponse.class);
            placesSearchResponse = response.getBody();
            pageToken = placesSearchResponse.getNext_page_token();
            System.out.println("Next Page Token: " + placesSearchResponse.getNext_page_token());
            placesSearchResponse.getResults().forEach(place -> System.out.println("Place Name: " + place.getName()));
        }
        System.out.println("END");
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
