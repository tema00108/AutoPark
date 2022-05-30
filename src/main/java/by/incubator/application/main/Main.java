package by.incubator.application.main;

import by.incubator.application.entity.Orders;
import by.incubator.application.entity.Rents;
import by.incubator.application.entity.Types;
import by.incubator.application.entity.Vehicles;
import by.incubator.application.infrastructure.core.impl.ApplicationContext;
import by.incubator.application.infrastructure.orm.EntityManager;
import by.incubator.application.infrastructure.vehicleService.Fixer;
import by.incubator.application.infrastructure.vehicleService.impl.BadMechanicService;
import by.incubator.application.infrastructure.validation.techroom.Workroom;
import by.incubator.application.infrastructure.vehicleService.impl.MechanicService;
import by.incubator.application.service.VehicleService;
import by.incubator.application.vehicle.Vehicle;
import by.incubator.application.vehicle.VehicleCollection;
import by.incubator.application.vehicle.parser.ParserVehicleInterface;
import by.incubator.application.vehicle.parser.impl.ParserVehicleFromFile;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map<Class<?>, Class<?>> interfaceToImplementation = initInterfaceToImplementation();
        ApplicationContext context = new ApplicationContext("by.incubator.application", interfaceToImplementation);

        VehicleCollection vehicleCollection = context.getObject(VehicleCollection.class);
        Workroom workroom = context.getObject(Workroom.class);

        printRents(vehicleCollection);
        workroom.checkAllVehicles(vehicleCollection.getVehicles());
    }

    private static Map<Class<?>, Class<?>> initInterfaceToImplementation() {
        Map<Class<?>, Class<?>> map = new HashMap<>();
        map.put(Fixer.class, MechanicService.class);
        map.put(ParserVehicleInterface.class, ParserVehicleFromFile.class);
        return map;
    }

    private static void printRents(VehicleCollection vehicleCollection) {
        System.out.println(vehicleCollection.getRents());
    }
}

