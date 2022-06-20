package com.example.firstspringboot.service;

import com.example.firstspringboot.entity.Level;

import java.util.List;

public interface LevelService {

    Level findById(Integer id);
    List<Level> findAll();
    void save(Level level);
    void update(Level level, Integer id);
    void delete(Integer id);
}
