package application.exceptions;

import java.io.FileNotFoundException;
import java.io.IOException;

public class NotVehicleException extends Exception {

    public NotVehicleException(String message) {
        super(message);
    }
}

