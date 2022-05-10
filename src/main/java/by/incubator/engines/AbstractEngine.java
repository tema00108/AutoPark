package by.incubator.engines;

public abstract class AbstractEngine implements Startable {
    private String typeName;
    private double taxCoefficient;

    public AbstractEngine(String typeName, double taxCoefficient) {
        this.typeName = typeName;
        this.taxCoefficient = taxCoefficient;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public double getTaxCoefficient() {
        return taxCoefficient;
    }

    public void setTaxCoefficient(double taxCoefficient) {
        if (taxCoefficient >= 0) {
            this.taxCoefficient = taxCoefficient;
        }
    }

    @Override
    public String toString() {
        return typeName +
                ",\"" + taxCoefficient + "\"";
    }
}
