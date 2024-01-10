package com.example.spring.domain;

import com.example.spring.domain.common.BaseEntity;
import com.example.spring.domain.enums.category;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class date_spot_category extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private category category;

    @OneToMany (mappedBy = "date_spot", cascade = CascadeType.ALL)
    private List<date_spot> dateSpotList = new ArrayList<>();
}
