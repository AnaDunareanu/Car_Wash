package org.loose.fis.sre.exceptions;

public class AppointmentNotAvailableException extends Exception {
    public AppointmentNotAvailableException() {
        super(String.format("Not available at this this. Please try another start time!"));
    }
}
