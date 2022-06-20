package by.incubator.application.vehicle.parser.impl;

import by.incubator.application.entity.Order;
import by.incubator.application.entity.Vehicle;
import by.incubator.application.infrastructure.core.annotations.Autowired;
import by.incubator.application.service.OrderService;
import by.incubator.application.vehicle.parser.ParserBreakingInterface;

import java.util.Optional;

public class ParserBreakingFromDB implements ParserBreakingInterface {
    @Autowired
    OrderService orderService;

    @Override
    public void writeOrder(long vehicleId, String defect, int breakingAmount) {
        orderService.save(new Order(vehicleId, defect, breakingAmount));
    }

    @Override
    public Optional<Order> findOrder(long id) {
        return Optional.ofNullable(orderService.get(id));
    }

    @Override
    public void deleteOrder(Vehicle vehicle) {
        orderService.delete(vehicle.getId());
    }
}
