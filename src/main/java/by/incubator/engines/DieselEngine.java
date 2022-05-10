package by.incubator.engines;

public class DieselEngine extends CombustionEngine {

    public DieselEngine(double engineCapacity, double fuelConsumptionPer100, double fuelTankCapacity) {

        super("Diesel", 1.2, engineCapacity, fuelConsumptionPer100, fuelTankCapacity);
    }

    @Override
    public String toString() {
        return "Diesel";
    }
}
