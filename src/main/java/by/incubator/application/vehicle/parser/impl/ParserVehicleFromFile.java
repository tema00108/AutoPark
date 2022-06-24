package by.incubator.application.vehicle.parser.impl;

import by.incubator.application.engines.DieselEngine;
import by.incubator.application.engines.ElectricalEngine;
import by.incubator.application.engines.GasolineEngine;
import by.incubator.application.entity.Rent;
import by.incubator.application.entity.Type;
import by.incubator.application.entity.Vehicle;
import by.incubator.application.infrastructure.core.annotations.Autowired;
import by.incubator.application.infrastructure.core.annotations.InitMethod;
import by.incubator.application.infrastructure.validation.TechnicalSpecialist;
import by.incubator.application.vehicle.parser.ParserVehicleInterface;
import lombok.SneakyThrows;

import java.io.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ParserVehicleFromFile implements ParserVehicleInterface {

    private static final String vehicleTypePath = "src/main/resources/data/types.csv";
    private static final String vehiclePath = "src/main/resources/data/vehicles.csv";
    private static final String rentPath = "src/main/resources/data/rents.csv";

    @Autowired
    private TechnicalSpecialist specialist;

    public ParserVehicleFromFile() { }

    @InitMethod
    public void init() {
    }

    @Override
    public List<Type> loadTypes() {

        List<Type> list = new ArrayList<>();
        List<String> csvStrings = readFile(vehicleTypePath);

        for (String csvString : csvStrings) {
            list.add(createType(csvString));
        }

        return list;
    }

    @Override
    public List<Vehicle> loadVehicles() {
        List<Vehicle> list = new ArrayList<>();
        List<String> csvStrings = readFile(vehiclePath);

        for (String csvString : csvStrings) {
            list.add(createVehicle(csvString));
        }

        return list;
    }

    @Override
    public List<Rent> loadRents() {

        List<Rent> list = new ArrayList<>();
        List<String> csvStrings = readFile(rentPath);

        for (String csvString : csvStrings) {
            list.add(createRent(csvString));
        }

        return list;
    }

    private Vehicle createVehicle(String csvString) {
        String[] params = parseLine(csvString);

        long id = Integer.parseInt(params[0]);
        String modelName = params[2];
        String registrationNumber = params[3];
        int weight = Integer.parseInt(params[4]);
        int manufactureYear = Integer.parseInt(params[5]);
        int mileage = Integer.parseInt(params[6]);
        String color = params[7];
        long typeId = Integer.parseInt(params[1]);
        String engine = params[8];
        double consumption;
        double capacity;
        double engineTaxCoefficient;

        switch (engine) {
            case GasolineEngine.TYPE_NAME:
                consumption = Double.parseDouble(params[10]);
                capacity = Double.parseDouble(params[11]);
                engineTaxCoefficient = GasolineEngine.TAX_COEFFICIENT;
                break;
            case DieselEngine.TYPE_NAME:
                consumption = Double.parseDouble(params[10]);
                capacity = Double.parseDouble(params[11]);
                engineTaxCoefficient = DieselEngine.TAX_COEFFICIENT;
                break;
            case ElectricalEngine.TYPE_NAME:
                consumption = Double.parseDouble(params[9]);
                capacity = Double.parseDouble(params[10]);
                engineTaxCoefficient = ElectricalEngine.TAX_COEFFICIENT;
                break;
            default:
                throw new IllegalArgumentException("Engine type is incorrect: " + engine);
        }

        return new Vehicle(id, typeId, modelName, registrationNumber, weight, manufactureYear, mileage, color, engine, consumption, capacity, engineTaxCoefficient);
    }

    private Type createType(String csvString) {
        String[] params = parseLine(csvString);

        double coefficient = Double.parseDouble(params[2]);
        long id = Integer.parseInt(params[0]);
        String typeName = params[1];

        return new Type(id, typeName, coefficient);
    }

    @SneakyThrows
    public Rent createRent(String csvString) {
        String[] params = parseLine(csvString);

        double cost = Double.parseDouble(params[2]);
        long id = Integer.parseInt(params[0]);

        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date(formatter.parse(params[1]).getTime());

        return new Rent(id, date, cost);
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
