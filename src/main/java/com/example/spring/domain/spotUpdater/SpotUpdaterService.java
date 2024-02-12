package com.example.spring.domain.spotUpdater;

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
    private final int RADIUS = 5000;
    @Transactional
    public void updateSpot(String location, String type) {
        String url = buildUpdateSpotUrl(location, type);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        System.out.println(response.getBody());
    }

    private String buildUpdateSpotUrl(String location, String type) {
        return GOOGLE_PLACES_SEARCH_NEARBY_URL
                + "?location=" + location
                + "&radius=" + RADIUS
                + "&type=" + type
                + "&key=" + apiKey;
    }
}
