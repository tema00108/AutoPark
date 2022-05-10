package by.incubator.validation;

import by.incubator.color.Color;
import by.incubator.engines.CombustionEngine;
import by.incubator.engines.DieselEngine;
import by.incubator.engines.ElectricalEngine;
import by.incubator.engines.GasolineEngine;
import by.incubator.vehicles.VehicleType;

public class TechnicalSpecialist {
    public static final int LOWER_LIMIT_MANUFACTURE_YEAR = 1886;

    public static boolean validateManufactureYear(int year) {
        return year >= LOWER_LIMIT_MANUFACTURE_YEAR && year < 10000;
    }

    public static boolean validateMileage(int mileage) {
        return mileage >= 0;
    }

    public static boolean validateWeight(int weight) {
        return weight >= 0;
    }

    static public boolean validateColor(Color color) {
        return color != null; //checking color?
    }

    public static boolean validateVehicleType(VehicleType type) {
        String name = type.getTypeName();

        if (name != null && !(name.equals(""))){
            return type.getTaxCoefficient() > 0.0;
        }

        return false;
    }

    public static boolean validateRegistrationNumber(String number) {
        if (number == null || number.length() != 9) {
            return false;
        }

        char[] chars = number.toCharArray();

        if (!isNumber(chars[0],chars[1],chars[2],chars[3],chars[8])) {
            return false;
        }

        if (chars[4] != ' ') {
            return false;
        }

        if (!isCapLetter(chars[5], chars[6])) {
            return false;
        }

        return chars[7] == '-';
    }

    public static boolean validateModelName(String name) {
        return name != null && !name.equals("");
    }

    public static boolean validateCombustionEngine(CombustionEngine engine) {
        if (engine == null) {
            return false;
        }

        return engine.getEngineCapacity() > 0 && engine.getFuelTankCapacity() > 0 && engine.getFuelConsumptionPer100() > 0;
    }

    public static boolean validateElectricalEngine(ElectricalEngine engine) {
        if (engine == null) {
            return false;
        }

        return engine.getBatterySize() > 0 && engine.getElectricityConsumption() > 0;
    }

    private static boolean isNumber(char... chars) {
        for (char ch : chars) {
            if (ch < '0' || ch > '9') {
                return false;
            }
        }

        return true;
    }

    private static boolean isCapLetter(char... chars) {
        for (char ch : chars) {
            if (ch < 'A' || ch > 'Z') {
                return false;
            }
        }

        return true;
    }
}