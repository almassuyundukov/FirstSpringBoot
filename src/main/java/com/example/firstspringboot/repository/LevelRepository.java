package com.example.firstspringboot.repository;

import com.example.firstspringboot.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LevelRepository extends JpaRepository<Level, Integer> {
}
