package com.quickbite.restaurant.service;

import com.quickbite.restaurant.entity.MenuItem;
import com.quickbite.restaurant.entity.Restaurant;
import com.quickbite.restaurant.repository.MenuItemRepository;
import com.quickbite.restaurant.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {

    private final MenuItemRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    public MenuItemService(MenuItemRepository menuRepository,
                           RestaurantRepository restaurantRepository) {
        this.menuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public List<MenuItem> getByRestaurant(Long restaurantId) {
        if (!restaurantRepository.existsById(restaurantId)) {
            throw new RuntimeException("Restaurant not found: " + restaurantId);
        }
        return menuRepository.findByRestaurantId(restaurantId);
    }

    public MenuItem create(Long restaurantId, MenuItem item) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found: " + restaurantId));

        item.setId(null);
        item.setRestaurant(restaurant);
        return menuRepository.save(item);
    }

    public MenuItem update(Long id, MenuItem input) {
        MenuItem item = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found: " + id));

        item.setName(input.getName());
        item.setDescription(input.getDescription());
        item.setPrice(input.getPrice());
        item.setAvailable(input.getAvailable());
        return menuRepository.save(item);
    }

    public void delete(Long id) {
        menuRepository.deleteById(id);
    }
}
