package com.example.firstspringboot.service;

import com.example.firstspringboot.entity.Level;
import com.example.firstspringboot.exception.AnswerNotFoundException;
import com.example.firstspringboot.exception.LevelNotFoundException;
import com.example.firstspringboot.repository.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelServiceImpl implements LevelService{

    private final LevelRepository levelRepository;

    @Autowired
    public LevelServiceImpl(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    @Override
    public Level findById(Integer id) {
        return levelRepository.findById(id).orElseThrow(() -> new LevelNotFoundException("Level with this id"+ id + "not found"));
    }

    @Override
    public List<Level> findAll() {
        List<Level> levels = levelRepository.findAll();
        if (levels.isEmpty()) throw new LevelNotFoundException("Levels is empty in database");
        return levels;
    }

    @Override
    public void save(Level level) {
        levelRepository.save(level);
    }

    @Override
    public void update(Level level, Integer id) {
        Level levelById = findById(id);
        levelById.setName(level.getName())
                .setQuestions(level.getQuestions());
        levelRepository.save(levelById);
    }

    @Override
    public void delete(Integer id) {
        Level levelById = findById(id);
        levelRepository.delete(levelById);
    }
}
