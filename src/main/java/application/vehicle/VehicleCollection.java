package application.vehicle;

import application.infrastructure.core.annotations.Autowired;
import application.infrastructure.core.annotations.InitMethod;
import application.vehicle.parser.ParserVehicleFromFile;

import java.util.*;

public class VehicleCollection {

    private List<VehicleType> vehicleTypes;
    private List<Vehicle> vehicles;

    @Autowired private ParserVehicleFromFile parser;

    public VehicleCollection() { }

    public List<VehicleType> getVehicleTypes() {
        return vehicleTypes;
    }

    public void setVehicleTypes(List<VehicleType> vehicleTypes) {
        this.vehicleTypes = vehicleTypes;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    @InitMethod
    public void init() {
        vehicles = parser.loadVehicles();
        vehicleTypes = parser.loadTypes();
    }
}
