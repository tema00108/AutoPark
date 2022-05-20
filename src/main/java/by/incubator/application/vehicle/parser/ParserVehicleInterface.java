package by.incubator.application.vehicle.parser;

import by.incubator.application.entity.Rents;
import by.incubator.application.entity.Types;
import by.incubator.application.entity.Vehicles;
import by.incubator.application.rent.Rent;
import by.incubator.application.vehicle.Vehicle;
import by.incubator.application.vehicle.VehicleType;

import java.util.List;

public interface ParserVehicleInterface {
    List<Types> loadTypes();
    List<Vehicles> loadVehicles();
    List<Rents> loadRents();
}
