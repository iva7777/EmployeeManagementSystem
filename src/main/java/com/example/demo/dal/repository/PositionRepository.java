package com.example.demo.dal.repository;

import com.example.demo.dal.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {
}
