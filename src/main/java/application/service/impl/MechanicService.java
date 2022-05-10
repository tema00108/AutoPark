package application.service.impl;

import application.service.entity.Fixer;
import application.vehicles.Vehicle;
import com.opencsv.*;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MechanicService implements Fixer {
    private static final String[] details = {"Фильтр", "Фтулка", "Вал", "Ось",
            "Свеча","Масло","ГРМ","ШРУС"};
    private static final int MAX_DEFECTS = 5;
    private static final String ORDERS_PATH = "src/by/incubator/projects/autopark/files/orders.csv";
    private static final CSVParser parserColon = new CSVParserBuilder()
            .withSeparator(':')
            .withIgnoreQuotations(true)
            .build();

    public MechanicService() { }

    @Override
    public Map<String, Integer> detectBreaking(Vehicle vehicle) {
        Random rand = new Random();
        String defect = details[rand.nextInt(details.length)];
        int amount = rand.nextInt(MAX_DEFECTS);

        Map<String, Integer> defects = new HashMap<>();
        defects.put(defect, amount);

        if (amount != 0) {
            writeOrder(vehicle.getId(), defect, amount);
        }

        return defects;
    }

    @Override
    public void repair(Vehicle vehicle) {
        int row;
        int id = vehicle.getId();

        row = findRow(id);
        if (row < 0) {
            return;
        }

        deleteOrderString(row);
    }

    @Override
    public boolean isBroken(Vehicle vehicle) {
        int id = vehicle.getId();
        return findRow(id) > 0;
    }

    private void writeOrder(int id, String defect, int amount) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(ORDERS_PATH, true),
                ',',
                CSVWriter.NO_QUOTE_CHARACTER,
                ' ',
                "\n"))
        {
            String[] line = {id + ": " + defect + "," + amount};
            writer.writeNext(line, false);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private int findRow(int id) {
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(ORDERS_PATH))
                .withCSVParser(parserColon)
                .build()) {

            String [] nextLine = reader.readNext();
            int counter = 0;
            int idFound = 0;

            while (nextLine != null) {
                if (!nextLine[0].isEmpty()) {
                    idFound = Integer.parseInt(nextLine[0]);
                }

                if (idFound == id) {
                    return counter;
                }

                nextLine = reader.readNext();
                counter++;
            }

        } catch (CsvException | IOException e) {
            e.printStackTrace();
        }

        return -1;
    }

    private void deleteOrderString(int rowNumber) {
        try (CSVReader reader = new CSVReader(new FileReader(ORDERS_PATH))) {
            List<String[]> allLines = reader.readAll();
            allLines.remove(rowNumber);

            CSVWriter writer = new CSVWriter(new FileWriter(ORDERS_PATH),
                    ',',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    ' ',
                    "\n");

            writer.writeAll(allLines);
            writer.close();

        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    public int countDefects(Vehicle vehicle) {
        try (CSVReader reader = new CSVReader(new FileReader(ORDERS_PATH))) {
            int counter = 0;
            int row = findRow(vehicle.getId());

            if (row < 0) {
                return 0;
            }

            String[] line = reader.readAll().get(row);

            for (int i = 1; i < line.length; i += 2) {
                counter += Integer.parseInt(line[i]);
            }

            return counter;

        } catch (CsvException | IOException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
