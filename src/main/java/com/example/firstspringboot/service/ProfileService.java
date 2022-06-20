package com.example.firstspringboot.service;

import com.example.firstspringboot.entity.Profile;

import java.util.List;

public interface ProfileService {
    Profile findById(Integer id);
    List<Profile> findAll();
    void save(Profile profile);
    void update(Profile profile, Integer id);
    void delete(Integer id);
}
