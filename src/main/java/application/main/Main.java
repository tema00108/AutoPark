package application.main;

import application.infrastructure.configurators.ObjectConfigurator;
import application.infrastructure.configurators.impl.AutowiredObjectConfigurator;
import application.infrastructure.core.impl.ApplicationContext;
import application.infrastructure.service.Fixer;
import application.infrastructure.service.impl.BadMechanicService;
import application.infrastructure.service.impl.MechanicService;
import application.infrastructure.validation.techroom.Workroom;
import application.vehicle.VehicleCollection;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map<Class<?>, Class<?>> interfaceToImplementation = initInterfaceToImplementation();
        ApplicationContext context = new ApplicationContext("src/main/java/application", interfaceToImplementation);
        VehicleCollection vehicleCollection = context.getObject(VehicleCollection.class);
        Workroom workroom = context.getObject(Workroom.class);
        workroom.checkAllVehicles(vehicleCollection.getVehicles());
    }

    private static Map<Class<?>, Class<?>> initInterfaceToImplementation() {
        Map<Class<?>, Class<?>> map = new HashMap<>();
        map.put(Fixer.class, BadMechanicService.class);
        return map;
    }
}

