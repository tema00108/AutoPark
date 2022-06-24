package by.incubator.application.vehicle.parser;

import by.incubator.application.entity.Type;
import by.incubator.application.entity.Rent;
import by.incubator.application.entity.Vehicle;

import java.util.List;

public interface ParserVehicleInterface {
    List<Type> loadTypes();
    List<Vehicle> loadVehicles();
    List<Rent> loadRents();
}
