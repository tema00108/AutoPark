package by.incubator.application.main;

import by.incubator.application.entity.Vehicles;
import by.incubator.application.vehicle.Vehicle;
import by.incubator.application.vehicle.VehicleCollection;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {
    public static final String PATH = "src/by/incubator/projects/autopark/files/";
    public static final String MESSAGE_VOLKSWAGEN = "The following vehicles are Volkswagen's:";
    public static final String MESSAGE_MAX_YEAR_VOLKSWAGEN = "The following vehicle is the oldest Volkswagen's:";

    public static void main(String[] args) {
        List<Vehicles> vehicles = initVehicles();

        List<Vehicles> volkswagens = findVolkswagens(vehicles);
        printVehicles(volkswagens, MESSAGE_VOLKSWAGEN);

        Vehicles maxYearVolkswagen = findMaxYear(volkswagens);
        printVehicles(maxYearVolkswagen, MESSAGE_MAX_YEAR_VOLKSWAGEN);

        washVehicles(vehicles);
        goToGarage(vehicles);
    }

    private static List<Vehicles> initVehicles() {
        VehicleCollection vehCollection = loadInfo();
        return vehCollection.getVehicles();
    }

    private static <T> Stream<T> reverse(Stream<T> stream) {
        LinkedList<T> stack = new LinkedList<>();
        stream.forEach(stack::push);

        return stack.stream();
    }

    private static void goToGarage(List<Vehicles> vehicles) {
        Stream<Vehicles> pushToGarage = reverse(vehicles.stream());
        pushToGarage.forEach(veh -> System.out.println(veh + " -- drove out from garage"));
    }

    private static void washVehicles(List<Vehicles> vehicles) {
        vehicles.stream().forEach(veh -> System.out.println(veh + "-- washed up"));
    }

    private static Vehicles findMaxYear(List<Vehicles> vehicles) {
        Optional<Vehicles> maxYear = vehicles.stream().max(Comparator.comparingInt(Vehicles::getManufactureYear));
        return maxYear.get();
    }

    private static void printVehicles(List<Vehicles> vehicles, String message) {
        System.out.println(message);
        vehicles.stream().forEach(System.out::println);
        System.out.println();
    }

    private static void printVehicles(Vehicles vehicle, String message) {
        System.out.println(message);
        System.out.println(vehicle + "\n");
    }

    private static List<Vehicles> findVolkswagens(List<Vehicles> vehicles) {
        return vehicles.stream()
                .filter(car -> car.getModel().contains("Volkswagen"))
                .collect(Collectors.toList());
    }

    private static VehicleCollection loadInfo() {
        VehicleCollection vehicleCollection = new VehicleCollection();

        return vehicleCollection;
    }
}

