package com.example.spring.domain;
import com.example.spring.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String body;

    private String spot1;
    private String spot2;
    private String spot3;

    private int heart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private member member;

    @OneToMany (mappedBy = "member", cascade = CascadeType.ALL)
    private List<review_image> reviewImageList = new ArrayList<>();
}
