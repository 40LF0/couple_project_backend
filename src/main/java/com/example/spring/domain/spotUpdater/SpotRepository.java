package com.example.spring.domain.spotUpdater;

import com.example.spring.domain.spotUpdater.domain.Spot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpotRepository extends JpaRepository<Spot,String> {
}
