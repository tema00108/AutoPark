package application.engines;

public class CombustionEngine extends AbstractEngine {

    double engineCapacity;
    double fuelTankCapacity;
    double fuelConsumptionPer100;

    public CombustionEngine(String typeName, double taxCoefficient, double engineCapacity, double fuelConsumptionPer100, double fuelTankCapacity) {
        super(typeName, taxCoefficient);
        this.fuelTankCapacity = fuelTankCapacity;
        this.fuelConsumptionPer100 = fuelConsumptionPer100;
    }

    public double getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(double engineCapacity) {
        if (engineCapacity > 0) {
            this.engineCapacity = engineCapacity;
        }
    }

    public double getFuelTankCapacity() {
        return fuelTankCapacity;
    }

    public void setFuelTankCapacity(double fuelTankCapacity) {
        if (fuelTankCapacity > 0) {
            this.fuelTankCapacity = fuelTankCapacity;
        }
    }

    public double getFuelConsumptionPer100() {
        return fuelConsumptionPer100;
    }

    public void setFuelConsumptionPer100(double fuelConsumptionPer100) {
        if (fuelConsumptionPer100 > 0) {
            this.fuelConsumptionPer100 = fuelConsumptionPer100;
        }
    }

    @Override
    public double getTaxPerMonth() {
        return getTaxCoefficient();
    }

    @Override
    public double getMaxKilometers() {
        return fuelTankCapacity * 100 / fuelConsumptionPer100;
    }
}
