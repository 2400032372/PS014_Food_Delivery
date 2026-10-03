package com.quickbite.order.service;

import com.quickbite.order.dto.PaymentRequest;
import com.quickbite.order.dto.PaymentResponse;
import com.quickbite.order.entity.FoodOrder;
import com.quickbite.order.entity.OrderStatus;
import com.quickbite.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final RestClient restClient;

    @Value("${payment.service.url}")
    private String paymentServiceUrl;

    @Value("${payment.service.payment-path}")
    private String paymentPath;

    public OrderService(OrderRepository repository, RestClient restClient) {
        this.repository = repository;
        this.restClient = restClient;
    }

    public List<FoodOrder> getAll() {
        return repository.findAll();
    }

    public FoodOrder getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found: " + id));
    }

    public List<FoodOrder> getByUser(Long userId) {
        return repository.findByUserId(userId);
    }

    public List<FoodOrder> getByRestaurant(Long restaurantId) {
        return repository.findByRestaurantId(restaurantId);
    }

    public FoodOrder create(FoodOrder order) {
        order.setId(null);
        order.setStatus(OrderStatus.CREATED);
        return repository.save(order);
    }

    public FoodOrder updateStatus(Long id, OrderStatus status) {
        FoodOrder order = getById(id);
        order.setStatus(status);
        return repository.save(order);
    }

    public FoodOrder pay(Long id) {
        FoodOrder order = getById(id);

        order.setStatus(OrderStatus.PAYMENT_PENDING);
        repository.save(order);

        try {
            PaymentResponse response = restClient.post()
                    .uri(paymentServiceUrl + paymentPath)
                    .body(new PaymentRequest(
                            order.getId(),
                            order.getUserId(),
                            order.getTotalAmount()))
                    .retrieve()
                    .body(PaymentResponse.class);

            if (response != null && response.success()) {
                order.setStatus(OrderStatus.PAID);
            } else {
                order.setStatus(OrderStatus.REJECTED);
            }
        } catch (Exception ex) {
            order.setStatus(OrderStatus.REJECTED);
        }

        return repository.save(order);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
