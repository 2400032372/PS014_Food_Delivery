package com.quickbite.order.controller;

import com.quickbite.order.entity.FoodOrder;
import com.quickbite.order.entity.OrderStatus;
import com.quickbite.order.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public List<FoodOrder> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public FoodOrder getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/user/{userId}")
    public List<FoodOrder> getByUser(@PathVariable Long userId) {
        return service.getByUser(userId);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public List<FoodOrder> getByRestaurant(@PathVariable Long restaurantId) {
        return service.getByRestaurant(restaurantId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FoodOrder create(@Valid @RequestBody FoodOrder order) {
        return service.create(order);
    }

    @PutMapping("/{id}/status")
    public FoodOrder updateStatus(@PathVariable Long id,
                                  @RequestParam OrderStatus status) {
        return service.updateStatus(id, status);
    }

    @PostMapping("/{id}/pay")
    public FoodOrder pay(@PathVariable Long id) {
        return service.pay(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
