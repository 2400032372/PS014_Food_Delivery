package com.quickbite.restaurant.controller;

import com.quickbite.restaurant.entity.MenuItem;
import com.quickbite.restaurant.service.MenuItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menu")
public class MenuManagementController {

    private final MenuItemService service;

    public MenuManagementController(MenuItemService service) {
        this.service = service;
    }

    @PutMapping("/{id}")
    public MenuItem update(@PathVariable Long id,
                           @Valid @RequestBody MenuItem item) {
        return service.update(id, item);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
