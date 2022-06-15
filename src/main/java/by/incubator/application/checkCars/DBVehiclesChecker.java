package by.incubator.application.checkCars;

import by.incubator.application.entity.Vehicle;
import by.incubator.application.infrastructure.core.Context;
import by.incubator.application.infrastructure.orm.EntityManager;
import by.incubator.application.infrastructure.threads.annotations.Schedule;
import by.incubator.application.infrastructure.validation.techroom.Workroom;

import java.util.List;

public class DBVehiclesChecker {

    public DBVehiclesChecker() { }

    @Schedule(delta = 10000,timeout = 10000)
    public void vehiclesFromDBToWorkroom(Context context) {
        EntityManager manager = context.getObject(EntityManager.class);
        List<Vehicle> vehicles = manager.getAll(Vehicle.class);
        context.getObject(Workroom.class).checkAllVehicles(vehicles);
    }
}
