package by.incubator.application.service;

import by.incubator.application.entity.Vehicle;
import by.incubator.application.infrastructure.core.annotations.Autowired;
import by.incubator.application.infrastructure.core.annotations.InitMethod;
import by.incubator.application.infrastructure.orm.EntityManager;

import java.util.List;

public class VehicleService {
    @Autowired
    EntityManager entityManager;

    @InitMethod
    public void init() { }

    public Vehicle get(Long id) {
        return entityManager.get(id, Vehicle.class).orElse(null);
    }

    public List<Vehicle> getAll() {
        return entityManager.getAll(Vehicle.class);
    }

    public Long save(Vehicle vehicle) {
        return entityManager.save(vehicle);
    }
}
