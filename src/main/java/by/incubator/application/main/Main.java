package by.incubator.application.main;

import by.incubator.application.checkCars.WorkroomManager;
import by.incubator.application.dtoFacade.DtoService;
import by.incubator.application.infrastructure.core.impl.ApplicationContext;
import by.incubator.application.infrastructure.vehicleService.Fixer;
import by.incubator.application.infrastructure.validation.techroom.Workroom;
import by.incubator.application.infrastructure.vehicleService.impl.MechanicService;
import by.incubator.application.vehicle.VehicleCollection;
import by.incubator.application.vehicle.parser.ParserBreakingInterface;
import by.incubator.application.vehicle.parser.ParserVehicleInterface;
import by.incubator.application.vehicle.parser.impl.ParserBreakingFromDB;
import by.incubator.application.vehicle.parser.impl.ParserVehicleFromDB;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map<Class<?>, Class<?>> interfaceToImplementation = initInterfaceToImplementation();
        ApplicationContext context = new ApplicationContext("by.incubator.application", interfaceToImplementation);

        VehicleCollection vehicleCollection = context.getObject(VehicleCollection.class);
        Workroom workroom = context.getObject(Workroom.class);

        printRents(vehicleCollection);
        printVehicles(vehicleCollection);

        DtoService dtoService = context.getObject(DtoService.class);
        System.out.println(dtoService.getVehicles());

        WorkroomManager workroomManager = context.getObject(WorkroomManager.class);
        workroomManager.checkVehiclesInWorkroom(context);

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static Map<Class<?>, Class<?>> initInterfaceToImplementation() {
        Map<Class<?>, Class<?>> map = new HashMap<>();
        map.put(Fixer.class, MechanicService.class);
        map.put(ParserVehicleInterface.class, ParserVehicleFromDB.class);
        map.put(ParserBreakingInterface.class, ParserBreakingFromDB.class);
        return map;
    }

    private static void printRents(VehicleCollection vehicleCollection) {
        System.out.println(vehicleCollection.getRents());
    }

    private static void printVehicles(VehicleCollection vehicleCollection) {
        System.out.println(vehicleCollection.getVehicles());
    }
}

