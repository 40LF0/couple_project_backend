package com.example.spring.domain.spotUpdater.enums;

import com.example.spring.global.apiResponse.code.status.ErrorStatus;
import com.example.spring.global.apiResponse.exception.GeneralException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum SpotArea {
    이태원("이태원","37.534664,126.994307");
    private final String key;
    private final String locationValue;

    public static SpotArea findByKey(String key) {
        for (SpotArea area : values()) {
            if (area.getKey().equals(key)) {
                return area;
            }
        }
        throw new GeneralException(ErrorStatus.REGION_NOT_FOUND);
    }
    public static List<SpotArea> getAll() {
        return Arrays.stream(values()).collect(Collectors.toList());
    }
}
