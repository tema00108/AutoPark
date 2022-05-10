package by.incubator.main;

import by.incubator.vehicles.Vehicle;
import by.incubator.vehicles.VehicleCollection;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {
    public static final String PATH = "src/by/incubator/projects/autopark/files/";
    public static final String MESSAGE_VOLKSWAGEN = "The following vehicles are Volkswagen's:";
    public static final String MESSAGE_MAX_YEAR_VOLKSWAGEN = "The following vehicle is the oldest Volkswagen's:";

    public static void main(String[] args) {
        List<Vehicle> vehicles = initVehicles();

        List<Vehicle> volkswagens = findVolkswagens(vehicles);
        printVehicles(volkswagens, MESSAGE_VOLKSWAGEN);

        Vehicle maxYearVolkswagen = findMaxYear(volkswagens);
        printVehicles(maxYearVolkswagen, MESSAGE_MAX_YEAR_VOLKSWAGEN);

        washVehicles(vehicles);
        goToGarage(vehicles);
    }

    private static List<Vehicle> initVehicles() {
        VehicleCollection vehCollection = loadInfo();
        return vehCollection.getVehicles();
    }

    private static <T> Stream<T> reverse(Stream<T> stream) {
        LinkedList<T> stack = new LinkedList<>();
        stream.forEach(stack::push);

        return stack.stream();
    }

    private static void goToGarage(List<Vehicle> vehicles) {
        Stream<Vehicle> pushToGarage = reverse(vehicles.stream());
        pushToGarage.forEach(veh -> System.out.println(veh + " -- drove out from garage"));
    }

    private static void washVehicles(List<Vehicle> vehicles) {
        vehicles.stream().forEach(veh -> System.out.println(veh + "-- washed up"));
    }

    private static Vehicle findMaxYear(List<Vehicle> vehicles) {
        Optional<Vehicle> maxYear = vehicles.stream().max(Comparator.comparingInt(Vehicle::getManufactureYear));
        return maxYear.get();
    }

    private static void printVehicles(List<Vehicle> vehicles, String message) {
        System.out.println(message);
        vehicles.stream().forEach(System.out::println);
        System.out.println();
    }

    private static void printVehicles(Vehicle vehicle, String message) {
        System.out.println(message);
        System.out.println(vehicle + "\n");
    }

    private static List<Vehicle> findVolkswagens(List<Vehicle> vehicles) {
        return vehicles.stream()
                .filter(car -> car.getModelName().contains("Volkswagen"))
                .collect(Collectors.toList());
    }

    private static VehicleCollection loadInfo() {
        VehicleCollection vehicleCollection = new VehicleCollection("types.csv","vehicles.csv", "rents.csv");

        vehicleCollection.loadTypes(PATH);
        vehicleCollection.loadVehicles(PATH);
        vehicleCollection.loadRents(PATH);

        return vehicleCollection;
    }
}

