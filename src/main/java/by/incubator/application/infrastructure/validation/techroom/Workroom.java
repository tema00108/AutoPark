package by.incubator.application.infrastructure.validation.techroom;

import by.incubator.application.entity.Vehicle;
import by.incubator.application.infrastructure.core.annotations.Autowired;
import by.incubator.application.infrastructure.vehicleService.Fixer;

import java.util.List;
import java.util.stream.Stream;

public class Workroom {
    @Autowired
    private Fixer mechanic;

    public Workroom() { }

    public Fixer getMechanic() {
        return mechanic;
    }

    public void setMechanic(Fixer mechanic) {
        this.mechanic = mechanic;
    }

    public void checkAllVehicles(List<Vehicle> vehicles) {
        vehicles.stream().forEach(vehicle -> mechanic.detectBreaking(vehicle));
    }

    public void repairAllVehicles(List<Vehicle> vehicles) {
        vehicles.stream().forEach(vehicle -> mechanic.repair(vehicle));
    }
}
