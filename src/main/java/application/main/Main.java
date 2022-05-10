package application.main;

import application.queue.MyQueue;
import application.queue.MyStack;
import application.vehicles.Vehicle;
import application.vehicles.VehicleCollection;

public class Main {

    public static final String TYPES_PATH = "src/by/incubator/projects/autopark/Java-main/";
    public static final String VEHICLES_PATH = "src/by/incubator/projects/autopark/Java-main/";
    public static final String RENTS_PATH = "src/by/incubator/projects/autopark/Java-main/";

    public static void main(String[] args) {
        VehicleCollection vehCollection = loadInfo();
        vehCollection.display();

        MyQueue<Vehicle> queue = new MyQueue<>();
        washVehicles(vehCollection, queue);

        System.out.println();

        MyStack<Vehicle> stack = new MyStack<>();
        goToTheGarage(vehCollection, stack);

    }

    public static void goToTheGarage(VehicleCollection vehCollection, MyStack<Vehicle> stack) {
        int size;

        for (Vehicle vehicle : vehCollection.getVehicles()) {
            stack.push(vehicle);
            System.out.println(stack.peek() + " -- has driven in");
        }

        System.out.println("Garage is fulled\n");
        size = stack.size();

        for (int i = 0; i < size; i++) {
            System.out.println(stack.pop() + " -- has driven out");
        }
    }

    public static VehicleCollection loadInfo() {
        VehicleCollection vehicleCollection = new VehicleCollection("types.csv","vehicles.csv", "rents.csv");

        vehicleCollection.loadTypes(TYPES_PATH);
        vehicleCollection.loadVehicles(VEHICLES_PATH);
        vehicleCollection.loadRents(RENTS_PATH);

        return vehicleCollection;
    }

    public static void washVehicles(VehicleCollection vehCollection, MyQueue<Vehicle> queue) {
        int size;

        for (Vehicle vehicle : vehCollection.getVehicles()) {
            queue.enqueue(vehicle);
        }

        size = queue.size();

        for (int i = 0; i < size; i++) {
            System.out.println(queue.dequeue() + " -- is washed up");
        }

    }
}

