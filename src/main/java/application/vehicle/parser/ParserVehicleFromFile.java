package application.vehicle.parser;

import application.color.Color;
import application.infrastructure.core.annotations.Autowired;
import application.infrastructure.core.annotations.InitMethod;
import application.engines.DieselEngine;
import application.engines.ElectricalEngine;
import application.engines.GasolineEngine;
import application.engines.Startable;
import application.infrastructure.validation.TechnicalSpecialist;
import application.vehicle.Vehicle;
import application.vehicle.VehicleType;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ParserVehicleFromFile {

    private static final String vehicleTypePath = "src/main/resources/data/types.csv";
    private static final String vehiclePath = "src/main/resources/data/vehicles.csv";
    @Autowired
    private TechnicalSpecialist specialist;

    public ParserVehicleFromFile() { }

    @InitMethod
    public void init() {
    }

    public List<VehicleType> loadTypes() {

        List<VehicleType> list = new ArrayList<>();
        List<String> csvStrings = readFile(vehicleTypePath);

        for (String csvString : csvStrings) {
            list.add(createType(csvString));
        }

        return list;
    }

    public List<Vehicle> loadVehicles() {
        List<Vehicle> list = new ArrayList<>();
        List<String> csvStrings = readFile(vehiclePath);

        for (String csvString : csvStrings) {
            list.add(createVehicle(csvString));
        }

        return list;
    }

    public Vehicle createVehicle(String csvString) {
        String[] params = parseLine(csvString);

        int id = Integer.parseInt(params[0]);
        String modelName = params[2];
        String registrationNumber = params[3];
        int weight = Integer.parseInt(params[4]);
        int manufactureYear = Integer.parseInt(params[5]);
        int mileage = Integer.parseInt(params[6]);
        Color color = Color.valueOf(params[7].toUpperCase(Locale.ROOT));

        int typeId = Integer.parseInt(params[1]);
        VehicleType type = getTypeById(typeId);

        Startable engine = createEngine(params, 8);

        return new Vehicle(id, type, engine, modelName, registrationNumber, weight, manufactureYear, mileage, color);
    }

    private VehicleType getTypeById(int typeId) {
        for (VehicleType type : loadTypes()) {
            if (type.getId() == typeId) {
                return type;
            }
        }
        throw new NoSuchElementException();
    }

    private Startable createEngine(String[] params, int order) {
        String engineStr = params[order];

        switch(engineStr) {
            case "Electrical":
                double batterySize = Double.parseDouble(params[order + 1]);
                double consumption = Double.parseDouble( params[order + 2]);

                return new ElectricalEngine(batterySize, consumption);

            case "Diesel":
                double engineCapacity = Double.parseDouble(params[order + 1]);
                double fuelConsumptionPer100 = Double.parseDouble( params[order + 2]);
                double fuelTankCapacity = Double.parseDouble( params[order + 3]);

                return new DieselEngine(engineCapacity, fuelConsumptionPer100, fuelTankCapacity);

            case "Gasoline":
                engineCapacity = Double.parseDouble(params[order + 1]);
                fuelConsumptionPer100 = Double.parseDouble( params[order + 2]);
                fuelTankCapacity = Double.parseDouble( params[order + 3]);

                return new GasolineEngine(engineCapacity, fuelConsumptionPer100, fuelTankCapacity);
        }

        throw new IllegalArgumentException("Name of engine: " + engineStr);
    }

    private VehicleType createType(String csvString) {
        String[] params = parseLine(csvString);

        double coefficient = Double.parseDouble(params[2]);
        int id = Integer.parseInt(params[0]);
        String typeName = params[1];

        return new VehicleType(typeName, coefficient, id);
    }

    private List<String> readFile(String inFile) {
        List<String> csvStrings = new ArrayList<>();

        try (FileReader reader = new FileReader(inFile);
             BufferedReader in = new BufferedReader(reader)){

            csvStrings = in.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return csvStrings;
    }

    private String[] parseLine(String line) {
        Pattern pattern = Pattern.compile("(?!\\B\"[^\"]*),(?![^\"]*\"\\B)");

        if (line.indexOf('"') < 0) {
            return line.split(",");
        }

        String[] params = pattern.split(line);
        for (int i = 0; i < params.length; i++) {
            params[i] = params[i].replaceAll("\"", "").replaceAll(",", ".");
        }
        return params;
    }
}
