package com.example.firstspringboot.repository;

import com.example.firstspringboot.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {
}
