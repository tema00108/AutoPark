package by.incubator.application.engines;

public class DieselEngine extends CombustionEngine {
    public static final double TAX_COEFFICIENT = 1.2;
    public static final String TYPE_NAME = "Diesel";

    public DieselEngine(double engineCapacity, double fuelConsumptionPer100, double fuelTankCapacity) {
        super(TYPE_NAME, TAX_COEFFICIENT, engineCapacity, fuelConsumptionPer100, fuelTankCapacity);
    }

    @Override
    public String toString() {
        return "Diesel";
    }
}
