package by.incubator.application.logic;

import by.incubator.application.entity.Type;
import by.incubator.application.entity.Vehicle;
import by.incubator.application.entity.Rent;

import java.util.List;

public class VehicleLogic {
    public static double getCalcTaxPerMonth(Vehicle vehicle, Type type) {
        return (double) Math.round(((vehicle.getWeight() * 0.0013) + (type.getTaxCoefficient() * vehicle.getEngineTaxCoefficient() * 30) + 5) * 100) / 100;
    }

    public static double getTotalIncome(List<Rent> rents) {
        double sum = 0.0d;

        for (Rent rent: rents) {
            sum += rent.getCost();
        }
        return (double) Math.round(sum * 100) / 100;
    }

    public static double getMaxKilometers(Vehicle vehicle) {
        return (double) Math.round((vehicle.getCapacity() * 100 / vehicle.getConsumption()) * 100) / 100;
    }
}
