package by.incubator.application.vehicle.parser;

import by.incubator.application.entity.Order;
import by.incubator.application.entity.Vehicle;

import java.util.Optional;
import java.util.OptionalInt;

public interface ParserBreakingInterface {
    void writeOrder(long id, String defect, int amount);
    Optional<Order> findOrder(long id);
    void deleteOrder(Vehicle vehicle);
}
