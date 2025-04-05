package com.example.demo.dal.repository;

import com.example.demo.dal.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
