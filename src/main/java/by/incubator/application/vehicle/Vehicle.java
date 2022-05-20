package by.incubator.application.vehicle;

import by.incubator.application.color.Color;
import by.incubator.application.engines.Startable;
import by.incubator.application.exceptions.NotVehicleException;
import by.incubator.application.rent.Rent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import static by.incubator.application.infrastructure.validation.TechnicalSpecialist.*;

public class Vehicle implements Comparable<Vehicle>{

    private VehicleType type;
    private String modelName;
    private String registrationNumber;
    private int weight;
    private int manufactureYear;
    private int mileage;
    private Color color;
    private int volume;
    private int id;
    private Startable engine;
    private List<Rent> rents = new ArrayList<>();

    public Vehicle(int id,
                   VehicleType type,
                   Startable engine,
                   String modelName,
                   String registrationNumber,
                   int weight,
                   int manufactureYear,
                   int mileage,
                   Color color) {
        try {
            if (!validateVehicleType(type)) {
                throw new NotVehicleException("Vehicle type: " + type);
            }

            this.type = type;

            if (!validateModelName(modelName)) {
                throw new NotVehicleException("Model name: " + modelName);
            }

            this.modelName = modelName;

            if (!validateRegistrationNumber(registrationNumber)) {
                throw new NotVehicleException("Registration number:" + registrationNumber);
            }

            this.registrationNumber = registrationNumber;

            if (!validateWeight(weight)) {
                throw new NotVehicleException("Weight: " + weight);
            }

            this.weight = weight;

            if (!validateManufactureYear(manufactureYear)) {
                throw new NotVehicleException("Manufacture year: " + manufactureYear);
            }

            this.manufactureYear = manufactureYear;

            if (!validateMileage(mileage)) {
                throw new NotVehicleException("Mileage: " + mileage);
            }

            this.mileage = mileage;

            if (!validateColor(color)) {
                throw new NotVehicleException("Color: " + color);
            }

            this.color = color;

        } catch (NotVehicleException e) {
            System.err.println(e.getMessage());
        }

        this.id = id;
        this.engine = engine;
    }

    public VehicleType getType() {
        return type;
    }

    public String getModelName() {
        return modelName;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        if (validateRegistrationNumber(registrationNumber)) {
            this.registrationNumber = registrationNumber;
        }
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        if (validateWeight(weight)) {
            this.weight = weight;
        }
    }

    public int getManufactureYear() {
        return manufactureYear;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        if (validateMileage(mileage)) {
            this.mileage = mileage;
        }
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        if (validateColor(color)) {
            this.color = color;
        }
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public Startable getEngine() {
        return engine;
    }

    public void setEngine(Startable engine) {
        this.engine = engine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Rent> getRents() {
        return rents;
    }

    public void setRents(List<Rent> rents) {
        this.rents = rents;
    }

    public double getCalcTaxPerMonth() {

        return (weight * 0.0013) + (type.getTaxCoefficient() * engine.getTaxPerMonth() * 30) + 5;
    }

    public double getTotalIncome() {
        double sum = 0.0d;

        for (Rent rent: rents) {
            sum += rent.getCost();
        }
        return sum;
    }

    public double getTotalProfit() {
        return getTotalIncome() - getCalcTaxPerMonth();
    }

    @Override
    public String toString() {
        return type.getTypeName() +
                ", " + modelName +
                ", " + registrationNumber +
                ", " + weight +
                ", " + manufactureYear +
                ", " + mileage +
                ", " + color +
                ", " + Math.round(getCalcTaxPerMonth() * 100) / 100  +
                ", " + engine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Vehicle that = (Vehicle) o;

        return (type.equals(that.type) && modelName.equals(that.modelName));
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, modelName);
    }

    public int compareTo(Vehicle obj) {
        if (manufactureYear > obj.manufactureYear) {
            return 1;
        }
        else if (manufactureYear < obj.manufactureYear) {
            return -1;
        }
        else {
            if (mileage > obj.mileage) {
                return 1;
            }
            else if (mileage < obj.mileage) {
                return -1;
            }
        }
        return 0;
    }
}


