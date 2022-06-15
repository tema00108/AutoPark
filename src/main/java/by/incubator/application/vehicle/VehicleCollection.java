package by.incubator.application.vehicle;

import by.incubator.application.entity.Rent;
import by.incubator.application.entity.Type;
import by.incubator.application.entity.Vehicle;
import by.incubator.application.infrastructure.core.annotations.Autowired;
import by.incubator.application.infrastructure.core.annotations.InitMethod;
import by.incubator.application.vehicle.parser.ParserVehicleInterface;

import java.util.*;
import java.util.stream.Stream;

public class VehicleCollection {

    private List<Type> vehicleTypes;
    private List<Vehicle> vehicles;
    private List<Rent> rents;

    @Autowired private ParserVehicleInterface parser;

    public VehicleCollection() { }

    @InitMethod
    public void init() {
        vehicles = parser.loadVehicles();
        vehicleTypes = parser.loadTypes();
        rents = parser.loadRents();
    }

    public List<Type> getTypes() {
        return vehicleTypes;
    }

    public void setVehicleTypes(List<Type> vehicleTypes) {
        this.vehicleTypes = vehicleTypes;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public List<Rent> getRents() {
        return rents;
    }

    public void setRents(List<Rent> rents) {
        this.rents = rents;
    }

    public Type getTypeById(long id) {
        return vehicleTypes.stream().filter(type -> type.getId().equals(id)).findFirst().get();
    }
}
