package by.incubator.application.vehicle.parser.impl;

import by.incubator.application.entity.Rent;
import by.incubator.application.entity.Type;
import by.incubator.application.entity.Vehicle;
import by.incubator.application.infrastructure.core.annotations.Autowired;
import by.incubator.application.service.RentService;
import by.incubator.application.service.TypeService;
import by.incubator.application.service.VehicleService;
import by.incubator.application.vehicle.parser.ParserVehicleInterface;

import java.util.List;

public class ParserVehicleFromDB implements ParserVehicleInterface {
    @Autowired
    private TypeService typeService;
    @Autowired
    private VehicleService vehiclesService;
    @Autowired
    private RentService rentService;

    @Override
    public List<Type> loadTypes() {
        return typeService.getAll();
    }

    @Override
    public List<Vehicle> loadVehicles() {
        return vehiclesService.getAll();
    }

    @Override
    public List<Rent> loadRents() {
        return rentService.getAll();
    }
}
