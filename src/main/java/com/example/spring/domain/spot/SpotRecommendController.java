package com.example.spring.domain.spot;

import com.example.spring.domain.spot.dto.RecommendResponseDTO;
import com.example.spring.global.apiResponse.ApiResponse;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spot-recommender")
@RequiredArgsConstructor
public class SpotRecommendController {
    private final SpotRecommendService spotRecommendService;
    @GetMapping()
    public ApiResponse<RecommendResponseDTO.SpotListDTO> recommendSpots(@NotBlank @RequestParam String location){
        RecommendResponseDTO.SpotListDTO spotListDTO = spotRecommendService.recommendSpots(location);
        return ApiResponse.onSuccess(spotListDTO);
    }
}
