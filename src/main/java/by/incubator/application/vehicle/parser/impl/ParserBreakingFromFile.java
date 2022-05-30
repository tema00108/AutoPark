package by.incubator.application.vehicle.parser.impl;

import by.incubator.application.vehicle.parser.ParserBreakingInterface;
import com.opencsv.*;
import com.opencsv.exceptions.CsvException;
import by.incubator.application.infrastructure.core.annotations.InitMethod;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

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

    public int findRow(long id) {
        try (CSVReader reader = new CSVReader(new FileReader(ORDERS_PATH))) {

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

    public void deleteOrderString(int rowNumber) {
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
}
