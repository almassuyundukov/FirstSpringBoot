package com.example.firstspringboot.service;

import com.example.firstspringboot.entity.Profile;
import com.example.firstspringboot.exception.ProfileNotFoundException;
import com.example.firstspringboot.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    @Override
    public Profile findById(Integer id) {
        return profileRepository.findById(id)
                .orElseThrow(() ->
                        new ProfileNotFoundException("Profile with this id " + id + " not found"));
    }

    @Override
    public List<Profile> findAll() {
        List<Profile> profiles = profileRepository.findAll();
        if (profiles.isEmpty()) throw new ProfileNotFoundException("Profile is not found in database");
        return profiles;
    }

    @Override
    public void save(Profile profile) {
        profileRepository.save(profile);
    }

    @Override
    public void update(Profile profile, Integer id) {
        Profile profileById = findById(id);
        profileById.setName(profile.getName())
                .setQuestions(profile.getQuestions());
        profileRepository.save(profileById);
    }

    @Override
    public void delete(Integer id) {
        Profile profile = findById(id);
        profileRepository.delete(profile);
    }
}
