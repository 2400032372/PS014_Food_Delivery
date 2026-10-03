package com.quickbite.user.service;

import com.quickbite.user.entity.UserProfile;
import com.quickbite.user.repository.UserProfileRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserProfileService {
    private final UserProfileRepository repository;

    public UserProfileService(UserProfileRepository repository) {
        this.repository = repository;
    }

    public List<UserProfile> getAll() {
        return repository.findAll();
    }

    public UserProfile getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
    }

    public UserProfile create(UserProfile user) {
        return repository.save(user);
    }

    public UserProfile update(Long id, UserProfile input) {
        UserProfile user = getById(id);
        user.setName(input.getName());
        user.setEmail(input.getEmail());
        user.setPhone(input.getPhone());
        user.setAddress(input.getAddress());
        return repository.save(user);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
