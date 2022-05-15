package application.infrastructure.validation.techroom;

import application.infrastructure.core.annotations.Autowired;
import application.infrastructure.service.Fixer;
import application.vehicle.Vehicle;

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
        showBroken(vehicles.stream());
        showServiceable(vehicles.stream());
    }

    private void showBroken(Stream<Vehicle> stream) {
        System.out.println("Broken vehicles:");
        stream.filter(v -> mechanic.isBroken(v)).forEach(System.out::println);
    }

    private void showServiceable(Stream<Vehicle> stream) {
        System.out.println("Serviceable vehicles:");
        stream.filter(v -> !mechanic.isBroken(v)).forEach(System.out::println);
    }

}
