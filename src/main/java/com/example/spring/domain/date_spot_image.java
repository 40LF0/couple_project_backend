package com.example.spring.domain;
import com.example.spring.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class date_spot_image extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String img_url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dateSpot_id")
    private date_spot date_spot;
}
