package by.incubator.application.vehicle.parser.impl;

import by.incubator.application.entity.Order;
import by.incubator.application.entity.Vehicle;
import by.incubator.application.vehicle.parser.ParserBreakingInterface;
import com.opencsv.*;
import com.opencsv.exceptions.CsvException;
import by.incubator.application.infrastructure.core.annotations.InitMethod;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ParserBreakingFromFile implements ParserBreakingInterface {

    private static final String ORDERS_PATH = "src/main/resources/data/orders.csv";

    public ParserBreakingFromFile() { }

    @InitMethod
    public void init() {
    }

    public void writeOrder(long id, String defect, int amount) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(ORDERS_PATH, true),
                ',',
                CSVWriter.NO_QUOTE_CHARACTER,
                ' ',
                "\n"))
        {
            String[] line = {String.valueOf(id), defect, String.valueOf(amount)};
            writer.writeNext(line, false);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Optional<Order> findOrder(long id) {
        try (CSVReader reader = new CSVReader(new FileReader(ORDERS_PATH))) {
            List<String[]> lines = reader.readAll();
            Optional<String[]> optionalStr = lines.stream().filter(line -> Long.parseLong(line[0]) == id).findFirst();

            if (optionalStr.isPresent()) {
                String[] line = optionalStr.get();
                return Optional.of(new Order(Long.parseLong(line[0]), line[1], Integer.parseInt(line[2])));
            }
        } catch (CsvException | IOException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public void deleteOrder(Vehicle vehicle) {
        try (CSVReader reader = new CSVReader(new FileReader(ORDERS_PATH));
             CSVWriter writer = new CSVWriter(new FileWriter(ORDERS_PATH),
                     ',',
                     CSVWriter.NO_QUOTE_CHARACTER,
                     ' ',
                     "\n")) {
            List<String[]> allLines = reader.readAll();
            allLines.stream().forEach(line -> {
                if (Long.parseLong(line[0]) == vehicle.getId()) {
                    allLines.remove(line);
                }
            });

            writer.writeAll(allLines);
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }
}
