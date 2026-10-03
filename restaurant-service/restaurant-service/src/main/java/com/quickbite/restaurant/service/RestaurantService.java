package com.quickbite.restaurant.service;

import com.quickbite.restaurant.entity.Restaurant;
import com.quickbite.restaurant.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public List<Restaurant> getAll() {
        return repository.findAll();
    }

    public Restaurant getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found: " + id));
    }

    public Restaurant create(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    public Restaurant update(Long id, Restaurant input) {
        Restaurant restaurant = getById(id);
        restaurant.setName(input.getName());
        restaurant.setAddress(input.getAddress());
        restaurant.setCity(input.getCity());
        restaurant.setPhone(input.getPhone());
        restaurant.setAvailable(input.getAvailable());
        return repository.save(restaurant);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
