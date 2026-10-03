package com.quickbite.order.repository;

import com.quickbite.order.entity.FoodOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<FoodOrder, Long> {
    List<FoodOrder> findByUserId(Long userId);
    List<FoodOrder> findByRestaurantId(Long restaurantId);
}
