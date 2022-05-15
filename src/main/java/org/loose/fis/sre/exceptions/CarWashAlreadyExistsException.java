package org.loose.fis.sre.exceptions;

public class CarWashAlreadyExistsException extends Exception {

    private String carwashname;

    public CarWashAlreadyExistsException(String carwashname) {
        super(String.format("An account with the username %s already exists!", carwashname));
        this.carwashname =carwashname;
    }

    public String getCarWashName() {
        return carwashname;
    }
}
