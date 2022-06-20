package by.incubator.application.infrastructure.vehicleService.impl;

import by.incubator.application.entity.Order;
import by.incubator.application.entity.Vehicle;
import by.incubator.application.infrastructure.core.annotations.Autowired;
import by.incubator.application.infrastructure.vehicleService.Fixer;
import by.incubator.application.vehicle.parser.ParserBreakingInterface;

import java.util.*;

public class MechanicService implements Fixer {
    private static final String[] details = {"Фильтр", "Втулка", "Вал", "Ось",
            "Свеча","Масло","ГРМ","ШРУС"};
    private static final int MAX_DEFECTS = 5;

    @Autowired
    private ParserBreakingInterface parser;

    public MechanicService() { }

    public ParserBreakingInterface getParser() {
        return parser;
    }

    public void setParser(ParserBreakingInterface parser) {
        this.parser = parser;
    }

    @Override
    public Map<String, Integer> detectBreaking(Vehicle vehicle) {
        Random rand = new Random();
        String defect = details[rand.nextInt(details.length)];
        int amount = rand.nextInt(MAX_DEFECTS);

        Map<String, Integer> defects = new HashMap<>();
        defects.put(defect, amount);

        if (amount != 0) {
            parser.writeOrder(vehicle.getId(), defect, amount);
        }

        return defects;
    }

    @Override
    public void repair(Vehicle vehicle) {
        long id = vehicle.getId();
        if (parser.findOrder(id).isEmpty()) {
            return;
        }

        parser.deleteOrder(vehicle);
    }

    @Override
    public boolean isBroken(Vehicle vehicle) {
        return parser.findOrder(vehicle.getId()).isPresent();
    }
}
