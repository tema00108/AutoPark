package application.vehicles;

import java.util.Objects;

public class VehicleType {
    private String typeName;
    private double taxCoefficient;
    private int id;

    public VehicleType() {}

    public VehicleType(String name, double taxRatio, int id) {
        this.typeName = name;
        this.taxCoefficient = taxRatio;
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTaxCoefficient() {
        return taxCoefficient;
    }

    public void setTaxCoefficient(double taxCoefficient) {
        this.taxCoefficient = taxCoefficient;
    }

    public void display() {
        System.out.println("typeName = " + typeName + '\n' +
                           "taxRatio = " + taxCoefficient);
    }

    public String getString() {
        return typeName + ',' + "\"" + taxCoefficient + "\"";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VehicleType)) return false;
        VehicleType that = (VehicleType) o;
        return Double.compare(that.taxCoefficient, taxCoefficient) == 0 && Objects.equals(typeName, that.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeName, taxCoefficient);
    }

    @Override
    public String toString() {
        return "VehicleType{" +
                "typeName='" + typeName + '\'' +
                ", taxCoefficient=" + taxCoefficient +
                '}';
    }
}
