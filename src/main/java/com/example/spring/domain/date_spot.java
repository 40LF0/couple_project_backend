package com.example.spring.domain;

import com.example.spring.domain.common.BaseEntity;
import com.example.spring.domain.enums.in_out;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class date_spot extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String map_url;

    @Enumerated(EnumType.STRING)
    private in_out in_out;

    private int age_band;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private date_spot_category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "costBand_id")
    private date_spot_costBand costBand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private date_spot_region region;

    @OneToMany (mappedBy = "member", cascade = CascadeType.ALL)
    private List<date_spot_image> dateSpotImageList = new ArrayList<>();
}
