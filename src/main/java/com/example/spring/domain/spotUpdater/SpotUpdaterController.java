package com.example.spring.domain.spotUpdater;


import com.example.spring.global.apiResponse.ApiResponse;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spot-updater")
@RequiredArgsConstructor
public class SpotUpdaterController {
    private final SpotUpdaterService spotUpdaterService;
    @PutMapping()
    public ApiResponse<Boolean> updateSpot(@NotBlank @RequestParam String location,@NotBlank @RequestParam String keyword) {
        spotUpdaterService.updateSpot(location, keyword);
        return ApiResponse.onSuccess(Boolean.TRUE);
    }
}
