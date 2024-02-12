package com.example.spring.global.config.initializer;

import com.example.spring.domain.spot.SpotUpdaterService;
import com.example.spring.domain.spot.enums.SpotArea;
import com.example.spring.domain.spot.enums.SpotType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Configuration
@RequiredArgsConstructor
public class AppInitializer {

    @Autowired
    private final SpotUpdaterService spotUpdaterService;

    @Bean
    CommandLineRunner initSpotInformation() {
        return args -> {
            List<SpotArea> spotAreaList = SpotArea.getAll();
            List<SpotType> spotTypeList = SpotType.getAll();

            // 모든 SpotArea와 SpotType 조합에 대해 병렬로 updateSpot을 실행합니다.
            List<CompletableFuture<Void>> futures = spotAreaList.parallelStream().flatMap(spotArea ->
                    spotTypeList.stream().map(spotType ->
                            CompletableFuture.runAsync(() ->
                                    spotUpdaterService.updateSpot(spotArea.getKey(), spotType.getKey())
                            )
                    )
            ).toList();

            // 모든 CompletableFuture가 완료될 때까지 대기합니다.
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        };
    }
}
