package application.engines;

public class ElectricalEngine extends  AbstractEngine {

    private double batterySize;
    private double electricityConsumption;

    public ElectricalEngine(double batterySize, double electricityConsumption) {
        super("Electrical", 0.1);
        this.batterySize = batterySize;
        this.electricityConsumption = electricityConsumption;
    }

    public double getBatterySize() {
        return batterySize;
    }

    public void setBatterySize(double batterySize) {
        if (batterySize > 0) {
            this.batterySize = batterySize;
        }
    }

    public double getElectricityConsumption() {
        return electricityConsumption;
    }

    public void setElectricityConsumption(double electricityConsumption) {
        if (electricityConsumption > 0) {
            this.electricityConsumption = electricityConsumption;
        }
    }

    @Override
    public double getTaxPerMonth() {
        return getTaxCoefficient();
    }

    @Override
    public double getMaxKilometers() {
        return batterySize / electricityConsumption;
    }

    @Override
    public String toString() {
        return "Electrical";
    }
}
