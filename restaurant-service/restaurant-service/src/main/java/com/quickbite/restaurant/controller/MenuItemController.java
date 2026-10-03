package com.quickbite.restaurant.controller;

import com.quickbite.restaurant.entity.MenuItem;
import com.quickbite.restaurant.service.MenuItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants/{restaurantId}/menu")
public class MenuItemController {

    private final MenuItemService service;

    public MenuItemController(MenuItemService service) {
        this.service = service;
    }

    @GetMapping
    public List<MenuItem> getMenu(@PathVariable Long restaurantId) {
        return service.getByRestaurant(restaurantId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MenuItem create(@PathVariable Long restaurantId,
                            @Valid @RequestBody MenuItem item) {
        return service.create(restaurantId, item);
    }
}
