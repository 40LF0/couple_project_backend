package com.example.spring.domain.spotUpdater.enums;

import com.example.spring.global.apiResponse.code.status.ErrorStatus;
import com.example.spring.global.apiResponse.exception.GeneralException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SpotType {
    맛집("맛집"),
    카페("카페"),
    술집("술집");

    private final String key;
    public static SpotType findByKey(String key) {
        for (SpotType type : values()) {
            if (type.getKey().equals(key)) {
                return type;
            }
        }
        throw new GeneralException(ErrorStatus.CATEGORY_NOT_FOUND);
    }
}
