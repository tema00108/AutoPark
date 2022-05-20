package by.incubator.application.infrastructure.validation.techroom;

import by.incubator.application.entity.Vehicles;
import by.incubator.application.infrastructure.core.annotations.Autowired;
import by.incubator.application.infrastructure.vehicleService.Fixer;
import by.incubator.application.vehicle.Vehicle;

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

    public void checkAllVehicles(List<Vehicles> vehicles) {
        showBroken(vehicles.stream());
        showServiceable(vehicles.stream());
    }

    private void showBroken(Stream<Vehicles> stream) {
        System.out.println("Broken vehicles:");
        stream.filter(v -> mechanic.isBroken(v)).forEach(System.out::println);
    }

    private void showServiceable(Stream<Vehicles> stream) {
        System.out.println("Serviceable vehicles:");
        stream.filter(v -> !mechanic.isBroken(v)).forEach(System.out::println);
    }

}
