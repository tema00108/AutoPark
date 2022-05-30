package by.incubator.application.vehicle;

import by.incubator.application.entity.Rents;
import by.incubator.application.entity.Types;
import by.incubator.application.entity.Vehicles;
import by.incubator.application.infrastructure.core.annotations.Autowired;
import by.incubator.application.infrastructure.core.annotations.InitMethod;
import by.incubator.application.vehicle.parser.ParserVehicleInterface;
import by.incubator.application.vehicle.parser.impl.ParserVehicleFromFile;

import java.util.*;

public class VehicleCollection {

    private List<Types> vehicleTypes;
    private List<Vehicles> vehicles;
    private List<Rents> rents;

    @Autowired private ParserVehicleInterface parser;

    public VehicleCollection() { }

    public List<Types> getVehicleTypes() {
        return vehicleTypes;
    }

    public void setVehicleTypes(List<Types> vehicleTypes) {
        this.vehicleTypes = vehicleTypes;
    }

    public void setVehicles(List<Vehicles> vehicles) {
        this.vehicles = vehicles;
    }

    public List<Vehicles> getVehicles() {
        return vehicles;
    }

    public List<Rents> getRents() {
        return rents;
    }

    public void setRents(List<Rents> rents) {
        this.rents = rents;
    }

    @InitMethod
    public void init() {
        vehicles = parser.loadVehicles();
        vehicleTypes = parser.loadTypes();
        rents = parser.loadRents();
    }
}
