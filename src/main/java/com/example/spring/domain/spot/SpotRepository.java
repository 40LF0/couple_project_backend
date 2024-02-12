package com.example.spring.domain.spot;

import com.example.spring.domain.spot.domain.Spot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpotRepository extends JpaRepository<Spot,String> {
}
