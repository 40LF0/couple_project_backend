package com.example.spring.domain.dateSpot.domain;

import com.example.spring.global.baseEntity.BaseEntity;
import com.example.spring.domain.dateSpot.enums.Category;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DateSpotCategory extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dateSpotCategoryId;

    private Category category;

    @OneToMany (mappedBy = "dateSpotCategory", cascade = CascadeType.ALL)
    private List<DateSpot> dateSpotList = new ArrayList<>();
}
