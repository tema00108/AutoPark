package by.incubator.application.infrastructure.vehicleService.impl;

import by.incubator.application.entity.Vehicle;
import by.incubator.application.infrastructure.vehicleService.Fixer;

import java.util.HashMap;
import java.util.Map;

public class BadMechanicService implements Fixer {
    @Override
    public Map<String, Integer> detectBreaking(Vehicle vehicle) {
        return new HashMap<>();
    }

    @Override
    public void repair(Vehicle vehicle) {

    }

    @Override
    public boolean isBroken(Vehicle vehicle) {
        return false;
    }
}
