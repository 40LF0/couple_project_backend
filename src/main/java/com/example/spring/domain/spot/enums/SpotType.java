package com.example.spring.domain.spot.enums;

import com.example.spring.global.apiResponse.code.status.ErrorStatus;
import com.example.spring.global.apiResponse.exception.GeneralException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum SpotType {
    RESTAURANT("맛집"),
    CAFE("카페"),
    BAR("술집");

    private final String key;
    public static SpotType findByKey(String key) {
        for (SpotType type : values()) {
            if (type.getKey().equals(key)) {
                return type;
            }
        }
        throw new GeneralException(ErrorStatus.CATEGORY_NOT_FOUND);
    }
    public static List<SpotType> getAll() {
        return Arrays.stream(values()).collect(Collectors.toList());
    }
}
