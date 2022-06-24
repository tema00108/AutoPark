package by.incubator.application.engines;

public class GasolineEngine extends CombustionEngine {
    public static final double TAX_COEFFICIENT = 1.1;
    public static final String TYPE_NAME = "Gasoline";

    public GasolineEngine(double engineCapacity, double fuelConsumptionPer100, double fuelTankCapacity) {
        super(TYPE_NAME, TAX_COEFFICIENT, engineCapacity, fuelConsumptionPer100, fuelTankCapacity);
    }

    @Override
    public String toString() {
        return "Gasoline";
    }
}
