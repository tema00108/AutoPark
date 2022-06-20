package by.incubator.application.service;

import by.incubator.application.entity.Order;
import by.incubator.application.infrastructure.core.annotations.Autowired;
import by.incubator.application.infrastructure.core.annotations.InitMethod;
import by.incubator.application.infrastructure.orm.EntityManager;

import java.util.List;
import java.util.Optional;

public class OrderService {
    @Autowired
    EntityManager entityManager;

    @InitMethod
    public void init() { }

    public Order get(Long id) {
        return entityManager.get(id, Order.class).orElse(null);
    }

    public List<Order> getAll() {
        return entityManager.getAll(Order.class);
    }

    public Long save(Order order) {
        return entityManager.save(order);
    }

    public Order delete(Long id) {
        return entityManager.delete(id, Order.class).orElse(null);
    }
}
