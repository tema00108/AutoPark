package by.incubator.application.vehicle.parser;

public interface ParserBreakingInterface {
    void writeOrder(long id, String defect, int amount);
    int findRow(long id);
    void deleteOrderString(int rowNumber);
}
