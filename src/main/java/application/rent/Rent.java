package application.rent;

import java.util.Date;

public class Rent {
    private Date date;
    private double cost;

    public Rent() { }

    public Rent(Date date, double cost) {
        this.date = date;
        this.cost = cost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
